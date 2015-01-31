#CanvasExercise
This is a simplified version of a piece of functionality we have in Canvas. Schools use many different Student Information Systems, some commercial, some homegrown, some are even just an excel spreadsheet. We give various options for synchronizing this data with Canvas, one option is the school can send us CSV files each time things change, and we'll process the CSV files to make the appropriate changes in Canvas.

In this problem we're only going to consider two data types: Students and Courses, so a Student can only be enrolled in one course.

For all data types, state is either 'active' or 'deleted'. The user_id and course_id are globally unique. As a result, if the id does not exist, create a new record, otherwise, update the existing record. 


course columns:     course_id, course_name, state
student columns:    user_id,   user_name, course_id, state
Here’s an example: 

course_id, course_name, state
2, "Operating Systems", active

user_id, user_name, course_id, state
4, "Jon Doe", 2, active

Write a program that will process a CSV file of courses as well as a CSV file of students. Feel free to use a CSV parsing library. Remember, you are free to use whatever technology stack you'd like, but please pick something you know; this isn't a good time to experiment with new technology! :) Also, your project does not need to be a web application, but feel free to make one.

You will need to determine the type of data in the CSV file based on the headers in the first row. Your program will output a list of active courses, and for each course, a list of active students enrolled in that course.