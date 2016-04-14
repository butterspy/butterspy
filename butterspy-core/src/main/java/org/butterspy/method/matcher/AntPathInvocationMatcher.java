package org.butterspy.method.matcher;

import org.butterspy.internal.invocation.SpyInvocationKey;

/**
 * @author Ted Vinke
 *
 */
public final class AntPathInvocationMatcher implements InvocationMatcher {
	
	private static final String MATCH_ALL = "*";

	//private final Matcher matcher;
	private final String pattern;
	private final boolean caseSensitive;

	public AntPathInvocationMatcher(String pattern) {
		this(pattern, false);
	}
	
	public AntPathInvocationMatcher(String pattern, boolean caseSensitive) {
		this.pattern = pattern;
		this.caseSensitive = caseSensitive;
	}

	@Override
	public boolean matches(int currentInvocationCount, SpyInvocationKey invocation) {
		return false;
	}

	

}
