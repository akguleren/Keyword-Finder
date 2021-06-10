import java.util.Scanner;

public class keywordFinder2 {
//Eren Akgül 150119028. Purpose of this program is to create a random table by a given size and search a given keyword and reverse of it in that table.
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the number of rows: ");		//Getting inputs from the user.
		int rows = input.nextInt();
		if (rows <= 0) {
			System.out.println("Rows must be bigger than 0.");
			System.exit(0);
		}
		System.out.print("Enter the number of columns: ");
		int columns = input.nextInt();
		if (columns <= 0) {		
			System.out.println("Columns must be bigger than 0.");	//Checking the validity of inputs. 
			System.exit(0);
		}
		char[][] list = new char[rows][columns];	//Creating the array that will hold the table.
		System.out.println();

		for (int i = 0; i < list.length; i++) {
			for (int j = 0; j < columns; j++) {
				int k = (int) (Math.random() * 2);		//Getting a random number to choose either print a number or a letter.
				if (k == 0)
					list[i][j] = (char) (Math.random() * 26 + 65); //Assigning the values in the array.
				else
					list[i][j] = (char) (Math.random() * 10 + 48);
				System.out.print(list[i][j] + " ");			//Printing the array on the screen.
			}
			System.out.println();
		}
		System.out.println();
		System.out.print("Enter the keyword: ");
		String keyword = input.next();					//Getting the keyword.
		for (int i = 0; i < keyword.length(); i++) {
			char ch = keyword.toUpperCase().charAt(i);		//Check for validity of the keyword.
			if (ch < 48 && ch > 57 && ch < 65 && ch > 90) {
				System.out.println("Keyword should only contain numbers and letters.");
				System.exit(0);
			}
		}
		String reverseKeyword = "";
		for (int i = keyword.length() - 1; i >= 0; i--) {
			reverseKeyword += keyword.charAt(i);		//We are getting the reverse version of keyword to check the keyword in reverse order.
		}

		if (horizontalOrder(keyword, reverseKeyword, list, columns)			//We are checking every possibility in
				|| verticalOrder(keyword, reverseKeyword, list, columns)	//I connect them with "or"s because if one of them is true condition will be true.
				|| crossOrderLeft(keyword, reverseKeyword, list, columns, rows)
				|| crossOrderRight(keyword, reverseKeyword, list, columns, rows)) {
			System.out.println("TRUE");
			System.exit(0);
		} else {
			System.out.println("FALSE");
			System.exit(0);
		}
		input.close();
	}

	public static boolean horizontalOrder(String keyword, String reverseKeyword, char[][] list, int columns) {
		char[] check = new char[columns];	//Creating new array to hold each row.
		int m = 0;							//This value holds keyword's index number.
		int trueCounter = 0;				//This value holds how many correct matches that the array have with keyword.

		for (int i = 0; i < list.length; i++) {
			for (int j = 0; j < columns; j++) {
				check[j] = list[i][j];		//Filling the rows one by one.
			}
			for (int k = 0; k < check.length; k++) {
				if (check[k] == keyword.toUpperCase().charAt(m) || check[k] == reverseKeyword.toUpperCase().charAt(m)) {
					m++;   trueCounter++;		//If keyword or reverse of keyword matches with array we increment the values.
					if (trueCounter == keyword.length()) {	//If the number of correct matches equal to length of keyword then condition is true.
						return true;
					}
				} 
				else {
					trueCounter = 0; 	//We reset the values if condition is false.
					m = 0;
				}
			}
		}
		return false;
	}
	public static boolean verticalOrder(String keyword, String reverseKeyword, char[][] list, int columns) {
		char[] check = new char[columns];	//Creating new array to hold each column.
		int m = 0;							//This value holds keyword's index number.
		int trueCounter = 0;				//This value holds how many correct matches that the array have with keyword.

		for (int i = 0; i < list.length; i++) {
			for (int j = 0; j < columns; j++) {
				check[j] = list[j][i];		//Filling columns one by one.
			}
			for (int k = 0; k < check.length; k++) {
				if (check[k] == keyword.toUpperCase().charAt(m) || check[k] == reverseKeyword.toUpperCase().charAt(m)) {
					m++;    trueCounter++;   	//If keyword or reverse of keyword matches with array we increment the values
					if (trueCounter == keyword.length()) { //If the number of correct matches equal to length of keyword then condition is true.
						return true;
					}
				} else {
					trueCounter = 0;  //We reset the values if condition is false.
					m = 0;
				}
			}
		}
		return false;
	}
	//This method checks if the keyword is in cross order from left to right.It also checks the reverse of keyword.
	public static boolean crossOrderLeft(String keyword, String reverseKeyword, char[][] list, int columns, int rows) {
		int m = 0;
		int trueCounter = 0;
		int n = 0;
		int temp = 0;

		for (int i = 0; i < list.length; i++) {
			n = temp;	//The index of rows increasing every time so wee need to hold a value for it. 
			char[] checkUp = new char[columns];		//This array holds the upper half of table.
			char[] checkDown = new char[columns];	//This array hold the bottom half of table
			for (int j = 0; j < columns && n < rows; j++) {
				checkUp[j] = list[n][j];		//Assigning the values in to the array.
				n++;
			}
			n = 0;
			temp++;  //Increasing the temp value for the next iteration.
			for (int j = i + 1; j < columns && n < rows; j++) {
				checkDown[j] = list[n][j];	//Assigning the values in to the array.
				n++;
			}
			for (int k = 0; k < checkUp.length; k++) {
				if (checkUp[k] == keyword.toUpperCase().charAt(m) || checkDown[k] == keyword.toUpperCase().charAt(m)
						|| checkUp[k] == reverseKeyword.toUpperCase().charAt(m)
						|| checkDown[k] == reverseKeyword.toUpperCase().charAt(m)) { //We check the match condition.
					m++;
					trueCounter++;
					if (trueCounter == keyword.length()) {
						return true;
					}
				} else {
					trueCounter = 0;
					m = 0;
				}
			}
		}
		return false;
	}
	//This method checks if the keyword is in cross order from right to left.It also checks the reverse of keyword.
	public static boolean crossOrderRight(String keyword, String reverseKeyword, char[][] list, int columns, int rows) {
		int m = 0;
		int trueCounter = 0;
		int n = 0;
		int temp = columns - 1;

		for (int i = 0; i < list.length; i++) {
			n = temp;						//Index of columns decreasing every time so we need to hold a value for it.
			char[] checkDown = new char[columns];	//This array checks the bottom half of the table.
			char[] checkUp = new char[columns];		//This array checks the upper half of the table.
			for (int j = 0; j < columns && n >= 0; j++) {
				checkDown[j] = list[j][n];		//Assigning the values to the array.
				n--;
			}
			n = columns - 1;
			temp--;			//Decreasing the temp value for the next iteration.
			int l = 0;		//This values are also for index numbers but it holds the index numbers for upper half of table.
			int g = i + 1;	
			for (int j = 1; j < columns && n >= 0 && l < columns && g < rows; j++) {
				checkUp[l] = list[g][n];	//Assigning the values in the array.
				n--;      l++;		g++;	//Incrementing the l and g and decrementing n.
			}
			for (int k = 0; k < checkDown.length; k++) {
				if (checkDown[k] == keyword.toUpperCase().charAt(m) || checkUp[k] == keyword.toUpperCase().charAt(m)
					|| checkDown[k] == reverseKeyword.toUpperCase().charAt(m) || checkUp[k] == reverseKeyword.toUpperCase().charAt(m)) {	 
					m++;					//We check the condition and incrementing values depending on this condition.
					trueCounter++;
					if (trueCounter == keyword.length()) {
						return true;
					}
				} else {
					trueCounter = 0;
					m = 0;
				}
			}
		}
		return false;
	}
}