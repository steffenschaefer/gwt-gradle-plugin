package de.richsource.gradle.plugins.gwt;

import java.io.File;

public class GwtSuperDev extends AbstractGwtActionTask {
	
	private File workDir;

	// private Boolean noserver;
	// private Integer port;
	// private String whitelist;

	@Override
	protected String getClassName() {
		return "com.google.gwt.dev.codeserver.CodeServer";
	}

	@Override
	protected void addArgs() {
		for (File srcDir : getSrc()) {
			// TODO warning if file?
			if(srcDir.exists() && srcDir.isDirectory()) {
				argIfSet("-src", srcDir);
			}
		}
		dirArgIfSet("-workDir", getWorkDir());

		// CodeServer [-bindAddress address] [-port port] [-workDir dir] [-src
		// dir] [module]
		//
		// where
		// -bindAddress The ip address of the code server. Defaults to
		// 127.0.0.1.
		// -port The port where the code server will run.
		// -workDir The root of the directory tree where the code server will
		// write compiler output. If not supplied, a temporary directory will be
		// used.
		// -src A directory containing GWT source to be prepended to the
		// classpath for compiling.
		// and
		// module The GWT modules that the code server should compile. (Example:
		// com.example.MyApp)

		// private boolean noPrecompile = false;
		// private boolean isCompileTest = false;
		// private File workDir;
		// private List<String> moduleNames = new ArrayList<String>();
		// private boolean allowMissingSourceDir = false;
		// private final List<File> sourcePath = new ArrayList<File>();
		// private String bindAddress = "127.0.0.1";
		// private String preferredHost = "localhost";
		// private int port = 9876;
		// private RecompileListener recompileListener = RecompileListener.NONE;
		// // Use the same default as the GWT compiler.
		// private SourceLevel sourceLevel = SourceLevel.DEFAULT_SOURCE_LEVEL;

	}
	
	protected boolean prependSrcToClasspath() {
		return false;
	}

	public File getWorkDir() {
		return workDir;
	}

	public void setWorkDir(File workDir) {
		this.workDir = workDir;
	}
}
