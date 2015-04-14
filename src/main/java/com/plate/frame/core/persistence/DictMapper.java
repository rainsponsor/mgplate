package com.plate.frame.core.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import com.plate.common.mybatis.MybatisSqlMapper;
import com.plate.frame.core.model.Dict;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：
 */
public interface DictMapper extends MybatisSqlMapper {
	
	/**
	 * <功能简述>
			* <功能详细描述> 根据菜单ID获取直接子菜单
			* @param id
			* @return
			* @see [类、类#方法、类#成员]
	 */
	@Select("select d.id,d.dict_name,d.dict_depict,d.parent_id,d.parent_ids,d.sort,d.dict_value from wp_dict d where d.parent_id=#{id} order by d.sort")
	public List<Dict> getDictsByParentId(Integer id);
	
	
	/**
	 * <功能简述> 获取全部数据字典树
			* <功能详细描述>
			* @return
			* @see [类、类#方法、类#成员]
	 */
	@Select("select d.id,d.dict_name name,d.dict_depict,d.parent_id pId,d.parent_ids,d.sort,d.dict_value from wp_dict d")
	public List<Map<String, Object>> getAllDictTrees();
	
	/**
	 * <功能简述> 获取全部数据字典树
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Select("select d.id,d.dict_name,d.dict_depict,d.parent_id,d.parent_ids,d.sort,d.dict_value from wp_dict d where d.id=#{id}")
	@Results(value={
			@Result(property="id",column="id"),
			@Result(property="children",column="id",javaType=List.class,
			many=@Many(select="com.plate.frame.core.persistence.DictMapper.getDictsByParentId"))
	})
	public Dict get(Integer id);
	
	/**
	 * <功能简述>添加数据字典
			* <功能详细描述>
			* @param dict
			* @see [类、类#方法、类#成员]
	 */
	@InsertProvider(type=Dict.class,method=METHOD_INSERT_SQL)
	void save(@Param("dict")Dict dict);
	
	/**
	 * <功能简述>
			* <功能详细描述>根据id获取字典信息bean
			* @param id
			* @return
			* @see [类、类#方法、类#成员]
	 */
	@Select("select d.id,d.dict_name,d.dict_depict,d.parent_id,d.parent_ids,d.sort,d.dict_value from wp_dict d where d.id=#{id}")
	public Dict getDictTreeById(Integer id);
	
	/**
	 * <功能简述>删除数据字典
			* <功能详细描述>根据字典id删除
			* @param id
			* @return
			* @see [类、类#方法、类#成员]
	 */
	@Delete("delete from wp_dict where id=#{id}")
	public Integer delete(Integer id);
	
	/**
	 * <功能简述>修改数据字典
	 * <功能详细描述>根据id修改数据
	 * @param dict
	 * @see [类、类#方法、类#成员]
	 */
	@UpdateProvider(type=Dict.class,method=METHOD_UPDATE_SQL)
	void update(@Param("dict")Dict dict);
	
	
}
