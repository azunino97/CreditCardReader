import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Vector;

public class ParserCsv extends Parser {
    public ParserCsv (String inputPathCsv, String outputPathCsv) { super(inputPathCsv, outputPathCsv); }
    
    public void readCreditCards() {
        Vector<CreditCard> tempCards = new Vector<CreditCard>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(super.inputPath));            
            String line;
            int skipLines = 0;
            while ((line = br.readLine()) != null) { // read csv
                if (skipLines++ == 0) continue;
                String[] cCard = line.split(","); // input file lines: cardNumber[0], expirationDate[1], cardHolderName[2]
                String cardNumber = cCard[0];
                String cardHolderName = cCard[2];
                String cardType = validateCard(cardNumber);
                tempCards.add(createCard(cardNumber, cardHolderName, cardType));
            }	
            br.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
        cards = tempCards;
    }

    public void writeCreditCards() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(super.outputPath));
            bw.write("cardNumber, cardType\n");            
            for (CreditCard card : cards) {
                // Add credit card to output file
                bw.write(card.getNumber() + "," + card.getType() + "\n");
            }
            bw.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}
