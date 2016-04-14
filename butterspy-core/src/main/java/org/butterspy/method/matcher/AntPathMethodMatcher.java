package org.butterspy.method.matcher;

import java.lang.reflect.Method;

/**
 * @author Ted Vinke
 *
 */
public final class AntPathMethodMatcher implements MethodMatcher {
	
	private static final String MATCH_ALL = "*";

	//private final Matcher matcher;
	private final String pattern;
	private final boolean caseSensitive;

	public AntPathMethodMatcher(String pattern) {
		this(pattern, false);
	}
	
	public AntPathMethodMatcher(String pattern, boolean caseSensitive) {
		this.pattern = pattern;
		this.caseSensitive = caseSensitive;
	}

	@Override
	public boolean matches(Method method) {
		return false;
	}

}
