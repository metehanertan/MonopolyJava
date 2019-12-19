import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class FileReaderJson {

    private int intPlayerSize; //Integer value of player number of game.
    private int intThreshold; //Integer threshold value for buying properties.
    private int intStartMoney; // Integer value of
    private int intGoMoney;
    private int intJailFine;
    private String[] properties = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
    private String[] propertyColor = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
    private int[] propertyFine = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int[] propertyPrice = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private String[] utilityName = {"", ""};
    private int[] utilityRate = {0, 0};
    private int[] utilityPrice = {0, 0};
    private String[] transportName = {"", "", "", "", ""};
    private int[] transportFine = {0, 0, 0, 0, 0};
    private int[] transportPrice = {0, 0, 0, 0, 0};
    private String[] taxSquares = {"", ""};
    private int[] taxFine = {0, 0};
    private int intGoToJailNumber;
    private int[] rent1 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int[] rent2 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int[] rent3 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int[] rent4 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int[] hotel = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int[] mortgage = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int[] pricePerHouse = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};


    public FileReaderJson() throws IOException, ParseException {
        int i = 0;

        Object obj = new JSONParser().parse(new FileReader("input.json"));
        JSONObject jo = (JSONObject) obj;


        //PROPETTIES
        JSONArray japrop = (JSONArray) jo.get("properties");
        for (int k = 0; k < japrop.size(); k++) {
            JSONObject jsonProp = (JSONObject) japrop.get(k);
            properties[k] = (String) jsonProp.get("name");
            propertyColor[k] = (String) jsonProp.get("color");
            long a = (long) jsonProp.get("rent");
            long b = (long) jsonProp.get("cost");
            long c = (long) jsonProp.get("rent1");
            long d = (long) jsonProp.get("rent2");
            long e = (long) jsonProp.get("rent3");
            long f = (long) jsonProp.get("rent4");
            long g = (long) jsonProp.get("hotel");
            long h = (long) jsonProp.get("mortgage");
            long l = (long) jsonProp.get("pricePerHouse");
            propertyFine[k] = (int) a;
            propertyPrice[k] = (int) b;
            rent1[k] = (int) c;
            rent2[k] = (int) d;
            rent3[k] = (int) e;
            rent4[k] = (int) f;
            hotel[k] = (int) g;
            mortgage[k] = (int) h;
            pricePerHouse[k] = (int) l;
        }

        //UTILITIES
        JSONArray jautils = (JSONArray) jo.get("utilities");
        for (int j = 0; j < jautils.size(); j++) {
            JSONObject jsonUtil = (JSONObject) jautils.get(j);

            utilityName[j] = (String) jsonUtil.get("name");
            long a = (long) jsonUtil.get("rate");
            long b = (long) jsonUtil.get("price");
            utilityPrice[j] = (int) b;
            utilityRate[j] = (int) a;
        }

        //TRANSPORT
        JSONArray jatransport = (JSONArray) jo.get("transport");
        for (int m = 0; m < jatransport.size(); m++) {

            JSONObject jsonTransport = (JSONObject) jatransport.get(m);

            transportName[m] = (String) jsonTransport.get("name");
            long a = (long) jsonTransport.get("price");
            long b = (long) jsonTransport.get("fine");
            transportPrice[m] = (int) b;
            transportFine[m] = (int) a;
        }

        JSONArray jaTax = (JSONArray) jo.get("tax");
        for (int n = 0; n < jaTax.size(); n++) {


            JSONObject jsonTax = (JSONObject) jaTax.get(n);
            taxSquares[n] = (String) jsonTax.get("name");
            long a = (long) jsonTax.get("fine");
            taxFine[n] = (int) a;
        }



        // Read inputs as string
        String playerSize = (String) jo.get("playerSize");
        String threshold = (String) jo.get("threshold");
        String startMoney = (String) jo.get("startMoney");
        String goMoney = (String) jo.get("goMoney");
        String jailFine = (String) jo.get("jailFine");
        String goToJailNumber = (String) jo.get("goToJailNumber");


        // Cast given strings to integers
        this.intPlayerSize = Integer.parseInt(playerSize);
        this.intThreshold = Integer.parseInt(threshold);
        this.intStartMoney = Integer.parseInt(startMoney);
        this.intGoMoney = Integer.parseInt(goMoney);
        this.intJailFine = Integer.parseInt(jailFine);
        this.intGoToJailNumber = Integer.parseInt(goToJailNumber);
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

    public int getIntGoToJailNumber() {
        return intGoToJailNumber;
    }

    public int[] getRent1(){
        return rent1;
    }

    public int[] getRent2() {
        return rent2;
    }

    public int[] getRent3() {
        return rent3;
    }

    public int[] getRent4() {
        return rent4;
    }

    public int[] getHotel() {
        return hotel;
    }

    public int[] getMortgage() {
        return mortgage;
    }

    public int[] getPricePerHouse() {
        return pricePerHouse;
    }
}
