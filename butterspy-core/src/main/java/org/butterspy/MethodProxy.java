package org.butterspy;

public interface MethodProxy {
	Object invoke(Object instanceToSpy, Object[] args) throws Throwable;
}
