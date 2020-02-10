package kr.co.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import kr.co.dao.ReplyDAO;
import kr.co.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Inject
	private ReplyDAO dao;
	
	@Override
	public List<ReplyVO> readReply(int bno) throws Exception {
		return dao.readReply(bno); 
	}

	@Override
	public void writeReply(ReplyVO replyVO) throws Exception {
			  dao.writeReply(replyVO);
	}

	@Override
	public void updateReply(ReplyVO replyVO) throws Exception {
			dao.updateReply(replyVO);
	}

	@Override
	public void deleteReply(ReplyVO replyVO) throws Exception {
			dao.deleteReply(replyVO);
	}

	@Override
	public ReplyVO selectReply(int rno) throws Exception {
		return dao.selectReply(rno);
	}

}
