<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="com.gcit.lms.service.AdministrativeService" %>
    <%@ page import="com.gcit.lms.domain.LibraryBranch" %>
    <%AdministrativeService service = new AdministrativeService(); 
    List<LibraryBranch> LibraryBranchs = new ArrayList<LibraryBranch>();
    LibraryBranchs = service.viewLibraryBranchs();
    %>
<%@ include file="include.html" %>

<h3>List of LibraryBranchs <a href="admin.jsp"><input type="button" value="go back!" name="cancel"/></a></h3> 
<body>
${message}<br/>



<div class="row">
	<div class="col-md-6">
		<table class="table" id="adminLibraryTable">
			<thead>
				<tr>
					<th>Branch ID</th>
	<th>Branch Name</th>
	<th>Branch Address</th>


	<th>Delete Library Branch</th>
				</tr>
			</thead>
			<tbody>
			<%
					for (LibraryBranch b: LibraryBranchs) {
				%>
				<tr>
					
					<td ><%=b.getBranchId() %></td>
			<td ><%=b.getBranchName() %></td>
			<td ><%=b.getBranchAddress() %></td>
					<td><button type="button" class="btn btn-sm btn-danger" data-toggle="modal" data-target="#myModal1"
					href='admindeleteLibraryBranch.jsp?BranchId=<%=b.getBranchId()%>'">DELETE</button></td>	
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




