package ua.nure.chuikov.SummaryTask4.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ua.nure.chuikov.SummaryTask4.dao.RoleDao;
import ua.nure.chuikov.SummaryTask4.db.DBManager;
import ua.nure.chuikov.SummaryTask4.entity.RoleEnum;

public class RoleDaoMySQL implements RoleDao {

    // //////////////////////////////////////////////////////////
    // SQL queries
    // //////////////////////////////////////////////////////////
    private static final String SQL_SELECT_ALL_ROLES = "SELECT * FROM `role`";
    private static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM `user` WHERE `role` = ?";

    private DBManager dbmanager;

    public RoleDaoMySQL(DBManager dbmanager) {
	this.dbmanager = dbmanager;
    }

    private RoleEnum extractRole(ResultSet rs) throws SQLException {
	RoleEnum roleEnum = null;

	try {
	    roleEnum = RoleEnum.valueOf(rs.getString("role").toUpperCase());
	} catch (Exception e) {
	    e.printStackTrace();
	    
	}
	return roleEnum;
    }

    @Override
    public RoleEnum listAllRoles() {
	Connection connection = null;
	RoleEnum role = null;
	try {
	    connection = dbmanager.getConnection();

	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(SQL_SELECT_ALL_ROLES);
	    while (rs.next()) {
		role = extractRole(rs);
	  }
	    st.close();
	} catch (SQLException e) {
	    dbmanager.rollBack(connection);
	} finally {
	    dbmanager.commitAndClose(connection);
	}
	return role;
    }

    @Override
    public RoleEnum loadRole(String Role) {
	RoleEnum role = null;
	Connection connection = null;
	try {
	    connection = dbmanager.getConnection();
	    PreparedStatement prst = connection.prepareStatement(SQL_SELECT_USER_BY_ID);
	    prst.setString(1, Role);
	    ResultSet rs = prst.executeQuery();
	    if (rs.next()) {
		role = extractRole(rs);
	    }
	    prst.close();

	} catch (SQLException e) {
	    dbmanager.rollBack(connection);
	} finally {
	    dbmanager.commitAndClose(connection);
	}
	return role;
    }

    public static void main(String[] args) {
	RoleDaoMySQL roleDao = new RoleDaoMySQL(new DBManager());
	System.out.println(roleDao.listAllRoles());
	System.out.println("Role By name" + roleDao.loadRole("ROLE_ADMIN"));
	// roleDao.addNewRole("ROle_TEacher".toUpperCase());
	System.out.println(roleDao.listAllRoles());
    }

  
}
