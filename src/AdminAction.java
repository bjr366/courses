

import java.util.ArrayList;

interface AdminAction {
	//REPORTS
	//view all courses that are full
	public ArrayList<Course> fullCourses(ArrayList<Course> all);
	
	//write to file list of full courses
	public void writeFullCourses(ArrayList<Course> fullCourses);
	
	//view students in a specific course
	public String studentsInCourse(ArrayList<Course> all, String courseName);
	
	
	//sort courses based on current number of students registered
	public ArrayList<Course> sortCourses(ArrayList<Course> all);
	
	
	
	//COURSE MANAGEMENT
	//create a new course
	public void createCourse(ArrayList<Course> all, String courseName, String courseID, int courseMax, String courseInstructor,
			String courseSection, String courseLocation);
	
	//delete a course
	public void deleteCourse(ArrayList<Course> all, String name);
	
	//edit a course
	public void editCourse(Course e, int courseMax, String courseInstructor,
			String courseSection, String courseLocation);
	
	//display info for given Course
	public ArrayList<Course> displayCourse(ArrayList<Course> all, String ID);
	
	//add a new Student
	public void addStudent(ArrayList<Student> allStudents, String username, String password, String firstname, String lastname);
}
