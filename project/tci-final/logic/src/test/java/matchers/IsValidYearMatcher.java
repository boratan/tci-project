package matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Calendar;

/**
 * Matcher for year
 */
public class IsValidYearMatcher extends TypeSafeMatcher<Integer> {
    /**
     * assert
     */
    @Override
    protected boolean matchesSafely(Integer integer) {
        int length = String.valueOf(integer).length();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return length == 4 && integer > 1900 && integer <= currentYear;
    }

    /**
     * assert
     */
    @Override
    public void describeTo(Description description) {
        description.appendText("valid year");
    }

    /**
     * assert
     */
    public static Matcher<Integer> isValidYearMatcher() {
        return new IsValidYearMatcher();
    }
}


