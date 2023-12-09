// import java.util.Vector;

class Main {
	public static void main(String[] args) {
		String inputPathName = "/Users/q/Documents/GitHub/CreditCardReader/inputOutput/input_file.csv";
		// String inputPathName = "/Users/q/Documents/GitHub/CreditCardReader/inputOutput/input_file.xml";
		// String inputPathName = "/Users/q/Documents/GitHub/CreditCardReader/inputOutput/input_file.json";

		String outputPathCsv = "/Users/q/Documents/GitHub/CreditCardReader/inputOutput/output_file.csv";
		// String outputPathXml = "/Users/q/Documents/GitHub/CreditCardReader/inputOutput/output_file.xml";
		// String outputPathJson = "/Users/q/Documents/GitHub/CreditCardReader/inputOutput/output_file.json";

		// create credit card parser to determine file type
		CreditCardParser cardParser = new CreditCardParser(inputPathName, outputPathCsv);
		
		// update card information from input file and validate cards
		cardParser.updateCards();
		cardParser.printAllCards();

		// write to output file with card info
		cardParser.writeOutputFile();
	}
}