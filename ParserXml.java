import java.io.File;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ParserXml extends Parser {
    public ParserXml (String inputPathXml, String outputPathXml) { super(inputPathXml, outputPathXml); }
    
    public void readCreditCards() {
        Vector<CreditCard> tempCards = new Vector<CreditCard>();
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
                    tempCards.add(createCard(cardNumber, cardHolderName, cardType));
                }
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
        cards = tempCards;
    }

    public void writeCreditCards() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root element
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("CARDS");
            doc.appendChild(rootElement);

            // add all card cardElements to rootElement
            for (CreditCard card : cards) {
                Element cardElement = doc.createElement("CARD");
                rootElement.appendChild(cardElement);

                Element name = doc.createElement("CARD_NUMBER");
                name.setTextContent(card.getNumber());
                cardElement.appendChild(name);

                Element role = doc.createElement("CARD_TYPE");
                role.setTextContent(card.getType());
                cardElement.appendChild(role);
            }
            // output to file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // pretty print
            
            // write to file
            DOMSource source = new DOMSource(doc);
            StreamResult file = new StreamResult(new File(outputPath));
            transformer.transform(source, file);
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}