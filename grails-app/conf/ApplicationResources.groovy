modules = {
    application {
        resource url:'js/application.js'
    }

    bootstrap {
        dependsOn 'jquery'
    	resource url: 'js/bootstrap.min.js'
    	resource url: 'css/magic-bootstrap.css'
    }

    main {
        resource url: 'css/main.css'
    }

    loginform {
        resource url: 'css/login-form.css'
    }

    importTransactions {
        dependsOn 'jquery'
        resource url: 'js/transaction/importTransactions.js'
    }

    chartjs {
        resource url: 'js/Chart.min.js'
    }

    dashboard {
        dependsOn 'jquery,chartjs'
        resource url: 'js/main/index.js'
    }
}
