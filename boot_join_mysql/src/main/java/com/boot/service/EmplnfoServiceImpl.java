package com.boot.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.EmpDeptDAO;
import com.boot.dto.EmpDeptDTO;



@Service
public class EmplnfoServiceImpl implements EmplnfoService{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public ArrayList<EmpDeptDTO> list() {
		EmpDeptDAO dao = sqlSession.getMapper(EmpDeptDAO.class);
		ArrayList<EmpDeptDTO> list = dao.list();
		return list;
	}

	
}




