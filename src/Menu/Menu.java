package Menu;

import DatabaseManager.DatabaseManager;
import Services.*;
import modules.Author;

import javax.xml.crypto.Data;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.util.Date;

public class Menu {
     DatabaseManager db;
     AdminService adminService;
     Audit audit;
     AuthorService authorService;
     ContestService contestService;
     SubmissionService submissionService;
     TaskService taskService;
     UserService userService;
     Scanner scanner;
     Date date;

     public Menu(DatabaseManager db) {
         this.db = db;
         this.adminService = new AdminService();
         this.audit = Audit.getInstance();
         this.authorService = new AuthorService();
         this.contestService = new ContestService();
         this.submissionService = new SubmissionService();
         this.taskService = new TaskService();
         this.userService = new UserService();
         this.scanner = new Scanner(System.in);
         this.date = new Date();
     }

     public void start() {
         System.out.println("Choose an action: ADD, LIST, UPDATE, DELETE, QUIT.");
         while (true) {
             System.out.println("Alegeti actiunea: ");
             String action = scanner.nextLine();
             switch (action) {
                 case "ADD":
                     add_menu();
                 case "LIST":
                     list_menu();
                 case "UPDATE":
                     update_menu();
                 case "DELETE":
                     delete_menu();
                 case "QUIT":
                     break;
             }
         }
     }

    private void add_menu() {
        System.out.println("Choose a category: User, Task, Submission, Contest, Author, Admin.");
        String cat = scanner.nextLine();
        switch (cat) {
            case "User":
                add_user();
            case "Task":
                add_task();
            case "Submission":
                add_sub();
            case "Contest":
                add_contest();
            case "Author":
                add_author();
            case "Admin":
                add_admin();
        }
    }

    private void list_menu() {
         System.out.println("Do you want to list a user by id or the entire list of users? (id / -1)");
         int ans = scanner.nextInt();

        System.out.println("Choose a category: User, Task, Submission, Contest, Author, Admin.");
        String cat = scanner.nextLine();
        switch (cat) {
            case "User":
                if (ans == - 1) {
                    System.out.println(userService.findAll(db));
                } else {
                    System.out.println(userService.findById(ans, db));
                }
            case "Task":
                if (ans == - 1) {
                    System.out.println(taskService.findAll(db));
                } else {
                    System.out.println(taskService.findById(ans, db));
                }
            case "Submission":
                if (ans == - 1) {
                    System.out.println(submissionService.findAll(db));
                } else {
                    System.out.println(submissionService.findById(ans, db));
                }
            case "Contest":
                if (ans == - 1) {
                    System.out.println(contestService.findAll(db));
                } else {
                    System.out.println(contestService.findById(ans, db));
                }
            case "Author":
                if (ans == - 1) {
                    System.out.println(authorService.findAll(db));
                } else {
                    System.out.println(authorService.findById(ans, db));
                }
            case "Admin":
                if (ans == - 1) {
                    System.out.println(adminService.findAll(db));
                } else {
                    System.out.println(adminService.findById(ans, db));
                }
        }
    }

    private void delete_menu() {
        System.out.println("Choose a category: User, Task, Submission, Contest, Author, Admin.");
        String cat = scanner.nextLine();
        System.out.println("Enter the id: ");
        int id = scanner.nextInt();
        switch (cat) {
            case "User":
                userService.delete(id, db);
            case "Task":
                taskService.delete(id, db);
            case "Submission":
                submissionService.delete(id, db);
            case "Contest":
                contestService.delete(id, db);
            case "Author":
                authorService.delete(id, db);
            case "Admin":
                adminService.delete(id, db);
        }
    }

    private void update_menu() {
        System.out.println("Choose a category: User, Task, Submission, Contest, Author, Admin.");
        String cat = scanner.nextLine();
        switch (cat) {
            case "User":
                update_user();
            case "Task":
                update_task();
            case "Submission":
                update_sub();
            case "Contest":
                update_contest();
            case "Author":
                update_author();
            case "Admin":
                update_admin();
        }
    }

    private void add_user() {
         String username = scanner.nextLine();
         String pass = scanner.nextLine();
         String email = scanner.nextLine();
         double rating = scanner.nextDouble();
         Timestamp tm = new Timestamp(date.getTime());
         userService.create(0, username, pass, email, rating, tm, db);
    }
    private void update_user() {
         int id = scanner.nextInt();
        String username = scanner.nextLine();
        String pass = scanner.nextLine();
        String email = scanner.nextLine();
        double rating = scanner.nextDouble();
        Timestamp tm = new Timestamp(date.getTime());
        userService.update(id, username, pass, email, rating, tm, db);
    }
    private void add_task() {
         String name = scanner.nextLine();
         String description = scanner.nextLine();
         String author = scanner.nextLine();
         String contest = scanner.nextLine();
         int difficulty = scanner.nextInt();
         Timestamp createdAt = new Timestamp(date.getTime());
         taskService.create(0, name, description, author, contest, difficulty, createdAt, db);
    }
    void update_task() {
         int id = scanner.nextInt();
        String name = scanner.nextLine();
        String description = scanner.nextLine();
        String author = scanner.nextLine();
        String contest = scanner.nextLine();
        int difficulty = scanner.nextInt();
        Timestamp createdAt = new Timestamp(date.getTime());
        taskService.update(id, name, description, author, contest, difficulty, createdAt, db);
    }
    private void add_sub() {
        int userId = scanner.nextInt();
        int problemId = scanner.nextInt();
        Timestamp submissionTime = new Timestamp(date.getTime());
        String verdict = scanner.nextLine();
        submissionService.create(0, userId, problemId, submissionTime, verdict, db);
    }
    private void update_sub() {
         int id = scanner.nextInt();
        int userId = scanner.nextInt();
        int problemId = scanner.nextInt();
        Timestamp submissionTime = new Timestamp(date.getTime());
        String verdict = scanner.nextLine();
        submissionService.update(id, userId, problemId, submissionTime, verdict, db);
    }
    private void add_contest() {
         String name = scanner.nextLine();
         Timestamp startTime = new Timestamp(date.getTime());
         double duration = scanner.nextDouble();
         contestService.create(0, name, startTime, duration, db);
    }
    private void update_contest() {
         int id = scanner.nextInt();
        String name = scanner.nextLine();
        Timestamp startTime = new Timestamp(date.getTime());
        double duration = scanner.nextDouble();
        contestService.update(id, name, startTime, duration, db);
    }
    private void add_author() {
         String firstName = scanner.nextLine();
         String lastName = scanner.nextLine();
         String institution = scanner.nextLine();
         authorService.create(0, firstName, lastName, institution, db);
    }
    private void update_author() {
         int id = scanner.nextInt();
        String firstName = scanner.nextLine();
        String lastName = scanner.nextLine();
        String institution = scanner.nextLine();
        authorService.update(id, firstName, lastName, institution, db);
    }
    private void add_admin() {
        String username = scanner.nextLine();
        String pass = scanner.nextLine();
        String email = scanner.nextLine();
        double rating = scanner.nextDouble();
        Timestamp tm = new Timestamp(date.getTime());
        String role = scanner.nextLine();
        adminService.create(0, username, pass, email, rating, tm, role, db);
    }
    private void update_admin() {
         int id = scanner.nextInt();
         String username = scanner.nextLine();
        String pass = scanner.nextLine();
        String email = scanner.nextLine();
        double rating = scanner.nextDouble();
        Timestamp tm = new Timestamp(date.getTime());
        String role = scanner.nextLine();
        adminService.update(id, username, pass, email, rating, tm, role, db);
    }
}
