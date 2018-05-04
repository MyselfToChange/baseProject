package com.gcx.api.common.util;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *<p>Title:StringUtils</p>
 *<p>Description:字符处理类</p>
 *<p>Company:gcx</p>
 *<p>Author:zhanglin</p>
 *<p>Date:2017年7月5日</p>
 */
public class StringUtils {

	public static boolean isEmpty(String str) {
		return null == str||str.equals("null") || "".equals(str.trim().length());
	}

	public static String isEmptyR(Object object) {
		String var = String.valueOf(object);
		if (null == var || var.equals("") || var.equals("null")) {
			return "0";
		}
		return var;
	}	
	
	public static String toTrim(String strtrim) {
		if (null != strtrim && !strtrim.equals("")) {
			return strtrim.trim();
		}
		return "";
	}

	
	public static String getRandomStr(int n) {
		Random random = new Random();
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < n; i++) {
			str.append(random.nextInt(10));
		}
		return str.toString();
	}

	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	public static String replaceEnter(String str) {
		if (str == null)
			return null;
		return str.replaceAll("\r", "").replaceAll("\n", "");
	}

	/**
	 * 字符转换
	 * 
	 * @param str
	 * @return
	 */
	public static String toUTF8(String str) {
		if (str == null || str.equals("")) {
			return "";
		}
		try {
			return new String(str.getBytes("ISO8859-1"), "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}

	public static String to(String str, String charset) {
		if (str == null || str.equals("")) {
			return "";
		}
		try {
			return new String(str.getBytes("ISO8859-1"), charset);
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}

	/**
	 * 去除HTML 元素
	 * 
	 * @param element
	 * @return
	 */
	public static String getTxtWithoutHTMLElement(String element) {
		if (null == element) {
			return element;
		}
		Pattern pattern = Pattern.compile("<[^<|^>]*>");
		Matcher matcher = pattern.matcher(element);
		StringBuffer txt = new StringBuffer();
		while (matcher.find()) {
			String group = matcher.group();
			if (group.matches("<[\\s]*>")) {
				matcher.appendReplacement(txt, group);
			} else {
				matcher.appendReplacement(txt, "");
			}
		}
		matcher.appendTail(txt);
		String temp = txt.toString().replaceAll("[\r|\n]", "");
		// 多个连续空格替换为一个空格
		temp = temp.replaceAll("\\s+", " ");
		return temp;
	}
	

	/**
	 * 将形如1,2，3,,，5 ==>1,2,3,5
	 * @param content
	 * @param split
	 * @return
	 */
	public static String handleSplit(String content, String split) {
		if(StringUtils.isEmpty(content) || StringUtils.isEmpty(split))
			return content;
		content = content.replaceAll("\\s+", "");
		if(",".equals(split))
			content = content.replaceAll("，", ",");
		if(content.indexOf(split) == -1) {
			return content;
		}
		String[] temp =content.split(split);
		if(temp == null || temp.length == 0)
			return "";
		int bufSize = temp.length;
		bufSize *= temp[0].toString().length() + split.length();
		StringBuilder buf = new StringBuilder(bufSize);
		for(int i=0; i<temp.length; i++){
			if(!StringUtils.isEmpty(temp[i])) {
				if(i > 0)
					buf.append(split);
				if(!StringUtils.isEmpty(temp[i])) 
					buf.append(temp[i]);
			}
		}
		return buf.toString(); 
	}
	
	/**
	 * 缩略字符串（不区分中英文字符）
	 * @param str 目标字符串
	 * @param length 截取长度
	 * @return
	 */
/*	public static String abbr(String str, int length) {
		if (str == null) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength <= length - 3) {
					sb.append(c);
				} else {
					sb.append("...");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}*/
	
	/**
	 * 替换掉HTML标签方法
	 */
	public static String replaceHtml(String html) {
		if (isEmpty(html)){
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}
	
	/**
	 * 缩略字符串（替换html）
	 * @param str 目标字符串
	 * @param length 截取长度
	 * @return
	 */
/*	public static String rabbr(String str, int length) {
        return abbr(replaceHtml(str), length);
	}*/
	
	public static String safeMobile(String mobile) {
		if(StringUtils.isEmpty(mobile)) {
			return "";
		}
		return mobile.substring(0,mobile.length()-(mobile.substring(3)).length())+"****"+mobile.substring(7);
	}
	
	public static String safeEmail(String email) {
		if(StringUtils.isEmpty(email)) {
			return "";
		}
		return email.replaceAll("(.{2}).+(.{1}@.+)", "$1***$2");
	}

	/**
	 * 
	 * numberToChinese:(“121”，返回“一百二十一”.). <br/> 
	 * @Title: numberToChinese
	 * @param: @param number
	 * @param: @return    设定文件
	 * @return: String    返回类型
	 * @author: cjb
	 * @date: 2016年11月16日 下午7:48:01
	 */
	public static String numberToChinese(String number) {
		String[] s1 = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
		String[] s2 = { "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千" };
		String result = "";
		int n = number.length();
		for (int i = 0; i < n; i++) {
			int num = number.charAt(i) - '0';
			if (i != n - 1 && num != 0) {
				result += s1[num] + s2[n - 2 - i];
			} else {
				result += s1[num];
			}
		}
		return result;
	}
	
	
	//判断字符串为字母或者数字或者即包括数字有包括字母
	public static boolean judgeContainsStr(String cardNum) {  
		String regex=".*[a-z0-9A-Z]+.*";  
	        Matcher m=Pattern.compile(regex).matcher(cardNum);  
	         return m.matches();  
    }  
	//判断是否包括汉字
	public static boolean isContainChinese(String str) {
	        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
	        Matcher m = p.matcher(str);
	        if (m.find()) {
	            return true;
	        }
	        return false;
	    }
	
	//double型数字页面保留两位小数
	public static String numberTo(Double str) {
		DecimalFormat df=new DecimalFormat("######0.00");  
        return df.format(str);
    }
	
	//差率小于0的颜色变红
	public static String numberRed(Double str) {
		DecimalFormat df=new DecimalFormat("######0.00");
		String strs;
		strs = df.format(str);
		String returnVal = "";
		if(str < 0){
			returnVal = "<font color='red'>"+strs+"%</font>";
		}else{
			returnVal = strs+"%";
		}
		return  returnVal;
    }

}