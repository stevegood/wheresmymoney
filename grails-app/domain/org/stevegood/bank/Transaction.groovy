package org.stevegood.bank

import org.stevegood.meta.UserCategory

class Transaction {

    static DEBIT = 'transaction.debit'
    static DEPOSIT = 'transaction.deposit'
    static transients = ['categories']

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
        displayName
    }

    def getCategories() {
        TransactionCategory.findAllByTransaction(this)?.collect { it.category }?.sort { it.name } ?: []
    }

    def addCategory(UserCategory category) {
        TransactionCategory.create(this, category)
    }

    def removeCategory(UserCategory category) {
        TransactionCategory.findByTransactionAndCategory(this, category)?.delete(flush: true)
    }

    static def findAllByCategory(UserCategory category) {
        TransactionCategory.findAllByCategory(category)?.collect { it.transaction } ?: []
    }
}
