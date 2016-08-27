cd ../../assignment5
grails create-app courseManager 
cd courseManager
grails create-controller enrollment 
grails create-domain-class coursemanager.Enrollment 
grails create-domain-class coursemanager.Course
grails create-scaffold-controller coursemanager.Course
grails create-domain-class coursemanager.Major
