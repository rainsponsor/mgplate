package com.plate.frame.security.function.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plate.common.cache.CacheContant;
import com.plate.common.cache.CacheUtils;
import com.plate.frame.base.BaseService;
import com.plate.frame.security.function.model.Function;
import com.plate.frame.security.function.persistence.FunctionMapper;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：
 */
@Service
@Transactional
public class FunctionService extends BaseService {
	
	@Autowired
	private FunctionMapper mapper;
	
	public void save(Function model) {
		mapper.save(model);
		CacheUtils.remove(CacheContant.SYS_CACHE_TREE_MENU);
	}

	public void update(Function model) {
		mapper.update(model);
		CacheUtils.remove(CacheContant.SYS_CACHE_TREE_MENU);
	}

	public void delete(Integer id) {
		mapper.delete(id);
		CacheUtils.remove(CacheContant.SYS_CACHE_TREE_MENU);
	}

	public Function getFuncInfo(Integer id){
		return mapper.getFuncInfo(id);
	}
}
