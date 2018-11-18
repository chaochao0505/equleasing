/**  
* <p>Title: ExceptionUtil.java</p>  
* <p>Description: </p>  
* @author koko 
* @date 2018年11月13日  
* @version 1.0  
*/ 
package com.shx.web.utils;

/**  
* <p>Title: ExceptionUtil</p>  
* <p>Description: </p>  
* @author koko  
* @date 2018年11月13日  
*/
public class ExceptionUtil {
	/**
	 * 获得异常描述
	 * @return
	 */
	public static String getExceptionDescribe(Exception e){
		StringBuffer sb = new StringBuffer();
		sb.append(e.toString()).append("\n");
		StackTraceElement[] elementArray = e.getStackTrace();
		for(StackTraceElement ele :elementArray){
			sb.append(ele).append("\n");
		}
		return sb.toString();
	}
	/**
	 * 获得异常描述
	 * @return
	 */
	public static String getExceptionDescribe(Throwable e){
		StringBuffer sb = new StringBuffer();
		sb.append(e.toString()).append("\n");
		StackTraceElement[] elementArray = e.getStackTrace();
		for(StackTraceElement ele :elementArray){
			sb.append(ele).append("\n");
		}
		return sb.toString();
	}
}
