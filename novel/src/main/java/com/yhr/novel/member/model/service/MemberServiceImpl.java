package com.yhr.novel.member.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhr.novel.member.model.dao.MemberDao;
import com.yhr.novel.member.model.vo.Member;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDao mDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	/**
	 * 회원 로그인 요청
	 */
	@Override
	public Member loginMember(Member m) {
		return mDao.loginMember(m, sqlSession);
	}

	/**
	 * 회원가입 요청
	 */
	@Override
	public int insertMember(Member m) {
		return mDao.insertMember(m, sqlSession);
	}

	@Override
	public int updateMember(Member m) {
		return 0;
	}

	@Override
	public int updateMemberPwd(Member m) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(Member m) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int idCheckMember(Member m) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
