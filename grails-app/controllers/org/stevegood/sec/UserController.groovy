package org.stevegood.sec

class UserController {

    def springSecurityService
    static scaffold = true

    def loginSuccess() {
        User user = User.findByUsername(springSecurityService.currentUser.username as String)
        if (user.authorities.contains(Role.findByAuthority('ROLE_SUPER_ADMIN'))) {
            redirect controller: 'admin', action: 'index'
        } else {
            redirect controller: 'dashboard', action: 'index'
        }
        return
    }
}
