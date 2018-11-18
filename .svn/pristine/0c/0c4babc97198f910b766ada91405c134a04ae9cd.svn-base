/**  
* <p>Title: WeixinBean.java</p>  
* <p>Description: </p>  
* @author koko 
* @date 2018年11月13日  
* @version 1.0  
*/ 
package com.shx.web.pay;

/**  
* <p>Title: WeixinBean</p>  
* <p>Description:微信实体 </p>  
* @author koko  
* @date 2018年11月13日  
*/
public class WeixinBean {

	/**
	 * 微信合法appId
	 */
	private String appid ;	
	/**
	 * 微信商户号
	 */
	private String mch_id ;	
	/**
	 * 微信API密钥
	 */
	private String key;				
	/**
	 * 微信支付类型  
	 */
	private String trade_type;		
	/**
	 * 支付者唯一标识,用于JSAPI支付
	 */
	private String openid; 			
	/**
	 * 此参数为二维码中包含的商品ID，商户自行定义 ,必填
	 */
	private String product_id;		
	/**
	 * 商品描述
	 */
	private String body; 			
	/**
	 * 订单号
	 */
	private String out_trade_no;	
	/**
	 * 金额  必须为整数  单位为分
	 */
	private String total_fee;		
	/**
	 * 异步通知地址
	 */
	private String notify_url;
	
	/**
	 * 无参构造
	 */
	public WeixinBean() {
		super();
	}
	
	/**
	 * 有参构造
	 * @param appid         微信合法appId
	 * @param mch_id		微信商户号	
	 * @param key			微信API密钥
	 * @param trade_type	微信支付类型 
	 * @param openid		支付者唯一标识,用于JSAPI支付
	 * @param product_id	此参数为二维码中包含的商品ID，商户自行定义 
	 * @param body			商品描述
	 * @param out_trade_no	订单号
	 * @param total_fee		支付金额  必须为整数  单位为分
	 * @param nonce_str		随机字符串
	 * @param notify_url	异步通知地址
	 */
	public WeixinBean(String appid, String mch_id, String key, String trade_type, String openid, String product_id,
			String body, String out_trade_no, String total_fee, String notify_url) {
		super();
		this.appid = appid;
		this.mch_id = mch_id;
		this.key = key;
		this.trade_type = trade_type;
		this.openid = openid;
		this.product_id = product_id;
		this.body = body;
		this.out_trade_no = out_trade_no;
		this.total_fee = total_fee;
		this.notify_url = notify_url;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	} 	
	
	
}
