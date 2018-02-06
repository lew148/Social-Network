<#-- @ftlvariable name="error" type="String" -->

<!DOCTYPE html>
<html lang="en">
<#include "Head.ftl">
<body>

<div class="ui main text container">

<#if error??>
    <div class="ui negative message">
        <i class="close icon"></i>
        <div class="header">
            User Registration Failed
        </div>
        <p>${error}</p>
    </div>
</#if>

    <form name="form" class="ui form" action="/signup" method="post">

        <div class="ui segment">

            <div class="field">
                <label for="username" class="ui label">Username</label>
                <input name="username"
                       id="username"
                       type="text"
                       placeholder="JohnDoe"
                       required="required"
                       maxlength="20"/>
            </div>

            <div class="field">
                <label for="fullname" class="ui label">Full Name</label>
                <input name="fullname"
                       id="fullname"
                       type="text"
                       placeholder="John Doe"
                       required="required"
                       maxlength="128"/>
            </div>

            <div class="field">
                <label for="password" class="ui label">Password</label>
                <input name="password"
                       id="password"
                       type="password"
                       required="required"
                       maxlength="128"/>
            </div>

            <button id="createUser" class="ui button" type="submit">Sign Up</button>

        </div>
    </form>
</div>

</body>
</html>