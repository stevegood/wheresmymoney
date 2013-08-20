class MainController {

    def springSecurityService

    def index() {

        render view: '/index', model: [:]
    }
}
