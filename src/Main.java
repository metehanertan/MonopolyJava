import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;

//Main class to play the game
public class Main {
    public static void main(String[] args) throws IOException, ParseException {

        // Read JSON file for inputs
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("input.json"));
        JSONObject jo = (JSONObject) obj;

        // Read inputs as string
        String playerSize =(String)jo.get("playerSize");
        String taxValue = (String)jo.get("taxValue");
        String startMoney = (String)jo.get("startMoney");
        String taxNumber = (String)jo.get("taxNumber");
        String goMoney = (String)jo.get("goMoney");

        // Cast given strings to integers
        int intPlayerSize = Integer.parseInt(playerSize);
        int intTaxValue = Integer.parseInt(taxValue);
        int intStartMoney = Integer.parseInt(startMoney);
        int intTaxNumber = Integer.parseInt(taxNumber);
        int intGoMoney = Integer.parseInt(goMoney);

        // Create MonopolyGame object with given parameters
        MonopolyGame monopolyGame = new MonopolyGame(intPlayerSize, intTaxValue, intStartMoney, intTaxNumber, intGoMoney);
        // Call the Play function of MonopolyGame to start the game
        monopolyGame.Play();

    }
}
