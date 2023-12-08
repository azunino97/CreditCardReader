// superclass
public class Parser {
    // private Vector<CreditCard> cards;
    public String inputPath;

    public Parser(String inputPathName) { inputPath = inputPathName; }

    // AmericanExpress (1st: 3, 2nd: 4, 7, len: 15)
    // Visa (1st: 4, len: 13, 16)
    // MasterCard (1st: 5, 2nd: 1-5, len: 16)
    // Discover (1st: 6011, len: 16)
    protected String validateCard(String cardNum) {
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

    protected CreditCard createCard(String cardNumber, String cardHolderName, String cardType) {
        return new CreditCard(cardNumber, cardHolderName, cardType);
    }
}
