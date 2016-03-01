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
	 * Restarts recording with a clean slate. Erases any earlier recordings.
	 * 
	 * <p>Equals {@link #stop()}, clearing recordings and {@link #start()}.
	 */
	public void restart();

	/**
	 * Stops recording.
	 * 
	 * <p>
	 * Method calls are no longer recorded. Use {@link #resume()} to continue.
	 */
	public void stop();
	
	/**
	 * Resumes recording, if stopped.
	 * 
	 * <p>
	 * Does nothing if already recording.
	 */
	public void resume();

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