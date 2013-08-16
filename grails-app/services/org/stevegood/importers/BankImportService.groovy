package org.stevegood.importers

import org.stevegood.bank.Account
import org.stevegood.bank.Transaction
import org.stevegood.parsers.SgmlParser

class BankImportService {

    ArrayList<Transaction> importFromSgml(File file, Account account) {
        def xml = new SgmlParser().extractXml(file)
        // TODO: use parsed XML to create domain object instances
        def transactions = []
        xml?.BANKMSGSRSV1?.STMTTRNRS?.STMTRS?.BANKTRANLIST?.STMTTRN?.each {
            def transaction = Transaction.findOrCreateByAccountAndFitId(account, it.FITID.toString())
            transaction.displayName = transaction.displayName ?: it.NAME
            transaction.name = it.NAME
            transaction.memo = it.MEMO
            transaction.amount = it.TRNAMT as long
            transaction.statementBalance = it."USERS.STMT".TRNBAL as long
            transaction.statementTraceNumber = it."USERS.STMT".TRACENUMBER as long
            transaction.statementTransactionType = it."USERS.STMT".TRNTYPE
            transaction.datePosted = new Date().parse('yyyyMMddhhmmss',it.DTPOSTED as String)
            transaction.transactionDate = new Date().parse('yyyyMMddhhmmss',it.DTUSER as String)

            switch (it.TRNTYPE) {
                case 'DEP':
                    transaction.type = Transaction.DEPOSIT
                    break
                case 'DEBIT':
                    transaction.type = Transaction.DEBIT
                    break
            }

            transaction.save()
            transactions << transaction
        }

        return transactions
    }
}
