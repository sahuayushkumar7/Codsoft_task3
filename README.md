# Student Management System 📚

A Java-based console application that allows users to *add, edit, remove, search, and display* student records.  
Data is stored persistently in a file using *Java Serialization*, so student records remain even after restarting the program.

---

## Features
- *Add Student* — Add a new student with name, roll number, and grade.
- *Edit Student* — Update name and/or grade of an existing student.
- *Remove Student* — Delete a student record by roll number.
- *Search Student* — Find a student by roll number.
- *Display All Students* — Show all stored student records.
- *Persistent Storage* — Student data is saved to a file (students.dat) using serialization.

---

## File Handling
- Uses ObjectOutputStream and ObjectInputStream to *save* and *load* data.
- Data is stored in the students.dat file in the program's directory.

---

## Example Usage

===== Student Management System =====

1. Add Student


2. Edit Student


3. Remove Student


4. Search Student


5. Display All Students


6. Exit Choose an option: 1 Enter name: John Doe Enter roll number: 101 Enter grade: A Student added successfully!



---

## Requirements
- Java Development Kit (JDK) 8 or higher.

---

## How to Run
1. Save the file as Main.java (and keep Student and StudentManagementSystem classes inside it, or in separate files if preferred).
2. Open a terminal/command prompt in the folder containing the files.
3. Compile the program:
   ```bash
   javac Main.java

4. Run the program:

java Main




---

Notes

The students.dat file will be created automatically in the program directory.

Ensure that the program has write permissions in the directory to save student data.



---

Author

Developed by Ayush as a practice project for learning Java OOP, serialization, and file handling.
