package org.butterspy.method.matcher;

public class AnyMethodNameMatcher implements StringMatcher {
	
	public final static StringMatcher INSTANCE = new AnyMethodNameMatcher();
	
	@Override
	public boolean matches(String name) {
		return true;
	}
	
	@Override
	public String toString() {
		return "AnyMethodNameMatcher";
	}
}