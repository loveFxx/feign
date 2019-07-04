package com.feign.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.feign.service.AccountService;

@RestController
public class AccountController {

	@Resource
	AccountService accountService;
	
	@RequestMapping("/account/phoneScanCode")
	public String phoneScan(@RequestParam("stbId") String stbId, @RequestParam("username") String username) {
		return accountService.phoneScanCode(stbId, username);
	}
	
//	@RequestMapping("/account/codeLogin")
//	public String scanCode(@RequestParam("stbId") String stbId){
//		return accountService.codeLogin(stbId);
//	}
	
	@RequestMapping("/account/sendVerificationCode")
	public String sendVerificationCode(@RequestParam(value = "email", required = false) String email, 
			@RequestParam(value = "phone", required = false) String phone, 
			@RequestParam("type") int type) {
		if(null != email) {
			return accountService.sendEmailVerificationCode(email, type);
		} 
		if(null != phone) {
			return accountService.sendPhoneVerificationCode(phone, type);
		}
		return "";
	}
	
	@RequestMapping("/account/checkVerificationCode")
	public String checkVerificationCode(@RequestParam(value = "email", required = false) String email, 
			@RequestParam(value = "phone", required = false) String phone, 
			@RequestParam("verificationCode") int verificationCode) {
		if(null != email || null != phone) {
			return accountService.checkVerificationCode(email, phone, verificationCode);
		} 
		return "";
	}
	
	@RequestMapping("/account/register")
	public String register(@RequestParam("id") int id, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam(value = "relationUser", required = false) String relationUser,
			@RequestParam(value = "type", required = false) Integer type, @RequestParam(value = "cid", required = false) Integer cid, @RequestParam(value = "pid", required = false) Integer pid, @RequestParam(value = "mid", required = false) Integer mid) {
		return accountService.register(id, username, relationUser, type, password, cid, pid, mid);
	}
	
	@RequestMapping("/account/resetPassword")
	public String resetPassword(@RequestParam(value = "email", required = false) String email, 
			@RequestParam(value = "phone", required = false) String phone, 
			@RequestParam("password") String password,
			@RequestParam("verificationCode") int verificationCode) {
		if(null != email || null != phone) {
			return accountService.resetPassword(email, phone, password, verificationCode);
		} 
		return "";
	}
	
	@RequestMapping("/account/getUserInfoByUsername")
	public String getUserInfoByUsername(@RequestParam("username") String username) {
		return accountService.getUserInfoByUsername(username);
	}
	
	@RequestMapping("/account/forgetPassword")
	public String forgetPassword(@RequestParam("usertoken") String usertoken, @RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword) {
		return accountService.forgetPassword(usertoken, oldPassword, newPassword);
	}
	
	@RequestMapping("/account/bindRelationUser")
	public String bindRelationUser(@RequestParam("usertoken") String usertoken,
			@RequestParam("relationUser") String relationUser) {
		return accountService.bindRelationUser(usertoken, relationUser);
	}
}
