package com.plate.ueditor.upload;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plate.common.spring.SpringContextHolder;

/**
 * @作者:Rainsponsor
 * @E-mail:xianzel@163.com
 * @时间: 2015-3-27
 * @描述: UEditor控件自定义Ajax控制类
 */
@Scope("prototype")
@Controller
@RequestMapping("/ue/ajax")
public class AjaxController {
	
	/**
	 * 根据图片路径删除服务器上的图片文件（无数据库数据操作）
	 * @param imgUrl
	 * @return
	 */
	@RequestMapping("/delimgnonedb")
	@ResponseBody
	public Integer ajaxDelImageNoneDB(HttpServletRequest request,String imgUrl){
		String rootRealPath = SpringContextHolder.getRootRealPath();
		File file = new File(rootRealPath+imgUrl);
		try {
			if(file.exists()){
				file.delete();
			}
			return 1;
		} catch (Exception e) {
			return 0;
		}
		
	}
	
	
	
}
