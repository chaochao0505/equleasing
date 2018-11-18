/**  
* <p>Title: WeixinConfig.java</p>  
* <p>Description: </p>  
* @author koko 
* @date 2018年11月13日  
* @version 1.0  
*/ 
package com.shx.web.config;

/**  
* <p>Title: WeixinConfig</p>  
* <p>Description:微信相关配置 </p>  
* @author koko  
* @date 2018年11月13日  
*/
public class WeixinConfig {
	//APP支付参数
	//商户平台和开发平台约定的API密钥，在商户平台设置 
    public static final String key="379726AA39BBAAAC0729E1FFDD777A14";
	//交易类型
	public static final String trade_type = "APP";
	//微信合法appId
	public static final String appid = "wx1ab1030c90623484";
	//商户号
	public static final String mch_id = "1498444282";
	//异步通知地址
	public static final String notify_url="https://www.shengfeikj.com/sharepowerbank/payment/wxPaynotify";
	//--------------------------------------------------------
	//JSAPI支付,二维码支付
	//微信合法appId
	public static final String html_appid = "wx1ab1030c90623484";
	//微信商户号
	public static final String partner_id = "1498444282";
	//微信API密钥
	public static final String html_key = "379726AA39BBAAAC0729E1FFDD777A14";
	//微信支付类型 ,此支付类型为---二维码支付
	public static final String html_type = "JSAPI";
	
	//--------------------------------------------------------------------------------
	//通用微信接口地址
	public static final String service_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		
}
