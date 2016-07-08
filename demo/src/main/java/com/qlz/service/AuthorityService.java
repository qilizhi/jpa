/**
 * 
 */
package com.qlz.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qlz.dao.AuthorityDao;
import com.qlz.dao.ResourceDao;
import com.qlz.entities.Authority;
import com.qlz.entities.Resource;
import com.qlz.entities.RoleToAuthority;
import com.qlz.model.State;
import com.qlz.model.Tree;

/**
 * @author qilizhi
 * @date 2016��7��4�� ����10:53:39
 */
@Service
@Transactional
public class AuthorityService {

	@Autowired
	private AuthorityDao authorityDao;

	@Autowired
	private ResourceDao resourceDao;

	/**
	 * 获取权限树
	 * 
	 */

	public List<Tree> getTree(List<Authority> authList) {
		List<Tree> auTrees = new ArrayList<Tree>();
		for (Authority aut : authList) {
			Tree AT = new Tree();
			if (aut != null && aut.getId() != null) {
				AT.setId(aut.getId());
				AT.setText(aut.getName());
				AT.setParentId(aut.getParent().getId());
				List<Authority> authoritys = authorityDao.findByParentId(aut.getId());
				if (authoritys != null && authoritys.size() > 0) {
					AT.setChildren(getTree(authoritys));
				}
				auTrees.add(AT);
			}

		}

		return auTrees;
	}

	/**
	 * ��ȡ�������ṹ
	 * 
	 */

	public List<Tree> getAllTree() {

		List<Tree> ATs = new ArrayList<Tree>();
		List<Authority> authoritys = authorityDao.findByParentIdIsNull();
		if (authoritys != null && authoritys.size() > 0) {
			ATs = getTree(authoritys);
		}
		return ATs;

	}

	/**
	 * 标记已授权的树
	 * 
	 * 
	 */

	public List<Tree> tagTree(List<RoleToAuthority> roleToAuthoritys, List<Tree> authorityTrees) {
		List<Tree> ATs = new ArrayList<Tree>();
		for (Tree at : authorityTrees) {
			if (at != null) {
				State st = new State();
				st.setOpened(true);
				for (RoleToAuthority RT : roleToAuthoritys) {
					if (RT != null && RT.getAuthorityId() != null && at.getId() != null
							&& RT.getAuthorityId() == at.getId()) {
						/* st.setSelected(true); */
						if (at.getChildren() != null && at.getChildren().size() > 0) {
							st.setChecked(false);
						} else {
							st.setChecked(true);
						}

						roleToAuthoritys.remove(at);
					}
				}
				at.setState(st);
			}
			if (at.getChildren() != null && at.getChildren().size() > 0) {
				at.setChildren(tagTree(roleToAuthoritys, at.getChildren()));
			}
			ATs.add(at);
		}

		return ATs;
	}

	/**
	 * @param authority
	 */
	public void insertSelective(Authority authority) {
		authorityDao.saveAndFlush(authority);
	}

	/**
	 * @param auth
	 */
	public void updateByPrimaryKeySelective(Authority auth) {
		authorityDao.saveAndFlush(auth);

	}

	/**
	 * @param id
	 */
	public void deleteByPrimaryKey(Long id) {
		authorityDao.delete(id);

	}

	/**
	 * @param authorityId
	 * @param resourceIdsList
	 */
	public void updateBydelete(Long authorityId, List<Long> resourceIdsList) {
		Set<Resource> resources = new HashSet<Resource>();
		resources = resourceDao.findByIdIn(resourceIdsList);
		Authority authority = authorityDao.findOne(authorityId);
		authority.setResources(resources);
		authorityDao.save(authority);
	}

}
