package kr.co.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.dao.MemberDAO;
import kr.co.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	private MemberDAO dao;
	
	@Override
	public void register(MemberVO memerVO) throws Exception {
		dao.register(memerVO);
	}

	@Override
	public MemberVO login(MemberVO memberVO) throws Exception {
		return dao.login(memberVO);
	}

	@Override
	public void memberUpdate(MemberVO memberVO) throws Exception {
			dao.memberUpdate(memberVO);
	}

	@Override
	public void memberDelete(MemberVO memberVO) throws Exception {
			dao.memberDelete(memberVO);
	}

	@Override
	public int passChk(MemberVO memberVO) throws Exception {
		int result = dao.passChk(memberVO);
		return result;
	}

}
