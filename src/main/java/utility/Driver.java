package utility;


import org.sikuli.script.Screen;

/**
 * A singleton class which creates a <@link>Screen</@link> instance once per application.
 */
public class Driver {
    private static Screen driver;

    public static Screen getInstance() {
        if (driver == null) {
            driver = new Screen();
        }
        return driver;
    }
}
