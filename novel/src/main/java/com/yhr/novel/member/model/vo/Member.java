package com.yhr.novel.member.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter @Setter
@ToString
public class Member {
	private int userNo;
	private String userId;
	private String userPwd;
	private String username;
	private String nickname;
	private String gender;
	private String address;
	private String addressDetail;
	private String birthday;
	private String enrollDate;
	private String modifyDate;
	private String phone;
	private String email;
	private String status;
	private int coin;
}
