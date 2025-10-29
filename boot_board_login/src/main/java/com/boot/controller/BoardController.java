package com.boot.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.dto.BoardDTO;
import com.boot.dto.MemDTO;
import com.boot.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	//게시판 목록 조회
	@RequestMapping("/list")
//	public String list(Model model) {
	public String list(Model model, HttpServletRequest request) { //로그인 세션 받아오기 
		log.info("@# list()");
		
		HttpSession session = request.getSession(); //세션 가져와서
		MemDTO mdto = (MemDTO) session.getAttribute("LOGIN_MEMBER"); // 세션
		log.info("@# mdto => " + mdto);
		
		if (mdto == null) {
			return "redirect:login"; //세션 정보가 없으면 login으로 이동 
		}
		
		ArrayList<BoardDTO> list = service.list();
		model.addAttribute("list", list);
		
		return "list";
	}
	
	@RequestMapping("/write")
	public String write(@RequestParam HashMap<String, String> param, Model model) {
		log.info("@# write()");
		log.info("@# param=>"+param);
		
		service.write(param);
		
		return "redirect:list";
	}
	
	@RequestMapping("/write_view")
	public String write_view() {
		log.info("@# write_view()");
		
		return "write_view";
	}
	
	@RequestMapping("/content_view")
	public String content_view(@RequestParam HashMap<String, String> param, Model model) {
		log.info("@# content_view()");
		
		BoardDTO dto = service.contentView(param);
		model.addAttribute("content_view", dto);
		
		return "content_view";
	}
	
	@RequestMapping("/modify")
	public String modify(@RequestParam HashMap<String, String> param, Model model) {
		log.info("@# modify()");
		
		service.modify(param);
		
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam HashMap<String, String> param, Model model) {
		log.info("@# delete()");
		
		service.delete(param);
		
		return "redirect:list";
	}
}









