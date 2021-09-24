package com.yhr.novel.member.model.service;

import com.yhr.novel.member.model.vo.Member;

public interface MemberService {
	
	// 로그인
	Member loginMember(Member m);
	
	// 회원가입
	int insertMember(Member m);
	
	// 회원정보 수정
	int updateMember(Member m);
	
	// 회원비밀번호 수정
	int updateMemberPwd(Member m);
	
	// 회원탈퇴
	int deleteMember(Member m);
	
	// 아이디 중복체크
	int idCheckMember(Member m);
	
}
