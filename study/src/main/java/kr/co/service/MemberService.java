package kr.co.service;

import kr.co.vo.MemberVO;

public interface MemberService {
	
	// 회원가입
	public void register(MemberVO memerVO) throws Exception;
	
	// 로그인
	public MemberVO login(MemberVO memberVO) throws Exception;
}
