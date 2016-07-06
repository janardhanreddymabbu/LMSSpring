<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="com.gcit.lms.service.AdministrativeService" %>
    <%@ page import="com.gcit.lms.domain.BookCopies" %>
    
     <%AdministrativeService service = new AdministrativeService(); 
    String BookId =request.getParameter("bookId");
    
     String BranchId = request.getParameter("branchId");
     String NoofCopies = request.getParameter("noofCopies");
    BookCopies bookcopies = new BookCopies();
   // bookcopies= service.viewBookCopiesbybookIdbranchId(Integer.parseInt(BookId),Integer.parseInt(BranchId));
    %>
<%@ include file="include.html" %>
<h3>Hello Librarian! Update Book Copies</h3>
<body>
<form action="managecopiesbyBranchIdBookId" method="post">
<input type="hidden"name="bookId" value=<%=BookId%>>
<input type="hidden"name="branchId" value=<%=BranchId%>>

Enter New Book Copies: <input type="text" name="NoOfCopies"  value ="<%=NoofCopies%>" required>
<button type="submit">Update Copies</button>

</form>
