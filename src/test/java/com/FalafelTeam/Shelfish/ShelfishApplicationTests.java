package com.FalafelTeam.Shelfish;

import com.FalafelTeam.Shelfish.model.*;
import com.FalafelTeam.Shelfish.repository.*;
import com.FalafelTeam.Shelfish.service.BookingSystemManager;
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

	@Test
	public void testCase1() throws Exception {

		// initial state
		Publisher publisher = new Publisher("testpublisher");
		publisherRepository.save(publisher);
		Author author = new Author("testauthor");
		authorRepository.save(author);
		Document book = new Document("testbook", 0, false, false, 0, publisher, author, "");
		book.setCopies(2);
		documentRepository.save(book);
		User patron = new User("testpatron", "student", "testpatron", "test", "", "");
		userRepository.save(patron);
		User librarian = new User( "testlibrarian", "librarian", "tesrlibrarian", "test", "", "");
		userRepository.save(librarian);

		// test case itself
		manager.bookDocument(book, patron, false);
		manager.checkOutDocument(book, patron, librarian);

		// check conditions
		if (patron.documents.contains(documentUserRepository.findByUserAndDocument(patron, book))) {
			if (book.availableCopies() == 1) {
				System.out.println("OK");
			} else {
				System.out.println("Wrong number of copies in the lbrary");
			}
		} else {
			System.out.println("The book isn't in the list of the patron's documents");
		}

		// deleting all created files from the database
		/*publisherRepository.deleteAll();
		authorRepository.deleteAll();
		documentRepository.deleteAll();
		userRepository.deleteAll();*/
	}

	@Test
	public void testCase2() {

		// initial state is the same as at the end of testCase1

		// test case
		Author found = authorRepository.findByName("author");
		if (found == null) {
			System.out.println("No such author found");
		}
	}

}
