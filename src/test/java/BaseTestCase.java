import org.junit.After;
import org.junit.Before;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import java.io.IOException;

/**
 * Base Test Case has details which uses each Test Case. Open and Close application after each test.
 */
public class BaseTestCase {
    private static String APP_PATH = "/Applications/Spotify.app";
    private static int DELAY = 5000;

    private  App app;

    @Before
    public void setUp() throws IOException {
        openSpotify();
    }

    @After
    public void tearDown() throws FindFailed {
        closeSpotify();
    }

    private void openSpotify() {
        app = App.open(APP_PATH);
    }

    /**
     * Sometimes next test fails during the previous test is closing the application. This delay make sure that app
     * was properly close.
     */
    private void closeSpotify() {
        app.close();
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
        }
    }

}
