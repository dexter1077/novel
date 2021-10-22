package com.yhr.novel.novel.model.service;

import com.yhr.novel.novel.model.vo.Novel;

public interface NovelService {
	
	// 1. 공모전조회
	Novel selectContest();
	
	// 2. 웹소설 조회
	Novel selectNovel();
	
	// 3. 작품 올리기
	int createNovel(Novel novel);
	
}
