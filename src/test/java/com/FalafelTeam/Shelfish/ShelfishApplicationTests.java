package com.FalafelTeam.Shelfish;

import com.FalafelTeam.Shelfish.model.*;
import com.FalafelTeam.Shelfish.repository.*;
import com.FalafelTeam.Shelfish.service.BookingSystemManager;
import com.FalafelTeam.Shelfish.service.ModelManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShelfishApplicationTests {

	@Autowired
	AuthorRepository authorRepository;
	@Autowired
	DocumentRepository documentRepository;
	@Autowired
	DocumentUserRepository documentUserRepository;
	@Autowired
	PublisherRepository publisherRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	BookingSystemManager manager;
	@Autowired
	ModelManager modelManager;

	@Test
	public void testCase1() throws Exception {

		// initial state
		Publisher publisher = modelManager.addPublisher("testpublisher");
		Author author = modelManager.addAuthor("testauthor");
		Document book = modelManager.addBook("testbook", 2, 0,false, false,
				0, publisher, author, "");
		User patron = modelManager.addUser("testpatron", "student", "testpatron", "test",
				"", "");
		User librarian = modelManager.addUser("testlibrarian", "librarian", "testlibrarian",
				"test", "", "");

		// test case itself
		manager.bookDocument(book, patron, false);
		manager.checkOutDocument(book, patron, librarian);
		book = documentRepository.findOne(book.getId());

		// checking conditions
		System.out.print("testCase1: ");
		if (documentUserRepository.findByUserAndDocument(patron, book) == null) {
		    throw new Exception("DocumentUser relation not found");
        }
		if (patron.documentsContain(documentUserRepository.findByUserAndDocument(patron, book))) {
			if (book.availableCopies() == 1) {
				System.out.println("OK");
			} else {
				throw new Exception("Wrong number of copies in the library");
			}
		} else {
			throw new Exception("The book isn't in the list of the patron's documents");
		}

		// deleting all created files from the database
        /*publisherRepository.deleteAll();
		authorRepository.deleteAll();
		documentRepository.deleteAll();
		userRepository.deleteAll();*/
	}

	@Test
	public void testCase2() throws Exception {

		System.out.print("testCase2: ");

		// initial state is the same as at the end of testCase1

		// test case
		Author found = authorRepository.findByName("author");
		if (found == null) {
			System.out.println("No such author found");
		} else System.out.println("OK");
	}

	@Test
	public void testCase3() throws Exception {

		System.out.print("testCase3: ");

		// initial state
		Publisher publisher = modelManager.addPublisher("testpublisher3");
		Author author = modelManager.addAuthor("testauthor3");
		Document book = modelManager.addBook("testbook3", 1, 0,false, false,
				0, publisher, author, "");
		User faculty = modelManager.addUser("testfaculty3", "faculty", "testfaculty3", "test",
				"", "");
		User student = modelManager.addUser("teststudent3", "student", "teststudent3", "test",
				"", "");
		User librarian = modelManager.addUser("testlibrarian3", "librarian", "testlibrarian3",
				"test", "", "");

		// test case
		manager.bookDocument(book, faculty, false);
		manager.checkOutDocument(book, faculty, librarian);

		// checking conditions
		DocumentUser found = documentUserRepository.findByUserAndDocument(faculty, book);
		if (TimeUnit.DAYS.convert(found.getDueDate().getTime() - found.getDate().getTime(),
				TimeUnit.MILLISECONDS) == 7 * 4) {
			System.out.println("OK");
		} else throw new Exception("Wrong due date");
	}

	@Test
	public void testCase4() throws Exception {

		System.out.print("testCase4: ");

		// initial state
		Publisher publisher = modelManager.addPublisher("testpublisher4");
		Author author = modelManager.addAuthor("testauthor4");
		Document book = modelManager.addBook("testbook4", 1, 0,false, true,
				0, publisher, author, "");
		User faculty = modelManager.addUser("testfaculty4", "faculty", "testfaculty4", "test",
				"", "");
		User student = modelManager.addUser("teststudent4", "student", "teststudent4", "test",
				"", "");
		User librarian = modelManager.addUser("testlibrarian4", "librarian", "testlibrarian4",
				"test", "", "");

		// test case
		manager.bookDocument(book, faculty, false);
		manager.checkOutDocument(book, faculty, librarian);

		// checking conditions
		DocumentUser found = documentUserRepository.findByUserAndDocument(faculty, book);
		if (TimeUnit.DAYS.convert(found.getDueDate().getTime() - found.getDate().getTime(),
				TimeUnit.MILLISECONDS) == 7 * 2) {
			System.out.println("OK");
		} else throw new Exception("Wrong due date");
	}

	/*@Test
	public void testCase5() throws Exception {


		System.out.print("testCase5: ");

		// initial state
		Publisher publisher = modelManager.addPublisher("testpublisher5");
		Author author = modelManager.addAuthor("testauthor5");
		Document book = modelManager.addBook("testbook5", 2, 0,false, false,
				0, publisher, author, "");
		User student1 = modelManager.addUser("teststudent5e1", "student", "teststusent5e1",
				"test", "", "");
		User student2 = modelManager.addUser("teststudent5e2", "student", "teststudent5e2",
				"test", "", "");
		User student3 = modelManager.addUser("teststudent5e3", "student", "teststudent5e3",
				"test", "", "");
		User librarian = modelManager.addUser("testlibrarian5", "librarian", "testlibrarian5",
				"test", "", "");

		// test case
		manager.bookDocument(book, student1, false);
		manager.checkOutDocument(book, student1, librarian);
		manager.bookDocument(book, student2, false);
		manager.checkOutDocument(book, student2, librarian);
		manager.bookDocument(book, student3, false);
		try {
			manager.checkOutDocument(book, student3, librarian);
		} catch (Throwable exception){
			System.out.println(exception.toString());
		}
	}*/
}
