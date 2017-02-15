package code.university.web.filter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import code.university.dao.mysql.UserDaoMysql;
import code.university.db.DBManager;
import code.university.entity.RoleEnum;
import code.university.entity.User;
import code.university.web.path.Path;

public class SecurityFilter implements Filter {
    private Map<RoleEnum, List<String>> accessMap = new TreeMap<>();
    private List<String> outOfControl = new ArrayList<>();
    private List<String> common = new ArrayList<>();

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
 }
    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	    throws IOException, ServletException {

	HttpServletRequest req = (HttpServletRequest) request;
	UserDaoMysql userDao = new UserDaoMysql(new DBManager());
	// need to refresh user in session
	HttpSession session = req.getSession(false);
	User aut_user = null;
	if (session != null && (aut_user = (User) session.getAttribute("aut_user")) != null) {
	    try {
		aut_user = userDao.getUserById(aut_user.getId());
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	    session.setAttribute("aut_user", aut_user);
	}

	if (isOutOfControl(req)) {
	    // all ok, access granted
	    chain.doFilter(request, response);
	    return;
	}

	if (hasAccess(req)) {
	    // user is logged in, but account is not active
	    if (aut_user != null && !aut_user.isActive()) {
		((HttpServletResponse) response).sendRedirect(Path.PAGE_ERROR);
		return;
	    }
	    // all ok, access granted
	    chain.doFilter(request, response);
	    return;
	}

	// user is not logged in, sending redirect to login page
	if (aut_user == null) {
	    ((HttpServletResponse) response).sendRedirect(Path.PAGE_LOGIN);
	    return;
	}

	((HttpServletResponse) response).sendRedirect(Path.COMMAND_ACCESS_DENIED);

    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
	accessMap.put(RoleEnum.ROLE_ADMIN, asList(fConfig.getInitParameter("admin")));
	accessMap.put(RoleEnum.ROLE_STUDENT, asList(fConfig.getInitParameter("student")));

	outOfControl = asList(fConfig.getInitParameter("outOfControll"));
	common = asList(fConfig.getInitParameter("common"));
    }

    private boolean hasAccess(HttpServletRequest req) {
	String command = req.getParameter("command");
	if (command == null) {
	    return true;
	}

	HttpSession session = req.getSession(false);
	if (session == null) {
	    return false;
	}

	User user = (User) session.getAttribute("aut_user");
	if (user == null || user.getRole() == null) {
	    return false;
	}

	List<String> accessList = accessMap.get(user.getRole());
	if (common.contains(command) || accessList != null && accessList.contains(command)) {
	    return true;
	}
	return false;

    }

    private boolean isOutOfControl(HttpServletRequest req) {
	String command = req.getParameter("command");
	if (command == null) {
	    return true;
	}
	return outOfControl.contains(command);
    }

    private List<String> asList(String str) {
	String[] parts = str.split(";");
	if (parts != null) {
	    return Arrays.asList(parts);
	}
	return new ArrayList<>();
    }

}
