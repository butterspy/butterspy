package test

import test.security.User

class Customer {
	
	String name
	Address homeAddress
	Address workAddress
	
	User user
	
    static constraints = {
		user nullable: true
		homeAddress nullable: true
		workAddress nullable: true
    }
}
