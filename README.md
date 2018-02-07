# README #

### What is this repository for? ###

* Automated GUI-test for Spotify client. Tests have been written in Java, using Sikuli, JUnit, Maven.


### Contribution guidelines ###
### Prerequisites ###
* Spotify GUI client should be downloaded and installed.
* Spotify backend should be always online.
* Tests should be run on Mac platform.
* If tests are going to be run on Windows platform you need to do:
	* Change APP_PATH variable in BaseTestCase class
	* Change Key for cleanTextField method in BaseScreen class
### Running the tests ###
* Switch to a repository's directory
* To run all tests suites: mvn test
* To run Login tests: mvn test -Dtest=LoginTest
* To run Search tests: mvn test -Dtest=SearchTest
* To verify playing tracks: mvn test -Dtest=PlayTest
* To run Additing songs to Your Library: mvn test -Dtest=YourLibrarySongsTest

### Tests description: ###
* Tests do the following:
	* Verify that the login process using a valid open/free account works.
	* Verify that the login process using an invalid open/free account fails.
	* Verify search scenarios by:
		* an artist
		* a song
		* an album.
	* Verify that playing tracks works by:
		* Clicking on a searched top result
		* Play button and move playing indicator forward and backward work
		* Playing the next track works
	* Verify that additing tracks to Your Library works.

### Test Data: ###
* Creates Test Data object and parametrizes it for each Test class.

## Login Test: ##
* Test opens Spotify app, enters login and password for valid user and verifies that Profile icon is displayed in 
* the Main Screen. Closes Spotify app.
* Test opens Spotify app, enters invalid login and password and verifies that an Error message is displayed. Closes Spotify app.

## Search Test: ##
* Verifies a seach by an Artist and compare an image under Top Result with the prepared image from Test Data object.
* Verifies a seach by a Song and compare an image under Top Result with the prepared image from Test Data object.
* Verifies a seach by an Album and compare an image under Top Result with the prepared image from Test Data object.
* Verifies a seach by an incorrect value and compare it with an expected Error mesage.

## Playing tracks Test: ##
* Verifies correct playing tracks after searching a song and click on an image under Top Results.
* Verifies correct playing tracks by clicking Play button and moving track playing indicator.
* Verifies correct playing tracks clicking Play button and click Next track button.

## Adding Songs to Your Library Test: ##
* I added the test because I think it's the next most important functionality on top of tests which were already implemented.

* TODO: Verify that songs from My Library can be played.

### Who do I talk to? ###
* Vera Kalashnikova
