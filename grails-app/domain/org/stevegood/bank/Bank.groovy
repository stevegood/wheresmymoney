package org.stevegood.bank

import org.stevegood.sec.User

class Bank {

    String name
    Date dateCreated
    Date lastUpdated

    static belongsTo = [primaryOwner:User]
    static hasMany = [accounts:Account]

    static constraints = {
    }
}
