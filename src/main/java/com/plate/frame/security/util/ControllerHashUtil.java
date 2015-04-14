package com.plate.frame.security.util;

import java.util.Map;

import com.google.common.collect.Maps;
import com.plate.frame.base.BaseController;
/**
 *作者： 李志刚
 *时间：2015-2-4
 */
public class ControllerHashUtil {
	
	private static final Map<Class<? extends BaseController>, Integer> CONTROLLER_HASH = Maps.newHashMap();
	
	/**
	 * <功能简述>
			* <功能详细描述>
			* 将Controller对应的Menu信息存储到权限Hash中
			* @param clazz
			* @param menu
			* @see [类、类#方法、类#成员]
	 */
	public static void putAcl(Class<? extends BaseController> clazz,Integer menu){
		CONTROLLER_HASH.put(clazz, menu);
	}
	
	/**
	 * <功能简述>
			* <功能详细描述>
			* 根据Controller获取其绑定的Menu信息
			* @param clazz
			* @return
			* @see [类、类#方法、类#成员]
	 */
	public static Integer getAcl(Class<?> clazz){
		Integer menu = CONTROLLER_HASH.get(clazz);
		if(menu == null){
			menu = 0;
		}
		return menu;
	}
	
}

	