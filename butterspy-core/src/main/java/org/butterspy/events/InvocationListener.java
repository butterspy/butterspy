package org.butterspy.events;

/**
 * This listener can be notified of changes on the recording or method
 * invocations on a spy.
 * 
 * @author Ted Vinke
 *
 */
public interface InvocationListener {

    /**
     * Called after the invocation of the listener's spy if it returned normally.
     *
     * @param invocationEvent Information about the method call that just happened.
     *
     * @see InvocationEvent
     */
    void reportInvocation(InvocationEvent invocationEvent);
}
