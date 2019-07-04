package com.feign.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("ACCOUNT")
public interface AccountService {

	@GetMapping("phoneScanCode")
	String phoneScanCode(@RequestParam("stbId") String stbId, @RequestParam("username") String username);

//	@GetMapping("codeLogin")
//	String codeLogin(@RequestParam("stbId") String stbId);

	@GetMapping("sendEmailVerificationCode")
	String sendEmailVerificationCode(@RequestParam("email") String email, @RequestParam("type") int type);
	
	@GetMapping("sendPhoneVerificationCode")
	String sendPhoneVerificationCode(@RequestParam("phone") String phone, @RequestParam("type") int type);

	@GetMapping("checkVerificationCode")
	String checkVerificationCode(@RequestParam("email") String email, @RequestParam("phone")  String phone, @RequestParam("verificationCode") int verificationCode);

	@PostMapping("register")
	String register(@RequestParam("id") int id, @RequestParam("username") String username, @RequestParam(value = "relationUser", required = false) String relationUser,
			@RequestParam(value = "type", required = false) Integer type, @RequestParam("password") String password, @RequestParam(value = "cid", required = false) Integer cid, 
			@RequestParam(value = "pid", required = false) Integer pid, @RequestParam(value = "mid", required = false) Integer mid);

	@GetMapping("resetPassword")
	String resetPassword(@RequestParam(value = "email", required = false) String email, 
			@RequestParam(value = "phone", required = false) String phone, 
			@RequestParam("password") String password,
			@RequestParam("verificationCode") int verificationCode);

	@GetMapping("getUserInfoByUsername")
	String getUserInfoByUsername(@RequestParam("username") String username);

	@GetMapping("forgetPassword")
	String forgetPassword(@RequestParam("usertoken") String usertoken, @RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword);

	@GetMapping("bindRelationUser")
	String bindRelationUser(@RequestParam("usertoken") String usertoken,
			@RequestParam("relationUser") String relationUser);

}
