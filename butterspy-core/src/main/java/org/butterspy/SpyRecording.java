package org.butterspy;

import java.util.List;

import org.butterspy.invocation.SpyInvocation;

/**
 * Represents a recording on a spy.
 * 
 * @author Ted Vinke
 *
 */
public interface SpyRecording {
	
	/**
	 * Gets the name of the spy.
	 * 
	 * @return the name
	 */
	public String getName();

	/**
	 * Starts recording.
	 * 
	 * <p>
	 * Method calls are recorded.
	 */
	public void start();

	/**
	 * Clears any earlier recording and restarts recording.
	 */
	public void restart();

	/**
	 * Stops recording.
	 * 
	 * <p>
	 * Method calls are no longer recorded.
	 */
	public void stop();

	/**
	 * Checks whether or not we are recording.
	 * 
	 * @return <code>true</code> if recording, <code>false</code> otherwise
	 */
	public boolean isRecording();

	/**
	 * @param recording
	 *            the recording to set
	 */
	public void setRecording(boolean recording);

	/**
	 * 
	 * Returns an unmodifiable view of the recorded method calls.
	 * 
	 * @return the invocations in chronological order
	 */
	public List<SpyInvocation> getInvocations();

}