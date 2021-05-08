

import java.io.Serializable;
import java.util.ArrayList;


class Student extends User implements StudentAction, Serializable{
	
	private static final long serialVersionUID = 56783457L;

	public Student(String username, String password, String firstname, String lastname) {
		super(username, password, firstname, lastname);
	}
	
	public Student() {
		super();
	}
	
	
	//implemented methods from student action
	
	// view all not full courses 
	public ArrayList<Course> notFullCourses(ArrayList<Course> all) {
		ArrayList<Course> notFull = new ArrayList<Course>();
		Course x;
		for (int i = 0; i < all.size(); i++) {
			x = all.get(i);
			if (x.getCourseCurrentNum() < x.getCourseMax()) {
				notFull.add(x);
			}
		}
		return notFull;
	}
	
	//register for course 
	public void register(ArrayList<Course> all, String courseName, String courseSection, String first, String last) {
		Course x = findCourse(all, courseName);
		String fullName = first + " " + last;
		int studentCount = x.getCourseCurrentNum();
		//check if student is in course then add
		if (!x.getStudents().contains(fullName)) {
			x.getStudents().add(fullName);
			//increment number of students by 1;
			studentCount += 1;
			x.setCourseCurrentNum(studentCount);
		}
		
		System.out.println("You are now enrolled in " + courseName);
	}
	
	//withdraw from course;
	public void withdraw(ArrayList<Course> all, String courseName, String first, String last) {
		Course x = findCourse(all, courseName);
		String fullName = first + " " + last;
		int studentCount = x.getCourseCurrentNum();
		//check if student is in course then remove
		if (x.getStudents().contains(fullName)) {
			x.getStudents().remove(fullName);
			//decrease number of students by 1;
			studentCount -= 1;
			x.setCourseCurrentNum(studentCount);
		}
		
		System.out.println("You are no longer enrolled in " + courseName);
		
	}

	
	
	
	
}
