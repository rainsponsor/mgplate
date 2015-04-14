package com.plate.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @作者:Rainsponsor
 * @E-mail:xianzel@163.com
 * @时间: 2014-12-30
 * @描述: 用于Mybatis操作对象CU的SQL自动生成 Table工具
 */
@Target(value=ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PlateTable {
	
	/**
	 * 操作类型对应的数据库表名
	 * 暂时只支持数据库中ID自增长操作类型
	 * @return
	 */
	public String tableName();
	
}
