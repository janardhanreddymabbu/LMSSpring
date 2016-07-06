package com.gcit.lms;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	AdministrativeService service;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home.jsp";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String gotoAdminPage(Locale locale, Model model) {
		return "admin.jsp";
	}

	@RequestMapping(value = "/viewAuthors", method = RequestMethod.GET)
	public String viewAuthors(Locale locale, Model model) {
		model.addAttribute("service", service);
		return "viewauthors.jsp";
	}

	@RequestMapping(value = "/addAuthorpage", method = RequestMethod.GET)
	public String addAuthorpage(Locale locale, Model model) {
		model.addAttribute("service", service);
		return "addauthor.jsp";
	}
	
	@RequestMapping(value = "/addPublisherpage", method = RequestMethod.GET)
	public String addPublisherpage(Locale locale, Model model) {
		model.addAttribute("service", service);
		return "addpublisher.jsp";
	}
	
	@RequestMapping(value = "/addgenrepage", method = RequestMethod.GET)
	public String addgenrepage(Locale locale, Model model) {
		model.addAttribute("service", service);
		return "addgenre.jsp";
	}
	
	@RequestMapping(value = "/addborrowerpage", method = RequestMethod.GET)
	public String addborrowerpage(Locale locale, Model model) {
		model.addAttribute("service", service);
		return "addborrower.jsp";
	}
	
	@RequestMapping(value = "/viewborrowerpage", method = RequestMethod.GET)
	public String viewborrowerpage(Locale locale, Model model) {
		model.addAttribute("service", service);
		return "viewborrower.jsp";
	}
	
	@RequestMapping(value = "/viewgenrepage", method = RequestMethod.GET)
	public String viewgenrepage(Locale locale, Model model) {
		model.addAttribute("service", service);
		return "viewgenre.jsp";
	}
	
	@RequestMapping(value = "/viewPublisherpage", method = RequestMethod.GET)
	public String viewPublisherpage(Locale locale, Model model) {
		model.addAttribute("service", service);
		return "viewpublisher.jsp";
	}
	
	@RequestMapping(value = "/viewbookloanspage", method = RequestMethod.GET)
	public String viewbookloanspage(Locale locale, Model model) {
		model.addAttribute("service", service);
		return "viewbookloans.jsp";
	}
	
	@RequestMapping(value = "/adminviewlibrarypage", method = RequestMethod.GET)
	public String adminviewlibrarypage(Locale locale, Model model) {
		
		return "adminviewlibrary.jsp";
	}
	
	
	@RequestMapping(value = "/addlibrarybranchpage", method = RequestMethod.GET)
	public String addlibrarybranchpage(Locale locale, Model model) {
		
		return "addlibrarybranch.jsp";
	}
	@RequestMapping(value = "/editbookloans", method = RequestMethod.GET)
	public String editbookloans(Locale locale, Model model, @RequestParam("branchId") String branchId, @RequestParam("bookId") String bookId, @RequestParam("cardNo") String cardNo, @RequestParam("dueDate") String dueDate) {
		model.addAttribute("service", service);
		model.addAttribute("branchId", branchId);
		model.addAttribute("bookId", bookId);
		model.addAttribute("cardNo", cardNo);
		model.addAttribute("dueDate", dueDate);
		return "editbookloans.jsp";
	}
	
	
	@RequestMapping(value = "/addLibrarybranch", method = RequestMethod.POST)
	public String addLibrarybranch(Locale locale, Model model,  @RequestParam("branchName") String BranchName, @RequestParam("branchAddress") String BranchAddress)
			throws ClassNotFoundException, SQLException {

		
		LibraryBranch librarybranch = new LibraryBranch();

		librarybranch.setBranchName(BranchName);
		librarybranch.setBranchAddress(BranchAddress);
		model.addAttribute("service", service);
		
		try {
			service.createLibraryBranch(librarybranch);
			model.addAttribute("message", "Library branch added sucessfully");
			return "adminviewlibrary.jsp";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("message", "Library branch failed !!");
			return "addlibrarybranch.html";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "addlibrarybranch.html";
		}

	}
	
	
	
	@RequestMapping(value = "/extendDueDays", method = RequestMethod.POST)
	public String extendDueDays(Locale locale, Model model,  @RequestParam("branchId") String BranchId, @RequestParam("bookId") String BookId, @RequestParam("cardNo") String CardNo, @RequestParam("extendDueDays") String extendDueDays)
			throws ClassNotFoundException, SQLException {
		int ExtendDueDays =Integer.parseInt(extendDueDays);
		Calendar c = Calendar.getInstance(); 
		c.setTime(new Date()); 
		c.add(Calendar.DATE, ExtendDueDays);
		
	     
	     BookLoans bookLoans = new BookLoans();
	     bookLoans.setBookId(Integer.parseInt(BookId));
	     bookLoans.setBranchId(Integer.parseInt(BranchId));
	    bookLoans.setCardNo(Integer.parseInt(CardNo));
	    
		 bookLoans.setDueDate(c.getTime());
		 
		
			model.addAttribute("service", service);
		
		try {
			service.updateBookLoans (bookLoans);
			//service.updateBookCopies(bookcopies);
			System.out.println("updated");
			model.addAttribute("message", "Book duedate changed sucessfully");
			//request.getRequestDis);
			
			return "viewbookloans.jsp";
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println("in catch blosck");
			model.addAttribute("message", "Book Duedate out failed");
			return "editbookloans.jsp";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("message", "Book Duedate out failed");
			return "editbookloans.jsp";
		}

	}
	

	@RequestMapping(value = "/addGenre", method = RequestMethod.POST)
	public String addGenre(Locale locale, Model model, @RequestParam("genreName") String genreName)
			throws ClassNotFoundException, SQLException {
		Genre genre = new Genre();
		genre.setGenre_name(genreName);
		try {
			service.createGenre(genre);
			model.addAttribute("message", "genre added sucessfully");
			return "admin.jsp";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("message", "genre failed !!");
			return "addgenre.html";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "addgenre.html";
		}

	}
	
	@RequestMapping(value = "/addBorrower", method = RequestMethod.POST)
	public String addBorrower(Locale locale, Model model, @RequestParam("name") String BorrowerName,@RequestParam("address") String BorrowerAddress,@RequestParam("phone") String BorrowerPhone)
			throws ClassNotFoundException, SQLException {
		Borrower borrower = new Borrower();

		borrower.setName(BorrowerName);
		borrower.setAddress(BorrowerAddress);
		borrower.setPhone(BorrowerPhone);
		try {
			service.createBorrower(borrower);
			model.addAttribute("message", "Borroweradded sucessfully");
			return "admin.jsp";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("message", "Borrower failed !!");
			return "addborrower.html";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "addborrower.html";
		}

	}
	
	
	@RequestMapping(value = "/addPublisher", method = RequestMethod.POST)
	public String addPublisher(Locale locale, Model model, @RequestParam("publisherName") String publisherName,@RequestParam("publisherAddress") String publisherAddress,@RequestParam("publisherPhone") String publisherPhone)
			throws ClassNotFoundException, SQLException {
		Publisher publisher = new Publisher();

		publisher.setPublisherName(publisherName);
		publisher.setPublisherAddress(publisherAddress);
		publisher.setPublisherPhone(publisherPhone);
		
		model.addAttribute("service", service);
		try {
			service.createPublisher(publisher);;
			model.addAttribute("message", "Publisher added sucessfully");
			return "viewpublisher.jsp";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("message", "Publisher failed !!");
			return "addpublisher.html";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "addpublisher.html";
		}

	}
	
	@RequestMapping(value = "/editPublisher", method = RequestMethod.GET)
	public String prepareeditPublisher(Locale locale, Model model, @RequestParam("publisherId") String publisherId) {

Publisher publisher = new Publisher();

		if(publisherId!=null && !("").equals(publisherId)){
			
			Integer publisherID = Integer.parseInt(publisherId);
			try {
				publisher = service.viewPublisherByID(publisherID);
				model.addAttribute("publisher", publisher);
				
				return "editpublisher.jsp";
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "viewpublishers.jsp";
			}
		}
		return null;

	}
	
	@RequestMapping(value = "/deletePublisher", method = RequestMethod.GET)
	public String preparedeletePublisher(Locale locale, Model model, @RequestParam("publisherId") String publisherId) {

		Publisher publisher = new Publisher();
		
		if(publisherId!=null && !("").equals(publisherId)){
			
			Integer publisherID = Integer.parseInt(publisherId);
			try {
				
				//System.out.println("id in value"+authorID);
				publisher = service.viewPublisherByID(publisherID);
				model.addAttribute("publisher", publisher);
				
				return "deletepublisher.jsp";
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "viewpublisher.jsp";
			}
		}
		return null;

	}
	
	
	@RequestMapping(value = "/EditAuthor", method = RequestMethod.GET)
	public String prepareEditAuthor(Locale locale, Model model, @RequestParam("authorId") Integer authorId) {

		Author author = new Author();

		if (authorId != null && !("").equals(authorId)) {

			try {
				author = service.viewAuthorByID(authorId);
				model.addAttribute("author", author);
				// System.out.println(author.getAuthorName());

				return "editauthor.jsp";
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "viewauthors.jsp";
			}
		}
		return null;

	}
	
	@RequestMapping(value = "/editPublisher", method = RequestMethod.POST)
	public String editPublisher(Locale locale, Model model, @RequestParam("publisherName") String publisherName,@RequestParam("publisherAddress") String publisherAddress,@RequestParam("publisherPhone") String publisherPhone,@RequestParam("publisherId") String publisherId)
			throws ClassNotFoundException, SQLException {
		Publisher publisher = new Publisher();
		publisher.setPublisherName(publisherName);
		publisher.setPublisherAddress(publisherAddress);
		publisher.setPublisherPhone(publisherPhone);
		publisher.setPublisherId(Integer.parseInt(publisherId));
		
		model.addAttribute("service", service);
		try {
			service.editPublisher(publisher);
			model.addAttribute("message", "Publisher edit sucessfully");
			return "viewpublisher.jsp";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("message", "Publisher failed sucessfully");
			return "editpublisher.jsp";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "editauthor.jsp";
		}

	}
	
	@RequestMapping(value = "/deletePublisher", method = RequestMethod.POST)
	public String deletePublisher(Locale locale, Model model, @RequestParam("publisherId") String publisherId)
			throws ClassNotFoundException, SQLException {
		Publisher publisher = new Publisher();
		//author.setAuthorName(authorName);
		publisher.setPublisherId(Integer.parseInt(publisherId));
		model.addAttribute("service", service);
		try {
			service.deletePublisher(publisher);
			model.addAttribute("message", "Author Delted sucessfully");
			
			return "viewpublisher.jsp";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("message", "Author failed sucessfully");
			return "deleteauthor.jsp";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "deleteauthor.jsp";
		}

	}

	@RequestMapping(value = "/pageAuthors", method = RequestMethod.GET)
	public void pageAuthors(Locale locale, Model model, @RequestParam("pageNo") Integer pageNo, HttpServletResponse response) throws IOException {

		List<Author> authors = new ArrayList<Author>();

		StringBuffer str = new StringBuffer();
		try {
			authors = service.viewAuthors(pageNo);

			str.append("<thead><tr><th>Author Name</th><th>Edit Author</th><th>Delete Author</th></tr></thead><tbody>");
			for (Author a : authors) {
				str.append("<tr><td >" + a.getAuthorName() + "</td>");
				str.append(
						"<td><button type='button' class='btn btn-sm btn-primary'  data-toggle='modal' data-target='#myModal1' href='EditAuthor?authorId="
								+ a.getAuthorId() + "'>EDIT</button></td>");
				str.append(
						"<td><button type='button' class='btn btn-sm btn-danger' data-toggle='modal' data-target='#myModal1' href='DeleteAuthor?authorId="
								+ a.getAuthorId() + "'>DELETE</button></td></tr>");
			}

			response.getWriter().append(str);
			
	
			
			

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		response.getWriter().append(str);
			
		}

	}
	
	
	@RequestMapping(value = "/pageBooks", method = RequestMethod.GET)
	public void pageBooks(Locale locale, Model model, @RequestParam("pageNo") Integer pageNo, HttpServletResponse response) throws IOException {

		List<Book> books = new ArrayList<Book>();
		
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
	
	@RequestMapping(value = "/pageLibraryBranchs", method = RequestMethod.GET)
	public void pageLibraryBranchs(Locale locale, Model model, @RequestParam("pageNo") Integer pageNo, HttpServletResponse response) throws IOException {

		List<LibraryBranch> LibraryBranchs = new ArrayList<LibraryBranch>();
		
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
	
	@RequestMapping(value = "/pageLibrary1Branchs", method = RequestMethod.GET)
	public void pageLibrary1Branchs(Locale locale, Model model, @RequestParam("pageNo") Integer pageNo,@RequestParam("cardNo") String Cardno, HttpServletResponse response) throws IOException {

		List<LibraryBranch> LibraryBranchs = new ArrayList<LibraryBranch>();
		
		
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
	
	
	@RequestMapping(value = "/SearchPageAuthors", method = RequestMethod.GET)
	public void SearchPageAuthors(Locale locale, Model model, HttpServletResponse response,@RequestParam(value = "searchString", required = false) String search) throws IOException {
List<Author> authors = new ArrayList<Author>();
		StringBuffer str = new StringBuffer();
		try {
			authors = service.searchAuthors(search);
			
			System.out.println(authors.size());
			
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
	
	
	@RequestMapping(value = "/SearchPageBooks", method = RequestMethod.GET)
	public void SearchPageBooks(Locale locale, Model model, HttpServletResponse response,@RequestParam(value = "searchString", required = false) String search) throws IOException {
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

	@RequestMapping(value = "/DeleteAuthor", method = RequestMethod.GET)
	public String prepareDeleteAuthor(Locale locale, Model model, @RequestParam("authorId") Integer authorId) {

		Author author = new Author();

		if (authorId != null && !("").equals(authorId)) {

			try {

				author = service.viewAuthorByID(authorId);
				model.addAttribute("author", author);

				return "deleteauthor.jsp";
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				model.addAttribute("service", service);
				return "viewauthors.jsp";
			}
		}
		return null;

	}

	@RequestMapping(value = "/addAuthor", method = RequestMethod.POST)
	public String addAuthor(Locale locale, Model model, @RequestParam("authorName") String AuthorName)
			throws ClassNotFoundException, SQLException {
		Author author = new Author();

		author.setAuthorName(AuthorName);

		try {
			service.createAuthor(author);
			model.addAttribute("message", "Author added sucessfully");
			return "admin.jsp";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("message", "Author failed sucessfully");
			return "addauthor.jsp";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "addauthor.jsp";
		}

	}

	@RequestMapping(value = "/EditAuthor", method = RequestMethod.POST)
	public String EditAuthor(Locale locale, Model model, @RequestParam("authorName") String authorName,
			@RequestParam("authorId") Integer authorId) throws ClassNotFoundException, SQLException {
		Author author = new Author();
		author.setAuthorName(authorName);
		author.setAuthorId(authorId);
		try {
			service.editAuthor(author);
			model.addAttribute("message", "Author edit sucessfully");
			model.addAttribute("service", service);
			return "viewauthors.jsp";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("message", "Author failed sucessfully");
			return "editauthor.jsp";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "editauthor.jsp";
		}
	}

	@RequestMapping(value = "/DeleteAuthor", method = RequestMethod.POST)
	public String DeleteAuthor(Locale locale, Model model, @RequestParam("authorId") Integer authorId)
			throws ClassNotFoundException, SQLException {
		Author author = new Author();
		// author.setAuthorName(authorName);
		author.setAuthorId(authorId);
		try {
			service.deleteAuthor(author);
			model.addAttribute("message", "Author Delted sucessfully");
			model.addAttribute("service", service);
			return "viewauthors.jsp";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("message", "Author failed sucessfully");
			return "deleteauthor.jsp";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "deleteauthor.jsp";
		}
	}

	@RequestMapping(value = "/viewBook", method = RequestMethod.GET)
	public String viewBook(Locale locale, Model model) {
		model.addAttribute("service", service);
		return "viewbook.jsp";
	}
	
	@RequestMapping(value = "/editBook", method = RequestMethod.GET)
	public String prepareeditBook(Locale locale, Model model, @RequestParam("bookId") String bookId) {

			Book book = new Book();
			model.addAttribute("service", service);
		if(bookId!=null && !("").equals(bookId)){
			
			Integer bookID = Integer.parseInt(bookId);
			try {
				book = service.viewBookByID(bookID);
				model.addAttribute("book", book);
				
				return "editbook.jsp";
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "viewbook.jsp";
			}
		}
		return null;

	}
	
	@RequestMapping(value = "/deleteBook", method = RequestMethod.GET)
	public String preparedeleteBook(Locale locale, Model model, @RequestParam("bookId") String BookId) {

Book book = new Book();
model.addAttribute("service", service);
		
		if(BookId!=null && !("").equals(BookId)){
			
			Integer bookID = Integer.parseInt(BookId);
			try {
				
				
				book = service.viewBookByID(bookID);
				model.addAttribute("book", book);
				
				return "deletebook.jsp";
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "viewbook.jsp";
			}
		}
		return null;
	}

	
	
	@RequestMapping(value = "/editBook", method = RequestMethod.POST)
	public String editBook(Locale locale, Model model, @RequestParam("bookName") String bookName,@RequestParam("bookId") String bookId,@RequestParam("selectedPublisherName") String selectedPublisherName,@RequestParam("selectedAuthors") String[] selectedAuthors,@RequestParam("selectedGenres") String[] selectedGenres)
			throws ClassNotFoundException, SQLException {
		Book book = new Book();
		book.setBookId(Integer.parseInt(bookId));
		book.setTitle(bookName);
		book.setPublishername(selectedPublisherName);
		book.setAuthors1(selectedAuthors);
		book.setGenres1(selectedGenres);
		model.addAttribute("service", service);
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
			 model.addAttribute("message", "Book edited sucessfully");
			 return "viewbook.jsp";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("message", "Book edit failed !!");
			return "addbook.jsp";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "addbook.jsp";
		}

	}
	
	
	@RequestMapping(value = "/deleteBook", method = RequestMethod.POST)
	public String deleteBook(Locale locale, Model model,@RequestParam("bookId") String bookId)
			throws ClassNotFoundException, SQLException {
		Book book = new Book();
		
		book.setBookId(Integer.parseInt(bookId));
		
		model.addAttribute("service", service);
		try {
			List<BookLoans> bookloans = new ArrayList<BookLoans>();
			bookloans =service.viewBookLoansByBookId(bookId);
			if(bookloans.size()==0) {
				service.deleteBookAuthhorsbyBookId(Integer.parseInt(bookId));
				 service.deleteBookGenresbyBookId(Integer.parseInt(bookId));
				
				service.deleteBook(book);
				
				 model.addAttribute("message", "Book Delted sucessfully");
				return "viewbook.jsp";
			}
			else{
				 model.addAttribute("message", "Book already loaned you can not delete");
				return "viewbook.jsp";}
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 model.addAttribute("message", "Author failed sucessfully");
			return "deleteauthor.jsp";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "deleteauthor.jsp";
		}

	}
	
	@RequestMapping(value = "/addBookpage", method = RequestMethod.GET)
	public String addBookpage(Locale locale, Model model) {
		model.addAttribute("service", service);
		return "addbook.jsp";
	}
	
	@RequestMapping(value = "/addBook", method = RequestMethod.POST)
	public String addBook(Locale locale, Model model, @RequestParam("bookName") String bookName,@RequestParam("selectedPublisherName") String selectedPublisherName,@RequestParam("selectedAuthors") String[] selectedAuthors,@RequestParam("selectedGenres") String[] selectedGenres)
			throws ClassNotFoundException, SQLException {
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
				 	
//					
			 }
			 
			 for (int i = 0; i < selectedGenres.length ; i++){
				 	service.createBookGenre(bookId,selectedGenres[i]);
				 	
//					
			 }
			 model.addAttribute("message", "Book added sucessfully");
			 return "admin.jsp";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("message", "Book failed !!");
			return "addbook.jsp";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "addbook.jsp";
		}

	}
	
	@RequestMapping(value = "/librarian", method = RequestMethod.GET)
	public String gotoLibrarianPage(Locale locale, Model model) {
		return "librarian.jsp";
	}
	

	@RequestMapping(value = "/viewlibrarybranch", method = RequestMethod.GET)
	public String gotoviewlibrarybranch(Locale locale, Model model) {
		model.addAttribute("service", service);
		return "viewlibrarybranch.jsp";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String gotohome(Locale locale, Model model) {
		return "home.jsp";
	}
	
	@RequestMapping(value = "/editbookcopies", method = RequestMethod.GET)
	public String gotoeditbookcopies(Locale locale, Model model,@RequestParam("BranchId") String BranchId) {
		model.addAttribute("service", service);
		model.addAttribute("BranchId", BranchId);
		return "editbookcopies.jsp";
	}
	@RequestMapping(value = "/managecopies", method = RequestMethod.GET)
	public String gotomanagecopies(Locale locale, Model model,@RequestParam("bookId") String bookId,@RequestParam("branchId") String branchId,@RequestParam("noofCopies") String noofCopies) {
		model.addAttribute("service", service);
		model.addAttribute("bookId", bookId);
		model.addAttribute("branchId", branchId);
		model.addAttribute("noofCopies", noofCopies);
		return "managecopies.jsp";
	}
	
	
	
	@RequestMapping(value = "/editLibrarybranch", method = RequestMethod.GET)
	public String gotoeditLibrarybranch(Locale locale, Model model,@RequestParam("BranchId") String BranchId) {
		model.addAttribute("service", service);
		model.addAttribute("BranchId", BranchId);
		
		LibraryBranch librarybranch = new LibraryBranch();
		
		if(BranchId!=null && !("").equals(BranchId)){
			
			Integer branchId = Integer.parseInt(BranchId);
			try {
				librarybranch = service.viewBranchByID(branchId);
				model.addAttribute("librarybranch", librarybranch);
				
				return "editlibrarybranch.jsp";
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "viewlibrarybranch.jsp";
			}
		}
		return null;
	}
	
	@RequestMapping(value = "/addBookcopies", method = RequestMethod.GET)
	public String gotoaddBookcopies(Locale locale, Model model,@RequestParam("BranchId") String BranchId) {
		model.addAttribute("service", service);
		model.addAttribute("BranchId", BranchId);
		
		LibraryBranch librarybranch = new LibraryBranch();
		List<Book> books = new ArrayList<Book>();
		
		if(BranchId!=null && !("").equals(BranchId)){
			
			Integer branchId = Integer.parseInt(BranchId);
			try {
				librarybranch = service.viewBranchByID(branchId);
				model.addAttribute("librarybranch", librarybranch);
				
			    books= service.viewBooksnotinbookcopies(branchId);
			    model.addAttribute("books", books);
			    model.addAttribute("service", service);
			
				return "addbookcopies.jsp";
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "viewlibrarybranch.jsp";
			}
		}
		return null;
	}
	
	
	@RequestMapping(value = "/managecopiesbyBranchIdBookId", method = RequestMethod.POST)
	public String managecopiesbyBranchIdBookId(Locale locale, Model model, @RequestParam("bookId") String BookId,@RequestParam("branchId") String BranchId,@RequestParam("NoOfCopies") String NoOfCopies)
			throws ClassNotFoundException, SQLException {

		BookCopies bookcopies = new BookCopies();

		bookcopies.setBookId(Integer.parseInt(BookId));
		bookcopies.setBranchId(Integer.parseInt(BranchId));
		bookcopies.setNoOfCopies(Integer.parseInt(NoOfCopies));
		
		
		
		try {
			service.updateBookCopies(bookcopies);
			model.addAttribute("message", "updated no of copies sucessfully");
		
		
				model.addAttribute("service", service);
				model.addAttribute("BranchId", BranchId);
				System.out.println("@@## "+BranchId);
				//return "editbookcopies?BranchId="+BranchId;
				return "editbookcopies.jsp?BranchId="+BranchId;
			//return "librarian";
			
			
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			model.addAttribute("message", "update no of copies failed !!");
			return "managecopies.jsp";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "managecopies.jsp";
		}

	}
	
	@RequestMapping(value = "/editLibrarybranch", method = RequestMethod.POST)
	public String editLibrarybranch(Locale locale, Model model, @RequestParam("branchId") String branchId,@RequestParam("branchName") String branchName,@RequestParam("branchAddress") String branchAddress)
			throws ClassNotFoundException, SQLException {

		LibraryBranch librarybranch = new LibraryBranch();
		librarybranch.setBranchId(Integer.parseInt(branchId));
		librarybranch.setBranchName(branchName);
		librarybranch.setBranchAddress(branchAddress);
		
		try {
			service.editLibrarybranch(librarybranch);
		model.addAttribute("message", "librarybranch edit sucessfully");
		model.addAttribute("service", service);
			return "viewlibrarybranch.jsp";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("message", "Author failed sucessfully");
			model.addAttribute("service", service);
			return "viewlibrarybranch.jsp";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("service", service);
			return "viewlibrarybranch.jsp";
		}

	}
	
	
	@RequestMapping(value = "/addBookcopies", method = RequestMethod.POST)
	public String addBookcopies(Locale locale, Model model, @RequestParam("selectedBookName") String bookId,@RequestParam("branchId") String branchId,@RequestParam("noOfCopies") String noOfCopies)
			throws ClassNotFoundException, SQLException {


		BookCopies bookcopies = new BookCopies();
		bookcopies.setBookId(Integer.parseInt(bookId));
		bookcopies.setBranchId(Integer.parseInt(branchId));
		bookcopies.setNoOfCopies(Integer.parseInt(noOfCopies));
		System.out.println(bookcopies.getBookId()+bookcopies.getBranchId()+bookcopies.getNoOfCopies());
		
		try {
			service.createBookCopies(bookcopies);
			
			model.addAttribute("message", "new book added sucessfully");
			model.addAttribute("service", service);
			return "viewlibrarybranch.jsp";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("message", "Borrower failed !!");
			model.addAttribute("service", service);
			return "addbookcopies.jsp";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("message", "Borrower failed !!");
			model.addAttribute("service", service);
			return "addbookcopies.jsp";
		}

	}
	
	@RequestMapping(value = "/borrowerlogin", method = RequestMethod.GET)
	public String gotoborrowerloginPage(Locale locale, Model model) {
		return "borrowerlogin.jsp";
	}
	
	
	@RequestMapping(value = "/borrowerLogin", method = RequestMethod.POST)
	public String borrowerCardDetails(Locale locale, Model model, @RequestParam("cardNo") String cardno)
			throws ClassNotFoundException, SQLException {


		Borrower borrower = new Borrower();
		borrower.setCardNo(Integer.parseInt(cardno));
		
		try {
			
			Borrower borrower1 = new Borrower();
			borrower1=service.viewBorrower(borrower);
		
			if(borrower1==null) {
				model.addAttribute("message", "Enter correct card no");
				return "borrowerlogin.jsp";
			}
			else{
				model.addAttribute("message", "Borrower logged in");
				model.addAttribute("service", service);
				return "borroweraction.jsp?cardNo="+borrower.getCardNo();}
			}
			
		 catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("message", "login failed sucessfully");
			return "borrowerlogin.jsp";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "borrowerlogin.jsp";
		}

	}
	
	@RequestMapping(value = "/checkin", method = RequestMethod.GET)
	public String gotocheckin(Locale locale, Model model,@RequestParam("cardNo") String cardNo) {
		model.addAttribute("service", service);
		model.addAttribute("cardNo", cardNo);
		return "checkin.jsp";
	}
	
	
	@RequestMapping(value = "/borroweraction", method = RequestMethod.GET)
	public String gotoborroweraction(Locale locale, Model model,@RequestParam("cardNo") String cardNo) {
		model.addAttribute("service", service);
		model.addAttribute("cardNo", cardNo);
		return "borroweraction.jsp";
	}
	
	
	
	@RequestMapping(value = "/checkIn", method = RequestMethod.GET)
	public String checkIn(Locale locale, Model model,@RequestParam("bookId") String BookId,@RequestParam("branchId") String BranchId,@RequestParam("cardNo") String CardNo) {
		model.addAttribute("service", service);
		model.addAttribute("bookId", BookId);
		model.addAttribute("branchId", BranchId);
		model.addAttribute("cardNo", CardNo);
		
		  BookLoans bookLoans = new BookLoans();
		     bookLoans.setBookId(Integer.parseInt(BookId));
		     bookLoans.setBranchId(Integer.parseInt(BranchId));
		    bookLoans.setCardNo(Integer.parseInt(CardNo));
		     
			 
			
			
			
			try {
				service.deleteBookLoans (bookLoans);
				//service.updateBookCopies(bookcopies);
			
				model.addAttribute("message", "Book Checked in  sucessfully");
				//request.getRequestDis);
				
				return "checkin.jsp?cardNo="+bookLoans.getCardNo();
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//System.out.println("in catch blosck");
				model.addAttribute("message", "Book Checkout failed");
				return "checkin.jsp?cardNo="+bookLoans.getCardNo();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				model.addAttribute("message", "Book Checkout failed");
				return "checkin.jsp?cardNo="+bookLoans.getCardNo();
			}
	}
	
	
	@RequestMapping(value = "/viewLibraries1", method = RequestMethod.GET)
	public String gotoviewLibraries1(Locale locale, Model model,@RequestParam("cardNo") String cardNo) {
		model.addAttribute("service", service);
		model.addAttribute("cardNo", cardNo);
	Borrower borrower = new Borrower();
		
		if(cardNo!=null && !("").equals(cardNo)){
			
			
			try {
				borrower = service.viewBorrowerByCardNo(cardNo);
				model.addAttribute("borrower", borrower);
				
				
				return "viewlibraries1.jsp";
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "borroweraction.jsp";
			}
		}
		return null;
	}
	
	@RequestMapping(value = "/borrowerbranchbooks", method = RequestMethod.GET)
	public String gotoborrowerbranchbooks(Locale locale, Model model,@RequestParam("BranchId") String BranchId,@RequestParam("CardNo") String CardNo) {
		model.addAttribute("service", service);
		model.addAttribute("BranchId", BranchId);
		model.addAttribute("CardNo", CardNo);
		return "borrowerbranchbooks.jsp";
	}
	
	
	
	@RequestMapping(value = "/checkOut", method = RequestMethod.GET)
	public String checkOut(Locale locale, Model model,@RequestParam("bookId") String bookId,@RequestParam("branchId") String branchId,@RequestParam("cardNo") String cardNo) {
		model.addAttribute("service", service);
		model.addAttribute("cardNo", cardNo);
		model.addAttribute("bookId", bookId);
		model.addAttribute("branchId", branchId);
		Calendar c = Calendar.getInstance(); 
		c.setTime(new Date()); 
		c.add(Calendar.DATE, 7);
		
	     
	     BookLoans bookLoans = new BookLoans();
	     bookLoans.setBookId(Integer.parseInt(bookId));
	     bookLoans.setBranchId(Integer.parseInt(branchId));
	    bookLoans.setCardNo(Integer.parseInt(cardNo));
	     bookLoans.setDateOut(new Date());
		 bookLoans.setDueDate(c.getTime());
		 
		
		
		
		try {
			service.insertBookLoans (bookLoans);
			
			
			model.addAttribute("message", "Book Checked out sucessfully");
			
			
			return "borrowerbranchbooks.jsp?BranchId="+bookLoans.getBranchId()+"&CardNo="+bookLoans.getCardNo();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println("in catch blosck");
			model.addAttribute("message", "Book Checkout failed");
			return "borrowerbranchbooks.jsp?BranchId="+bookLoans.getBranchId()+"&CardNo="+bookLoans.getCardNo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("message", "Book Checkout failed");
			return "borrowerbranchbooks.jsp?BranchId="+bookLoans.getBranchId()+"&CardNo="+bookLoans.getCardNo();
		}
	}
	
	
}
