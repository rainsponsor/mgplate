package com.plate.common.mybatis;

public interface MybatisSqlMapper {
	
	/**
	 * 平台自动生成单表插入SQL
	 */
	public static final String METHOD_INSERT_SQL = "getInsertSql";
	/**
	 * 平台自动生成单表修改SQL
	 */
	public static final String METHOD_UPDATE_SQL = "getUpdateSql";
	/**
	 * 平台自动生成单表删除SQL
	 */
	public static final String METHOD_DELETE_SQL = "getDeleteSql";
	/**
	 * 平台自动生成单表全查询SQL
	 */
	public static final String METHOD_SELECT_SQL = "getSelectSql";
	/**
	 * 平台自动生成单表查询一条数据(id为条件时)SQL
	 */
	public static final String METHOD_SELECT_ONE_SQL = "getOneSql";
	/**
	 * 平台自动生成单表条件查询SQL
	 */
	public static final String METHOD_SELECT_CONDATION_SQL = "getSelectSqlByCondation";
	
}
