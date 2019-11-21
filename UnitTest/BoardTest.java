import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
/*
public class BoardTest {

    int realTaxValue;
    int realTaxSquareNumber;

    int resultTaxSquareNumber;
    int resultNormalSquareNumber;
    int resultGoSquareNumber;

    @Test
    public void checkNormalSquares() throws IOException, ParseException {
        calculateSquares();
        assertEquals(resultTaxSquareNumber, realTaxSquareNumber);
    }

    @Test
    public void checkTaxSquares() throws IOException, ParseException {
        calculateSquares();
        assertEquals(resultNormalSquareNumber, (39 - realTaxSquareNumber) );
    }

    @Test
    public void checkGoSquare() throws IOException, ParseException {
        calculateSquares();
        assertEquals(resultGoSquareNumber, 1);
    }

    public void getTaxValuesFromFile() throws IOException, ParseException{
        JSONParser parser = new JSONParser();

        Object obj = parser.parse(new FileReader("input.json"));
        JSONObject jo = (JSONObject) obj;

        String taxNumber = (String)jo.get("taxNumber");
        String taxValue = (String)jo.get("taxValue");

        realTaxSquareNumber = Integer.parseInt(taxNumber);
        realTaxValue = Integer.parseInt(taxNumber);

    }

    public void calculateSquares() throws IOException, ParseException {
        resultTaxSquareNumber = 0;
        resultNormalSquareNumber = 0;
        resultGoSquareNumber = 0;

        getTaxValuesFromFile();
        Board board = new Board(realTaxSquareNumber, realTaxValue);
        board.setSquareList();
        Square[] squares = board.getSquareList();

        for(int i = 0; i < squares.length; i++){
            if(squares[i].getSquareName() == "TAX"){
                resultTaxSquareNumber++;
            }
            if(squares[i].getSquareName() == "NORMAL"){
                resultNormalSquareNumber++;
            }
            if(squares[i].getSquareName() == "GO"){
                resultGoSquareNumber++;
            }
        }

    }

}*/
