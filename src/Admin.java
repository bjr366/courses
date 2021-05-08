

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User implements AdminAction{
	
	public Admin(String username, String password, String firstname, String lastname) {
		super(username, password, firstname, lastname);
		
	}
	
	public Admin() {
		super();
	}
	
	//implemented methods 
	
	//REPORTS
	//view full courses 
	public ArrayList<Course> fullCourses(ArrayList<Course> all) {
		Course x;
		ArrayList<Course> full = new ArrayList<Course>();
		for (int i = 0; i < all.size(); i++) {
			x = all.get(i);
			if (x.getCourseCurrentNum() == x.getCourseMax()) {
				full.add(x);
			}
		}
		return full;
	}
	
	//write to a file the courses that are full 
	//From fileWriterTest.java
	public void writeFullCourses(ArrayList<Course> fullCourses) {
		String full = "Full_Courses.csv";
		try{
			FileWriter fileWriter = new FileWriter(full);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			Course x;
			String text = "";
			//shortcut separation
			String s = ", ";
			
			//get elements of each full course into a String
			for (int i = 0; i < fullCourses.size(); i ++) {
				x = fullCourses.get(i);
				text += x.getCourseName() + s + x.getCourseID() + s + x.getCourseMax() + s +
						x.getCourseCurrentNum() + s +  x.getStudents() + s
						+  x.getCourseInstructor() + s + x.getCourseSection() + s +
						x.getCourseLocation();
			}
			//write full courses to file
			bufferedWriter.write(text);
			bufferedWriter.newLine();

			
			//close writer
			bufferedWriter.close();
		}
		catch (IOException exk) {
			System.out.println( "Error writing file '" + full + "'");
			exk.printStackTrace();
		}
		
	}
	
	//view students in a specific course
	public String studentsInCourse(ArrayList<Course> all, String courseName) {
		String s = "";
		Course x = findCourse(all, courseName);
		for (int i = 0; i < x.getStudents().size(); i++) {
			s += x.getStudents().get(i) + ", ";
		}
		return s;
	}

	//sort courses based on current number of students
	public ArrayList<Course> sortCourses(ArrayList<Course> all) {
		Course tmp;
		for (int i = 0; i < all.size(); i++) {
			for (int j = all.size() - 1; j > i; j--) {
				if (all.get(i).courseCurrentNum > all.get(j).courseCurrentNum) {
					tmp = all.get(i);
					all.set(i, all.get(j));
					all.set(j,tmp);
				}
			}
		}
		return all;
	}
	
	//create new course
	public void createCourse(ArrayList<Course> all, String courseName, String courseID, int courseMax, String courseInstructor,
			String courseSection, String courseLocation) {
		ArrayList<String> none = new ArrayList<String>();
		Course plus = new Course(courseName, courseID, courseMax, 0, none, courseInstructor,
				courseSection, courseLocation);
		all.add(plus);
	}
	
	//delete a course
	public void deleteCourse(ArrayList<Course> all, String courseName) {
		Course x = findCourse(all, courseName);
		all.remove(x);
	}
	
	//edit a course (except for course name and ID)
	public void editCourse(Course e, int courseMax, String courseInstructor,
			String courseSection, String courseLocation) {
		e.setCourseMax(courseMax);
		e.setCourseInstructor(courseInstructor);
		e.setCourseSection(courseSection);
		e.setCourseLocation(courseLocation);
		
	}
	
	//display a course given course ID
	public ArrayList<Course> displayCourse(ArrayList<Course> all, String ID) {
		ArrayList<Course> hold = new ArrayList<Course>();
		Course x;
		for (int i = 0; i < all.size(); i++) {
			x = all.get(i);
			if(x.getCourseID().equals(ID)) {
				hold.add(x);
			}
			
		}
		if (hold.size() > 1) {
			return specifyInstructor(hold);
		}
		else {
			return hold;
		}
	}

	public void addStudent(ArrayList<Student> allStudents, String username, String password, String firstname, String lastname) {
		Student add = new Student(username, password, firstname, lastname);
		allStudents.add(add);
	
	}
	
	
}
