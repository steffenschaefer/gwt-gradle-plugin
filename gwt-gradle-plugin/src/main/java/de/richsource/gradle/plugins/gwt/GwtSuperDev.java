package de.richsource.gradle.plugins.gwt;

import java.io.File;
import java.util.concurrent.Callable;

import org.gradle.api.Task;
import org.gradle.api.specs.Spec;

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
	
	private final GwtSuperDevOptions options = new GwtSuperDevOptions();
	
	public GwtSuperDev() {
		getOutputs().upToDateWhen(new Spec<Task>(){
			@Override
			public boolean isSatisfiedBy(Task task) {
				return false;
			}});
	}

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
	
	protected void configure(final GwtSuperDevOptions options) {
		conventionMapping("bindAddress", new Callable<String>() {
			@Override
			public String call() throws Exception {
				return options.getBindAddress();
			}
		});
		conventionMapping("port", new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return options.getPort();
			}
		});
		conventionMapping("noPrecompile", new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return options.getNoPrecompile();
			}
		});
	}
	
	protected boolean prependSrcToClasspath() {
		return false;
	}

	public File getWorkDir() {
		return options.getWorkDir();
	}

	public void setWorkDir(File workDir) {
		options.setWorkDir(workDir);
	}

	public String getBindAddress() {
		return options.getBindAddress();
	}

	public void setBindAddress(String bindAddress) {
		options.setBindAddress(bindAddress);
	}

	public Integer getPort() {
		return options.getPort();
	}

	public void setPort(Integer port) {
		options.setPort(port);
	}

	public Boolean getNoPrecompile() {
		return options.getNoPrecompile();
	}

	public void setNoPrecompile(Boolean noPrecompile) {
		options.setNoPrecompile(noPrecompile);
	}
}
