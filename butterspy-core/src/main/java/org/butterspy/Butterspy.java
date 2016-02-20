package org.butterspy;

import org.butterspy.internal.JavassistSpyProxyFactory;
import org.butterspy.internal.SpySettingsImpl;

public class Butterspy {

	private static final SpyProxyFactory proxyFactory = new JavassistSpyProxyFactory();

	private Butterspy() {
		// prevents instantiation
	}

	/**
	 * Creates a spy for specified class or interface and instance.
	 * 
	 * @param classForSpy
	 *            Class or interface for the spy
	 * @param instanceToSpy
	 *            Any concrete instance of specified class to spy
	 * @return spied proxy class
	 * @throws Exception
	 *             in case anything fails to work with us ;-)
	 */
	public static <T> T spy(Class<T> classForSpy, final T instanceToSpy)
			throws Exception {
		return proxyFactory.create(classForSpy, instanceToSpy, withSettings());
	}

	/**
	 * Creates a spy for specified class or interface and instance.
	 * 
	 * @param name
	 *            Name of the spy, used in logging or other output
	 * @param classForSpy
	 *            Class or interface for the spy
	 * @param instanceToSpy
	 *            Any concrete instance of specified class to spy
	 * @return spied proxy class
	 * @throws Exception
	 *             in case anything fails to work with us ;-)
	 */
	public static <T> T spy(String name, Class<T> classForSpy, final T instanceToSpy)
			throws Exception {
		return proxyFactory.create(classForSpy, instanceToSpy,
				withSettings().name(name));
	}
	
	/**
	 * Creates a spy for specified class or interface and instance.
	 * 
	 * @param classForSpy
	 *            Class or interface for the spy
	 * @param instanceToSpy
	 *            Any concrete instance of specified class to spy
	 * @param settings
	 *            Settings to influence created spy
	 * @return spied proxy class
	 * @throws Exception
	 *             in case anything fails to work with us ;-)
	 */
	public static <T> T spy(Class<T> classForSpy, final T instanceToSpy,
			SpySettings settings) throws Exception {
		return proxyFactory.create(classForSpy, instanceToSpy, settings);
	}

	/**
	 * Creates a spy for specified class or interface and instance.
	 * 
	 * @param name
	 *            Name of the spy, used in logging or other output
	 * @param classForSpy
	 *            Class or interface for the spy
	 * @param instanceToSpy
	 *            Any concrete instance of specified class to spy
	 * @param settings
	 *            Settings to influence created spy
	 * @return spied proxy class
	 * @throws Exception
	 *             in case anything fails to work with us ;-)
	 */
	public static <T> T spy(String name, Class<T> classForSpy,
			final T instanceToSpy, SpySettings settings) throws Exception {
		return proxyFactory.create(classForSpy, instanceToSpy, settings.name(name));
	}
	

	/**
	 * Get the recording for specified spy.
	 * 
	 * @param spy
	 *            The spy
	 * @return the recording
	 */
	public static SpyRecording getSpyRecording(Object spy) {
		return proxyFactory.getSpyRecording(spy);
	}

	/**
	 * 
	 * Allows spy creation with additional settings.
	 * 
	 * @return spy settings instance with defaults
	 */
	@SuppressWarnings("rawtypes")
	public static SpySettings withSettings() {
		return new SpySettingsImpl();
	}
}
