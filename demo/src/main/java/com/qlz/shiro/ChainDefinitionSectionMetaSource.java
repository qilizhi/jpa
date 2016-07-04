package com.qlz.shiro;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;

import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qlz.entities.Resource;
import com.qlz.entities.Role;
import com.qlz.service.ResourceService;
import com.qlz.service.RoleService;

/**
 * ����spring {@link FactoryBean} ��apache shiro��premission���ж�̬����
 * 
 * @author quan
 * 
 */
@Component
public class ChainDefinitionSectionMetaSource implements FactoryBean<Ini.Section> {

	private static Logger logger = LoggerFactory.getLogger(ChainDefinitionSectionMetaSource.class);

	@Autowired
	private RoleService roleService;

	@Autowired
	private ResourceService resourceService;

	// shiroĬ�ϵ����Ӷ���
	private String filterChainDefinitions;

	@Autowired
	private ShiroFilterFactoryBean shiroFilterFactoryBean;

	// private static final String CRLF= "\r\n";

	/**
	 * ���¹���ȫ��Ȩ����Դ
	 */
	public void reLoad() {
		AbstractShiroFilter shiroFilter = null;
		try {
			shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		// ��ȡ���˹�����
		PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter
				.getFilterChainResolver();
		DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();

		// ��ճ�ʼȨ������
		manager.getFilterChains().clear();
		shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
		Map<String, Filter> mapFilters = manager.getFilters();
		if (!CollectionUtils.isEmpty(mapFilters)) {
			for (Map.Entry<String, Filter> entry : mapFilters.entrySet()) {
				Filter filter = entry.getValue();
				if (filter instanceof MyPermissionsAuthorizationFilter) {
					((MyPermissionsAuthorizationFilter) filter).clearAppliedPaths();
				}
			}
		}

		// ���¹�������
		// String _filterChainDefinitions = getDefinitions();
		// System.out.println(_filterChainDefinitions);
		// shiroFilterFactoryBean.setFilterChainDefinitions(_filterChainDefinitions);
		try {
			shiroFilterFactoryBean.setFilterChainDefinitionMap(getObject());
			Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
			for (Map.Entry<String, String> entry : chains.entrySet()) {
				String url = entry.getKey();
				String chainDefinition = entry.getValue().trim().replace(" ", "");
				manager.createChain(url, chainDefinition);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	// /**
	// * ��ȡȨ�޹���������
	// * @return
	// */
	// private String getDefinitions() {
	// StringBuffer sb = new StringBuffer("");
	// sb.append( filterChainDefinitions ).append( CRLF );
	// List<Role> rolesList = roleService.list(null);
	//
	// // ѭ�����ݿ���Դ��url
	// if (rolesList != null && rolesList.size() > 0) {
	// logger.info("loaded role menu finish! result = " + rolesList.size(),
	// this);
	// for (Role role : rolesList) {
	// if (role != null && !role.equals("")) {
	// // ���ҽ�ɫ�µ��� Դ
	// List<Resource> resources =
	// resourceService.getResourceByRoleId(role.getId());
	//
	// for (Resource resource : resources) {
	// if (resource != null) {
	// String[] _strRegexs = new String[] { "\\/admin|\\/admin\\/" };
	// Pattern pattern = Pattern.compile(_strRegexs[0]);
	// Matcher matcher = pattern.matcher(resource.getPath());
	// if (matcher.find()) {
	// sb.append(resource.getPath()).append("=").append( "role[" ).append(
	// role.getName()
	// ).append("],
	// perms[").append(resource.getPath()).append("]").append(CRLF);
	// }
	// }
	// }
	// }
	// }
	// } else {
	// logger.info("loaded resource menu finish !,but it's null", this);
	// }
	// sb.append( "/admin/**" ).append( "=" ).append(
	// "role[admin],perms[admin:*:view:x]" ).append( CRLF );
	// return sb.toString();
	// }

	/**
	 * ͨ��filterChainDefinitions��Ĭ�ϵ����ӹ��˶���
	 * 
	 * @param filterChainDefinitions
	 *            Ĭ�ϵĽӹ��˶���
	 */
	public void setFilterChainDefinitions(String filterChainDefinitions) {
		this.filterChainDefinitions = filterChainDefinitions;
	}

	@Override
	public Section getObject() throws Exception {
		// TODO Auto-generated method stub
		Ini ini = new Ini();
		// ����Ĭ�ϵ�url
		ini.load(filterChainDefinitions);
		logger.info(filterChainDefinitions);
		Ini.Section section = ini.getSection(IniFilterChainResolverFactory.URLS);
		if (CollectionUtils.isEmpty(section)) {
			section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
		}

		// section.put("/acct/scene","authc,user, perms[scene:*]");
		// test/* = role[admin],perms[test:view]

		List<Role> rolesList = roleService.findAll();
		// ѭ�����ݿ���Դ��url
		if (rolesList != null && rolesList.size() > 0) {
			logger.info("loaded role menu finish! result = " + rolesList.size(), this);
			for (Role role : rolesList) {
				if (role != null && !role.equals("")) {
					// ���ҽ�ɫ�µ��� Դ
					List<Resource> resources = resourceService.findResourceByRoleId(role.getId());

					for (Resource resource : resources) {
						if (resource != null) {
							String[] _strRegexs = new String[] { "\\/admin|\\/admin\\/" };
							Pattern pattern = Pattern.compile(_strRegexs[0]);
							Matcher matcher = pattern.matcher(resource.getPath());
							if (matcher.find()) {
								section.put(resource.getPath(),
										"role[" + role.getName() + "],perms[" + resource.getPath() + "]");
							}
						}
					}

					/*
					 * System.out.println( cmlMenu.getActionUrl() + " = role[" +
					 * cmlMenu.getAppRoleName() + "], perms[" +
					 * cmlMenu.getPermission() + "]" );
					 */
				}
			}
			// admin/** = role[admin],perms
			section.put("/admin/**", "role[admin],perms[admin:*:view:x]");
			logger.info("Ȩ�����ر�" + section.values().toString());
			logger.info("Ȩ�����ر�" + ini.toString());
		} else {
			logger.info("loaded resource menu finish !,but it's null", this);
		}

		return section;
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return Section.class;
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return true;
	}

}
