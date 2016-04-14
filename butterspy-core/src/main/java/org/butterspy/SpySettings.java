package org.butterspy;

import java.io.Serializable;
import java.util.List;

import org.butterspy.events.InvocationListener;
import org.butterspy.method.AllowInvocationsConfiguration;

public interface SpySettings extends Serializable {

	/**
	 * Gets the name of the spy.
	 * 
	 * @return the name
	 */
	public String getName();

	/**
	 * Sets the name of the spy.
	 * 
	 * @param name
	 *            The name
	 * @return this settings instance
	 */
	public SpySettings name(String name);

	/**
	 * Enables real-time logging of method invocations on this spy. Can be used
	 * during test debugging in order to find weird interactions with the spy.
	 * <p>
	 * Invocations are logged as they happen to the standard output stream.
	 * <p>
	 * Calling this method multiple times makes no difference.
	 * <p>
	 * Example:
	 * 
	 * <pre class="code">
	 * <code class="java">
	 * SomeObject spy = spy(SomeObject.class, withSettings().verboseLogging());
	 * </code>
	 * </pre>
	 *
	 * @return this settings instance
	 */
	public SpySettings verboseLogging();
	
	/**
	 * Enables fencing of method invocations on this spy. Can be used
	 * during test debugging to fail at certain invocations.
	 * <p>
	 * Calling this method multiple times makes no difference.
	 * <p>
	 * Example:
	 * 
	 * <pre class="code">
	 * <code class="java">
	 * SomeObject spy = spy(SomeObject.class, withSettings().fence());
	 * </code>
	 * </pre>
	 *
	 * @return this settings instance
	 */
	public SpySettings fence();
	
	/**
	 * @param config
	 * @return
	 */
	public SpySettings fence(AllowInvocationsConfiguration config);
	
	/**
	 * Returns all invocation listeners.
	 * 
	 * @return all listeners
	 */
	public List<InvocationListener> getInvocationListeners();
	
	public MethodInterceptor getMethodInterceptor();
}
