package screens;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;

/**
 * Abstracts Search screen details which use Sikuli client to access Spotify Search functionality. Exposes methods to
 * be used in the tests.
 */
public class SearchScreen extends BaseScreen {
    private final Pattern browseMenu;
    private final Pattern searchField;
    private final Pattern profileName;
    private final Pattern noResultFoundError;
    private final Region searchScreen;

    public SearchScreen() throws FindFailed {
        browseMenu = new Pattern("searchScreen/browseMenu.png");
        searchField = new Pattern("searchScreen/searchArea.png");
        profileName = new Pattern("searchScreen/profileTestName.png");
        noResultFoundError = new Pattern("searchScreen/noResultsFoundMessage.png");
        searchScreen = getDriver().wait(searchField);
    }

    public SearchScreen typeSearch(String text) throws FindFailed {
        searchScreen.click(searchField);
        cleanTextField(searchScreen, searchField);
        searchScreen.type(searchField, text);
        searchScreen.type(Key.ENTER);
        return this;
    }

    public boolean isScreenExists() {
        try {
            getDriver().find(browseMenu);
            return true;
        } catch (FindFailed findFailed) {
            return false;
        }
    }

    public boolean isErrorMessage() {
        try {
            getDriver().find(noResultFoundError);
            return true;
        } catch (FindFailed findFailed) {
            return false;
        }
    }

    /**
     * Method receives an imagePath as a parameter and verifies that a pattern which is preassigned by the image path
     * is displayed.
     * @param imagePath
     * @return true if the image is displayed or false in the other case.
     */
    public boolean isSearchResultDisplayed(String imagePath) {
        try {
            Pattern searchResultByImage = new Pattern(imagePath);
            getDriver().find(searchResultByImage);
            return true;
        } catch (FindFailed findFailed) {
            return false;
        }
    }

    /**
     * Method verifies that Profile Name is displayed and confirms a successful user's login.
     * @return true if Profile name is displayed in the Screen. false if it's not found.
     */
    public boolean isProfileNameDisplayed() {
        try {
            getDriver().find(profileName);
            return true;
        } catch (FindFailed findFailed) {
            return false;
        }
    }
}
