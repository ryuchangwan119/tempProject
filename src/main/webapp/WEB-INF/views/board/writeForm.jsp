<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h2>글 작성</h2>
<!-- modelAttribute="boardDTO" : DTO와 바인딩되어 연결된다. -->
<!-- required="require : required는 필수항목으로 지정되면 값이 없는경우 에러 메세지를 띄운다. -->
<form:form action="/board/writePro" method="post" modelAttribute="boardDTO" enctype="multipart/form-data">
	작성자	<form:input type="text" path="writer" required="required" /><br/>
			<form:errors path="writer" cssClass="error" />
			
	비밀번호	<form:input type="password" path="passwd" required="required" /><br/>
			<form:errors path="passwd" cssClass="error" />
	
	제목		<form:input type="text" path="title" required="required" /><br/>
			<form:errors path="title" cssClass="error" />
			
	본문		<form:textarea rows="10" cols="40" path="content" required="required"></form:textarea><br/>
			<form:errors path="content" cssClass="error" />
	파일		<form:input type="file" path="boardFile" multiple="multiple" />
			
			<input type="submit" value="글 작성">
</form:form>





