package com.yhr.novel.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {
	
	/**
	 * 회원가입 폼 포워딩
	 */
	@RequestMapping("enrollForm.me")
	public String memberEnrollForm() {
		return "member/memberEnrollForm";
	}
	
	@RequestMapping("myPage.me")
	public String myPageView() {
		return "member/myPage";
	}
	
}
