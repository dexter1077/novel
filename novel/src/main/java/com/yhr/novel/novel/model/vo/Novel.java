package com.yhr.novel.novel.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter @Setter
@ToString
public class Novel {
	
	private int novelNo;
	private int userNo;
	private String mainTitle;
	private String league;
	private String mainThumbnail;
	private String createDate;
	private String endDate;
	
}
