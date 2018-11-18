/**  
* <p>Title: WechatPay.java</p>  
* <p>Description: </p>  
* @author koko 
* @date 2018年11月13日  
* @version 1.0  
*/ 
package com.shx.web.pay;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jdom.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shx.web.config.WeixinConfig;
import com.shx.web.utils.HtmlUtil;
import com.shx.web.utils.HttpUtil;
import com.shx.web.utils.MD5Util;
import com.shx.web.utils.Sha1Util;
import com.shx.web.utils.XMLUtil;
/**  
* <p>Title: WechatPay</p>  
* <p>Description:微信支付 </p>  
* @author koko  
* @date 2018年11月13日  
*/
public class WechatPay {
	private Logger log=LoggerFactory.getLogger(getClass());
	
    /**
     * 微信-JSAPI支付
     * @param indentNum  订单号
     * @param money      订单金额
     * @param openid     支付者唯一标识
     * @param body 		   支付信息
     * @param notify_url  回调地址
     * @return   返回支付参数集合
     * @throws UnsupportedEncodingException
     */
	 public Map<String, String> wechatPayJSAPI(String indentNum,BigDecimal money,String openid,String body,String notify_url,
	    		String html_appid,String partner_id,String html_key,String type) throws UnsupportedEncodingException  {
	    	//测试参数
	    	log.info("-----------------进入微信支付wechatPayJSAPI1--"+indentNum+"--"+money+"--"+openid+"--"+body+"--"+notify_url+"-------------");
	    	
	    	BigDecimal a = new BigDecimal(100);
	    	log.info("-----------------进入微信支付wechatPayJSAPI2--"+indentNum+"--"+money+"--"+openid+"--"+body+"--"+notify_url+"-------------");
		  	String totalCount = money.multiply(a).setScale(0,BigDecimal.ROUND_DOWN).toString();
		  	log.info("-----------------进入微信支付wechatPayJSAPI3--"+indentNum+"--"+money+"--"+openid+"--"+body+"--"+notify_url+"-------------");
	    	//支付信息转换
	    	//body = new String(body.getBytes("ISO-8859-1"), "UTF-8");
	    	log.info("-----------支付信息body----------"+body+"-----"+totalCount+"--------------");
	    	//存入实体
	    	WeixinBean weixinBean = new WeixinBean(html_appid, partner_id, html_key,type , openid,null, 
	    										body, indentNum, totalCount, notify_url);
	    	log.info("-------------------回调地址："+notify_url);
	    	//接收预支付订单回调参数,xml格式
	    	Map<String,String> xml= doInBackground(weixinBean);
	    	
	    	log.info("-----------------进入微信支付wechatPayJSAPI，返回XML----"+xml+"-------------------");
	    	//生成微信预支付订单返回成功
	    	Map<String, String> payMap = new HashMap<String, String>();
		    if(xml.get("return_code").equals("SUCCESS")){   
		    	String	nonceStr = genNonceStr();    //随机字符串
		        String date = Sha1Util.getTimeStamp();    //时间截
		        //将返回的参数进行第二次签名然后返回给客户端    	
		        List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
		        packageParams.add(new BasicNameValuePair("appId", html_appid)); //商户 APPID
	 	        packageParams.add(new BasicNameValuePair("nonceStr",nonceStr)); //随机字符串
		        packageParams.add(new BasicNameValuePair("package", "prepay_id="+xml.get("prepay_id"))); //生成的微信预支付信息ID        
		        packageParams.add(new BasicNameValuePair("signType","MD5"));
		        packageParams.add(new BasicNameValuePair("timeStamp",date)); //时间截
		        String sign = genPackageSign(packageParams,html_key);  //第二次签名
		        
		        //放入map集合
		        payMap.put("sign", sign);
		        payMap.put("nonceStr", nonceStr);
		        payMap.put("timestamp", date);
		        payMap.put("package", "prepay_id="+xml.get("prepay_id"));
		        payMap.put("appid", html_appid);
		        payMap.put("signType","MD5");
		        //System.out.println(xml.get("prepay_id"));
		    }else{
		    	String str=new String(xml.get("return_msg").toString().getBytes("ISO-8859-1"),"UTF-8");
		    	System.out.println("0000000"+str);
		    	 payMap.put("error", str);
		    }
		    log.info("-----------------进入微信支付wechatPayJSAPI，结束-----------------------");
			return payMap;
	    }
	    
	    /**
	     * 发送生成预支付订单请求
	     * @param money      支付金额
	     * @param indentNum  订单号
	     * @param openid  	  支付者唯一标识
	     * @param request    
	     * @return   
	     */
	  	@SuppressWarnings("unchecked")
		protected  Map<String, String>  doInBackground(WeixinBean weixinBean) {
	          String entity = getproduct(weixinBean);
	          String buf =  HtmlUtil.postData(WeixinConfig.service_url,entity);
	          Map<String,String> xml=null;
	          try {
	        	  String content = new String(buf.getBytes(), "UTF-8");
				  xml=XMLUtil.doXMLParse(content);
	  		  }catch (UnsupportedEncodingException e1) {
	  			// TODO Auto-generated catch block
	  			e1.printStackTrace();
	  		  } catch (JDOMException e) {
	  			// TODO Auto-generated catch block 
	  			 e.printStackTrace();
	  		  }catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			  }
	          return xml;
	      }
	  	
	  	 /**
	  	   * 封装产品信息
	  	   * @param weixinBean   微信支付参数实体
	  	   * @return   返回支付参数封装类(map类型)
	  	   */
		  public String getproduct(WeixinBean weixinBean){
		       List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
		       //APP_ID 应用从官方网站申请到的合法appId 
		       packageParams.add(new BasicNameValuePair("appid",weixinBean.getAppid()));          
		       //packageParams.add(new BasicNameValuePair("attach",inputMoney));
		       //body为汉字是要转成UTF-8
		       packageParams.add(new BasicNameValuePair("body",weixinBean.getBody()));
		       //商户号
		       packageParams.add(new BasicNameValuePair("mch_id", weixinBean.getMch_id()));
		       //验证参数
		       packageParams.add(new BasicNameValuePair("nonce_str", genNonceStr()));
		       //回调地址
		       packageParams.add(new BasicNameValuePair("notify_url",weixinBean.getNotify_url()));
		       //支付者唯一标示
		       if("JSAPI".equals(weixinBean.getTrade_type()) && weixinBean.getOpenid() !=null){  //JSAPI支付使用
		    	   packageParams.add(new BasicNameValuePair("openid", weixinBean.getOpenid()));
		       }
		       //订单号码
		       packageParams.add(new BasicNameValuePair("out_trade_no",weixinBean.getOut_trade_no()));
		       //服务器地址
		       packageParams.add(new BasicNameValuePair("spbill_create_ip",getLocalIP()));
		       //支付金额
		       packageParams.add(new BasicNameValuePair("total_fee", weixinBean.getTotal_fee()));
		       //支付类型    jsapi
		       packageParams.add(new BasicNameValuePair("trade_type", weixinBean.getTrade_type()));
		       //签名
		       String sign = genPackageSign(packageParams,weixinBean.getKey());
		       packageParams.add(new BasicNameValuePair("sign", sign));
		       String xmlstring =toXml(packageParams);    //将请求参数转换为xml格式
		       return xmlstring;
		  }
		  
		    /**
		     * 将请求参数转换为xml格式
		     * @param params 参数list
		     * @return xml字符串
		     */
		    public String toXml(List<NameValuePair> params) {
		        StringBuilder sb = new StringBuilder();
		        sb.append("<xml>");
		        for (int i = 0; i < params.size(); i++) {
		            sb.append("<"+params.get(i).getName()+">");
		            sb.append(params.get(i).getValue());
		            sb.append("</"+params.get(i).getName()+">");
		        }
		        sb.append("</xml>");
		        return sb.toString();
		    }
		  
	    //获得服务器ip地址
	    public static String getLocalIP() {   
	    	InetAddress addr = null;   
	        try {
				addr = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}         
	        byte[] ipAddr = addr.getAddress();   
	        String ipAddrStr = "";   
	        for (int i = 0; i < ipAddr.length; i++) {   
	            if (i > 0) {   
	                ipAddrStr += ".";   
	            }   
	            ipAddrStr += ipAddr[i] & 0xFF;   
	        }   
		    //System.out.println(ipAddrStr);   
	        System.out.println("微信支付终端Ip:"+ipAddrStr);
			return ipAddrStr;   
		} 
	  	
	    /**
	     * 查询订单支付信息
	     * @param transaction_id   //微信交易单号
	     * @param type	支付类型      APP,JSAPI,NATIVE
	     * @return
	     */
	    public  Map<String, String> getSelectOrder(String transaction_id,String type){              
	    	 String WX_APP_ID = null;        //appid 
	    	 String WX_PARTNER_ID = null;    //商户id
	    	 String key = null;              //秘钥
	    	 if("APP".equals(type)){   //判断支付方式 
	    		 WX_APP_ID = WeixinConfig.appid;
	    		 WX_PARTNER_ID = WeixinConfig.mch_id;
	    		 key = WeixinConfig.key;
	    	 }else{  //公众号   JSAPI支付,NATIVE(二维码支付)
	    		 WX_APP_ID = WeixinConfig.html_appid;
	    		 WX_PARTNER_ID = WeixinConfig.partner_id;
	    		 key = WeixinConfig.html_key;
	    	 }
	         List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
	         packageParams.add(new BasicNameValuePair("appid",WX_APP_ID));
	         packageParams.add(new BasicNameValuePair("mch_id", WX_PARTNER_ID));
	         packageParams.add(new BasicNameValuePair("nonce_str", genNonceStr()));
	         packageParams.add(new BasicNameValuePair("transaction_id",transaction_id));
	         String sign = genPackageSign(packageParams,key);
	         packageParams.add(new BasicNameValuePair("sign", sign));
	         String xmlstring =toXml(packageParams);
	         try {
	        	 xmlstring= new String(xmlstring.toString().getBytes(), "ISO8859-1");
	    	} catch (UnsupportedEncodingException e) {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	    	}    
	         String url = String.format("https://api.mch.weixin.qq.com/pay/orderquery");
	         String buf = HttpUtil.sendPostUrl(url,xmlstring);
	         String content = new String(buf);
	         Map<String,String> xml=null;
	         try {
	 			xml=XMLUtil.doXMLParse(content);
	 		} catch (JDOMException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		} catch (IOException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}
	         log.info("出去了====="+xml);
	    	return xml;
	    }
	  	
	  	
	    /**
	     * 获得签名
	     * @param params  参数
	     * @return  签名以后的字符串
	     */
	    private String genPackageSign(List<NameValuePair> params,String key) {
	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < params.size(); i++) {
	            sb.append(params.get(i).getName());
	            sb.append('=');
	            sb.append(params.get(i).getValue());
	            sb.append('&');
	        }
	        sb.append("key=");
	        sb.append(key);
	        String packageSign = MD5Util.MD5Encode(sb.toString(),"utf-8").toUpperCase();
	        return packageSign;
	    }
	  	
	  	
	    /**
	     * 随机字符串
	     * @return
	     */
	    private String genNonceStr() {
	        Random random = new Random();
	        return MD5Util.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
	    }
}
