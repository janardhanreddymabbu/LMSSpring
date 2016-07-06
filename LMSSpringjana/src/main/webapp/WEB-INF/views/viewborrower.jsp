<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="com.gcit.lms.service.AdministrativeService" %>
    <%@ page import="com.gcit.lms.domain.Borrower" %>
    <%AdministrativeService service = (AdministrativeService) request.getAttribute("service"); 
    List<Borrower> borrowers = new ArrayList<Borrower>();
    borrowers = service.viewBorrowers();
    %>
<%@ include file="include.html" %>
<h3>List of Borrowers  <a href="admin"><input type="button" value="Cancel" name="cancel"/></a></h3> 

<div class="row">
	<div class="col-md-6">
		<table class="table" id="borrowersTable">
			<thead>
				<tr>
					<th>Borrower Card NO</th>
	<th>Borrower Name</th>
	<th>Borrower Address</th>
	<th>Borrower Phone</th>
	<th>Edit Author</th>
	<th>Delete Author</th>
				</tr>
			</thead>
			<tbody>
			<%
					for (Borrower b: borrowers) {
				%>
				<tr>
					
					<td ><%=b.getCardNo() %></td>
			<td ><%=b.getName() %></td>
			<td ><%=b.getAddress() %></td>
			<td ><%=b.getPhone() %></td>
					<td><button type="button" class="btn btn-sm btn-primary"  data-toggle="modal" data-target="#myModal1"
							href='editborrower.jsp?CardNo=<%=b.getCardNo()%>'">EDIT</button></td>
					<td><button type="button" class="btn btn-sm btn-danger" data-toggle="modal" data-target="#myModal1"
					href='deleteborrower.jsp?CardNo=<%=b.getCardNo()%>'">DELETE</button></td>	
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
		
<div id="myModal1" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg">
		<div class="modal-content"></div>
	</div>
</div>





</html>