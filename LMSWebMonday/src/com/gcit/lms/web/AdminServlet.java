package com.gcit.lms.web;

import java.io.IOException;
import java.security.Provider.Service;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.problem.ShouldNotImplement;
import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.BookCopies;
import com.gcit.lms.domain.BookLoans;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.Genre;
import com.gcit.lms.domain.LibraryBranch;
import com.gcit.lms.domain.Publisher;
import com.gcit.lms.service.AdministrativeService;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet({ "/addAuthor", "/addGenre" ,"/addPublisher","/addBook","/EditAuthor","/DeleteAuthor","/editPublisher",
	"/deletePublisher","/editBook","/deleteBook","/pageAuthors","/pageLibraryBranchs","/SearchPageAuthors","/pageLibrary1Branchs","/pageBooks","/SearchPageBooks" ,"/extendDueDays",
	"/addBorrower","/addLibrarybranch","/editLibrarybranch","/addBookcopies","/updateBookcopies",
	"/managecopiesbyBranchIdBookId","/borrowerLogin","/viewLibraries1","/checkOut","/checkIn"})
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public AdminServlet() {
		super();
		// TODO Auto-generated constructor stub
		}
	AdministrativeService service = new AdministrativeService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mapping = request.getRequestURI().substring(request.getContextPath().length(), request.getRequestURI().length());
		String forwardPath = "index.html";
		

		switch (mapping) {
		case "/EditAuthor":
			forwardPath = prepareEditAuthor(request);
			break;
//		case "/pageAuthors":
//			pageAuthors(request);
//			forwardPath = "viewauthors.jsp";
//			break;
			
		case "/pageAuthors":
		pageAuthors(request, response);
		forwardPath = null;
			break;
		case "/pageLibraryBranchs":
			pageLibraryBranchs(request, response);
			forwardPath = null;
				break;
				
		case "/SearchPageAuthors":
			SearchPageAuthors(request, response,request.getParameter("searchString"));
			forwardPath = null;
				break;
		case "/SearchPageBooks":
			SearchPageBooks(request, response,request.getParameter("searchString"));
			forwardPath = null;
				break;		
				
		case "/pageLibrary1Branchs":
			pageLibrary1Branchs(request, response);
			forwardPath = null;
				break;	
			
		case "/pageBooks":
			pageBooks(request, response);
			forwardPath = null;
				break;	
			
			
		case "/editPublisher":
			forwardPath = prepareeditPublisher(request);
			break;
			
		case "/editBook":
			forwardPath = prepareeditBook(request);
			break;	
			
		case "/checkOut":
			forwardPath = checkOut(request);
			break;
			
		case "/checkIn":
			forwardPath = checkIn(request);
			break;
		case "/editLibrarybranch":
			System.out.println(" in edit librarybanch");
			forwardPath = prepareeditLibrarybranch(request);
			break;
			
		case "/DeleteAuthor":
			forwardPath = prepareDeleteAuthor(request);
			break;
		
		case "/deleteBook":
			forwardPath = preparedeleteBook(request);
			break;
			
			
		case "/deletePublisher":
			forwardPath = preparedeletePublisher(request);
			break;	
			
			
		case "/viewLibraries1":
			forwardPath = prepareviewLibraries1(request);
			break;
	
		case "/addBookcopies":
			
			forwardPath = prepareaddBookcopies(request);
			break;
			
//         case "/updateBookcopies":
//			
//			forwardPath = prepareupdateBookcopies(request);
//			break;
			
		default:
			break;
		}
		
		if (!(forwardPath==null)){
		RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);
		}
	}
	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mapping = request.getRequestURI().substring(request.getContextPath().length(), request.getRequestURI().length());
		String forwardPath = "index.html";
		switch (mapping) {
		case "/addAuthor":
		forwardPath = addAuthor(request);
				
			
			break;
			
		
		
		case "/addGenre":
			forwardPath = addGenre(request);
				
			
			break;
		case "/addPublisher":
			forwardPath = addPublisher(request);
			
		
		break;
		
		case "/addBook":
			forwardPath = addBook(request);
			
		
		break;
		
		case "/editBook":
			forwardPath = editBook(request);
			
		
		break;
		
		case "/addBookcopies":
			forwardPath = addBookcopies(request);
			
		
		break;
		
		
		
		case "/EditAuthor":
			forwardPath = EditAuthor(request);
			
		
		break;
		
		
		case "/editPublisher":
			forwardPath = editPublisher(request);
			
		
		break;
		
		case "/editLibrarybranch":
			forwardPath = editLibrarybranch(request);
			
		
		break;
		case "/DeleteAuthor":
			
			
			forwardPath = DeleteAuthor(request);
			
		
		break;
		
     case "/deleteBook":
			
			
			forwardPath = deleteBook(request);
			
		
		break;
		
		
		
        case "/deletePublisher":
			
			
			forwardPath = deletePublisher(request);
			
		
		break;
		
		case "/addBorrower":
			forwardPath = addBorrower(request);
			
		
		break;
		
		case "/addLibrarybranch":
			forwardPath = addLibrarybranch(request);
			
		
		break;
		
		case "/managecopiesbyBranchIdBookId":
			forwardPath = managecopiesbyBranchIdBookId(request);
			
		
		break;
		
		
		case "/extendDueDays":
			forwardPath = extendDueDays(request);
			
		
		break;
		
		case "/borrowerLogin":
			forwardPath = borrowerCardDetails(request);
		
		default:
			break;
		}
		RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);
		
			
	}


	private String addLibrarybranch(HttpServletRequest request) {
		String BranchName = request.getParameter("branchName");
		String BranchAddress = request.getParameter("branchAddress");
		
		
		LibraryBranch librarybranch = new LibraryBranch();

		librarybranch.setBranchName(BranchName);
		librarybranch.setBranchAddress(BranchAddress);
		
		try {
			service.createLibraryBranch(librarybranch);
			request.setAttribute("message", "Library branch added sucessfully");
			return "adminviewlibrary.jsp";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", "Library branch failed !!");
			return "addlibrarybranch.html";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "addlibrarybranch.html";
		}
	}

//	 private void pageAuthors(HttpServletRequest request) {
//		 List<Author> authors = new ArrayList<Author>();
//		 int pageNo = Integer.parseInt(request.getParameter("pageNo"));
//		 try {
//		 authors = service.viewAuthors(pageNo);
//		 request.setAttribute("authors", authors);
//		 } catch (ClassNotFoundException | SQLException e) {
//		 // TODO Auto-generated catch block
//		 e.printStackTrace();
//		 request.setAttribute("authors", null);
//		 }
//		 }

	
	private void pageAuthors(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Author> authors = new ArrayList<Author>();
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		StringBuffer str = new StringBuffer();
		try {
			authors = service.viewAuthors(pageNo);
			
			str.append("<thead><tr><th>Author Name</th><th>Edit Author</th><th>Delete Author</th></tr></thead><tbody>");
			for (Author a : authors) {
				str.append("<tr><td >"+a.getAuthorName()+"</td>");
				str.append("<td><button type='button' class='btn btn-sm btn-primary'  data-toggle='modal' data-target='#myModal1' href='EditAuthor?authorId="+a.getAuthorId()+"'>EDIT</button></td>");
				str.append("<td><button type='button' class='btn btn-sm btn-danger' data-toggle='modal' data-target='#myModal1' href='DeleteAuthor?authorId="+a.getAuthorId()+"'>DELETE</button></td></tr>");
			}
			response.getWriter().append(str);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().append(str);
		}
	}
	
	
	
	private void SearchPageAuthors(HttpServletRequest request, HttpServletResponse response,String search) throws IOException {
		List<Author> authors = new ArrayList<Author>();
		
		StringBuffer str = new StringBuffer();
		try {
			authors = service.searchAuthors(search);
			
			str.append("<thead><tr><th>Author Name</th><th>Edit Author</th><th>Delete Author</th></tr></thead><tbody>");
			for (Author a : authors) {
				str.append("<tr><td >"+a.getAuthorName()+"</td>");
				str.append("<td><button type='button' class='btn btn-sm btn-primary'  data-toggle='modal' data-target='#myModal1' href='EditAuthor?authorId="+a.getAuthorId()+"'>EDIT</button></td>");
				str.append("<td><button type='button' class='btn btn-sm btn-danger' data-toggle='modal' data-target='#myModal1' href='DeleteAuthor?authorId="+a.getAuthorId()+"'>DELETE</button></td></tr>");
			}
			response.getWriter().append(str);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().append(str);
		}
	}

	
	
	
	
	
	private void pageLibraryBranchs(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<LibraryBranch> LibraryBranchs = new ArrayList<LibraryBranch>();
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		StringBuffer str = new StringBuffer();
		try {
			LibraryBranchs = service.viewLibraryBranchs(pageNo);
			
			str.append("<thead><tr><th>Branch Name</th><th>Branch Address</th><th>Update Library Details</th><th>Add new books/Copies</th><th>Manage Book Copies</th></tr></thead><tbody>");
			for (LibraryBranch b: LibraryBranchs) {
				str.append("<tr><td>"+b.getBranchName()+"</td>");
				str.append("<td >"+b.getBranchAddress()+"</td>");
				str.append("<td><button type='button' class='btn btn-sm btn-primary'  data-toggle='modal' data-target='#myModal1'href='editLibrarybranch?BranchId="+b.getBranchId()+"'>Update Library Details</button></td>");
				str.append("<td><button type='button' class='btn btn-sm btn-primary'  data-toggle='modal' data-target='#myModal1'href='addBookcopies?BranchId="+b.getBranchId()+"'>Add new books/Copies</button></td>");
				
				str.append("<td ><button type=\"button\" class=\"btn btn-sm btn-primary\" onclick=\"javascript:location.href='editbookcopies.jsp?BranchId="+b.getBranchId()+"'\">Manage Book Copies</button></td></tr>");
				//str.append("<td ><button type='button' class='btn btn-sm btn-primary' onclick='javascript:location.href='editbookcopies.jsp?BranchId="+b.getBranchId()+"'>Manage Book Copies</button></td></tr>");
			//	str.append("<td ><button type='button' class='btn btn-sm btn-primary' onclick='javascript:location.href='http://localhost:8080/LMSWebMonday/editbookcopies.jsp?BranchId="+b.getBranchId()+"'>Manage Book Copies</button></td></tr>");
				
				
			//	str.append("<td ><button type='button' class='btn btn-sm btn-primary' onclick='javascript:location.href='editbookcopies.jsp?BranchId="+b.getBranchId()+"''>Manage Book Copies</button></td></tr>");
			}
			response.getWriter().append(str);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().append(str);
		}
	}
	
	
	
	
	
	
	
	
	private void pageLibrary1Branchs(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<LibraryBranch> LibraryBranchs = new ArrayList<LibraryBranch>();
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		String Cardno =request.getParameter("cardNo");
		
		StringBuffer str = new StringBuffer();
		try {
			LibraryBranchs = service.viewLibraryBranchs(pageNo);
			
			str.append("<thead><tr><th >Branch Name</th><th >Check out Books</th></tr></thead><tbody>");
			for (LibraryBranch b: LibraryBranchs) {
				str.append("<tr><td >"+b.getBranchName()+"</td>");
				
				
			///	str.append("<td ><button type=\"button\" class=\"btn btn-sm btn-primary\" href='borrowerbranchbooks.jsp?BranchId="+b.getBranchId()+"&CardNo="+Cardno+"'>Manage Book Copies</button></td></tr>");
				
				
				str.append("<td ><button type=\"button\" class=\"btn btn-sm btn-primary\" onclick=\"javascript:location.href='borrowerbranchbooks.jsp?BranchId="+b.getBranchId()+"&CardNo="+Cardno+"'\">Manage Book Copies</button></td></tr>");
				//str.append("<td ><button type='button' class='btn btn-sm btn-primary' onclick='javascript:location.href='editbookcopies.jsp?BranchId="+b.getBranchId()+"'>Manage Book Copies</button></td></tr>");
			//	str.append("<td ><button type='button' class='btn btn-sm btn-primary' onclick='javascript:location.href='http://localhost:8080/LMSWebMonday/editbookcopies.jsp?BranchId="+b.getBranchId()+"'>Manage Book Copies</button></td></tr>");
				
				
			//	str.append("<td ><button type='button' class='btn btn-sm btn-primary' onclick='javascript:location.href='editbookcopies.jsp?BranchId="+b.getBranchId()+"''>Manage Book Copies</button></td></tr>");
			}
			response.getWriter().append(str);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().append(str);
		}
	}
	
	private void pageBooks(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Book> books = new ArrayList<Book>();
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		StringBuffer str = new StringBuffer();
		try {
			books = service.viewBooks(pageNo);
			
			str.append("<thead><tr><th>Book Title</th><th>Publisher Name</th><th>Author's Name</th><th>Genre's Name</th><th>Edit Book</th><th>Delete Book</th></tr></thead><tbody>");
			for (Book b : books) {
				
				str.append("<tr><td >"+b.getTitle()+"</td>");
				str.append("<td >"+b.getPublisher().getPublisherName()+"</td>");
//				str.append("<td >"+if(b.getAuthors()!=null && b.getAuthors().size() >0){for(Author a: b.getAuthors()){out.println(a.getAuthorName()+',');}}+"</td>");
//				str.append("<td >"+if(b.getGenres()!=null && b.getGenres().size() >0){for(Genre g: b.getGenres()){out.println(g.getGenre_name()+',');}}+"</td>");
				String authorNameTemp = "";

				 if(b.getAuthors()!=null && b.getAuthors().size() >0){
				  for(Author a: b.getAuthors()){
				   authorNameTemp += a.getAuthorName()+",";
				  }
				 }
				str.append("<td >"+authorNameTemp+"</td>");
				String genreNameTemp = "";
				 if(b.getGenres()!=null && b.getGenres().size() >0){
				  for(Genre g: b.getGenres()){
				   genreNameTemp+=g.getGenre_name()+",";
				  }
				 }
				str.append("<td >"+genreNameTemp+"</td>");
				
				
				
				str.append("<td><button type='button' class='btn btn-sm btn-primary'  data-toggle='modal' data-target='#myModal1'href='editBook?bookId="+b.getBookId()+"'>EDIT</button></td>");
				str.append("<td><button type='button' class='btn btn-sm btn-danger' data-toggle='modal' data-target='#myModal1'href='deleteBook?bookId="+b.getBookId()+"'>DELETE</button></td></tr>");
			}
			response.getWriter().append(str);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().append(str);
		}
	}

	private void SearchPageBooks(HttpServletRequest request, HttpServletResponse response,String search) throws IOException {
		List<Book> books = new ArrayList<Book>();
		
		StringBuffer str = new StringBuffer();
		try {
			books = service.searchBooks(search);
			
			str.append("<thead><tr><th>Book Title</th><th>Publisher Name</th><th>Author's Name</th><th>Genre's Name</th><th>Edit Book</th><th>Delete Book</th></tr></thead><tbody>");
			for (Book b : books) {
				
				str.append("<tr><td >"+b.getTitle()+"</td>");
				str.append("<td >"+b.getPublisher().getPublisherName()+"</td>");
//				str.append("<td >"+if(b.getAuthors()!=null && b.getAuthors().size() >0){for(Author a: b.getAuthors()){out.println(a.getAuthorName()+',');}}+"</td>");
//				str.append("<td >"+if(b.getGenres()!=null && b.getGenres().size() >0){for(Genre g: b.getGenres()){out.println(g.getGenre_name()+',');}}+"</td>");
				String authorNameTemp = "";

				 if(b.getAuthors()!=null && b.getAuthors().size() >0){
				  for(Author a: b.getAuthors()){
				   authorNameTemp += a.getAuthorName()+",";
				  }
				 }
				str.append("<td >"+authorNameTemp+"</td>");
				String genreNameTemp = "";
				 if(b.getGenres()!=null && b.getGenres().size() >0){
				  for(Genre g: b.getGenres()){
				   genreNameTemp+=g.getGenre_name()+",";
				  }
				 }
				str.append("<td >"+genreNameTemp+"</td>");
				
				
				
				str.append("<td><button type='button' class='btn btn-sm btn-primary'  data-toggle='modal' data-target='#myModal1'href='editBook?bookId="+b.getBookId()+"'>EDIT</button></td>");
				str.append("<td><button type='button' class='btn btn-sm btn-danger' data-toggle='modal' data-target='#myModal1'href='deleteBook?bookId="+b.getBookId()+"'>DELETE</button></td></tr>");
			}
			response.getWriter().append(str);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().append(str);
		}
	}
	
	
	
	
	private String addBorrower(HttpServletRequest request) {
		String BorrowerName = request.getParameter("name");
		String BorrowerAddress = request.getParameter("address");
		String BorrowerPhone = request.getParameter("phone");
		
		Borrower borrower = new Borrower();

		borrower.setName(BorrowerName);
		borrower.setAddress(BorrowerAddress);
		borrower.setPhone(BorrowerPhone);
		try {
			service.createBorrower(borrower);
			request.setAttribute("message", "Borroweradded sucessfully");
			return "admin.jsp";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", "Borrower failed !!");
			return "addborrower.html";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "addborrower.html";
		}
	}
	
	private String addBookcopies(HttpServletRequest request) {
		String bookId = request.getParameter("selectedBookName");
		String branchId = request.getParameter("branchId");
		String noOfCopies = request.getParameter("noOfCopies");
		System.out.println(bookId+ " " + branchId+ " " +noOfCopies);
		
		BookCopies bookcopies = new BookCopies();
		bookcopies.setBookId(Integer.parseInt(bookId));
		bookcopies.setBranchId(Integer.parseInt(branchId));
		bookcopies.setNoOfCopies(Integer.parseInt(noOfCopies));
		System.out.println(bookcopies.getBookId()+bookcopies.getBranchId()+bookcopies.getNoOfCopies());
		
		try {
			service.createBookCopies(bookcopies);
			System.out.println("added");
			request.setAttribute("message", "new book added sucessfully");
			return "viewlibrarybranch.jsp";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", "Borrower failed !!");
			return "addbookcopies.jsp";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", "Borrower failed !!");
			return "addbookcopies.jsp";
		}
	}

	private String managecopiesbyBranchIdBookId(HttpServletRequest request) {
		String BookId =request.getParameter("bookId");
	    
	     String BranchId = request.getParameter("branchId");
	     String NoofCopies = request.getParameter("NoOfCopies");
	     
		
		
		BookCopies bookcopies = new BookCopies();

		bookcopies.setBookId(Integer.parseInt(BookId));
		bookcopies.setBranchId(Integer.parseInt(BranchId));
		bookcopies.setNoOfCopies(Integer.parseInt(NoofCopies));
		
		
		
		try {
			service.updateBookCopies(bookcopies);
			request.setAttribute("message", "updated no of copies sucessfully");
			//request.getRequestDis);
			return "editbookcopies.jsp?BranchId="+bookcopies.getBranchId();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println("in catch blosck");
			request.setAttribute("message", "update no of copies failed !!");
			return "managecopies.jsp";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "managecopies.jsp";
		}
	}
	
	private String checkOut(HttpServletRequest request)  {
		String BookId =request.getParameter("bookId");
		String BranchId = request.getParameter("branchId");
		String CardNo =request.getParameter("cardNo");
	  
		Calendar c = Calendar.getInstance(); 
		c.setTime(new Date()); 
		c.add(Calendar.DATE, 7);
		
	     
	     BookLoans bookLoans = new BookLoans();
	     bookLoans.setBookId(Integer.parseInt(BookId));
	     bookLoans.setBranchId(Integer.parseInt(BranchId));
	    bookLoans.setCardNo(Integer.parseInt(CardNo));
	     bookLoans.setDateOut(new Date());
		 bookLoans.setDueDate(c.getTime());
		 
		
		
		
		try {
			service.insertBookLoans (bookLoans);
			//service.updateBookCopies(bookcopies);
			System.out.println("updated");
			request.setAttribute("message", "Book Checked out sucessfully");
			//request.getRequestDis);
			
			return "borrowerbranchbooks.jsp?BranchId="+bookLoans.getBranchId()+"&CardNo="+bookLoans.getCardNo();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println("in catch blosck");
			request.setAttribute("message", "Book Checkout failed");
			return "borrowerbranchbooks.jsp?BranchId="+bookLoans.getBranchId()+"&CardNo="+bookLoans.getCardNo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", "Book Checkout failed");
			return "borrowerbranchbooks.jsp?BranchId="+bookLoans.getBranchId()+"&CardNo="+bookLoans.getCardNo();
		}
	}
	
	private String extendDueDays(HttpServletRequest request)  {
		String BookId =request.getParameter("bookId");
		String BranchId = request.getParameter("branchId");
		String CardNo =request.getParameter("cardNo");
		String extendDueDays =request.getParameter("extendDueDays");
		int ExtendDueDays =Integer.parseInt(extendDueDays);
		Calendar c = Calendar.getInstance(); 
		c.setTime(new Date()); 
		c.add(Calendar.DATE, 7+ExtendDueDays);
		
	     
	     BookLoans bookLoans = new BookLoans();
	     bookLoans.setBookId(Integer.parseInt(BookId));
	     bookLoans.setBranchId(Integer.parseInt(BranchId));
	    bookLoans.setCardNo(Integer.parseInt(CardNo));
	    
		 bookLoans.setDueDate(c.getTime());
		 
		
		
		
		try {
			service.updateBookLoans (bookLoans);
			//service.updateBookCopies(bookcopies);
			System.out.println("updated");
			request.setAttribute("message", "Book duedate changed sucessfully");
			//request.getRequestDis);
			
			return "viewbookloans.jsp";
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println("in catch blosck");
			request.setAttribute("message", "Book Duedate out failed");
			return "editbookloans.jsp";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", "Book Duedate out failed");
			return "editbookloans.jsp";
		}
	}
	
	
	private String checkIn(HttpServletRequest request)  {
		String BookId =request.getParameter("bookId");
		String BranchId = request.getParameter("branchId");
		String CardNo =request.getParameter("cardNo");
	  
//		Calendar c = Calendar.getInstance(); 
//		c.setTime(new Date()); 
//		c.add(Calendar.DATE, 7);
		
	     
	     BookLoans bookLoans = new BookLoans();
	     bookLoans.setBookId(Integer.parseInt(BookId));
	     bookLoans.setBranchId(Integer.parseInt(BranchId));
	    bookLoans.setCardNo(Integer.parseInt(CardNo));
	     
		 
		
		
		
		try {
			service.deleteBookLoans (bookLoans);
			//service.updateBookCopies(bookcopies);
		
			request.setAttribute("message", "Book Checked in  sucessfully");
			//request.getRequestDis);
			
			return "checkin.jsp?cardNo="+bookLoans.getCardNo();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println("in catch blosck");
			request.setAttribute("message", "Book Checkout failed");
			return "checkin.jsp?cardNo="+bookLoans.getCardNo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", "Book Checkout failed");
			return "checkin.jsp?cardNo="+bookLoans.getCardNo();
		}
	}
	

	private String DeleteAuthor(HttpServletRequest request) {
		
		//String authorName = request.getParameter("authorName");
		String authorId = request.getParameter("authorId");
		Author author = new Author();
		//author.setAuthorName(authorName);
		author.setAuthorId(Integer.parseInt(authorId));
		try {
			service.deleteAuthor(author);
			request.setAttribute("message", "Author Delted sucessfully");
			return "viewauthors.jsp";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", "Author failed sucessfully");
			return "deleteauthor.jsp";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "deleteauthor.jsp";
		}
	}
	
	
private String deleteBook(HttpServletRequest request) {
		
		//String authorName = request.getParameter("authorName");
		String bookId = request.getParameter("bookId");
		Book book = new Book();
		//author.setAuthorName(authorName);
		book.setBookId(Integer.parseInt(bookId));
		try {
			List<BookLoans> bookloans = new ArrayList<BookLoans>();
			bookloans =service.viewBookLoansByBookId(bookId);
			if(bookloans.size()==0) {
				service.deleteBookAuthhorsbyBookId(Integer.parseInt(bookId));
				 service.deleteBookGenresbyBookId(Integer.parseInt(bookId));
				
				service.deleteBook(book);
				
				request.setAttribute("message", "Book Delted sucessfully");
				return "viewbook.jsp";
			}
			else{
				request.setAttribute("message", "Book already loaned you can not delete");
				return "viewbook.jsp";}
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", "Author failed sucessfully");
			return "deleteauthor.jsp";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "deleteauthor.jsp";
		}
	}
	
private String deletePublisher(HttpServletRequest request) {
		
		//String authorName = request.getParameter("authorName");
		String publisherId = request.getParameter("publisherId");
		Publisher publisher = new Publisher();
		//author.setAuthorName(authorName);
		publisher.setPublisherId(Integer.parseInt(publisherId));
		try {
			service.deletePublisher(publisher);
			request.setAttribute("message", "Author Delted sucessfully");
			return "viewpublisher.jsp";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", "Author failed sucessfully");
			return "deleteauthor.jsp";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "deleteauthor.jsp";
		}
	}


	private String EditAuthor(HttpServletRequest request) {
		String authorName = request.getParameter("authorName");
		String authorId = request.getParameter("authorId");
		Author author = new Author();
		author.setAuthorName(authorName);
		author.setAuthorId(Integer.parseInt(authorId));
		try {
			service.editAuthor(author);
			request.setAttribute("message", "Author edit sucessfully");
			return "viewauthors.jsp";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", "Author failed sucessfully");
			return "editauthor.jsp";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "editauthor.jsp";
		}
	}
	
	private String editPublisher(HttpServletRequest request) {
		String publisherName = request.getParameter("publisherName");
		String publisherAddress = request.getParameter("publisherName");
		String publisherPhone = request.getParameter("publisherPhone");
		String publisherId = request.getParameter("publisherId");
		Publisher publisher = new Publisher();
		publisher.setPublisherName(publisherName);
		publisher.setPublisherAddress(publisherAddress);
		publisher.setPublisherPhone(publisherPhone);
		publisher.setPublisherId(Integer.parseInt(publisherId));
		try {
			service.editPublisher(publisher);
			request.setAttribute("message", "Publisher edit sucessfully");
			return "viewpublisher.jsp";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", "Publisher failed sucessfully");
			return "editpublisher.jsp";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "editauthor.jsp";
		}
	}
	
	private String borrowerCardDetails(HttpServletRequest request) {
		String cardno = request.getParameter("cardNo");
		Borrower borrower = new Borrower();
		borrower.setCardNo(Integer.parseInt(cardno));
		
		try {
			
			Borrower borrower1 = new Borrower();
			borrower1=service.viewBorrower(borrower);
		
			if(borrower1==null) {
				request.setAttribute("message", "Enter correct card no");
				return "borrowerlogin.jsp";
			}
			else{
				request.setAttribute("message", "Borrower logged in");
				return "borroweraction.jsp?cardNo="+borrower.getCardNo();}
			}
			
		 catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", "Author failed sucessfully");
			return "editauthor.jsp";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "editauthor.jsp";
		}
	}
	
	private String editLibrarybranch (HttpServletRequest request) {
		
		String branchId = request.getParameter("branchId");
		String branchName = request.getParameter("branchName");
		String branchAddress =request.getParameter("branchAddress");
		LibraryBranch librarybranch = new LibraryBranch();
		librarybranch.setBranchId(Integer.parseInt(branchId));
		librarybranch.setBranchName(branchName);
		librarybranch.setBranchAddress(branchAddress);
		
		try {
			service.editLibrarybranch(librarybranch);
			request.setAttribute("message", "librarybranch edit sucessfully");
			return "viewlibrarybranch.jsp";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", "Author failed sucessfully");
			return "viewlibrarybranch.jsp";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "viewlibrarybranch.jsp";
		}
	}


	private String addBook(HttpServletRequest request) {
		String bookName = request.getParameter("bookName");
		String selectedPublisherName = request.getParameter("selectedPublisherName");
		
		
		String[] selectedAuthors = request.getParameterValues("selectedAuthors");
		
		
		
		String[] selectedGenres = request.getParameterValues("selectedGenres");
		
//		System.out.println("publisher id from add books" +selectedPublisherName);
//		
//		System.out.println("comma sep author ids" + selectedAuthors[0]);
		
		
		Book book = new Book();
		
		book.setTitle(bookName);
		book.setPublishername(selectedPublisherName);
		book.setAuthors1(selectedAuthors);
		book.setGenres1(selectedGenres);
		
		try {
			int bookId =service.createBook(book);
			 book.setBookId(bookId);
			 for (int i = 0; i < selectedAuthors.length ; i++){
				 	service.createBookAuthor(bookId,selectedAuthors[i]);
				 	
//					System.out.println("fuckbook"+bookId);
			 }
			 
			 for (int i = 0; i < selectedGenres.length ; i++){
				 	service.createBookGenre(bookId,selectedGenres[i]);
				 	
//					System.out.println("fuckbook"+bookId);
			 }
			 request.setAttribute("message", "Book added sucessfully");
			 return "admin.jsp";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", "Book failed !!");
			return "addbook.jsp";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "addbook.jsp";
		}
	}
	
	private String editBook(HttpServletRequest request) {
		String bookName = request.getParameter("bookName");
		String bookId =  request.getParameter("bookId");
		String selectedPublisherName = request.getParameter("selectedPublisherName");
		
		
		String[] selectedAuthors = request.getParameterValues("selectedAuthors");
		
		
		
		String[] selectedGenres = request.getParameterValues("selectedGenres");
		
		System.out.println("publisher id from add books" +selectedPublisherName);
		
		System.out.println("comma sep author ids" + selectedAuthors[0]);
		
		
		Book book = new Book();
		book.setBookId(Integer.parseInt(bookId));
		book.setTitle(bookName);
		book.setPublishername(selectedPublisherName);
		book.setAuthors1(selectedAuthors);
		book.setGenres1(selectedGenres);
		
		try {
			service.updateBook(book);
			 
			 service.deleteBookAuthhorsbyBookId(Integer.parseInt(bookId));
			 service.deleteBookGenresbyBookId(Integer.parseInt(bookId));
			 for (int i = 0; i < selectedAuthors.length ; i++){
				 	service.createBookAuthor(Integer.parseInt(bookId),selectedAuthors[i]);
				 	
					
			 }
			 
			 for (int i = 0; i < selectedGenres.length ; i++){
				 	service.createBookGenre(Integer.parseInt(bookId),selectedGenres[i]);
				 	
					
			 }
			 request.setAttribute("message", "Book edited sucessfully");
			 return "viewbook.jsp";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", "Book edit failed !!");
			return "addbook.jsp";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "addbook.jsp";
		}
	}


	private String addPublisher(HttpServletRequest request) {
		String publisherName = request.getParameter("publisherName");
		String publisherAddress = request.getParameter("publisherAddress");
		String publisherPhone = request.getParameter("publisherPhone");
		
		Publisher publisher = new Publisher();

		publisher.setPublisherName(publisherName);
		publisher.setPublisherAddress(publisherAddress);
		publisher.setPublisherPhone(publisherPhone);
		try {
			service.createPublisher(publisher);;
			request.setAttribute("message", "Publisher added sucessfully");
			return "viewpublisher.jsp";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", "Publisher failed !!");
			return "addpublisher.html";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "addpublisher.html";
		}
	}


	private String addGenre(HttpServletRequest request) {
		String genreName = request.getParameter("genreName");
		
		Genre genre = new Genre();
		genre.setGenre_name(genreName);
		try {
			service.createGenre(genre);
			request.setAttribute("message", "genre added sucessfully");
			return "viewgenre.jsp";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", "genre failed !!");
			return "addgenre.html";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "addgenre.html";
		}
	}


	private String addAuthor(HttpServletRequest request) {
		String authorName = request.getParameter("authorName");
		
		Author author = new Author();
		author.setAuthorName(authorName);
		try {
			service.createAuthor(author);
			request.setAttribute("message", "Author added sucessfully");
			return "viewauthors.jsp";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", "Author failed sucessfully");
			return "addauthor.html";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "addauthor.jsp";
		}
	}
	
	private String prepareEditAuthor(HttpServletRequest request) {
		String authorId = request.getParameter("authorId");
		Author author = new Author();
		
		if(authorId!=null && !("").equals(authorId)){
			
			Integer authorID = Integer.parseInt(authorId);
			try {
				author = service.viewAuthorByID(authorID);
				request.setAttribute("author", author);
				//System.out.println(author.getAuthorName());
				
				return "editauthor.jsp";
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "viewauthors.jsp";
			}
		}
		return null;
	}
	
	private String prepareeditPublisher(HttpServletRequest request) {
		String publisherId = request.getParameter("publisherId");
		Publisher publisher = new Publisher();
		
		if(publisherId!=null && !("").equals(publisherId)){
			
			Integer publisherID = Integer.parseInt(publisherId);
			try {
				publisher = service.viewPublisherByID(publisherID);
				request.setAttribute("publisher", publisher);
				
				return "editpublisher.jsp";
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "viewpublishers.jsp";
			}
		}
		return null;
	}
	
	private String prepareeditBook(HttpServletRequest request) {
		String bookId = request.getParameter("bookId");
		Book book = new Book();
		
		if(bookId!=null && !("").equals(bookId)){
			
			Integer bookID = Integer.parseInt(bookId);
			try {
				book = service.viewBookByID(bookID);
				request.setAttribute("book", book);
				
				return "editbook.jsp";
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "viewbook.jsp";
			}
		}
		return null;
	}
	
	private String prepareeditLibrarybranch(HttpServletRequest request) {
		String BranchId= request.getParameter("BranchId");
		LibraryBranch librarybranch = new LibraryBranch();
		
		if(BranchId!=null && !("").equals(BranchId)){
			
			Integer branchId = Integer.parseInt(BranchId);
			try {
				librarybranch = service.viewBranchByID(branchId);
				request.setAttribute("librarybranch", librarybranch);
				
				return "editlibrarybranch.jsp";
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "viewlibrarybranch.jsp";
			}
		}
		return null;
	}
	
	private String prepareviewLibraries1(HttpServletRequest request) {
		String cardNo= request.getParameter("cardNo");
		Borrower borrower = new Borrower();
		
		if(cardNo!=null && !("").equals(cardNo)){
			
			
			try {
				borrower = service.viewBorrowerByCardNo(cardNo);
				request.setAttribute("borrower", borrower);
				request.setAttribute("cardNo", cardNo);
				
				return "viewlibraries1.jsp";
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "borroweraction.jsp";
			}
		}
		return null;
	}
	
	private String prepareaddBookcopies(HttpServletRequest request) {
		String BranchId= request.getParameter("BranchId");
		LibraryBranch librarybranch = new LibraryBranch();
		List<Book> books = new ArrayList<Book>();
		
		if(BranchId!=null && !("").equals(BranchId)){
			
			Integer branchId = Integer.parseInt(BranchId);
			try {
				librarybranch = service.viewBranchByID(branchId);
				request.setAttribute("librarybranch", librarybranch);
				
			    books= service.viewBooksnotinbookcopies(branchId);
			    request.setAttribute("books", books);
			
				return "addbookcopies.jsp";
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "viewlibrarybranch.jsp";
			}
		}
		return null;
	}
	
//	private String prepareupdateBookcopies(HttpServletRequest request) {
//		String BranchId= request.getParameter("BranchId");
//		BookCopies bookcopies = new BookCopies();
//		
//		
//		if(BranchId!=null && !("").equals(BranchId)){
//			
//			Integer branchId = Integer.parseInt(BranchId);
//			try {
//				bookcopies = service.viewBooksbyBranchId(branchId);
//				request.setAttribute("librarybranch", librarybranch);
//				
//			    books= service.viewBooks();
//			    request.setAttribute("books", books);
//			
//				return "addbookcopies.jsp";
//				
//			} catch (ClassNotFoundException | SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				return "viewlibrarybranch.jsp";
//			}
//		}
//		return null;
//	}
	
	private String preparedeletePublisher(HttpServletRequest request) {
		String publisherId = request.getParameter("publisherId");
		Publisher publisher = new Publisher();
		System.out.println("in edit author");
		if(publisherId!=null && !("").equals(publisherId)){
			
			Integer publisherID = Integer.parseInt(publisherId);
			try {
				
				//System.out.println("id in value"+authorID);
				publisher = service.viewPublisherByID(publisherID);
				request.setAttribute("publisher", publisher);
				
				return "deletepublisher.jsp";
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "viewpublisher.jsp";
			}
		}
		return null;
	}

	private String prepareDeleteAuthor(HttpServletRequest request) {
		String authorId = request.getParameter("authorId");
		Author author = new Author();
		System.out.println("in edit author");
		if(authorId!=null && !("").equals(authorId)){
			
			Integer authorID = Integer.parseInt(authorId);
			try {
				
				System.out.println("id in value"+authorID);
				author = service.viewAuthorByID(authorID);
				request.setAttribute("author", author);
				System.out.println("in try method");
				return "deleteauthor.jsp";
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "viewauthors.jsp";
			}
		}
		return null;
	}
	
	private String preparedeleteBook(HttpServletRequest request) {
		String BookId = request.getParameter("bookId");
		Book book = new Book();
		
		if(BookId!=null && !("").equals(BookId)){
			
			Integer bookID = Integer.parseInt(BookId);
			try {
				
				
				book = service.viewBookByID(bookID);
				request.setAttribute("book", book);
				
				return "deletebook.jsp";
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "viewbook.jsp";
			}
		}
		return null;
	}
	
}
			

//@WebServlet("/addAuthor")
//public class AdminServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public AdminServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//    AdministrativeService service = new AdministrativeService();
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String authorName = request.getParameter("authorName");
//		Author author = new Author();
//		author.setAuthorName(authorName);
//		try {
//			service.createAuthor(author);
//			request.setAttribute("message", "Author added sucessfully");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			request.setAttribute("message", "Author failed sucessfully");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
//		rd.forward(request, response);
//	}
//}
//	@WebServlet("/addGenre")
//	public class AdminServlet1 extends HttpServlet {
//		private static final long serialVersionUID = 1L;
//	       
//	    /**
//	     * @see HttpServlet#HttpServlet()
//	     */
//	    public AdminServlet1() {
//	        super();
//	        // TODO Auto-generated constructor stub
//	    }
//	    
// 
//	    AdministrativeService service = new AdministrativeService();
//		/**
//		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//		 */
//		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//			// TODO Auto-generated method stub
//			response.getWriter().append("Served at: ").append(request.getContextPath());
//		}
//
//		/**
//		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//		 */
//		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//			String genreName = request.getParameter("genreName");
//			Genre genre = new Genre();
//			genre.setGenre_name(genreName);
//			try {
//				service.createGenre(genre);
//				request.setAttribute("message", "genre added sucessfully");
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				request.setAttribute("message", "genre failed !!");
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
//			rd.forward(request, response);
//		}
//
//}
//
//
//
//
