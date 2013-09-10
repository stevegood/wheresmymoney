package org.stevegood.bank

class Account {

    static transients = ['balance']
    static SAVINGS = 'account.savings'
    static CHECKING = 'account.checking'

    String type = CHECKING
    String name
    Date dateCreated
    Date lastUpdated

    static belongsTo = [bank:Bank]
    static hasMany = [transactions:Transaction]

    static constraints = {
    }

    static mapping = {
        transactions sort: 'transactionDate', order: 'asc'
    }

    String toString() {
        name
    }

    def getBalance(){
        Transaction.findByAccount(this,[sort: 'datePosted', order: 'desc'])?.amount ?: 0
    }
}
