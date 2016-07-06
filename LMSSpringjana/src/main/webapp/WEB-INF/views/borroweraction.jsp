<%@page import="com.gcit.lms.service.AdministrativeService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="com.gcit.lms.domain.Borrower"%>
    <%
    String cardno= request.getParameter("cardNo");
    AdministrativeService service = (AdministrativeService) request.getAttribute("service");
    Borrower b = new Borrower();
    b=service.viewBorrowerByCardNo(cardno);
%>
<%@ include file="include.html" %>
<h2>You can check In or check Out Books!</h2>
<h3>Hello <%=b.getName()%>!! </h3>
<body>
<input type="hidden" name="CardNo" value=<%=b.getCardNo()%>>
<button type="button" onclick="javascript:location.href='viewLibraries1?cardNo=<%=b.getCardNo()%>'">Check Out books</button></td>
<button type="button" onclick="javascript:location.href='checkin?cardNo=<%=b.getCardNo()%>'">Check In books</button></td>
 
</body>
</html>