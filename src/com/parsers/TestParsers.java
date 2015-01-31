package com.parsers;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Collection;

import org.junit.Test;

public class TestParsers {

	@Test
	/*
	 * Unit test
	 */
	public void testUnit() {
		
		String sf1 = "./csvfiles/Students1.csv";
		String cf200 = "./csvfiles/Courses200.csv";
		String cf300 = "./csvfiles/Courses300.csv"; // test column order
		String cf400 = "./csvfiles/Courses400.csv"; // test invalid course handle, and another test for column order
		
		File file = new File(sf1);
		assertTrue(file.exists());
		System.out.println(sf1 + " exists");
		
		file = new File(cf200);
		assertTrue(file.exists());
		System.out.println(cf200 + " exists");
		
		file = new File(cf300);
		assertTrue(file.exists());
		System.out.println(cf300 + " exists");
		
		file = new File(cf400);
		assertTrue(file.exists());
		System.out.println(cf400 + " exists");
		
		String [] files = new String [] {sf1, cf200, cf300, cf400};
		CSVParser csvp = new CSVParser();
		csvp.doCSVParser(files, true);
		
		assertEquals(csvp.courses.size(), 9);
		assertEquals(csvp.students.size(), 3);
		
		assertTrue(csvp.students.get(Integer.valueOf(1002)).name.equalsIgnoreCase("rico suave")); // test update record
		Collection<Course> col = csvp.courses.values();
		for (Course c: col) {
			assertFalse(c.name.equalsIgnoreCase("Invalid course")); // test no invalid course was inserted
		}

		
		System.out.println("Test run complete");
	}

	//TODO: test column order
	public void testInvalidColumnOrder()
	{
		
	}
	//TODO: invalid course id 
	public void testInvalidCourseID()
	{
		
	}
}
