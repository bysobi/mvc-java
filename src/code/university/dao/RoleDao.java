/**
 * 
 */
package code.university.dao;

import code.university.entity.RoleEnum;
/**
 * @author Yarik
 *
 */
public interface RoleDao {
    public RoleEnum listAllRoles();
    public RoleEnum loadRole(String Role);
   
}
