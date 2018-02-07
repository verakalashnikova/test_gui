import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.sikuli.script.FindFailed;
import screens.LoginScreen;
import screens.SearchScreen;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.Assert.fail;
import static junit.framework.TestCase.assertTrue;

@RunWith(Parameterized.class)
public class LoginTest extends BaseTestCase {
    @Parameter
    public LoginTestData data;

    /**
     * Method generates 2 test data for correct and incorrect login.
     * @return Collection of LoginTestData.
     */
    @Parameterized.Parameters(name = "{index}: {0})")
    public static Collection<LoginTestData> data() {

        return Arrays.asList(
                new LoginTestData[]{new LoginTestData("test123", "123",
                        LoginTestData.Expectation.FAILURE),
                        new LoginTestData("veraTest@gmail.com", "12345678",
                                LoginTestData.Expectation.SUCCESS)

                });
    }

    /**
     * Test runs 2 times for each data from LoginTestData. If login data is correct verify that User Profile is
     * displayed in a new Screen. If login data is incorrect, an error message is expected.
     * @throws FindFailed exception if patters are not found.
     */
    @Test
    public void testLogin() throws FindFailed {
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.turnRememberMeOffIfNeeded();
        loginScreen.fillLoginForm(data.login, data.password).clickLogin();
        if (data.expectation == LoginTestData.Expectation.FAILURE) {
            assertTrue(loginScreen.isErrorMessage());
        } else if (data.expectation == LoginTestData.Expectation.SUCCESS) {

            SearchScreen searchScreen = new SearchScreen();
            assertTrue(searchScreen.isScreenExists());
            assertTrue(searchScreen.isProfileNameDisplayed());
        } else {
            fail("Unexpected expectation");
        }
    }

    /**
     * Test data object to generate test data specially for LoginTest: login, password and expectation (success,
     * failure).
     */
    private static class LoginTestData {
        private String login;
        private String password;
        private Expectation expectation;

        public LoginTestData(String login, String password, Expectation expectation) {
            this.login = login;
            this.password = password;
            this.expectation = expectation;
        }

        @Override
        public String toString() {
            return "LoginTest{" +
                    "login='" + login + '\'' +
                    ", password='" + password + '\'' +
                    ", expectation=" + expectation +
                    '}';
        }

        enum Expectation {
            SUCCESS, FAILURE;
        }
    }
}
