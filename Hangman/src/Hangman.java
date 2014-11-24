import java.util.Scanner;

public class Hangman {

	public static void main(String[] args) {

		// Hold the answer String
		String answer;

		// Helper String
		String tmp;

		// character to hold the guessed letter
		char guess;

		// this array will hold the letters guessed and the dashes
		char[] letters;
		char[] obscured;

		// reserve array with guessed letters
		boolean[] guessed = new boolean[26];

		// boolean to indicate whether the puzzle is solved or not
		boolean solved = false;

		// counter for wrong tries
		int failCount = 0;

		// declare a scanner
		Scanner sc = new Scanner(System.in);

		// display a quick title
		System.out.println("Hangman");
		System.out.println();
		System.out.println("You have 10 guesses to guess a word");
		System.out.println();

		// Get the word to guess
		System.out.print("Please enter the word to guess: ");
		answer = sc.nextLine();

		// Copy the word to guess into the letters array
		letters = answer.toUpperCase().toCharArray();

		// create the obscured array with the same length as the letters array
		obscured = answer.toCharArray();

		// fill the obscured array with dashes '-'
		for (int i = 0; i < obscured.length; i++) {
			obscured[i] = '-';
		}

		do {

			// Print info
			System.out.println();
			System.out.println("You have " + (10 - failCount) + " tries left.");
			System.out.println();
			System.out
					.println("The word to guess: " + String.valueOf(obscured));
			System.out.println();

			// Get guess from user
			System.out.print("Please enter your guess: ");
			tmp = sc.nextLine();
			guess = tmp.toUpperCase().charAt(0);

			// create an array index to check if user already guessed that
			// letter
			int idx = guess - 'A';
			if (!guessed[idx]) {
				// User has not yet guessed that letter

				// set the letter at index as "guessed" to avoid repetitive
				// guessing of the same letter
				guessed[idx] = true;

				// Boolean variable indicating a correct (= true) or wrong (=
				// false) guess
				boolean found = false;
				// go through all the letters of the word
				for (int i = 0; i < letters.length; i++) {
					// check if the guessed letter it at position i in the word
					if (guess == letters[i]) {
						// uncover the guessed letter at the correct position
						obscured[i] = letters[i];
						// indicate that the guess was correct
						found = true;
					}
				}

				// increment the fail count if the guessed letter was not found
				// in the word
				if (!found) {
					failCount++;
				}

				// variable to indicate that all letters have been guessed
				solved = true;

				// go through all the obscured letters
				for (int i = 0; i < obscured.length; i++) {
					if (obscured[i] == '-') {
						// if there still is a '-' in the obscured array, it's
						// not yet solved
						solved = false;
					}
				}
			} else { // The letter has already been guessed, print message
				System.out.println("You have already guessed that letter.");
				System.out.println("Please try again.");
				System.out.println();
			}

		} while (!solved && (failCount < 10)); // repeat guessing until either
												// found or out of tries

		if (solved) { // Solution has been found, display gratulations
			System.out.println();
			System.out.println("Congratulations!");
			System.out.println("You correctly guessed the word: "
					+ answer.toUpperCase());
		} else { // Not solved. Display fail message.
			System.out.println();
			System.out.println("You failed.");
			System.out.println("The sought word was: " + answer.toUpperCase());
		}

		// Close the scanner to avoid resource leaks
		sc.close();
	}

}
