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

	public static void main(String[] args) {
		// String inputNameCsv =
		// "/Users/q/Documents/GitHub/CreditCardReader/inputOutput/input_file.csv";
		// String outputNameCsv =
		// "/Users/q/Documents/GitHub/CreditCardReader/inputOutput/output.csv";
		String inputXmlPath = "/Users/q/Documents/GitHub/CreditCardReader/inputOutput/input_file.xml";
		String outputXmlPath = "/Users/q/Documents/GitHub/CreditCardReader/inputOutput/output.xml";
		try {
			File inputXmlFile = new File(inputXmlPath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputXmlFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("CARD");
			Vector<CreditCard> cards = new Vector<CreditCard>();
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					String cardNumber = eElement.getElementsByTagName("CARD_NUMBER").item(0).getTextContent();
					String cardHolderName = eElement.getElementsByTagName("CARD_HOLDER_NAME").item(0).getTextContent();
					// System.out.println("\ncardNumber: " + cardNumber);
					// System.out.println("cardHolderName: " + cardHolderName);

			/*
			 * BufferedReader br = new BufferedReader(new FileReader(inputNameCsv));
			 * BufferedWriter bw = new BufferedWriter(new FileWriter(outputNameCsv));
			 * bw.write("cardNumber, cardType\n");
			 * // read csv
			 * String line;
			 * int skipLines = 0;
			 * Vector<CreditCard> cards = new Vector<CreditCard>();
			 * while ((line = br.readLine()) != null) {
			 * if (skipLines++ == 0)
			 * continue;
			 * String[] cCard = line.split(","); // input file lines: cardNumber[0],
			 * expirationDate[1], cardHolderName[2]
			 * String cardNumber = cCard[0];
			 * String cardHolderName = cCard[2];
			 */

					// Validate cardNumber
					String cardType = validateCard(cardNumber);
					
					// Create new subclass credit card
					if (cardType == "MasterCard") cards.add(new MasterCard(cardNumber, cardHolderName, cardType));
					else if (cardType == "Visa") cards.add(new Visa(cardNumber, cardHolderName, cardType));
					else if (cardType == "AmericanExpress") cards.add(new AmericanExpress(cardNumber, cardHolderName, cardType));
					else if (cardType == "Discover") cards.add(new Discover(cardNumber, cardHolderName, cardType));
					
					// Add credit card to output file
					// bw.write(cardNumber + "," + cardType + "\n");
				}
			}
			
			for (CreditCard card : cards) {
			card.printCard();
			}
			// br.close();
			// bw.close();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
}