import java.util.Vector;

// superclass
public class CreditCardParser {
    private String inputFileName;
    private String outputFileName;
    private String fileType;
    private Vector<CreditCard> cards;

    public CreditCardParser(String inputFile, String outputFile) { 
        inputFileName = inputFile;
        outputFileName = outputFile;
        fileType = getFileType();
        if (!fileType.equals(".csv") && !fileType.equals(".xml") && !fileType.equals(".json")) {
            throw new IllegalArgumentException("Input file type is not one of: .csv, .xml, or .json!");
        }
    }

    public Vector<CreditCard> getCards() {
        return cards;
    }

    public void printAllCards() {
        for (CreditCard card : cards)
            card.printCard();
    }

    public void updateCards() {
        if (fileType.equals(".csv")) {
            ParserCsv parser = new ParserCsv(inputFileName, outputFileName);
            cards = parser.getCreditCardsCsv();
        } else if (fileType.equals(".xml")) {
            ParserXml parser = new ParserXml(inputFileName, outputFileName);
            cards = parser.getCreditCardsXml();
        } else if (fileType.equals(".json")) {
            ParserJson parser = new ParserJson(inputFileName,outputFileName);
            cards = parser.getCreditCardsJson();
        }
    }

    public void writeOutputFile() {
        if (fileType.equals(".csv")) {
            ParserCsv parser = new ParserCsv(inputFileName, outputFileName);
            parser.writeCreditCardsCsv(cards);
        } else if (fileType.equals(".xml")) {
            ParserXml parser = new ParserXml(inputFileName, outputFileName);
            cards = parser.getCreditCardsXml();
        } else if (fileType.equals(".json")) {
            ParserJson parser = new ParserJson(inputFileName,outputFileName);
            cards = parser.getCreditCardsJson();
        }
    }

    // e.g.	String inputPathCsv = "/Users/q/Documents/GitHub/CreditCardReader/inputOutput/input_file.csv";
    private String getFileType() {
        if (inputFileName == null || inputFileName == "") return "";
        return (inputFileName.substring(inputFileName.lastIndexOf('.'))).trim();
    }
}