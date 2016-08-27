<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">
  		<asset:stylesheet src="application.css"/>
		<asset:javascript src="application.js"/>
		<g:layoutHead/>
	</head>
	<body>
		<div id="grailsLogo" role="banner"><h1>Course Manager</h1></div>
		<g:layoutBody/>
		<div class="footer" role="contentinfo"></div>
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
		<div id="loginBox" class="loginBox">
  <g:if test="${session?.user}">
    <div style="margin-top:20px">
      <div style="float:right;">
        <a href="#">Profile</a> | <g:link controller="user"
                                   action="logout">Logout</g:link><br>
      </div>
      Welcome back
      <span id="userFirstName">
        ${session?.user?.firstName}!
      </span><br><br>
      You have purchased (${session.user.purchasedSongs?.size() ?: 0}) songs.<br>
    </div>
  </g:if>
  <g:else>
    <g:form
      name="loginForm"
      url="[controller:'user',action:'login']">
      <div>Username:</div>
      <g:textField name="login"
                   value="${fieldValue(bean:loginCmd, field:'login')}">
      </g:textField>
      <div>Password:</div>
      <g:passwordField name="password"></g:passwordField>
      <br/>
      <input  type="image"
          src="${createLinkTo(dir:'images', file:'login-button.gif')}"
          name="loginButton" id="loginButton" border="0"></input>
    </g:form>
    <g:renderErrors bean="${loginCmd}"></g:renderErrors>
  </g:else>
</div>
<div id="navPane">
      <g:if test="${session.user}">
        <ul>
          <li><g:link controller="user"
                      action=“enrollment”>My Classes</g:link></li>
          <li><g:link controller="store"
                      action=“add”>Add Class</g:link></li>
        </ul>
      </g:if>
      <g:else>
        <div id="registerPane">
          Need an account?
            <g:link controller="user"
                    action="register">Signup now</g:link>
          to enroll in classes!
        </div>
      </g:else>
</div>
	</body>
</html>
