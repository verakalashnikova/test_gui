import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.sikuli.script.FindFailed;
import screens.*;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

@RunWith(Parameterized.class)
public class YourLibrarySongsTest extends BaseTestCase {
    @Parameterized.Parameter
    public YourLibrarySongsTest.YourLibrarySongsTestData data;

    @Parameterized.Parameters(name = "{index}: {0})")
    public static Collection<YourLibrarySongsTest.YourLibrarySongsTestData> data() {
        return Arrays.asList(new YourLibrarySongsTest.YourLibrarySongsTestData[]{
                new YourLibrarySongsTest.YourLibrarySongsTestData("veraTest@gmail.com", "12345678",
                        "way we were")});
    }

    /**
     * Test to verify Add songs to Your Library.
     * 1. Log in using correct credentials generated with YourLibrarySongsTestData object.
     * 2. Search by a song. Song name is taken from YourLibrarySongsTestData object.
     * 3. Hover over search result and click Play.
     * 4. Click "+" add a track to Your Library.
     * 5. Click Songs from Your Library (Left menu).
     * 6. Search by song name in a filter field in Your Library.
     * 7. Verify that only one record is displayed.
     * 8. Hover over that record, right click and Delete from Your library.
     * 9. Verify that no records are displayed.
     */
    @Test
    public void testVerifyAddSongsToYourLibrary() throws FindFailed, InterruptedException {
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.turnRememberMeOffIfNeeded();
        loginScreen = loginScreen.fillLoginForm(data.login, data.password);
        loginScreen.clickLogin();
        SearchScreen searchScreen = new SearchScreen();
        assertTrue(searchScreen.isScreenExists());
        searchScreen.typeSearch(data.searchValue);
        assertFalse(searchScreen.isErrorMessage());
        PlayScreen playScreen = new PlayScreen();
        playScreen.hoverTopResult();
        playScreen.playTopResult();
        assertTrue(playScreen.isTrackPlaying());
        YourLibrarySongs yourLibrarySongs = new YourLibrarySongs();
        yourLibrarySongs.addToYourLibrary();
        yourLibrarySongs.clickYourLibrary();
        yourLibrarySongs.searchByFilter(data.searchValue);
        assertEquals(1, yourLibrarySongs.calculateItemsInYourLibrary());
        yourLibrarySongs.cleanYourLibrary();
        assertEquals(0, yourLibrarySongs.calculateItemsInYourLibrary());
        playScreen.clickPauseButton();
    }

    /**
     * Test data object to generate test data specially for YourLibrarySongsTest.
     * Correct Login and password to Log in to the application and search value for the test.
     */
    private static class YourLibrarySongsTestData {
        private String login;
        private String password;
        private String searchValue;

        public YourLibrarySongsTestData(String login, String password, String searchValue) {
            this.login = login;
            this.password = password;
            this.searchValue = searchValue;
        }

        @Override
        public String toString() {
            return "PlaylistTestData{" +
                    "login='" + login + '\'' +
                    ", password='" + password + '\'' +
                    ", searchValue='" + searchValue + '\'' +
                    '}';
        }

    }
}
