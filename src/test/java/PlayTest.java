import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.sikuli.script.FindFailed;
import screens.LoginScreen;
import screens.PlayScreen;
import screens.SearchScreen;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.Assert.assertTrue;

@RunWith(Parameterized.class)
public class PlayTest extends BaseTestCase {
    @Parameterized.Parameter
    public PlayTest.PlayTestData data;

    @Parameterized.Parameters(name = "{index}: {0})")
    public static Collection<PlayTest.PlayTestData> data(){
        return Arrays.asList(new PlayTestData[]{new PlayTestData("veraTest@gmail.com", "12345678",
                "shape")});
    }
    /**
     * Test verifies playing tracks after searching a song and click on an image under Top Results.
     * 1. Log in using correct credentials generated with YourLibrarySongsTestData object.
     * 2. Search by a song. Song name is taken from PlayTestData object.
     * 3. Hover over search result and click Play.
     * 4. Verify that a song is playing.
     * 5. Hover over search result and click Pause.
     */
    @Test
    public void testPlayingTracksByCLickingUnderTopResult() throws FindFailed {
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.turnRememberMeOffIfNeeded();
        loginScreen = loginScreen.fillLoginForm(data.login, data.password);
        loginScreen.clickLogin();
        SearchScreen searchScreen = new SearchScreen();
        assertTrue(searchScreen.isScreenExists());
        searchScreen.typeSearch(data.searchValue);
        PlayScreen playScreen = new PlayScreen();
        playScreen.hoverTopResult();
        playScreen.playTopResult();
        assertTrue(playScreen.isTrackPlaying());
        playScreen.hoverTopResult();
        playScreen.playTopResult();
    }

    /**
     * Test verifies playing tracks by clicking Play button and moving track playing indicator.
     *  1. Log in using correct credentials generated with YourLibrarySongsTestData object.
     *  2. Click Play button to invoke playing a track.
     *  3. Move track playing indicator to the right.
     *  4. Verify that a song is playing.
     *  5. Move track playing indicator to the left.
     *  6. Verify that a song is playing.
     *  7. Click on Pause button.
     */
    @Test
    public void testPlayingTracksRegularWay() throws FindFailed {
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.turnRememberMeOffIfNeeded();
        loginScreen = loginScreen.fillLoginForm(data.login, data.password);
        loginScreen.clickLogin();
        PlayScreen playScreen = new PlayScreen();
        playScreen.clickPlayButton();
        playScreen.isTrackPlaying();
        playScreen.dragAndDropPlayingIndicatorForward();
        playScreen.isTrackPlaying();
        playScreen.dragAndDropPlayingIndicatorBack();
        playScreen.isTrackPlaying();
        playScreen.clickPauseButton();
    }

    /*
    Test verifies playing tracks clicking Play button and click Next track button.
    1. Log in using correct credentials generated with YourLibrarySongsTestData object.
    2. Click Play button to invoke playing a track.
    3. Click play next track button.
    4. Verify that a song is playing.
    5. Click on Pause button.
     */
    @Test
    public void testPlayingTracksChangeTracks() throws FindFailed, InterruptedException {
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.turnRememberMeOffIfNeeded();
        loginScreen = loginScreen.fillLoginForm(data.login, data.password);
        loginScreen.clickLogin();
        PlayScreen playScreen = new PlayScreen();
        playScreen.clickPlayButton();
        playScreen.isTrackPlaying();
        playScreen.clickPlayNextTrack();
        playScreen.isTrackPlaying();
        playScreen.clickPauseButton();
    }

    /**
     * Test data object to generate test data specially for Play Test:
     * login, password are for correct Log In.
     * searchValue is a test data to verify Playing track functionality.
     */
    static class PlayTestData {
        private String login;
        private String password;
        private String searchValue;
        public PlayTestData(String login, String password, String searchValue) {
            this.login = login;
            this.password = password;
            this.searchValue = searchValue;
        }

        @Override
        public String toString() {
            return "PlayTestData{" +
                    "login='" + login + '\'' +
                    ", password='" + password + '\'' +
                    ", searchValue='" + searchValue + '\'' +
                    '}';
        }
    }
}
