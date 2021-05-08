

import java.util.ArrayList;

interface StudentAction {
	//view all courses that are not full 
	public ArrayList<Course> notFullCourses(ArrayList<Course> all);
	
	//register in a course
	public void register(ArrayList<Course> all, String courseName, String courseSection, String first, String last);
	
	//withdraw from a course 
	public void withdraw(ArrayList<Course> all, String courseName, String first, String last);
	
}
