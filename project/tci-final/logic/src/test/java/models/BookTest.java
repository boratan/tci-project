package models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookTest {

    private Book book;

    @Before
    public void setUp(){
        List<String> authors = new ArrayList<>();
        authors.add("Douglas Adams");
        book = new Book(
                "The Hitchhiker's Guide to the Galaxy",
                "Science Fiction",
                "Hardcover",
                1978,
                    authors,
                "Hermes",
                "978-0201485669"
        );
    }

    @Test
    public void BookNameIsNotNullWhenConstructorIsInvoked(){
        Assert.assertNotNull(book.getName());
    }

    @Test
    public void BookGenreIsNotNullWhenConstructorIsInvoked(){
        Assert.assertNotNull(book.getGenre());
    }

    @Test
    public void BookFormatIsNotNullWhenConstructorIsInvoked(){
        Assert.assertNotNull(book.getFormat());
    }

    @Test
    public void BookYearIsNotNullWhenConstructorIsInvoked(){
        Assert.assertNotNull(book.getYear());
    }

    @Test
    public void BookAuthorsIsNotNullWhenConstructorIsInvoked(){
        Assert.assertNotNull(book.getAuthors());
    }

    @Test
    public void BookPublisherIsNotNullWhenConstructorIsInvoked(){
        Assert.assertNotNull(book.getPublisher());
    }

    @Test
    public void BookIsbnIsNotNullWhenConstructorIsInvoked(){
        Assert.assertNotNull(book.getIsbn());
    }

    @Test (expected = IllegalArgumentException.class)
    public void BookNameThrowsExceptionIfTheGivenValueIsNull(){
        book.setName(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BookNameThrowsExceptionIfTheGivenValueIsEmpty(){
        book.setName("");
    }

    @Test (expected = IllegalArgumentException.class)
    public void BookGenreThrowsExceptionIfTheGivenValueIsNull(){
        book.setGenre(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BookGenreThrowsExceptionIfTheGivenValueIsEmpty(){
        book.setGenre("");
    }

    @Test (expected = IllegalArgumentException.class)
    public void BookFormatThrowsExceptionIfTheGivenValueIsNull(){
        book.setFormat(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BookFormatThrowsExceptionIfTheGivenValueIsEmpty(){
        book.setFormat("");
    }

    @Test (expected = IllegalArgumentException.class)
    public void BookYearThrowsExceptionIfTheGivenValueIsNegative(){
        book.setYear(-5);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BookAuthorsThrowsExceptionIfTheGivenValueIsNull(){
        book.setAuthors(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BookAuthorsThrowsExceptionIfTheGivenValueIsEmpty(){
        List<String> testAuthors = Collections.emptyList();
        book.setAuthors(testAuthors);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BookPublisherThrowsExceptionIfTheGivenValueIsNull(){
        book.setPublisher(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void BookPublisherThrowsExceptionIfTheGivenValueIsEmpty(){
        book.setPublisher("");
    }

    @Test (expected = IllegalArgumentException.class)
    public void SetBookIsbnThrowsIllegalArgumentExceptionIfStringIsNotOnlyNumberAndMinus(){
        book.setisbn("Incorrect");
    }
}