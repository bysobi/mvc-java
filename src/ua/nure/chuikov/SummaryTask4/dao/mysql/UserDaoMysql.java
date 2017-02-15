package ua.nure.chuikov.SummaryTask4.dao.mysql;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.chuikov.SummaryTask4.dao.UserDao;
import ua.nure.chuikov.SummaryTask4.db.DBManager;
import ua.nure.chuikov.SummaryTask4.entity.RoleEnum;
import ua.nure.chuikov.SummaryTask4.entity.User;

public class UserDaoMysql implements UserDao {

    private static final Logger LOG = Logger.getLogger(UserDaoMysql.class);

    // //////////////////////////////////////////////////////////
    // SQL queries
    // //////////////////////////////////////////////////////////
    private static final String SQL_SELECT_ALL = "SELECT * FROM `user` ";
    private static final String SQL_SELECT_BY_LOGIN = "SELECT * FROM user WHERE userLogin = ?";
    private static final String SQL_UPDATE_USER_BY_ID = "UPDATE user  SET firstName = ?,secondName = ?,lastName = ?,"
	    + "userLogin =?,userPassword = ? ,role = ?,locale=?,isActive = ? WHERE id = ?";
    private static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM `user` WHERE id = ?";
    private static final String SQL_SELECT_USER_BY_ROLE = "SELECT * FROM `user` WHERE role = ?";
    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM `user` WHERE id = ?";
    private static final String SQL_REGISTRATION_OF_USER = "INSERT INTO `user`( `firstName`, `secondName`, `lastName`, `userLogin`,"
	    + "`userPassword`, `role`, `locale`, `isActive`) VALUES (?,?,?,?,?,?,?,?)";

    private DBManager dbManager;

    public UserDaoMysql(DBManager dbManager) {
	this.dbManager = dbManager;
    }

    private User extractUser(ResultSet rs) throws SQLException {
	User user = new User();
	user.setId(rs.getInt("id"));
	user.setFirstName(rs.getString("firstName"));
	user.setSecondName(rs.getString("secondName"));
	user.setLastName(rs.getString("lastName"));
	user.setUserLogin(rs.getString("userLogin"));
	user.setUserPassword(rs.getString("userPassword"));
	user.setRole(RoleEnum.valueOf(rs.getString("role").toUpperCase()));
	user.setLocale(rs.getString("locale"));
	user.setActive(rs.getBoolean("isActive"));
	return user;
    }

    @Override
    public User getUserByLogin(String userLogin) throws UnsupportedEncodingException {
	Connection connection = null;
	User user = null;
	try {
	    connection = dbManager.getConnection();
	    PreparedStatement prst = connection.prepareStatement(SQL_SELECT_BY_LOGIN);
	    prst.setString(1, userLogin);
	    //LOG.debug(prst);
	    ResultSet rs = prst.executeQuery();
	    if (rs.next()) {
		user = extractUser(rs);
	    }
	    rs.close();
	} catch (SQLException e) {
	    dbManager.rollBack(connection);
	} finally {
	    dbManager.commitAndClose(connection);
	}
	return user;
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
	Connection connection = null;
	List<User> listUser = new ArrayList<>();
	try {
	    connection = dbManager.getConnection();
	    PreparedStatement prst = connection.prepareStatement(SQL_SELECT_ALL);
	    //LOG.debug(prst);
	    ResultSet rs = prst.executeQuery();
	    while (rs.next()) {
		User user = extractUser(rs);
		listUser.add(user);
	    }
	    rs.close();
	} catch (SQLException e) {
	    dbManager.rollBack(connection);
	} finally {
	    dbManager.commitAndClose(connection);
	}
	return listUser;

    }

    @Override
    public void updateUserById(int userId, User user) throws SQLException {
	Connection connection = null;
	try {
	    connection = dbManager.getConnection();
	    PreparedStatement prst = connection.prepareStatement(SQL_UPDATE_USER_BY_ID);
	    int index = 1;
	    prst.setString(index++, user.getFirstName());
	    prst.setString(index++, user.getSecondName());
	    prst.setString(index++, user.getLastName());
	    prst.setString(index++, user.getUserLogin());
	    prst.setString(index++, user.getUserPassword());
	    prst.setString(index++, user.getRole().toString());
	    prst.setString(index++, user.getLocale());
	    prst.setBoolean(index++, user.isActive());
	    prst.setInt(index++, userId);
	    LOG.debug(prst);
	    prst.executeUpdate();
	} catch (SQLException e) {
	    dbManager.rollBack(connection);
	} finally {
	    dbManager.commitAndClose(connection);
	}
    }

    @Override
    public Collection<User> getByRole(String role) throws SQLException {
	Connection connection = null;
	List<User> listUsers = new ArrayList<>();

	try {
	    connection = dbManager.getConnection();
	    PreparedStatement prst = connection.prepareStatement(SQL_SELECT_USER_BY_ROLE);
	    LOG.debug(prst);
	    prst.setString(1, role);
	    ResultSet rs = prst.executeQuery();
	    while (rs.next()) {
		User user = extractUser(rs);
		listUsers.add(user);
	    }
	    rs.close();
	} catch (SQLException e) {
	    dbManager.rollBack(connection);
	} finally {
	    dbManager.commitAndClose(connection);
	}
	return listUsers;
    }

    @Override
    public void removeUserById(int userId) throws SQLException {
	Connection connection = null;
	try {
	    connection = dbManager.getConnection();
	    PreparedStatement prst = connection.prepareStatement(SQL_DELETE_USER_BY_ID);
	    prst.setInt(1, userId);
	    LOG.debug(prst);
	    prst.executeUpdate();
	} catch (SQLException e) {
	    dbManager.rollBack(connection);
	} finally {
	    dbManager.commitAndClose(connection);
	}
    }

    @Override
    public User getUserById(int userId) throws SQLException {
	Connection connection = null;
	User user = null;
	try {
	    connection = dbManager.getConnection();
	    PreparedStatement prst = connection.prepareStatement(SQL_SELECT_USER_BY_ID);
	    prst.setInt(1, userId);
	    LOG.debug(prst);
	    ResultSet rs = prst.executeQuery();
	    while (rs.next()) {
		user = extractUser(rs);
	    }
	    rs.close();
	} catch (SQLException e) {
	    dbManager.rollBack(connection);
	} finally {
	    dbManager.commitAndClose(connection);
	}
	return user;
    }

    /*
     * @Override public User getUserByLoginAndPassword(String userLogin, String
     * userPassword) throws SQLException { User user = null; Connection conn =
     * null; try { conn = dbManager.getConnection(); PreparedStatement prst =
     * conn.prepareStatement(SQL_SELECT_BY_LOGIN_AND_PASSWORD);
     * prst.setString(1, userLogin); prst.setString(2, userPassword);
     * 
     * LOG.debug(prst); ResultSet rs = prst.executeQuery(); if (rs.next()) {
     * user = extractUser(rs); } rs.close(); } catch (SQLException e) {
     * dbManager.rollBack(conn); } finally { dbManager.commitAndClose(conn); }
     * 
     * return user; }
     */

    @Override
    public int createUser(User user) throws SQLException {
	Connection connection = null;
	int generatedId = 0;

	try {
	    connection = dbManager.getConnection();
	    PreparedStatement prst = connection.prepareStatement(SQL_REGISTRATION_OF_USER,
		    PreparedStatement.RETURN_GENERATED_KEYS);
	    int index = 1;
	    prst.setString(index++, user.getFirstName());
	    prst.setString(index++, user.getSecondName());
	    prst.setString(index++, user.getLastName());
	    prst.setString(index++, user.getUserLogin());
	    prst.setString(index++, user.getUserPassword());
	    prst.setString(index++, user.getRole().toString().toUpperCase());
	    prst.setString(index++, user.getLocale());
	    prst.setBoolean(index++, user.isActive());
	 //   LOG.debug(prst);
	    prst.executeUpdate();
	    ResultSet rs = prst.getGeneratedKeys();
	    if (rs.next()) {
		generatedId = rs.getInt(1);
	    }
	    rs.close();
	} catch (SQLException exception) {
	    dbManager.rollBack(connection);
	} finally {
	    dbManager.commitAndClose(connection);
	}
	return generatedId;
    }

    public static void main(String[] args) throws SQLException, UnsupportedEncodingException {
	// System.getProperties().entrySet().forEach(System.out::println);

	// UserDaoMysql dao = new UserDaoMysql(new DBManager());

	// User u = new User("Харченко", "Вячеслав", "Сергеевич", "ВС", "0000",
	// 2, "ру", true);
	// dao.createUser(u);

	// System.out.println(dao.getUserByLogin("ВС"));

	// System.out.println(dao.getAllUsers());
	// System.out.println(dao.getUserByLogin("Вика"));
	// System.out.println(dao.getAllUsers());

	// System.out.println(dao.getUserByLoginAndPassword("vika", "2511"));

	/*
	 * System.out.println(dao.getUserById(1));
	 * System.out.println(dao.getByRoleId(2));
	 */

	// REMOVE
	/*
	 * System.out.println("Removing"); dao.deleteUserById(4);
	 * System.out.println(dao.getAllUsers());
	 */

	// UPDATE
	/*
	 * System.out.println("UPPDATING USER"); dao.updateUserById(4, u);
	 * System.out.println(dao.getUserById(4));
	 * 
	 */

    }

}
