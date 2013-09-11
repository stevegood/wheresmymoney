<html>
<head>
	<meta name='layout' content='main'/>
	<title><g:message code="springSecurity.login.title"/></title>
</head>

<body>
    <g:render template="form/loginForm" model="${[postUrl: postUrl, rememberMeParameter: rememberMeParameter]}" />
</body>
</html>
