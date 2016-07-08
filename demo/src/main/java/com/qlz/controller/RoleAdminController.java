package com.qlz.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qlz.constant.Const;
import com.qlz.constant.ExceptionCode;
import com.qlz.entities.Role;
import com.qlz.entities.RoleToAuthority;
import com.qlz.model.JsonResult;
import com.qlz.model.Tree;
import com.qlz.service.AuthorityService;
import com.qlz.service.RoleService;
import com.qlz.shiro.ChainDefinitionSectionMetaSource;
import com.qlz.util.StringUtil;

/**
 * 角色Controller
 * 
 * @author QiQi-04-PC
 *
 */
@Controller
@RequestMapping("/admin/role")
public class RoleAdminController {
	private String basePath = "admin/role/";
	@Autowired
	private RoleService roleService;
	@Autowired
	private AuthorityService authorityService;
	//@Autowired
	//private RoleToAuthorityService roleToAuthorityService;
	@Autowired
	private ChainDefinitionSectionMetaSource chainDefinitionSectionMetaSource;

	/**
	 * 读取公共的参数值和设置,根据界面设置的参数值来选择页面菜单选中效果
	 * 
	 * @param menuBar
	 * @param model
	 */
	@ModelAttribute
	public void common(Model model) {
		model.addAttribute("systemclass", Const.MENU_FIRST);
		model.addAttribute("system_roleclass", Const.MENU_SUB);
	}

	/**
	 * 新增
	 * 
	 * @param authority
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult insert(Role role) {
		try {
			roleService.insertSelective(role);
			chainDefinitionSectionMetaSource.reLoad();
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(ExceptionCode.FAIL, role);
		}
		return new JsonResult(ExceptionCode.SUCCESSFUL, role);
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
			return new JsonResult(ExceptionCode.FAIL, "Id不能为空！");
		}
		try {
			roleService.deleteByPrimaryKey(id);
			chainDefinitionSectionMetaSource.reLoad();
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
	public JsonResult update(Role role) {
		if (role.getId() == null) {
			return new JsonResult(ExceptionCode.FAIL, "id为空！");
		}
		try {
			roleService.updateByPrimaryKeySelective(role);
			chainDefinitionSectionMetaSource.reLoad();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new JsonResult(ExceptionCode.FAIL);
		}
		return new JsonResult(ExceptionCode.SUCCESSFUL);
	}

	/**
	 * 页面列表跳转
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list() {
		return basePath + "list";
	}

	/**
	 * 根据roleID 加载权限树 ,并打给树上checked
	 * 
	 * @return
	 */
	@RequestMapping("/authorityTree")
	@ResponseBody
	public JsonResult loadTreeByRoleId(@RequestParam("roleId") Long roleId) {

		List<Tree> authorityTree;
		List<Tree> AT;
		// 查找已授权的树并添加标识
		List<RoleToAuthority> rts;
		try {
			authorityTree = authorityService.getAllTree();
			AT = new ArrayList<Tree>();
			//rts = roleToAuthorityService.selectByRoleId(roleId);
			//AT = authorityService.tagTree(rts, authorityTree);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new JsonResult(ExceptionCode.FAIL, e.getMessage());
		}
		return new JsonResult(ExceptionCode.SUCCESSFUL, AT);
	}

	/**
	 * 加载角色树
	 * 
	 */
	@RequestMapping("/roleTree")
	@ResponseBody
	public JsonResult loadRoleTree() {

		List<Tree> authorityTree = new ArrayList<>();
		try {
			authorityTree = roleService.getAllTree();
		} catch (Exception e) {

			e.printStackTrace();
			return new JsonResult(ExceptionCode.FAIL, e.getMessage());
		}

		return new JsonResult(ExceptionCode.SUCCESSFUL, authorityTree);
	}

	/**
	 * 根据用户角色ID及权限ID插入授权
	 * 
	 * @param roleId
	 * @param authorityIds
	 * @return
	 */
	@RequestMapping("/insertByRoleIdAndAuthIds")
	@ResponseBody
	public JsonResult insertAuthorityToRole(Long roleId, String authorityIds) {
		List<Long> authorityIdsList = StringUtil.generateListLong(authorityIds);
		try {
			/* 先删除后插入 */
		//	roleToAuthorityService.batDelete(params);
		//	roleToAuthorityService.batInsert(params);
			//roleToAuthorityService.updateByDelete(roleId,authorityIdsList);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(ExceptionCode.FAIL);
		}
		// 刷新资源shiro chain
		chainDefinitionSectionMetaSource.reLoad();
		return new JsonResult(ExceptionCode.SUCCESSFUL);
	}

	/**
	 * 根据用户角色Id及权限ID 删除
	 * 
	 * @param roleId
	 * @param authorityIds
	 * @return
	 */
	@RequestMapping("/deleteByRoleIdAndAuthIds")
	@ResponseBody
	public JsonResult deleteAuthorityToRole(Long roleId, String authorityIds) {
	//	Map<String, Object> params = new HashMap<String, Object>();
		List<Long> authorityIdsList = StringUtil.generateListLong(authorityIds);
		//params.put("authorityIds", idsList);
	//	params.put("roleId", roleId);
		try {
			//roleToAuthorityService.batDelete(roleId,authorityIdsList);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(ExceptionCode.FAIL);
		}
		chainDefinitionSectionMetaSource.reLoad();
		return new JsonResult(ExceptionCode.SUCCESSFUL);
	}

}
