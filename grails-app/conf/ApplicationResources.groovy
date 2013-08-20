modules = {
    application {
        resource url:'js/application.js'
    }

    bootstrap {
        dependsOn 'jquery'
    	resource url: 'js/bootstrap.min.js'
    	resource url: 'css/bootstrap.min.css'
    }

    main {
        resource url: 'css/main.css'
    }

    loginform {
        resource url: 'css/login-form.css'
    }
}