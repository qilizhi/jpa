package com.qlz.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qlz.constant.Const;
import com.qlz.constant.ExceptionCode;
import com.qlz.entities.Authority;
import com.qlz.entities.Resource;
import com.qlz.model.JsonResult;
import com.qlz.model.Tree;
import com.qlz.service.AuthorityService;
import com.qlz.service.ResourceService;
import com.qlz.util.StringUtil;

/**
 * 
 * 权限授权
 * 
 * @author QiQi-04-PC
 *
 */
@RequestMapping("/admin/authority")
@Controller
public class AuthorityAdminController {

	@Autowired
	private AuthorityService authorityService;

	//@Autowired
	//private AuthorityToResourceService atrService;
	@Autowired
	private ResourceService resourceService;
	
	
	/**
	 * 公共属性
	 * 
	 * @param menuBar
	 * @param model
	 */
	@ModelAttribute
	public void common( Model model ) {
		model.addAttribute( "systemclass", Const.MENU_FIRST );
		model.addAttribute( "system_perimclass", Const.MENU_SUB );
	}


	/**
	 * 权限列表
	 * 
	 * @param name
	 *            
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Long authorityId ,@RequestParam(defaultValue = "1") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize,Model model) {

		PageRequest pageBounds = new PageRequest(pageNo, pageSize,new Sort(Direction.ASC,"id"));
		Page<Resource> list = resourceService.getResourceByAuthorityId(authorityId, pageBounds);
		model.addAttribute("list", list);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("authorityId", authorityId);
		return "/admin/authority/list";
	}
	
	/** 创建 
	 * 
	 * @param authority
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult insert(Authority authority) {
		try {
			authority=authorityService.insertSelective(authority);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(ExceptionCode.FAIL, authority);
		}
		return new JsonResult(ExceptionCode.SUCCESSFUL, authority);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult delete(Long id) {

		if (id == null) {
			return new JsonResult(ExceptionCode.FAIL, "id为空");
		}
		try {
			authorityService.deleteByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(ExceptionCode.FAIL);
		}
		return new JsonResult(ExceptionCode.SUCCESSFUL);
	}


	/**
	 * 更新
	 * 
	 * @param auth
	 * @return
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public JsonResult update(Authority auth) {
		if (auth.getId() == null) {
			return new JsonResult(ExceptionCode.FAIL, "id不能为空");
		}
		try {
			authorityService.updateByPrimaryKeySelective(auth);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new JsonResult(ExceptionCode.FAIL);
		}
		return new JsonResult(ExceptionCode.SUCCESSFUL);
	}

	/**
	 * 加载资源树
	 * 
	 * @param authorityId
	 * @return
	 */
	@RequestMapping("/loadResource")
	public String loadResource(Long authorityId,Pageable pageBounds,Model model) {
		//PageRequest pageBounds = new PageRequest(pageNo, pageSize,new Sort(Direction.DESC,"id"));
		Page<Resource> list = resourceService.getResourceByAuthorityId(authorityId, pageBounds);
		//model.addAttribute("paginator", list != null ? list.getPaginator() : null);
		model.addAttribute("list", list);
		model.addAttribute("authorityId", authorityId);
		//model.addAttribute("pageSize", pageSize);
		//model.addAttribute("pageNo", pageNo);
		return "/admin/authority/table";
	}

	/**
	 * 设置资源树
	 * @param resourceIds
	 * @param authorityId
	 * @return
	 */
	@RequestMapping("/setResource")
	@ResponseBody
	public JsonResult setResourceByAuthority(String resourceIds, Long authorityId) {
		if(authorityId==null){
			return new JsonResult(ExceptionCode.FAIL,"authorityIdΪ�գ�");
			
		}
	
		List<Long> resourceIdsList = StringUtil.generateListLong(resourceIds);
		
		try {

			authorityService.updateBydelete(authorityId,resourceIdsList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new JsonResult(ExceptionCode.FAIL);
		}
		return new JsonResult(ExceptionCode.SUCCESSFUL);

	}

	/**
	 * 权限树
	 * 
	 * @return
	 */
	@RequestMapping("/Alltree")
	@ResponseBody
	public JsonResult getAllTree() {

		List<Tree> tree = new ArrayList<>();
		try {
			tree = authorityService.getAllTree();
		} catch (Exception e) {

			e.printStackTrace();
			return new JsonResult(ExceptionCode.FAIL, e.getMessage());
		}
		return new JsonResult(ExceptionCode.SUCCESSFUL, tree);
	}

}
