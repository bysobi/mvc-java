package code.university.web.listener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.PropertyConfigurator;

public class ContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext servCont = sce.getServletContext();
		initI18n(servCont);
		//initLog4J(servCont);
	}
	
	private void initI18n(ServletContext servletContext) {
		String localesValue = servletContext.getInitParameter("locales");
		if (localesValue == null || localesValue.isEmpty()) {
			//LOG.warn("'locales' init parameter is empty, the default encoding will be used");
		} else {
			List<String> locales = new ArrayList<String>();
			String[] locs = localesValue.split(";");
			//LOG.debug("Application attribute set: locales --> " + locales);
			servletContext.setAttribute("locales", Arrays.asList(locs));
		}		
	}
	
	private void initLog4J(ServletContext servletContext) {
		//log("Log4J initialization started");
		try {
			PropertyConfigurator.configure(servletContext.getRealPath(
							"WEB-INF/log4j.properties"));
		} catch (Exception ex) {
			//LOG.error("Cannot configure Log4j", ex);			
		}		
//		log("Log4J initialization finished");
//		LOG.debug("Log4j has been initialized");
	}

}
