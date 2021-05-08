
import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2938456L;
	
	String courseName;
	String courseID;
	private int courseMax;
	public int courseCurrentNum;
	private ArrayList<String> students = new ArrayList<String>();
	private String courseInstructor;
	private String courseSection;
	private String courseLocation;
	
	
	
	//getters and setters
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public int getCourseMax() {
		return courseMax;
	}

	public void setCourseMax(int courseMax) {
		this.courseMax = courseMax;
	}

	public int getCourseCurrentNum() {
		return courseCurrentNum;
	}

	public void setCourseCurrentNum(int courseCurrentNum) {
		this.courseCurrentNum = courseCurrentNum;
		//the current number of students is capped at the max
		if (this.courseCurrentNum > courseMax){
			this.courseCurrentNum = courseMax;
		}
	}

	public String getCourseInstructor() {
		return courseInstructor;
	}

	public void setCourseInstructor(String courseInstructor) {
		this.courseInstructor = courseInstructor;
	}

	public String getCourseSection() {
		return courseSection;
	}

	public void setCourseSection(String courseSection) {
		this.courseSection = courseSection;
	}

	public String getCourseLocation() {
		return courseLocation;
	}

	public void setCourseLocation(String courseLocation) {
		this.courseLocation = courseLocation;
	}

	public ArrayList<String> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<String> students) {
		this.students = students;
	}

	//constructor
	public Course(String courseName, String courseID, int courseMax, int courseCurrentNum, ArrayList<String> students, String courseInstructor,
			String courseSection, String courseLocation) {
		super();
		this.courseName = courseName;
		this.courseID = courseID;
		this.courseMax = courseMax;
		this.courseCurrentNum = courseCurrentNum;
		this.courseInstructor = courseInstructor;
		this.courseSection = courseSection;
		this.courseLocation = courseLocation;
		this.students = students;
	}
	
	public Course() {
		
	}
	
	
}

