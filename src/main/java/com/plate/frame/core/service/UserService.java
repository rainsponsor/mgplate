package com.plate.frame.core.service;

import org.mybatis.pagination.dto.PageMyBatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plate.common.cache.CacheContant;
import com.plate.common.cache.CacheUtils;
import com.plate.common.util.MD5;
import com.plate.frame.base.BaseService;
import com.plate.frame.core.model.User;
import com.plate.frame.core.persistence.UserMapper;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：
 */
@Service
@Transactional
public class UserService extends BaseService {

	@Autowired
	private UserMapper userMapper;
	
	public PageMyBatis<User> getListByPage(User model) {
		return userMapper.getListByPage(model,model.getPagingCriteria());
	}

	/**
	 * <功能简述>保存系统用户
			* <功能详细描述> 添加系统用户并保存
			* @param user
			* @see [类、类#方法、类#成员]
	 */
	public void save(User user) {
		user.setLogin_password("123456");
		user.setLogin_password(initPWD(user.getLogin_password()));
		userMapper.save(user);
		CacheUtils.remove(CacheContant.SYS_CACHE_TREE_ORGS_USERS);
	}
	
	/**
	 * <功能简述>重置密码
			* <功能详细描述> 重置密码123456
			* @param user
			* @see [类、类#方法、类#成员]
	 */
	public void reset(Integer id){
		String password = initPWD("123456");
		userMapper.reset(id,password);
		CacheUtils.remove(CacheContant.SYS_CACHE_TREE_ORGS_USERS);
	}
	
	/**
	 * <功能简述>密码加密
			* <功能详细描述>获取加密后密码
			* @param passWord
			* @return String
			* @see [类、类#方法、类#成员]
	 */
	public String initPWD(String passWord){
		return MD5.getMD5ofStr(passWord);
	}
	
	/**
	 * <功能简述>根据ID修改系统用户
	 * @param id
	 * <功能详细描述>修改用户
	 * @see [类、类#方法、类#成员]
	 */
	public void update(User user) {
		userMapper.update(user);
		CacheUtils.remove(CacheContant.SYS_CACHE_TREE_ORGS_USERS);
	}

	/**
	 * <功能简述>根据ID停用系统用户
	 * @param id
	 * <功能详细描述>修改状态
	 * @see [类、类#方法、类#成员]
	 */
	public void delete(Integer id) {
		userMapper.delete(id);
		CacheUtils.remove(CacheContant.SYS_CACHE_TREE_ORGS_USERS);
	}
	
	/**
	 * <功能简述>根据ID物理删除系统用户
	 * @param id
	 * <功能详细描述>彻底删除用户
	 * @see [类、类#方法、类#成员]
	 */
	public void delUser(Integer id) {
		userMapper.delUser(id);
		userMapper.delUserRole(id);
		CacheUtils.remove(CacheContant.SYS_CACHE_TREE_ORGS_USERS);
	}
	
	/**
	 * <功能简述>根据ID恢复系统用户
	 * <功能详细描述>修改状态
	 * @param id
	 * @see [类、类#方法、类#成员]
	 */
	public void recovery(Integer id) {
		userMapper.recovery(id);
	}

	/**
	 * 根据ID获取类实例
	 * @param id
	 * @return
	 */
	public User get(Integer id){
		return userMapper.get(id);
	}
	
	/**
	  * <功能简述>登陆名是否重复
	 		* <功能详细描述>null 不重复
	 		* @param request
	 		* @return
	 		* @see [类、类#方法、类#成员]
	  */
	public User getUserByLoginName(String loginName){
		return userMapper.getUserByLoginName(loginName);
	}
}
