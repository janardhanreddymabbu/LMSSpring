<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="com.gcit.lms.service.AdministrativeService" %>
    <%@ page import="com.gcit.lms.domain.LibraryBranch" %>
    <%AdministrativeService service = new AdministrativeService(); 
   
    Integer count = service.getLibraryBranchsCount();
	Integer pageCount = 0;
	if(count!=null && count >0){
		int rem = count % 10;
		if(rem == 0){
			pageCount = count/10;
		}else{
			pageCount = count/10+1;
		}
	}
	List<LibraryBranch> LibraryBranchs = new ArrayList<LibraryBranch>();
	if(request.getAttribute("LibraryBranchs")!=null){
		LibraryBranchs = (List<LibraryBranch>)request.getAttribute("LibraryBranchs");	
	}else{
		LibraryBranchs = service.viewLibraryBranchs(1);	
	}
    
    
    
    %>
    
    <script>
	function pageLibraryBranchs(val){
		$.ajax({url: "pageLibraryBranchs",data: { pageNo: val},
			})
			  .done(function( data ) {
			    $('#viewlibrarybranchtabelsTable').html(data);
			  });
	}

	</script>
	
	<nav>
  <ul class="pagination">
    <%for(int i=1; i<=pageCount;i++){ %>
    <li><a id="pageNo" onclick="pageLibraryBranchs(<%=i%>)"><%=i%></a></li>
	<%} %>
  </ul>
</nav>
<form action="searchLibraryBranch" method="post">
		<input type="text" name="searchString" id="searchString" placeholder="Enter LibraryName" onkeypress="searchLibraryBranchs()"/>
	</form>
<%@ include file="include.html" %>

<h3>List of LibraryBranchs <a href="librarian.jsp"><input type="button" value="go back!" name="cancel"/></a></h3> 
<body>
${message}<br/>
<div class="row">
	<div class="col-md-9">
		<table class="table" id="viewlibrarybranchtabelsTable">
			<thead>
				<tr>
					
	<th>Branch Name</th>
	<th>Branch Address</th>
	<th>Update Library Details</th>
	<th>Add new books/Copies</th>
	<th>Manage Book Copies</th>
				</tr>
			</thead>
			<tbody>
			<%
					for (LibraryBranch b: LibraryBranchs) {
				%>
				<tr>
					
					
			<td ><%=b.getBranchName() %></td>
			<td ><%=b.getBranchAddress() %></td>
					<td><button type="button" class="btn btn-sm btn-primary"  data-toggle="modal" data-target="#myModal1"
							href='editLibrarybranch?BranchId=<%=b.getBranchId()%>'">Update Library Details</button></td>
					<td><button type="button" class="btn btn-sm btn-primary"  data-toggle="modal" data-target="#myModal1"
							href='addBookcopies?BranchId=<%=b.getBranchId()%>'">Add new books/Copies</button></td>		
					<td ><button type="button" class="btn btn-sm btn-primary" 
					onclick="javascript:location.href='editbookcopies.jsp?BranchId=<%=b.getBranchId()%>'">Manage Book Copies</button></td>	
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






