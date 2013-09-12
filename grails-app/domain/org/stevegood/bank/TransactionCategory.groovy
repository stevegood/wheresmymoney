package org.stevegood.bank

import org.stevegood.meta.UserCategory

class TransactionCategory {

    Transaction transaction
    UserCategory category

    static constraints = {
    }

    static TransactionCategory create(Transaction transaction, UserCategory category) {
        TransactionCategory.findOrCreateByTransactionAndCategory(transaction, category).save()
    }
}
