import org.stevegood.bank.Account
import org.stevegood.bank.Bank
import org.stevegood.sec.Role
import org.stevegood.sec.User
import org.stevegood.sec.UserRole

class BootStrap {

    def bankImportService

    def init = { servletContext ->
        if (User.count() == 0) {
            // setup the application
            User admin = new User(username: 'admin', password: 'admin', enabled: true).save(flush: true, insert: true)
            Role roleSuperAdmin = new Role(authority: 'ROLE_SUPER_ADMIN').save(flush: true, insert: true)
            UserRole.create(admin, roleSuperAdmin)

            User demoUser = new User(username: 'demo', password: 'demo', enabled: true).save(flush: true, insert: true)
            Bank demoBank = new Bank(name: 'Demo Bank', primaryOwner: demoUser).save(flush: true, insert: true)
            Account demoAccount = new Account(name: 'Demo Checking Account', type: Account.CHECKING, bank: demoBank).save(flush: true, insert: true)

            String filePath = '/Users/steve/Downloads/Checking_7_History_8-1-2013_8-14-2013.qfx'
            File file = new File(filePath)
            bankImportService.importFromSgml(file, demoAccount)
        }
    }
    def destroy = {
    }
}
