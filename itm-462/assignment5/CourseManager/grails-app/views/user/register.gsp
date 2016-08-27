<!DOCTYPE html>
<html>
	<head>
		<title><g:if env="development">Grails Runtime Exception</g:if><g:else>Error</g:else></title>
		<meta name="layout" content="main">
		<g:if env="development"><asset:stylesheet src="errors.css"/></g:if>
	</head>
	<body id="body">
        <h1>Registration</h1>
     
        <table>
        	<tr>
        		<td>
        			   <p style="padding: 20px 0px 20px 20px;">Complete the form below to create an account!</p>
        <g:hasErrors bean="${user}">
          <div class="errors">
            <g:renderErrors bean="${user}"></g:renderErrors>
          </div>
        </g:hasErrors>
        			<g:form controller="user" action="register" name="registerForm">
				        	<table>
				        		<tr>
				        			<td>
				        			  <div class="formField">
							            <label for="userName">User Name:</label>
							            <g:textField name="userName" value="${user?.userName}"></g:textField>
							          </div>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td>
				        			  <div class="formField">
							            <label for="password">Password:</label>
							            <g:passwordField name="password" value="${user?.password}"></g:passwordField>
							          </div>
				        			</td>
				        		</tr>
				        		<tr>
				        			<td>
							          <div class="formField">
							            <label for="confirm">Confirm Password:</label>
							            <g:passwordField name="confirm" value="${params?.confirm}"></g:passwordField>
							          </div>        			
				        			</td>
				        		</tr>
				        		<tr>
				        			<td>
				    					<g:submitButton class="formButton" name="register" value="Register"></g:submitButton>
				        			</td>
				        		</tr>
				        	</table>
					</g:form>
        		</td>
        	</tr>
        	<tr>
        		<td style="border-top: 1px;">
        			<g:form controller="user" action="back" name="cancelRegisterForm">
						<g:submitButton class="formButton" name="cancel" value="Cancel"></g:submitButton>
					</g:form>        		
        		</td>
        	</tr>
        </table>


</body>
</html>
