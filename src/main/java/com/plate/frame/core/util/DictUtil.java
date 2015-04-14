package com.plate.frame.core.util;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.plate.common.cache.CacheContant;
import com.plate.common.cache.CacheUtils;
import com.plate.common.spring.SpringContextHolder;
import com.plate.frame.core.model.Dict;
import com.plate.frame.core.service.CoreTreeService;
import com.plate.frame.core.service.DictService;

/**
 * @作者:Rainsponsor
 * @E-mail:xianzel@163.com
 * @时间: 2015-1-7
 * @描述: 核心系统缓存工具类
 */
@SuppressWarnings("unchecked")
public class DictUtil {
	
	private static DictService dictService = SpringContextHolder.getBean(DictService.class);
	
	public final static String SYS_DIC = "dictTree";
	public final static String SYS_DIC_NAME = "dictName";
	
	/**
	 * <功能简述>
			* <功能详细描述> 根据数据字典ID对应的直接子
			* @param parentId
			* @return
			* @see [类、类#方法、类#成员]
	 */
	public static List<Dict> getDictsByParentId(Integer parentId){
		List<Dict> dictList = (List<Dict>) CacheUtils.get(SYS_DIC_NAME+parentId);
		if(dictList==null || dictList.size() == 0){
			dictList = dictService.getDictsByParentId(parentId);
			CacheUtils.put(SYS_DIC_NAME+parentId, dictList);
		}
		return dictList;
	}
	
	/**
	 * 获取完整菜单树
	 * @return
	 */
	public static List<Map<String,Object>> getAllDictTrees(){
		List<Map<String,Object>> result = (List<Map<String, Object>>) CacheUtils.get(SYS_DIC);
		if(result == null || result.size() == 0){
			result = dictService.getAllDictTrees();
			Map<String,Object> root = Maps.newHashMap();
			root.put("id", 0);
			root.put("name", "数据字典");
			root.put("open", true);
			result.add(0, root);
			for(Map<String,Object> menu : result){
				menu.put("target", "tree_info_content");
				menu.put("url", SpringContextHolder.getContextPath()+"/sys/dict/content?id="+menu.get("id"));
			}
			CacheUtils.put(SYS_DIC, result);
		}
		return result;
	}
}
