package code.university.dao.mysql;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

import code.university.dao.UserDao;
import code.university.db.DBManager;
import code.university.entity.RoleEnum;
import code.university.entity.User;

public class UserDaoMysql implements UserDao {

    private static final Logger LOG = Logger.getLogger(UserDaoMysql.class);

    // //////////////////////////////////////////////////////////
    // SQL queries
    // //////////////////////////////////////////////////////////
    private static final String SQL_SELECT_ALL = "SELECT * FROM `user` ";
    private static final String SQL_SELECT_BY_LOGIN = "SELECT * FROM user WHERE email = ?";
    private static final String SQL_UPDATE_USER_BY_ID = "UPDATE user  SET firstName = ?,secondName = ?,lastName = ?,"
	    + "userPassword = ? ,role = ?,locale=?,isActive = ? WHERE id = ?";
    private static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM `user` WHERE id = ?";
    private static final String SQL_SELECT_USER_BY_ROLE = "SELECT * FROM `user` WHERE role = ?";
    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM `user` WHERE id = ?";
    private static final String SQL_REGISTRATION_OF_USER = "INSERT INTO `user`(`f_name_ukr`,`s_name_ukr`,`m_name_ukr`,`f_name_eng`,`s_name_eng`,`m_name_eng`,`email`, `phone`, `sec_email`, `department`, `password`, `created_at`, `updated_at`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private DBManager dbManager;

    public UserDaoMysql(DBManager dbManager) {
	this.dbManager = dbManager;
    }

    
    
    private User extractUser(ResultSet rs) throws SQLException {
	User user = new User();
	user.setId(rs.getInt("id"));
	user.setFirstNameEng(rs.getString("f_name_eng"));
	user.setSecondNameEng(rs.getString("s_name_eng"));
	user.setMiddleNameEng(rs.getString("m_name_eng"));
	user.setFirstNameUkr(rs.getString("f_name_eng"));
	user.setSecondNameUkr(rs.getString("s_name_eng"));
	user.setMiddleNameUkr(rs.getString("m_name_eng"));
	user.setEmail(rs.getString("email"));
	user.setPhone(rs.getString("phone"));
	user.setSecondEmail(rs.getString("sec_email"));
	user.setDepartment(rs.getInt("department"));
	user.setPassword(rs.getString("password"));
	return user;
    }

    @Override
    public User getUserByLogin(String email) throws UnsupportedEncodingException {
	Connection connection = null;
	User user = null;
	try {
	    connection = dbManager.getConnection();
	    PreparedStatement prst = connection.prepareStatement(SQL_SELECT_BY_LOGIN);
	    prst.setString(1, email);
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
	    prst.setString(index++, user.getFirstNameEng());
	    prst.setString(index++, user.getSecondNameEng());
	    prst.setString(index++, user.getMiddleNameEng());
	    prst.setString(index++, user.getFirstNameUkr());
	    prst.setString(index++, user.getSecondNameUkr());
	    prst.setString(index++, user.getMiddleNameUkr());
	    prst.setString(index++, user.getEmail());
	    prst.setString(index++, user.getPhone());
	    prst.setString(index++, user.getDepartment().toString());
	    prst.setString(index++, user.getSecondEmail());
	    prst.setString(index++, user.getPassword());
	   
	    prst.setString(index++, user.getRole().toString());

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
	    prst.setString(index++, user.getFirstNameEng());
	    prst.setString(index++, user.getSecondNameEng());
	    prst.setString(index++, user.getMiddleNameEng());
	    prst.setString(index++, user.getFirstNameUkr());
	    prst.setString(index++, user.getSecondNameUkr());
	    prst.setString(index++, user.getMiddleNameUkr());
	    prst.setString(index++, user.getEmail());
	    prst.setString(index++, user.getPhone());
	    prst.setString(index++, user.getSecondEmail());
	    prst.setInt(index++, 1);
	    prst.setString(index++, user.getPassword());
	    prst.setTimestamp(index++, this.getTimestamp());
	    prst.setTimestamp(index++, this.getTimestamp());
	    
	    
	    
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

	// User u = new User("��������", "��������", "���������", "��", "0000",
	// 2, "��", true);
	// dao.createUser(u);

	// System.out.println(dao.getUserByLogin("��"));

	// System.out.println(dao.getAllUsers());
	// System.out.println(dao.getUserByLogin("����"));
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
    
    protected Timestamp getTimestamp(){
	return new Timestamp(System.currentTimeMillis());
    }

}
