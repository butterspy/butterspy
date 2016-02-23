package org.butterspy.internal

import groovy.transform.CompileStatic;

import org.butterspy.SpyProxyFactory
import org.butterspy.SpyRecording
import org.butterspy.SpySettings

import spock.lang.Specification


class JavassistSpyProxyFactorySpec extends Specification {
	
	SpySettings settings = Mock(SpySettings)
	
	SpyProxyFactory proxyFactory
	
	def setup() {
		proxyFactory = new JavassistSpyProxyFactory()
	}

	void "should create proxy with just an instance"() {
		
		given:
		def original = new Duck(name: "Tucker")
		
		when:
		def spy = proxyFactory.create(original, settings)
		
		then: "spy is subclass of original (class)"
		spy.getClass().getSuperclass() == Duck
		spy.getClass() != Duck // org.butterspy.internal.Duck_$$_javassist_0

		and: "spy is same type as original"
		spy instanceof Animal
		spy instanceof Duck
		
		and: "has additionally Butterspied interface"
		spy instanceof Butterspied
		
		and: "spy really proxies original"
		spy.greet() == "Hello, Tucker"
		
		and: "is equal with original object"
		spy == original
		!spy.is(original)
		
		and: "we have a recording"
		proxyFactory.getSpyRecording(spy)
	}
	
	void "should create proxy with superclass and instance"() {
		
		given:
		def original = new Duck(name: "Tucker")
		
		when:
		def spy = proxyFactory.create(Animal, original, settings)
		
		then: "spy is subclass of specified (super)class"
		spy.getClass().getSuperclass() == Animal
		spy instanceof Animal
		
		and: "because of specified superclass, spy is not same type as original"
		spy.getClass() != Duck // org.butterspy.internal.Animal_$$_javassist_0
		!(spy instanceof Duck)
				
		and: "has additionally Butterspied interface"
		spy instanceof Butterspied
		
		and: "spy really proxies original"
		spy.greet() == "Hello, Tucker"
		
		and: "is equal with original object"
		spy == original
		!spy.is(original)
		
		and: "we have a recording"
		proxyFactory.getSpyRecording(spy)
	}
	
	void "should create proxy with interface and instance"() {
		
		given:
		def original = new Duck(name: "Tucker")
		
		when:
		def spy = proxyFactory.create(Flyable, original, settings)
		
		then: "spy is subclass of Object and implements specified interface"
		spy.getClass().getSuperclass() == Object
		spy instanceof Flyable
		
		and: "because of specified interface, spy is not same type as original"
		spy.getClass() != Duck // org.butterspy.internal.Animal_$$_javassist_0
		!(spy instanceof Animal)
		!(spy instanceof Duck)
				
		and: "has additionally Butterspied interface"
		spy instanceof Butterspied
		
		and: "no ducktyping: no greet() method on a Flyable"
		try {
			spy.greet() == "Hello, Tucker"
			assert false, "Should not have been able to call greet()"
		} catch (MissingMethodException e) {
			// success, should not be able to invoke greet()
		}
		
		and: "is equal with original object"
		spy == original
		!spy.is(original)
		
		and: "we have a recording"
		proxyFactory.getSpyRecording(spy)
	}
	
	void "should record immediately after creation"() {
		
		given:
		def spy = proxyFactory.create(new Dog(), settings)
		def spyRecording = proxyFactory.getSpyRecording(spy)
		
		expect: "recording and initially no invocations"
		spyRecording.isRecording()
		!spyRecording.invocations
		
		when:
		spy.name = "Bazoo"
		
		then: "we recorded the invocation"
		spyRecording.invocations
	}
	
	void "should be able to stop recording"() {
		
		given:
		def spy = proxyFactory.create(new Dog(), settings)
		def spyRecording = proxyFactory.getSpyRecording(spy)
		
		expect: "recording and initially no invocations"
		spyRecording.isRecording()
		!spyRecording.invocations

		when: "we stop recording"
		spyRecording.stop()
		
		then:
		!spyRecording.isRecording()
		
		when: "method is called"
		spy.name = "Bazoo"
		
		then: "we have not recorded anything"
		!spyRecording.invocations		
	}
	
	void "should be able to restart recording"() {
		
		given:
		def spy = proxyFactory.create(new Dog(), settings)
		def spyRecording = proxyFactory.getSpyRecording(spy)
		
		expect: "recording and initially no invocations"
		spyRecording.isRecording()
		!spyRecording.invocations

		when: "method is called"
		spy.name = "Bazoo"
		
		then: "we recorded the invocation"
		spyRecording.invocations
		
		when: "we restart recording"
		spyRecording.restart()
		
		then: "we are starting with a clean slate"
		!spyRecording.invocations
	}
}
interface Flyable {
	void fly()
}
abstract class Animal { 
	String name 
	String greet() { "Hello, $name" }
}
class Dog extends Animal { }
class Duck extends Animal implements Flyable {

	@Override
	public void fly() {
		println "Flying"
	}
}