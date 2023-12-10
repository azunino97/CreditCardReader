/***
* CreditCard superclass to implement each of the different subclasses for the cards:
* Mastercard (1st: 5, 2nd: 1-5, len: 16)
* Visa (1st: 4, len: 13, 16)
* AmericanExpress (1st: 3, 2nd: 4, 7, len: 15)
* Discover (1st: 6011, len: 16)
***/

// superclass
public class InputOutputPaths {
    public String inputPath;
    public String outputPath;

    public InputOutputPaths(String input, String output) {
        inputPath = input;
        outputPath = output;
    }
}