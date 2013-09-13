<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome</title>
    <r:require modules="dashboard"/>
</head>

<body>

<div class="container">

    <div class="row">
        <div id="sidebar" class="col-md-3">
            %{--Sidebar--}%
            <div class="well">

                <div class="sidebar-item">
                    <div class="page-header">
                        <h3><g:message code="accounts" default="Accounts"/></h3>
                    </div>
                    <g:each in="${banks}" var="bank">
                        <g:each in="${bank.accounts}" var="account" status="i">
                            <div class="account-item ${i == bank.accounts.size() - 1 ? 'last' : ''}">
                                <div>
                                    <g:link controller="account" action="show" id="${account.id}" class="bank-name">
                                        ${bank.name}
                                    </g:link>
                                    <span class="account-balance pull-right">${account.balance}</span>
                                </div>
                                <span class="account-name">${account.name}</span>
                            </div>
                        </g:each>
                    </g:each>
                </div>

                <div class="sidebar-item">
                    <div class="page-header">
                        <h3><g:message code="trends" default="Trends" /></h3>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-9">
            <div class="page-header">
                <h2><g:message code="category.overview" default="Category Overview"/></h2>
            </div>
            %{--Main content--}%
            <div class="row">
                <div class="col-md-5">
                    <canvas id="categoriesChart" height="300" width="300"></canvas>
                </div>

                <div id="categoriesLegend" class="col-md-3"></div>
            </div>
        </div>
    </div>

</div>

</body>
</html>
