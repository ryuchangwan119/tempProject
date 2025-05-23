<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script
	src="https://code.jquery.com/jquery-3.7.1.min.js"
  	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
  	crossorigin="anonymous">
</script>



<c:if test="${not empty dto}">
	<table border="1" width="700">
		<tr>
			<th>제목</th>
			<td>${dto.title}</td>
		</tr>
		
		<tr>
			<th>글 번호</th>
			<td>${dto.id}</td>
		</tr>
	
		<tr>
			<th>작성자</th>
			<td>${dto.writer}</td>
		</tr>	
	
		<tr>
			<th>내용</th>
			<td>${dto.content}</td>
		</tr>

		<tr>
			<th>조회수</th>
			<td>${dto.readCount}</td>
		</tr>
		
		<tr>
			<th>작성일</th>
			<td><fmt:formatDate value="${dto.boardWriteTime}" pattern="yyyy/MM/dd hh:mm:ss"/></td>
		</tr>
		
		<c:if test="${not empty fileList}">
			<tr>
				<th>첨부파일</th>
				<td>
				<table>
					<c:forEach var="file" items="${fileList}">
						<tr>
							<td>
								<img src="/upload/${file.uploadFileName}" width="300" >
							</td>
						</tr>
					</c:forEach>
				</table>
				</td>
			</tr>
		</c:if>
	</table>
</c:if>

<div id="comment-write">
	<input type="text" id="commentWriter" placeholder="작성자" />
	<input type="text" id="commentContent" placeholder="댓글 내용" />
	<button id="comment-write-btn" onclick="commentWrite()">댓글 작성</button>
</div>

<div id="comment-list" >
</div>

<script>
	function loadComment() {	// HTML이 상세페이지를 모두 로딩한 후 실행되는 함수 (댓글 불러오기)
		const writer = document.getElementById("commentWriter").value;
		const contents = document.getElementById("commentContent").value;
		const id = ${dto.id}
		console.log("작성자 : ", writer);
		console.log("댓글 : ", contents);
		console.log("글 번호 : ", id);
		$.ajax({
			type: "post",
			url: "/comment/save" ,
			data: {
				"writer" : writer,
				"contents" : contents,
				"boardId" : id
			},
			success: function(response) {
				var output = "";
				for(var i=0; i < response.length; i++) {
					output += "<p> 작성자 :" + response[i].writer + "</p>";
					output += "<p> 댓글 :" + response[i].contents + "</p>";
					output += "---------------------------------------------------";
				}
				document.getElementById("comment-list").innerHTML = output;
				
				// 입력 필드 초기화
				document.getElementById("commentWriter").value="";
				document.getElementById("commentContent").value="";
			}, 
			error: function(error) {
				console.log("실패", error)
			}
		});
	}
	
	function commentWrite() {	// 댓글 작성 후 작성한 글을 포함한 댓글을 다시 불러오는 함수
		const writer = document.getElementById("commentWriter").value;
		const contents = document.getElementById("commentContent").value;
		const id = ${dto.id}
		console.log("작성자 : ", writer);
		console.log("댓글 : ", contents);
		console.log("글 번호 : ", id);
		$.ajax({
			type: "post",
			url: "/comment/save" ,
			data: {
				"writer" : writer,
				"contents" : contents,
				"boardId" : id
			},
			success: function(response) {
				var output = "";
				for(var i=0; i < response.length; i++) {
					output += "<p> 작성자 :" + response[i].writer + "</p>";
					output += "<p> 댓글 :" + response[i].contents + "</p>";
					output += "---------------------------------------------------";
				}
				document.getElementById("comment-list").innerHTML = output;
				
				// 입력 필드 초기화
				document.getElementById("commentWriter").value="";
				document.getElementById("commentContent").value="";
			}, 
			error: function(error) {
				console.log("실패", error)
			}
		});
	}
	
	
	$(document).ready(function() {
		loadComment();
	});
	
</script>


<button onclick="location.href='/board/list?page=${page}'">글목록</button>
<button onclick="location.href='/board/update?id=${dto.id}&page=${page}'">글수정</button>
<button onclick="location.href='/board/delete?id=${dto.id}&page=${page}'">글삭제</button>
























