package com.boot.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.dto.MemDTO;
import com.boot.service.MemService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MemController {
	
//	servlet-context 에 있는 sqlSession 객체 연결
//	@Autowired
//	private SqlSession sqlSession;
	@Autowired
	private MemService service;
	
//	로그인 화면 이동
	@RequestMapping("/login")
	public String login() {
		log.info("@# login()");
		
		return "login";
	}
	
//	로그인화면->로그인 여부 판단
	@RequestMapping("/login_yn")
	public String login_yn(@RequestParam HashMap<String, String> param, Model model) {
		log.info("@# login_yn()");
		
//		model.addAttribute("request", request);
		
//		MemDAO dao = sqlSession.getMapper(MemDAO.class);
//		
//		ArrayList<MemDTO> dtos = 
//				dao.loginYn(request.getParameter("mem_uid")
//						  , request.getParameter("mem_pwd"));
		ArrayList<MemDTO> dtos = service.loginYn(param);
		
		if (dtos.isEmpty()) {
			return "login";
		} else {
//			if (request.getParameter("mem_pwd").equals(dtos.get(0).getMem_pwd())) {
			if (param.get("mem_pwd").equals(dtos.get(0).getMem_pwd())) {
				return "login_ok";
			} else {
				return "login";
			}
		}
	}

//	등록 화면 이동
	@RequestMapping("/register")
	public String register() {
		log.info("@# register()");
		
		return "register";
	}
	
	@RequestMapping("/registerOk")
	public String registerOk(@RequestParam HashMap<String, String> param, Model model) {
		log.info("@# registerOk()");
		
//		model.addAttribute("request", request);
//		
//		MemDAO dao = sqlSession.getMapper(MemDAO.class);
//		dao.write(request.getParameter("mem_uid")
//				, request.getParameter("mem_pwd")
//				, request.getParameter("mem_name"));
		service.write(param);
		
		return "login";
	}
}









