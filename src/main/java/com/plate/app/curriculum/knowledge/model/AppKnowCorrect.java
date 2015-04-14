package com.plate.app.curriculum.knowledge.model;

import com.plate.common.annotation.PlateColumn;
import com.plate.common.annotation.PlateTable;
import com.plate.frame.base.BaseModel;

/**
 * @作者:Rainsponsor
 * @E-mail:xianzel@163.com
 * @时间: 2015-2-5
 * @描述: 知识点纠错 - App模型
 */
@PlateTable(tableName = "c_know_correction")
public class AppKnowCorrect extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	
	@PlateColumn
	private Integer knowledge_id;
	@PlateColumn
	private Integer user_id;
	@PlateColumn
	private Integer replyer_id;//回复人ID
	@PlateColumn
	private String replyer_name;//回复人姓名
	@PlateColumn
	private String content;//纠错内容
	@PlateColumn
	private String send_time;//纠错时间
	@PlateColumn
	private String reply_time;//回复时间
	@PlateColumn
	private String reply_content;//回复内容
	
	public AppKnowCorrect(){}
	
	public AppKnowCorrect(Integer knowledge_id, Integer user_id,String content,String send_time) {
		this.knowledge_id = knowledge_id;
		this.user_id = user_id;
		this.content = content;
		this.send_time = send_time;
	}
	
	public Integer getKnowledge_id() {
		return knowledge_id;
	}
	public void setKnowledge_id(Integer knowledge_id) {
		this.knowledge_id = knowledge_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getReplyer_id() {
		return replyer_id;
	}
	public void setReplyer_id(Integer replyer_id) {
		this.replyer_id = replyer_id;
	}
	public String getReplyer_name() {
		return replyer_name;
	}
	public void setReplyer_name(String replyer_name) {
		this.replyer_name = replyer_name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSend_time() {
		return send_time;
	}
	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}
	public String getReply_time() {
		return reply_time;
	}
	public void setReply_time(String reply_time) {
		this.reply_time = reply_time;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	
	
	
}
