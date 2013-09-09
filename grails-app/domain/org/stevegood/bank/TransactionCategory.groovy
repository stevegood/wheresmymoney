package org.stevegood.bank

import org.stevegood.meta.Category

class TransactionCategory {

    Transaction transaction
    Category category

    static constraints = {
    }

    static TransactionCategory create(Transaction transaction, Category category) {
        TransactionCategory.findOrCreateByTransactionAndCategory(transaction, category).save()
    }
}
