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

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Delivery1Tests {

	@Autowired
	BookingSystemManager manager;
	@Autowired
	ModelManager modelManager;

	@Test
	public void testCase1() throws Exception {

		// initial state
		List<String> authorNames = new LinkedList<>();
		authorNames.add("author");
		Document book = modelManager.addDocument("testbook", "book", authorNames, "publisher",
				"editor", 1, new Date(), "", "", false, 0, 2,
				false);
		User patron = modelManager.addUser("testpatron", "student", "testpatron", "test",
				"", "");
		User librarian = modelManager.addUser("testlibrarian", "librarian", "testlibrarian",
				"test", "", "");

		// test case itself
		manager.bookDocument(book, patron, false);
		manager.checkOutDocument(book, patron, librarian);
		DocumentUser relation = modelManager.getDocumentUserByDocumentAndUser(book, patron, librarian);

		// checking conditions
		System.out.print("testCase1: ");
		if (relation == null) {
			throw new Exception("Document-user relation not found");
		}
		if (relation.getUser().getId().equals(patron.getId()) &&
				relation.getDocument().getId().equals(book.getId())) {
			System.out.println("OK");
		} else {
			throw new Exception("Error");
		}

		// deleting all created files from the database
		modelManager.clearDB();
	}

	@Test
	public void testCase2() throws Exception {

	    // initial state
        List<String> authorNames = new LinkedList<>();
        authorNames.add("author");
        Document book = modelManager.addDocument("testbook", "book", authorNames, "publisher",
                "editor", 1, new Date(), "", "", false, 0, 2,
                false);
        User patron = modelManager.addUser("testpatron", "student", "testpatron", "test",
                "", "");

		// test case
        System.out.print("testCase2: ");
        try {
            manager.bookDocument(book, patron, false);
            System.out.println("OK");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

		// deleting all created files
        modelManager.clearDB();
	}

	/*@Test
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

		// deleting all created files from the database
		modelManager.deleteAllDocumentUsers();
		modelManager.deleteAllDocuments();
		modelManager.deleteAllUsers();
		modelManager.deleteAllPublishers();
		modelManager.deleteAllAuthors();
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

		// deleting all created files from the database
		modelManager.deleteAllDocumentUsers();
		modelManager.deleteAllDocuments();
		modelManager.deleteAllUsers();
		modelManager.deleteAllPublishers();
		modelManager.deleteAllAuthors();
	}

	@Test
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

		// deleting all created files from the database
		modelManager.deleteAllDocumentUsers();
		modelManager.deleteAllDocuments();
		modelManager.deleteAllUsers();
		modelManager.deleteAllPublishers();
		modelManager.deleteAllAuthors();
	}*/
}
