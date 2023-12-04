import java.io.*;
import java.util.Vector;

class Main {


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
				if (skipLines++ == 0) continue;
				String[] cCard = line.split(","); // input file lines: cardNumber[0], expirationDate[1], cardHolderName[2]
				String cardNumber = cCard[0];
				String cardHolderName = cCard[2];

				// Validate cardNumber
				String cardType = "MasterCard";

				// Create new subclass credit card
				if (cardType == "MasterCard") cards.add(new MasterCard(cardNumber, cardHolderName, cardType));
				else if (cardType == "Visa") cards.add(new Visa(cardNumber, cardHolderName, cardType));
				else if (cardType == "AmericanExpress") cards.add(new AmericanExpress(cardNumber, cardHolderName, cardType));
				else if (cardType == "Discover") cards.add(new Discover(cardNumber, cardHolderName, cardType));				

				// Add credit card to output file
				bw.write(cardNumber + ", " + cCard[1] + ", " + cCard[2] + "\n");
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