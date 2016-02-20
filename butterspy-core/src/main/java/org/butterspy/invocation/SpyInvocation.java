package org.butterspy.invocation;

import java.lang.reflect.Method;

/**
 * Method call on a spy.
 * 
 * @author Ted Vinke
 *
 */
public interface SpyInvocation {

	/**
	 * Returns the the invoked method.
	 * 
	 * @return the method, never null
	 */
	public Method getMethod();

	/**
	 * Returns the the recorded arguments passed to the method.
	 * 
	 * @return the arguments, never null
	 */
	Object[] getArguments();

	/**
	 * Returns the recorded returned object.
	 * 
	 * @return an object, possibly <code>null</code
	 */
	Object getReturnedObject();

	/**
	 * Returns the type of the method.
	 * 
	 * @return type
	 */
	Class<?> getReturnType();

	/**
	 * Returns the globally unique sequence of this invocation.
	 * 
	 * @return a positive number
	 */
	int getSequenceNumber();
}
