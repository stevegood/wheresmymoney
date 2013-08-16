package org.stevegood.bank

class Transaction {

    static DEBIT = 'transaction.debit'
    static DEPOSIT = 'transaction.deposit'

    Date dateCreated
    Date lastUpdated
    Date transactionDate
    Date datePosted
    String type
    String name
    String displayName
    String memo
    long amount
    String fitId
    long statementBalance
    long statementTraceNumber
    String statementTransactionType

    static belongsTo = [account:Account]

    static constraints = {
    }

    static mapping = {
        transactionDate index: 'transactionDate_Idx'
        fitId index: 'fitId_Idx'
    }
}
