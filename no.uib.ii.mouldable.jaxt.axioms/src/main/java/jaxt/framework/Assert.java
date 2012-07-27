package jaxt.framework;

public class Assert extends junit.framework.Assert {

	/**
	 * Used as a marker of a successful test. The opposite of {@code
	 * junit.framework.Assert.fail()} which marks a failed test.
	 */
	public static void succeed() {
	}

	/**
	 * Used as a marker of a successful test. The opposite of {@code
	 * junit.framework.Assert.fail(String s)} which marks a failed test.
	 */
	public static void succeed(String s) {
	}

}
