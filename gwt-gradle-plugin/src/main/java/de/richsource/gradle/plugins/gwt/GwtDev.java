/**
 * Copyright (C) 2013 Steffen Schaefer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.richsource.gradle.plugins.gwt;

import java.io.File;
import java.util.concurrent.Callable;

import org.gradle.api.Task;
import org.gradle.api.specs.Spec;

import de.richsource.gradle.plugins.gwt.internal.GwtDevOptionsImpl;

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
public class GwtDev extends AbstractGwtTask implements GwtDevOptions {
	
	private final GwtDevOptions options = new GwtDevOptionsImpl();
	
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
		argIfSet("-logdir", getLogDir());
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

	/** {@inheritDoc} */
	@Override
	public Boolean getNoserver() {
		return options.getNoserver();
	}

	/** {@inheritDoc} */
	@Override
	public void setNoserver(Boolean noserver) {
		options.setNoserver(noserver);
	}

	/** {@inheritDoc} */
	@Override
	public Integer getPort() {
		return options.getPort();
	}

	/** {@inheritDoc} */
	@Override
	public void setPort(Integer port) {
		options.setPort(port);
	}

	/** {@inheritDoc} */
	@Override
	public String getWhitelist() {
		return options.getWhitelist();
	}

	/** {@inheritDoc} */
	@Override
	public void setWhitelist(String whitelist) {
		options.setWhitelist(whitelist);
	}

	/** {@inheritDoc} */
	@Override
	public String getBlacklist() {
		return options.getBlacklist();
	}

	/** {@inheritDoc} */
	@Override
	public void setBlacklist(String blacklist) {
		options.setBlacklist(blacklist);
	}

	/** {@inheritDoc} */
	@Override
	public File getLogDir() {
		return options.getLogDir();
	}

	/** {@inheritDoc} */
	@Override
	public void setLogDir(File logDir) {
		options.setLogDir(logDir);
	}

	/** {@inheritDoc} */
	@Override
	public String getBindAddress() {
		return options.getBindAddress();
	}

	/** {@inheritDoc} */
	@Override
	public void setBindAddress(String bindAddress) {
		options.setBindAddress(bindAddress);
	}

	/** {@inheritDoc} */
	@Override
	public Integer getCodeServerPort() {
		return options.getCodeServerPort();
	}

	/** {@inheritDoc} */
	@Override
	public void setCodeServerPort(Integer codeServerPort) {
		options.setCodeServerPort(codeServerPort);
	}

	/** {@inheritDoc} */
	@Override
	public String getServer() {
		return options.getServer();
	}

	/** {@inheritDoc} */
	@Override
	public void setServer(String server) {
		options.setServer(server);
	}

	/** {@inheritDoc} */
	@Override
	public String getStartupUrl() {
		return options.getStartupUrl();
	}

	/** {@inheritDoc} */
	@Override
	public void setStartupUrl(String startupUrl) {
		options.setStartupUrl(startupUrl);
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getAutoPort() {
		return options.getAutoPort();
	}

	/** {@inheritDoc} */
	@Override
	public void setAutoPort(Boolean autoPort) {
		options.setAutoPort(autoPort);
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getAutoCodeServerPort() {
		return options.getAutoCodeServerPort();
	}

	/** {@inheritDoc} */
	@Override
	public void setAutoCodeServerPort(Boolean autoCodeServerPort) {
		options.setAutoCodeServerPort(autoCodeServerPort);
	}
}
