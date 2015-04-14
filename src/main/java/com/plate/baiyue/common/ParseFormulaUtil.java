package com.plate.baiyue.common;
/**
 * @作者:Rainsponsor
 * @E-mail:xianzel@163.com
 * @时间: 2015-2-10
 * @描述:解析公式工具类
 */
public class ParseFormulaUtil {
	
	/**
	 * 将base64图片描述中获取Latex格式描述
	 * 说明：此功能只针对百度UEditor集成KfFormula插件使用
	 * @param content
	 * @return
	 */
	public static StringBuilder parseLatexByBase64ImgDataType(StringBuilder content){
		if(content!=null){
			int flag = content.indexOf("<img class=\"kfformula\"");
			if(flag!=-1){
				int startIndex = content.indexOf("<img class=\"kfformula\"");
				int endIndex = content.indexOf("/>", startIndex);
				String imgHtml = content.subSequence(startIndex, endIndex+2).toString();
				int x = imgHtml.indexOf("data-latex=\"");
				int y = imgHtml.indexOf("\"", x+12);
				imgHtml = imgHtml.substring(x+12, y);
				content.replace(startIndex, endIndex+2, "\\("+imgHtml+"\\)");
				parseLatexByBase64ImgDataType(content);
			}
		}
		return content;
	}
	
}
