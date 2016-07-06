<%@page import="com.gcit.lms.domain.Borrower"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="com.gcit.lms.service.AdministrativeService" %>
    <%@ page import="com.gcit.lms.domain.LibraryBranch" %>
    <%AdministrativeService service = (AdministrativeService) request.getAttribute("service"); 
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
    
    <%
	Borrower b = (Borrower) request.getAttribute("borrower");
%>

 <script>
	function pageLibrary1Branchs(val){
		var myNewVariable = '${cardNo}';
		
		$.ajax({
			url: "pageLibrary1Branchs",
			data: { pageNo: val,
					cardNo: myNewVariable
							
			},
			})
			  .done(function( data ) {
			    $('#viewLibraries1Table').html(data);
			  });
	}

	</script>
	
	<nav>
  <ul class="pagination">
    <%for(int i=1; i<=pageCount;i++){ %>
    <li><a id="pageNo" onclick="pageLibrary1Branchs(<%=i%>)"><%=i%></a></li>
	<%} %>
	
  </ul>
</nav>

<%@ include file="include.html" %>
<h3>welcome<%=b.getName() %>!! </h3>
<h4>List of LibraryBranchs <a href="borroweraction?cardNo=<%=b.getCardNo() %>"><input type="button" value="go back!" name="cancel"/></a></h4> 
<body>
${message}<br/>
<input type="hidden" name="CardNo" value=<%=b.getCardNo()%>>

<div class="row">
	<div class="col-md-6">
		<table class="table" id="viewLibraries1Table">
			<thead>
				<tr>
					
					<th >Branch Name</th>
	
					<th >Check out Books</th>
				</tr>
			</thead>
			<tbody>
			<%
					for (LibraryBranch l: LibraryBranchs) {
				%>
				<tr>
					
					<td ><%=l.getBranchName() %></td>
					<td><button type="button" class="btn btn-sm btn-primary"  
							onclick="javascript:location.href='borrowerbranchbooks?BranchId=<%=l.getBranchId()%>&CardNo=<%=b.getCardNo()%>'">Checkout</button></td>

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




