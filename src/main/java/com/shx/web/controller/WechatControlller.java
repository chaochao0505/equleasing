/**  
* <p>Title: WechatControlller.java</p>  
* <p>Description: </p>  
* @author koko 
* @date 2018年11月13日  
* @version 1.0  
*/ 
package com.shx.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.shx.web.utils.StringUtils;
import com.shx.web.utils.UPloadUtil;

/**  
* <p>Title: WechatControlller</p>  
* <p>Description: </p>  
* @author koko  
* @date 2018年11月13日  
*/
@RestController
@RequestMapping("/wechat")
public class WechatControlller {
	
	private Logger logger=LoggerFactory.getLogger(getClass());
	@GetMapping(value="/auth")
	public Map<String,Object> auth(HttpServletRequest request,String js_code,HttpSession session){
		Map<String,Object> result=new HashMap<>();
		if(!StringUtils.isEmpty(js_code)){
			try {
				String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wxce1c35a44cdb6ff4&secret=4892e3e876a771f4def7437206bf72b6&js_code="+js_code+"&grant_type=authorization_code";
				logger.info("preurl:----"+url);
				String json = UPloadUtil.loadJSON(url);
				logger.info("jsonurl:----"+url);
				Gson gson = new Gson();
				Map<String, Object> map = new HashMap<String, Object>();
				map =  JSONObject.parseObject(json, Map.class);
				logger.info("jsonmapperMap:----"+map);
				if(map.get("errcode") !=null){
					result.put("messsage", "链接错误");
					logger.error("链接错误");
					
					return result;
				} 
				Map<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("session_key", map.get("session_key").toString());
				resultMap.put("openid", map.get("openid").toString());
				result.put("code", 0);
				result.put("message","查询成功");
				result.put("data",resultMap);
				
				return result;
			} catch (Exception e) {
				logger.info(e.getMessage()+"====================");
				result.put("messsage", "失败");
				return result;
			}
		}
		result.put("messsage", "code值不能为空");
		return result;
	}
}
