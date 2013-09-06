class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller: 'main', action: 'index')
        "/login"(controller: 'login', action: 'auth')
        "/logout"(controller: 'logout', action: 'index')
        "/transaction/import"(controller: 'transaction', action: 'importTransactions')
		"500"(view:'/error')
	}
}
