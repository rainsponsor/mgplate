package com.plate.baiyue.curriculum.knowledge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plate.baiyue.common.ParseFormulaUtil;
import com.plate.baiyue.common.ParseHtmlUtil;
import com.plate.baiyue.curriculum.knowledge.model.Knowledge;
import com.plate.baiyue.curriculum.knowledge.persistence.KnowledgeMapper;
import com.plate.frame.base.BaseService;
import com.plate.frame.core.model.Catalog;
import com.plate.frame.core.persistence.CatalogMapper;

/**
 * @作者:Rainsponsor
 * @E-mail:xianzel@163.com
 * @时间: 2015-1-27
 * @描述:
 */
@Service
@Transactional
public class KnowledgeService extends BaseService{
	
	@Autowired
	private KnowledgeMapper mapper;
	@Autowired
	private CatalogMapper catalogMapper;
	
	/**
	 * 根据目录获取知识点内容
	 * @param id
	 * @return
	 */
	public Knowledge getByDirId(Integer dirId) {
		Catalog catalog = catalogMapper.get(dirId);
		Knowledge knowledge = mapper.getByDirId(dirId);
		if(knowledge == null){
			knowledge = new Knowledge();
		}
		knowledge.setCatalog(catalog);
		return knowledge;
	}
	
	/**
	 * 根据ID获取知识点内容
	 * @param id
	 * @return
	 */
	public Knowledge get(Integer id) {
		return mapper.get(id);
	}
	

	/**
	 * 修改
	 * @param knowledge
	 */
	public void update(Knowledge knowledge){
		String content = knowledge.getContent();
		if(content != null){
			StringBuilder sb = new StringBuilder(content);
			sb = ParseFormulaUtil.parseLatexByBase64ImgDataType(sb);
			String html = ParseHtmlUtil.parseHtmlFormatParagraph(sb.toString());
			knowledge.setBackups(html);
		}
		mapper.update(knowledge);
	}
	
	/**
	 * 保存
	 * @param knowledge
	 */
	public void save(Knowledge knowledge){
		String content = knowledge.getContent();
		if(content != null){
			StringBuilder sb = new StringBuilder(content);
			sb = ParseFormulaUtil.parseLatexByBase64ImgDataType(sb);
			String html = ParseHtmlUtil.parseHtmlFormatParagraph(sb.toString());
			knowledge.setBackups(html);
		}
		mapper.save(knowledge);
	}
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(Integer id){
		mapper.delete(id);
	}
	
}
