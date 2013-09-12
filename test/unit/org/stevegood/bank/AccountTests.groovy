package org.stevegood.bank



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Account)
class AccountTests {

    void testToString() {
        def account = new Account(name:'testing')
        assert account.toString() == 'testing'
    }
}
