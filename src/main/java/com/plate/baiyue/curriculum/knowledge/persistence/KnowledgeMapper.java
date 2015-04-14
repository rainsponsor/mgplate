package com.plate.baiyue.curriculum.knowledge.persistence;



import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.plate.baiyue.curriculum.knowledge.model.Knowledge;
import com.plate.common.mybatis.MybatisSqlMapper;

/**
 * @作者:Rainsponsor
 * @E-mail:xianzel@163.com
 * @时间: 2015-1-27
 * @描述:
 */
public interface KnowledgeMapper extends MybatisSqlMapper {
	
	/**
	 * 根据ID获取知识点信息
	 * @param id
	 * @return
	 */
	@Select("select k.id,k.search_key,k.content,k.dir_id,k.dir_ids,k.sub_id,k.serialno,k.creator from c_knowledge k where k.id=#{id}")
	Knowledge get(Integer id);
	
	/**
	 * 根据ID获取知识点信息
	 * @param id
	 * @return
	 */
	@Select("select k.id,k.search_key,k.backups content,k.dir_id,k.dir_ids,k.sub_id,k.serialno,k.creator from c_knowledge k where k.dir_ids like concat('%,',#{id},',%')")
	List<Knowledge> getDescendantKnowledgeByCatalog(Integer id);
	
	/**
	 * 根据目录获取知识内容
	 * @param dirId
	 * @return
	 */
	@Select("select k.id,k.search_key,k.content,k.dir_id,k.dir_ids,k.sub_id,k.serialno,k.creator from c_knowledge k where k.dir_id=#{dirId}")
	Knowledge getByDirId(Integer dirId);
	
	/**
	 * 保存
	 * @param knowledge
	 */
	@InsertProvider(type=Knowledge.class,method=METHOD_INSERT_SQL)
	void save(@Param("knowledge")Knowledge knowledge);
	
	/**
	 * 修改
	 * @param knowledge
	 */
	@InsertProvider(type=Knowledge.class,method=METHOD_UPDATE_SQL)
	void update(@Param("knowledge")Knowledge knowledge);
	
	/**
	 * 删除
	 * @param id
	 */
	@DeleteProvider(type=Knowledge.class,method=METHOD_DELETE_SQL)
	void delete(Integer id);

}
