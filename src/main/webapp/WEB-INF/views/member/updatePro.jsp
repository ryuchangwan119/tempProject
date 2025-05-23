<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 세션이 없으면 로그인 페이지로 이동 -->
<c:if test="${empty sessionScope.memId}">
	<script>
		location.href='/member/loginForm';
	</script>
</c:if>

<!-- 세션이 있는 상태에서 result값이 0보다 크면 리스트로 이동 -->
<c:if test="${not empty sessionScope.memId and result > 0}">
	<script>
		location.href='/member/memberList';
	</script>
</c:if>

<!-- 세션이 있는 상태에서 result값이 0과 같거나 작으면 이전 페이지로 이동 -->
<c:if test="${not empty sessionScope.memId and result <= 0}">
	<script>
		alert("회원 정보가 수정되지 않았습니다.");
		history.go(-1);
	</script>
</c:if>