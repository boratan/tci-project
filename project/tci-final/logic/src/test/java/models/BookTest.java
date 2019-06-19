package models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.rules.Timeout;
import org.junit.runner.Description;

public class BookTest {

    private Book book;

    private String testLog = "";
    @Rule
    public final TestRule watchman = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            testLog += String.format("Test %s started\n", description);
        }

        @Override
        protected void succeeded(Description description) {
            testLog += String.format("Test %s succeeded", description);
        }

        @Override
        protected void failed(Throwable e, Description description) {
            testLog += String.format("Test %s failed", description);
        }

        @Override
        protected void finished(Description description) {
            System.out.println(testLog);
        }
    };

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Rule
    public TestRule globalTimeout = Timeout.seconds(7);

    @Before
    public void setUp(){
        book = new Book(
                "The Hitchhiker's Guide to the Galaxy",
                "Science Fiction",
                "Hardcover",
                1978,
                "Douglas Adams",
                "Hermes",
                "978-0201485669"
        );
    }

    /**
     * When the constructor is called with not empty arguments,
     * Book Name can be returned upon calling getName().
     */
    @Test
    public void BookNameIsNotNullWhenConstructorIsInvoked(){
        Assert.assertNotNull(book.getName());
    }

    /**
     * When the constructor is called with not empty arguments,
     * Book Genre can be returned upon calling getGenre().
     */
    @Test
    public void BookGenreIsNotNullWhenConstructorIsInvoked(){
        Assert.assertNotNull(book.getGenre());
    }

    /**
     * When the constructor is called with not empty arguments,
     * Book Format can be returned upon calling getFormat().
     */
    @Test
    public void BookFormatIsNotNullWhenConstructorIsInvoked(){
        Assert.assertNotNull(book.getFormat());
    }

    /**
     * When the constructor is called with not empty arguments,
     * Book Year can be returned upon calling getYear().
     */
    @Test
    public void BookYearIsNotNullWhenConstructorIsInvoked(){
        Assert.assertNotNull(book.getYear());
    }

    /**
     * When the constructor is called with not empty arguments,
     * Book Authors can be returned upon calling getAuthors().
     */
    @Test
    public void BookAuthorsIsNotNullWhenConstructorIsInvoked(){
        Assert.assertNotNull(book.getAuthors());
    }

    /**
     * When the constructor is called with not empty arguments,
     * Book Publisher can be returned upon calling getPublisher().
     */
    @Test
    public void BookPublisherIsNotNullWhenConstructorIsInvoked(){ Assert.assertNotNull(book.getPublisher()); }

    /**
     * When the constructor is called with not empty arguments,
     * Book Isbn can be returned upon calling getisbn().
     */
    @Test
    public void BookIsbnIsNotNullWhenConstructorIsInvoked(){
        Assert.assertNotNull(book.getIsbn());
    }

    /**
     * When the constructor is called with name argument null,
     * Illegal Argument Exception is thrown.
     */
    @Test
    public void BookNameThrowsExceptionIfTheGivenValueIsNull(){
        exception.expect(IllegalArgumentException.class);
        book.setName(null);
    }

    /**
     * When the constructor is called with empty name argument,
     * Illegal Argument Exception is thrown.
     */
    @Test
    public void BookNameThrowsExceptionIfTheGivenValueIsEmpty(){
        exception.expect(IllegalArgumentException.class);
        book.setName("");
    }

    /**
     * When the constructor is called with genre argument null,
     * Illegal Argument Exception is thrown.
     */
    @Test
    public void BookGenreThrowsExceptionIfTheGivenValueIsNull(){
        exception.expect(IllegalArgumentException.class);
        book.setGenre(null);
    }

    /**
     * When the constructor is called with empty genre argument,
     * Illegal Argument Exception is thrown.
     */
    @Test
    public void BookGenreThrowsExceptionIfTheGivenValueIsEmpty(){
        exception.expect(IllegalArgumentException.class);
        book.setGenre("");
    }

    /**
     * When the constructor is called with format argument null,
     * Illegal Argument Exception is thrown.
     */
    @Test
    public void BookFormatThrowsExceptionIfTheGivenValueIsNull(){
        exception.expect(IllegalArgumentException.class);
        book.setFormat(null);
    }

    /**
     * When the constructor is called with empty format argument,
     * Illegal Argument Exception is thrown.
     */
    @Test
    public void BookFormatThrowsExceptionIfTheGivenValueIsEmpty(){
        exception.expect(IllegalArgumentException.class);
        book.setFormat("");
    }

    @Test
    public void BookYearThrowsExceptionIfTheGivenValueIsNegative(){
        exception.expect(IllegalArgumentException.class);
        book.setYear(-5);
    }

    /**
     * When the constructor is called with authors argument null,
     * Illegal Argument Exception is thrown.
     */
    @Test
    public void BookAuthorsThrowsExceptionIfTheGivenValueIsNull(){
        exception.expect(IllegalArgumentException.class);
        book.setAuthors(null);
    }

    /**
     * When the constructor is called with empty Set<String> as authors argument,
     * Illegal Argument Exception is thrown.
     */
    @Test
    public void BookAuthorsThrowsExceptionIfTheGivenValueIsEmpty(){
        exception.expect(IllegalArgumentException.class);
        String testAuthors = "";
        book.setAuthors(testAuthors);
    }

    /**
     * When the constructor is called with publisher argument null,
     * Illegal Argument Exception is thrown.
     */
    @Test
    public void BookPublisherThrowsExceptionIfTheGivenValueIsNull(){
        exception.expect(IllegalArgumentException.class);
        book.setPublisher(null);
    }

    /**
     * When the constructor is called with empty publisher argument,
     * Illegal Argument Exception is thrown.
     */
    @Test
    public void BookPublisherThrowsExceptionIfTheGivenValueIsEmpty(){
        exception.expect(IllegalArgumentException.class);
        book.setPublisher("");
    }

    /**
     * When the constructor is called with isbn argument being different than numbers and dash,
     * Illegal Argument Exception is thrown.
     */
    @Test
    public void SetBookIsbnThrowsIllegalArgumentExceptionIfStringIsNotOnlyNumberAndDashes(){
        exception.expect(IllegalArgumentException.class);
        book.setisbn("Incorrect");
    }

    /**
     * When the constructor is called with isbn argument null,
     * Illegal Argument Exception is thrown.
     */
    @Test
    public void SetBookIsbnThrowsIllegalArgumentExceptionIfStringIsNotNull(){
        exception.expect(IllegalArgumentException.class);
        book.setisbn(null);
    }
}