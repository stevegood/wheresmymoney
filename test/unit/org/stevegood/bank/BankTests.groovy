package org.stevegood.bank



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Bank)
class BankTests {

    void testToString() {
        def bank = new Bank(name: 'testing')
        assert bank.toString() == 'testing'
    }
}
