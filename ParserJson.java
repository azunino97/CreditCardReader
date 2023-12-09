import java.io.FileReader;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ParserJson extends Parser {
    public ParserJson (String inputPathJson, String outputPathJson) { super(inputPathJson, outputPathJson); }
    
    public Vector<CreditCard> getCreditCardsJson() {
        Vector<CreditCard> cards = new Vector<CreditCard>();
		try {
			JSONParser parser = new JSONParser();
			JSONArray jAry = (JSONArray) parser.parse(new FileReader(super.inputPath));
			for (Object line : jAry) {
				JSONObject creditCard = (JSONObject) line;
				String cardNumber = (String) creditCard.get("cardNumber");
				String cardHolderName = (String) creditCard.get("cardHolderName");
				String cardType = validateCard(cardNumber);
				cards.add(createCard(cardNumber, cardHolderName, cardType));
			}
		} catch (ParseException err) {
			err.printStackTrace();
		} catch (Exception err) {
			err.printStackTrace();
		} 
		return cards;
	}
}