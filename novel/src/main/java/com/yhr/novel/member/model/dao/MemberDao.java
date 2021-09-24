package com.yhr.novel.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.yhr.novel.member.model.vo.Member;

@Repository
public class MemberDao {

	/**
	 * 회원가입 요청
	 */
	public int insertMember(Member m, SqlSessionTemplate sqlSession) {
		return sqlSession.insert("memberMapper.insertMember", m);
	}

	/**
	 * 회원 로그인 요청
	 */
	public Member loginMember(Member m, SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("memberMapper.loginMember", m);
	}
	
}
