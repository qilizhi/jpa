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
import com.qlz.entities.User;
import com.qlz.model.State;
import com.qlz.model.Tree;

/**
 * 
 * @author qilizhi
 * @date 2016年7月12日 下午6:15:39
 */
@Service
@Transactional
public class RoleService {

	@Autowired
	private RoleDao roleDao;

	public List<Role> findAll() {
		return roleDao.findAll();
	}

	public Role saveOrUpdate(Role r) {
	/*	if (r.getParent() != null && r.getParent().getId() != null) {
			Role rp=roleDao.getOne(r.getParent().getId());
			if(rp!=null)
			r.setParent(rp);
		}*/
		return roleDao.saveOrUpdate(r);
	}

	public Role findOne(Long id) {
		return roleDao.findOne(id);

	}

	/**
	 * 角色树
	 * 
	 */

	public List<Tree> getTree(List<Role> roleList) {
		List<Tree> roleTrees = new ArrayList<Tree>();
		for (Role role : roleList) {
			Tree AT = new Tree();
			if (role != null && role.getId() != null) {
				AT.setId(role.getId());
				AT.setText(role.getName());
				AT.setParentId(role.getParent() == null ? null : role
						.getParent().getId());
				//
				List<Role> roles = role.getChildren();
				if (!roles.isEmpty()) {
					AT.setChildren(getTree(roles));
				}
				roleTrees.add(AT);
			}

		}

		return roleTrees;
	}

	/**
	 * ��ȡ�������ṹ
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
	 * �ݹ��ʶ����Ȩ����
	 * 
	 * 
	 */

	public List<Tree> tagTree(User user, List<Tree> roleTrees) {
		List<Tree> ATs = new ArrayList<Tree>();
		for (Tree at : roleTrees) {
			if (at != null) {
				for (Role RT : user.getRoles()) {
					if (RT != null && RT.getId() != null && at.getId() != null
							&& RT.getId() == at.getId()) {
						State st = new State();
						st.setChecked(true);
						at.setState(st);
						user.getRoles().remove(at);
					}
				}
			}
			if (at.getChildren() != null && at.getChildren().size() > 0) {
				at.setChildren(tagTree(user, at.getChildren()));
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
		roleDao.save(role);

	}

}
