

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;

//This is where I execute my main method
public class CourseRegistrationSystem {
	public static void main(String[] args) {
		
		
		//Main ArrayLists of courses and students
		ArrayList<Course> all = new ArrayList<Course> ();
		ArrayList<Student> allStudents = new ArrayList<Student>();
		
		
		Course readCourse; 
		String csvFile = "MyUniversityCourses (3).csv";
		String line = null;
		
		//DESERIALIZE
		if (new File("Byte_Stream_of_Courses.csv").exists()) {
			all = deserializeCourses();
			allStudents = deserializeStudents();
			
		}
	
		else {
			//Read from file and store in course array
			//from readerTest.java
			try {
				FileReader fileReader = new FileReader(csvFile);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				
				bufferedReader.readLine();
				
				while((line = bufferedReader.readLine()) != null) {
					String[] entries = line.split(",");
					ArrayList<String> students = new ArrayList<String>();
					students.add("");//I added this because of a "null pointer" error 
					readCourse = new Course(entries[0], entries[1], Integer.parseInt(entries[2]), Integer.parseInt(entries[3]), students , entries[5],
							entries[6], entries[7]);
					all.add(readCourse);
					//Test
					//System.out.println(line);
				}
				
				bufferedReader.close();
			}
			catch(FileNotFoundException ex) {
				System.out.println("Unable to open file '" + csvFile + "'");
				ex.printStackTrace();
			}
			
			catch (IOException ex) {
				System.out.println( "Error reading file '" + csvFile + "'");
				ex.printStackTrace();
			}
		
		}
		
		//create admin user
		Admin admin = new Admin("admin", "admin001", "Brian", "Rios");
		
		//Introduction and Login 
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to the University Course Registration System."
				+ "Please enter a username and password.");
		System.out.println("Username: ");
		String username = input.nextLine().toLowerCase();
		System.out.print("Password: ");
		String password = input.nextLine().toLowerCase();
		
		//If User is an Admin
		if(username.equals("admin") && password.equals("admin001")) {
			//Introduction
			System.out.println("Welcome Aboard Administrator! "
					+ "Please select one of the keys listed below ... " + "\n");
			
			//User select choice
			String choice;
			do {
			//Menu
			System.out.print(
					"------------------------------------------------------------------" + "\n" +
					"COURSE MANAGEMENT" + "\n" + 
					"'C' - Create a new course." + "\n" + 
					"'D' - Delete a course." + "\n" + 
					"'E' - Edit a Course." + "\n" + 
					"'I' - Display information for a given course." + "\n" + 
					"'R' - Register a Student." + "\n" + 
					"------------------------------------------------------------------" + "\n" +
					"REPORTS" + "\n" +
					"'W' - Write to a file the courses that are full." + "\n" +
					"'A' - View All Courses." + "\n" +
					"'F' - View All Courses that are FULL" + "\n" + 
					"'SN' - View the names of the students that are registered in a specific course" + "\n" + 
					"'SC' - View the list of Courses that a given student is registed in." + "\n" +
					"'S' - Sort the Courses based on the number of students registered." + "\n" +
					"'Q' - Quit." + "\n" +
					"------------------------------------------------------------------");
			choice = input.next().toUpperCase();
			Scanner create = new Scanner(System.in);
			String courseName;
			String courseID;
			int courseMax;
			String courseInstructor;
			String courseSection;
			String courseLocation;
			String firstName;
			String lastName;
				switch (choice){
					
					case "C":
						line();
						System.out.println("Let's create a new course! Fill in the following ...");
						System.out.println("Course Name: ");
						courseName = create.nextLine();
						System.out.println("Course ID: ");
						courseID = create.nextLine();
						System.out.println("Course Maximum Number of Students: ");
						courseMax = create.nextInt();
						create.nextLine();
						System.out.println("Course Instructor: ");
						courseInstructor = create.nextLine();
						System.out.println("Course Section: ");
						courseSection = create.nextLine();
						System.out.println("Course Location: ");
						courseLocation = create.nextLine();
						admin.createCourse(all, courseName, courseID, courseMax, courseInstructor,
								courseSection, courseLocation);
						Course find = admin.findCourse(all, courseName);
						System.out.println("You have just created the course : " + find.getCourseName() + "!");
						line();
						break;
	
					case "D":
						line();
						System.out.println("Enter the name of the course you would like to delete: ");
						courseName = create.nextLine();
						admin.deleteCourse(all, courseName);
						System.out.println("You just deleted the course: " + courseName + ".");
						line();
						break;
	
					case "E":
						line();
						System.out.println("Enter the name of the course you would like to edit: ");
						String edit = create.nextLine();
						Course e = admin.findCourse(all, edit);
						System.out.println("Course Maximum Number of Students: ");
						courseMax = create.nextInt();
						create.nextLine();
						System.out.println("Course Instructor: ");
						courseInstructor = create.nextLine();
						System.out.println("Course Section: ");
						courseSection = create.nextLine();
						System.out.println("Course Location: ");
						courseLocation = create.nextLine();
						admin.editCourse(e, courseMax, courseInstructor,
								courseSection, courseLocation);
						System.out.println("Editing of " + edit + "done.");
						line();
						break;
	
					case "I":
						line();
						System.out.println("Enter the ID of the course you would like to see: ");
						courseID = create.nextLine();
						System.out.println("Here it is!");
						admin.viewCourses(admin.displayCourse(all, courseID));
						line();
						break;
						
					case "R":
						line();
						System.out.println("Enter the following information for this new student: ");
						System.out.println("Username: ");
						String sUsername = create.nextLine();
						System.out.println("Password: ");
						String sPassword = create.nextLine();
						System.out.println("First Name: ");
						firstName = create.nextLine();
						System.out.println("Last Name: ");
						lastName = create.nextLine();
						admin.addStudent(allStudents, sUsername, sPassword, firstName, lastName);
						System.out.println("You have just registered " + firstName + " " + lastName +
								" into the Course Registration System");
						line();
						break;
						
					case "W":
						line();
						admin.writeFullCourses(admin.fullCourses(all));
						System.out.println("Writing all full courses to a file...");
						line();
						break;
						
					case "A":
						line();
						System.out.println("Here are all the courses!");
						admin.viewCourses(all);
						line();
						break;
						
					case "F":
						line();
						System.out.println("Here are all the courses that are FULL: ");
						admin.viewCourses(admin.fullCourses(all));
						line();
						break;
						
					case "SN":
						line();
						System.out.println("Enter course name you would like to see the students for: ");
						courseName = create.nextLine();
						System.out.println("The students in " + courseName + " are...");
						System.out.println(admin.studentsInCourse(all, courseName));
					    line();
					    
						break;
					
					case "SC":
						line();
						System.out.println("Enter a student whose list of courses you would like to see."
								+ "\n" + "First Name: ");
						firstName = create.nextLine();
						System.out.println("Last Name: ");
						lastName = create.nextLine();
						System.out.println("The courses " + firstName + " " + lastName + " is taking are...");
						admin.viewCourses(admin.studentCourses(all, firstName, lastName));
						line();
						break;
					
					case "S":
						line();
						System.out.println("Let's sort the courses!");
						admin.sortCourses(all);
						
						line();
						break;
						
					case "Q":
						line();
						serializeCourses(all);
						serializeStudents(allStudents);
						System.out.println("Exiting.");
						line();
						return;
				}
			} while (!choice.equals("Q"));
		}
		
		
		
		Student student = findStudent(allStudents, username, password);
		//System.out.println(student.getFirstname() + " " + student.getLastname());
		if (allStudents.contains(student)) {
			//TODO
			System.out.println("Hello " + student.getFirstname() + " " + student.getLastname() + "\n" 
			+ "Please select one of the keys listed below ... " + "\n");
			//menu
			String choice;
			do {
				//Menu
				System.out.print(
						"------------------------------------------------------------------" + "\n" +
						"REPORTS" + "\n" +
						"'A' - View All Courses." + "\n" +
						"'N' - View All Courses that are Not full" + "\n" + 
						"'R' - Register for a course." + "\n" + 
						"'W' - Withdraw from a course." + "\n" + 
						"'V' - View all courses that you are registered in." + "\n" +
						"'Q' - Quit." + "\n" +
						"------------------------------------------------------------------");
				
				choice = input.next().toUpperCase();
				Scanner create = new Scanner(System.in);
				String courseName;
				String courseID;
				int courseMax;
				String courseInstructor;
				String courseSection;
				String courseLocation;
				String firstName;
				String lastName;
				
				switch (choice) {
					//view all courses
					case "A":
						line();
						System.out.println("Here are all the courses!");
						student.viewCourses(all);
						line();
						break;
					
					//view not full courses
					case "N":
						line();
						System.out.println("Here are all the courses that are not full: ");
						student.viewCourses(student.notFullCourses(all));
						line();
						break;
					
					//register for course
					case "R":
						line();
						//TODO
						System.out.println("What course would you like to register for? Please enter the"
								+ " course name, section, and your first and last name.");
						System.out.println("Course Name: ");
						courseName = create.nextLine();
						System.out.println("Course Section: ");
						courseSection = create.nextLine();
						System.out.println("First Name: ");
						firstName = create.nextLine();
						System.out.println("Last Name: ");
						lastName = create.nextLine();
						student.register(all, courseName, courseSection, firstName, lastName);
						line();
						break;
						
					//withdraw from course
					case "W":
						line();
						//TODO
						System.out.println("What course would you like to withdraw from? Please enter the"
								+ " course name, and your first and last name.");
						System.out.println("Course Name: ");
						courseName = create.nextLine();
						System.out.println("First Name: ");
						firstName = create.nextLine();
						System.out.println("Last Name: ");
						lastName = create.nextLine();
						student.withdraw(all, courseName, firstName, lastName);
						System.out.println("You are no longer enrolled in " + courseName);
						line();
						break;
					
					//view courses student is in 
					case "V":
						line();
						System.out.println("Here are all the courses you are registered in ...");
						ArrayList<Course> scourses = student.studentCourses(all, student.getFirstname(), student.getLastname());
						student.viewCourses(scourses);
						line();
						break;
						
					case "Q":
						line();
						serializeCourses(all);
						serializeStudents(allStudents);
						System.out.println("Exiting.");
						line();
						break;
				
				
				}
				
				
				
				}while(!choice.equals("Q"));
					
			
			}
		else {
			line();
			System.out.println("INCORRECT USERNAME OR PASSWORD.");
			line();
		}		
	
	}
	public static void line() {
		System.out.println("-----------------------------------------------------------------");
	}
	
	
	//My serialize methods are based off of the provided Serialization files!
	
	public static void serializeCourses(ArrayList<Course> allCourses) {
		String file = "Byte_Stream_of_Courses.csv";
		try {
			
			
			//FileOutput Stream writes data to a file
			FileOutputStream fos = new FileOutputStream(file);
			
			//ObjectOutputStream writes objects to a stream (A sequence of data)
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			//Writes the specific object to the OOS
			oos.writeObject(allCourses);
			
			
			//Close both streams
			oos.close();
			fos.close();
			System.out.println("Course Serialization complete");
		} 
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		
	}
	
	public static void serializeStudents(ArrayList<Student> allStudents) {
		String file = "Byte_Stream_of_Students.csv";
		try {
			
			
			//FileOutput Stream writes data to a file
			FileOutputStream fos = new FileOutputStream(file);
			
			//ObjectOutputStream writes objects to a stream (A sequence of data)
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			//Writes the specific object to the OOS
			oos.writeObject(allStudents);
			
			//Close both streams
			oos.close();
			fos.close();
			System.out.println("Serialization complete");
		} 
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		
	}
	
	//My deserialize methods are based from the deserialization files provided!
	
	public static ArrayList<Course> deserializeCourses() {
		String file = "Byte_Stream_of_Courses.csv";
		ArrayList<Course> allCourses;
		try{
			  //FileInputSystem recieves bytes from a file
		      FileInputStream fis = new FileInputStream(file);
		      
		      //ObjectInputStream does the deserialization-- it reconstructs the data into an object
		      ObjectInputStream ois = new ObjectInputStream(fis);
		      
		      //Cast as Employee. readObject will take the object from ObjectInputStream
		      allCourses = (ArrayList<Course>)ois.readObject();
		      
		      
		      ois.close();
		      fis.close();

			  System.out.println("Course Deserialization Complete.");
			  return allCourses;
		   }
		    catch(IOException ioe) {
		       ioe.printStackTrace();
		       //return;
		    }
		 	catch(ClassNotFoundException cnfe) {
		       cnfe.printStackTrace();
		       //return;
		    }
		return null;
			
    }
	
	public static ArrayList<Student> deserializeStudents() {
		String file = "Byte_Stream_of_Students.csv";
		ArrayList<Student> allStudents;
		try{
			  //FileInputSystem recieves bytes from a file
		      FileInputStream fis = new FileInputStream(file);
		      
		      //ObjectInputStream does the deserialization-- it reconstructs the data into an object
		      ObjectInputStream ois = new ObjectInputStream(fis);
		      
		      //Cast as Employee. readObject will take the object from ObjectInputStream
		      
		      allStudents = (ArrayList<Student>)ois.readObject();
		      
		      ois.close();
		      fis.close();
		      System.out.println("Student Deserialization Complete.");
		      return allStudents;
		     
		   }
		    catch(IOException ioe) {
		       ioe.printStackTrace();
		       //return;
		    }
		 	catch(ClassNotFoundException cnfe) {
		       cnfe.printStackTrace();
		       //return;
		    }
			return null;

    }
	
	
	
	
	
	public static Student findStudent(ArrayList<Student> allStudents, String userName, String passWord) {
		Student find = null;
		Student x;
		for (int i = 0; i < allStudents.size(); i ++) {
			x = allStudents.get(i);
			if (x.getUsername().equals(userName) &&
					x.getPassword().equals(passWord)) {
				find = allStudents.get(i);
			}
		}
		return find;
	}


	
}	

	
	
	

	
	

