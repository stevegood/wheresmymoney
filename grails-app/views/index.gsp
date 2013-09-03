<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="bootstrap"/>
		<title>Welcome</title>
	</head>
	<body>

    <div class="container">
        <h1><g:message code="wheres.my.money" default="Where's My Money" /></h1>
        <ul>
            <g:each in="${banks}" var="bank">
                <li>
                    <h3>${bank.name}</h3>
                    <ul>
                        <g:each in="${bank.accounts}" var="account">
                            <li>
                                <h4>${account.name}</h4>
                                <table class="table">
                                    <tr>
                                        <th>Transaction date</th>
                                        <th>Name</th>
                                        <th>Date Posted</th>
                                        <th>Amount</th>
                                        <th>Balance</th>
                                    </tr>
                                    <g:each in="${account.transactions}" var="transaction">
                                        <tr>
                                            <td>${transaction.transactionDate.format(message(code: 'default.date.only.format'))}</td>
                                            <td>${transaction.displayName}</td>
                                            <td>${transaction.datePosted.format(message(code: 'default.date.only.format'))}</td>
                                            <td class="transaction-${transaction.amount < 0 ? 'negative' : 'positive'}">${transaction.amount}</td>
                                            <td class="transaction-${transaction.statementBalance < 0 ? 'negative' : 'positive'}">${transaction.statementBalance}</td>
                                        </tr>
                                    </g:each>
                                </table>
                            </li>
                        </g:each>
                    </ul>
                </li>
            </g:each>
        </ul>
    </div>

	</body>
</html>
