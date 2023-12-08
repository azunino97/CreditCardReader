import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;

public class ParserCsv extends Parser {
    public ParserCsv (String inputPathCsv) { super(inputPathCsv); }
    
    public Vector<CreditCard> getCreditCardsCsv() {
        Vector<CreditCard> cards = new Vector<CreditCard>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(super.inputPath));
            // BufferedWriter bw = new BufferedWriter(new FileWriter(outputCsvPath));
            // bw.write("cardNumber, cardType\n");
            
            // read csv
            String line;
            int skipLines = 0;
            while ((line = br.readLine()) != null) {
                if (skipLines++ == 0) continue;
                String[] cCard = line.split(","); // input file lines: cardNumber[0], expirationDate[1], cardHolderName[2]
                String cardNumber = cCard[0];
                String cardHolderName = cCard[2];
                String cardType = validateCard(cardNumber);
                cards.add(createCard(cardNumber, cardHolderName, cardType));
                // Add credit card to output file
                // bw.write(cardNumber + "," + cardType + "\n");
            }	
            br.close();
            // bw.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
        return cards;
    }
}
