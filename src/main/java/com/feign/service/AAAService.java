package com.feign.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("AAA")
public interface AAAService {
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	String login(@RequestParam(value = "userid") String userid, 
			@RequestParam(value = "Authenticator") String Authenticator,
			@RequestParam(value = "type") String type,
			@RequestParam(value = "cid", required = false) String cid, 
			@RequestParam(value = "pid", required = false) String pid, 
			@RequestParam(value = "mid", required = false) String mid,
			@RequestParam(value = "encrypt", required = false) String encrypt,
			@RequestParam(value = "wifimac", required = false) String wifimac,
			@RequestParam(value = "clientIP", required = false) String clientIP,
			@RequestParam(value = "timezone", required = false) String timezone, 
			@RequestParam(value = "verificationCode", required = false) String verificationCode);
	
	@RequestMapping(value = "/getLoginCode", method = RequestMethod.GET)
	String getLoginCode(@RequestParam(value = "userid") String userid);
	
	@RequestMapping(value = "updateUserToken", method = RequestMethod.GET)
	String updateUserToken(@RequestParam(value = "oldusertoken") String usertoken);

	@RequestMapping(value = "modifyIptvFavouritesCategory")
	String modifyIptvFavouritesCategory(@RequestParam(value = "favcategoryid") int favcategoryid, @RequestParam(value = "favcategoryname") String favcategoryname);

	@RequestMapping(value = "favorite")
	String favorite(@RequestParam(value = "usertoken") String usertoken, 
			@RequestParam(value = "action") int action,
			@RequestParam(value = "pid") int pid,
			@RequestParam(value = "sourcetype") String sourcetype, 
			@RequestParam(value = "favcategoryid") int favcategoryid,
			@RequestParam(value = "userid") String userid);

	@PostMapping("getCodeByDeviceId")
	String getCodeByDeviceId(@RequestParam("deviceid")String deviceId);

	@GetMapping("changeDeviceId")
	String changeDeviceId(@RequestParam(value = "activationCode") String activationCode,
			@RequestParam(value = "deviceId") String deviceId, @RequestParam(value = "newDeviceId") String newDeviceIdd);

	@PostMapping("activationCodeRegister")
	String activationCodeRegister(@RequestParam(value = "activationCode") String activationCode,
			@RequestParam(value = "deviceid") String deviceid,@RequestParam(value = "cid", required = false) String cid,
			@RequestParam(value = "pid", required = false) String pid,@RequestParam(value = "mid", required = false) String mid);

	@PostMapping("getLoginQRCode")
	String getLoginQRCode(@RequestParam(value = "usertoken") String usertoken);

	@PostMapping("loginByQRCode")
	String loginByQRCode(@RequestParam(value = "qrcode") String qrcode);

	@PostMapping("codeLogin")
	String codeLogin(@RequestParam("stbId") String stbId);

	@PostMapping("logout")
	String logout(@RequestParam("userid")String userid, @RequestParam("usertoken")String usertoken);

	@PostMapping("heart")
	String heart(@RequestParam("usertoken")String usertoken);

}
