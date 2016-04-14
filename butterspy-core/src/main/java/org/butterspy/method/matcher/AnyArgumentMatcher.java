package org.butterspy.method.matcher;

public class AnyArgumentMatcher implements ArgumentMatcher {
	
	public final static ArgumentMatcher INSTANCE = new AnyArgumentMatcher();
	
	@Override
	public boolean matches(Object argument) {
		return true;
	}
	
	@Override
	public String toString() {
		return "AnyArgumentMatcher";
	}
}