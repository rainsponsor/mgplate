package com.plate.baiyue.common;
/**
 * @作者:Rainsponsor
 * @E-mail:xianzel@163.com
 * @时间: 2015-2-10
 * @描述:解析HTML内容工具类
 */
public class ParseHtmlUtil {
	
	/**
	 * 将UEditor提交的内容中所有的<p>...</p>全部剔除
	 * @param content
	 * @return
	 */
	public static String parseHtmlFormatParagraph(String html){
		if(html!=null){
			html = html.replaceAll("<p>", "");
			html = html.replaceAll("</p>", "<br/>");
			html = "<p>"+html.substring(0, html.length()-5)+"</p>";
		}
		return html;
	}
	
}
