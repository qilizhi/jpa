package com.qlz.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.impl.Log4jLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;










import com.qlz.constant.Const;
import com.qlz.constant.ExceptionCode;
import com.qlz.entities.User;
import com.qlz.model.JsonResult;
import com.qlz.model.Tree;
import com.qlz.service.UserService;

/**
 * 系统用户管理
 * @author QiQi-04-PC
 *
 */
@Controller
@RequestMapping("/admin/sysUser")
public class SysUserAdminController {
	Logger logger=new Log4jLoggerFactory().getLogger(this.getClass().getName());
	
	@Autowired
	private UserService userService;
	/**
	 * 读取公共的参数值和设置,根据界面设置的参数值来选择页面菜单选中效果
	 * 
	 * @param menuBar
	 * @param model
	 */
	
	@ModelAttribute
	public void common(Model model) {
		model.addAttribute("systemclass", Const.MENU_FIRST);
		model.addAttribute("system_sysUserclass", Const.MENU_SUB);
	}
	
	
	
	/**
	 * 获取用户信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/get/{id}")
	@ResponseBody
	public JsonResult get (@PathVariable("id")Long id){
		
		User u=new User();
		try {
			u = userService.getUserInfoByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
			new JsonResult(ExceptionCode.SUCCESSFUL,e.getMessage());
		}
		return  new JsonResult(ExceptionCode.SUCCESSFUL,u);
	}
	
	@RequestMapping(value="/resetPassword",method=RequestMethod.POST)
	@ResponseBody
	public JsonResult resetPassword(User user){
		if(user.getId()==null||user.getPassword()==null){
			return new JsonResult(ExceptionCode.FAIL,"user.id==null or password=null");
		}
		try {
			userService.resetPassword(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new JsonResult(ExceptionCode.FAIL,e.getMessage());
		}
		return new JsonResult(ExceptionCode.SUCCESSFUL);
	}
	
	/*导游用户列表*/
	
	@RequestMapping("/list")
	public String list(@RequestParam( value = "pageNo", defaultValue = "1" ) Integer pageNo,
	        @RequestParam( value = "pageSize", defaultValue = Const.PAGE_SIZE ) Integer pageSize,Model model,User userInfo){
		PageRequest pageBounds = new PageRequest( pageNo, pageSize,Direction.ASC,"id");
		Page<User> list = userService.getUserInfoPageList(userInfo, pageBounds);
		model.addAttribute("list",list);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("userInfo", userInfo);
		return "admin/sysUser/list";
		
	}
	
	
	  
		
		/**
		 * 根据roleID 加载权限树 ,并打给树上checked
		 * 
		 * @return
		 */
		@RequestMapping("/roleTree")
		@ResponseBody
		public JsonResult loadTreeByRoleId(@RequestParam("userNo") String userNo) {
		
			List<Tree> roleTree;
			List<Tree> AT;
			// 查找已授权的树并添加标识
		//	List<UserToRole> utr;
			try {
			//	roleTree = userToRoleService.getAllTree();
				AT = new ArrayList<Tree>();
			//	utr = userToRoleService.selectByUserNo(userNo);
			//	AT=userToRoleService.tagTree(utr,roleTree);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO Auto-generated catch block
				return new JsonResult(ExceptionCode.FAIL,e.getMessage());
			}
			return new JsonResult(ExceptionCode.SUCCESSFUL, AT);
		}

				
		@RequestMapping("/insertByUserNoAndRoleIds")
		@ResponseBody
		public JsonResult insertAuthorityToRole(String userNo,String roleIds){
			Map<String,Object> params=new HashMap<String,Object>();
			//List<Integer> idsList = StringUtil.generateListInteger(roleIds);
			//params.put("roleIds", idsList);
			//params.put("userNo", userNo);
			try {
				/*先删除后插入*/
				//userToRoleService.batDelete(params);
				//userToRoleService.batInsert(params);
			} catch (Exception e) {
				e.printStackTrace();
				return new JsonResult(ExceptionCode.FAIL);
			}
			return new JsonResult(ExceptionCode.SUCCESSFUL);
		}
		@RequestMapping("/deleteByUserNoAndRoleIds")
		@ResponseBody
		public JsonResult deleteAuthorityToRole(String userNo,String roleIds){
			Map<String,Object> params=new HashMap<String,Object>();
			//List<Integer> idsList = StringUtil.generateListInteger(roleIds);
		//params.put("roleIds", idsList);
		//	params.put("userNo", userNo);
			try {
			//	userToRoleService.batDelete(params);
			} catch (Exception e) {
				e.printStackTrace();
				return new JsonResult(ExceptionCode.FAIL);
			}
			return new JsonResult(ExceptionCode.SUCCESSFUL);
		}
		
		

	    

}
