package org.butterspy.internal.invocation;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.butterspy.invocation.SpyInvocation;
import org.butterspy.invocation.SpyMethod;

/**
 * Recorded method call on a spy.
 * 
 * @author Ted Vinke
 *
 */
public class SpyInvocationImpl implements SpyInvocation, Serializable {

	private static final long serialVersionUID = 6980099776059264873L;

	private final int sequenceNumber;
	private final Object spy;
	private final SpyMethod method;
	private final Object[] arguments;
	private final Object returnedObject;

	public SpyInvocationImpl(Object spy, SpyMethod spyMethod,
			Object[] args, Object returnedObject, int sequenceNumber) {
		this.spy = spy;
		this.method = spyMethod;
		this.arguments = args;
		this.sequenceNumber = sequenceNumber;
		this.returnedObject = returnedObject;
	}

	@Override
	public Method getMethod() {
		return method.getJavaMethod();
	}

	@Override
	public Object[] getArguments() {
		return arguments;
	}

	@Override
	public Object getReturnedObject() {
		return returnedObject;
	}

	@Override
	public Class<?> getReturnType() {
		return method.getReturnType();
	}

	@Override
	public int getSequenceNumber() {
		return sequenceNumber;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || !o.getClass().equals(this.getClass())) {
			return false;
		}

		SpyInvocationImpl other = (SpyInvocationImpl) o;

		return this.spy.equals(other.spy)
				&& this.method.equals(other.method)
				&& this.equalArguments(other.arguments);
	}

	private boolean equalArguments(Object[] arguments) {
		return Arrays.equals(arguments, this.arguments);
	}

	@Override
	public int hashCode() {
		return 1;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("#");
		builder.append(sequenceNumber);
		builder.append(" [method=");
		builder.append(method.getJavaMethod());
		builder.append(", arguments=");
		builder.append(Arrays.asList(arguments));
		builder.append(", returnedObject=");
		builder.append(returnedObject);
		builder.append("]");
		return builder.toString();
	}
}
