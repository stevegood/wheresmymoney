package org.stevegood.bank



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Transaction)
class TransactionTests {

    void testToString() {
        def transaction = new Transaction(displayName: 'testing')
        assert transaction.toString() == 'testing'
    }
}
