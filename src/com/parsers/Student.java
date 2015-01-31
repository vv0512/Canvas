package com.parsers;

import com.parsers.CSVParser.UserHeader;

public class Student extends CanvasDataType {
	public int courseid = -1;

	public Student(String [] p, UserHeader header)
	{
		if (p.length == 4)
			addStudent(p[header.id_pos], p[header.name_pos], p[header.courseid_pos], p[header.state_pos]);
	}
	
	public void addStudent(String id, String name, String courseId, String state) {
		int ID = verifyId(id);
		int COURSEID = verifyId(courseId);
		CanvasState CS = verifyState(state);
		if (ID != -1 && COURSEID != -1 && CS.compareTo(CanvasState.unknown)!=0 && !name.isEmpty()){
			this.id = ID;
			this.name = name;
			this.courseid = COURSEID;
			this.state = CS;
			this.verified = true;
		}
	}
	
	public void printAll()
	{
		System.out.println(id);
		System.out.println(name);
		System.out.println(courseid);
		System.out.println(state.name());
	}
}
