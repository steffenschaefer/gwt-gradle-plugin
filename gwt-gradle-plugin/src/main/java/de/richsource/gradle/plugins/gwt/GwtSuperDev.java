package de.richsource.gradle.plugins.gwt;

import java.io.File;

//CodeServer [-bindAddress address] [-port port] [-workDir dir] [-src
// dir] [module]
//
// where
// -bindAddress The ip address of the code server. Defaults to 127.0.0.1.
// -port The port where the code server will run.
// -workDir The root of the directory tree where the code server will write compiler output. If not supplied, a temporary directory will be used.
// -src A directory containing GWT source to be prepended to the classpath for compiling.
// and
// module The GWT modules that the code server should compile. (Example: com.example.MyApp)
public class GwtSuperDev extends AbstractGwtActionTask {
	
	private File workDir;

	private String bindAddress;
	private Integer port;
	private Boolean noPrecompile;

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
		argIfSet("-bindAddress", getBindAddress());
		argIfSet("-port", getPort());
		argIfEnabled(getNoPrecompile(), "-noprecompile");
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

	public String getBindAddress() {
		return bindAddress;
	}

	public void setBindAddress(String bindAddress) {
		this.bindAddress = bindAddress;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Boolean getNoPrecompile() {
		return noPrecompile;
	}

	public void setNoPrecompile(Boolean noPrecompile) {
		this.noPrecompile = noPrecompile;
	}
}
