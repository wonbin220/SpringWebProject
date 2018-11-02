package com.springtest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springtest.domain.BoardVO;
import com.springtest.domain.Criteria;
import com.springtest.domain.PageMaker;
import com.springtest.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService service;
	
	@RequestMapping(value="/register", method= RequestMethod.GET)
	public void registerGET(BoardVO board, Model model) throws Exception{
		logger.info("register get .........");
	}
	
//	@RequestMapping(value="/register", method= RequestMethod.POST)
//	public String registPOST(BoardVO board, Model model) throws Exception{
//		logger.info("regist post.....................");
//		logger.info(board.toString());
//		
//		service.regist(board);
//		
//		model.addAttribute("result","success");
//		//return "/board/success";
//		return "redirect:/board/listAll";	
//	}
	
	//등록
//	http://localhost:8080/board/listAll?result=success 뒤에 result=success가 계속 남기때문에 안보이게 수정
	@RequestMapping(value="/register", method= RequestMethod.POST)
	public String registPOST(BoardVO board, RedirectAttributes rttr) throws Exception{
		logger.info("regist post.....................");
		logger.info(board.toString());
		
		service.regist(board);
		
		rttr.addFlashAttribute("msg","success"); //redirect는 글 도배 방지.
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

	//리스트
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void listAll(Model model) throws Exception{
		
		logger.info("show all list...............");
		model.addAttribute("list", service.listAll());
	}

	//조회
	@RequestMapping(value="/read", method= RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, Model model)throws Exception{
	
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value="/remove", method= RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno, RedirectAttributes rttr) throws Exception {
		
		service.remove(bno);
		rttr.addFlashAttribute("msg","success");
		
		return "redirect:/board/listAll";
	}
	
	//GET방식으로 조회페이로 이동 -> 원래의 게시물 데이터를 읽어와서 Model에 넣어서 전달
	@RequestMapping(value="/modify", method= RequestMethod.GET)
	public void modifyGET(int bno, Model model) throws Exception{
		
		model.addAttribute(service.read(bno));
	
	}
	
	// POST방식으로 실제 수정 작업을 처리, 
	@RequestMapping(value="/modify", method= RequestMethod.POST)
	public String modifyPOST(BoardVO board, RedirectAttributes rttr) throws Exception{
		
		logger.info("mod post..............");
		service.modify(board);
		rttr.addFlashAttribute("msg", "success");
		
		return "redirect:/board/listAll";
	}
	
//	@RequestMapping(value="/listCri", method= RequestMethod.GET)
//	public void lisAll(Criteria cri, Model model)throws Exception{
//		
//		logger.info("show list Page with Criteria............");
//		model.addAttribute("list", service.listCriteria(cri));
//	}
	
	// 직관적으로 알수있게 listCri가 어디에 속해있는지, board인지 다른곳인지  void보단 String으로 변경해주고 return값으로 알수있게
	@RequestMapping(value="/listCri", method= RequestMethod.GET)
	public String lisAll(Criteria cri, Model model)throws Exception{
		
		logger.info("show list Page with Criteria............");
		model.addAttribute("list", service.listCriteria(cri));
		return "board/listCri";
	}
	
	@RequestMapping(value="/listPage", method=RequestMethod.GET)
	public void listPage(Criteria cri, Model model)throws Exception{
		logger.info(cri.toString());
		
		model.addAttribute("list", service.listCriteria(cri));
		PageMaker pageMaker =  new PageMaker();
		pageMaker.setCri(cri);
		//pageMaker.setTotalCount(131);//임의 값
		
		pageMaker.setTotalCount(service.listCountCriteria(cri)); //totalcount
		
		model.addAttribute("pageMaker", pageMaker);
	}
	
	
}