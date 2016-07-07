/**
 * 
 */
package com.qlz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qlz.dao.RoleDao;
import com.qlz.entities.Role;
import com.qlz.entities.UserToRole;
import com.qlz.model.State;
import com.qlz.model.Tree;

/**
 * @author qilizhi
 * @date 2016年7月4日 下午5:13:44
 */
@Service
@Transactional
public class RoleService {

	@Autowired
	private RoleDao roleDao;

	public List<Role> findAll() {

		return roleDao.findAll();

	}

	/**
	 * 递 归生成结构树
	 * 
	 */

	public List<Tree> getTree(List<Role> authList) {
		List<Tree> auTrees = new ArrayList<Tree>();
		for (Role aut : authList) {
			Tree AT = new Tree();
			if (aut != null && aut.getId() != null) {
				AT.setId(aut.getId());
				AT.setText(aut.getName());
				AT.setParentId(aut.getParentId());
				// 查询 子Node
				List<Role> authoritys = roleDao.findByParentId(aut.getId());
				if (authoritys != null && authoritys.size() > 0) {
					AT.setChildren(getTree(authoritys));
				}
				auTrees.add(AT);
			}

		}

		return auTrees;
	}

	/**
	 * 获取所有树结构
	 * 
	 */

	public List<Tree> getAllTree() {

		List<Tree> ATs = new ArrayList<Tree>();
		List<Role> roles = roleDao.findByParentIdIsNull();
		if (roles != null && roles.size() > 0) {
			ATs = getTree(roles);
		}
		return ATs;

	}

	/**
	 * 递归标识已受权的树
	 * 
	 * 
	 */

	public List<Tree> tagTree(List<UserToRole> userToRoles, List<Tree> roleTrees) {
		List<Tree> ATs = new ArrayList<Tree>();
		for (Tree at : roleTrees) {
			if (at != null) {
				for (UserToRole RT : userToRoles) {
					if (RT != null && RT.getRoleId() != null && at.getId() != null && RT.getRoleId() == at.getId()) {
						State st = new State();
						st.setChecked(true);
						at.setState(st);
						;
						userToRoles.remove(at);
					}
				}
			}
			if (at.getChildren() != null && at.getChildren().size() > 0) {
				at.setChildren(tagTree(userToRoles, at.getChildren()));
			}
			ATs.add(at);
		}

		return ATs;
	}

	/**
	 * @param id
	 */
	public void deleteByPrimaryKey(Long id) {
		roleDao.delete(id);
	}

	/**
	 * @param role
	 */
	public void insertSelective(Role role) {
		roleDao.save(role);
	}

	/**
	 * @param role
	 */
	public void updateByPrimaryKeySelective(Role role) {
		roleDao.saveAndFlush(role);

	}

}
