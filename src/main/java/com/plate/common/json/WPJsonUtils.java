package com.plate.common.json;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-9-30
 * @描述：系统平台中JSON处理工具类
 */
@SuppressWarnings("unchecked")
public class WPJsonUtils {
	
	/**
	 * 将JSON格式的字符串转换为Map，如: {name:Daniel} -> key:name value:Daniel
	 * @param jsonStr
	 * @return
	 */
	public static Map<String,Object> parseJSONStrToMap(String jsonStr){
		Map<String,Object> map = null;
		try {
			map = new ObjectMapper().readValue(jsonStr, Map.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 将JSON数组格式的字符串转换为Map，如: [{name:Daniel},{name:Jack}] -> List [{key:name value:Daniel},{key:name value:Jack}]
	 * @param jsonArrayStr
	 * @return
	 */
	public static List<Map<String,Object>> parseJSONStrToList(String jsonArrayStr){
		List<Map<String,Object>> list = null;
		try {
			list = new ObjectMapper().readValue(jsonArrayStr, List.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
