package coursemanager
/**
 * Course Domain Class
 * 
 * @author bradyhouse@gmail.com
 */
class Course {
	String title
	Integer courseCredits
	String description
	static constraints = {
	title blank: false
	courseCredits blank: false
	description blank: false
	}
}
