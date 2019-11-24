import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.text.html.HTMLDocument;
import java.io.FileReader;
import java.util.Iterator;

//Main class to play the game
public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        int i = 0;

        // Read JSON file for inputs
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("input.json"));
        JSONObject jo = (JSONObject) obj;

        // Read inputs as string
        String playerSize = (String) jo.get("playerSize");
        String threshold = (String) jo.get("threshold");
        String startMoney = (String) jo.get("startMoney");
        String taxNumber = (String) jo.get("taxNumber");
        String goMoney = (String) jo.get("goMoney");
        String jailFine = (String) jo.get("jailFine");

        JSONArray jsonProperties = (JSONArray) jo.get("propertyName");
        Iterator iterator1 = jsonProperties.iterator();
        String[] properties = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};

        JSONArray jsonPropertyColor = (JSONArray) jo.get("propertyColor");
        Iterator iterator2 = jsonPropertyColor.iterator();
        String[] propertyColor = {"", "", "", "", "", "", "", ""};

        JSONArray jsonPropertyFine = (JSONArray) jo.get("propertyFine");
        Iterator iterator3 = jsonPropertyFine.iterator();
        int[] propertyFine = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        JSONArray jsonPropertyPrice = (JSONArray) jo.get("propertyPrice");
        Iterator iterator4 = jsonPropertyPrice.iterator();
        int[] propertyPrice = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        JSONArray jsonUtilityName = (JSONArray) jo.get("utilityName");
        Iterator iterator5 = jsonUtilityName.iterator();
        String[] utilityName = {"", ""};

        JSONArray jsonUtilityRate = (JSONArray) jo.get("utilityRate");
        Iterator iterator6 = jsonUtilityRate.iterator();
        int[] utilityRate = {0, 0};

        JSONArray jsonUtilityPrice = (JSONArray) jo.get("utilityPrice");
        Iterator iterator7 = jsonUtilityPrice.iterator();
        int[] utilityPrice = {0, 0};

        JSONArray jsonTransportName = (JSONArray) jo.get("transportName");
        Iterator iterator8 = jsonTransportName.iterator();
        String[] transportName = {"", "", "", "", ""};

        JSONArray jsonTransportFine = (JSONArray) jo.get("transportFine");
        Iterator iterator9 = jsonTransportFine.iterator();
        int[] transportFine = {0, 0, 0, 0, 0};

        JSONArray jsonTransportPrice = (JSONArray) jo.get("transportPrice");
        Iterator iterator10 = jsonTransportPrice.iterator();
        int[] transportPrice = {0, 0, 0, 0, 0};

        JSONArray jsonTaxSquares = (JSONArray) jo.get("taxSquares");
        Iterator iterator11 = jsonTaxSquares.iterator();
        String[] taxSquares = {"", ""};

        JSONArray jsonTaxFine = (JSONArray) jo.get("taxFine");
        Iterator iterator12 = jsonTaxFine.iterator();
        int[] taxFine = {0, 0};

        while (iterator1.hasNext()) {
            int a = Integer.parseInt((String) iterator3.next());
            int b = Integer.parseInt((String) iterator4.next());

            properties[i] = (String) iterator1.next();
            propertyFine[i] = a;
            propertyPrice[i] = b;
            i++;


        }
        i = 0;
        while (iterator2.hasNext()) {
            propertyColor[i] = (String) iterator2.next();
            i++;

        }

        i = 0;
        while (iterator5.hasNext()) {
            int a = Integer.parseInt((String) iterator6.next());
            int b = Integer.parseInt((String) iterator7.next());

            utilityName[i] = (String) iterator5.next();
            utilityRate[i] = a;
            utilityPrice[i] = b;
            i++;

        }

        i = 0;
        while (iterator8.hasNext()) {
            int a = Integer.parseInt((String) iterator9.next());
            int b = Integer.parseInt((String) iterator10.next());
            transportName[i] = (String) iterator8.next();
            transportFine[i] = a;
            transportPrice[i] = b;
            i++;
        }

        i = 0;
        while (iterator11.hasNext()) {
            int a = Integer.parseInt((String) iterator12.next());
            taxFine[i] = a;
            taxSquares[i] = (String) iterator11.next();
            i++;

        }

        // Cast given strings to integers
        int intPlayerSize = Integer.parseInt(playerSize);
        int intThreshold = Integer.parseInt(threshold);
        int intStartMoney = Integer.parseInt(startMoney);
        int intTaxNumber = Integer.parseInt(taxNumber);
        int intGoMoney = Integer.parseInt(goMoney);
        int intJailFine = Integer.parseInt(jailFine);

        // Create MonopolyGame object with given parameters
        MonopolyGame monopolyGame = new MonopolyGame(intPlayerSize, intThreshold, intStartMoney, intTaxNumber, intGoMoney,
                properties, propertyFine, propertyPrice, propertyColor, utilityName, utilityRate, utilityPrice, transportName,
                transportFine, transportPrice, taxFine, taxSquares, intJailFine);
        // Call the Play function of MonopolyGame to start the game
        monopolyGame.Play();

    }
}
