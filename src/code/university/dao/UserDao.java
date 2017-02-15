/**
 * 
 */
package code.university.dao;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Collection;

import code.university.entity.User;

/**
 * @author Yarik Chuikov
 *
 */
public interface UserDao {
    public User getUserByLogin(String email) throws SQLException, UnsupportedEncodingException;// Executed

    public Collection<User> getAllUsers() throws SQLException;// Executed

    public User getUserById(int id) throws SQLException;// Executed

    public Collection<User> getByRole(String role) throws SQLException;// Executed

    public void removeUserById(int userId) throws SQLException;// Executed

    public int createUser(User user) throws SQLException;// Executed
  
    public void updateUserById(int id,User user) throws SQLException;// Executed
}