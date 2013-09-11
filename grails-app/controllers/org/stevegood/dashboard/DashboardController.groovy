package org.stevegood.dashboard

import grails.converters.JSON
import org.stevegood.bank.Bank
import org.stevegood.bank.Transaction
import org.stevegood.sec.User
import org.stevegood.meta.Category

class DashboardController {

    def springSecurityService

    def afterInterceptor = { model ->
        if (params.action != 'index') {
            render( model as JSON )
            return
        }
    }

    def index() {

        User user = User.findByUsername(springSecurityService.currentUser.username as String)
        int bankCount = Bank.countByPrimaryOwner(user)
        if (bankCount == 0) {
            redirect controller: 'bank', action: 'create'
            return
        }

        def banks = Bank.findAllByPrimaryOwner(user)

        [banks: banks]
    }

    def categoryOverview() {
        def result = [data: []]
        def categoryMaps = ['uncategorized': [color: '#f2f2f2', value: 0, title: 'Uncategorized']]
        User user = User.findByUsername(springSecurityService.currentUser.username as String)
        def transactions = Transaction.withCriteria {
            account {
                bank {
                    eq 'primaryOwner', user
                }
            }
        }

        transactions.each { Transaction transaction ->
            if (transaction.categories?.size()) {
                // the transaction has a category, add it to the collection
                transaction.categories.each { Category category ->
                    if (!categoryMaps[category.name]) {
                        categoryMaps[category.name] = [color: category.color, value: 0, title: category.name]
                    }

                    categoryMaps[category.name].value++
                }
            } else {
                // no categories, put it in uncategorized
                categoryMaps.uncategorized.value++
            }
        }

        categoryMaps.each { key, value ->
            result.data << value
        }

        result.data = result.data.sort { a,b -> b.value <=> a.value }

        return result
    }
}
