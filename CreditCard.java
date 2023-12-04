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

      CreditCard(String num, String name) {
        cardNumber = num;
        cardHolderName = name;
      }

      public String getName() { return cardHolderName; }
      public String getNumber() { return cardNumber; }

      public static void printCard(CreditCard card) { 
        System.out.println("Number = " + card.getNumber());
        System.out.println("Name = " + card.getName());
      }
}