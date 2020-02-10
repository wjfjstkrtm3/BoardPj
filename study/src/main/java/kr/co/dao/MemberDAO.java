package kr.co.dao;

import kr.co.vo.MemberVO;

public interface MemberDAO {
	
	// 회원가입
	public void register(MemberVO memerVO) throws Exception;
	
	// 로그인
	public MemberVO login(MemberVO memberVO) throws Exception;
	
	// 회원정보 수정
	public void memberUpdate(MemberVO memberVO) throws Exception;
	
	// 회원탈퇴
	public void memberDelete(MemberVO memberVO) throws Exception;
	
}
