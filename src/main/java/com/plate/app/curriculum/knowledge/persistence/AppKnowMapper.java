package com.plate.app.curriculum.knowledge.persistence;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;

import com.plate.app.curriculum.knowledge.model.AppKnowCorrect;
import com.plate.common.mybatis.MybatisSqlMapper;

/**
 * @作者:Rainsponsor
 * @E-mail:xianzel@163.com
 * @时间: 2015-2-6
 * @描述:
 */
public interface AppKnowMapper extends MybatisSqlMapper {
	
	/**
	 * 保存纠错信息
	 * @param appknowcorrect
	 */
	@InsertProvider(type=AppKnowCorrect.class,method=METHOD_INSERT_SQL)
	public void saveCorrect(@Param("appknowcorrect") AppKnowCorrect appknowcorrect);
	
	
	
}
