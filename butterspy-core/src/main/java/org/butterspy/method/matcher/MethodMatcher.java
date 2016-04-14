package org.butterspy.method.matcher;

import java.lang.reflect.Method;

/**
 * Simple strategy to match a method.
 * 
 * @author Ted Vinke
 *
 */
public interface MethodMatcher {

	/**
	 * Decides whether the rule implemented by the strategy matches the supplied
	 * method.
	 * 
	 * @param method
	 *            The method to check for a match
	 * @return true of the method matches, false otherwise
	 */
	boolean matches(Method method);
}
