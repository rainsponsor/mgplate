package com.plate.frame.core.service;

import java.util.List;
import java.util.Map;

import org.mybatis.pagination.dto.PageMyBatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plate.common.cache.CacheContant;
import com.plate.common.cache.CacheUtils;
import com.plate.common.util.MD5;
import com.plate.frame.base.BaseService;
import com.plate.frame.core.model.Dict;
import com.plate.frame.core.model.User;
import com.plate.frame.core.persistence.DictMapper;
import com.plate.frame.core.persistence.UserMapper;
import com.plate.frame.core.util.DictUtil;
import com.plate.frame.security.menutree.model.MenuTree;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：
 */
@Service
@Transactional
public class DictService extends BaseService {

	@Autowired
	private DictMapper mapper;
	
//	public PageMyBatis<User> getListByPage(User model) {
//		return userMapper.getListByPage(model,model.getPagingCriteria());
//	}
//
//	/**
//	 * <功能简述>保存系统用户
//			* <功能详细描述> 添加系统用户并保存
//			* @param user
//			* @see [类、类#方法、类#成员]
//	 */
//	public void save(User user) {
//		user.setLogin_password(initPWD(user.getLogin_password()));
//		userMapper.save(user);
//		CacheUtils.remove(CacheContant.SYS_CACHE_TREE_ORGS_USERS);
//	}
//	
//	/**
//	 * <功能简述>重置密码
//			* <功能详细描述> 重置密码123456
//			* @param user
//			* @see [类、类#方法、类#成员]
//	 */
//	public void reset(Integer id){
//		String password = initPWD("123456");
//		userMapper.reset(id,password);
//	}
//	
//	/**
//	 * <功能简述>密码加密
//			* <功能详细描述>获取加密后密码
//			* @param passWord
//			* @return String
//			* @see [类、类#方法、类#成员]
//	 */
//	public String initPWD(String passWord){
//		return MD5.getMD5ofStr(passWord);
//	}
//	
//	/**
//	 * <功能简述>根据ID修改系统用户
//	 * @param id
//	 * <功能详细描述>修改用户
//	 * @see [类、类#方法、类#成员]
//	 */
//	public void update(User user) {
//		userMapper.update(user);
//		CacheUtils.remove(CacheContant.SYS_CACHE_TREE_ORGS_USERS);
//	}
//
//	/**
//	 * <功能简述>根据ID逻辑删除系统用户
//	 * @param id
//	 * <功能详细描述>修改状态
//	 * @see [类、类#方法、类#成员]
//	 */
//	public void delete(Integer id) {
//		userMapper.delete(id);
//		CacheUtils.remove(CacheContant.SYS_CACHE_TREE_ORGS_USERS);
//	}

	/**
	 * <功能简述>获取select数据
			* <功能详细描述>根据parentId获取数据字典内容
			* @param parentId
			* @return
			* @see [类、类#方法、类#成员]
	 */
	public List<Dict> getDictsByParentId(Integer parentId) {
		return mapper.getDictsByParentId(parentId);
	}
	
	/**
	 * <功能简述>获取全部数据字典树
			* <功能详细描述>
			* @return
			* @see [类、类#方法、类#成员]
	 */
	public List<Map<String, Object>> getAllDictTrees() {
		List<Map<String, Object>> result = mapper.getAllDictTrees();
		return result;
	}
	
	/**
	 * <功能简述>获取数据字典内容
			* <功能详细描述>根据父ID 获取数据字典内容
			* @param id
			* @return
			* @see [类、类#方法、类#成员]
	 */
	public Dict get(Integer id){
		Dict dicttree = null;
		if(id==null || id == 0){
			dicttree = new Dict();
			dicttree.setId(0);
			dicttree.setDict_name("数据字典");
			dicttree.setChildren(mapper.getDictsByParentId(0));
		}else{
			dicttree = mapper.get(id);
		}
		return dicttree;
	}
	
	/**
	 * <功能简述>添加数据字典
			* <功能详细描述>
			* @param dict
			* @see [类、类#方法、类#成员]
	 */
	public void save(Dict dict){
		dict.setParent_ids(getParentIds(dict.getParent_id()));
		mapper.save(dict);
		CacheUtils.remove(DictUtil.SYS_DIC);
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
			return mapper.getDictTreeById(parentId).getParent_ids() + parentId+",";
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
		Integer result = mapper.delete(id);
		CacheUtils.remove(DictUtil.SYS_DIC);
		return result;
		
	}
	
	public void update(Dict dict){
		mapper.update(dict);
		CacheUtils.remove(DictUtil.SYS_DIC);
	}
}
