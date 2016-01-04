/**
 * Copyright (C) 2013-2014 Steffen Schaefer
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
import org.gradle.api.internal.ConventionMapping;
import org.gradle.api.internal.IConventionAware;
import org.gradle.api.specs.Spec;

import de.richsource.gradle.plugins.gwt.internal.GwtDevOptionsImpl;

public class GwtDev extends AbstractGwtTask implements GwtDevOptions {
	
	private final GwtDevOptions options = new GwtDevOptionsImpl();
	
	private String modulePathPrefix;
	
	public GwtDev() {
		super("com.google.gwt.dev.DevMode");
		
		getOutputs().upToDateWhen(new Spec<Task>(){
			@Override
			public boolean isSatisfiedBy(Task task) {
				return false;
			}});
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
		argOnOff(getSuperDevMode(), "-superDevMode", "-nosuperDevMode");
		argOnOff(getStartServer(), "-startServer", "-nostartServer");
		argIfSet("-XmethodNameDisplayMode", getMethodNameDisplayMode());
		argIfSet("-modulePathPrefix", getModulePathPrefix());
	}
	
	protected void configure(final GwtPluginExtension gwtPluginExtension) {
		final GwtDevOptions options = gwtPluginExtension.getDev();
		ConventionMapping conventionMapping =((IConventionAware)this).getConventionMapping();
		conventionMapping.map("noserver", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getNoserver();
			}
		});
		conventionMapping.map("port", new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return options.getPort();
			}
		});
		conventionMapping.map("autoPort", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getAutoPort();
			}
		});
		conventionMapping.map("whitelist", new Callable<String>() {
			@Override
			public String call() throws Exception {
				return options.getWhitelist();
			}
		});
		conventionMapping.map("blacklist", new Callable<String>() {
			@Override
			public String call() throws Exception {
				return options.getBlacklist();
			}
		});
		conventionMapping.map("logDir", new Callable<File>() {
			@Override
			public File call() throws Exception {
				return options.getLogDir();
			}
		});
		conventionMapping.map("bindAddress", new Callable<String>() {
			@Override
			public String call() throws Exception {
				return options.getBindAddress();
			}
		});
		conventionMapping.map("codeServerPort", new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return options.getCodeServerPort();
			}
		});
		conventionMapping.map("autoCodeServerPort", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getAutoCodeServerPort();
			}
		});
		conventionMapping.map("server", new Callable<String>() {
			@Override
			public String call() throws Exception {
				return options.getServer();
			}
		});
		conventionMapping.map("startupUrl", new Callable<String>() {
			@Override
			public String call() throws Exception {
				return options.getStartupUrl();
			}
		});
		conventionMapping.map("superDevMode", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getSuperDevMode();
			}
		});
		conventionMapping.map("startServer", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getStartServer();
			}
		});
		conventionMapping.map("modulePathPrefix", new Callable<String>() {
			@Override
			public String call() throws Exception {
				return gwtPluginExtension.getModulePathPrefix();
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
	
	/** {@inheritDoc} */
	@Override
	public Boolean getSuperDevMode() {
		return options.getSuperDevMode();
	}
	
	/** {@inheritDoc} */
	@Override
	public void setSuperDevMode(Boolean superDevMode) {
		options.setSuperDevMode(superDevMode);
	}
	
	/** {@inheritDoc} */
	@Override
	public Boolean getStartServer() {
		return options.getStartServer();
	}
	
	/** {@inheritDoc} */
	@Override
	public void setStartServer(Boolean startServer) {
		options.setStartServer(startServer);
	}

	public String getModulePathPrefix() {
		return modulePathPrefix;
	}

	/**
	 * Sets the "-modulePathPrefix" parameter introduced in GWT 2.7.
	 * 
	 * @param modulePathPrefix the path prefix where the GWT modules are located relative to the war root.
	 */
	public void setModulePathPrefix(String modulePathPrefix) {
		this.modulePathPrefix = modulePathPrefix;
	}
}
