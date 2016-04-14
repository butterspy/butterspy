package org.butterspy.method.matcher;

class AntArgumentMatcher implements ArgumentMatcher {
	private final Object value;
	public AntArgumentMatcher(Object argument) {
		this.value = argument;
	}

	@Override
	public boolean matches(Object argument) {
		StringBuilder builder = new StringBuilder();
		builder.append("AntArgumentMatcher checking if value=[");
		builder.append(value);
		builder.append("] equals [");
		builder.append(argument);
		builder.append("]");
		System.out.println(builder.toString());
		
		return value.equals(argument);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AntArgumentMatcher [argument=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}
	
}