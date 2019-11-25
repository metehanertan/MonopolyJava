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
        FileReader fileReader = new FileReader();


        // Create MonopolyGame object with given parameters
        MonopolyGame monopolyGame = new MonopolyGame(intPlayerSize, intThreshold, intStartMoney, intTaxNumber, intGoMoney,
                properties, propertyFine, propertyPrice, propertyColor, utilityName, utilityRate, utilityPrice, transportName,
                transportFine, transportPrice, taxFine, taxSquares, intJailFine);
        // Call the Play function of MonopolyGame to start the game
        monopolyGame.Play();

    }
}
