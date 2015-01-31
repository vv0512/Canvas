package com.parsers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

public class CSVParser {

	public HashMap<Integer,Course> courses = new HashMap<Integer,Course>();
	public HashMap<Integer,Student> students = new HashMap<Integer,Student>();
	
	private static String STUDENT_CSV = "student";
	private static String COURSE_CSV = "course";
	
	public static void main(String[] args) {
		CSVParser csvp = new CSVParser();
		if (args!=null && args.length > 0) 
			csvp.doCSVParser(args, false);
	}

	public void doCSVParser(String [] args, boolean debug)
	{
		for (String filename : args) {
			System.out.println("attempt to parse file: " + filename);
			
			File file = new File(filename);
			if (file.exists() && file.isFile()) 
			{
				if (filename.toLowerCase().indexOf(STUDENT_CSV) >= 0) {
					parseStudentCSVFile(filename, this.students, debug);
				}
				else if (filename.toLowerCase().indexOf(COURSE_CSV) >= 0) {
					parseCourseCSVFile(filename, this.courses, debug);
				}
				else {
					System.out.println(String.format("Error: Unsupported file type!"));
				}
			}
			else {
				System.out.println(String.format("Error: File doesn't exist!"));
			}
		}
		
		displayCourseStudentList(this.courses, this.students, debug);
	}
	
	public void displayCourseStudentList(HashMap<Integer,Course> coursesMap, HashMap<Integer,Student> studentsMap, boolean debug)
	{
		Collection<Course> courseslist = coursesMap.values();
		for (Course c : courseslist) {
			if (c.id != -1 && c.state == CanvasState.active)
			{
				String header = String.format("LIST OF STUDENTS ENROLLED IN %d: %s", c.id, c.name);
				System.out.println(header);
				int numberOfStudents = 0;
				Collection<Student> studentslist = studentsMap.values();
				for (Student s : studentslist) 
				{
					if (s.courseid != -1 && s.courseid == c.id && s.state == CanvasState.active) {
						numberOfStudents++;
						System.out.println(s.name);
					}
				}

				System.out.println(numberOfStudents + " student(s) enrolled\n==========");
			}
		}
	}
	
	public void parseCourseCSVFile(String csvFile, HashMap<Integer,Course> coursesMap, boolean debug)
	{
		if (debug) System.out.println("DEBUG: Call parseCourseCSVFile");
		
		BufferedReader br = null;
		String line = "";
		CourseHeader header = null;
	 
			try {
				br = new BufferedReader(new FileReader(csvFile));
				while ((line = br.readLine()) != null && line.contains(",")) 
				{
				    // use comma as separator
					String[] p = line.split(",");

					// parse the first line for column headers
					if (header == null)
						header = new CourseHeader(p);
					else {
						if (!header.set) {
							break;
						}
						else {
							Course course = new Course(p, header);
							if (course.verified) {
								// if the course exists, update the data
								if (coursesMap.containsKey(course.id))
									coursesMap.remove(course.id);
								coursesMap.put(course.id, course);
							}
							if (debug) 
							{
								System.out.println("DEBUG:");
								course.printAll();
							}
						}
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void parseStudentCSVFile(String csvFile, HashMap<Integer, Student> studentsMap, boolean debug)
	{
		if (debug) System.out.println("DEBUG: Call parseStudentCSVFile: " + csvFile);
		
		BufferedReader br = null;
		String line = "";
		UserHeader header = null;
	 
			try {
				br = new BufferedReader(new FileReader(csvFile));
				while ((line = br.readLine()) != null && line.contains(",")) 
				{
			        // use comma as separator
					String[] p = line.split(",");				
					if (header == null)
						header = new UserHeader(p);
					else {
						if (!header.set) {
							break;
						}
						else {
							Student student = new Student(p, header);
							if (student.verified) {
								// if the student exists, update the data
								if (studentsMap.containsKey(student.id))
									studentsMap.remove(student.id);
								studentsMap.put(student.id, student);
							}
							if (debug) 
							{
								System.out.println("DEBUG:");
								student.printAll();
							}
						}
					}
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	private static String COURSE_ID = "course_id"; 
	private static String COURSE_NAME = "course_name"; 
	private static String COURSE_STATE = "course_state"; 
	
	/*
	 * Class to assign positions based on order of fixed column names of course file
	 */
	class CourseHeader {
		public int id_pos = 0;
		public int name_pos = 1;
		public int state_pos = 2;
		public boolean set = false;
		
		public CourseHeader(String [] headers)
		{
			if (headers.length != 3)
				return;
			int index = 0;
			for (String column : headers)
			{
				if (column.equalsIgnoreCase(COURSE_ID))
					id_pos = index;
				else if (column.equalsIgnoreCase(COURSE_NAME))
					name_pos = index;
				else if (column.equalsIgnoreCase(COURSE_STATE))
					state_pos = index;
				index++;
			}
			set = true;
		}
	}
	
	private static String USER_ID = "user_id"; 
	private static String USER_NAME = "user_name"; 
	private static String USER_STATE = "user_state"; 
	
	/*
	 * Class to assign positions based on order of fixed column names of student file
	 */
	class UserHeader {
		public int id_pos = 0;
		public int name_pos = 1;
		public int courseid_pos = 2;
		public int state_pos = 3;
		public boolean set = false;
		
		public UserHeader(String [] headers)
		{
			if (headers.length != 4)
				return;
			int index = 0;
			for (String column : headers)
			{
				if (column.equalsIgnoreCase(USER_ID))
					id_pos = index;
				else if (column.equalsIgnoreCase(USER_NAME))
					name_pos = index;
				else if (column.equalsIgnoreCase(USER_STATE))
					state_pos = index;
				else if (column.equalsIgnoreCase(COURSE_ID))
					courseid_pos = index;
				index++;
			}
			set = true;
		}
	}
}