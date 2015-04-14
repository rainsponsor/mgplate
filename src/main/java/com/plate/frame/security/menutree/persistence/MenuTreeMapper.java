package com.plate.frame.security.menutree.persistence;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import com.plate.common.mybatis.MybatisSqlMapper;
import com.plate.frame.security.menutree.model.MenuTree;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：
 */
public interface MenuTreeMapper extends MybatisSqlMapper {
	
	/**
	 * 根据id获取菜单信息
	 * @param id
	 * @return
	 */
	@Select("select m.id,m.menu_path,m.parent_id,m.menu_name,m.menu_url,m.is_leaf,m.menu_code,m.menu_no from wp_menutree m where m.id=#{id}")
	@Results(value={
			@Result(property="id",column="id"),
			@Result(property="children",column="id",javaType=List.class,
			many=@Many(select="com.plate.frame.security.menutree.persistence.MenuTreeMapper.getChildrenById")),
			@Result(property="functions",column="id",javaType=List.class,
			many=@Many(select="com.plate.frame.security.function.persistence.FunctionMapper.getFuncsByMenuTreeId"))
	})
	MenuTree get(Integer id);
	
	/**
	 * 根据菜单ID获取所有子菜单
	 * @param parentId
	 * @return
	 */
	@Select("select m.id,m.menu_path,m.parent_id,m.menu_name,m.menu_url,m.is_leaf,m.menu_code,m.menu_no from wp_menutree m where m.parent_id=#{id}")
	List<MenuTree> getChildrenById(Integer id);
	
	@Select("select m.id,m.menu_path,m.parent_id,m.menu_name,m.menu_url,m.is_leaf,m.menu_code,m.menu_no from wp_menutree m where m.id=#{id}")
	MenuTree getMenuTreeById(Integer id);
	
	@InsertProvider(type=MenuTree.class,method=METHOD_INSERT_SQL)
	void save(@Param("menutree")MenuTree menutree);
	
	@DeleteProvider(type=MenuTree.class,method=METHOD_DELETE_SQL)
	void delete(Integer id);
	
	@UpdateProvider(type=MenuTree.class,method=METHOD_UPDATE_SQL)
	void update(@Param("menutree")MenuTree model);
	
	@Select("select count(*) from wp_function where menutree_id = #{id}")
	Integer isExistFunc(Integer id);
}
