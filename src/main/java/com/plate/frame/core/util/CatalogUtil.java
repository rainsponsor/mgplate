package com.plate.frame.core.util;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.plate.common.cache.CacheUtils;
import com.plate.common.spring.SpringContextHolder;
import com.plate.common.util.StringUtils;
import com.plate.frame.core.model.Catalog;
import com.plate.frame.core.service.CatalogService;

/**
 * @作者:Rainsponsor
 * @E-mail:xianzel@163.com
 * @时间: 2015-1-7
 * @描述: 核心系统缓存工具类	-	目录工具
 */
@SuppressWarnings("unchecked")
public class CatalogUtil {
	
	private static CatalogService catalogService = SpringContextHolder.getBean(CatalogService.class);
	
	public final static String SYS_CATALOG = "catalogTree";
	public final static String SYS_CATALOG_NAME = "catalogName";
	
	/**
	 * <功能简述>
			* <功能详细描述> 根据目录ID对应的直接子
			* @param parentId
			* @return
			* @see [类、类#方法、类#成员]
	 */
	public static List<Catalog> getCatalogsByParentId(Integer parentId){
		List<Catalog> catalogList = (List<Catalog>) CacheUtils.get(SYS_CATALOG_NAME+parentId);
		if(catalogList==null || catalogList.size() == 0){
			catalogList = catalogService.getCatalogsByParentId(parentId);
			CacheUtils.put(SYS_CATALOG_NAME+parentId, catalogList);
		}
		return catalogList;
	}
	
	/**
	 * 获取完整目录树
	 * @return
	 */
	public static List<Map<String,Object>> getAllCatalogTrees(){
		List<Map<String,Object>> result = (List<Map<String, Object>>) CacheUtils.get(SYS_CATALOG);
		if(result == null || result.size() == 0){
			result = catalogService.getAllCatalogTrees();
			Map<String,Object> root = Maps.newHashMap();
			root.put("id", 0);
			root.put("name", "目录管理");
			root.put("open", true);
			result.add(0, root);
			for(Map<String,Object> menu : result){
				menu.put("target", "tree_info_content");
				menu.put("url", SpringContextHolder.getContextPath()+"/sys/catalog/content?id="+menu.get("id"));
			}
			CacheUtils.put(SYS_CATALOG, result);
		}
		return result;
	}
	
	/**
	 * 根据指定目录获取目录下所有子目录tree
	 * @param id
	 * @return
	 */
	public static List<Map<String, Object>> getCatalogTreeById(Integer id) {
		List<Map<String,Object>> list = (List<Map<String, Object>>) CacheUtils.get(SYS_CATALOG);
		List<Map<String,Object>> result = Lists.newArrayList();
		if(list == null || list.size() == 0){
			list = catalogService.getAllCatalogTrees();
		}
		Map<String,Object> catalog = null;
		for(Map<String,Object> menu : list){
			catalog = Maps.newHashMap();
			catalog.putAll(menu);
			if(String.valueOf(id).equals(catalog.get("id").toString())){
				catalog.put("open", true);
				catalog.put("url", SpringContextHolder.getContextPath()+"/curr/know/content?id="+catalog.get("id"));
				result.add(catalog);
			}
			String dirIds = StringUtils.noNull(catalog.get("dir_ids"));
			if(dirIds.contains(String.valueOf(","+id+","))){
				if("1".equals(StringUtils.noNull(catalog.get("leaf")))){
					catalog.put("url", SpringContextHolder.getContextPath()+"/curr/know/dircontent?dirid="+catalog.get("id"));
				}else{
					catalog.remove("url");
				}
				result.add(catalog);
			}
			catalog.put("target", "tree_info_content");
		}
		return result;
	}
}
