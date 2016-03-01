package org.butterspy.invocation;

/**
 * Gives information about the invocation.
 * 
 * @author Ted Vinke
 *
 */
public interface DescribedInvocation {

	/**
     * Describes the invocation in the human friendly way.
     *
     * @return the description of this invocation.
     */
    String toString();
}
