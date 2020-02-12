package kr.co.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.service.MemberService;
import kr.co.vo.MemberVO;

@Controller
@RequestMapping(value="/member/*")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	private MemberService service;
	
	@Inject
	private BCryptPasswordEncoder pwdEncoder;
	
	// 회원가입 get
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public void getRegister() throws Exception {
		logger.info("get register");
	}
	
	// 회원가입 post
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String postRegister(MemberVO memberVO) throws Exception {
		logger.info("post register");
		int result = service.idChk(memberVO);
		if(result == 1) {
			return "/member/register";
		} else if(result ==0) {
			String inputPass = memberVO.getUserPass();
			String pwd = pwdEncoder.encode(inputPass);
			memberVO.setUserPass(pwd);
			service.register(memberVO);
		}
		
		return "redirect:/";
	}
	
	// 로그인 POST
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(MemberVO memberVO, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		logger.info("post login");
		
		HttpSession session = request.getSession();
		MemberVO login = service.login(memberVO);
		
		boolean pwdMatch = pwdEncoder.matches(memberVO.getUserPass(), login.getUserPass());
		
		
		if(login != null && pwdMatch == true) {
			session.setAttribute("member", login);
		} else {
			session.setAttribute("member", null);
			rttr.addFlashAttribute("msg", false);
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
		
		service.memberDelete(memberVO);
		session.invalidate();
		return "redirect:/";
	}
	
	// 패스워드 체크
	@ResponseBody
	@RequestMapping(value="/passChk", method=RequestMethod.POST)
	public boolean passChk(MemberVO memberVO) throws Exception {
		MemberVO login = service.login(memberVO);
		boolean pwdChk = pwdEncoder.matches(memberVO.getUserPass(), login.getUserPass());
		return pwdChk;
	}
	
	@ResponseBody
	@RequestMapping(value="/idChk", method=RequestMethod.POST)
	// 아이디 중복 체크
	public int idChk(MemberVO memberVO) throws Exception {
		int result = service.idChk(memberVO);
		return result;
	}
	
	
	
}
