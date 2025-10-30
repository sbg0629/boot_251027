<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!--	<script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>-->
	<!-- <script src="${pageContext.request.contextPath}/js/jquery.js"></script> -->
	<script src="${contextPath}/js/jquery.js"></script>
	<script type="text/javascript">
		function fn_submit() {
			var formData = $("#frm").serialize();//form 요소 자체
			
			//비동기 전송방식의 jquery 함수
			$.ajax({
				 type:"post"
				,data:formData
				,url:"write"
				,success: function(data) {
					alert("저장완료");
					location.href="list";
				}
				,error: function() {
					alert("오류발생");
				}
			});
		}
	</script>
</head>
<body>
	<table width="500" border="1">
<!-- 		<form method="post" action="write"> -->
		<form id="frm">
			<tr>
				<td>이름</td>
				<td>
					<input type="text" name="boardName" size="50">
				</td>
			</tr>
			<tr>
				<td>제목</td>
				<td>
					<input type="text" name="boardTitle" size="50">
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>
					<textarea rows="10" name="boardContent"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
<!-- 					<input type="submit" value="입력"> -->
					<input type="button" onclick="fn_submit()" value="입력">
					&nbsp;&nbsp;
					<a href="list">목록보기</a>
				</td>
			</tr>
		</form>
	</table>

	File Attach
	<div class="uploadDiv">
		<input type="file" name="uploadFile" multiple>
	</div>

	<div class="uploadResult">
		<ul>

		</ul>
	</div>
</body>
</html>
<script>
	$(document).ready(function (e) {
		//확장자가 exe|sh|js 업로드 금지하기 위한 정규식
		var regex = new RegExp("(.*?)\.(exe|sh|js)$");
		// 파일크기(5MB 이하) 조건
		var maxSize = 5242880; //5MB

		function checkExtension(fileName, fileSize) {
			if (fileSize > maxSize) {
				alert("파일 사이즈 초과");
				return false;
			}
			if (regex.test(fileName)) {
				alert("해당 종류의 파일은 업로드할 수 없습니다.");
				return false;
			}
			return true;
		}

		$("input[type='file']").change(function (e) {
			console.log("@# files=>",this.files);
			console.log("@# length=>",this.files.length);

			var formData = new FormData();
			var files = this.files;

			for(var i=0; i<files.length; i++){
				console.log("@# i=>",i);
				console.log("@# name=>",files[i].name);
				console.log("@# size=>",files[i].size);

				alert("01");
				//파일크기와 종류중에서 거짓이면 리턴
				if (!checkExtension(files[i].name, files[i].size)) {
					return false;
				}

				alert("02");
				//파일 정보를 formData에 추가
				formData.append("uploadFile",files[i]);
				alert("03");
			}//end of for

			alert("11");
			$.ajax({
				 type: "post"
				,data: formData
				,url: "uploadAjaxAction"
				//processData : 기본은 key/value 를 Query String 으로 전송하는게 true
				//(파일 업로드는 false)
				,processData: false
				//contentType : 기본값 : "application / x-www-form-urlencoded; charset = UTF-8"
				//첨부파일은 false : multipart/form-data로 전송됨
				,contentType: false
				,success: function (result) {
					alert("Uploaded");
					console.log(result);
					//파일정보들을 함수로 보냄
					showUploadResult(result);//업로드 결과 처리 함수
				}
			});//end of ajax

			function showUploadResult(uploadResultArr) {
				console.log("@# uploadResultArr=>",uploadResultArr);
				console.log("@# uploadFile=>",uploadResultArr.uploadFile);

				if(!uploadResultArr || uploadResultArr.length == 0){
					return;
				}

				var uploadUL = $(".uploadResult ul");
				var str="";

				$(uploadResultArr).each(function (i,obj) {
					console.log("@# i=>",i);
					console.log("@# obj=>",obj);

					//image type
					if (obj.image) {
						console.log("@# uuid=>",obj.uuid);
						console.log("@# fileName=>",obj.fileName);

						//FileReader() : 파일리더객체는 파일객체로부터 바이너리를 읽어올 수 있는 객체
						reader = new FileReader();
						//파일 객체로부터 DATA-URL 형태로 바이너리를 읽어온다.
						reader.readAsDataURL(files[i]);

						//파일 리더가 파일을 다 읽으면 이후 내용을 함수의 기능으로 넣어준다.
						reader.onload=function (e) {
							console.log("@# e.target.result=>",e.target.result);

							$(".uploadResult ul").append("<li><div>"
								+"<span>"+obj.fileName+"</span>"
								+"<img src='"
								+e.target.result
								+"'>"
								+"</div></li>");
						}
					} else {
						str += "<li><div>";
						str += "<span>"+obj.fileName+"</span>"
						str += "<img src='${contextPath}/img/attach.png'>"
						str += "</div></li>";
					}
				});//end of each

				//div class 에 파일 목록 추가
				uploadUL.append(str);
			}//end of showUploadResult
		});//end of change
	});//end of ready
</script>








