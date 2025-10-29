package com.boot.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpDeptDTO {
	//조인을 많이 하기 떄문에 아래와 같은 형식으로 많이 사용
	
	//emp table
	private int empno;
	private String ename;
	private String job;
	private int mgr;
	private Timestamp hiredate;
	private int sal;
	private int comm;
	
	//dept table
	private int deptno;
	private String dname;
	private String loc;
}
