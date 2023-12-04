import java.io.*;
import java.util.Vector;

class Main {
	// AmericanExpress (1st: 3, 2nd: 4, 7, len: 15)
	// Visa (1st: 4, len: 13, 16)
	// MasterCard (1st: 5, 2nd: 1-5, len: 16)
	// Discover (1st: 6011, len: 16)
	public static String validateCard(String cardNum) {
		String cardType = "Invalid: not a possible card number";
		String cardNumActual = cardNum.trim();
		int cardLen = cardNumActual.length();
		if (cardLen != 0 && cardNumActual != null) {
			if (cardNumActual.matches("[0-9]+")) {
				if (cardLen <= 19) {
					if (cardLen >= 10) {
						int index = 0;
						char firstChar = cardNumActual.charAt(index);
						char secondChar = cardNumActual.charAt(index + 1);
						if (firstChar == '3') { // American Express
							if (secondChar == '4' || secondChar == '7')
							cardType = "AmericanExpress";
						} else if (firstChar == '4') { // Visa
							cardType = "Visa";
						} else if (firstChar == '5') { // MasterCard
							int hold = Character.getNumericValue(secondChar);
							if (hold >= 1 && hold <= 5)
								cardType = "MasterCard";
						} else if (cardNumActual.substring(index, index + 4).equals("6011")) { // Discover
							cardType = "Discover";
						}
					} else cardType = "Invalid: less than 10 digits";
				} else cardType = "Invalid: more than 19 digits";
			} else cardType = "Invalid: non numeric characters";
		} else cardType = "Invalid: empty/null card number";
		return cardType;
	}

	public static void main(String[] args) {
		String inputName = "/Users/q/Documents/GitHub/CreditCardReader/inputOutput/input_file.csv";
		String outputName = "/Users/q/Documents/GitHub/CreditCardReader/inputOutput/output.csv";
		try {
			BufferedReader br = new BufferedReader(new FileReader(inputName));
			BufferedWriter bw = new BufferedWriter(new FileWriter(outputName));

			bw.write("cardNumber, cardType\n");

			String line;
			int skipLines = 0;
			Vector<CreditCard> cards = new Vector<CreditCard>();
			while ((line = br.readLine()) != null) {
				if (skipLines++ == 0)
					continue;
				String[] cCard = line.split(","); // input file lines: cardNumber[0], expirationDate[1],
													// cardHolderName[2]
				String cardNumber = cCard[0];
				String cardHolderName = cCard[2];

				// Validate cardNumber
				String cardType = validateCard(cardNumber);

				// Create new subclass credit card
				if (cardType == "MasterCard")
					cards.add(new MasterCard(cardNumber, cardHolderName, cardType));
				else if (cardType == "Visa")
					cards.add(new Visa(cardNumber, cardHolderName, cardType));
				else if (cardType == "AmericanExpress")
					cards.add(new AmericanExpress(cardNumber, cardHolderName, cardType));
				else if (cardType == "Discover")
					cards.add(new Discover(cardNumber, cardHolderName, cardType));
				// else

				// Add credit card to output file
				bw.write(cardNumber + "," + cardType + "\n");
			}

			for (CreditCard card : cards) {
				card.printCard();
			}

			br.close();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
}