package com.feign.controller;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.feign.service.AAAService;

@RestController
public class AAAController {

	@Resource
	AAAService aaaService;
	
	@Value("${protocol.scheme:#{null}}")
	private String protocolScheme;
	
	@Value("${protocol.port:#{null}}")
	private String protocolPort;
	
	@Value("${server.port}")
	private String port;
	
	@Value("${server-ip-host-address:#{null}}")
	private String serverIpHostAddress;

	@Resource
	private HttpServletRequest request;
	
	private static final Logger logger = LoggerFactory.getLogger(AAAController.class);

	@RequestMapping(value = "/aaa/ott/login")
	public String login(@RequestParam(value = "userid") String userid,
			@RequestParam(value = "Authenticator") String Authenticator, @RequestParam(value = "type") String type,
			@RequestParam(value = "cid", required = false) String cid,
			@RequestParam(value = "pid", required = false) String pid,
			@RequestParam(value = "mid", required = false) String mid,
			@RequestParam(value = "encrypt", required = false) String encrypt,
			@RequestParam(value = "wifimac", required = false) String wifimac,
			@RequestParam(value = "timezone", required = false) String timezone,
			@RequestParam(value = "verificationCode", required = false) String verificationCode) {
		String clientIP = request.getRemoteHost();
		System.out.println("clientIP:" + clientIP);
		return aaaService.login(userid, Authenticator, type, cid, pid, mid, encrypt, wifimac, clientIP, timezone, verificationCode);
	}

	@RequestMapping(value = "/aaa/ott/getLoginCode")
	public String getLoginCode(@RequestParam(value = "userid") String userid) {
		return aaaService.getLoginCode(userid);
	}

	@RequestMapping(value = "/aaa/ott/updateUserToken")
	public String updateUserToken(@RequestParam(value = "oldusertoken") String usertoken) {
		return aaaService.updateUserToken(usertoken);
	}

	@RequestMapping("/aaa/ott/activationCodeRegister")
	public String activationCodeRegister(@RequestParam(value = "activationCode") String activationCode,
			@RequestParam(value = "deviceid") String deviceid,
			@RequestParam(value = "cid", required = false) String cid,
			@RequestParam(value = "pid", required = false) String pid,
			@RequestParam(value = "mid", required = false) String mid) {
		return aaaService.activationCodeRegister(activationCode, deviceid, cid, pid, mid);
	}

	@RequestMapping(value = "/aaa/ott/getCodeByDeviceId")
	public String getCodeByDeviceId(@RequestParam(value = "deviceid") String deviceId) {
		return aaaService.getCodeByDeviceId(deviceId);
	}

	@RequestMapping(value = "/aaa/ott/changeDeviceId")
	public String changeDeviceId(@RequestParam(value = "activationCode") String activationCode,
			@RequestParam(value = "deviceId") String deviceId,
			@RequestParam(value = "newDeviceId") String newDeviceId) {
		return aaaService.changeDeviceId(activationCode, deviceId, newDeviceId);
	}
	
	@RequestMapping(value = "/aaa/ott/getLoginQRCode")
	public String getLoginQRCode(@RequestParam("usertoken") String usertoken) {
		return aaaService.getLoginQRCode(usertoken);
	}
	
	@RequestMapping(value = "/aaa/ott/loginByQRCode")
	public String loginByQRCode(@RequestParam("qrcode") String qrcode) {
		return aaaService.loginByQRCode(qrcode);
	}
	
	@RequestMapping(value="/aaa/ott/heart")
	public String heart(@RequestParam(value="usertoken")String usertoken){
		return aaaService.heart(usertoken);
	}
	
	@RequestMapping(value = "/aaa/ott/logout")
	public String logout(@RequestParam("userid") String userid, @RequestParam("usertoken") String usertoken) {
		return aaaService.logout(userid,usertoken);
	}
	
	@RequestMapping("/account/codeLogin")
	public String scanCode(@RequestParam("stbId") String stbId){
		return aaaService.codeLogin(stbId);
	}
	
	@RequestMapping("getFeignIp")
	public String getFeignIp() throws SocketException, UnknownHostException {
		String ipHostAddress = null;
		if(null == serverIpHostAddress) {
			Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ipHost = null;
			while (allNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
				Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
				while (addresses.hasMoreElements()) {
					ipHost = (InetAddress) addresses.nextElement();
					String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
							+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
							+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
							+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[2-9])$";
					if (ipHost.getHostAddress().matches(regex)) {
						ipHostAddress = ipHost.getHostAddress();
					}
				}
			}
		} else {
			ipHostAddress = serverIpHostAddress;
		}
		String result = String.format("%s://%s:%s", protocolScheme == null ? "http" : protocolScheme, ipHostAddress, protocolPort == null ? port : protocolPort);
		logger.info(result);
		
		return result;
	}
}
