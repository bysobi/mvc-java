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

import code.university.dao.DepartmentDao;
import code.university.dao.UserDao;
import code.university.db.DBManager;
import code.university.entity.Departement;
import code.university.entity.RoleEnum;
import code.university.entity.User;

public class DepartmentDaoMysql implements DepartmentDao {

    private static final Logger LOG = Logger.getLogger(DepartmentDaoMysql.class);

    // //////////////////////////////////////////////////////////
    // SQL queries
    // //////////////////////////////////////////////////////////
    private static final String SQL_SELECT_ALL = "SELECT * FROM `department` ";
   
    private DBManager dbManager;

    public DepartmentDaoMysql(DBManager dbManager) {
	this.dbManager = dbManager;
    }
    
    private Departement extractDepartment(ResultSet rs) throws SQLException {
	Departement department = new Departement();
	department.setId(rs.getInt("id"));
	department.setParentId(rs.getInt("parent_id"));
	department.setName(rs.getString("name"));
	return department;
    }
  
    public List<Departement> getAll() throws SQLException {
	Connection connection = null;
	List<Departement> list = new ArrayList<>();
	try {
	    connection = dbManager.getConnection();
	    PreparedStatement prst = connection.prepareStatement(SQL_SELECT_ALL);
	    ResultSet rs = prst.executeQuery();
	    while (rs.next()) {
		Departement department = extractDepartment(rs);
		list.add(department);
	    }
	    rs.close();
	} catch (SQLException e) {
	    dbManager.rollBack(connection);
	} finally {
	    dbManager.commitAndClose(connection);
	}
	return list;
    }

    protected Timestamp getTimestamp(){
	return new Timestamp(System.currentTimeMillis());
    }
}
