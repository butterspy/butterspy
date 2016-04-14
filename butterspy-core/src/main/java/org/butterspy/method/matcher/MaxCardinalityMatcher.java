package org.butterspy.method.matcher;

public class MaxCardinalityMatcher implements CardinalityMatcher {
	
	private final int maxValue;

	public MaxCardinalityMatcher(int maxValue) {
		this.maxValue = maxValue;
	}

	@Override
	public boolean matches(int count) {
		StringBuilder builder = new StringBuilder();
		builder.append("MaxCardinalityMatcher checking if maxValue=");
		builder.append(maxValue);
		builder.append(" > ");
		builder.append(count);
		builder.append("");
		System.out.println(builder.toString());
		
		return maxValue > count;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MaxCardinalityMatcher [maxValue=");
		builder.append(maxValue);
		builder.append("]");
		return builder.toString();
	}
	
}