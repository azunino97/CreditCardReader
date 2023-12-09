import java.io.File;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ParserXml extends Parser {
    public ParserXml (String inputPathXml, String outputPathXml) { super(inputPathXml, outputPathXml); }
    
    public Vector<CreditCard> getCreditCardsXml() {
        Vector<CreditCard> cards = new Vector<CreditCard>();
        try {
            File inputXmlFile = new File(super.inputPath);
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
        } catch (Exception err) {
            err.printStackTrace();
        }
        return cards;
    }

    public void writeCreditCardsXml(Vector<CreditCard> cards) {
        try {

        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}