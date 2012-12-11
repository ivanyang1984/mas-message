package com.dayang.mas.utils;

import com.jasson.mas.api.ApiException;
import com.jasson.mas.api.common.ConnectStatus;
import com.jasson.mas.api.sms.SmsApiClient;
import com.jasson.mas.api.sms.SmsApiClientHandler;
import com.jasson.mas.api.sms.SmsApiClientImpl;

public class MasUtils {
	
	public static SmsApiClient getSmsApiClient(SmsApiClientHandler smsHandler,
			String server_ip, int port, String app_id, String password) throws ApiException {
		SmsApiClient smsApiClient = new SmsApiClientImpl(smsHandler, server_ip, port,
				app_id, password);
		
		// 设置是否自动重连到服务器(可以不需要设置)
		smsApiClient.setAutoConnect(true);
		// 设置自动重连服务器相隔时间(单位:秒), 默认为30秒(可以不需要设置)
		smsApiClient.setReConnectInterval(60);
		// 设置与服务连接超时时长,单位:millisecond(可以不需要设置)
		smsApiClient.setConnectTimeout(100000);
		// 设置发送超时时长,单位:millisecond(可以不需要设置)
		smsApiClient.setSendTimeout(1000000);
		
		return smsApiClient;
	}
	
	public static boolean isConnected(SmsApiClient client) throws ApiException {
		ConnectStatus connectStatus = client.getConnStatusIAGW();
		return ConnectStatus.Connect.equals(connectStatus);
	}
	
}
