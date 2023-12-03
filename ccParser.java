import java.io.*;

class Main {
	public static void main(String[] args) {
		String inputName = "/Users/q/Documents/SJSU MSCMPE/CMPE202/IndividualProject/inputOutput/input_file.csv";
		String outputName = "/Users/q/Documents/SJSU MSCMPE/CMPE202/IndividualProject/inputOutput/output.csv";
		try {
			BufferedReader br = new BufferedReader(new FileReader(inputName));
			BufferedWriter bw = new BufferedWriter(new FileWriter(outputName));
			
			bw.write("cardNumber, expirationDate, cardHolderName\n");
			
			String line;
			int skipLines = 0;
			while ((line = br.readLine()) != null) {
				if (skipLines++ == 0) continue;
				String[] cCard = line.split(","); // input file lines: cardNumber, expirationDate (not used), cardHolderName (not used)
				String cardNumber = cCard[0];
				// System.out.println("cardHolderName," + cCard[2] + ", expirationDate," + cCard[1] + ", cardNumber," + cardNumber);
				bw.write(cardNumber + ", " + cCard[1] + ", " + cCard[2] + "\n");
			}
			br.close();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
}