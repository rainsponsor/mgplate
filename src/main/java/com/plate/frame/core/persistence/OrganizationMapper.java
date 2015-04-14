package com.plate.frame.core.persistence;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.mybatis.pagination.dto.PageMyBatis;
import org.mybatis.pagination.dto.datatables.PagingCriteria;

import com.plate.common.mybatis.MybatisSqlMapper;
import com.plate.frame.core.model.Organization;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：
 */
public interface OrganizationMapper extends MybatisSqlMapper {
	
	/**
	 * 获取组织结构列表
	 * @param model
	 * @param pagingCriteria
	 * @return
	 */
	@SelectProvider(type=Organization.class,method=METHOD_SELECT_CONDATION_SQL)
	public PageMyBatis<Organization> getListByPage(@Param("organization")Organization orgModel,PagingCriteria pagingCriteria);
	
	/**
	 * 根据ID获取类实例
	 * @param id
	 * @return
	 */
	@SelectProvider(type=Organization.class,method=METHOD_SELECT_ONE_SQL)
	public Organization get(Integer id);
	
	/**
	 * 保存部门信息
	 * @param orgModel
	 */
	@InsertProvider(type=Organization.class,method=METHOD_INSERT_SQL)
	public void save(@Param("organization")Organization orgModel);
	
	/**
	 * 修改部门信息
	 * @param orgModel
	 */
	@SelectProvider(type=Organization.class,method=METHOD_UPDATE_SQL)
	public void update(@Param("organization")Organization orgModel);
	
	/**
	 * 删除指定部门信息
	 * @param id
	 */
	@DeleteProvider(type=Organization.class,method=METHOD_DELETE_SQL)
	public Integer delete(Integer id);
	
	@Select("select * from wp_organization where parent_id = #{id}")
	public List<Organization> getOrgListById(Integer id);
}
