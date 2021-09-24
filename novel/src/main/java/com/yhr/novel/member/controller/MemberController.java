package com.yhr.novel.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yhr.novel.member.model.service.MemberService;
import com.yhr.novel.member.model.vo.Member;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService mService;
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	/**
	 * 회원가입 폼 포워딩
	 */
	@RequestMapping("enrollForm.me")
	public String memberEnrollForm() {
		return "member/memberEnrollForm";
	}
	
	/**
	 * 회원가입
	 */
	@RequestMapping("enroll.me")
	public ModelAndView insertMember(HttpSession session, ModelAndView mv, Member m) {
		
		System.out.println(m);
		
		if(m.getUserPwd() != null) {
			// 비밀번호 암호화 후 m의 password 필드에 담기
			m.setUserPwd(bcryptPasswordEncoder.encode(m.getUserPwd()));
		}
		
		m.setAddress(m.getAddress() + " " + m.getAddressDetail());
		
		int result = mService.insertMember(m);
		
		if(result > 0) {
			session.setAttribute("alertMsg", "회원가입에 성공했습니다!");
			mv.setViewName("main");
		}else {
			mv.addObject("errorMsg", "회원가입 실패");
			mv.setViewName("common/errorPage");
		}
		
		return mv;
	}
	
	/**
	 * 
	 */
	@RequestMapping("myPage.me")
	public String myPageView() {
		return "member/myPage";
	}
	
	@RequestMapping("login.me")
	public ModelAndView loginMember(Member m, HttpSession session, ModelAndView mv) {
		
		Member loginUser = mService.loginMember(m);
		
		if(loginUser != null && bcryptPasswordEncoder.matches(m.getUserPwd(), loginUser.getUserPwd())) {
			
			session.setAttribute("loginUser", loginUser);
			session.setAttribute("alertMsg", loginUser.getNickname() + "님 환영합니다!");
			mv.setViewName("redirect:/");
			
		}else {
			mv.addObject("errorMsg", "로그인 실패");
			mv.setViewName("common/errorPage");
		}
		
		return mv;
	}
	
	@RequestMapping("logout.me")
	public String logoutMember(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
	}
	
}
