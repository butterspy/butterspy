package org.butterspy.method.matcher;

import org.butterspy.internal.invocation.SpyInvocationKey;

/**
 * Simple strategy to match an invocation of a method.
 * 
 * @author Ted Vinke
 *
 */
public interface InvocationMatcher {

	/**
	 * Decides whether the rule implemented by the strategy matches the supplied
	 * method.
	 * 
	 * @param currentInvocationCount
	 *            The current amount of times this method has been invoked
	 * @param invocation
	 *            The invocation to check for a match
	 * @return true of the method matches, false otherwise
	 */
	boolean matches(int currentInvocationCount, SpyInvocationKey invocation);
}
