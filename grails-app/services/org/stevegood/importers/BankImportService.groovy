package org.stevegood.importers

import groovy.xml.XmlUtil
import org.stevegood.bank.Account
import org.stevegood.bank.Transaction
import org.stevegood.parsers.SgmlParser

class BankImportService {

    ArrayList<Transaction> importFromSgml(File file, Account account) {
        def xml = new SgmlParser().extractXml(file)
        // TODO: use parsed XML to create domain object instances
        def transactions = []
        xml?.BANKMSGSRSV1?.STMTTRNRS?.STMTRS?.BANKTRANLIST?.STMTTRN?.each {
            if (it.TRNAMT.toString() != '' && it."USERS.STMT".TRNBAL.toString() != '') {
                def transaction = Transaction.findOrCreateByAccountAndFitId(account, it.FITID.toString())
                transaction.displayName = transaction.displayName ?: it.NAME.toString()
                transaction.name = it.NAME.toString()
                transaction.memo = it.MEMO.toString()
                transaction.amount = it.TRNAMT.toString().toDouble()
                transaction.statementBalance = it."USERS.STMT".TRNBAL.toString().toDouble()
                transaction.statementTraceNumber = it."USERS.STMT".TRACENUMBER.toString()
                transaction.statementTransactionType = it."USERS.STMT".TRNTYPE.toString()
                transaction.datePosted = new Date().parse('yyyyMMddhhmmss',it.DTPOSTED.toString())
                transaction.transactionDate = new Date().parse('yyyyMMddhhmmss',it.DTUSER.toString())
                transaction.importXml = XmlUtil.serialize(it)

                switch (it.TRNTYPE.toString()) {
                    case 'DEP':
                        transaction.type = Transaction.DEPOSIT
                        break
                    case 'DEBIT':
                        transaction.type = Transaction.DEBIT
                        break
                }

                transaction.save(flush: true)
                transactions << transaction
            }
        }

        return transactions
    }
}
