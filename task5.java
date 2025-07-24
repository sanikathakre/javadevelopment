import java.util.*;

class Student {
    private String username;
    private String password;
    private Map<Integer, String> answers = new HashMap<>();

    Student(String user, String pass) {
        this.username = user;
        this.password = pass;
    }

    boolean login(String user, String pass) {
        return username.equals(user) && password.equals(pass);
    }

    void updateProfile(String newUser, String newPass) {
        username = newUser;
        password = newPass;
        System.out.println("✅ Profile updated successfully.");
    }

    void submitAnswer(int qNo, String answer) {
        answers.put(qNo, answer);
    }

    Map<Integer, String> getSubmittedAnswers() {
        return answers;
    }

    String getUsername() {
        return username;
    }
}

class Question {
    String questionText;
    List<String> options;
    String correctAnswer;

    Question(String questionText, List<String> options, String correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    void display(int qNo) {
        System.out.println("Q" + qNo + ": " + questionText);
        char optionChar = 'A';
        for (String option : options) {
            System.out.println("  " + optionChar + ". " + option);
            optionChar++;
        }
    }
}

public class OnlineExamSystem {

    static List<Question> loadQuestions() {
        List<Question> list = new ArrayList<>();
        list.add(new Question("Which language runs in a web browser?",
                Arrays.asList("Java", "Python", "C++", "JavaScript"), "D"));
        list.add(new Question("What is the full form of CPU?",
                Arrays.asList("Control Panel Unit", "Central Processing Unit", "Computer Power Unit", "Central Processor Utility"), "B"));
        list.add(new Question("Which company developed Java?",
                Arrays.asList("Microsoft", "Apple", "Sun Microsystems", "Google"), "C"));
        return list;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Student user = new Student("student01", "pass123");

        System.out.println("📘 Welcome to e-Exam Portal");

        // Login
        System.out.print("👤 Username: ");
        String uname = sc.nextLine();
        System.out.print("🔐 Password: ");
        String pass = sc.nextLine();

        if (!user.login(uname, pass)) {
            System.out.println("❌ Invalid credentials. Exiting...");
            return;
        }

        int choice;
        boolean sessionActive = true;

        while (sessionActive) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Take Exam");
            System.out.println("2. Update Profile");
            System.out.println("3. View Submitted Answers");
            System.out.println("4. Logout");
            System.out.print("Select option: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    List<Question> questions = loadQuestions();
                    int duration = 60; // seconds
                    System.out.println("\n🕒 Exam started. You have " + duration + " seconds.");

                    long startTime = System.currentTimeMillis();

                    for (int i = 0; i < questions.size(); i++) {
                        long elapsed = (System.currentTimeMillis() - startTime) / 1000;
                        if (elapsed >= duration) {
                            System.out.println("⏰ Time's up! Auto-submitting your answers.");
                            break;
                        }

                        questions.get(i).display(i + 1);
                        System.out.print("Your Answer (A/B/C/D): ");
                        String ans = sc.nextLine().toUpperCase();
                        user.submitAnswer(i + 1, ans);
                    }

                    System.out.println("✅ Exam submitted successfully!");
                    break;

                case 2:
                    System.out.print("Enter new username: ");
                    String newUser = sc.nextLine();
                    System.out.print("Enter new password: ");
                    String newPass = sc.nextLine();
                    user.updateProfile(newUser, newPass);
                    break;

                case 3:
                    System.out.println("📄 Your Submitted Answers:");
                    Map<Integer, String> submissions = user.getSubmittedAnswers();
                    if (submissions.isEmpty()) {
                        System.out.println("No answers submitted yet.");
                    } else {
                        for (Map.Entry<Integer, String> entry : submissions.entrySet()) {
                            System.out.println("Q" + entry.getKey() + ": " + entry.getValue());
                        }
                    }
                    break;

                case 4:
                    System.out.println("👋 Logging out. Goodbye " + user.getUsername() + "!");
                    sessionActive = false;
                    break;

                default:
                    System.out.println("⚠ Invalid selection. Try again.");
            }
        }

        sc.close();
    }
}