package com.plate.app.curriculum.knowledge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plate.app.curriculum.knowledge.model.AppKnowCorrect;
import com.plate.app.curriculum.knowledge.persistence.AppKnowMapper;
import com.plate.common.util.DateUtils;


/**
 * @作者:Rainsponsor
 * @E-mail:xianzel@163.com
 * @时间: 2015-2-5
 * @描述:
 */
@Service
@Transactional
public class AppKnowService{
	
	@Autowired
	private AppKnowMapper mapper;
	
	/**
	 * 
	 * @param id		知识点id
	 * @param content	纠正内容
	 * @param userid	用户id	如果userid为0则说明未登陆用户
	 */
	public void correct(Integer id,String content,Integer userid){
		String send_time = DateUtils.getDate("yyyy-MM-dd HH:mm:ss");
		AppKnowCorrect appKnow = new AppKnowCorrect(id,userid,content,send_time);
		mapper.saveCorrect(appKnow);
	}
	
}
