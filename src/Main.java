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
        String taxValue = (String) jo.get("taxValue");
        String startMoney = (String) jo.get("startMoney");
        String taxNumber = (String) jo.get("taxNumber");
        String goMoney = (String) jo.get("goMoney");

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
            properties[i] = (String) iterator1.next();
            System.out.println(properties[i]);
        }

        while (iterator2.hasNext()) {
            propertyColor[i] = (String) iterator2.next();
            System.out.println(propertyColor[i]);
        }

        while (iterator3.hasNext()) {
            int a = Integer.parseInt((String) iterator3.next());
            propertyFine[i] = a;
            System.out.println(propertyFine[i]);
        }

        while (iterator4.hasNext()) {
            int a = Integer.parseInt((String) iterator4.next());
            propertyPrice[i] = a;
            System.out.println(propertyPrice[i]);
        }

        while (iterator5.hasNext()) {
            utilityName[i] = (String) iterator5.next();
            System.out.println(utilityName[i]);
        }

        while (iterator6.hasNext()) {
            int a = Integer.parseInt((String) iterator6.next());
            utilityRate[i] = a;
            System.out.println(utilityRate[i]);
        }

        while (iterator7.hasNext()) {
            int a = Integer.parseInt((String) iterator7.next());
            utilityPrice[i] = a;
            System.out.println(utilityPrice[i]);
        }

        while (iterator8.hasNext()) {
            transportName[i] = (String) iterator8.next();
            System.out.println(transportName[i]);
        }

        while (iterator9.hasNext()) {
            int a = Integer.parseInt((String) iterator9.next());
            transportFine[i] = a;
            System.out.println(transportFine[i]);
        }

        while (iterator10.hasNext()) {
            int a = Integer.parseInt((String) iterator10.next());
            transportPrice[i] = a;
            System.out.println(transportPrice[i]);
        }

        while (iterator11.hasNext()) {
            taxSquares[i] = (String) iterator11.next();
            System.out.println(taxSquares[i]);
        }

        while (iterator12.hasNext()) {
            int a = Integer.parseInt((String) iterator12.next());
            taxFine[i] = a;
            System.out.println(taxFine[i]);
        }

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
