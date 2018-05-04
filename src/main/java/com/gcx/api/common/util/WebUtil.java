package com.gcx.api.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcx.api.common.spring.SpringContextHolder;





public class WebUtil {

	private static final String PROJECT_FILE = "project";

	/**
	 * 获取服务器IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 得到WEB-INF的绝对路�?
	 * 
	 * @return
	 */
	public static String getWebInfPath() {
		String filePath = Thread.currentThread().getContextClassLoader()
				.getResource("").toString();
		if (filePath.toLowerCase().indexOf("file:") > -1) {
			filePath = filePath.substring(6, filePath.length());
		}
		if (filePath.toLowerCase().indexOf("classes") > -1) {
			filePath = filePath.replaceAll("/classes", "");
		}
		if (System.getProperty("os.name").toLowerCase().indexOf("window") < 0) {
			filePath = "/" + filePath;
		}
		if (!filePath.endsWith("/"))
			filePath += "/";
		return filePath;
	}

	/**
	 * 得到根目录绝对路�?(不包含WEB-INF)
	 * 
	 * @return
	 */
	public static String getRootPath() {
		String filePath = Thread.currentThread().getContextClassLoader()
				.getResource("").toString();
		if (filePath.toLowerCase().indexOf("file:") > -1) {
			filePath = filePath.substring(6, filePath.length());
		}
		if (filePath.toLowerCase().indexOf("classes") > -1) {
			filePath = filePath.replaceAll("/classes", "");
		}
		if (filePath.toLowerCase().indexOf("web-inf") > -1) {
			filePath = filePath.substring(0, filePath.length() - 9);
		}
		if (System.getProperty("os.name").toLowerCase().indexOf("window") < 0) {
			filePath = "/" + filePath;
		}

		if (filePath.endsWith("/"))
			filePath = filePath.substring(0, filePath.length() - 1);

		return filePath;
	}

	public static String getRootPath(String resource) {
		String filePath = Thread.currentThread().getContextClassLoader()
				.getResource(resource).toString();
		if (filePath.toLowerCase().indexOf("file:") > -1) {
			filePath = filePath.substring(6, filePath.length());
		}
		if (filePath.toLowerCase().indexOf("classes") > -1) {
			filePath = filePath.replaceAll("/classes", "");
		}
		if (filePath.toLowerCase().indexOf("web-inf") > -1) {
			filePath = filePath.substring(0, filePath.length() - 9);
		}
		if (System.getProperty("os.name").toLowerCase().indexOf("window") < 0) {
			filePath = "/" + filePath;
		}

		if (!filePath.endsWith("/"))
			filePath += "/";

		return filePath;
	}

	/**
	 * 符合白名单的允许，其它拒绝
	 * 
	 * @param ip1
	 *            客户的ip
	 * @param ip2
	 *            数据库中的白名单
	 * @return
	 */
	public static boolean checkIp(String ip1, String ip2) {
		boolean boo = false;
		if (null == ip2 || "".equals(ip2)) {
			return true;
		}
		boolean isOrderIpRule = ip1
				.matches("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
		if (!isOrderIpRule) {
			return boo;
		}
		String ipArray[] = ip2.split(",");
		for (int i = 0; i < ipArray.length; i++) {
			String ipArr = ipArray[i];
			String ip = "";
			if (ipArr.contains("*")) {// 如格式为192.168.1.*判断
				ip = ipArr.substring(0, ipArr.lastIndexOf("."));
				boo = ip1.substring(0, ip1.lastIndexOf(".")).equals(ip);
				if (boo) {
					return boo;
				}
			} else if (ipArr.contains("-")) {// 如格式为192.168.1.1-155判断
				ip = ipArr.substring(ipArr.lastIndexOf(".") + 1);
				String ipayArray[] = ip.split("-");
				String ips = ip1.substring(ip1.lastIndexOf(".") + 1);
				if (Integer.parseInt(ipayArray[0]) <= Integer.parseInt(ips)
						&& Integer.parseInt(ips) <= Integer
								.parseInt(ipayArray[1])) {
					return true;
				}
			} else { // 如格式为192.168.1.1判断
				boo = ip1.equals(ipArr);
				if (boo) {
					return boo;
				}
			}
		}
		return boo;
	}

	public static Cookie cookie(String key, String value) {
		return cookie(key, value, -1);
	}

	public static Cookie cookie(String key, String value, int validy) {
		if (key != null && value != null) {
			Cookie cookie = new Cookie(key, value);
			cookie.setMaxAge(validy);
			cookie.setPath("/");
			cookie.setDomain(PropertiesReader.getValue(PROJECT_FILE,
					CommonValue.DOMAIN_NAME));
			return cookie;
		} else {
			return null;
		}
	}

	public static Cookie cookie(String key, String value, int validy,
			String domainName) {
		if (key != null && value != null) {
			Cookie cookie = new Cookie(key, value);
			cookie.setMaxAge(validy);
			cookie.setPath("/");
			if (domainName != null) {
				cookie.setDomain(domainName);
			}
			return cookie;
		} else {
			return null;
		}
	}

	/**
	 * 根据Key 值来获得移除的 Cookie
	 * 
	 * @param key
	 *            Add by zx.liu
	 */
	public static Cookie removeableCookie(String key) {
		if (key != null) {
			Cookie cookie = new Cookie(key, "");
			cookie.setMaxAge(-1);
			cookie.setPath("/");
			cookie.setDomain(".qianpin.com");
			return cookie;
		} else {
			return null;
		}
	}

	public static Cookie removeableCookie(String key, String domainName) {
		if (key != null) {
			Cookie cookie = new Cookie(key, "");
			cookie.setMaxAge(0);
			cookie.setPath("/");
			if (domainName != null)
				cookie.setDomain(domainName);
			return cookie;
		} else {
			return null;
		}
	}

	public static boolean areEquals(Cookie cookie1, Cookie cookie2) {
		return cookie1.getName().equals(cookie2.getName())
				&& (cookie1.getValue() != null ? cookie1.getValue().equals(
						cookie2.getValue()) : cookie2.getValue() == null)
				&& (cookie1.getMaxAge() == cookie2.getMaxAge()
						&& cookie1.getSecure() == cookie2.getSecure() && cookie1
						.getVersion() == cookie2.getVersion())
				&& (cookie1.getComment() != null ? cookie1.getComment().equals(
						cookie2.getComment()) : cookie2.getComment() == null)
				&& (cookie1.getDomain() != null ? cookie1.getDomain().equals(
						cookie2.getDomain()) : cookie2.getDomain() == null)
				&& (cookie1.getPath() != null ? cookie1.getPath().equals(
						cookie2.getPath()) : cookie2.getPath() == null);
	}

	public static final String getCookieValue(String key, HttpServletRequest req) {
		String str = null;
		Cookie cookies[] = req.getCookies();
		if (null == cookies)
			return null;
		for (int i = 0; i < cookies.length && str == null; i++) {
			Cookie cookie = cookies[i];
			if (cookie.getName().equals(key))
				return cookie.getValue();
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public static String parseQueryString(HttpServletRequest request) {
		Map map = request.getParameterMap();
		String result = "";
		for (Object o : map.keySet()) {
			String key = o + "";
			String[] values = (String[]) map.get(key);
			result += key + "=" + values[0] + "&";
		}
		if (result.length() > 0) {
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	public static String getRequestPath(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder(request.getRequestURI());
		Enumeration enumeration = request.getParameterNames();
		if (enumeration.hasMoreElements()) {
			sb.append("?");
		}
		while (enumeration.hasMoreElements()) {
			Object object = enumeration.nextElement();
			sb.append(object);
			sb.append("=");
			sb.append(request.getParameter(object.toString()));
			sb.append("&");
		}
		String requesturi = "";
		String contextPath = request.getContextPath();
		if (sb.indexOf("&") != -1) {
			requesturi = sb.substring(0, sb.lastIndexOf("&"));
		} else {
			requesturi = sb.toString();
		}
		requesturi = requesturi.substring(requesturi.indexOf(contextPath)
				+ contextPath.length());
		return requesturi;
	}

	/**
	 * 记录手机wap端的cookie
	 * 
	 * @param response
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @param validy
	 *            时间
	 */
	public static void setMCookieByKey(HttpServletResponse response,
			String key, String value, int validy) {
		Cookie cookieObj;
		Cookie cookieObjLocal;
		cookieObj = cookie(key, value, validy);
		response.addCookie(cookieObj);
		cookieObjLocal = cookie(key, value, validy, null);
		response.addCookie(cookieObjLocal);
	}

	/**
	 * 清除手机wap端cookie，包括清除域名cookie以及本地cookie
	 * 
	 * @param response
	 * @param key
	 *            cookie键值
	 */
	public static void removeMCookieByKey(HttpServletResponse response,
			String key) {
		Cookie cookieObj;
		Cookie cookieObjLocal;
		cookieObj = removeableCookie(key);
		response.addCookie(cookieObj);
		cookieObjLocal = removeableCookie(key, null);
		response.addCookie(cookieObjLocal);
	}
	
	/**
	 * 判断是否ajax请求
	 * @param request
	 * @return
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		boolean isAjax = false;
		String header = request.getHeader("x-requested-with");
		if(!StringUtils.isEmpty(header) && "XMLHttpRequest".equalsIgnoreCase(header)) {
			isAjax = true;
		}
		return isAjax;
	}
	
	/**
	 * 设置 Cookie（生成时间为1天）
	 * @param name 名称
	 * @param value 值
	 */
	public static void setCookie(HttpServletResponse response, String name, String value) {
		setCookie(response, name, value, 60*60*24);
	}
	
	/**
	 * 设置 Cookie
	 * @param name 名称
	 * @param value 值
	 * @param maxAge 生存时间（单位秒）
	 */
	public static void setCookie(HttpServletResponse response, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, null);
        if(!StringUtils.isEmpty(SpringContextHolder.getApplicationContext().getApplicationName())){
            cookie.setPath(SpringContextHolder.getApplicationContext().getApplicationName());
        }else{
            cookie.setPath("/");
        }
		cookie.setMaxAge(maxAge);
		try {
			cookie.setValue(URLEncoder.encode(value, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.addCookie(cookie);
	}
	/**
	 * 获得指定Cookie的值
	 * @param name 名称
	 * @return 值
	 */
	public static String getCookie(HttpServletRequest request, String name) {
		return getCookie(request, null, name, false);
	}
	/**
	 * 获得指定Cookie的值，并删除。
	 * @param name 名称
	 * @return 值
	 */
	public static String getCookie(HttpServletRequest request, HttpServletResponse response, String name) {
		return getCookie(request, response, name, true);
	}
	/**
	 * 获得指定Cookie的值
	 * @param request 请求对象
	 * @param response 响应对象
	 * @param name 名字
	 * @param isRemove 是否移除
	 * @return 值
	 */
	public static String getCookie(HttpServletRequest request, HttpServletResponse response, String name, boolean isRemove) {
		String value = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					try {
						value = URLDecoder.decode(cookie.getValue(), "utf-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					if (isRemove) {
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}
				}
			}
		}
		return value;
	}
	
	
}