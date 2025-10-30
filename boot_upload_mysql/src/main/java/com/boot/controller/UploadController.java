package com.boot.controller;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.boot.dto.BoardAttachDTO;
import com.boot.dto.BoardDTO;
import com.boot.dto.CommentDTO;
import com.boot.dto.MemDTO;
import com.boot.service.BoardService;
import com.boot.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UploadController {
	
//	@Autowired
//	private BoardService service;
	
//	이미지 여부 체크
	public boolean checkImageType(File file) {
		try {
//			이미지파일인지 체크하기 위한 타입(probeContentType)
			String contentType = Files.probeContentType(file.toPath());
			log.info("@# contentType=>"+contentType);
			
//			startsWith : 파일종류 판단
			return contentType.startsWith("image");//참이면 이미지파일
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;//거짓이면 이미지파일이 아님
	}
	
	@PostMapping("/uploadAjaxAction")
	public ResponseEntity<List<BoardAttachDTO>> uploadAjaxPost(MultipartFile[] uploadFile) {
		log.info("@# uploadAjaxPost()");
		
		List<BoardAttachDTO> list = new ArrayList<BoardAttachDTO>();
		
		for (MultipartFile multipartFile : uploadFile) {
			log.info("==============================)");
			log.info("@# 업로드 되는 파일 이름=>"+multipartFile.getOriginalFilename());
			log.info("@# 업로드 되는 파일 크기=>"+multipartFile.getSize());
			
			String uploadFileName = multipartFile.getOriginalFilename();
			
			UUID uuid = UUID.randomUUID();
			log.info("@# uuid=>"+uuid);
			
			BoardAttachDTO boardAttachDTO = new BoardAttachDTO();
			boardAttachDTO.setFileName(uploadFileName);
			boardAttachDTO.setUuid(uuid.toString());
			log.info("@# boardAttachDTO=>"+boardAttachDTO);
			
			File saveFile = new File(uploadFileName);
			
//			참이면 이미지 파일
			if (checkImageType(saveFile)) {
				boardAttachDTO.setImage(true);
			}
			list.add(boardAttachDTO);
		}
		
		return new ResponseEntity<List<BoardAttachDTO>>(list, HttpStatus.OK);
	}
	
}
















