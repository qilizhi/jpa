/**
 * 
 */
package com.qlz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qlz.dao.AuthorityToResourceDao;
import com.qlz.entities.AuthorityToResource;
import com.qlz.entities.Resource;

/**
 * @author qilizhi
 * @date 2016年7月4日 下午10:53:51
 */
@Service
@Transactional
public class AuthorityToResourceService {

	@Autowired
	private AuthorityToResourceDao authorityToResourceDao;
	/**
	 * @param authorityId
	 * @param pageBounds
	 * @return
	 */



	/**更新资源
	 * @param authorityId
	 * @param idsList
	 */
	public void updateBydelete(Long authorityId, List<Long> resourceIdsList) {

         authorityToResourceDao.deleteByAuthorityId(authorityId);
         List<AuthorityToResource> atrs=new ArrayList<AuthorityToResource>();
         for(Long resourceId:resourceIdsList){
        	 AuthorityToResource atr=new AuthorityToResource();
        	 atr.setAuthorityId(authorityId);
        	 atr.setResourceId(resourceId);
        	 atrs.add(atr);
         }
         authorityToResourceDao.save(atrs);
		
		
	}



	/**
	 * @param authorityToResource
	 * @return
	 */
	public List<AuthorityToResource> selectByExample(AuthorityToResource authorityToResource) {
		// TODO Auto-generated method stub
		return authorityToResourceDao.findAll(Example.of(authorityToResource));
	}

}
