package kr.co.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.vo.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO{

	@Inject
	private SqlSession sqlsession;	
	
	
	@Override
	public void register(MemberVO memberVO) throws Exception {
		sqlsession.insert("memberMapper.register", memberVO);
	}

}
