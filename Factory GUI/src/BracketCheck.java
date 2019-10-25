import java.util.ArrayList;

public class BracketCheck 
{
	public static void main(String [] args)
	{
		//create a char arraylist to read in each char of a string
		//use token to count each symbol, if even, evaluate, if odd, will not
		int curlyCount = 0;
		int curvedCount = 0;
		int angledCount = 0;
		int squareCount = 0;
		
		//Strings to evaluate
		String eval = "{(<<eeeek>>){}{…}(e(e)e){hello}}";
		String noEval = "{(< eeeek>>){}{…} e(e)e){hello}";
		
		ArrayList <Character> charCheck = new ArrayList<Character>();
		
		//For each char in string, send to char array for parsing into list
		for(char c : eval.toCharArray())
		{
			charCheck.add(c);
		}
		
		//count off sets
		for(char c : charCheck)
		{
			if((int) c == 123 || (int) c == 125)
			{
				curlyCount++;
			}
			
			if((int) c == 40 || (int) c == 41)
			{
				curvedCount++;
			}
			
			if((int) c == 60 || (int) c == 62)
			{
				angledCount++;
			}
			
			if((int) c == 91 || (int) c == 93)
			{
				squareCount++;
			}
		}
		
		//modulo to pair
		if(curlyCount % 2 == 0 && curvedCount % 2 == 0 && angledCount % 2 == 0 && squareCount % 2 == 0)
		{
			System.out.println("Will evaluate successfully");
		}
		else
		{
			System.out.println("Will not evaluate successfully");
		}
	}
}
