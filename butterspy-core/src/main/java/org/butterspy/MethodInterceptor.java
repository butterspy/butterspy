package org.butterspy;

import java.lang.reflect.Method;

public interface MethodInterceptor {

	/**
	 * This method is called by the spy proxyt whenever a method is invoked.
	 * 
	 * @param methodProxy
	 *            the method proxy to be used to call the method on the spy
	 *            proxy
	 * @param spy
	 *            the spy proxy
	 * @param method
	 *            the method that was invoked in the spy proxy
	 * @param args
	 *            the arguments that were passed to the method call on the spy
	 *            proxy
	 * @return should return an object that is compatible with the type returned
	 *         by the original method
	 * @throws Throwable
	 *             in case something goes wrong
	 */
	Object intercept(MethodProxy methodProxy, Object spy,
			Method method, Object[] args) throws Throwable;
}
