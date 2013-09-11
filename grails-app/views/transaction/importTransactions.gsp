<%--
  Created by IntelliJ IDEA.
  User: steve
  Date: 9/6/13
  Time: 2:13 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Import Transactions</title>
    <meta name="layout" content="main" />
    <r:require module="importTransactions" />
</head>

<body>

<div class="container">
    <h1>Import Transactions</h1>

    <hr>

    <g:uploadForm action="upload" class="form" role="form">
        <div class="form-group">
            <select class="form-control" name="account" id="account">
                <option value="0" selected="selected">Select Account...</option>
                <g:each in="${banks}" var="bank">
                    <optgroup label="${bank.name}">
                        <g:each in="${bank.accounts}" var="account">
                            <option value="${account.id}">${account.name}</option>
                        </g:each>
                    </optgroup>
                </g:each>
            </select>
        </div>

        <div class="form-group">
            <label class="control-label" for="transactionFile">Select Transactions File</label>
            <input type="file" id="transactionFile" name="transactionFile" accept=".ofx, .qfx" disabled/>
            <p class="help-block">Supported formats: <em>*.ofx</em>, <em>*.qfx</em></p>
        </div>

        <button type="submit" id="uploadBtn" class="btn btn-primary" disabled>Upload</button>
    </g:uploadForm>
</div>

</body>
</html>