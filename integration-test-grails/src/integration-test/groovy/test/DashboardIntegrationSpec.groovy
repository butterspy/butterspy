package test

import grails.plugin.springsecurity.userdetails.GrailsUser
import grails.test.mixin.integration.Integration
import grails.transaction.*

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder;

import spock.lang.*
import geb.spock.*
import test.security.*

/**
 * See http://www.gebish.org/manual/current/ for more instructions
 */
@Integration(applicationClass=test.Application)
@Rollback
@Ignore
class DashboardIntegrationSpec extends GebSpec {
	
	def springSecurityService
	
	def currentUser
	
    def setup() {
		currentUser = createUser("andy")
		login(currentUser)
    }

    def cleanup() {
    }
	
	private User createUser(String username) {
		def user = User.findOrSaveByUsernameAndPassword(username, 'password')
		def role = Role.findOrSaveByAuthority('ROLE_USER')
		if (!(UserRole.exists(user.id, role.id))) {
			UserRole.create(user, role)
		}
		return user
	}
	
	private void login(User user) {
		springSecurityService.reauthenticate(user.username, 'password')
		refreshUserPrincipal(user.username)
	}
	
	private def refreshUserPrincipal(username) {
		def user = User.findByUsername(username)
		Collection<GrantedAuthority> auths = user.authorities.collect {
			new SimpleGrantedAuthority(it.authority)
		}
		def grailsUser = new GrailsUser(
				user.username,
				"",
				true,
				true,
				true,
				true,
				auths,
				user.id)

		def authToken =  new UsernamePasswordAuthenticationToken(
				grailsUser, '', auths)
		SecurityContextHolder.context.authentication = authToken
	}

    void "dashboard should find weather for logged in customer"() {
		
		given:
		def customer = Customer.build(user: currentUser, name: "Andy")
		
		expect:
		Customer.count() == 1
		springSecurityService.isLoggedIn()
		println "Principal: " + springSecurityService.principal
		println "User: " + springSecurityService.currentUser
        
		when:
		go '/dashboard'
		
		then:
        //$('title').text() == "Welcome to Grails"
		title == "Welcome to Grails"
    }
}
