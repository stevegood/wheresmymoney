<r:require module="loginform" />

<form action="${postUrl}" method="POST" class="form-signin">
    <h2 class="form-signin-heading">Please sign in</h2>
    <input type="text" class="form-control" placeholder="Username" name="j_username" autofocus>
    <input type="password" class="form-control" placeholder="Password" name="j_password">
    <label class="checkbox">
        <input type="checkbox" value="remember-me" name='${rememberMeParameter}' <g:if test='${hasCookie}'>checked='checked'</g:if>> Remember me
    </label>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
</form>