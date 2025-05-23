<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script
  src="https://code.jquery.com/jquery-3.7.1.min.js"
  integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
  crossorigin="anonymous">
</script>


<h1>/ajax/ajaxDTOList.jsp</h1>

<h2>Ajax List로 매개변수 받기(GET, POST)</h2>

<!--  버튼 클릭시 sendGet() 함수 호출 -->
<button onclick="sendGetDTOList()">GetDTOList 요청 보내기</button>
<button onclick="sendPostDTOList()">PostDTOList 요청 보내기</button>

<!-- div태그에 입력값 출력 -->
<div id="result" style="margin-top:20px; padding:10px; border:1px solid #ccc;"></div>

<script>
	function sendPostDTOList(){
		$.ajax({
			type: "post" ,
			url: "/ajax/postDTOList" ,
			dataType: "json" ,	/* data를 json으로 설정시 배열로 파싱 */
			
			success: function(response) {
				var output = "";
				for(var i = 0; i < response.length; i++) {
					output += "msg : " + response[i].msg + ", extra : "  + response[i].extra + "<br/>";
				}
				$("#result").html(output)
			},
			
			error: function(xhr, status, error) {
				$("#result").html("POST_DTO_LIST Err : " + error)
			}
		});
	};
</script>

<script>
	function sendGetDTOList(){
		$.ajax({
			type: "get" ,
			url: "/ajax/getDTOList" ,
			dataType: "json" ,	/* data를 json으로 설정시 배열로 파싱 */
			
			success: function(response) {
				var output = "";
				for(var i = 0; i < response.length; i++) {
					output += "msg : " + response[i].msg + ", extra : "  + response[i].extra + "<br/>";
				}
				$("#result").html(output)
			},
			
			error: function(xhr, status, error) {
				$("#result").html("GET_DTO_LIST Err : " + error)
			}
		});
	};
</script>




















