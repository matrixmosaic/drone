/**
 * 
 */
package com.musala.drones.service.implementation;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.musala.drones.model.Role;
import com.musala.drones.repository.RoleDao;
import com.musala.drones.service.RoleService;


/**
 * @author George J. Budeba
 *
 */


@Service
public class RoleServiceImp implements RoleService{

	@Autowired
	private RoleDao roleDao;
	
    @PersistenceContext
    private EntityManager entityManager;

	 Query query;
	
	@Transactional
	public void addRole(Role role)  {
		
		//Role role = new Role(roleId, roleName,  roleCode,  description);
		
		roleDao.save(role);
		
	}
	
	


	@Transactional
	public void editRole(int roleId, String name, String description) {
		Role role = findRoleById(roleId);
		
		
		if(name != null){
			role.setRoleName(name);
		}
		if(description!= null){
			role.setDescription(description);
		}
		
		
		roleDao.save(role);		
		
		/**end setting role */
	}

	@Transactional
	public void deleteRole(int roleId) {
		Role role = findRoleById(roleId);
		roleDao.delete(role);
	}

	@Transactional
	public Role findRoleById(int roleId) {
		return roleDao.getReferenceById(roleId); 
	}


	@Transactional
	public Role findRoleByName(String roleName){
		
		return roleDao.findByRoleName(roleName);
	}

	
	@Transactional
	public List<Role> findAllRoles() {
		return roleDao.findAll();
	}

	@Transactional
	public String findRoleNameById(int roleId) {
		return roleDao.findById(roleId).get().getRoleName();
	}

	@Transactional
	public void saveRole(Role role) {
		roleDao.save(role);
		
	}




	@Override
	public Role addAndGetInstance(Role roleForm) {
		return roleDao.save(roleForm);
	}




	@Override
	public boolean isRoleNameOccupied(String roleName) {
		return (roleDao.findFirstByRoleNameIgnoreCase(roleName) != null) ;

	}




	@SuppressWarnings("unchecked")
	@Override
	public Role getRoleByIdNQ(Integer roleId) {
		query = entityManager.createNamedQuery("RoleByIdNamedQuery")
                .setParameter("roleId", roleId);

        return (Role) DataAccessUtils.singleResult(query.getResultList());

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role>   getRoleByIdLListNQ(List<Integer> roleIds) {
		query = entityManager.createNamedQuery("RoleByIdListNamedQuery")
                .setParameter("roleIds", roleIds);

	    return ( query.getResultList());

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Role getRoleByCodeNamedQuery(String roleCode) {
		query = entityManager.createNamedQuery("RoleByCodeNamedQuery")
                .setParameter("roleCode", roleCode);

        return (Role) DataAccessUtils.singleResult(query.getResultList());

	}


	
}
