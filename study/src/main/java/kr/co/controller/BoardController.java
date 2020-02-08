package kr.co.controller;

import java.io.Writer;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.service.BoardService;
import kr.co.vo.BoardVO;
import kr.co.vo.Criteria;
import kr.co.vo.PageMaker;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	BoardService service;
	
	// 게시판 글 작성 화면
	@RequestMapping(value = "/board/writeView", method = RequestMethod.GET)
	public void writeView() throws Exception{
		logger.info("writeView");
		
	}
	
	// 게시판 글 작성
	@RequestMapping(value = "/board/write", method = RequestMethod.POST)
	public String write(BoardVO boardVO) throws Exception{
		logger.info("write");
		
		service.write(boardVO);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Criteria cri) throws Exception{
		logger.info("list");
		
		model.addAttribute("list", service.list(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listCount());
		
		model.addAttribute("pageMaker", pageMaker);
		
		return "board/list";
		
	}
	
	// 게시물 조회
	@RequestMapping(value="/readView", method=RequestMethod.GET)
	public String read(BoardVO boardVO, Model model) throws Exception {
		logger.info("read");
		model.addAttribute("read", service.read(boardVO.getBno()));
		return "/board/readView";
	}
	
	// 게시물 수정뷰
	@RequestMapping(value="/updateView", method=RequestMethod.GET)
	public String updateView(BoardVO boardVO, Model model) throws Exception {
		logger.info("updateView");
		model.addAttribute("update", service.read(boardVO.getBno()));
		return "/board/updateView";
	}
	
	// 게시물 수정
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(BoardVO boardVO) throws Exception {
		logger.info("update");
		service.update(boardVO);
		return "redirect:/board/list";
	}
	
	// 게시물 삭제
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(int bno) throws Exception {
		service.delete(bno);
		return "redirect:/board/list";
	}
}