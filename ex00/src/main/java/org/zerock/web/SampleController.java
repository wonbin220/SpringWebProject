package org.zerock.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.ProductVO;

@Controller
@RequestMapping
public class SampleController {

	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	@RequestMapping("doA")
	public void doA() {
		
		logger.info("doA called................");
	}
	
	@RequestMapping("doB")
	public void doB() {
		logger.info("doB called.................");
	}
	
	@RequestMapping("doC")
	public String doC( @ModelAttribute("msg") String msg) {
		logger.info("doC called.................");
		
		return "result";
	}
	@RequestMapping("doZ")
	public String doZ( @RequestParam String msg, ModelMap model) {
		logger.info("doZ called................." + msg);
		model.addAttribute("msg", "z-" + msg);
		return "result";
	}
	@RequestMapping("doD")
	public String doD( Model model) {
		
		//make sample data
		ProductVO product = new ProductVO("sample Product", 10000);
		logger.info("doD called.................");
		model.addAttribute(product);
		return "productDetail";
	}
	@RequestMapping("doE")
	public String doE( RedirectAttributes rttr) {
		logger.info("doE called but redirect to /doC.................");
		
		rttr.addFlashAttribute("msg", "This is the Message!! with redirected");
		return "redirect:/doC";
	}
	
	@RequestMapping("doY")
	public void doY(HttpServletResponse res) throws Exception {
		logger.info("doY called.....................");
		res.sendRedirect("doC?msg=This is the Message!! with redirected");
	}
	
	@RequestMapping("/doJSON")
	public @ResponseBody ProductVO doJSON() {
		ProductVO vo = new ProductVO("샘플상품", 30000);
		
		return vo;
	}
}
