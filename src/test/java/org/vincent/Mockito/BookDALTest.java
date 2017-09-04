package org.vincent.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.vincent.Mockito.dal.BookDAL;
import org.vincent.Mockito.models.Book;

/**
 * http://www.importnew.com/10083.html
 * @author liuhy
 *
 */
public class BookDALTest {
	 private static BookDAL mockedBookDAL;
	 private static Book book1;
	 private static Book book2;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//Create mock object of BookDAL
	    mockedBookDAL = mock(BookDAL.class);
	 
	    //Create few instances of Book class.
	    book1 = new Book("8131721019","Compilers Principles",
	            Arrays.asList("D. Jeffrey Ulman","Ravi Sethi", "Alfred V. Aho", "Monica S. Lam"),
	            "Pearson Education Singapore Pte Ltd", 2008,1009,"BOOK_IMAGE");
	 
	    book2 = new Book("9788183331630","Let Us C 13th Edition",
	            Arrays.asList("Yashavant Kanetkar"),"BPB PUBLICATIONS", 2012,675,"BOOK_IMAGE");
	 
	    //Stubbing the methods of mocked BookDAL with mocked data. 
	    //为设置不同方法设置期望的返回值。
	    when(mockedBookDAL.getAllBooks()).thenReturn(Arrays.asList(book1, book2));
	    when(mockedBookDAL.getBook("8131721019")).thenReturn(book1);
	    when(mockedBookDAL.addBook(book1)).thenReturn(book1.getIsbn());
	    when(mockedBookDAL.updateBook(book1)).thenReturn(book1.getIsbn());
	    System.out.println("setup setUpBeforeClass begin");
	}

	 

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("tearDown tearDownAfterClass end");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("setup begin");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("tearDown end");
	}

	@Test
	public void testGetAllBooks() {
			System.out.println("testGetAllBooks");
			//判定所有书中第一本书是 book1 实例
		 	List<Book> allBooks = mockedBookDAL.getAllBooks();
		    assertEquals(2, allBooks.size());
		    Book myBook = allBooks.get(0);
		    assertEquals("8131721019", myBook.getIsbn());
		    assertEquals("Compilers Principles", myBook.getTitle());
		    assertEquals(4, myBook.getAuthors().size());
		    assertEquals((Integer)2008, myBook.getYearOfPublication());
		    assertEquals((Integer) 1009, myBook.getNumberOfPages());
		    assertEquals("Pearson Education Singapore Pte Ltd", myBook.getPublication());
		    assertEquals("BOOK_IMAGE", myBook.getImage());
	}

	@Test
	public void testGetBook() {
		String isbn = "8131721019";
		 
	    Book myBook = mockedBookDAL.getBook(isbn);
	 
	    assertNotNull(myBook);
	    assertEquals(isbn, myBook.getIsbn());
	    assertEquals("Compilers Principles", myBook.getTitle());
	    assertEquals(4, myBook.getAuthors().size());
	    assertEquals("Pearson Education Singapore Pte Ltd", myBook.getPublication());
	    assertEquals((Integer)2008, myBook.getYearOfPublication());
	    assertEquals((Integer)1009, myBook.getNumberOfPages());
	}

	@Test
	public void testAddBook() {
		 	String isbn = mockedBookDAL.addBook(book1);
		    assertNotNull(isbn);
		    assertEquals(book1.getIsbn(), isbn);
	}

	@Test
	public void testUpdateBook() {
		 	String isbn = mockedBookDAL.updateBook(book1);
		    assertNotNull(isbn);
		    assertEquals(book1.getIsbn(), isbn);
	}

	/*@Test
	public void testGetInstance() {
		fail("Not yet implemented");
	}*/

}
