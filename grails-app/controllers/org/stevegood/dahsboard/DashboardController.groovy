package org.stevegood.dahsboard

import grails.converters.JSON
import org.stevegood.bank.Transaction
import org.stevegood.sec.User
import org.stevegood.meta.Category

class DashboardController {

    def springSecurityService

    def afterInterceptor = { model ->
        render( model as JSON )
        return
    }

    def categoryOverview() {
        def result = [categories: []]
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
            result.categories << value
        }

        result.categories = result.categories.sort { it.title }

        return result
    }
}
