package org.butterspy;

public interface SpyProxyFactory {

	/**
	 * Creates a spy for specified instance.
	 * 
	 * @param instanceToSpy
	 *            Any concrete instance of specified class to spy
	 * @param settings
	 *            Settings to influence created spy
	 * @return spied proxy class
	 * @throws Exception
	 *             in case anything fails to work with us ;-)
	 */
	<T> T create(final T instanceToSpy, final SpySettings settings)
			throws Exception;

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
	<T> T create(final Class<T> classForSpy, final T instanceToSpy,
			final SpySettings settings) throws Exception;

	/**
	 * Get the recording for specified spy.
	 * 
	 * @param spy
	 *            The spy
	 * @return the recording
	 */
	SpyRecording getSpyRecording(Object spy);
}