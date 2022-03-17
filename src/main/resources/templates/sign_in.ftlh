<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Please, sign in</title>
</head>
<body>
<h1>Sign in</h1>

<form role="form" action="/signIn" method="post">
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <div>
        <label for="username">Username</label>
        <input type="text" name="username" id="username" required autofocus/>
    </div>
    <div>
        <label for="password">Password</label>
        <input type="password" name="password" id="password" required/>
    </div>
    <button type="submit">Sign In</button>
</form>
</body>
</html>