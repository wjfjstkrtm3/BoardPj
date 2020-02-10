package kr.co.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.vo.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

	@Inject
	private SqlSession sqlsession;
	
	@Override
	public List<ReplyVO> readReply(int bno) throws Exception {
		return sqlsession.selectList("replyMapper.readReply", bno);
	}

	@Override
	public void writeReply(ReplyVO replyVO) throws Exception {
		sqlsession.insert("replyMapper.writeReply", replyVO);
	}

}
