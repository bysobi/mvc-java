/**
 * 
 */
package ua.nure.chuikov.SummaryTask4.dao;

import ua.nure.chuikov.SummaryTask4.entity.RoleEnum;
/**
 * @author Yarik
 *
 */
public interface RoleDao {
    public RoleEnum listAllRoles();
    public RoleEnum loadRole(String Role);
   
}
