package code.university.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class DBManager {

    private static final Logger LOGDB = Logger.getLogger(DBManager.class);

    /*
     * private static final String CONNECTION_URL; private static final String
     * CONNECTION_USER; private static final String CONNECTION_PASSWORD;
     * 
     * static { // loading connection params from properties ResourceBundle
     * resourse = ResourceBundle.getBundle("db_mysql"); CONNECTION_URL =
     * resourse.getString("CONNECTION_URL"); CONNECTION_USER =
     * resourse.getString("CONNECTION_USER"); CONNECTION_PASSWORD =
     * resourse.getString("CONNECTION_PASSWORD"); try {
     * Class.forName("com.mysql.jdbc.Driver"); } catch (ClassNotFoundException
     * e) { throw new IllegalStateException("jdbc driver was not found"); } }
     */
    // //////////////////////////////////////////////////////////
    // singleton
    // //////////////////////////////////////////////////////////

    private static DBManager instance;

    public DBManager() {
    }

    public synchronized static final DBManager getInstance() {
	if (instance == null) {
	    instance = new DBManager();
	}
	return instance;

    }

    /**
     * Returns a DB connection from the Pool Connections
     * 
     * 
     * @return DB connection.
     */
    /*
     * public Connection getConnection() throws SQLException { Connection
     * connection = null; try { System.out.println("before context"); DataSource
     * dataSource = getDataSourceByKolesnikovApproach(); // DataSource
     * dataSource = getDataSourceByAlexApproach(); connection =
     * dataSource.getConnection(); } catch (NamingException ex) { LOGDB.error(
     * "Cannot obtain a connection from the pool", ex); } return connection; }
     * 
     * public DataSource getDataSourceByAlexApproach() throws NamingException,
     * SQLException { Context initContext = new InitialContext(); return
     * (DataSource) initContext.lookup("java:comp/env/jdbc/test"); }
     * 
     * public DataSource getDataSourceByKolesnikovApproach() throws
     * NamingException { Context initContext = new InitialContext();
     * System.out.println("context"); Context envContext = (Context)
     * initContext.lookup("java:comp/env"); return (DataSource)
     * envContext.lookup("jdbc/test"); }
     */

    public Connection getConnection() throws SQLException {
	Connection connection = null;
	try { // get JNDI-context

	    Context initContext = new InitialContext();
	    Context envContext = (Context) initContext.lookup("java:comp/env");
	    // get context
	    DataSource ds = (DataSource) envContext.lookup("jdbc/test");
	    connection = ds.getConnection();
	} catch (NamingException ex) {
	    LOGDB.error("Cannot obtain a connection from the pool", ex);
	}

	/*
	 * Connection connection = DriverManager.getConnection(CONNECTION_URL,
	 * CONNECTION_USER, CONNECTION_PASSWORD);
	 * connection.setAutoCommit(false);
	 */
	return connection;
    }

    public void commitAndClose(Connection connection) {
	if (connection != null) {
	    try {
		connection.commit();
		connection.close();
	    } catch (SQLException exc) {
		System.out.println(exc);
	    }
	}
    }

    public void rollBack(Connection connection) {
	if (connection != null) {
	    try {
		connection.rollback();
	    } catch (SQLException e) {
		System.out.println(e);
	    }
	}
    }

    /*
     * public static User extractUser(ResultSet rs) throws SQLException { User
     * user = new User(); user.setFirstName(rs.getString("firstName"));
     * user.setSecondName(rs.getString("secondName"));
     * user.setLastName(rs.getString("lastName"));
     * user.setUserLogin(rs.getString("userLogin"));
     * user.setUserPassword(rs.getString("userPassword"));
     * user.setLocale(rs.getString("locale"));
     * user.setRoleId(rs.getInt("roleId"));
     * user.setActive(rs.getBoolean("isActive")); return user; }
     */

    /*
     * public User getUserByLogin(String userLogin) throws SQLException { User
     * user = null; Connection con = null; try { con = getConnection();
     * PreparedStatement prst = con.prepareStatement(SQL_SELECT_BY_LOGIN);
     * prst.setString(1, userLogin); ResultSet rs = prst.executeQuery(); if
     * (rs.next()) { user = extractUser(rs); } prst.close(); } catch
     * (SQLException e) { rollBack(con); } finally { commitAndClose(con); }
     * return user; }
     */

    /*
     * public static void main(String[] args) throws SQLException {
     * System.out.println(CONNECTION_URL); DBManager db =
     * DBManager.getInstance(); UserDaoMysql dao = new UserDaoMysql();
     * 
     * 
     * // System.out.println(dao.getUserById(1)); //
     * System.out.println(dao.getUserByLogin("vika")); //
     * System.out.println(dao.getAllUsers(new User())); //
     * System.out.println(dao.getByRoleId(1));
     * 
     * 
     * //demonstration deleting System.out.println("before");
     * System.out.println(dao.getAllUsers(new User()));
     * //System.out.println(dao.getByRoleId(1)); System.out.println("deleting");
     * dao.remove(3); System.out.println(dao.getAllUsers(new User()));
     * 
     * 
     * }
     */
}
