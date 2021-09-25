package com.yhr.novel.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
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
	 * 마이페이지 뷰 포워딩
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
	
	/**
	 * 회원 로그인
	 */
	@RequestMapping("logout.me")
	public String logoutMember(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	/**
	 * 카카오 계정 로그인
	 */
	@RequestMapping(value = "klogin.do", produces = "application/json", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView kakaoLogin(@RequestParam("code") String code, 
									HttpServletRequest request, 
									HttpServletResponse response, 
									HttpSession session, 
									ModelAndView mv) throws Exception {
		
		JsonNode node = KakaoController.getAccessToken("login", code);
		JsonNode accessToken = node.get("access_token");
		String token = accessToken.toString();
		session.setAttribute("token", token);
		
		// 사용자 정보
		JsonNode userInfo = KakaoController.getKakaoUserInfo(accessToken);
		String kemail = null;
		String kgender = null;
		String kage = null;
		
		System.out.println("사용자 정보  : " + userInfo);
		
		// 유저정보 카카오에서 가져오기
		JsonNode properties = userInfo.path("properties");
		JsonNode kakao_account = userInfo.path("kakao_account");
		
		kemail = kakao_account.path("email").asText();
		kgender = kakao_account.path("gender").asText();
		kage = kakao_account.path("kage").asText();
		
		// 계정 Member에 담기
		Member m = new Member();
		m.setEmail(kemail);
		m.setGender(kgender);
		m.setBirthday(kage);
		
		System.out.println(m);
		
		mv.setViewName("redirect:/");
		
		
		return mv;
	}
	
}
