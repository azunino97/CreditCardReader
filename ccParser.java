import java.io.*;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

class Main {
	// AmericanExpress (1st: 3, 2nd: 4, 7, len: 15)
	// Visa (1st: 4, len: 13, 16)
	// MasterCard (1st: 5, 2nd: 1-5, len: 16)
	// Discover (1st: 6011, len: 16)
	public static String validateCard(String cardNum) {
		String cardType = "Invalid: not a possible card number";
		String cardNumActual = cardNum.trim();
		int cardLen = cardNumActual.length();
		if (cardLen != 0 && cardNumActual != null) {
			if (cardNumActual.matches("[0-9]+")) {
				if (cardLen <= 19) {
					if (cardLen >= 10) {
						int index = 0;
						char firstChar = cardNumActual.charAt(index);
						char secondChar = cardNumActual.charAt(index + 1);
						if (firstChar == '3') { // American Express
							if (secondChar == '4' || secondChar == '7')
								cardType = "AmericanExpress";
						} else if (firstChar == '4') { // Visa
							cardType = "Visa";
						} else if (firstChar == '5') { // MasterCard
							int hold = Character.getNumericValue(secondChar);
							if (hold >= 1 && hold <= 5)
								cardType = "MasterCard";
						} else if (cardNumActual.substring(index, index + 4).equals("6011")) { // Discover
							cardType = "Discover";
						}
					} else
						cardType = "Invalid: less than 10 digits";
				} else
					cardType = "Invalid: more than 19 digits";
			} else
				cardType = "Invalid: non numeric characters";
		} else
			cardType = "Invalid: empty/null card number";
		return cardType;
	}

	public static Vector<CreditCard> getCreditCardsXml(String inputPathXml) {
		Vector<CreditCard> cards = new Vector<CreditCard>();
		try {
			File inputXmlFile = new File(inputPathXml);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputXmlFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("CARD");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					String cardNumber = eElement.getElementsByTagName("CARD_NUMBER").item(0).getTextContent();
					String cardHolderName = eElement.getElementsByTagName("CARD_HOLDER_NAME").item(0).getTextContent();
					String cardType = validateCard(cardNumber);
					cards.add(createCard(cardNumber, cardHolderName, cardType));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cards;
	}

	public static Vector<CreditCard> getCreditCardsCsv(String inputCsvPath) {
		Vector<CreditCard> cards = new Vector<CreditCard>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(inputCsvPath));
			// BufferedWriter bw = new BufferedWriter(new FileWriter(outputCsvPath));
			// bw.write("cardNumber, cardType\n");
			
			// read csv
			String line;
			int skipLines = 0;
			while ((line = br.readLine()) != null) {
				if (skipLines++ == 0)
				continue;
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cards;
	}

	public static CreditCard createCard(String cardNumber, String cardHolderName, String cardType) {
		// Create new subclass credit card
		if (cardType == "MasterCard") return new MasterCard(cardNumber, cardHolderName, cardType);
		else if (cardType == "Visa") return new Visa(cardNumber, cardHolderName, cardType);
		else if (cardType == "AmericanExpress") return new AmericanExpress(cardNumber, cardHolderName, cardType);
		else if (cardType == "Discover") return new Discover(cardNumber, cardHolderName, cardType);
		return new CreditCard(cardNumber, cardHolderName, cardType);
	}

	public static void main(String[] args) {
		String inputPathCsv = "/Users/q/Documents/GitHub/CreditCardReader/inputOutput/input_file.csv";
		// String outputNameCsv = "/Users/q/Documents/GitHub/CreditCardReader/inputOutput/output.csv";
		String inputPathXml = "/Users/q/Documents/GitHub/CreditCardReader/inputOutput/input_file.xml";
		// String outputXmlPath = "/Users/q/Documents/GitHub/CreditCardReader/inputOutput/output.xml";

		Vector<CreditCard> cardsCsv;
		Vector<CreditCard> cardsXml;
		// Depending on input file name, create a vector of validated creditcards depending on file type
		cardsCsv = getCreditCardsCsv(inputPathCsv);
		cardsXml = getCreditCardsXml(inputPathXml);

		for (CreditCard card : cardsCsv)
			card.printCard();
		System.err.println();
		for (CreditCard card : cardsXml)
			card.printCard();
	}
}