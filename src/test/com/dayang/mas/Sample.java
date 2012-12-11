package com.dayang.mas;

import static com.dayang.mas.utils.MasUtils.getSmsApiClient;
import static com.dayang.mas.utils.MasUtils.isConnected;

import java.util.ArrayList;
import java.util.List;

import com.jasson.mas.api.sms.SmsApiClient;
import com.jasson.mas.api.sms.SmsApiClientHandler;
import com.jasson.mas.api.smsapi.MsgFmt;
import com.jasson.mas.api.smsapi.SmsSendRequest;
import com.jasson.mas.api.smsapi.SmsSendResponse;
import com.jasson.mas.api.smsapi.SmsType;

public class Sample {

	static final String IP = "127.0.0.1";
	static final int PORT = 1234;
	static final long MOBILE = 13800000000L;
	static final String APP_ID = "123";// API插件标识ID
	static final String PWD = "123";// API插件密码
	static final String XCODE = "123"; // 短信扩展码

	public static void main(String[] args) {
		SmsApiClient smsApiClient = null;
		String content = "发送API短信"; // 短信内容
		
		// SmsApiClientHandlerImpl是由自己实现的
		SmsApiClientHandler smsHandler = new SmsApiClientHandlerImpl();
		
		try {
			smsApiClient = getSmsApiClient(smsHandler, IP, PORT,
					APP_ID, PWD);
			
			// 获取网关连接状态(Connect:连接正常, Disconnect:断连, NotConnect:没有连接, Other:其他)
			if(!isConnected(smsApiClient)) {
				System.out.println("网关未连接");
				return;
			}
			
//			// 计算短信条数和字数
//			SmsCount smsCount = smsApiClient.getSmsCount(content,
//					MsgFmt.GB2312, SmsType.Normal);
//			// 获得扩展服务代码(插件短信扩展号码+流水号)长度
//			int xcodeLength = smsApiClient.getXCodeLength();
			
			
			// 发送短信
			// ======构造发送短信对象开始,下面代码演示发送短信对象几个比较主要的属性值,
			// 其它的属性可以不设置,如果要设置可以参考sendSms方法中 SmsSendRequest参数===
			int ret = smsApiClient.getDestAddrsLimit();
			
			List<String> list = new ArrayList<String>();
			// 每次群发数量不能超过最大限制数
			for (int i = 0; i < ret; i++) {
				long address = MOBILE + i;
				list.add(String.valueOf(address));
			}
			SmsSendRequest smsSendRequest = new SmsSendRequest();
			
			smsSendRequest.destAddrs = list;
			smsSendRequest.validTime = 10000; // 短信存活期,单位秒
			smsSendRequest.xCode = XCODE; // 短信扩展码
			smsSendRequest.message = content; // 短信内容
			smsSendRequest.msgFormat = MsgFmt.GB2312; // 短信编码类型
			smsSendRequest.isNeedReport = true; // 短信是否需要状态报告
			smsSendRequest.priority = 1; // 短信网关优先级, 短信优先级大于0 的整数
											// 0为最高优先级,数字越大级别越低

			// Normal: 普通短信,Instant:免提短信, Long:长短信,Structured:二进制短信,WapPush:
			// WapPush 短信
			smsSendRequest.type = SmsType.Normal;
			smsSendRequest.appID = APP_ID;
			SmsSendResponse smsSendResponse = smsApiClient
					.sendSms(smsSendRequest);
			System.out.println("提交成功,requestID:" + smsSendResponse.requestID);
			
		} catch (Exception e) {
			
			System.out.println("API短信客户端调用失败:" + e.getMessage());
			return;
		}
	}
}
