package de.richsource.gradle.plugins.gwt.eclipse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.gradle.api.internal.ConventionTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.Optional;
import org.gradle.api.tasks.OutputFile;
import org.gradle.api.tasks.TaskAction;

import de.richsource.gradle.plugins.gwt.eclipse.internal.GdtOptionsImpl;

// TODO It's currently not possible to use PropertiesGeneratorTask as it forces imports of internal stuff. Evaluate this again later.
public class GenerateGdt extends ConventionTask implements GdtOptions {
	
	private final GdtOptions options = new GdtOptionsImpl();
	
	@OutputFile
	private File settingsFile;
	
	@TaskAction
	private void generate() {
		if(getSettingsFile() == null || getSettingsFile().isDirectory()) {
			throw new IllegalStateException();
		}
		final Properties properties = new Properties();
		readProperties(properties);
			
		configureProperties(properties);
		
		writeProperties(properties);
	}

	private void configureProperties(Properties properties) {
		if(getLastWarOutDir() != null) {
			properties.put("lastWarOutDir", getLastWarOutDir().getAbsoluteFile().getPath());
		}
		String warSrcPath = getProject().getProjectDir().toURI().relativize(getWarSrcDir().toURI()).getPath();
		properties.put("warSrcDir", warSrcPath);
		properties.put("warSrcDirIsOutput", ""+Boolean.TRUE.equals(getWarSrcDirIsOutput()));
	}

	private void readProperties(final Properties properties) {
		InputStream inputStream = null;
		try {
			if(getSettingsFile().exists()) {
			inputStream = new FileInputStream(getSettingsFile());
			} else {
				inputStream = GenerateGdt.class.getResourceAsStream("defaultGdtPrefs.properties");
			}
			properties.load(inputStream);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if(inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					throw new IllegalStateException(e);
				}
			}
		}
	}


	private void writeProperties(final Properties properties) {
		OutputStream stream = null;
		try {
			stream = new FileOutputStream(getSettingsFile());
			properties.store(stream, null);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if(stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					throw new IllegalStateException(e);
				}
			}
		}
	}

	/** {@inheritDoc} */
	@Override
	public void setWarSrcDirIsOutput(Boolean warSrcDirIsOutput) {
		options.setWarSrcDirIsOutput(warSrcDirIsOutput);
	}

	/** {@inheritDoc} */
	@Override
	@Input
	public Boolean getWarSrcDirIsOutput() {
		return options.getWarSrcDirIsOutput();
	}

	/** {@inheritDoc} */
	@Override
	public void setWarSrcDir(File warSrcDir) {
		options.setWarSrcDir(warSrcDir);
	}

	/** {@inheritDoc} */
	@Override
	@Input
	public File getWarSrcDir() {
		return options.getWarSrcDir();
	}

	/** {@inheritDoc} */
	@Override
	public void setLastWarOutDir(File lastWarOutDir) {
		options.setLastWarOutDir(lastWarOutDir);
	}

	/** {@inheritDoc} */
	@Override
	@Input
	@Optional
	public File getLastWarOutDir() {
		return options.getLastWarOutDir();
	}

	public File getSettingsFile() {
		return settingsFile;
	}

	public void setSettingsFile(File settingsFile) {
		this.settingsFile = settingsFile;
	}

}
