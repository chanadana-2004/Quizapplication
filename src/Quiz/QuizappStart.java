package Quiz;
import java.util.Scanner;
public class QuizappStart {
	public static void main(String[] args)
	{
		Quizapp q=new Quizapp();
		q.setplayerName("a");
		q.setlocation("b");
		q.setphno(1);
		q.setAge(2);
		q.setlevel("c");
		Scanner sc=new Scanner(System.in);
		System.out.println("playerName:"+q.getplayerName());
		String playername=sc.next();
		System.out.println("location:"+q.getlocation());
		String location=sc.next();
		System.out.println("phno:"+q.getphno());
		int phno=sc.nextInt();
		System.out.println("Age:"+q.getAge());
		int Age=sc.nextInt();
		System.out.println("level:"+q.getlevel());
		String level=sc.next();
	}

	}


