import java.util.Vector;

class Main {
	public static void main(String[] args) {
		String inputPathName = "/Users/q/Documents/GitHub/CreditCardReader/inputOutput/input_file.csv";
		// String inputPathName = "/Users/q/Documents/GitHub/CreditCardReader/inputOutput/input_file.xml";
		// String inputPathName = "/Users/q/Documents/GitHub/CreditCardReader/inputOutput/input_file.json";

		// String outputPathCsv = "/Users/q/Documents/GitHub/CreditCardReader/inputOutput/output.csv";
		// String outputPathXml = "/Users/q/Documents/GitHub/CreditCardReader/inputOutput/output.xml";
		// String outputPathJson = "/Users/q/Documents/GitHub/CreditCardReader/inputOutput/output.json";

		// create credit card parser to determine file type
		CreditCardParser cardParser = new CreditCardParser(inputPathName);
		cardParser.printAllCards();
		
		// get validated card information from input file
		Vector<CreditCard> cardsCsv = cardParser.getCards();

		// Depending on input file name, create a vector of validated creditcards depending on file type
		// cardsCsv = getCreditCardsCsv(inputPathCsv);
		// cardsXml = getCreditCardsXml(inputPathXml);
		// cardsJson = getCreditCardsJson(inputPathJson);

	}
}