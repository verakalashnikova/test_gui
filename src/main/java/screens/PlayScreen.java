package screens;

import org.sikuli.script.*;

/**
 * Abstracts Play Screen details which use sikuli client to access Play Track functionality. Exposes methods to be
 * used in the tests.
 */
public class PlayScreen extends BaseScreen {
    private final Pattern playTracksArea;
    private final Region playTracksScreen;
    private final Pattern playButton;
    private final Pattern pauseButton;
    private final Pattern topResultTitle;
    private final Pattern playNextButton;
    private final Pattern playingTrackIndicator;
    private final Pattern playingTrackIndicatorDropPlace;
    private final Pattern playingTrackLine;
    private final Pattern playingTrackIndicatorDropPlaceInBegining;
    private Region topResult;

    public PlayScreen() throws FindFailed {
        playTracksArea = new Pattern("playScreen/playTracksArea.png").similar((float) 0.5);
        playTracksScreen = getDriver().wait(playTracksArea);
        playButton = new Pattern("playScreen/playTrackButton.png");
        playingTrackIndicator = new Pattern("playScreen/playingTrackIndicator.png");
        playingTrackIndicatorDropPlace = new Pattern("playScreen/playingTrackIndicatorDropPlace.png");
        pauseButton = new Pattern("playScreen/pauseButton.png");
        topResultTitle = new Pattern("playScreen/topResultTitle.png");
        playNextButton = new Pattern("playScreen/playNextButton.png");
        playingTrackLine = new Pattern("playScreen/playingTrackLine.png").similar((float) 0.5);
        playingTrackIndicatorDropPlaceInBegining = new Pattern("playScreen/playingTrackIndicatorDropPlaceInBegining.png");
    }

    /**
     * The Method Clicks Play button. Sometimes it could be so fast and need to wait until Pause button is displayed
     * to be sure that Save button is pressed.
     * @return
     */
    public PlayScreen clickPlayButton() throws FindFailed {
        Region playRegion = playTracksScreen.wait(playButton);
        playRegion.waitVanish(playButton);
        playRegion.click();
        return this;
    }

    public PlayScreen clickPauseButton() throws FindFailed {
        playTracksScreen.wait(pauseButton).click();
        return this;
    }

    /**
     * Waiting Top Result title and hover under it to be sure to click on image and don't rely on particular file.
     * @return a region of a top result.
     */
    public Region hoverTopResult() throws FindFailed {
        // Calculated value to click under Top Result title and don't be tied to particular search result.
        topResult = getDriver().wait(topResultTitle).below(80);
        topResult.hover();
        return topResult;
    }

    public void playTopResult() throws FindFailed {
        if (topResult != null) {
            topResult.click();
        }
    }

    /**
     * Assuming if a track is playing Pause button should be displayed.
     */
    public boolean isTrackPlaying() {
        try {
            playTracksScreen.waitVanish(playButton);
            playTracksScreen.wait(pauseButton);
            return true;
        } catch (FindFailed findFailed) {
            return false;
        }
    }

    /**
     * Move playing indicator forward.
     * @throws FindFailed exception if patters are not found.
     */
    public PlayScreen dragAndDropPlayingIndicatorForward() throws FindFailed {
        Region line = playTracksScreen.find(playingTrackLine);
        line.hover();
        line.dragDrop(playingTrackIndicator, playingTrackIndicatorDropPlace);
        return this;
    }

    /**
     * Move playing indicator to the beginning.
     * @throws FindFailed exception if patters are not found.
     */
    public PlayScreen dragAndDropPlayingIndicatorBack() throws FindFailed {
        Region progressBar = playTracksScreen.find(playingTrackLine);
        progressBar.hover();
        progressBar.dragDrop(playingTrackIndicator, playingTrackIndicatorDropPlaceInBegining);
        return this;
    }

    public PlayScreen clickPlayNextTrack() throws FindFailed {
        playTracksScreen.click(playNextButton);
        return this;
    }
}
