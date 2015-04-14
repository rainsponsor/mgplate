package com.plate.frame.core.service;

import java.util.List;

import org.mybatis.pagination.dto.PageMyBatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plate.common.cache.CacheContant;
import com.plate.common.cache.CacheUtils;
import com.plate.frame.base.BaseService;
import com.plate.frame.core.model.Organization;
import com.plate.frame.core.persistence.OrganizationMapper;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：
 */
@Service
@Transactional
public class OrganizationService extends BaseService{
	
	@Autowired
	private OrganizationMapper orgMapper;
	
	public PageMyBatis<Organization> getListByPage(Organization model) {
		if(model.getId() !=null){//检索是拼接ids
			model.setParent_ids(","+model.getId()+",");
		}
		return orgMapper.getListByPage(model,model.getPagingCriteria());
	}
	
	private String getParentIds(Integer parentId){
		return get(parentId).getParent_ids() + parentId+",";
	}
	
	public void save(Organization model) {
		model.setParent_ids(getParentIds(model.getParent_id()));
		orgMapper.save(model);
		CacheUtils.remove(CacheContant.SYS_CACHE_TREE_ORGS);
	}
	
	public void update(Organization model) {
		model.setParent_ids(getParentIds(model.getParent_id()));
		orgMapper.update(model);
		CacheUtils.remove(CacheContant.SYS_CACHE_TREE_ORGS);
	}

	public Integer delete(Integer id) {
		Integer count = orgMapper.delete(id);
		CacheUtils.remove(CacheContant.SYS_CACHE_TREE_ORGS);
		return count;
	}

	public List<Organization> getOrgList(Integer id){
		return orgMapper.getOrgListById(id);
	}
	
	public Organization get(Integer id) {
		return orgMapper.get(id);
	}

}
