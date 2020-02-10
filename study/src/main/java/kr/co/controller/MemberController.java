package kr.co.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	// 로그인 POST
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(MemberVO memberVO, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		logger.info("post login");
		
		HttpSession session = request.getSession();
		MemberVO login = service.login(memberVO);
		if(login == null) {
			session.setAttribute("member", null);
			rttr.addFlashAttribute("msg", false);
		} else {
			session.setAttribute("member", login);
		}
		
		return "redirect:/";
	}
	
	// 로그아웃 GET
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) throws Exception {
		session.invalidate();
		return "redirect:/";
	}
	
	// 회원정보 수정(View) GET
	@RequestMapping(value="/memberUpdateView", method=RequestMethod.GET) 
	public String registerUpdateView() throws Exception{
		return "/member/memberUpdateView";
	}
	
	// 회원정보 수정 (처리) POST
	@RequestMapping(value="/memberUpdate", method=RequestMethod.POST)
	public String registerUpdate(MemberVO memberVO, HttpSession session) throws Exception {
		service.memberUpdate(memberVO);
		session.invalidate();
		return "redirect:/";
	}
	
	// 회원탈퇴 GET
	@RequestMapping(value="/memberDeleteView", method=RequestMethod.GET)
	public String memberDeleteView() throws Exception {
		return "/member/memberDeleteView";
	}
	
	// 회원탈퇴 처리 POST
	@RequestMapping(value="memberDelete", method=RequestMethod.POST)
	public String memberDelete(MemberVO memberVO, HttpSession session, RedirectAttributes rttr) throws Exception {
		// 세션이 있는 member를 가져와 member변수에 넣어줍니다
		MemberVO member = (MemberVO)session.getAttribute("member");
		
		// 세션에 들어있는 비밀번호
		String sessionPass = member.getUserPass();
		
		// vo로 들어오는 비밀번호
		String voPass = memberVO.getUserPass();
		
		if(!(sessionPass.equals(voPass))) {
			rttr.addFlashAttribute("msg", false);
			return "redirect:/member/memberDeleteView";
		}
		service.memberDelete(memberVO);
		session.invalidate();
		return "redirect:/";
	}
	
}
