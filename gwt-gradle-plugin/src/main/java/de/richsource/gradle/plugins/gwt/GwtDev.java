package de.richsource.gradle.plugins.gwt;

import java.io.File;
import java.util.concurrent.Callable;

import org.gradle.api.Task;
import org.gradle.api.specs.Spec;

//-noserver        Prevents the embedded web server from running
//-port            Specifies the TCP port for the embedded web server (defaults to 8888)
//-whitelist       Allows the user to browse URLs that match the specified regexes (comma or space separated)
//-blacklist       Prevents the user browsing URLs that match the specified regexes (comma or space separated)
//-logdir          Logs to a file in the given directory, as well as graphically
//-logLevel        The level of logging detail: ERROR, WARN, INFO, TRACE, DEBUG, SPAM, or ALL
//-gen             Debugging: causes normally-transient generated types to be saved in the specified directory
//-bindAddress     Specifies the bind address for the code server and web server (defaults to 127.0.0.1)
//-codeServerPort  Specifies the TCP port for the code server (defaults to 9997)
//-server          Specify a different embedded web server to run (must implement ServletContainerLauncher)
//-startupUrl      Automatically launches the specified URL
//-war             The directory into which deployable output files will be written (defaults to 'war')
//-deploy          The directory into which deployable but not servable output files will be written (defaults to 'WEB-INF/deploy' under the -war directory/jar, and may be the same as the -extra directory/jar)
//-extra           The directory into which extra files, not intended for deployment, will be written
//-workDir         The compiler's working directory for internal use (must be writeable; defaults to a system temp dir)
public class GwtDev extends AbstractGwtTask {
	
	private final GwtDevOptions options = new GwtDevOptions();
	
	public GwtDev() {
		getOutputs().upToDateWhen(new Spec<Task>(){
			@Override
			public boolean isSatisfiedBy(Task task) {
				return false;
			}});
	}

	@Override
	protected String getClassName() {
		return "com.google.gwt.dev.DevMode";
	}
	
	@Override
	protected void addArgs() {
		super.addArgs();

		argIfEnabled(getNoserver(), "-noserver");
		argIfSet("-port", Boolean.TRUE.equals(getAutoPort())? "auto" : getPort());
		argIfSet("-whitelist", getWhitelist());
		argIfSet("-blacklist", getBlacklist());
		argIfSet("-logDir", getLogDir());
		argIfSet("-bindAddress", getBindAddress());
		argIfSet("-codeServerPort", Boolean.TRUE.equals(getAutoCodeServerPort())? "auto" : getCodeServerPort());
		argIfSet("-server", getServer());
		argIfSet("-startupUrl", getStartupUrl());
	}
	
	protected void configure(final GwtDevOptions options) {
		conventionMapping("noserver", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getNoserver();
			}
		});
		conventionMapping("port", new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return options.getPort();
			}
		});
		conventionMapping("autoPort", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getAutoPort();
			}
		});
		conventionMapping("whitelist", new Callable<String>() {
			@Override
			public String call() throws Exception {
				return options.getWhitelist();
			}
		});
		conventionMapping("blacklist", new Callable<String>() {
			@Override
			public String call() throws Exception {
				return options.getBlacklist();
			}
		});
		conventionMapping("logDir", new Callable<File>() {
			@Override
			public File call() throws Exception {
				return options.getLogDir();
			}
		});
		conventionMapping("bindAddress", new Callable<String>() {
			@Override
			public String call() throws Exception {
				return options.getBindAddress();
			}
		});
		conventionMapping("codeServerPort", new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return options.getCodeServerPort();
			}
		});
		conventionMapping("autoCodeServerPort", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getAutoCodeServerPort();
			}
		});
		conventionMapping("server", new Callable<String>() {
			@Override
			public String call() throws Exception {
				return options.getServer();
			}
		});
		conventionMapping("startupUrl", new Callable<String>() {
			@Override
			public String call() throws Exception {
				return options.getStartupUrl();
			}
		});
	}

	public Boolean getNoserver() {
		return options.getNoserver();
	}

	public void setNoserver(Boolean noserver) {
		options.setNoserver(noserver);
	}

	public Integer getPort() {
		return options.getPort();
	}

	public void setPort(Integer port) {
		options.setPort(port);
	}

	public String getWhitelist() {
		return options.getWhitelist();
	}

	public void setWhitelist(String whitelist) {
		options.setWhitelist(whitelist);
	}

	public String getBlacklist() {
		return options.getBlacklist();
	}

	public void setBlacklist(String blacklist) {
		options.setBlacklist(blacklist);
	}

	public File getLogDir() {
		return options.getLogDir();
	}

	public void setLogDir(File logDir) {
		options.setLogDir(logDir);
	}

	public String getBindAddress() {
		return options.getBindAddress();
	}

	public void setBindAddress(String bindAddress) {
		options.setBindAddress(bindAddress);
	}

	public Integer getCodeServerPort() {
		return options.getCodeServerPort();
	}

	public void setCodeServerPort(Integer codeServerPort) {
		options.setCodeServerPort(codeServerPort);
	}

	public String getServer() {
		return options.getServer();
	}

	public void setServer(String server) {
		options.setServer(server);
	}

	public String getStartupUrl() {
		return options.getStartupUrl();
	}

	public void setStartupUrl(String startupUrl) {
		options.setStartupUrl(startupUrl);
	}

	public Boolean getAutoPort() {
		return options.getAutoPort();
	}

	public void setAutoPort(Boolean autoPort) {
		options.setAutoPort(autoPort);
	}

	public Boolean getAutoCodeServerPort() {
		return options.getAutoCodeServerPort();
	}

	public void setAutoCodeServerPort(Boolean autoCodeServerPort) {
		options.setAutoCodeServerPort(autoCodeServerPort);
	}
}
