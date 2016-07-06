<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="com.gcit.lms.service.AdministrativeService" %>
    <%@ page import="com.gcit.lms.domain.BookLoans" %>
    <%AdministrativeService service = (AdministrativeService) request.getAttribute("service"); 
    List<BookLoans> bookloans = new ArrayList<BookLoans>();
    String CardNo = request.getParameter("cardNo");
    bookloans = service.viewBookLoansByCardNo(CardNo);
    %>
<%@ include file="include.html" %>
<h3>List of books you checked out  <a href="borroweraction?cardNo=<%=CardNo %>"><input type="button" value="Cancel" name="cancel"/></a></h3> 
<div class="container">
<table class="table table-bordered-hover-condensed" >
	<tr>
	<th>Library ID</th>
	<th>Library  Name</th>
	<th>Book ID</th>
	<th>Book  Title</th>
	<th>Check out date</th>
	<th>Due date</th>
	<th>Check in</th>
	
	</tr>
	<%for(BookLoans b: bookloans){ %>
		<tr>
			<td align="center"><%=b.getBranchId() %></td>
			<td align="center"><%=b.getBranchname() %></td>
			<td align="center"><%=b.getBookId()%></td>
			<td align="center"><%=b.getBooktitle() %></td>
			<td align="center"><%=b.getDateOut() %></td>
			<td align="center"><%=b.getDueDate() %></td>
			<td align="center"><button type="button" onclick="javascript:location.href='checkIn?branchId=<%=b.getBranchId()%>&bookId=<%=b.getBookId()%>&cardNo=<%=b.getCardNo()%>'">Check in</button></td>
							   
			
			</tr>
	<%} %>
	
	
	
	
	
</table></br>
</div>

</html>