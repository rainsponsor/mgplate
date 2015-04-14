/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.plate.frame.security.util;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;

import com.google.common.collect.Maps;
import com.plate.frame.base.BaseService;
import com.plate.frame.core.model.Role;
import com.plate.frame.core.model.User;
import com.plate.frame.core.persistence.RoleMapper;
import com.plate.frame.core.persistence.UserMapper;
import com.plate.common.spring.SpringContextHolder;
import com.plate.frame.security.SystemAuthorizingRealm.Principal;
import com.plate.frame.security.acl.persistence.AclMapper;
import com.plate.frame.security.menutree.model.MenuTree;
import com.plate.frame.security.menutree.persistence.MenuTreeMapper;

/**
 * 用户工具类
 * @author ThinkGem
 * @version 2013-5-29
 */
public class UserUtils extends BaseService {

	private static UserMapper userMapper = SpringContextHolder.getBean(UserMapper.class);
	private static RoleMapper roleMapper = SpringContextHolder.getBean(RoleMapper.class);
//	private static MenuTreeMapper menuMapper = SpringContextHolder.getBean(MenuTreeMapper.class);
//	private static AclMapper aclMapper = SpringContextHolder.getBean(AclMapper.class);
	
	public static final String CACHE_USER = "user";
	public static final String CACHE_ROLE_LIST = "roleList";
	public static final String CACHE_MENU_LIST = "menuList";
	public static final String CACHE_AREA_LIST = "areaList";
	public static final String CACHE_OFFICE_LIST = "officeList";
	
	public static User getUser(){
		User user = (User)getCache(CACHE_USER);
		if (user == null){
			try{
				Subject subject = SecurityUtils.getSubject();
				Principal principal = (Principal)subject.getPrincipal();
				if (principal!=null){
					user = userMapper.get(principal.getId());
					putCache(CACHE_USER, user);
				}
			}catch (UnavailableSecurityManagerException e) {
				
			}catch (InvalidSessionException e){
				
			}
		}
		if (user == null){
			user = new User();
			try{
				SecurityUtils.getSubject().logout();
			}catch (UnavailableSecurityManagerException e) {
				
			}catch (InvalidSessionException e){
				
			}
		}
		return user;
	}
	
	public static User getUser(boolean isRefresh){
		if (isRefresh){
			removeCache(CACHE_USER);
		}
		return getUser();
	}

	public static List<Role> getRoleList(){
		@SuppressWarnings("unchecked")
		List<Role> list = (List<Role>)getCache(CACHE_ROLE_LIST);
		if (list == null){
			User user = getUser();
			list = roleMapper.getRoleByUserId(user.getId());
//			DetachedCriteria dc = roleDao.createDetachedCriteria();
//			dc.createAlias("office", "office");
//			dc.createAlias("userList", "userList", JoinType.LEFT_OUTER_JOIN);
//			dc.add(dataScopeFilter(user, "office", "userList"));
//			dc.add(Restrictions.eq(Role.FIELD_DEL_FLAG, Role.DEL_FLAG_NORMAL));
//			dc.addOrder(Order.asc("office.code")).addOrder(Order.asc("name"));
//			list = roleDao.find(dc);
			putCache(CACHE_ROLE_LIST, list);
		}
		return list;
	}
	
	public static List<MenuTree> getMenuList(){
		@SuppressWarnings("unchecked")
		List<MenuTree> menuList = (List<MenuTree>)getCache(CACHE_MENU_LIST);
		if (menuList == null){
			User user = getUser();
			if (user.isAdmin()){
				//menuList = menuDao.findAllList();
			}else{
				//menuList = menuDao.findByUserId(user.getId());
			}
			putCache(CACHE_MENU_LIST, menuList);
		}
		return menuList;
	}
	
//	public static List<Area> getAreaList(){
//		@SuppressWarnings("unchecked")
//		List<Area> areaList = (List<Area>)getCache(CACHE_AREA_LIST);
//		if (areaList == null){
////			User user = getUser();
////			if (user.isAdmin()){
//				areaList = areaDao.findAllList();
////			}else{
////				areaList = areaDao.findAllChild(user.getArea().getId(), "%,"+user.getArea().getId()+",%");
////			}
//			putCache(CACHE_AREA_LIST, areaList);
//		}
//		return areaList;
//	}
	
//	public static List<Office> getOfficeList(){
//		@SuppressWarnings("unchecked")
//		List<Office> officeList = (List<Office>)getCache(CACHE_OFFICE_LIST);
//		if (officeList == null){
//			User user = getUser();
////			if (user.isAdmin()){
////				officeList = officeDao.findAllList();
////			}else{
////				officeList = officeDao.findAllChild(user.getOffice().getId(), "%,"+user.getOffice().getId()+",%");
////			}
//			DetachedCriteria dc = officeDao.createDetachedCriteria();
//			dc.add(dataScopeFilter(user, dc.getAlias(), ""));
//			dc.add(Restrictions.eq(Office.FIELD_DEL_FLAG, Office.DEL_FLAG_NORMAL));
//			dc.addOrder(Order.asc("code"));
//			officeList = officeDao.find(dc);
//			putCache(CACHE_OFFICE_LIST, officeList);
//		}
//		return officeList;
//	}
	

	public static User getUserById(String id){
		if(StringUtils.isNotBlank(id)) {
			return null;//userDao.get(id);
		} else {
			return null;
		}
	}
	
	// ============== User Cache ==============
	
	public static Object getCache(String key) {
		return getCache(key, null);
	}
	
	public static Object getCache(String key, Object defaultValue) {
		Object obj = getCacheMap().get(key);
		return obj==null?defaultValue:obj;
	}

	public static void putCache(String key, Object value) {
		getCacheMap().put(key, value);
	}

	public static void removeCache(String key) {
		getCacheMap().remove(key);
	}
	
	public static Map<String, Object> getCacheMap(){
		Map<String, Object> map = Maps.newHashMap();
		try{
			Subject subject = SecurityUtils.getSubject();
			Principal principal = (Principal) subject.getPrincipal();
			return principal!=null?principal.getCacheMap():map;
		}catch (UnavailableSecurityManagerException e) {
			
		}catch (InvalidSessionException e){
			
		}
		return map;
	}
	
}
