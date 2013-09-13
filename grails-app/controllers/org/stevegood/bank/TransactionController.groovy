package org.stevegood.bank

import org.stevegood.sec.User

class TransactionController {

    def bankImportService
    def springSecurityService

    static scaffold = true

    def importTransactions() {
        def banks = Bank.findAllByPrimaryOwner(User.findByUsername(springSecurityService.currentUser.username as String))
        [banks: banks]
    }

    def upload() {
        def account = Account.withCriteria {
            eq 'id', params.account as Long
            bank {
                primaryOwner {
                    eq 'username', springSecurityService.currentUser.username
                }
            }
        }?.getAt(0)

        if (!account) {
            flash.message = 'Invalid account, please try again'
            render(view: 'importTransactions')
            return
        }

        def file = request.getFile('transactionFile')
        if (file.empty) {
            flash.message = 'File cannot be empty'
            render(view: 'importTransactions')
            return
        }

        def ts = new Date().time.toString()
        File tmpFile = File.createTempFile(ts, file.originalFilename as String)
        file.transferTo(tmpFile)

        def transactions = bankImportService.importFromSgml(tmpFile, account)
        tmpFile.delete()

        flash.message = "Successfully imported ${transactions.size()}"
        redirect(controller: 'dashboard', action: 'overview')
    }
}
