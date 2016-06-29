<jsp:include page='include.html'></jsp:include>
<h3>Hello Borrower! Enter card number</h3>
<body>
${message}<br/>
	<form action="borrowerLogin" method="post">
Edit Card No: <input type="text" name="cardNo" required> <br/>

<% String CardNo =request.getParameter("cardNo");
    %>

<button type="submit">Go!</button>    

	</form>
