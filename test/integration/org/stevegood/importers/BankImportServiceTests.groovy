package org.stevegood.importers

import grails.test.GrailsUnitTestCase
import org.stevegood.bank.Account
import org.stevegood.bank.Bank
import org.stevegood.bank.Transaction
import org.stevegood.parsers.SgmlParser
import org.stevegood.sec.User

import static org.junit.Assert.*
import org.junit.*

class BankImportServiceTests extends GrailsUnitTestCase {

    def bankImportService
    def grailsApplication

    @Before
    void setUp() {
        // Setup logic here
        Transaction.list()*.delete(flush: true)
    }

    @After
    void tearDown() {
        // Tear down logic here
        Transaction.list()*.delete(flush: true)
    }

    @Test
    void testImportFromSgml() {
        String filePath = new File(URLDecoder.decode(grailsApplication.classLoader.getResource('Demo_Checking_Account.qfx').getPath(), "utf-8")).getPath()
        File file = new File(filePath)

        def rawTransactions = new SgmlParser().extractXml(file)
        def rawTransCount = rawTransactions?.BANKMSGSRSV1?.STMTTRNRS?.STMTRS?.BANKTRANLIST?.STMTTRN?.size()
        assert rawTransCount > 0
        println "Expecting $rawTransCount transactions"

        User user = new User(username: 'testing', password: 'testing', enabled: true).save(flush: true)
        Bank bank = new Bank(primaryOwner: user, name: 'testing bank').save(flush: true)
        Account account = new Account(name: 'testing account', bank: bank).save(flush: true)
        def transactions = bankImportService.importFromSgml(file, account)
        assert transactions.size() > 0

        println "Import resulted in ${transactions.size()} transactions"
        assertEquals transactions.size(), rawTransCount
    }
}
