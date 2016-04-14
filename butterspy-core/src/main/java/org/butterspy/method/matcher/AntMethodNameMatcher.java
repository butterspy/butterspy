package org.butterspy.method.matcher;

class AntMethodNameMatcher implements StringMatcher {
	private final String methodName;
	public AntMethodNameMatcher(String methodName) {
		this.methodName = methodName;
	}

	@Override
	public boolean matches(String name) {
		StringBuilder builder = new StringBuilder();
		builder.append("AntMethodNameMatcher checking if value=[");
		builder.append(methodName);
		builder.append("] equals [");
		builder.append(name);
		builder.append("]");
		System.out.println(builder.toString());
		
		return methodName.equals(name);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AntMethodNameMatcher [methodName=");
		builder.append(methodName);
		builder.append("]");
		return builder.toString();
	}
	
}