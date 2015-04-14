package com.plate.frame.base.tags;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import org.mybatis.pagination.dto.PageMyBatis;

import com.plate.frame.base.BaseModel;

@SuppressWarnings({ "rawtypes"})
public class PageTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private String action = "";
	private String field = "page";
	private String showReSize = "true";
	private String onlyOneShow = "true";
	private String useModelDriven = "false";
	private String mhFrom = "";
	private String showListNo = "true";
	private int pageNo = 1;
	private int pageSize = 10;
	private int count = 1;
	private int pageCount = 1;

	private int listPageCount = 10;
	private String otherStyle = ""; // 其他页码样式
	private String nonceStyle = ""; // 当前页码样式
	private BaseModel page;

	public int doStartTag() throws JspException {
		listPageCount = 10;
		HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
		JspWriter out = this.pageContext.getOut();
		
		PageMyBatis pageMyBatis = null;
		Enumeration<String> enums = request.getAttributeNames();
		while (enums.hasMoreElements()) {
			String element = enums.nextElement();
			Object elementObj = request.getAttribute(element);
			if (elementObj instanceof PageMyBatis) {
				pageMyBatis = (PageMyBatis) elementObj;
				continue;
			}
			if (page == null && (elementObj instanceof BaseModel)) {
				page = (BaseModel) elementObj;
			}
		}
		if (page != null) {
			pageNo = page.getPageNo();
			pageSize = page.getPageSize();
			count = Integer.valueOf(String.valueOf(pageMyBatis.getTotal()));
		}

		if (count % pageSize == 0) {
			pageCount = count / pageSize;
		} else {
			pageCount = count / pageSize + 1;
		}

		if (listPageCount > pageCount) {
			listPageCount = pageCount;
		}
		if (pageNo > pageCount) {
			pageNo = pageCount;
		}
		if (pageNo < 1) {
			pageNo = 1;
		}

		if (pageCount > 1 || "true".equals(this.onlyOneShow) || "TRUE".equals(this.onlyOneShow)) {
			try {

				out.print("<form name=\"page\" action=\"" + request.getContextPath() + action
						+ "\" method=\"post\">\n");

				if ("true".equals(useModelDriven) || "TRUE".equals(useModelDriven)) {
					out.print("<input type=\"hidden\" id=\"pageNo\" name=\"pageNo\" />\n");
					out.print("<input type=\"hidden\" id=\"pageSize\" name=\"pageSize\" value=\""
							+ pageSize + "\" />\n");
					out.print("<input type=\"hidden\" id=\"pageCount\" name=\"" + field
							+ ".pageCount\" value=\"" + pageCount + "\" />\n");
				} else {

					out.print("<input type=\"hidden\" id=\"pageNo\" name=\"pageNo\" />\n");
					out.print("<input type=\"hidden\" id=\"pageSize\" name=\"pageSize\" value=\""
							+ pageSize + "\" />\n");
					out.print("<input type=\"hidden\" id=\"pageCount\" name=\"pageCount\" value=\""
							+ pageCount + "\" />\n");
				}

				out.print("</form>\n");

				out.print("<script type=\"text/javascript\">\n");
				out.print("function jump(no,size){     \n");
				out.print("	if(size==null || size==\"\"){\n");
				out.print("		document.getElementById('pageNo').value= no;\n");
				if (!"".equals(mhFrom))
					out.print("     var mhform=document.forms['" + mhFrom + "']\n"
							+ "        for( var k=0 ;k<mhform.elements.length;k++){\n"
							+ "            var ins=document.createElement(\"input\")\n"
							+ "                ins.type=\"hidden\" \n "
							+ "                ins.name=mhform.elements[k].name\n"
							+ "                ins.value=mhform.elements[k].value\n"
							+ "                document.forms['page'].appendChild(ins);\n"
							+ "          }  \n");

				out.print("		document.forms['page'].submit();\n	}else{\n");
				if (!"".equals(mhFrom))
					out.print("     var mhform=document.forms['" + mhFrom + "']\n"
							+ "        for( var k=0 ;k<mhform.elements.length;k++){\n"
							+ "            var ins=document.createElement(\"input\")\n"
							+ "                ins.type=\"hidden\" \n "
							+ "                ins.name=mhform.elements[k].name\n"
							+ "                ins.value=mhform.elements[k].value\n"
							+ "                document.forms['page'].appendChild(ins);\n"
							+ "          }  \n");

				out.print("		if(no=='' || no == 0){		\n" + "			no = 1;		\n" + "		}		\n"
						+ "		if(size=='' || size == 0 ){		\n" + "			size = 10;		\n" + "		}		\n"
						+ "		document.getElementById('pageNo').value= no;\n"
						+ "		document.getElementById('pageSize').value= size\n"
						+ "		document.forms['page'].submit();\n	}\n");

				out.print("}\n");
				out.print("</script>\n");
				out.print("<ul class=\"pager\"> \n");
				if (pageNo == 1) {
					out.print("<li class='disabled'><a href=\"#\">首页</a></li>\n");
					out.print("<li class='disabled'><a href=\"#\">上一页</a></li>\n");
				} else {
					out.print("<li><a href=\"javascript:jump('1')\">首页</a></li>\n");

					if (pageNo - listPageCount <= 0) {
						// TODO 快退
					} else {
						// TODO 快退
					}

					out.print("<li><a  href=\"javascript:jump('" + (pageNo - 1)
							+ "')\">上一页</a></li>\n");
				}
				if ("true".equals(showListNo)) {
					if (pageCount - pageNo < listPageCount) {

						for (int i = (pageCount - listPageCount) < 0 ? 1 : (pageCount
								- listPageCount + 1); i <= pageCount; i++) {
							String style = "active";
							if (pageNo == i) {
								style = nonceStyle;
								out.print("<li class=\"active\"> <a href=\"#\"> " + i + "</a></li>");
							} else {
								style = otherStyle;
								out.print("<li> <a href=\"javascript:jump('" + i + "')\" class=\""
										+ style + "\"  > " + i + " </a></li>");
							}
						}
					} else {
						int i = 1;
						if (pageNo <= listPageCount / 2) {
							i = 1;
						} else {

							i = pageNo - listPageCount / 2;
						}

						int k = 1;

						do {
							String style = "";
							if (pageNo == i) {
								style = nonceStyle;
								out.print(" <li class=\"active\"><a href=\"#\" class=\"" + style
										+ "\"  > <b> " + i + "</b>   </a></li> ");
							} else {
								style = otherStyle;
								out.print(" <li><a href=\"javascript:jump('" + i + "')\" class=\""
										+ style + "\"  >  " + i + "   </a></li> ");
							}

							i++;
							k++;
						} while (k <= listPageCount);
						out.print("<li>......</li>");
					}

				}

				if (pageNo == pageCount || pageCount == 0) {
					out.print("<li class='disabled'><a href=\"#\">下一页</a></li>\n");
					out.print("<li class='disabled'><a href=\"#\">尾页</a></li>\n");
				} else {
					out.print("<li><a href=\"javascript:jump('" + (pageNo + 1)
							+ "')\">下一页</a></li>\n");

					if (pageCount == 0 || (pageCount - pageNo) < listPageCount) {
						// TODO 快进

					} else {
						// TODO 快进

					}

					out.print("<li><a href=\"javascript:jump('" + pageCount + "')\">尾页</a> </li>\n");
				}

				out.print("<li>跳转到<input id=\"pn\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\"  onafterpaste=\"this.value=this.value.replace(/\\D/g,'')\" size=\"3\" name=\"pn\" type=\"text\"   value=\""
						+ pageNo
						+ "\"  />页 </li>"
						+ "<li> <a  href=\"javascript:jump(document.getElementById('pn').value,document.getElementById('pageSize_value').value)\"  >跳转</a> </li>");
				if ("true".equals(this.showReSize) || "TRUE".equals(this.showReSize)) {
					out.print("<li>共&nbsp;<font style=\"font-weight: bold;\" color='#428bca' size='2px;'>"
							+ count
							+ "</font>&nbsp;条记录&nbsp;&nbsp;&nbsp;&nbsp;每页"
							+ "<input size=\"3px;\" title=\"输入数字,回车设置每页显示条数!\" onkeyup=\"this.value=this.value.replace(/\\D/g,'')\"  onafterpaste=\"this.value=this.value.replace(/\\D/g,'')\" type=\"text\" id=\"pageSize_value\" "
							+ "value=\""
							+ pageSize
							+ "\" onkeydown=\"if(event.keyCode==13 && this.value.search(/[^\\d]/g)==-1)jump('"
							+ pageNo
							+ "',this.value);\"/>条 共&nbsp;<font style=\"font-weight: bold;\" color='#428bca' size='2px;'>"
							+ pageCount + "</font>&nbsp;页</li>\n");
				}
				out.print("<ul>");
				this.page = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return super.doStartTag();
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getShowReSize() {
		return showReSize;
	}

	public void setShowReSize(String showReSize) {
		this.showReSize = showReSize;
	}

	public String getOnlyOneShow() {
		return onlyOneShow;
	}

	public void setOnlyOneShow(String onlyOneShow) {
		this.onlyOneShow = onlyOneShow;
	}

	public String getUseModelDriven() {
		return useModelDriven;
	}

	public void setUseModelDriven(String useModelDriven) {
		this.useModelDriven = useModelDriven;
	}

	public String getMhFrom() {
		return mhFrom;
	}

	public void setMhFrom(String mhFrom) {
		this.mhFrom = mhFrom;
	}

	public String getShowListNo() {
		return showListNo;
	}

	public void setShowListNo(String showListNo) {
		this.showListNo = showListNo;
	}

	public int getListPageCount() {
		return listPageCount;
	}

	public void setListPageCount(int listPageCount) {
		this.listPageCount = listPageCount;
	}

	public String getOtherStyle() {
		return otherStyle;
	}

	public void setOtherStyle(String otherStyle) {
		this.otherStyle = otherStyle;
	}

	public String getNonceStyle() {
		return nonceStyle;
	}

	public void setNonceStyle(String nonceStyle) {
		this.nonceStyle = nonceStyle;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * @return 返回 page
	 */

	public BaseModel getPage() {
		return page;
	}

	/**
	 * @param 对page进行赋值
	 */

	public void setPage(BaseModel page) {
		this.page = page;
	}

}
