package com.plate.frame.base.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.plate.frame.core.model.Dict;
import com.plate.frame.core.util.DictUtil;

/**
 * 
 * @author Rainsponsor
 * @描述:根据ParentID与本身的value获取text内容
 */
public class DicTextTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	private String id;
	private String value;
	private Integer parentDicId;

	public int doStartTag() throws JspException {
		JspWriter out = this.pageContext.getOut();
		List<Dict> list = DictUtil.getDictsByParentId(parentDicId);
		try {
			StringBuilder text = new StringBuilder("<span id='" + id + "' name='" + id
					+ "'>");
			//text
			if(list!=null && list.size()>0){
				for(Dict dict : list){
					if(value.equals(dict.getDict_value())){
						text.append(dict.getDict_name());
					}
				}
			}
			text.append("</span>");
			out.print(text.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doStartTag();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return 返回 parentDicId
	 */
	
	public Integer getParentDicId() {
		return parentDicId;
	}

	/**
	 * @param 对parentDicId进行赋值
	 */
	
	public void setParentDicId(Integer parentDicId) {
		this.parentDicId = parentDicId;
	}

}
