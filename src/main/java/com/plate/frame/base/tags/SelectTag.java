package com.plate.frame.base.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.plate.frame.core.model.Dict;
import com.plate.frame.core.util.DictUtil;

public class SelectTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	private String id;
	private String value;
	private Boolean option;
	private String cssClass;
	private Integer parentDicId;

	public int doStartTag() throws JspException {
		JspWriter out = this.pageContext.getOut();
		List<Dict> list = DictUtil.getDictsByParentId(parentDicId);
		try {
			StringBuilder select = new StringBuilder("<select id='" + id + "' name='" + id
					+ "' class='" + cssClass + "'>");
			// 自定义选项
			if (option != null && option) {
				select.append("<option value=''>--请选择--</option>");
			}
			//options
			if(list!=null && list.size()>0){
				for(Dict dict : list){
					if(value.equals(dict.getDict_value())){
						select.append("<option selected='selected '").append(" value='"+dict.getDict_value()+"' >").append(dict.getDict_name()).append("</option>");
					}else{
						select.append("<option").append(" value='"+dict.getDict_value()+"' >").append(dict.getDict_name()).append("</option>");
					}
				}
			}
			select.append("</select>");
			out.print(select.toString());
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
	 * @return 返回 option
	 */

	public Boolean getOption() {
		return option;
	}

	/**
	 * @param 对option进行赋值
	 */

	public void setOption(Boolean option) {
		this.option = option;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
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
