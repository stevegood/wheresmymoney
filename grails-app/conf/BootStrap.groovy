import org.stevegood.bank.Account
import org.stevegood.bank.Bank
import org.stevegood.sec.Role
import org.stevegood.sec.User
import org.stevegood.sec.UserRole

class BootStrap {

    def bankImportService
    def grailsApplication

    def init = { servletContext ->
        if (User.count() == 0) {
            // setup the application
            User admin = new User(username: 'admin', password: 'admin', enabled: true).save(flush: true, insert: true)
            Role roleSuperAdmin = new Role(authority: 'ROLE_SUPER_ADMIN').save(flush: true, insert: true)
            UserRole.create(admin, roleSuperAdmin)

            User demoUser = new User(username: 'demo', password: 'demo', enabled: true).save(flush: true, insert: true)
            Bank demoBank = new Bank(name: 'Demo Bank', primaryOwner: demoUser).save(flush: true, insert: true)
            Account demoAccount = new Account(name: 'Demo Checking Account', type: Account.CHECKING, bank: demoBank).save(flush: true, insert: true)

            // Load some demo data
            String filePath = new File(URLDecoder.decode(grailsApplication.classLoader.getResource('Demo_Checking_Account.qfx').getPath(), "utf-8")).getPath()
            File file = new File(filePath)
            println "Attempting to load: '$filePath' :: Exists? ${file.exists()}"
            if (file.exists()) {
                bankImportService.importFromSgml(file, demoAccount)
            }
        }
    }
    def destroy = {
    }
}
