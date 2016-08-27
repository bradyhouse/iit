package coursemanager
/**
 * Course Domain Class
 * 
 * @author bradyhouse@gmail.com
 */
class Course {
	Integer courseId
	// Required Fields
	String title
	Integer creditHours
	String description
	String toString() {
		return this.title	
	}
	static constraints = {
		courseId(blank: false, nullable: false, unique: true, min: 1)
		title(blank: false, nullable: false, unique: true)
		description(blank: false, nullable: false)
		creditHours(blank: false, nullable: false, min: 3, max: 4)
    }
}
