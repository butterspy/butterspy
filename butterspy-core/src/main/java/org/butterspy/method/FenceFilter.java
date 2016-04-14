package org.butterspy.method;

import java.lang.reflect.Method;

public interface FenceFilter {

	boolean accept(Method method);
}
