<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="com.gcit.lms.service.AdministrativeService" %>
    <%@ page import="com.gcit.lms.domain.Publisher" %>
    <%AdministrativeService service = new AdministrativeService(); 
    List<Publisher> publishers = new ArrayList<Publisher>();
    publishers = service.viewPublishers();
    %>
    
     <script>
	function editPublisher(authorId) {
		newWindow = window.open("editpublisher.jsp", "test", "height=200, width = 500");
	}
	</script>
<%@ include file="include.html" %>
<h3>List of Publishers  <a href="admin.jsp"><input type="button" value="Cancel" name="cancel"/></a></h3>
<div class="row">
	<div class="col-md-6">
		<table class="table" id="publishersTable">
			<thead>
				<tr>
					<th>Publisher Name</th>
					<th>Publisher Address</th>
					<th>Publisher Phone</th>
					<th>Edit Publisher</th>
					<th>Delete Publisher</th>
				</tr>
			</thead>
			<tbody>
			<%
					for (Publisher p: publishers) {
				%>
				<tr>
					
					<td ><%=p.getPublisherName() %></td>
					<td ><%=p.getPublisherAddress() %></td>
					<td ><%=p.getPublisherPhone() %></td>
					<td><button type="button" class="btn btn-sm btn-primary"  data-toggle="modal" data-target="#myModal1"
							href='editPublisher?publisherId=<%=p.getPublisherId()%>'">EDIT</button></td>
					<td><button type="button" class="btn btn-sm btn-danger" data-toggle="modal" data-target="#myModal1"
					href='deletePublisher?publisherId=<%=p.getPublisherId()%>'">DELETE</button></td>	
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