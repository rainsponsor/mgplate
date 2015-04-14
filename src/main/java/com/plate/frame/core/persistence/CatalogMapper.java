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
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import com.plate.common.mybatis.MybatisSqlMapper;
import com.plate.frame.core.model.Catalog;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：目录持久化操作接口
 */
public interface CatalogMapper extends MybatisSqlMapper {
	
	/**
	 * <功能简述>
			* <功能详细描述> 根据目录ID获取直接子目录
			* @param id
			* @return
			* @see [类、类#方法、类#成员]
	 */
	@Select("SELECT d.id,d.name,d.parent_id,d.dir_ids,d.leaf,d.serialno,d.sub_id,d.version,d.file_path FROM c_dir d where d.parent_id=#{id} order by d.serialno")
	public List<Catalog> getCatalogsByParentId(Integer id);
	
	
	/**
	 * <功能简述> 获取全部目录树
			* <功能详细描述>
			* @return
			* @see [类、类#方法、类#成员]
	 */
	@Select("SELECT d.id,d.name,d.parent_id pId,d.dir_ids,d.leaf,d.serialno,d.sub_id,d.version,d.file_path FROM c_dir d order by d.serialno")
	public List<Map<String, Object>> getAllCatalogTrees();
	
	/**
	 * <功能简述> 获取全部目录树
	 * <功能详细描述>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Select("SELECT d.id,d.name,d.parent_id,d.dir_ids,d.leaf,d.serialno,d.sub_id,d.version,d.file_path FROM c_dir d where d.id=#{id}")
	@Results(value={
			@Result(property="id",column="id"),
			@Result(property="children",column="id",javaType=List.class,
			many=@Many(select="com.plate.frame.core.persistence.CatalogMapper.getCatalogsByParentId"))
	})
	public Catalog get(Integer id);
	
	/**
	 * <功能简述>添加目录
			* <功能详细描述>
			* @param dict
			* @see [类、类#方法、类#成员]
	 */
	@InsertProvider(type=Catalog.class,method=METHOD_INSERT_SQL)
	void save(@Param("catalog")Catalog catalog);
	
	/**
	 * <功能简述>
			* <功能详细描述>根据id获取目录信息bean
			* @param id
			* @return
			* @see [类、类#方法、类#成员]
	 */
	@Select("SELECT d.id,d.name,d.parent_id,d.dir_ids,d.leaf,d.serialno,d.sub_id,d.version,d.file_path FROM c_dir d where d.id=#{id}")
	public Catalog getCatalogById(Integer id);
	
	/**
	 * 获取指定ID的所有后代成员
	 * @param id
	 * @return
	 */
	@Select("SELECT d.id,d.name,d.parent_id,d.dir_ids,d.leaf,d.serialno,d.sub_id,d.version,d.file_path FROM c_dir d where d.dir_ids like concat('%,',#{id},',%')")
	public List<Catalog> getDescendantCatalogById(Integer id);
	
	/**
	 * <功能简述>删除目录
			* <功能详细描述>根据字典id删除
			* @param id
			* @return
			* @see [类、类#方法、类#成员]
	 */
	@Delete("delete from c_dir where id=#{id}")
	public Integer delete(Integer id);
	
	/**
	 * <功能简述>修改目录
	 * <功能详细描述>根据id修改数据
	 * @param dict
	 * @see [类、类#方法、类#成员]
	 */
	@UpdateProvider(type=Catalog.class,method=METHOD_UPDATE_SQL)
	void update(@Param("catalog")Catalog catalog);
	
	/**
	 * <功能简述>修改目录
	 * <功能详细描述>根据id修改数据
	 * @param dict
	 * @see [类、类#方法、类#成员]
	 */
	@Update("update c_dir set version=version+1 where id=#{id}")
	void updateCatalogVersion(Integer id);
	
}
