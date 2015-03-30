/*
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <alexmsagen@gmail.com> wrote this file.  As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return.   Alexander Sagen
 * ----------------------------------------------------------------------------
 */
package info.nordbyen.survivalheaven;

import info.nordbyen.survivalheaven.api.config.CustomConfiguration;

import java.io.File;

/**
 * The Class MySQLConfiguration.
 */
public class SHConfig extends CustomConfiguration {

	/** The cfg. */
	private static SHConfig cfg;
	
	/**
	 * Gets the single instance of SHConfig.
	 *
	 * @return single instance of SHConfig
	 */
	public static void createInstance() {
		if (cfg == null) {
			cfg = new SHConfig();
		}
	}
	
	public static boolean isDebugEnabled() {
		createInstance();
		return cfg.getBoolean("debug");
	}

	/**
	 * Instantiates a new SH configuration.
	 */
	public SHConfig() {
		super(new File("./plugins/SurvivalHeaven/main-config.yml"));
		cfg = this;
		load();
		save();
		saveDefault();
	}

	/**
	 * Save default.
	 */
	private void saveDefault() {
		if(!contains("debug")) {
			set("debug", false);
		}
		save();
	}
}
