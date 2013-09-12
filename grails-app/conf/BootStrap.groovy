import org.stevegood.bank.Account
import org.stevegood.bank.Bank
import org.stevegood.bank.Transaction
import org.stevegood.meta.UserCategory
import org.stevegood.sec.Role
import org.stevegood.sec.User
import org.stevegood.sec.UserRole
import org.stevegood.util.color.ColorUtils

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
                def transactions = bankImportService.importFromSgml(file, demoAccount)
                def categories = []
                def numCats = 5
                numCats.times {
                    categories << new UserCategory(name: "Demo Category ${it + 1}", color: ColorUtils.randomHexColor(), user: demoUser).save(insert: true, flush: true)
                    println "Added category: ${categories[-1].name}"
                }

                transactions.each { Transaction transaction ->
                    def rnd = new Random().nextInt(numCats)
                    UserCategory category = categories[rnd] as UserCategory
                    println "Attempting to add category: ${category.name} to transaction: ${transaction.displayName}"
                    transaction.addCategory(category)
                }
            }
        }
    }
    def destroy = {
    }
}
