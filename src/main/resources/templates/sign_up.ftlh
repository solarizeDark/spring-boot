<!DOCTYPE html>
<html lang="en">
<#import "spring.ftl" as spring/>
<head>
    <meta charset="UTF-8">
    <title><@spring.message 'signUp.title'/></title>
</head>
<body>

<h2><a href="?lang=ru">ru</a> </h2>
<h2><a href="?lang=en">en</a> </h2>

<@spring.bind "signUpForm"/>

<h1><@spring.message 'signUp.head'/></h1>

<#if usernamePasswordEquality ?? >
    ${usernamePasswordEquality}
</#if>

<form role="form" action="/signUp" method="post">
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <div>
        <label for="email"><@spring.message 'signUp.email'/></label>
        <@spring.formInput "signUpForm.email" "id='email'"/>
        <@spring.showErrors "<br>" "error"/>
    </div>
    <div>
        <label for="username"><@spring.message 'signUp.username'/></label>
        <@spring.formInput "signUpForm.username" "id='username'"/>
        <@spring.showErrors "<br>" "error"/>
    </div>
    <div>
        <label for="password"><@spring.message 'signUp.password'/></label>
        <@spring.formInput "signUpForm.password" "id='password'" "password"/>
        <@spring.showErrors "<br>" "error"/>
    </div>
    <button type="submit"><@spring.message 'signUp.submitButton'/></button>
</form>
</body>
</html>