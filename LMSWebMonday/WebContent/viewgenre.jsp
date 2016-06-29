<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="com.gcit.lms.service.AdministrativeService" %>
    <%@ page import="com.gcit.lms.domain.Genre" %>
    <%AdministrativeService service = new AdministrativeService(); 
    List<Genre> genres = new ArrayList<Genre>();
    genres= service.viewGenres();
    %>
<jsp:include page='include.html'></jsp:include>
<h3>List of Genres  <a href="admin.jsp"><input type="button" value="Cancel" name="cancel"/></a></h3>

<div class="row">
	<div class="col-md-6">
		<table class="table" id="genresTable">
			<thead>
				<tr>
					<th>Genre ID</th>
					<th>Genre Name</th>
	
					<th>Edit Genre</th>
					<th>Delete Genre</th>
				</tr>
			</thead>
			<tbody>
			<%
					for (Genre g: genres) {
				%>
				<tr>
					
					<td ><%=g.getGenre_id() %></td>
					<td ><%=g.getGenre_name() %></td>
					<td><button type="button" class="btn btn-sm btn-primary"  data-toggle="modal" data-target="#myModal1"
							href='editgenre.jsp?genreId=<%=g.getGenre_id()%>'">EDIT</button></td>
					<td><button type="button" class="btn btn-sm btn-danger" data-toggle="modal" data-target="#myModal1"
					href='deletegenre.jsp?genreId=<%=g.getGenre_id()%>'">DELETE</button></td>	
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