import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Heads_Or_Tails {

	public static void main(String[] args) throws IOException
	{
		Scanner in=new Scanner(new BufferedInputStream(System.in));

		// trial - the only Input
		String[] trial = new String[2];										

		for(int i=0; i<2; i++)
		{
			// Reading two lines of input and assigning it to a single 'trial' array.
			trial[i] = in.nextLine();									
		}
		in.close();

		// N - Number of tosses
		int N = Integer.parseInt(trial[0]);

		// characters 	- Sequence of the toss
		String characters = trial[1];										
		if(Heads_Or_Tails.Validation(N, characters) == 1)
		{
			// Calling the methods for each Exercise
			Heads_Or_Tails.Exercise1(characters);
			Heads_Or_Tails.Exercise2(characters);
			Heads_Or_Tails.Exercise3(characters);
		}
	}

	// Validating the Input conditions
	public static int Validation(int n, String c)									
	{
		if(n >= 1 && n <= 1000000) 
		{
			if(c.length() == n)
			{
				for(int i=0; i<c.length(); i++)
				{
					if(!(c.charAt(i) != 'H' && c.charAt(i) != 'T')) {
						continue;
					}
					else { System.out.println("The sequence should only be either H or T."); return 0; }
				}
				return 1;
			}
			else 
			{ 
				System.out.println("The length of the trail sequence should be equal to the number of trails.");
				return 0;
			}
		}
		else 
		{
			System.out.println("The number of trails should be in the range 1 to 1000000.");
			return 0;
		}
	}

	// Method for performing Exercise 1
	public static void Exercise1(String characters)										
	{			
		// Count the number of times each sequence appeared. The array is indexed by the length of a sequence. 
		int[] counts = new int[characters.length()];
		int currentHeads = 0;
		int consecutiveHeads=0;
		
		for (char value : characters.toCharArray()) {
			if (value == 'H') {
				currentHeads++;
			} 
			else if (currentHeads == 2){ 
				counts[currentHeads]++;
				currentHeads = 0;
			}
			else {
				currentHeads = 0;
			}
		}

		// If the loop ends with 'H', increment the count
		if (currentHeads == 2) { 
			counts[currentHeads]++; 
		}

		// From the computed counts, find the one that appears the most often.
		for(int i=1;i<characters.length();i++)
		{
			if(counts[i] > 0)
			{
				consecutiveHeads = counts[i];
			}
		}
		System.out.println(consecutiveHeads);
	}

	// Method for performing Exercise 2
	public static void Exercise2(String characters) 								
	{
		int longestHeads = 0;
		int currentHeads = 0;
		int longestTails = 0;
		int currentTails = 0;

		for (char value : characters.toCharArray()){

			/* If the character is 'H', Heads count is incremented by 1 and is checked whether there is a larger Heads count. 
			 If there is no larger Heads count, the current Heads count value is taken as the largest. Similarly with Tails 'T'. */
			
			if (value == 'H') {
				currentHeads++;
				currentTails=0;
				if (currentHeads > longestHeads){
					longestHeads = currentHeads;
				}
			}
			else if(value =='T'){
				currentTails++;
				currentHeads=0;
				if(currentTails > longestTails){
					longestTails = currentTails;
				}
			}
		}
		System.out.println(longestHeads+" "+longestTails);
	}

	// Method for performing Exercise 3
	public static void Exercise3(String characters) {								

		int[] counts = new int[characters.length()];
		int currentHeads = 0;
		int tails = 0;
		for (char value : characters.toCharArray()) {
			if (value == 'H') {
				currentHeads++;
			} 
			else if (currentHeads != 0){
				counts[currentHeads-1]++;
				currentHeads = 0;
			}
			else {
				tails ++;
			}
		}

		if (currentHeads != 0) {
			counts[currentHeads-1]++;
		}

		int mostFrequentLength = 0;
		int mostFrequentValue = 0;
		for (int i = 0; i < counts.length; i++) {
			if (tails != characters.length() && counts[i] >= mostFrequentValue) {
				mostFrequentValue = counts[i];
				mostFrequentLength = i+1;
			}
		}
		System.out.println(mostFrequentLength);
	}
}


