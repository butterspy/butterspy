package org.butterspy.invocation;

/**
 * Interface for reporters.
 * 
 * @author Ted Vinke
 *
 */
public interface SpyInvocationReporter {

	/**
     * Describes the invocation in the human friendly way.
     *
     * @return the description of this invocation.
     */
	void report(SpyInvocation spyInvocation);
}
