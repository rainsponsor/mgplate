package com.plate.frame.security.function.persistence;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import com.plate.common.mybatis.MybatisSqlMapper;
import com.plate.frame.security.function.model.Function;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：
 */
public interface FunctionMapper extends MybatisSqlMapper {
	
	/**
	 * 添加菜单功能
	 * @param model
	 */
	@InsertProvider(type=Function.class,method=METHOD_INSERT_SQL)
	void save(@Param("function")Function model);
	
	/**
	 * 修改菜单功能
	 * @param model
	 */
	@UpdateProvider(type=Function.class,method=METHOD_UPDATE_SQL)
	void update(@Param("function")Function model);
	
	/**
	 * 删除菜单功能
	 * @param model
	 */
	@DeleteProvider(type=Function.class,method=METHOD_DELETE_SQL)
	void delete(Integer id);
	
	/**
	 * 根据菜单ID，获取菜单下所有功能列表
	 * @param id
	 * @return
	 */
	@Select("select f.id,f.function_name,f.function_url,f.function_no,f.menutree_id from wp_function f where f.menutree_id=#{id}")
	List<Function> getFuncsByMenuTreeId(Integer id);
	
	
	/**
	 * 根据菜单ID，获取菜单下所有功能列表
	 * @param id
	 * @return
	 */
	@Select("select f.id,f.function_name,f.function_url,f.function_no,f.menutree_id from wp_function f where f.id=#{id}")
	Function getFuncInfo(Integer id);
	
}
