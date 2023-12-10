import java.util.Vector;

// superclass
public class CreditCardParser {
    private String inputFileName;
    private String outputFileName;
    private String fileType;
    public Parser parser; // allow calls to access Vector<CreditCard>

    public CreditCardParser(String inputFile, String outputFile) { 
        inputFileName = inputFile;
        outputFileName = outputFile;
        fileType = getFileType();
        if (!fileType.equals(".csv") && !fileType.equals(".xml") && !fileType.equals(".json")) {
            throw new IllegalArgumentException("Input file type is not one of: .csv, .xml, or .json!");
        }
        if (fileType.equals(".csv")) parser = new ParserCsv(inputFileName, outputFileName);
        else if (fileType.equals(".xml")) parser = new ParserXml(inputFileName, outputFileName);
        else if (fileType.equals(".json")) parser = new ParserJson(inputFileName,outputFileName);
    }

    public void readInputFile() {
        parser.readCreditCards();
    }

    public void writeOutputFile() {
        parser.writeCreditCards();
    }

    // e.g.	String inputPathCsv = "/Users/q/Documents/GitHub/CreditCardReader/inputOutput/input_file.csv";
    private String getFileType() {
        if (inputFileName == null || inputFileName == "") return "";
        return (inputFileName.substring(inputFileName.lastIndexOf('.'))).trim();
    }
}