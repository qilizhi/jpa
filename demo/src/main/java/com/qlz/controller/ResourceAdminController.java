package com.qlz.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qlz.constant.Const;
import com.qlz.constant.ExceptionCode;
import com.qlz.entities.Resource;
import com.qlz.model.JsonResult;
import com.qlz.service.ResourceService;
import com.qlz.shiro.ChainDefinitionSectionMetaSource;

/**
 * 资源controller
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
	// @Autowired
	// private AuthorityToResourceService authorityToResourceService;

	@Autowired
	private ChainDefinitionSectionMetaSource chainDefinitionSectionMetaSource;

	/**
	 * 公共菜单
	 * 
	 * @param menuBar
	 * @param model
	 */
	@ModelAttribute
	public void common(Model model) {
		model.addAttribute("systemclass", Const.MENU_FIRST);
		model.addAttribute("system_resourceclass", Const.MENU_SUB);
	}

	/**
	 * ˢ��ȫ��Ȩ����Դ
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/refreshGlobal")
	@ResponseBody
	public JsonResult refreshShiroResources() throws Exception {
		chainDefinitionSectionMetaSource.reLoad();
		return new JsonResult(ExceptionCode.SUCCESSFUL);
	}

	@RequestMapping("/list")
	public String list(Pageable pageBounds, HttpServletRequest request, Model model, Resource resource) {

		try {
			Page<Resource> list = resourceService.findAll(pageBounds);
			// List<Resource> list=resourceService.findAll();
			// model.addAttribute("paginator", list != null ?
			// list.getPaginator() : null);
			model.addAttribute("list", list);
			model.addAttribute("resource", resource);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		// return "admin/resource/list";
		return "/admin/resource/list";

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	@ResponseBody
	Page<Resource> persons(@PageableDefault(value=10,sort={"id"},direction=Sort.Direction.ASC,page=0)Pageable pageable) {

		Page<Resource> list = resourceService.findAll(pageable);
		return list;
	}

	/**
	 * 加载资源
	 * 
	 * @param request
	 * @param model
	 * @param resource
	 * @param authorityToResource
	 * @return
	 */
	@RequestMapping("/all")
	@ResponseBody
	public JsonResult list(HttpServletRequest request, Model model, Resource resource) {

		List<Resource> list = new ArrayList<>();
		//List<AuthorityToResource> atrlist = new ArrayList<AuthorityToResource>();
		try {
			list = resourceService.getResourceList(resource);
			// atrlist =
			// authorityToResourceService.selectByExample(authorityToResource);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new JsonResult(ExceptionCode.FAIL);
		}
		// resource�Ƿ���ѡ������
		/*for (AuthorityToResource at : atrlist) {
			for (Resource r : list) {
				if (r.getId() != null && at.getResourceId() != null && r.getId() == at.getResourceId()) {
					r.setFlag(1);
					continue;
				}

			}

		}*/

		return new JsonResult(ExceptionCode.SUCCESSFUL, list);

	}

	/**
	 * 
	 * 新增资源
	 * 
	 * @param request
	 * @param id
	 * @return
	 */

	@RequestMapping("/{id}")
	@ResponseBody
	public JsonResult get(HttpServletRequest request, @PathVariable("id") Long id) {
		JsonResult ajaxResult = new JsonResult();
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
	 * ����
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
	 * ����
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
	 * ����
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
	 * ����ɾ��
	 * 
	 * @param ids
	 *            id����,����:1,2,3,4
	 * @return
	 */
	@RequestMapping(value = "/deletes/{ids}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JsonResult delete(@PathVariable String ids) {
		JsonResult ajaxResult = null;
		if (ids == null || ids.equals("")) {
			ajaxResult = new JsonResult(ExceptionCode.FAIL, "ids为空�?");
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
	 * ɾ��
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
