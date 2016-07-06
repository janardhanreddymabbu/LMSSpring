<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="com.gcit.lms.service.AdministrativeService" %>
    <%@ page import="com.gcit.lms.domain.Author" %>
    <%AdministrativeService service = (AdministrativeService) request.getAttribute("service");
	
    Integer count = service.getAuthorsCount();
	Integer pageCount = 0;
	if(count!=null && count >0){
		int rem = count % 10;
		if(rem == 0){
			pageCount = count/10;
		}else{
			pageCount = count/10+1;
		}
	}
	List<Author> authors = new ArrayList<Author>();
	if(request.getAttribute("authors")!=null){
		authors = (List<Author>) request.getAttribute("authors");	
	}else{
		authors = service.viewAuthors(1);	
	}


    %>
    
    <script>
	function editAuthor(authorId) {
		newWindow = window.open("editauthor.jsp", "test", "height=200, width = 500");
	}
	</script>
	
	<script>
	function pageAuthors(val){
		$.ajax({url: "pageAuthors",data: { pageNo: val},
			})
			  .done(function( data ) {
				 // console.log(data);
			    $('#authorsTable').html(data);
			  });
	}

	function searchAuthors(){
		$.ajax({url: "SearchPageAuthors",data: { searchString:$('#searchString').val()},
	 			})
				  .done(function( data ) {
			    $('#authorsTable').html(data);
			  });
	 	}

	
	</script>


<nav>
  <ul class="pagination">
    <%for(int i=1; i<=pageCount;i++){ %>
    <li><a id="pageNo" onclick="pageAuthors(<%=i%>)"><%=i%></a></li>
	<%} %>
  </ul>
</nav>
<form action="searchAuthor" >
		<input type="text"  id="searchString" placeholder="Enter Authorname" onkeyup="searchAuthors()"/>
	</form>

<%@ include file="include.html" %>
<h3>List of Authors  <a href="admin"><input type="button" value="Cancel" name="cancel"/></a></h3> 
<body>
${message}<br/>

<div class="row">
	<div class="col-md-6">
		<table class="table" id="authorsTable">
			<thead>
				<tr>
					<th>Author Name</th>
					<th>Edit Author</th>
					<th>Delete Author</th>
				</tr>
			</thead>
			<tbody>
			<%
					for (Author a : authors) {
				%>
				<tr>
					<td ><%=a.getAuthorName()%></td>
					<td><button type="button" class="btn btn-sm btn-primary"  data-toggle="modal" data-target="#myModal1"
							href='EditAuthor?authorId=<%=a.getAuthorId()%>'>EDIT</button></td>
					<td><button type="button" class="btn btn-sm btn-danger" data-toggle="modal" data-target="#myModal1"
					href='DeleteAuthor?authorId=<%=a.getAuthorId()%>'">DELETE</button></td>		
<%-- 					<td><button type="button" class="btn btn-sm btn-danger" onclick="javascript:location.href='DeleteAuthor?authorId=<%=a.getAuthorId()%>'">DELETE</button></td> --%>
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


	
	
	
	
	
</table></br>
</div>

</html>