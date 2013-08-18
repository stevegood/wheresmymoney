import org.stevegood.sec.Role
import org.stevegood.sec.User
import org.stevegood.sec.UserRole

class BootStrap {

    def init = { servletContext ->
        if (User.count() == 0) {
            // setup the application
            User admin = new User(username: 'admin', password: 'admin', enabled: true).save(flush: true, insert: true)
            Role roleSuperAdmin = new Role(authority: 'ROLE_SUPER_ADMIN').save(flush: true, insert: true)
            UserRole.create(admin, roleSuperAdmin)
        }
    }
    def destroy = {
    }
}
