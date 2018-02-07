import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.sikuli.script.FindFailed;
import screens.LoginScreen;
import screens.SearchScreen;
import java.util.Arrays;
import java.util.Collection;
import static junit.framework.Assert.assertTrue;

@RunWith(Parameterized.class)
public class SearchTest extends BaseTestCase {

    @Parameterized.Parameter
    public SearchTest.SearchTestData data;

    /**
     * The Method generates 3 objects to search by artists, album and song.
     * @return Collection of SearchTestData.
     */
    @Parameterized.Parameters(name = "{index}: {0})")
    public static Collection<SearchTest.SearchTestData> data() {

        return Arrays.asList(
                new SearchTest.SearchTestData[]{
                        new SearchTest.SearchTestData("veraTest@gmail.com", "12345678",
                                SearchTestData.SearchObject.ARTISTS, "sam",
                                "searchResultByArtistSam.png"),
                        new SearchTest.SearchTestData("veraTest@gmail.com", "12345678",
                                SearchTestData.SearchObject.ALBUM, "25",
                                "searchResultByAlbum25.png"),
                        new SearchTest.SearchTestData("veraTest@gmail.com", "12345678",
                                SearchTestData.SearchObject.SONG, "too good",
                                "searchResultBySongTooGood.png"),
                        new SearchTest.SearchTestData("veraTest@gmail.com", "12345678",
                                SearchTestData.SearchObject.ERROR, "perfectttttt",
                                null)
                });
    }

    /**
     * Test will be called 4 times for each Test object to verify search by Artist, Song and Album and one time when
     * nothing is found. Test will compare a result with an expected image.
     */
    @Test
    public void testSearch() throws FindFailed {
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.turnRememberMeOffIfNeeded();
        loginScreen.fillLoginForm(data.login, data.password).clickLogin();
        SearchScreen searchScreen = new SearchScreen();
        assertTrue(searchScreen.isScreenExists());
        searchScreen.typeSearch(data.searchValue);
        if (data.searchObject == SearchTestData.SearchObject.ERROR){
            assertTrue(searchScreen.isErrorMessage());
        }
        else {
            assertTrue(searchScreen.isSearchResultDisplayed(data.expectedImagePath));
        }
    }

    /**
     * Test data object to generate test data specially for Search Test:
     * login, password are for correct Log In.
     * searchObject indicates by which object will be searched: Artist, Song, Album or Error message is expected.
     * searchValue is a test data.
     * expected image is a path to an expected image pattern.
     */
    private static class SearchTestData {
        private String login;
        private String password;
        private SearchObject searchObject;
        private String searchValue;
        private String expectedImagePath;
        public SearchTestData(String login, String password, SearchObject searchObject, String searchValue,
                              String expectedImagePath) {
            this.login = login;
            this.password = password;
            this.searchObject = searchObject;
            this.searchValue = searchValue;
            this.expectedImagePath = expectedImagePath;
        }

        @Override
        public String toString() {
            return "PlayTestData{" +
                    "login='" + login + '\'' +
                    ", password='" + password + '\'' +
                    ", searchObject=" + searchObject +
                    ", searchValue='" + searchValue + '\'' +
                    ", expectedImagePath='" + expectedImagePath + '\'' +
                    '}';
        }

        enum SearchObject {
            ARTISTS, SONG, ALBUM, ERROR;
        }
    }
}
