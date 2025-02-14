import java.util.*;

class User {
    private String userId;
    private String password;
    private String profile;

    public User(String userId, String password, String profile) {
        this.userId = userId;
        this.password = password;
        this.profile = profile;
    }

    public boolean authenticate(String userId, String password) {
        return this.userId.equals(userId) && this.password.equals(password);
    }

    public void updateProfile(String newProfile) {
        this.profile = newProfile;
        System.out.println("Profile updated successfully.");
    }

    public void updatePassword(String oldPassword, String newPassword) {
        if (this.password.equals(oldPassword)) {
            this.password = newPassword;
            System.out.println("Password updated successfully.");
        } else {
            System.out.println("Incorrect old password!");
        }
    }
}

class Exam {
    private Map<String, String> questions;
    private Map<String, String> userAnswers;
    private Timer timer;
    
    public Exam() {
        questions = new LinkedHashMap<>();
        userAnswers = new HashMap<>();
        
        questions.put("What is Java?", "A");
        questions.put("Which keyword is used to define a class in Java?", "B");
        questions.put("What is the default value of an int variable?", "C");
    }

    public void startExam() {
        System.out.println("Exam started. Answer the following questions:");
        Scanner scanner = new Scanner(System.in);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Time is up! Auto-submitting your exam.");
                submitExam();
            }
        }, 60000); // 60 seconds timer

        for (Map.Entry<String, String> entry : questions.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println("A) Object-Oriented B) Keyword C) Null D) Static");
            System.out.print("Your answer: ");
            String answer = scanner.next();
            userAnswers.put(entry.getKey(), answer);
        }
        
        submitExam();
    }

    public void submitExam() {
        timer.cancel();
        System.out.println("Exam submitted successfully!");
    }
}

public class OnlineExaminationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = new User("user123", "pass123", "Student");

        System.out.print("Enter User ID: ");
        String userId = scanner.next();
        System.out.print("Enter Password: ");
        String password = scanner.next();

        if (user.authenticate(userId, password)) {
            System.out.println("Login successful!");
            Exam exam = new Exam();
            exam.startExam();
        } else {
            System.out.println("Invalid credentials!");
        }
    }
}
