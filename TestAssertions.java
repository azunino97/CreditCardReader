import org.junit.Test;
import static org.junit.Assert.*;

public class TestAssertions {
    @Test
    public void testCreditCard() {
        String num = "1234";
        String name = "todd";
        String type = "visa";
        CreditCard card = new CreditCard(num, name, type);
        assertEquals(num, card.getNumber());
        assertEquals(name, card.getName());
        assertEquals(type, card.getType());
    }

    @Test
    public void testInputOutputPath() {
        String inputPath = "thisisaninputpath.csv";
        String outputPath = "thisisanoutputpath.xml";
        InputOutputPaths paths = new InputOutputPaths(inputPath, outputPath);
        assertEquals(inputPath, paths.inputPath);
        assertEquals(outputPath, paths.outputPath);
    }

    @Test
    public void testParser() {
        String inputPath = "thisisaninputpath.csv";
        String outputPath = "thisisanoutputpath.xml";
        String num = "1234";
        String name = "todd";
        String type = "visa";

        Parser parser = new Parser(inputPath, outputPath);
        CreditCard card = parser.createCard(num, name, type);
        CreditCard cardDirect = new CreditCard(num, name, type);
        assertSame(card.getClass(), cardDirect.getClass());

        String invalidCardNum = "666666666666";
        String invalidNullCard = "";
        String invalidNonNumeric = "!12345679123";
        String invalidOver19 = "111333555777999222444666888";
        String invalidLess10 = "54321";
        String invalidCardNumErr = "Invalid: not a possible card number";
        String invalidNullCardErr = "Invalid: empty/null card number";
        String invalidNonNumericErr = "Invalid: non numeric characters";
        String invalidOver19Err = "Invalid: more than 19 digits";
        String invalidLess10Err = "Invalid: less than 10 digits";

        assertEquals(parser.validateCard(invalidCardNum), invalidCardNumErr);
        assertEquals(parser.validateCard(invalidNullCard), invalidNullCardErr);
        assertEquals(parser.validateCard(invalidNonNumeric), invalidNonNumericErr);
        assertEquals(parser.validateCard(invalidOver19), invalidOver19Err);
        assertEquals(parser.validateCard(invalidLess10), invalidLess10Err);

        String visaNum = "4123456789123";
        String discoverNum = "6011111100007756";
        String mastercardNum = "5167894523129089";
        String amexNum = "347856341908126";
        String visa = "Visa";
        String Discover = "Discover";
        String mastercard = "MasterCard";
        String amex = "AmericanExpress";

        assertEquals(parser.validateCard(visaNum), visa);
        assertEquals(parser.validateCard(discoverNum), Discover);
        assertEquals(parser.validateCard(mastercardNum), mastercard);
        assertEquals(parser.validateCard(amexNum), amex);
    }

    @Test
    public void testCreditCardParser() {
        CreditCardParser ccParserCsv = new CreditCardParser("test.csv", "test.csv");
        assertEquals(ccParserCsv.getFileType(), ".csv");
        assertSame(ccParserCsv.parser.getClass(), ParserCsv.class);
        CreditCardParser ccParserXml = new CreditCardParser("test.xml", "test.xml");
        assertEquals(ccParserXml.getFileType(), ".xml");
        assertSame(ccParserXml.parser.getClass(), ParserXml.class);
        CreditCardParser ccParserJson = new CreditCardParser("test.json", "test.json");
        assertEquals(ccParserJson.getFileType(), ".json");    
        assertSame(ccParserJson.parser.getClass(), ParserJson.class);
    }
}
