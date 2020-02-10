package kr.co.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.service.MemberService;
import kr.co.vo.MemberVO;

@Controller
@RequestMapping(value="/member/*")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	private MemberService service;
	
	// 회원가입 get
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public void getRegister() throws Exception {
		logger.info("get register");
	}
	
	// 회원가입 post
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String postRegister(MemberVO memberVO) throws Exception {
		logger.info("post register");
		service.register(memberVO);
		
		return null;
	}
	
}
