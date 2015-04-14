package com.plate.common.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * @作者：Rainsponsor
 * @E-mail：xianzel@163.com
 * @时间：2014-9-30
 * @描述：
 */
public class StringUtils {
	
	/**
	 * 说明: 将字符串变量由null转换为""串
	 * @param 需要处理的字符串
	 * @return 处理后的字符串
	 */
	public static String nullToStr(String str) {
		if (str == null || "".equals(str))
			return null;
		return str.trim();
	}

	/**
	 * 判断字符串是否为空或null
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		if (str == null || "".equals(str)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 拼接数组成字符串
	 * @param array
	 * @param fix
	 * @return
	 */
	public static String arrayToString(String array[], String fix) {
		if (array == null) {
			return null;
		}
		String res = "";
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null) {
				if (res.length() > 0)
					res = res.concat(fix);
				res = res.concat(array[i]);
			}
		}
		return res;
	}

	/**
	 * <pre>
	 * For example:
	 * String strVal=&quot;This is a dog&quot;;
	 * String strResult=CTools.replace(strVal,&quot;dog&quot;,&quot;cat&quot;);
	 * The result: &quot;This is cat&quot;
	 * 
	 * &#064;param strSrc: the string for replacing.
	 * &#064;param strOld: the old string.
	 * &#064;param strNew: the new string.
	 * @return the replaced string and the untouched string.
	 * <pre>
	 */
	public static final String replace(String strSrc, String strOld,
			String strNew) {
		if (strSrc == null || strOld == null || strNew == null)
			return "";
		int i = 0;
		if ((i = strSrc.indexOf(strOld, i)) >= 0) {
			char[] arr_cSrc = strSrc.toCharArray();
			char[] arr_cNew = strNew.toCharArray();
			int intOldLen = strOld.length();
			StringBuffer buf = new StringBuffer(arr_cSrc.length);
			buf.append(arr_cSrc, 0, i).append(arr_cNew);
			i += intOldLen;
			int j = i;
			while ((i = strSrc.indexOf(strOld, i)) > 0) {
				buf.append(arr_cSrc, j, i - j).append(arr_cNew);
				i += intOldLen;
				j = i;
			}
			buf.append(arr_cSrc, j, arr_cSrc.length - j);
			return buf.toString();
		}
		return strSrc;
	}

	/**
	 * 在将数据存入数据库前转换 传入编码方式，返回想要的指定编码
	 * @param strVal 要转换的字符串
	 * @return 从“ISO8859_1”到“GBK”得到的字符串
	 */
	public static final String deCode(String strVal, String byteOld,
			String byteNew) {
		try {
			if (strVal == null) {
				return "";
			} else {
				strVal = strVal.trim();
				strVal = new String(strVal.getBytes(byteOld), byteNew);
				return strVal;
			}
		} catch (Exception exp) {
			return "";
		}
	}

	/**
	 * Replaces all instances of oldString with newString in line with the added
	 * feature that matches of newString in oldString ignore case.
	 * 
	 * @param line
	 *            the String to search to perform replacements on
	 * @param oldString
	 *            the String that should be replaced by newString
	 * @param newString
	 *            the String that will replace all instances of oldString
	 * @return a String will all instances of oldString replaced by newString
	 */
	public static final String replaceIgnoreCase(String line, String oldString,
			String newString) {
		if (line == null) {
			return null;
		}
		String lcLine = line.toLowerCase();
		String lcOldString = oldString.toLowerCase();
		int i = 0;
		if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while ((i = lcLine.indexOf(lcOldString, i)) > 0) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			return buf.toString();
		}
		return line;
	}

	/**
	 * 说明：对字符串进行分割
	 * @param line
	 * @param separator
	 * @return
	 */
	public static String[] separate(String line, String separator) {
		StringTokenizer tokenizer = new StringTokenizer(line, separator);
		String[] result = new String[tokenizer.countTokens()];
		int count = 0;
		while (tokenizer.hasMoreTokens()) {
			result[count++] = tokenizer.nextToken();
		}
		return result;
	}


	/**
	 * System.out.println(substring(10,"一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十"));
	 * 输出：一二三四五六七八九十... 你想要输入字符的是多少个之后的，默认是15个字 length 字符长度 name 字符的名字
	 */
	public static String substring(int length, String name) {
		if (name == null || name.equals("")) {
			return "";
		} else if (name.length() <= length) {
			return name;
		} else if (name.length() > length) {
			return name.substring(0, length) + "...";
		} else {
			return name.substring(0, 15);
		}
	}

	/**
	 * 获取字符串的字节长度
	 * @param s
	 * @return
	 */
	public static int getLengthByte(String str) {
		if (str == null || str.equals("")) {
			return 0;
		}
		byte[] bytes = null;
		try {
			bytes = str.getBytes("Unicode");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (bytes != null) {
			return bytes.length;
		}
		return 0;
	}

	/**
	 * 按字节截取字符串, 一个汉字为2个字节, 不截断汉字
	 * @param length
	 * @param s
	 * @return
	 */
	public static String substringByte(int length, String s) {
		if (s == null || s.equals("")) {
			return "";
		}
		byte[] bytes;
		String result = s;
		try {
			bytes = s.getBytes("Unicode");
			int n = 0; // 表示当前的字节数
			int i = 2; // 要截取的字节数，从第3个字节开始
			for (; i < bytes.length && n < length; i++) {
				// 奇数位置，如3、5、7等，为UCS2编码中两个字节的第二个字节
				if (i % 2 == 1) {
					n++; // 在UCS2第二个字节时n加1
				} else {
					// 当UCS2编码的第一个字节不等于0时，该UCS2字符为汉字，一个汉字算两个字节
					if (bytes[i] != 0) {
						n++;
					}
				}
			}
			// 如果i为奇数时，处理成偶数
			if (i % 2 == 1) {
				// 该UCS2字符是汉字时，去掉这个截一半的汉字
				if (bytes[i - 1] != 0)
					i = i - 1;
				// 该UCS2字符是字母或数字，则保留该字符
				else
					i = i + 1;
			}
			result = new String(bytes, 0, i, "Unicode");
			if (bytes.length > length) {
				result += "...";
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 转成人民币大写金额形式
	 * @author Behone Choi (mail@caibihong.cn)
	 * @param money 人民币金额
	 * @return 人民币大写形式
	 */
	public static String moneyUpper(String money) {
		String upperNumber = "零壹贰叁肆伍陆柒捌玖"; // 0-9所对应的所有大写汉字
		String upperPosition = "万仟佰拾亿仟佰拾万仟佰拾元角分"; // 数字位所对应的所有大写汉字
		String upperMoney = ""; // 人民币大写金额形式
		int nzero = 0; // 用来计算连续的零值是几个
		money = roundHalfUp(Double.valueOf(money), 2); // 将money取绝对值并四舍五入取2位小数
		if (Double.valueOf(money) == 0) {
			return "零元整";
		}
		String moneyStr = roundHalfUp(Double.valueOf(money) * 100, 0); // 将money乘100并转换成字符串形式
		int moneyLength = moneyStr.length(); // 找出最高位
		if (moneyLength > 15) {
			return "溢出";
		}
		// 取出对应位数的upperPosition的值。如：123.45,moneyLength为5所以upperPosition=佰拾元角分
		upperPosition = upperPosition.substring(15 - moneyLength);
		// 循环取出每一位需要转换的值
		for (int i = 0; i < moneyLength; i++) {
			String currUN = ""; // 当前数字的大写汉语
			String currUP = ""; // 当前数字位的大写汉字
			int temp = Integer.valueOf(moneyStr.substring(i, i + 1)); // 取出需转换的某一位的值
			// 当所取位数不为元、万、亿、万亿上的数字时
			if (i != (moneyLength - 3) && i != (moneyLength - 7)
					&& i != (moneyLength - 11) && i != (moneyLength - 15)) {
				if (temp == 0) {
					nzero = nzero + 1;
				} else {
					if (temp != 0 && nzero != 0) {
						currUN = "零"
								+ upperNumber.substring(Integer.valueOf(temp),
										Integer.valueOf(temp) + 1);
						currUP = upperPosition.substring(i, i + 1);
						nzero = 0;
					} else {
						currUN = upperNumber.substring(Integer.valueOf(temp),
								Integer.valueOf(temp) + 1);
						currUP = upperPosition.substring(i, i + 1);
						nzero = 0;
					}
				}
			} else { // 该位是万亿，亿，万，元位等关键位
				if (temp != 0 && nzero != 0) {
					currUN = "零"
							+ upperNumber.substring(Integer.valueOf(temp),
									Integer.valueOf(temp) + 1);
					currUP = upperPosition.substring(i, i + 1);
					nzero = 0;
				} else {
					if (temp != 0 && nzero == 0) {
						currUN = upperNumber.substring(Integer.valueOf(temp),
								Integer.valueOf(temp) + 1);
						currUP = upperPosition.substring(i, i + 1);
						nzero = 0;
					} else {
						if (temp == 0 && nzero >= 3) {
							currUN = "";
							currUP = "";
							nzero = nzero + 1;
						} else {
							if (moneyLength >= 11) {
								currUN = "";
								nzero = nzero + 1;
							} else {
								currUN = "";
								currUP = upperPosition.substring(i, i + 1);
								nzero = nzero + 1;
							}
						}
					}
				}
			}
			if (i == (moneyLength - 11) || i == (moneyLength - 3)) { // 如果该位是亿位或元位，则必须写上
				currUP = upperPosition.substring(i, i + 1);
			}
			upperMoney = upperMoney + currUN + currUP;

			if (i == moneyLength - 1 && temp == 0) { // 最后一位（分）为0时，加上“整”
				upperMoney = upperMoney + "整";
			}
		}
		return upperMoney;
	}

	/**
	 * 精确小数点位数四舍五入
	 * @param num   小数
	 * @param scale 小数点后位数
	 * @return 保留小数点后scale位四舍五入的数值字符串
	 */
	public static String roundHalfUp(double num, int scale) {
		return String.valueOf(new BigDecimal(num).setScale(scale,
				BigDecimal.ROUND_HALF_UP));
	}

	/**
	 * 用正则表达式判断字符串是否整数或小数
	 * @param str 待检测字符串
	 * @return 布尔型
	 */
	public static boolean isNumeric(String str) {
		if (str == null)
			return false;
		String regex = "[0-9]+(\\.[0-9]+)?";
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(str).matches();
	}

	/**
	 * 用正则表达式判断字符串是否整型
	 * @param str 待检测字符串
	 * @return 布尔型
	 */
	public static boolean isInt(String str) {
		if (str == null)
			return false;
		String regex = "[0-9]+";
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(str).matches();
	}

	/**
	 * 用正则表达式判断字符串是否浮点型
	 * @param str 待检测字符串
	 * @return 布尔型
	 */
	public static boolean isFloat(String str) {
		if (str == null)
			return false;
		String regex = "[0-9]+\\.[0-9]+";
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(str).matches();
	}

	/**
	 * 所有非空类型转换均采用noNull函数 noNullStr、noNullDouble弃用
	 * @param str
	 * @return
	 */
	public static String noNull(Object object) {
		if (object == null) {
			return "";
		}
		return object.toString().trim();
	}

	/**
	 * 所有非空类型转换均采用noNull函数 noNullStr、noNullDouble弃用
	 * @param str
	 * @return
	 */
	public static String noNull(String str) {
		if (str == null || str.equalsIgnoreCase("null")) {
			return "";
		}
		return str.trim();
	}

	public static Double noNull(Double data) {
		if (data == null) {
			return 0d;
		}
		return data;
	}

	public static Integer noNull(Integer data) {
		if (data == null) {
			return 0;
		}
		return data;

	}

	public static Float noNull(Float data) {
		if (data == null) {
			return 0f;
		}
		return data;

	}
}
