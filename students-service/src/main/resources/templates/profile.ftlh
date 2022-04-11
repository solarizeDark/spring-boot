<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Profile</title>
</head>
<body>

<h1>Profile page</h1>
<br>
<label for="info">Personal info</label>

<form action="/profile" method="post">
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <input type="text" placeholder="name" name="name"/>
    <br>
    <input type="text" placeholder="surname" name="surname"/>
    <br>
    <input type="text" placeholder="age" name="age"/>
    <br>
    <input type="text" placeholder="group" name="group"/>
    <br>
    <button type="submit">Add info</button>
</form>

<#if error??>
    ${error}
</#if>

</body>
</html>
