package com.plate.frame.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.plate.common.cache.CacheUtils;
import com.plate.common.spring.SpringContextHolder;
import com.plate.common.util.MD5;
import com.plate.frame.core.model.Role;
import com.plate.frame.core.model.User;
import com.plate.frame.core.persistence.RoleMapper;
import com.plate.frame.core.persistence.UserMapper;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-24
 * @描述：系统安全验证
 */
@Service
public class SystemAuthorizingRealm extends AuthorizingRealm {
	@Autowired 
	private RoleMapper mapper;
	@Autowired
	private UserMapper userMapper;
	private static final String USER_PERMISSION="user_permission";
	/**
	 * 回调权限授权验证
	 * 注：进行鉴权但缓存中无用户的授权信息时调用
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		Principal principal = (Principal) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		List<Map<String, Object>> aclMaps = new ArrayList<Map<String,Object>>(); 
		List<String> permissionList = (List<String>)CacheUtils.get(USER_PERMISSION+"_"+principal.getLoginName()); 
		if(permissionList == null || permissionList.size()==0){
			permissionList = new ArrayList<String>();
			List<String> roleNames = new ArrayList<String>();
			if(!principal.getLoginName().equals("admin")){
				if(principal.getId() != null){
					List<Role> roleList = mapper.getRoleByUserId(principal.getId());
					if(roleList != null && roleList.size()>0){
						for (Role role : roleList) {
							roleNames.add(role.getRole_name());
						}
						info.addRoles(roleNames);
					}
					aclMaps = mapper.getPermissionAllFunctionById(principal.getId());
					for(Map<String, Object> map: aclMaps){
						if(!"menutree".equals(map.get("resource_type"))){
							String primary = map.get("resource_type")+":"+map.get("function_name");
							permissionList.add(primary);
						}
					}
				}
			}else{
				aclMaps = mapper.getPermissionAllFunction();
				for(Map<String, Object> map: aclMaps){
				    String primary = map.get("menutree_id")+":"+map.get("function_name");
				    permissionList.add(primary);
				}
			}
			CacheUtils.put(USER_PERMISSION+"_"+principal.getLoginName(), permissionList);
		}
		info.addStringPermissions(permissionList);
		return info;
	}
	
	/**
	 * 更新用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清除所有用户授权信息缓存.
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}
	
	/**
	 * 认证回调函数
	 * 注:登录验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String loginName = token.getUsername();//得到登陆名
	    String password = new String((char[])token.getCredentials()); //得到密码  
		User user = userMapper.getUserByLoginName(token.getUsername());
		if (user != null) {
				if(!user.getLogin_name().equals(loginName)){
					throw new UnknownAccountException(); //如果用户名错误  
				}
				if(!user.getLogin_password().equals(MD5.getMD5ofStr(password))){
					 throw new IncorrectCredentialsException(); //如果密码错误 
				}
			return new SimpleAuthenticationInfo(new Principal(user),password, this.getName());
		} else {
			return null;
		}
	}
	
	 /** 
     *  解决在登录时无法加载菜单项的问题,即在每次登录时重新加载用户权限缓存 
     */  
    public static void forceShiroToReloadUserAuthorityCache(String username) {  
        SpringContextHolder.getBean(SystemAuthorizingRealm.class).doGetAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());  
    	SystemAuthorizingRealm shiroDbRealm = SpringContextHolder.getBean(SystemAuthorizingRealm.class);  
          
        shiroDbRealm.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals()); // 清除权限缓存  
        shiroDbRealm.isPermitted(SecurityUtils.getSubject().getPrincipals(), "强制shiro检查加载用户权限缓存,避免懒加载!" + System.currentTimeMillis()); 
    }
    
	/**
	 * 授权用户信息
	 */
	public static class Principal implements Serializable {

		private static final long serialVersionUID = 1L;
		
		private Integer id;
		private String loginName;
		private String name;
		private Map<String, Object> cacheMap;

		public Principal(User user) {
			this.id = user.getId();
			this.loginName = user.getLogin_name();
			this.name = user.getUser_name();
		}

		public Integer getId() {
			return id;
		}

		public String getLoginName() {
			return loginName;
		}

		public String getName() {
			return name;
		}

		public Map<String, Object> getCacheMap() {
			if (cacheMap==null){
				cacheMap = new HashMap<String, Object>();
			}
			return cacheMap;
		}

	}
	
}
