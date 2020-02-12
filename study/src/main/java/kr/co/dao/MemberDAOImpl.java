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


	@Override
	public MemberVO login(MemberVO memberVO) throws Exception {
		return sqlsession.selectOne("memberMapper.login", memberVO);
	}


	@Override
	public void memberUpdate(MemberVO memberVO) throws Exception {
		sqlsession.update("memberMapper.memberUpdate", memberVO);
	}


	@Override
	public void memberDelete(MemberVO memberVO) throws Exception {
		sqlsession.delete("memberMapper.memberDelete", memberVO);
	}


	@Override
	public int passChk(MemberVO memberVO) throws Exception {
		int result = sqlsession.selectOne("memberMapper.passChk", memberVO);
		return result;
	}

}
