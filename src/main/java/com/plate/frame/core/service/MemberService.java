package com.plate.frame.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plate.common.util.MD5;
import com.plate.frame.base.BaseService;
import com.plate.frame.core.model.User;
import com.plate.frame.core.persistence.MemberMapper;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：
 */
@Service
@Transactional
public class MemberService extends BaseService {
	@Autowired
	private MemberMapper mapper;
	
	/**
	 * 根据ID获取类实例
	 * @param id
	 * @return
	 */
		public User get(Integer id){
			return mapper.get(id);
	}
	
	/**
	 * <功能简述>保存密码
			* <功能详细描述>
			* @param user
			* @see [类、类#方法、类#成员]
	 */
	public void updatePwd(User user){
		user.setNew_password(MD5.getMD5ofStr(user.getNew_password()));
		mapper.updatePwd(user);
	}
	
	/**
	 * <功能简述>验证密码
			* <功能详细描述>
			* @param user
			* @return
			* @see [类、类#方法、类#成员]
	 */
	public boolean validatePwd(User user){
		boolean bol = false;
		user.setLogin_password(MD5.getMD5ofStr(user.getLogin_password()));
		Integer result = mapper.validatePwd(user);
		if(result == 0){
			bol = true;
		}
		return bol;
	}
	
	public void form(User user){
		mapper.form(user);
	}
	
}
