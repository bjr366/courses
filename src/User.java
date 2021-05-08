

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

abstract class User implements Serializable{

	private static final long serialVersionUID = 234513245L;
	
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	
	//Getters and setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	//Constructors
	public User(){
		
	}

	public User(String username, String password, String firstname, String lastname) {
		
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	//Methods
	
	
	
	
	//view all courses 
	public void viewCourses(ArrayList<Course> list){
		Course x;
		for(int i = 0; i < list.size(); i++) {
			x = list.get(i);
			System.out.println("-------------------------------------------------------------");
			System.out.println("Course Name: " + x.getCourseName());
			System.out.println("Course ID: " + x.getCourseID());
			System.out.println("Maximum Students: " + x.getCourseMax());
			System.out.println("Current Number of Students: " + x.getCourseCurrentNum());
			System.out.println("List of Names: " + x.getStudents());
			System.out.println("Course Instructor: " + x.getCourseInstructor());
			System.out.println("Course Section Number: " + x.getCourseSection());
			System.out.println("Course Location: " + x.getCourseLocation());
			System.out.println("-------------------------------------------------------------");
		}
		
	}
	
	
	//View all courses a student is enlisted in
	public ArrayList<Course> studentCourses(ArrayList<Course> all, String first, String last) {
		ArrayList<Course> myCourses = new ArrayList<Course>();
		Course x;
		String fullName = first + " " + last;
		for (int i = 0; i < all.size(); i++) {
			x = all.get(i);
			if (x.getStudents().contains(fullName)) {
				myCourses.add(x);
			}
		}
		return myCourses;
	
	}
	
	//in case of two courses having same name and ID
	public ArrayList<Course> specifyInstructor(ArrayList<Course> multi) {
		Scanner input = new Scanner(System.in);
		System.out.println("There are multiple courses with this same ID. Please specify the Professor");
		String professor = input.nextLine();
		ArrayList<Course> dont = new ArrayList<Course>();
		ArrayList<Course> yes = new ArrayList<Course>();
		for(int i = 0; i < multi.size(); i++) {
			if (!multi.get(i).getCourseInstructor().equals(professor)) {
				dont.add(multi.get(i));
			}
		}
		for (int j = 0; j < multi.size(); j++) {
			if (!dont.contains(multi.get(j))) {
				yes.add(multi.get(j));
			}
		}
		return yes;
	}
	
	//find a course given course name
	public Course findCourse(ArrayList<Course> all, String courseName) {
		ArrayList<Course> hold = new ArrayList<Course>();
		Course x;
		for (int i = 0; i < all.size(); i++) {
			x = all.get(i);
			if(x.getCourseName().equals(courseName)) {
				hold.add(x);
			}
		}
		if (hold.size() > 1) {
			return (specifyInstructor(hold).get(0));
		}
		else {
			return hold.get(0);
		}
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	
}
