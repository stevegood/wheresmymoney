import org.stevegood.bank.Bank
import org.stevegood.sec.User

class MainController {

    def springSecurityService

    def index() {

        User user = User.findByUsername(springSecurityService.currentUser.username as String)
        int bankCount = Bank.countByPrimaryOwner(user)
        if (bankCount == 0) {
            redirect controller: 'bank', action: 'create'
            return
        }

        def banks = Bank.findAllByPrimaryOwner(user)

        render view: '/index', model: [banks: banks]
    }
}
