package coursemanager

/**
 * User Domain model.
 * 
 * @author bradyhouse@gmail.com
 */
class User {
	Integer userId
	// Required Fields
	String userName
	String password
	String toString() {
		return this.userName
	}
	// TODO - Encrypt Password and validate--i.e. alphanumeric, has an integer, at least 8 characters
	static constraints = {
		userId(blank: false, nullable: false, unique: true, min: 1)
		userName(blank: false, nullable: false, unique: true)
		password(blank: false, nullable: false)
	}
}
