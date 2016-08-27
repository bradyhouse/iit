package coursemanager

/**
 * Enrollment Domain Class. The domain is
 * used to map (or link) users to courses.
 * 
 * @author bradyhouse@gmail.com
 */
class Enrollment {
	Integer enrollmentId
	User user
	Course course
    String toString() {
		return this.user.toString() + ' - ' 
		+ this.course.toString()
	}
	static constraints = {
		enrollmentId(blank: false, nullable: false, unique: true, min: 1)
		user(nullable: false)
		course(nullable: false)
    }
}
