package coursemanager

/**
 * User Controller class.
 * 
 * @author bradyhouse@gmail.com
 *
 */
class UserController {
	def scaffold = true
	def register() {
		if(request.method == 'POST') {
			def u = new User()
			u.properties['userName', 'password'] = params
			if(u.password != params.confirm) {
				u.errors.rejectValue("password", "user.password.dontmatch")
				return [user:u]
			} else if (u.save()) {
				session.user = u
				this.back()
			} else {
				return [user:u]
			}
		}
	}
	def login(LoginCommand cmd) {
		if(request.method == 'POST') {
		  if(!cmd.hasErrors()) {
			session.user = cmd.getUser()
		  	this.back()
		  } else {
			render view:'/', model:[loginCmd:cmd]
		  }
		} else {
		  this.back()
		} 
	}
	def logout = {
		session.invalidate()
		this.back()
	}
	def back() {
		redirect uri:'/'
	}
}
class LoginCommand {
	String userName
	String password
	private u
	User getUser() {
		if(!u && userName) {
			u = User.findByUserName(userName, [fetch:[Enrollment:'join']])
		}
		return u
	}
	static constraints = {
		userName blank:false, validator:{ val, obj ->
			if(!obj.user)
				return "user.not.found"
		}
		password blank:false, validator:{ val, obj ->
			if(obj.user && obj.user.password != val)
				return "user.password.invalid"
		}
	}
}
	