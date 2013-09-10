<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="bootstrap"/>
		<title>Welcome</title>
        <r:require modules="dashboard" />
        <style>
            #categoriesLegend .item {
                display: block;
                margin: 0.5em;
                border-style: solid;
                border-width: 0 0 0 1em;
                padding: 0 0.3em;
            }

            #sidebar {
                margin: 7px 0 0 0;
            }
        </style>
	</head>
	<body>

    <div class="container">

        <div class="row">
            <div class="col-md-9">
                <div class="page-header">
                    <h2><g:message code="category.overview" default="Category Overview" /></h2>
                </div>
                %{--Main content--}%
                <div class="row">
                    <div class="col-md-5">
                        <canvas id="categoriesChart" height="300" width="300"></canvas>
                    </div>

                    <div id="categoriesLegend" class="col-md-3"></div>
                </div>
            </div>

            <div id="sidebar" class="col-md-3">
                %{--Sidebar--}%
                <div class="page-header">
                    <h3><g:message code="banks.and.accounts" default="Banks &amp; Accounts" /></h3>
                </div>
                <ul class="nav nav-stacked">
                    <g:each in="${banks}" var="bank">
                        <li>
                            <g:link controller="bank" action="show" id="${bank.id}">${bank.name}</g:link>
                            <ul class="nav nav-stacked">
                                <g:each in="${bank.accounts}" var="account">
                                    <li>
                                        <g:link controller="account" action="show" id="${account.id}">${account.name} <span class="pull-right">${account.balance}</span></g:link>
                                    </li>
                                </g:each>
                            </ul>
                        </li>
                    </g:each>
                </ul>
            </div>
        </div>

    </div>

	</body>
</html>
