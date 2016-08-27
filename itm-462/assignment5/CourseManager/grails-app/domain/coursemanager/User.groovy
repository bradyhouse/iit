package coursemanager

/**
 * User Domain model.
 * 
 * @author bradyhouse@gmail.com
 */
class User {
	// Required Fields
	String userName
	String password
	String toString() {
		return this.userName
	}
	static constraints = {
		userName blank:false, size:5..15, matches:/[\S]+/, unique: true
		password blank:false, size:5..15, matches:/[\S]+/
	}
}
