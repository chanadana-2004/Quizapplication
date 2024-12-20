package Quiz;
import java.util.Scanner;
import java.util.Random;
public class Quizapp {
	private Scanner sc=new Scanner(System.in);
	private int count=0;
	private boolean isFiftyFiftyUsed=false;
	private boolean isAudiencePollUsed=false;
	private String playername;
	private String location;
	private int phno;
	private int age;
	private String level;

	public void setplayerName(String playername)
	{
		this.playername=playername;
	}
	public String getplayerName() {
		return playername;
	}
	public void setlocation(String location)
	{
		this.location=location;
	}
	public String getlocation() {
		return location;
	}
	
	public void setphno(int phno)
	{
		this.phno=phno;
	}
	public int getphno() {
		return phno;
	}
	public void setAge(int age) 
	{
		this.age=age;
	}
	public int getAge(){
	     return age;
	}
	public void setlevel(String level)
	{
		this.level=level;
	}
	public String getlevel() {
		return level;
	}
	  public void details() {
			Scanner sc=new Scanner(System.in);
	    Quizapp q= new Quizapp();
	    System.out.println("Enter playername: ");
		q.setplayerName(sc.next());
		System.out.println("Enter location: ");
		q.setlocation(sc.next());
		System.out.println("Enter phno: ");
		q.setphno(sc.nextInt());
		System.out.println("Enter age: ");
		q.setAge(sc.nextInt());
		System.out.println("Enter level: ");
		q.setlevel(sc.next());
	}

	   
	 // Method to display the rules of the game
	    public void rules() {
	    System.out.println("Welcome to the Quiz Game! Your goal is to answer all the questions correctly and score the highest points.");
	    System.out.println("1. Each question has four options: A, B, C, and D. Choose the correct answer by typing the corresponding letter.");
	    System.out.println("2. For every correct answer, you will earn 10 points. There are no points for incorrect answers.");
	    System.out.println("3. You have two lifelines to assist you during the game:");
	    System.out.println("   - The 50-50 Lifeline removes two incorrect options, leaving one correct and one incorrect option.");
	    System.out.println("   - The Audience Poll Lifeline shows audience voting percentages for each option to help you decide.");
	    System.out.println("4. Each lifeline can only be used once during the game.");
	    System.out.println("5. If you answer incorrectly, the game ends immediately, and your final score will be displayed.");
	    System.out.println("6. Try your best and enjoy the game! Good luck!");

	    }
	    
	    
	    
	    

	    // Lifeline implementation
	    public void useFiftyFifty(String[] options, String correctAnswer) {
	        if (isFiftyFiftyUsed) {
	            System.out.println("You have already used the 50-50 lifeline.");
	            return;
	        }

	        Random random = new Random();
	        int correctIndex = -1;
	        for (int i = 0; i < options.length; i++) {
	            if (options[i].equalsIgnoreCase(correctAnswer)) {
	                correctIndex = i;
	                break;
	            }
	        }

	        boolean[] toShow = new boolean[options.length];
	        toShow[correctIndex] = true;
	        int removed = 0;

	        while (removed < 2) {
	            int randomIndex = random.nextInt(options.length);
	            if (randomIndex != correctIndex && !toShow[randomIndex]) {
	                toShow[randomIndex] = true;
	                removed++;
	            }
	        }

	        System.out.println("50-50 Lifeline Used. Remaining options:");
	        for (int i = 0; i < options.length; i++) {
	            if (toShow[i]) {
	                System.out.println("Option " + (char) ('A' + i) + ": " + options[i]);
	            }
	        }

	        isFiftyFiftyUsed = true;
	    }

	    public void useAudiencePoll(String[] options, String correctAnswer) {
	        if (isAudiencePollUsed) {
	            System.out.println("You have already used the Audience Poll lifeline.");
	            return;
	        }

	        Random random = new Random();
	        int correctIndex = -1;
	        for (int i = 0; i < options.length; i++) {
	            if (options[i].equalsIgnoreCase(correctAnswer)) {
	                correctIndex = i;
	                break;
	            }
	        }

	        int[] poll = new int[options.length];
	        int remaining = 100;
	        for (int i = 0; i < poll.length; i++) {
	            if (i == correctIndex) {
	                poll[i] = 50 + random.nextInt(21); // 50-70% for the correct answer
	            } else {
	                poll[i] = random.nextInt(remaining / 2);
	            }
	            remaining -= poll[i];
	        }

	        poll[correctIndex] += remaining; // Ensure percentages add to 100

	        System.out.println("Audience Poll Results:");
	        for (int i = 0; i < options.length; i++) {
	            System.out.println("Option " + (char) ('A' + i) + ": " + poll[i] + "%");
	        }

	        isAudiencePollUsed = true;
	    }

	    // Ask a Question with Lifelines
	    public void askQuestion(String question, String[] options, String correctAnswer) {
	        System.out.println(question);
	        for (int i = 0; i < options.length; i++) {
	            System.out.println("Option " + (char) ('A' + i) + ": " + options[i]);
	        }

	        System.out.println("Enter your answer or type '50-50' or 'Poll' to use a lifeline:");
	        String answer = sc.nextLine().trim();

	        if (answer.equalsIgnoreCase("50-50")) {
	            useFiftyFifty(options, correctAnswer);
	            askQuestion(question, options, correctAnswer); // Re-ask the question
	        } else if (answer.equalsIgnoreCase("Poll")) {
	            useAudiencePoll(options, correctAnswer);
	            askQuestion(question, options, correctAnswer); // Re-ask the question
	        } else if (answer.equalsIgnoreCase(correctAnswer)) {
	            System.out.println("Correct!");
	            count += 10;
	            System.out.println("You have scored " + count + " points.");
	        } else {
	            System.out.println("Incorrect! The correct answer was: " + correctAnswer);
	            System.out.println("Game over! You are leaving with " + count + " points.");
	            System.exit(0);
	        }
	    }

	    // Main Game Loop
	    public void playGame() {
	    	askQuestion("what are the popular brands for footwear?", new String[]{"Nike", "Adidas", "Puma", "Reebok"}, "Nike");
	    	askQuestion("which are the phones mostly used by cricketers?", new String[]{"Apple", "Nokia", "Motorola", "Samsung"}, "Motorola");
	    	askQuestion("what are the products occured in pizza huts?", new String[]{"mianoize", "burger", "pizza", "wings"}, "burger");
	    	askQuestion("Which brand is known for the slogan 'Think Different'?", new String[]{"Apple", "Microsoft", "IBM", "Google"}, "Apple");
	    	askQuestion("what is the flavor of maaza?", new String[]{"sweet", "bitter", "spicy", "mango"}, "mango");
	    	askQuestion("what is the service of spotify?", new String[]{"Spotify AB", "Apple", "Google", "Amazon"}, "Spotify AB");
	    	askQuestion("Which luxury brand is famous for its red-bottomed shoes?", new String[]{"Christian Louboutin", "Gucci", "Prada", "Louis Vuitton"}, "Christian Louboutin");
	    	askQuestion("which brand provides the most popular snacks?", new String[]{"McDonald's", "Burger King", "Wendy's", "KFC"}, "McDonald's");
	    	askQuestion("most probably used site?", new String[]{"Google", "Apple", "Microsoft", "Amazon"}, "Google");
	    	askQuestion("what are the vehicles used to fast transport?", new String[]{"Rapido", "Uber", "cabs", "taxi"}, "Rapido");
	    }
	    }}
}
	

