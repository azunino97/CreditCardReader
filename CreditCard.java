/***
* CreditCard superclass to implement each of the different subclasses for the cards:
* Mastercard (1st: 5, 2nd: 1-5, len: 16)
* Visa (1st: 4, len: 13, 16)
* AmericanExpress (1st: 3, 2nd: 4, 7, len: 15)
* Discover (1st: 6011, len: 16)
***/

// superclass
public class CreditCard {
      private String cardNumber;
      private String cardHolderName;
      private String cardType;

      public CreditCard(String num, String name, String type) {
        cardNumber = num;
        cardHolderName = name;
        cardType = type;
      }

      public String getName() { return cardHolderName; }
      public String getNumber() { return cardNumber; }
      public String getType() { return cardType; }

      public void printCard() { 
        System.out.print("cardNumber," + getNumber());
        System.out.print(", cardHolderName," + getName());
        System.out.println(", cardType," + getType());
      }
}

