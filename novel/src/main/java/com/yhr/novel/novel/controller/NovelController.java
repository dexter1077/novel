package com.yhr.novel.novel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author HeeRak
 *
 */
@Controller
public class NovelController {
	
	/**
	 * 공모전 페이지 조회
	 */
	@RequestMapping("contest.no")
	public String contestView() {
		return "novel/contestView";
	}
	
}
