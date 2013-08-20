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
    Double amount
    String fitId
    Double statementBalance
    String statementTraceNumber
    String statementTransactionType
    String importXml

    static belongsTo = [account:Account]

    static constraints = {
        importXml nullable: true, blank: true
    }

    static mapping = {
        transactionDate index: 'transactionDate_Idx'
        fitId index: 'fitId_Idx'
        importXml type: 'text'
    }

    String toString() {
        name
    }
}
