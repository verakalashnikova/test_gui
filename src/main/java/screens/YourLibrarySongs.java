package screens;

import org.sikuli.script.*;

import java.util.Iterator;

/*
 * Add songs to Your Library class.
 */
public class YourLibrarySongs extends BaseScreen {
    private final Pattern addToYourLibrary;
    private final Pattern checkInSongsTable;
    private final Pattern itemsInLibraryTableHeader;
    private final Pattern songsMenuItem;
    private final Pattern songsTitle;
    private final Pattern filterField;
    private final Pattern actionOptionsForLibraryItem;
    private final Pattern removeFromYourLibraryOption;

    public YourLibrarySongs() throws FindFailed {
        addToYourLibrary = new Pattern("yourLibrary/addToYourLibrary.png");
        checkInSongsTable = new Pattern("yourLibrary/checkInSongsTable.png").similar((float) 0.99);
        itemsInLibraryTableHeader = new Pattern("yourLibrary/itemsInLibraryTableHeader.png");
        songsMenuItem = new Pattern("yourLibrary/songsMenuItem.png");
        songsTitle = new Pattern("yourLibrary/songsTitle.png");
        filterField = new Pattern("yourLibrary/filterField.png");
        actionOptionsForLibraryItem = new Pattern("yourLibrary/actionOptionsForLibraryItem.png");
        removeFromYourLibraryOption = new Pattern("yourLibrary/removeFromYourLibraryOption.png");
    }

    public YourLibrarySongs addToYourLibrary() throws FindFailed {
        getDriver().find(addToYourLibrary).click();
        return this;
    }

    public YourLibrarySongs clickYourLibrary() throws FindFailed {
        getDriver().find(songsMenuItem).click();
        getDriver().wait(songsTitle);
        return this;
    }

    /*
     * The Method calculates items in Your Library calculating all 'v' marks.
     */
    public int calculateItemsInYourLibrary() {
        Iterator<Match> allChecks;
        try {
            allChecks = getDriver().findAll(checkInSongsTable);
        } catch (FindFailed findFailed) {
            return 0;
        }
        int counter = 0;
        while (allChecks.hasNext()) {
            allChecks.next();
            counter += 1;
        }
        return counter;
    }

    public YourLibrarySongs typeValueToFilterField(String value) throws FindFailed {
        getDriver().type(filterField, value + Key.ENTER);
        return this;
    }

    public YourLibrarySongs searchByFilter(String value) throws FindFailed {
        Region filterRegion = getDriver().find(filterField);
        cleanTextField(filterRegion, filterField);
        typeValueToFilterField(value);
        return this;
    }

    /**
     * The Method delete added song from Your Library for the clean next test run.
     * @throws FindFailed
     */
    public YourLibrarySongs cleanYourLibrary() throws FindFailed {
        Region filterRegion = getDriver().find(itemsInLibraryTableHeader);
        // hard coded calculated value to not be tied to particular search result image.
        Region record = filterRegion.below(50);
        record.hover(record);
        record.rightClick();
        Region actionOptionsDialog = getDriver().find(actionOptionsForLibraryItem);
        actionOptionsDialog.find(removeFromYourLibraryOption).click();
        return this;
    }
}
