import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.Iterator;

public class FileReader {

    private int intPlayerSize;
    private int intThreshold;
    private int intStartMoney;
    private int intTaxNumber;
    private int intGoMoney;
    private int intJailFine;
    private String[] properties = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};;
    private String[] propertyColor = {"", "", "", "", "", "", "", ""};
    private int[]  propertyFine = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int[] propertyPrice = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private String[] utilityName = {"", ""};
    private int[] utilityRate = {0, 0};
    private int[] utilityPrice = {0, 0};
    private String[] transportName = {"", "", "", "", ""};
    private int[] transportFine = {0, 0, 0, 0, 0};
    private int[] transportPrice = {0, 0, 0, 0, 0};
    private String[] taxSquares = {"", ""};
    private int[] taxFine = {0, 0};


    public FileReader() throws IOException, ParseException {
        int i = 0;

        // Read JSON file for inputs
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new java.io.FileReader("input.json"));
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


        JSONArray jsonPropertyColor = (JSONArray) jo.get("propertyColor");
        Iterator iterator2 = jsonPropertyColor.iterator();


        JSONArray jsonPropertyFine = (JSONArray) jo.get("propertyFine");
        Iterator iterator3 = jsonPropertyFine.iterator();


        JSONArray jsonPropertyPrice = (JSONArray) jo.get("propertyPrice");
        Iterator iterator4 = jsonPropertyPrice.iterator();


        JSONArray jsonUtilityName = (JSONArray) jo.get("utilityName");
        Iterator iterator5 = jsonUtilityName.iterator();


        JSONArray jsonUtilityRate = (JSONArray) jo.get("utilityRate");
        Iterator iterator6 = jsonUtilityRate.iterator();


        JSONArray jsonUtilityPrice = (JSONArray) jo.get("utilityPrice");
        Iterator iterator7 = jsonUtilityPrice.iterator();


        JSONArray jsonTransportName = (JSONArray) jo.get("transportName");
        Iterator iterator8 = jsonTransportName.iterator();


        JSONArray jsonTransportFine = (JSONArray) jo.get("transportFine");
        Iterator iterator9 = jsonTransportFine.iterator();


        JSONArray jsonTransportPrice = (JSONArray) jo.get("transportPrice");
        Iterator iterator10 = jsonTransportPrice.iterator();


        JSONArray jsonTaxSquares = (JSONArray) jo.get("taxSquares");
        Iterator iterator11 = jsonTaxSquares.iterator();


        JSONArray jsonTaxFine = (JSONArray) jo.get("taxFine");
        Iterator iterator12 = jsonTaxFine.iterator();


        while (iterator1.hasNext()) {
            int a = Integer.parseInt((String) iterator3.next());
            int b = Integer.parseInt((String) iterator4.next());

            this.properties[i] = (String) iterator1.next();
            this.propertyFine[i] = a;
            this.propertyPrice[i] = b;
            i++;


        }
        i = 0;
        while (iterator2.hasNext()) {
            this.propertyColor[i] = (String) iterator2.next();
            i++;

        }

        i = 0;
        while (iterator5.hasNext()) {
            int a = Integer.parseInt((String) iterator6.next());
            int b = Integer.parseInt((String) iterator7.next());

            this.utilityName[i] = (String) iterator5.next();
            this.utilityRate[i] = a;
            this.utilityPrice[i] = b;
            i++;

        }

        i = 0;
        while (iterator8.hasNext()) {
            int a = Integer.parseInt((String) iterator9.next());
            int b = Integer.parseInt((String) iterator10.next());
            this.transportName[i] = (String) iterator8.next();
            this.transportFine[i] = a;
            this.transportPrice[i] = b;
            i++;
        }

        i = 0;
        while (iterator11.hasNext()) {
            int a = Integer.parseInt((String) iterator12.next());
            this.taxFine[i] = a;
            this.taxSquares[i] = (String) iterator11.next();
            i++;

        }

        // Cast given strings to integers
         this.intPlayerSize = Integer.parseInt(playerSize);
         this.intThreshold = Integer.parseInt(threshold);
         this.intStartMoney = Integer.parseInt(startMoney);
         this.intTaxNumber = Integer.parseInt(taxNumber);
         this.intGoMoney = Integer.parseInt(goMoney);
         this.intJailFine = Integer.parseInt(jailFine);
    }

    public int getIntPlayerSize() {
        return intPlayerSize;
    }

    public int getIntThreshold() {
        return intThreshold;
    }

    public int getIntStartMoney() {
        return intStartMoney;
    }

    public int getIntTaxNumber() {
        return intTaxNumber;
    }

    public int getIntGoMoney() {
        return intGoMoney;
    }

    public int getIntJailFine() {
        return intJailFine;
    }

    public int[] getPropertyFine() {
        return propertyFine;
    }

    public int[] getPropertyPrice() {
        return propertyPrice;
    }

    public int[] getTaxFine() {
        return taxFine;
    }

    public int[] getTransportFine() {
        return transportFine;
    }

    public int[] getTransportPrice() {
        return transportPrice;
    }

    public int[] getUtilityPrice() {
        return utilityPrice;
    }

    public int[] getUtilityRate() {
        return utilityRate;
    }

    public String[] getProperties() {
        return properties;
    }

    public String[] getPropertyColor() {
        return propertyColor;
    }

    public String[] getTaxSquares() {
        return taxSquares;
    }

    public String[] getTransportName() {
        return transportName;
    }

    public String[] getUtilityName() {
        return utilityName;
    }
}
