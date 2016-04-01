package org.butterspy

import groovy.transform.ToString

@ToString
class Cat {
	
	String name
	int age
	
	String greeting() {
		"Hello, $name!"
	}
}
