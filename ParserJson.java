import java.io.FileReader;
import java.io.FileWriter;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ParserJson extends Parser {
    public ParserJson (String inputPathJson, String outputPathJson) { super(inputPathJson, outputPathJson); }
    
    public void readCreditCards() {
        Vector<CreditCard> tempCards = new Vector<CreditCard>();
		try {
			JSONParser parser = new JSONParser();
			JSONArray jAry = (JSONArray) parser.parse(new FileReader(super.inputPath));
			for (Object line : jAry) {
				JSONObject creditCard = (JSONObject) line;
				String cardNumber = (String) creditCard.get("cardNumber");
				String cardHolderName = (String) creditCard.get("cardHolderName");
				String cardType = validateCard(cardNumber);
				tempCards.add(createCard(cardNumber, cardHolderName, cardType));
			}
		} catch (ParseException err) {
			err.printStackTrace();
		} catch (Exception err) {
			err.printStackTrace();
		} 
		cards = tempCards;
	}

    @SuppressWarnings("unchecked")
    public void writeCreditCards() {
        try {
            JSONArray cardList = new JSONArray();
            for (CreditCard card : cards) {
                // new card
                JSONObject cardInfo = new JSONObject();
                cardInfo.put(" cardNumber", card.getNumber());
                cardInfo.put(" cardType", card.getType());
                        
                // add card to list
                cardList.add(cardInfo);
            }
            // write JSON file
            FileWriter file = new FileWriter(outputPath);
            file.write(cardList.toJSONString()); 
            file.flush();
            file.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}