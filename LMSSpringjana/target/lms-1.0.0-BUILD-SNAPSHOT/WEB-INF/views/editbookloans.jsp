<%@page import="java.util.Date"%>

<script>
$(document).ready(function()
		{
		    $('.modal').on('hidden.bs.modal', function(e)
		    { 
		        $(this).removeData();
		    }) ;
		});
</script>


<%@ page import="com.gcit.lms.domain.BookLoans"%>
<%
String BookId =request.getParameter("bookId");
String BranchId = request.getParameter("branchId");
String CardNo =request.getParameter("cardNo");
String DueDate = request.getParameter("dueDate");

BookLoans bookLoans = new BookLoans();
bookLoans.setBookId(Integer.parseInt(BookId));
bookLoans.setBranchId(Integer.parseInt(BranchId));
bookLoans.setCardNo(Integer.parseInt(CardNo));

%>
<%@ include file="include.html" %>


<h3>Hello Admin! Edit Author details</h3>
<body>
	<form action="extendDueDays" method="post">
	
how many more days you want to extend: <input type="text" name="extendDueDays" > 
<input type="hidden" name="bookId" value=<%=bookLoans.getBookId()%>>
<input type="hidden" name="branchId" value=<%=bookLoans.getBranchId()%>>
<input type="hidden" name="cardNo" value=<%=bookLoans.getCardNo()%>>
<button type="submit">Extend Due</button>
	</form>
	
	
</body>


</html>