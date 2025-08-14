import java.io.*;
import java.util.*;

class Student implements Serializable {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    // Getters & Setters
    public String getName() { return name; }
    public int getRollNumber() { return rollNumber; }
    public String getGrade() { return grade; }

    public void setName(String name) { this.name = name; }
    public void setGrade(String grade) { this.grade = grade; }

    @Override
    public String toString() {
        return "Roll No: " + rollNumber + ", Name: " + name + ", Grade: " + grade;
    }
}

class StudentManagementSystem {
    private List<Student> students;
    private final String fileName = "students.dat";

    public StudentManagementSystem() {
        students = new ArrayList<>();
        loadFromFile();
    }

    public void addStudent(Student student) {
        students.add(student);
        saveToFile();
    }

    public boolean removeStudent(int rollNumber) {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student s = iterator.next();
            if (s.getRollNumber() == rollNumber) {
                iterator.remove();
                saveToFile();
                return true;
            }
        }
        return false;
    }

    public Student searchStudent(int rollNumber) {
        for (Student s : students) {
            if (s.getRollNumber() == rollNumber) {
                return s;
            }
        }
        return null;
    }

    public List<Student> getAllStudents() {
        return students;
    }

    // File Handling
    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        File file = new File(fileName);
        if (!file.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            students = (List<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Remove Student");
            System.out.println("4. Search Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid choice. Please enter a number.");
                sc.next();
                continue;
            }

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter name: ");
                    String name = sc.nextLine().trim();
                    System.out.print("Enter roll number: ");
                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid roll number.");
                        sc.next();
                        continue;
                    }
                    int roll = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter grade: ");
                    String grade = sc.nextLine().trim();

                    if (name.isEmpty() || grade.isEmpty()) {
                        System.out.println("Fields cannot be empty!");
                        continue;
                    }

                    if (sms.searchStudent(roll) != null) {
                        System.out.println("Student with this roll number already exists!");
                        continue;
                    }

                    sms.addStudent(new Student(name, roll, grade));
                    System.out.println("Student added successfully!");
                }
                case 2 -> {
                    System.out.print("Enter roll number to edit: ");
                    int rollEdit = sc.nextInt();
                    sc.nextLine();
                    Student studentToEdit = sms.searchStudent(rollEdit);
                    if (studentToEdit == null) {
                        System.out.println("Student not found!");
                        continue;
                    }
                    System.out.print("Enter new name (leave blank to keep same): ");
                    String newName = sc.nextLine().trim();
                    System.out.print("Enter new grade (leave blank to keep same): ");
                    String newGrade = sc.nextLine().trim();

                    if (!newName.isEmpty()) studentToEdit.setName(newName);
                    if (!newGrade.isEmpty()) studentToEdit.setGrade(newGrade);

                    System.out.println("Student details updated!");
                }
                case 3 -> {
                    System.out.print("Enter roll number to remove: ");
                    int rollRemove = sc.nextInt();
                    if (sms.removeStudent(rollRemove)) {
                        System.out.println("Student removed successfully!");
                    } else {
                        System.out.println("Student not found!");
                    }
                }
                case 4 -> {
                    System.out.print("Enter roll number to search: ");
                    int rollSearch = sc.nextInt();
                    Student found = sms.searchStudent(rollSearch);
                    if (found != null) {
                        System.out.println(found);
                    } else {
                        System.out.println("Student not found!");
                    }
                }
                case 5 -> {
                    List<Student> all = sms.getAllStudents();
                    if (all.isEmpty()) {
                        System.out.println("No students to display.");
                    } else {
                        for (Student s : all) {
                            System.out.println(s);
                        }
                    }
                }
                case 6 -> {
                    System.out.println("Exiting... Goodbye!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }
}