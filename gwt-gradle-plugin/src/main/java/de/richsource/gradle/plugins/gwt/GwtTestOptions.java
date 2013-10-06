package de.richsource.gradle.plugins.gwt;

//Google Web Toolkit 2.5.1
//JUnitShell [-port port-number | "auto"] [-whitelist whitelist-string] [-blacklist blacklist-string] [-logdir directory] [-logLevel level] [-gen dir] [-codeServerPort port-number | "auto"] [-war dir] [-deploy dir] [-extra dir] [-workDir dir] [-style style] [-ea] [-XdisableClassMetadata] [-XdisableCastChecking] [-draftCompile] [-localWorkers count] [-prod] [-testMethodTimeout minutes] [-testBeginTimeout minutes] [-runStyle runstyle[:args]] [-notHeadless] [-standardsMode] [-quirksMode] [-Xtries 1] [-userAgents userAgents]
//where 
//  -port                   Specifies the TCP port for the embedded web server (defaults to 8888)
//  -whitelist              Allows the user to browse URLs that match the specified regexes (comma or space separated)
//  -blacklist              Prevents the user browsing URLs that match the specified regexes (comma or space separated)
//  -logdir                 Logs to a file in the given directory, as well as graphically
//  -logLevel               The level of logging detail: ERROR, WARN, INFO, TRACE, DEBUG, SPAM, or ALL
//  -gen                    Debugging: causes normally-transient generated types to be saved in the specified directory
//  -codeServerPort         Specifies the TCP port for the code server (defaults to 9997)
//  -war                    The directory into which deployable output files will be written (defaults to 'war')
//  -deploy                 The directory into which deployable but not servable output files will be written (defaults to 'WEB-INF/deploy' under the -war directory/jar, and may be the same as the -extra directory/jar)
//Gradle Worker 1 finished executing tests.
//  -extra                  The directory into which extra files, not intended for deployment, will be written
//  -workDir                The compiler's working directory for internal use (must be writeable; defaults to a system temp dir)
//  -style                  Script output style: OBF[USCATED], PRETTY, or DETAILED (defaults to OBF)
//  -ea                     Debugging: causes the compiled output to check assert statements
//  -XdisableClassMetadata  EXPERIMENTAL: Disables some java.lang.Class methods (e.g. getName())
//  -XdisableCastChecking   EXPERIMENTAL: Disables run-time checking of cast operations
//  -draftCompile           Enable faster, but less-optimized, compilations
//  -localWorkers           The number of local workers to use when compiling permutations
//  -prod                   Causes your test to run in production (compiled) mode (defaults to development mode)
//  -testMethodTimeout      Set the test method timeout, in minutes
//  -testBeginTimeout       Set the test begin timeout (time for clients to contact server), in minutes
//  -runStyle               Selects the runstyle to use for this test.  The name is a suffix of com.google.gwt.junit.RunStyle or is a fully qualified class name, and may be followed with a colon and an argument for this runstyle.  The specified class mustextend RunStyle.
//  -notHeadless            Causes the log window and browser windows to be displayed; useful for debugging
//  -standardsMode          Run each test using an HTML document in standards mode (rather than quirks mode)
//  -quirksMode             Run each test using an HTML document in quirks mode (rather than standards mode)
//  -Xtries                 EXPERIMENTAL: Sets the maximum number of attempts for running each test method
//  -userAgents             Specify the user agents to reduce the number of permutations for remote browser tests; e.g. ie6,ie8,safari,gecko1_8,opera
public class GwtTestOptions {
	private boolean hasGwtTests = false;
	private boolean prod = false;

	public boolean isHasGwtTests() {
		return hasGwtTests;
	}

	public void setHasGwtTests(boolean hasGwtTests) {
		this.hasGwtTests = hasGwtTests;
	}

	public boolean isProd() {
		return prod;
	}

	public void setProd(boolean prod) {
		this.prod = prod;
	}
}
