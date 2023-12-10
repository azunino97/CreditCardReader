import java.util.Vector;

class Main {
	public static void main(String[] args) {
		String inputPathCsv = "/Users/q/Documents/GitHub/CreditCardReader/inputOutput/input_file.csv";
		String inputPathXml = "/Users/q/Documents/GitHub/CreditCardReader/inputOutput/input_file.xml";
		String inputPathJson = "/Users/q/Documents/GitHub/CreditCardReader/inputOutput/input_file.json";

		String outputPathCsv = "/Users/q/Documents/GitHub/CreditCardReader/inputOutput/output_file.csv";
		String outputPathXml = "/Users/q/Documents/GitHub/CreditCardReader/inputOutput/output_file.xml";
		String outputPathJson = "/Users/q/Documents/GitHub/CreditCardReader/inputOutput/output_file.json";

		Vector<InputOutputPaths> paths = new Vector<InputOutputPaths>();
		paths.add(new InputOutputPaths(inputPathCsv, outputPathCsv));
		paths.add(new InputOutputPaths(inputPathXml, outputPathXml));
		paths.add(new InputOutputPaths(inputPathJson, outputPathJson));

		for (InputOutputPaths path : paths) {
			// create credit card parser to determine file type
			CreditCardParser cardParser = new CreditCardParser(path.inputPath, path.outputPath);
			
			// update card information from input file and validate cards
			cardParser.readInputFile();
			cardParser.parser.printAllCards();

			// write to output file with card info
			cardParser.writeOutputFile();
		}
	}
}