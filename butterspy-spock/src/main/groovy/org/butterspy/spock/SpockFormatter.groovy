package org.butterspy.spock

import java.util.List;

import groovy.transform.CompileStatic;
import groovy.transform.TypeChecked;

import org.butterspy.SpyRecording
import org.butterspy.invocation.SpyInvocation;

public class SpockFormatter {
	
	private static class OrderedInvocation {
		SpyInvocation invocation
		SpyRecording recording
		String toString() {
			"$invocation of $recording"
		}
	}

	public SpockFormatter() {
	}

	public String format(SpyRecording...recordings) {
		
		List<OrderedInvocation> invocations = orderInvocations(recordings)

		StringBuilder sb = new StringBuilder()
		invocations.eachWithIndex { invocation, i ->
	
			println "------------------------------\n"

			writeInteraction sb, invocation
			
			if (i < invocations.size() - 1) {
				sb.append "\n"
			}
			
			println "------------------------------\n"
		}
		sb.toString()
	}
	
	@TypeChecked
	List<OrderedInvocation> orderInvocations(SpyRecording...recordings) {
		List<OrderedInvocation> invocations = []
		recordings.each { SpyRecording recording ->
			recording.invocations.each { invocation ->
				invocations << new OrderedInvocation(invocation: invocation, 
														recording: recording)
			}
		}
		invocations.sort { it.invocation.sequenceNumber }
	}

	void writeInteraction(StringBuilder sb, OrderedInvocation orderedInvocation) {
		println "*** Writing interaction for " + orderedInvocation
		SpyInvocation invocation = orderedInvocation.invocation
		if (invocation.returnedObject) {
			println "Generating interaction for result " + invocation.returnedObject
			writeResult(sb, invocation.returnedObject)
			sb.append "\n"
		}
		writeMethodCall sb, orderedInvocation
	}
	
	void writeMethodCall(StringBuilder sb, OrderedInvocation orderedInvocation) {
		println "*** Writing method call for " + orderedInvocation
		SpyInvocation invocation = orderedInvocation.invocation
		
		// cardinality
		sb.append "1 * "
		
		// objectName.methodName
		sb.append orderedInvocation.recording.name
		sb.append "."
		sb.append invocation.method.name
		
		// arguments
		if (invocation.arguments) {
			
			List argValues = invocation.arguments.collect { Object arg ->
				if (arg instanceof String) {
					"\"${arg}\""
				} else {
					arg
				}
			}
			
			sb.append "("
			sb.append argValues.join(", ")
			sb.append ")"
		}
	}

	void writeResult(StringBuilder sb, def result) {
		writeInstantiation(sb, result)
	}

	void writeInstantiation(StringBuilder sb, List properties) {

		Map classProperty = properties.find { it.'property' == 'class' }

		println "Found classProperty " + classProperty
		String clazz = classProperty.value
		boolean simpleType = classProperty.simpleType

		List remainingProperties = properties.findAll { it != classProperty }
		if (simpleType) {
			sb.append "$prop.property: $value"
			sb.append "\n"
		} else {
			sb.append "new $clazz(" + "\n"
			remainingProperties.each { prop ->
				println "Iterating remainder of props: " + remainingProperties*.property
				if (properties.simpleType) {
					sb.append " $prop.property: $prop.value"
					sb.append "\n"
				}
			}
			sb.append ")"
		}
	}

}
