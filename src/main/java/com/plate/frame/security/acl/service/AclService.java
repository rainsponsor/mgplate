package com.plate.frame.security.acl.service;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plate.frame.base.BaseService;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：
 */
@Service
@Transactional
public class AclService extends BaseService {

	public void addAcl(Integer resourceId, Integer roleId, Integer permission,
			Integer resourceType) {
		// TODO Auto-generated method stub
		
	}

	public void removeAcl(int roleId) {
		// TODO Auto-generated method stub
		
	}

	public void removeAcl(int resourceId, String resourceType) {
		// TODO Auto-generated method stub
		
	}

	public void removeAcl(int roleId, String permission, String resourceType) {
		// TODO Auto-generated method stub
		
	}

	public Collection<Integer> getUserAcls(int empId, String[] permissions,
			String resourceType) {
		// TODO Auto-generated method stub
		return null;
	}

}
