package main.java;

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
        FileReaderJson fileReader = new FileReaderJson();

        // Read necessary variables with the FileReaderJson object
        int intPlayerSize = fileReader.getIntPlayerSize();
        int intThreshold = fileReader.getIntThreshold();
        int intStartMoney = fileReader.getIntStartMoney();
        int intGoMoney = fileReader.getIntGoMoney();
        int intJailFine = fileReader.getIntJailFine();
        String[] properties = fileReader.getProperties();
        String[] propertyColor = fileReader.getPropertyColor();
        int[] propertyFine = fileReader.getPropertyFine();
        int[] propertyPrice = fileReader.getPropertyPrice();
        String[] utilityName = fileReader.getUtilityName();
        int[] utilityRate = fileReader.getUtilityRate();
        int[] utilityPrice = fileReader.getUtilityPrice();
        String[] transportName = fileReader.getTransportName();
        int[] transportFine = fileReader.getTransportFine();
        int[] transportPrice = fileReader.getTransportPrice();
        String[] taxSquares = fileReader.getTaxSquares();
        int[] taxFine = fileReader.getTaxFine();
        int intGoToJailNumber = fileReader.getIntGoToJailNumber();

        // Create MonopolyGame object with given parameters
        MonopolyGame monopolyGame = new MonopolyGame(intPlayerSize, intThreshold, intStartMoney, intGoMoney,
                properties, propertyFine, propertyPrice, propertyColor, utilityName, utilityRate, utilityPrice, transportName,
                transportFine, transportPrice, taxFine, taxSquares, intJailFine, intGoToJailNumber);
        // Call the Play function of MonopolyGame to start the game
        monopolyGame.Play();

    }
}
