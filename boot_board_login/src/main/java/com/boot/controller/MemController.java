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
//	public String login_yn(@RequestParam HashMap<String, String> param, Model model) {
	public String login_yn(@RequestParam HashMap<String, String> param, HttpServletRequest request) {
		log.info("@# login_yn()");
		log.info("@# param => "+param);
		
		HttpSession session = request.getSession(); //세션
		
		MemDTO dto = new MemDTO(param.get("mem_uid"), param.get("mem_pwd"), param.get("mem_name")); //가져와서 56줄에 넣음
		ArrayList<MemDTO> dtos = service.loginYn(param);
		MemDTO mdto = null;
		
		if (dtos.isEmpty()) {
			return "login";
		} else {
			if (param.get("mem_pwd").equals(dtos.get(0).getMem_pwd())) {
//				return "login_ok";
				session.setAttribute("LOGIN_MEMBER",dto);
				mdto = (MemDTO) session.getAttribute("LOGIN_MEMBER"); // 세션
				log.info("@# mdto111 => "+ mdto);
//				return "login_ok";
				return "redirect:list";
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
		
		service.write(param);
		
		return "login";
	}
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		log.info("@# logout()");
		
		HttpSession session = request.getSession();
		session.invalidate(); //세션 초기화
		
		return "login";
	}
}









