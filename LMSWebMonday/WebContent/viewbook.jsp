<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="com.gcit.lms.service.AdministrativeService" %>
    <%@ page import="com.gcit.lms.domain.Book" %>
    <%@ page import="com.gcit.lms.domain.Author" %>
    <%@ page import="com.gcit.lms.domain.Genre" %>
    <%@ page import="com.gcit.lms.domain.Publisher" %>
    <%AdministrativeService service = new AdministrativeService(); 
    Integer count = service.getBooksCount();
	Integer pageCount = 0;
	if(count!=null && count >0){
		int rem = count % 10;
		if(rem == 0){
			pageCount = count/10;
		}else{
			pageCount = count/10+1;
		}
	}
    
    
    
    List<Book> books = new ArrayList<Book>();
    if(request.getAttribute("books")!=null){
    	books = (List<Book> )request.getAttribute("books");	
	}else{
		books = service.viewBooks(1);	
	}
    
    
    %>
    <script>
	function pageBooks(val){
		$.ajax({url: "pageBooks",data: { pageNo: val},
			})
			  .done(function( data ) {
			    $('#booksTable').html(data);
			  });
	}
	
	function searchBooks(){
		$.ajax({url: "SearchPageBooks",data: { searchString:$('#searchString').val()},
	 			})
				  .done(function( data ) {
			    $('#booksTable').html(data);
			  });
	 	}

	</script>
	
	<nav>
  <ul class="pagination">
    <%for(int i=1; i<=pageCount;i++){ %>
    <li><a id="pageNo" onclick="pageBooks(<%=i%>)"><%=i%></a></li>
	<%} %>
  </ul>
</nav>

<form action="searchBook" >
		<input type="text"  id="searchString" placeholder="Enter Book name" onkeypress="searchBooks()"/>
	</form>
<jsp:include page='include.html'></jsp:include>
<h3>List of Books  <a href="admin.jsp"><input type="button" value="Cancel" name="cancel"/></a></h3>
<body>
${message}<br/>

<div class="row">
	<div class="col-md-9">
		<table class="table" id="booksTable">
			<thead>
				<tr>
					
					<th>Book Title</th>
					<th>Publisher Name</th>
					<th>Author's Name</th>
					<th>Genre's Name</th>
					<th>Edit Book</th>
					<th>Delete Book</th>
				</tr>
			</thead>
			<tbody>
			<%
					for (Book b: books) {
				%>
				<tr>
					
					<td ><%=b.getTitle()%></td>
					<td ><%=b.getPublisher().getPublisherName()%></td>
					<td ><%if(b.getAuthors()!=null && b.getAuthors().size() >0){
				for(Author a: b.getAuthors()){
					out.println(a.getAuthorName()+",");
					
				}
						}%></td>
			
			<td ><%if(b.getGenres()!=null && b.getGenres().size() >0){
				for(Genre g: b.getGenres()){
					out.println(g.getGenre_name()+",");
					
				}
						}%></td>
					
					<td><button type="button" class="btn btn-sm btn-primary"  data-toggle="modal" data-target="#myModal1"
							href='editBook?bookId=<%=b.getBookId()%>'>EDIT</button></td>
					<td><button type="button" class="btn btn-sm btn-danger" data-toggle="modal" data-target="#myModal1"
					href='deleteBook?bookId=<%=b.getBookId()%>'">DELETE</button></td>		
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




</body>
</html>