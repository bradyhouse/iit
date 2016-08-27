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
				<div style="margin-top:20px; padding: 10px 10px 10px 10px;">
					<div style="float:right;">
						<g:link controller="user" action="logout">Logout</g:link>
					</div>
					Welcome back
					<span id="userName" style="font-weight: bold;">
						<i>${session?.user?.userName}</i>
					</span><br><br>
				</div>
			</g:if>
			<g:else>
				<div style="padding: 10px 10px 10px 10px;">
					<g:form name="loginForm" url="[controller:'user',action:'login']">
						<table>
							<tr>
								<td nowrap>
									<div>Username:</div>
									<g:textField name="userName" value="${fieldValue(bean:loginCmd, field:'userName')}"></g:textField>
								</td>
							</tr>
							<tr>
								<td>
									<div>Password:</div>
									 <g:passwordField name="password"></g:passwordField>
								</td>
							</tr>
							<tr>
								<td>
									<g:submitButton class="formButton" name="login" value="Login"></g:submitButton>
								</td>
							</tr>
						</table>
					</g:form>
					<g:renderErrors beans="${loginCmd}"></g:renderErrors>
				</div>
			</g:else>
		</div>
		<div id="navPane" style="padding: 10px 10px 10px 10px;">
			<g:if test="${session.user}">
				<div id="controller-list" role="navigation">
					<ul>
					 <li><g:link controller="Course">Courses</g:link></li>
				     <li><g:link controller="User">Users</g:link></li>
					 <li><g:link controller="Enrollment">Enrollment</g:link></li>
					</ul>
				</div>
			</g:if>
			<g:else>
				<div id="registerPane">
					Need an account?
					<g:link controller="user" action="register">Signup now</g:link> 
					to start your own personal Course collection!
				</div>
			</g:else>
		</div>
	</body>
</html>
