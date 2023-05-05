import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class examination {

    // user database
    static Map<String, String> users = new HashMap<>();
    static {
        users.put("user1", "password1");
        users.put("user2", "password2");
    }

    // question database
    private static ArrayList<Question> questions = new ArrayList<>();
    static {
        questions.add(new Question("Which of the following is a programming language?", 
        "Java", "HTML", "CSS", "XML", "Java"));
        questions.add(new Question("What is the capital of Australia?", 
        "Sydney", "Melbourne", "Canberra", "Brisbane", "Canberra"));
        questions.add(new Question("Who wrote the novel 'To Kill a Mockingbird'?", 
        "Harper Lee", "J.K. Rowling", "Stephen King", "John Grisham", "Harper Lee"));
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String username, password;
        do {
            System.out.print("Enter username: ");
            username = input.nextLine();
            System.out.print("Enter password: ");
            password = input.nextLine();
        } while (!isValidUser(username, password));
        
        User user = new User(username);
        System.out.println("Welcome, " + user.getUsername());

        System.out.println("1. Update profile");
        System.out.println("2. Update password");
        System.out.println("3. Start exam");
        System.out.println("3.Exit");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                user.updateProfile();
                break;
            case 2:
                user.updatePassword();
                break;
            case 3:
                Exam exam = new Exam(questions);
                exam.start();
                System.out.println("Your score: " + exam.submit());
                break;

            case 4:
            
                System.out.println("Thank you for taking the exam!");
                user.logout();
                break;
            default:
                System.out.println("Invalid choice");
        }

    }

    private static boolean isValidUser(String username, String password) {
        if (users.containsKey(username) && users.get(username).equals(password)) {
            return true;
        }
        System.out.println("Invalid username or password");
        return false;
    }
}

class User {
    private String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void updateProfile() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter new name: ");
        String name = input.nextLine();
        System.out.print("Enter new email: ");
        String email = input.nextLine();
        System.out.println("Profile updated successfully");
    }

    public void updatePassword() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter current password: ");
        String currentPassword = input.nextLine();
        if (examination.users.get(username).equals(currentPassword)) {
            System.out.print("Enter new password: ");
            String newPassword = input.nextLine();
            System.out.print("Confirm new password: ");
            String confirmPassword = input.nextLine();
            if (newPassword.equals(confirmPassword)) {
                examination.users.put(username, newPassword);
                System.out.println("Password updated successfully");
            } else {
                System.out.println("Passwords do not match");
            }
        } else {
            System.out.println("Incorrect password");
        }
    }

    public void logout() {

        System.out.println("Logout successful");
    }
}
class Exam {
    private ArrayList<Question> questions;

        private int score;
        private int currentQuestion;
        private int timeLimit = 10; // in minutes
        private long startTime;

        public Exam(ArrayList<Question> questions) {
            this.questions = questions;
        }

    public void start() {
        Scanner input = new Scanner(System.in);
        System.out.println("You have " + timeLimit + " minutes to complete the exam");
        System.out.println("Press any key to start");
        input.nextLine();
        startTime = System.currentTimeMillis();
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println((i + 1) + ". " + question.getQuestionText());
            System.out.println("A. " + question.getOptionA());
            System.out.println("B. " + question.getOptionB());
            System.out.println("C. " + question.getOptionC());
            System.out.println("D. " + question.getOptionD());
            System.out.print("Your answer (A/B/C/D): ");
            String answer = input.nextLine();
            if (answer.equals(question.getCorrectAnswer())) {
                score++;
            }
        }

    }
    public int submit() {
        long endTime = System.currentTimeMillis();
        int elapsedMinutes = (int) ((endTime - startTime) / (1000 * 60));
        if (elapsedMinutes >= timeLimit) {
            System.out.println("Time's up!");
        } else {
            System.out.println("Exam completed");
        }
        return score;
    }
}
class Question {
    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;
    public Question(String questionText, String optionA, String optionB, String optionC, String optionD, String correctAnswer) {
        this.questionText = questionText;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
    }
    
    public String getQuestionText() {
        return questionText;
    }
    
    public String getOptionA() {
        return optionA;
    }
    
    public String getOptionB() {
        return optionB;
    }
    
    public String getOptionC() {
        return optionC;
    }
    
    public String getOptionD() {
        return optionD;
    }
    
    public String getCorrectAnswer() {
        return correctAnswer;
    }
}       
