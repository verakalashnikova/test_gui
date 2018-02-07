package screens;

import org.sikuli.script.*;
import utility.Driver;

/**
 * Base class for all screens which are controlled in the tests.
 */
public class BaseScreen {
    public static String IMAGE_PATH = "src/main/resources/";

    public BaseScreen() {
        ImagePath.setBundlePath(IMAGE_PATH);
    }

    protected Screen getDriver() {
        return Driver.getInstance();
    }

    /**
     * Simulates pressing Cmd+A sequence to clear text field contents.
     * <p>
     * TODO: Note that this is not cross-platform safe. Implement a robust
     * way to clear text field for all platforms.
     */
    protected void cleanTextField(Region region, Pattern textField) throws FindFailed {
        region.click(textField);
        region.type("a", Key.CMD);
        region.type(Key.BACKSPACE);
    }

}
