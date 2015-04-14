package com.plate.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @作者:Rainsponsor
 * @E-mail:xianzel@163.com
 * @时间: 2014-12-30
 * @描述: 用于Mybatis操作对象CU的SQL自动生成	Column工具
 */
@Target(value=ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PlateColumn {
	
	public String columnName() default "";
	
	public String type() default "java.lang.String";
	
}
