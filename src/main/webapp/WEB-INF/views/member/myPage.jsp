<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2>마이 페이지</h2>
<h3>회원번호 : ${dto.num}</h3>
<h3>아이디 : ${dto.id}</h3>
<h3>이름 : ${dto.name}</h3>
<h3>이메일 : ${dto.email}</h3>
<h3>가입일 : <fmt:formatDate value="${dto.regdate}" pattern="yyyy/MM/dd"/> </h3>

<button onclick="location.href='/member/home'">메인</button>
<button onclick="location.href='/member/myUpdateForm'">개인정보 수정</button>
<button onclick="location.href='/member/myDeleteForm'">회원 탈퇴</button>
