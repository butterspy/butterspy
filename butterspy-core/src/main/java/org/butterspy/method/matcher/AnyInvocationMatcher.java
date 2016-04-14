package org.butterspy.method.matcher;

import org.butterspy.internal.invocation.SpyInvocationKey;

/**
 * Matches any supplied method.
 * 
 * @author Ted Vinke
 *
 */
public class AnyInvocationMatcher implements InvocationMatcher {
	public final static InvocationMatcher INSTANCE = new AnyInvocationMatcher();

	private AnyInvocationMatcher() {
	}

	@Override
	public boolean matches(int currentInvocationCount, SpyInvocationKey invocation) {
		return true;
	}

	public boolean equals(Object obj) {
		return obj instanceof AnyInvocationMatcher;
	}

	@Override
	public int hashCode() {
		return 1;
	}
	
	@Override
	public String toString() {
		return "AnyInvocationMatcher";
	}
}
