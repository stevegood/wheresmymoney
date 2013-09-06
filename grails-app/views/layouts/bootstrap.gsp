<%--
  Created by IntelliJ IDEA.
  User: stgood
  Date: 8/20/13
  Time: 12:35 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title><g:layoutTitle/> - <g:message code="wheres.my.money" default="Where's My Money" /></title>
    <r:require modules="bootstrap,main" />
    <r:layoutResources />
    <g:layoutHead />
</head>
<body>

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <g:link controller="main" action="index" class="navbar-brand">Where's My Money</g:link>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <sec:ifLoggedIn>
                    <li class="${(params.controller == 'main' && params.action == 'index') ? 'active' : ''}">
                        <g:link controller="main" action="index">Dashboard</g:link>
                    </li>
                    <li class="${(params.controller == 'bank') ? 'active' : ''}">
                        <g:link controller="bank" action="list">My Banks</g:link>
                    </li>
                    <li class="${(params.controller == 'transaction' && params.action == 'importTransactions') ? 'active' : ''}">
                        <g:link controller="transaction" action="import">Import Transactions</g:link>
                    </li>
                    <sec:ifAllGranted roles="ROLE_SUPER_ADMIN">
                        <li class="${params.controller == 'admin' ? 'active' : ''}">
                            <g:link controller="admin" action="index">
                                <g:message code="menu.admin" default="Admin" />
                            </g:link>
                        </li>
                    </sec:ifAllGranted>
                </sec:ifLoggedIn>
            </ul>

            <ul class="nav navbar-nav pull-right">
                <sec:ifLoggedIn>
                    <li>
                        <g:link controller="logout" action="index">Logout</g:link>
                    </li>
                </sec:ifLoggedIn>
                <sec:ifNotLoggedIn>
                    <li>
                        <g:link controller="login" action="auth">Login</g:link>
                    </li>
                </sec:ifNotLoggedIn>
            </ul>
        </div>
    </div>
</div>

<g:layoutBody/>

<r:layoutResources />
</body>
</html>