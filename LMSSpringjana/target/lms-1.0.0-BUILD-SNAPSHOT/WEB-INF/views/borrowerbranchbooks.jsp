<script>
$(document).ready(function()
		{
		    $('.modal').on('hidden.bs.modal', function(e)
		    { 
		        $(this).removeData();
		    }) ;
		});
</script>

<%@page import="com.gcit.lms.domain.Borrower"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="com.gcit.lms.service.AdministrativeService" %>
    <%@ page import="com.gcit.lms.domain.BookCopies" %>
    <%AdministrativeService service = new AdministrativeService(); 
    String BranchId = request.getParameter("BranchId");
    String CardNo = request.getParameter("CardNo");
    Borrower br = new Borrower();
    br =service.viewBorrowerByCardNo(CardNo);
    List<BookCopies> bookcopies = new ArrayList<BookCopies>();
    bookcopies = service.viewBookCopiesbybranchIdavailable(Integer.parseInt(BranchId),Integer.parseInt(CardNo));
    
    %>
<%@ include file="include.html" %>
<h3>welcome<%=br.getName() %>!! </h3>
<h4>List of Books  <a href="borroweraction.jsp?cardNo=<%=br.getCardNo() %>"><input type="button" value="Cancel" name="cancel"/></a></h4>
<body>
${message}<br/>
<div class="container">
<table class="table table-bordered-hover-condensed" >
	<tr>
<!-- 	<th>Book ID</th> -->
	<th>Title</th>
<!-- 	<th>Branch ID</th> -->
	<th>Branch Name</th>
	
	
	<th>Checkout Copies</th>
	
	</tr>
	<%for(BookCopies b: bookcopies){ %>
		<tr>
<%-- 			<td align="center"><%=b.getBookId() %></td> --%>
			<td align="center"><%=b.getBookTitle() %></td>
<%-- 			<td align="center"><%=b.getBranchId() %></td> --%>
			<td align="center"><%=b.getBranchName() %></td>
	
			<td align="center"><button type="button" onclick="javascript:location.href='checkOut?bookId=<%=b.getBookId()%>&branchId=<%=b.getBranchId()%>&cardNo=<%=br.getCardNo()%>'">Check out</button></td>
			
			</tr>
	<%} %>
	
<!-- 	<select multiple="multiple" name="selectedAuthors"> -->
<%-- 	<%for(Publisher p: publishers){ %> --%>
<%-- 		<option value=<%=p.getPublisherId() %>><%=p.getPublisherName() %></option> --%>
<%-- 	<%} %> --%>
<!-- 	</select> -->
	
</table></br>
</div>

</html>
</body>
