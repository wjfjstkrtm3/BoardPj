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

}
