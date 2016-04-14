package org.butterspy.method.matcher;

import java.lang.reflect.Method;

/**
 * Matches any supplied method.
 * 
 * @author Ted Vinke
 *
 */
public class AnyMethodMatcher implements MethodMatcher {
	public final static MethodMatcher INSTANCE = new AnyMethodMatcher();

	private AnyMethodMatcher() {
	}

	@Override
	public boolean matches(Method method) {
		return true;
	}

	public boolean equals(Object obj) {
		return obj instanceof AnyMethodMatcher;
	}

	@Override
	public int hashCode() {
		return 1;
	}
	
	@Override
	public String toString() {
		return "AnyMethodMatcher";
	}
}
