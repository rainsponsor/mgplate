package com.plate.frame.base;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Map;

import org.mybatis.pagination.dto.datatables.PagingCriteria;

import com.plate.common.annotation.PlateColumn;
import com.plate.common.annotation.PlateSelectCondition;
import com.plate.common.annotation.PlateTable;


/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-22
 * @描述：领域模型上层公共基类
 */
public class BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 公共基类统一标识
	 */
	@PlateColumn(columnName="id")
	private Integer id;
	
	private Integer menuId;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public PagingCriteria getPagingCriteria() {
		PagingCriteria pageCriteria = PagingCriteria.createCriteria(getPageSize(),getDisplaySize(), getPageNo());
		return pageCriteria;
	}
	
	private String getTableName(){
		return this.getClass().getAnnotation(PlateTable.class).tableName();
	}
	
	private String getColumnList(){
		Field[] fs = this.getClass().getDeclaredFields();
		StringBuilder field = new StringBuilder("");
		try {
			for(Field f : fs){
				PlateColumn column = f.getAnnotation(PlateColumn.class);
				if(column != null){
					String fieldName = f.getName();
					field.append(fieldName).append(",");
				}
			}
			field.deleteCharAt(field.length() - 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return field.toString();
	}
	
	private String getPrepareList(){
		String className = this.getClass().getSimpleName().toLowerCase();
		Field[] fs = this.getClass().getDeclaredFields();
		StringBuilder pre = new StringBuilder();
		try {
			for(Field f : fs){
				PlateColumn column = f.getAnnotation(PlateColumn.class);
				if(column != null){
					pre.append("#{").append(className).append(".").append(f.getName()).append("}").append(",");
				}
			}
			pre.deleteCharAt(pre.length() - 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pre.toString();
	}
	
	public String getInsertSql(){
		StringBuilder sql = new StringBuilder("insert into ").append(getTableName()).append("(").append(getColumnList()).append(")");
		sql.append(" values(").append(getPrepareList()).append(")");
		return sql.toString();
	}
	
	public String getUpdateSql(){
		String className = this.getClass().getSimpleName().toLowerCase();
		StringBuilder sql = new StringBuilder("update ").append(getTableName()).append(" set ");
		Field[] fs = this.getClass().getDeclaredFields();
		try {
			for(Field f : fs){
				PlateColumn column = f.getAnnotation(PlateColumn.class);
				if(column != null){
					String fieldName = f.getName();
					sql.append(fieldName).append("=#{").append(className).append(".").append(fieldName).append("},");
				}
			}
			sql.deleteCharAt(sql.length() - 1);
			sql.append(" where id=").append("#{").append(className).append(".id}");;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sql.toString();
	}
	
	public String getDeleteSql(){
		StringBuilder sql = new StringBuilder("delete from ").append(getTableName()).append(" where id=#{id}");
		return sql.toString();
	}
	
	/**
	 * 获取单表基本查询语句
	 * @param map
	 * @return
	 */
	public String getSelectSql(){
		StringBuilder sql = new StringBuilder("select id,").append(getColumnList()).append(" from ");
		sql.append(getTableName()).append(" where 1=1 ");
		return sql.toString();
	}
	
	/**
	 * 获取指定ID表单数据基本条件查询语句
	 * @param map
	 * @return
	 */
	public String getOneSql(){
		StringBuilder sql = new StringBuilder("select id,").append(getColumnList()).append(" from ");
		sql.append(getTableName()).append(" where id=#{id}");
		return sql.toString();
	}
	
	public String getSelectSqlByCondation(Map<String,Object> map){
		String className = this.getClass().getSimpleName().toLowerCase();
		Object obj = map.get(className);
		if(obj == null){
			try {
				throw new Exception("BizMapper中如果使用getSelectSql方法，那么对象参数名称必须为类名小写！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		StringBuilder sql = new StringBuilder("select id,").append(getColumnList()).append(" from ");
		sql.append(getTableName()).append(" where 1=1 ");
		Field[] fs = this.getClass().getDeclaredFields();
		try {
			for(Field f : fs){
				PlateSelectCondition selectCondation = f.getAnnotation(PlateSelectCondition.class);
				if(selectCondation != null){
					String likeAlias = selectCondation.linkAlias();
					String fieldName = f.getName();
					boolean isAccess = f.isAccessible();
					if(!isAccess){
						f.setAccessible(true);
					}
					Object val = f.get(obj);
					if(val!=null){
						String sqlParams = className+"."+fieldName;
						//like
						if("like".equalsIgnoreCase(likeAlias)){
							sql.append(" and ").append(fieldName).append(" like CONCAT('%',#{"+sqlParams+"},'%')");
						}else if("=".equals(likeAlias)){
							sql.append(" and ").append(fieldName).append("=#{"+sqlParams+"}");
						}else if(">".equals(likeAlias)){
							sql.append(" and ").append(fieldName).append(">#{"+sqlParams+"}");
						}else if("<".equals(likeAlias)){
							sql.append(" and ").append(fieldName).append("<#{"+sqlParams+"}");
						}
					}
					f.setAccessible(isAccess);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sql.toString();
	}
	
	// 显示/隐藏
	public static final String SHOW = "1";
	public static final String HIDE = "0";
	
	// 是/否
	public static final String YES = "1";
	public static final String NO = "0";

	// 删除标记（0：正常；1：删除；2：审核；）
	public static final String FIELD_DEL_FLAG = "delFlag";
	public static final String DEL_FLAG_NORMAL = "0";
	public static final String DEL_FLAG_DELETE = "1";
	public static final String DEL_FLAG_AUDIT = "2";
	
	//===========================
	public int pageNo = 1; // 当前页
	public int pageSize = 10; // 每页显示记录数目
	public int pageCount; // 总页数
	public int rowCount; // 记录总数
	public int displaySize; // 分页起始条数
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {

		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getDisplaySize() {
		displaySize = pageNo * pageSize - pageSize;
		return displaySize;
	}

	public void setDisplaySize(int displaySize) {
		this.displaySize = displaySize;
	}

	/**
	 * @return 返回 menuId
	 */
	
	public Integer getMenuId() {
		return menuId;
	}

	/**
	 * @param 对menuId进行赋值
	 */
	
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}


}
