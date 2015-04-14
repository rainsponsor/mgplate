package com.plate.frame.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plate.common.cache.CacheUtils;
import com.plate.frame.base.BaseService;
import com.plate.frame.core.model.Catalog;
import com.plate.frame.core.persistence.CatalogMapper;
import com.plate.frame.core.util.CatalogUtil;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：目录业务处理类
 */
@Service
@Transactional
public class CatalogService extends BaseService {

	@Autowired
	private CatalogMapper mapper;
	
	/**
	 * <功能简述>获取select数据
			* <功能详细描述>根据parentId获取数据字典内容
			* @param parentId
			* @return
			* @see [类、类#方法、类#成员]
	 */
	public List<Catalog> getCatalogsByParentId(Integer parentId) {
		return mapper.getCatalogsByParentId(parentId);
	}
	
	/**
	 * <功能简述>获取全部数据字典树
			* <功能详细描述>
			* @return
			* @see [类、类#方法、类#成员]
	 */
	public List<Map<String, Object>> getAllCatalogTrees() {
		List<Map<String, Object>> result = mapper.getAllCatalogTrees();
		return result;
	}
	
	/**
	 * <功能简述>获取数据字典内容
			* <功能详细描述>根据父ID 获取数据字典内容
			* @param id
			* @return
			* @see [类、类#方法、类#成员]
	 */
	public Catalog get(Integer id){
		Catalog catalogtree = null;
		if(id==null || id == 0){
			catalogtree = new Catalog();
			catalogtree.setId(0);
			catalogtree.setName("");
			catalogtree.setChildren(mapper.getCatalogsByParentId(0));
		}else{
			catalogtree = mapper.get(id);
		}
		return catalogtree;
	}
	
	/**
	 * <功能简述>添加数据字典
			* <功能详细描述>
			* @param dict
			* @see [类、类#方法、类#成员]
	 */
	public void save(Catalog catalog){
		catalog.setDir_ids(getParentIds(catalog.getParent_id()));
		mapper.save(catalog);
		CacheUtils.remove(CatalogUtil.SYS_CATALOG);
	}

	/**
	 * <功能简述>获取全部父ID
			* <功能详细描述>
			* @param parentId
			* @return String 形式：(pId1,pId2,)
			* @see [类、类#方法、类#成员]
	 */
	private String getParentIds(Integer parentId){
		if(parentId == 0){
			return "0,";
		}else{
			return mapper.getCatalogById(parentId).getDir_ids() + parentId+",";
		}
	}
	
	/**
	 * <功能简述>删除功能
			* <功能详细描述>根据id删除字典数九
			* @param id
			* @return
			* @see [类、类#方法、类#成员]
	 */
	public Integer delete(Integer id){
		Integer result = mapper.getCatalogById(id).getParent_id();
		mapper.delete(id);
		CacheUtils.remove(CatalogUtil.SYS_CATALOG);
		return result;
		
	}
	
	public void update(Catalog catalog){
		catalog.setDir_ids(getParentIds(catalog.getParent_id()));
		mapper.update(catalog);
		CacheUtils.remove(CatalogUtil.SYS_CATALOG);
	}
}
