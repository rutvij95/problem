package socialNetworkPackage;

import static org.junit.Assert.fail;

public class TestBase {
	static void checkAmount(int actual, int minimum, String message) {
		if (actual < minimum) {
			fail(message + " (" + actual + "/" + minimum + ")");
		}
	}
}
