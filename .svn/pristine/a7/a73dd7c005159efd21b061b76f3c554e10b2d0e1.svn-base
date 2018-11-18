/**  
* <p>Title: WechatConfig.java</p>  
* <p>Description: </p>  
* @author koko 
* @date 2018年11月13日  
* @version 1.0  
*/ 
package com.shx.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**  
* <p>Title: WechatConfig</p>  
* <p>Description:微信相关配置 </p>  
* @author koko  
* @date 2018年11月13日  
*/
@Component
@ConfigurationProperties(prefix="wechat")
public class WechatConfig {

	/**
	 * 公众平台id
	 */
	@Value("${wechat.appid}")
	private String appid;
    /**
     * 商户号
     */
	@Value("${wechat.mchId}")
    private String mchId;

    /**
     * 商户密钥
     */
    @Value("${wechat.mchKey}")
    private String mchKey;

    /**
     * 商户证书路径
     */
    @Value("${wechat.keyPath}")
    private String keyPath;

    /**
     * 微信支付异步通知地址
     */
    @Value("${wechat.notify_url}")
    private String notifyUrl;
   
    /**
     * 微信统一下单接口地址
     */
    @Value("${wechat.service_url}")
    private String serviceUrl;
    

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getMchKey() {
		return mchKey;
	}

	public void setMchKey(String mchKey) {
		this.mchKey = mchKey;
	}

	public String getKeyPath() {
		return keyPath;
	}

	public void setKeyPath(String keyPath) {
		this.keyPath = keyPath;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
    
    
}
