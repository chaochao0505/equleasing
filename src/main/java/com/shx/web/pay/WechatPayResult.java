/**  
* <p>Title: WechatPayResult.java</p>  
* <p>Description: </p>  
* @author koko 
* @date 2018年11月13日  
* @version 1.0  
*/ 
package com.shx.web.pay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jdom.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shx.web.utils.ExceptionUtil;
import com.shx.web.utils.XMLUtil;


/**  
* <p>Title: WechatPayResult</p>  
* <p>Description: 支付回调函数 </p>  
* @author koko  
* @date 2018年11月13日  
*/
@Controller
@RequestMapping(value = "wechat")
public class WechatPayResult {
	private final Logger logger = LoggerFactory.getLogger(WechatPayResult.class);
	
	/**
	 * 微信支付回调函数
	 * <p>Title: weiXinOrderSearch</p>  
	 * <p>Description: </p>  
	 * @param request
	 * @param response
	 * @throws JDOMException
	 * @throws IOException
	 */
	@RequestMapping(value = "/WeChatOrderSearchApi")
	public void weiXinOrderSearch(HttpServletRequest request, HttpServletResponse response) throws JDOMException, IOException{
		logger.info("进入微信支付回调函数!!!!-----------");
		
		String xml = this.wechatResultInfo(request);
		logger.info("获取微信支付完成回调信息!!!!-----"+xml+"------");
		//解析返回的xml
		Map result = XMLUtil.doXMLParse(xml);
		logger.info("微信支付--------返回数据-----"+result+"---");
		
		//定义回复微信类型
		List<NameValuePair> packageParams = new LinkedList<NameValuePair>(); 
		WechatPay wcpu = new WechatPay();
		//判断支付是否成功
		if (result.get("return_code").equals("SUCCESS")) {
			logger.info("微信支付--------判签成功--------");	
			String transaction_id = (String) result.get("transaction_id");   //微信支付后订单号
			String indentNum = (String) result.get("out_trade_no");   	// 商户订单号
			String openid = (String) result.get("openid");         //消费者唯一标识
			String type = (String) result.get("trade_type");
			logger.info("微信支付------支付单号--"+transaction_id+"--------订单号--"+indentNum+"-----消费者唯一标识--"+openid+"----"+"type---------------------"+type);
			
			//通过订单号查询支付信息,业务结果 return_code 返回 SUCCESS/FAIL
			Map xmlNew = wcpu.getSelectOrder(transaction_id,type);
			logger.info("微信支付------判断是否支付成功:=="+xmlNew.get("return_code").equals("SUCCESS")+"-----订单号"+indentNum+"-----");
			
			if (xmlNew.get("return_code").equals("SUCCESS")) {// 支付成功
				
				logger.info("微信支付---支付成功!!! 进入修改项目,支付订单号：" + indentNum + "=========================");
				
				try {    //处理支付成功的方法
					transactionSUCCESS(indentNum);
					logger.info("微信支付-----================参数修改成功!=======.=========");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error("微信支付-----===错误信息：====="+e.getMessage()+"======订单号:======="+indentNum);
				}
				//返回给微信回调信息
				packageParams.add(new BasicNameValuePair("return_code", "SUCCESS"));
				
				packageParams.add(new BasicNameValuePair("return_msg", "ok"));
				
			} else {// 支付失败
				// 订单号
				logger.info("微信支付-----支付失败!进入失败回调回调11111----------");
				transactionFAIL(indentNum);
				packageParams.add(new BasicNameValuePair("return_code", "FAIL"));
				packageParams.add(new BasicNameValuePair("return_msg", "no"));
				logger.error("错误信息："+"订单号："+indentNum);
			}
		}else{
			logger.info("微信支付-----支付失败!进入失败回调回调222222------------");
			// 订单号
			String indentNum = (String) result.get("out_trade_no");
			transactionFAIL(indentNum);
			packageParams.add(new BasicNameValuePair("return_code", "FAIL"));
			packageParams.add(new BasicNameValuePair("return_msg", "no"));
		}
		//转换成xml,返给微信
		String xmlstring = wcpu.toXml(packageParams);
		try {
			xmlstring = new String(xmlstring.toString().getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			logger.error("错误信息："+e.getMessage()+ExceptionUtil.getExceptionDescribe(e));
		}
		response.setContentType("application/xml");
		PrintWriter pw = response.getWriter();
		pw.print(xmlstring);
		pw.flush();
		pw.close();
		logger.info("微信支付-----支付!进入回调尾部---结束--------");
	}
	
	/**
	 * 获取微信支付完成回调信息
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public String wechatResultInfo(HttpServletRequest request) throws IOException {
		char[] readerBuffer = new char[request.getContentLength()];
		BufferedReader bufferedReader = request.getReader();
		int portion = bufferedReader.read(readerBuffer);
		int amount = portion;
		while (amount < readerBuffer.length) {
			portion = bufferedReader.read(readerBuffer, amount, readerBuffer.length - amount);
			amount = amount + portion;
		}
		StringBuffer stringBuffer = new StringBuffer((int) (readerBuffer.length * 1.5));
		for (int index = 0; index < readerBuffer.length; index++) {
			char c = readerBuffer[index];
			stringBuffer.append(c);
		}
		String xml = stringBuffer.toString();
		bufferedReader.close();
		return xml;
	}
	
	/**
	 * 支付成功：根据不同交易类型处理不同的业务。
	 * @param indentNum		       订单号
	 * @param transaction_id  交易单号 
	 * @param openid		      消费者唯一标识
	 * @return		  
	 * @throws Exception
	 */
	@Transactional(rollbackFor=Exception.class)
	public void transactionSUCCESS(String indentNum) throws Exception{
	
	}
	
	/**
	 * 支付失败：根据不同交易类型处理不同的业务。
	 * @param indentNum 订单号
	 * @author liduo
	 */
	public void transactionFAIL(String indentNum) {
		logger.info("PayResult----1.支付失败回调函数，订单号："+indentNum);
		// type 0订单支付1充值支付
		Integer type = new Integer(indentNum.substring(0, 1));
		switch (type) {
			case 0:// 订单支付
				//无需操作
				logger.info("PayResult----2.消费支付失败回调函数，订单号："+indentNum+"，订单支付失败操作。");
				break;
			case 1:// 充值       
				logger.info("PayResult----2.充值支付失败回调函数，订单号："+indentNum+"，订单支付失败操作。");
				break;
			default:
				logger.info("支付失败回调函数错误信息：解析订单号错误");
		}
	}
}
