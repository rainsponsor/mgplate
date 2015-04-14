package com.plate.frame.core.model;

import com.plate.common.annotation.PlateColumn;
import com.plate.common.annotation.PlateTable;
import com.plate.frame.base.BaseModel;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-10-30
 * @描述：用户模型
 */
@PlateTable(tableName = "wp_user")
public class User extends BaseModel {
	@PlateColumn
	private String user_name;//系统用户姓名
	@PlateColumn
	private String login_name;//系统用户登录名
	@PlateColumn
	private String login_password;//系统用户登录密码
	@PlateColumn
	private Integer wp_org_id;//系统用户所属部门
	
	private String org_name;//系统用户所属部门名称
	
	private Integer user_status;//用户状态0有效 1无效
	@PlateColumn
	private String phone;//系统用户电话号码
	@PlateColumn
	private String email;//电子邮件
	@PlateColumn
	private Integer major;//直接主管
	
	private String major_name;//直接主管
	@PlateColumn
	private Integer is_major;//是否是主管
	private String new_password;//新密码
	
	public User() {
		super();
	}

	/**
	 * @return 返回 user_name
	 */
	
	public String getUser_name() {
		return user_name;
	}

	/**
	 * @param 对user_name进行赋值
	 */
	
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	/**
	 * @return 返回 login_name
	 */
	
	public String getLogin_name() {
		return login_name;
	}

	/**
	 * @param 对login_name进行赋值
	 */
	
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}

	/**
	 * @return 返回 login_password
	 */
	
	public String getLogin_password() {
		return login_password;
	}

	/**
	 * @param 对login_password进行赋值
	 */
	
	public void setLogin_password(String login_password) {
		this.login_password = login_password;
	}

	/**
	 * @return 返回 wp_org_id
	 */
	
	public Integer getWp_org_id() {
		return wp_org_id;
	}

	/**
	 * @param 对wp_org_id进行赋值
	 */
	
	public void setWp_org_id(Integer wp_org_id) {
		this.wp_org_id = wp_org_id;
	}

	/**
	 * @return 返回 org_name
	 */
	
	public String getOrg_name() {
		return org_name;
	}

	/**
	 * @param 对org_name进行赋值
	 */
	
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

	/**
	 * @return 返回 user_status
	 */
	
	public Integer getUser_status() {
		return user_status;
	}

	/**
	 * @param 对user_status进行赋值
	 */
	
	public void setUser_status(Integer user_status) {
		this.user_status = user_status;
	}

	/**
	 * @return 返回 phone
	 */
	
	public String getPhone() {
		return phone;
	}

	/**
	 * @param 对phone进行赋值
	 */
	
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return 返回 email
	 */
	
	public String getEmail() {
		return email;
	}

	/**
	 * @param 对email进行赋值
	 */
	
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return 返回 major
	 */
	
	public Integer getMajor() {
		return major;
	}

	/**
	 * @param 对major进行赋值
	 */
	
	public void setMajor(Integer major) {
		this.major = major;
	}

	/**
	 * @return 返回 is_major
	 */
	
	public Integer getIs_major() {
		return is_major;
	}

	/**
	 * @param 对is_major进行赋值
	 */
	
	public void setIs_major(Integer is_major) {
		this.is_major = is_major;
	}

	public boolean isAdmin(){
		return isAdmin(this.getId());
	}
	
	public static boolean isAdmin(Integer id){
		return id != null && (id==1);
	}

	/**
	 * @return 返回 major_name
	 */
	
	public String getMajor_name() {
		return major_name;
	}

	/**
	 * @param 对major_name进行赋值
	 */
	
	public void setMajor_name(String major_name) {
		this.major_name = major_name;
	}

	/**
	 * @return 返回 new_password
	 */
	
	public String getNew_password() {
		return new_password;
	}

	/**
	 * @param 对new_password进行赋值
	 */
	
	public void setNew_password(String new_password) {
		this.new_password = new_password;
	}
}
