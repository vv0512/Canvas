package com.parsers;

import com.parsers.CSVParser.CourseHeader;

public class Course extends CanvasDataType {
	
	public Course(String [] p, CourseHeader header)
	{
		if (p.length == 3)
			addCourse(p[header.id_pos], p[header.name_pos], p[header.state_pos]);
	}
	
	public void addCourse(String id, String name, String state) {
		int ID = verifyId(id);
		CanvasState CS = verifyState(state);
		if (ID != -1 && CS.compareTo(CanvasState.unknown)!=0 && !name.isEmpty()){
			this.id = ID;
			this.name = name;
			this.state = CS;
			this.verified = true;
		}
	}
	
	public void printAll()
	{
		System.out.println(id);
		System.out.println(name);
		System.out.println(state.name());
	}
}
