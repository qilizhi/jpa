package com.qlz.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.MediaType;
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
import com.qlz.entities.AuthorityToResource;
import com.qlz.entities.Resource;
import com.qlz.model.JsonResult;
import com.qlz.service.AuthorityToResourceService;
import com.qlz.service.ResourceService;
import com.qlz.shiro.ChainDefinitionSectionMetaSource;

/**
 * 璧勬簮controller
 * 
 * @author QiQi-04-PC
 *
 */
@Controller
@RequestMapping("/admin/resource")
public class ResourceAdminController {
	private static Logger logger = LoggerFactory.getLogger(ResourceAdminController.class);

	@Autowired
	private ResourceService resourceService;
	@Autowired
	private AuthorityToResourceService authorityToResourceService;
	
	@Autowired
	private ChainDefinitionSectionMetaSource chainDefinitionSectionMetaSource;
	
	/**
	 * 读取公共的参数值和设置,根据界面设置的参数值来选择页面菜单选中效果
	 * 
	 * @param menuBar
	 * @param model
	 */
	@ModelAttribute
	public void common( Model model ) {
		model.addAttribute( "systemclass", Const.MENU_FIRST );
		model.addAttribute( "system_resourceclass", Const.MENU_SUB );
	}
	
	/**
	 * 刷新全局权限资源
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/refreshGlobal")
	@ResponseBody
	public JsonResult refreshShiroResources() throws Exception {
		chainDefinitionSectionMetaSource.reLoad();
		return new JsonResult( ExceptionCode.SUCCESSFUL );
    }

	@RequestMapping("/list")
	public String list(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = Const.PAGE_SIZE) Integer pageSize,
			HttpServletRequest request, Model model, Resource resource) {

		try {
			PageRequest pageBounds = new PageRequest(pageNo, pageSize,new Sort(Direction.DESC,"id"));
			Page<Resource> list = resourceService.getResourcePageList(resource, pageBounds);
			//model.addAttribute("paginator", list != null ? list.getPaginator() : null);
			model.addAttribute("list", list);
			model.addAttribute("resource", resource);
			model.addAttribute("pageSize", pageSize);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "admin/resource/list";

	}
	/**
	 * 权限与资源树
	 * @param request
	 * @param model
	 * @param resource
	 * @param authorityToResource
	 * @return
	 */
	@RequestMapping("/all")
	@ResponseBody
	public JsonResult list(HttpServletRequest request, Model model, Resource resource,
			AuthorityToResource authorityToResource) {

		List<Resource> list = new ArrayList<>();
		List<AuthorityToResource> atrlist = new ArrayList<AuthorityToResource>();
		try {
			list = resourceService.getResourceList(resource);
			atrlist = authorityToResourceService.selectByExample(authorityToResource);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new JsonResult(ExceptionCode.FAIL);
		}
		//resource是否有选分配了
		for (AuthorityToResource at : atrlist) {
			for (Resource r : list) {
				if (r.getId() != null && at.getResourceId() != null && r.getId() == at.getResourceId()) {
					r.setFlag(1);
					continue;
				}

			}

		}

		return new JsonResult(ExceptionCode.SUCCESSFUL, list);

	}


	/**
	 * 
	 * 根据id 获取 资源
	 * 
	 * @param request
	 * @param id
	 * @return
	 */

	@RequestMapping("/{id}")
	@ResponseBody
	public JsonResult get(HttpServletRequest request, @PathVariable("id") Long id) {
		JsonResult ajaxResult = null;
		try {

			Resource resource = resourceService.selectByPrimaryKey(id);
			ajaxResult = new JsonResult(ExceptionCode.SUCCESSFUL, resource);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			ajaxResult = new JsonResult(ExceptionCode.FAIL, e.getMessage());
		}
		return ajaxResult;
	}
	/**
	 * 详情
	 * 
	 * @param actInfo
	 * @return
	 */
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable Long id, Model model) {
		try {
			Resource resource = resourceService.selectByPrimaryKey(id);
			model.addAttribute("actInfo", resource);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "admin/actInfo/form";
	}

	/**
	 * 更新
	 * 
	 * @param actInfo
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JsonResult update(HttpServletRequest request, Resource resource) {
		JsonResult ajaxResult = null;
		try {

			resourceService.updateByPrimaryKeySelective(resource);
			chainDefinitionSectionMetaSource.reLoad();
			ajaxResult = new JsonResult(ExceptionCode.SUCCESSFUL, resource);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			ajaxResult = new JsonResult(ExceptionCode.FAIL, e.getMessage());
		}
		return ajaxResult;
	}


	/**
	 * 创建
	 * 
	 * @param resource
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JsonResult create(HttpServletRequest request, Resource resource) {
		JsonResult ajaxResult = null;
		try {

			resourceService.insertSelective(resource);
			ajaxResult = new JsonResult(ExceptionCode.SUCCESSFUL, resource);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			ajaxResult = new JsonResult(ExceptionCode.FAIL, e.getMessage());
		}
		return ajaxResult;
	}

	/**
	 * 批量删除
	 * 
	 * @param ids
	 *            id集合,例如:1,2,3,4
	 * @return
	 */
	@RequestMapping(value = "/deletes/{ids}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JsonResult delete(@PathVariable String ids) {
		JsonResult ajaxResult = null;
		if (ids == null || ids.equals("")) {
			ajaxResult = new JsonResult(ExceptionCode.FAIL, "ids涓虹┖锛?");
		}
		try {
			String[] idArray = ids.split(",");
			resourceService.deleteResourceBitch(idArray);
			ajaxResult = new JsonResult(ExceptionCode.SUCCESSFUL);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			ajaxResult = new JsonResult(ExceptionCode.FAIL, e.getMessage());
		}
		return ajaxResult;
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JsonResult delete(@PathVariable Long id) {
		JsonResult ajaxResult = null;
		try {

			resourceService.deleteByPrimaryKey(id);
			ajaxResult = new JsonResult(ExceptionCode.SUCCESSFUL);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			ajaxResult = new JsonResult(ExceptionCode.FAIL, e.getMessage());
		}
		return ajaxResult;
	}

}
