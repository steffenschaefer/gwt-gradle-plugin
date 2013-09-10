package de.richsource.gradle.plugins.gwt;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.gradle.api.file.FileCollection;
import org.gradle.api.internal.ConventionTask;
import org.gradle.api.internal.file.FileResolver;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.InputFiles;
import org.gradle.api.tasks.TaskAction;
import org.gradle.process.internal.DefaultJavaExecAction;
import org.gradle.process.internal.JavaExecAction;

public abstract class AbstractGwtActionTask extends ConventionTask {
	
	private JavaExecAction javaExec;
	
	private List<String> modules;
	
	private Set<File> src = new HashSet<File>();
	private FileCollection classpath;
	private List<String> extraArgs = new ArrayList<String>();
	
	private String ms = "256M";
	private String mx = "512M";

	public AbstractGwtActionTask() {
		FileResolver fileResolver = getServices().get(FileResolver.class);
		javaExec = new DefaultJavaExecAction(fileResolver);
	}
	
	@TaskAction
	public void run() {
		javaExec.setMain(getClassName());
		
		if(prependSrcToClasspath()) {
			javaExec.classpath(getSrc().toArray());
		}
		javaExec.classpath(getClasspath());
		
		javaExec.setMinHeapSize(ms);
		javaExec.setMaxHeapSize(mx);
		
		addArgs();
		javaExec.args(getExtraArgs());
		javaExec.args(getModules());
		
		javaExec.execute();
	}
	
	protected boolean prependSrcToClasspath() {
		return true;
	}
	
	@Input
	public List<String> getModules() {
		return modules;
	}

	public void setModules(List<String> modules) {
		this.modules = modules;
	}
	
	public void javaArgs(Object... args) {
		javaExec.jvmArgs(args);
	}
	
	public List<String> getExtraArgs() {
		return extraArgs;
	}
	
	protected void arg(Object... arg) {
		javaExec.args(arg);
	}
	
	protected void argIfEnabled(Boolean condition, String arg) {
		if(Boolean.TRUE.equals(condition)) {
			arg(arg);
		}
	}
	
	protected void dirArgIfSet(String arg, File dir) {
		if(dir != null) {
			dir.mkdirs();
			arg(arg, dir);
		}
	}
	
	protected void argIfSet(String arg, Object value) {
		if(value != null) {
			arg(arg, value);
		}
	}
	
	public void extraArg(String arg) {
		extraArgs.add(arg);
	}
	
	protected abstract String getClassName();
	
	protected abstract void addArgs();

	public String getMs() {
		return ms;
	}

	public void setMs(String ms) {
		this.ms = ms;
	}

	public String getMx() {
		return mx;
	}

	public void setMx(String mx) {
		this.mx = mx;
	}
	
	public Set<File> getSrc() {
		return src;
	}

	@InputFiles
	public void setSrc(Set<File> src) {
		this.src = src;
	}
	
	public void src(File src) {
		this.src.add(src);
	}
	
	public void src(Collection<File> src) {
		this.src.addAll(src);
	}

	@InputFiles
	public FileCollection getClasspath() {
		return classpath;
	}

	public void setClasspath(FileCollection classpath) {
		this.classpath = classpath;
	}
}
