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

		System.out.print("testCase1: ");

		// initial state
		Publisher publisher = modelManager.addPublisher("testpublisher");
		Author author = modelManager.addAuthor("testauthor");
		Document book = modelManager.addBook("testbook", 0, 0,false, false, 0, publisher, author, "");
		modelManager.setCopies(book, 2);
		User patron = modelManager.addUser("testpatron", "student", "testpatron", "test", "", "");
		User librarian = modelManager.addUser("testlibrarian", "librarian", "testlibrarian", "test", "", "");

		// test case itself
		manager.bookDocument(book, patron, false);
		manager.checkOutDocument(book, patron, librarian);
		book = documentRepository.findOne(book.getId());
		
		// check conditions
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
	public void testCase2() {

		System.out.print("testCase2: ");

		// initial state is the same as at the end of testCase1

		// test case
		Author found = authorRepository.findByName("author");
		if (found == null) {
			System.out.println("No such author found");
		} else System.out.println("OK");
	}

}
