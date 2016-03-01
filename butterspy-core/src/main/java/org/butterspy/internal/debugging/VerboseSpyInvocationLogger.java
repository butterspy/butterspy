package org.butterspy.internal.debugging;

import java.io.PrintStream;

import org.butterspy.events.InvocationEvent;
import org.butterspy.events.InvocationListener;

/**
 * Logs all invocations to standard output.
 * 
 * <p>Used for debugging interactions with a spy. 
 * 
 * @author Ted Vinke
 *
 */
public class VerboseSpyInvocationLogger implements InvocationListener {
	
	final PrintStream printStream;
	
	public VerboseSpyInvocationLogger() {
		this(System.out);
	}

	public VerboseSpyInvocationLogger(PrintStream printStream) {
		this.printStream = printStream;
	}

	@Override
	public void reportInvocation(InvocationEvent invocationEvent) {
		printStream.println(invocationEvent.getDescription());	
	}

}
