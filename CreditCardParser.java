import java.util.Vector;

// superclass
public class CreditCardParser {
    private String inputFileName;
    private String fileType;
    private Vector<CreditCard> cards;

    public CreditCardParser(String fileName) { 
        inputFileName = fileName;
        parseInputFile();
        if (!fileType.equals(".csv") && !fileType.equals(".xml") && !fileType.equals(".json")) {
            throw new IllegalArgumentException("Input file type is not one of: .csv, .xml, or .json!");
        } else {
            updateCards();
        }
    }

    public Vector<CreditCard> getCards() {
        return cards;
    }

    public void printAllCards() {
        for (CreditCard card : cards)
            card.printCard();
    }

    private void updateCards() {
        if (fileType.equals(".csv")) {
            ParserCsv parser = new ParserCsv(inputFileName);
            cards = parser.getCreditCardsCsv();
        } else if (fileType.equals(".xml")) {
            ParserXml parser = new ParserXml(inputFileName);
            cards = parser.getCreditCardsXml();
        } else if (fileType.equals(".json")) {
            ParserJson parser = new ParserJson(inputFileName);
            cards = parser.getCreditCardsJson();
        }
    }

    // e.g.	String inputPathCsv = "/Users/q/Documents/GitHub/CreditCardReader/inputOutput/input_file.csv";
    private void parseInputFile() {
        if (inputFileName == null || inputFileName == "") return;
        fileType = (inputFileName.substring(inputFileName.lastIndexOf('.'))).trim();
    }
}