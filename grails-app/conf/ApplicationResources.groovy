modules = {

    bootstrap {
        dependsOn 'jquery'
        defaultBundle 'ui'
    	resource url: 'js/bootstrap.min.js'
    	resource url: 'css/magic-bootstrap.css'
    }

    main {
        defaultBundle 'ui'
        resource url: 'css/main.css'
    }

    auth {
        defaultBundle 'login'
        resource url: 'css/login/auth.css'
    }

    importTransactions {
        dependsOn 'jquery'
        defaultBundle 'transaction'
        resource url: 'js/transaction/importTransactions.js'
    }

    chartjs {
        defaultBundle 'ui'
        resource url: 'js/Chart.min.js'
    }

    dashboard {
        dependsOn 'jquery,chartjs'
        defaultBundle 'dashboard'
        resource url: 'js/dashboard/index.js'
        resource url: 'css/dashboard/index.css'
    }
}
