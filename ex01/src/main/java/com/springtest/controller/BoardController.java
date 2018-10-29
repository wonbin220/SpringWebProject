package com.springtest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springtest.domain.BoardVO;
import com.springtest.service.BoardService;

@Controller
@RequestMapping("board")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService service;
	
	@RequestMapping(value="/register", method= RequestMethod.GET)
	public void registerGET(BoardVO board, Model model) throws Exception{
		logger.info("register get .........");
	}
	
	@RequestMapping(value="/register", method= RequestMethod.POST)
	public String registPOST(BoardVO board, Model model) throws Exception{
		logger.info("regist post.....................");
		logger.info(board.toString());
		
		service.regist(board);
		
		model.addAttribute("result","success");
		//return "/board/success";
		return "redirect:/board/listAll";	
	}
	
	
//	// jsp방법
//	@RequestMapping(value="register", method= RequestMethod.POST)
//	public void registPOST(BoardVO board, Model model, HttpServletRequest req, HttpServletResponse res) throws Exception{
//		logger.info("regist post.....................");
//		logger.info(board.toString());
//		
//		service.regist(board);
//		res.sendRedirect("/board/listAll");
//	}

	
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void listAll(Model model) throws Exception{
		
		logger.info("show all list...............");
	}
	
}
