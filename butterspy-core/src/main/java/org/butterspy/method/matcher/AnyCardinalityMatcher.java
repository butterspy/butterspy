package org.butterspy.method.matcher;

public class AnyCardinalityMatcher implements CardinalityMatcher {
	
	public final static CardinalityMatcher INSTANCE = new AnyCardinalityMatcher();
	
	@Override
	public boolean matches(int count) {
		return true;
	}

	@Override
	public String toString() {
		return "AnyCardinalityMatcher";
	}
}