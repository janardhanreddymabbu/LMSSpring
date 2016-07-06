<script>
$(document).ready(function()
		{
		    $('.modal').on('hidden.bs.modal', function(e)
		    { 
		        $(this).removeData();
		    }) ;
		});
</script>



<%@page import="com.gcit.lms.domain.Book"%>
<%@ page import="java.util.List" %>
<%@ page import="com.gcit.lms.service.AdministrativeService" %>
  <%@ page import="java.util.ArrayList" %>
<%@page import="com.gcit.lms.domain.LibraryBranch"%>
<%@ page import="com.gcit.lms.domain.Author"%>
<%
AdministrativeService service = (AdministrativeService) request.getAttribute("service"); 
String BranchId = request.getParameter("BranchId");
List<Book> books = new ArrayList<Book>();
books= service.viewBooksnotinbookcopies(Integer.parseInt(BranchId));
	LibraryBranch lb = (LibraryBranch) request.getAttribute("librarybranch");

%>


<%@ include file="include.html" %>
<h3>Hello Librarian! Add new books to libarary</h3>
<body>
	<form action="addBookcopies" method="post">
		<input type="hidden"name="branchId" value=<%=lb.getBranchId()%>>
		
		You are adding new books for the Branch: <%=lb.getBranchName()%>,<%=lb.getBranchAddress()%></br>
		Select Book Name:<select name="selectedBookName"required>
     <%for (Book b: books) {%>
	  
	  <option value="<%=b.getBookId()%>"><%=b.getTitle()%></option>
	  <%} %>
</select></br>

Enter no of copies: <input type="text" name="noOfCopies"required></br>

		<button type="submit"> Add Book Copies</button>
</form>

</body>
</html>
	
