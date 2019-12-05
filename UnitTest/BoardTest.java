import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;

public class BoardTest {
    String[] properties = {"KADIKÖY", "TAKSİM", "KARAKÖY", "ÇANAKKALE", "4.LEVENT", "BAĞDAT CADDESİ", "ACIBADEM", "EMİNÖNÜ", "FATİH", "BURSA",
            "ESKİŞEHİR", "ANKARA", "BEŞİKTAŞ", "OSMANBEY", "HALKALI", "GÖZTEPE", "ERENKÖY","BEYLİKDÜZÜ", "BEYOĞLU", "ŞİŞLİ", "BEYKOZ", "ÜMRANİYE"};
    int[] propertyFine = {3, 3, 5, 5, 6, 7, 7, 8, 9, 9, 10, 11, 11, 12, 13, 13, 14, 15, 15, 16, 18, 20};
    int[] propertyPrice = {6, 6, 10, 10, 12, 14, 14, 16, 18, 18, 20, 22, 22, 24, 26, 26, 28, 30, 30, 32, 36, 40};
    String[] propertyColor = {"Blue", "Brown", "Red", "Yellow", "Orange", "Green", "Black", "Gray"};
    String[] utilityName = {"ELECTRIC", "WATER"};
    int[] utilityRate = {1, 1};
    int[] utilityPrice = {15, 15};
    String[] transportName = {"OTOBUS","METRO","METROBUS","UCAK"};
    int[] transportFine = {5, 5, 5, 5};
    int[] transportPrice = {20, 20, 20, 20};
    int[] taxFine = {20, 10};
    String[] taxSquares = {"INCOME TAX", "LUXURY TAX"};
    int goToJailNumber = 3;

    @Test
    public void testGoSquare(){
        Board board = new Board(properties, propertyFine, propertyPrice, propertyColor, utilityName, utilityRate, utilityPrice, transportName,
                transportFine, transportPrice, taxFine, taxSquares, goToJailNumber);
        board.setSquareList();
        Square[] squares = board.getSquareList();

        assertEquals("First square is not GO square.", squares[0].getSquareName() , "GO");
    }

    @Test
    public void testGoJailSquares(){
        Board board = new Board(properties, propertyFine, propertyPrice, propertyColor, utilityName, utilityRate, utilityPrice, transportName,
                transportFine, transportPrice, taxFine, taxSquares, goToJailNumber);
        board.setSquareList();
        Square[] squares = board.getSquareList();

        int evaluatedGoToJailNumber = 0;

        for(int i = 0; i < squares.length; i++){
            if(squares[i].getSquareName() == "GOTOJAIL"){
                evaluatedGoToJailNumber++;
            }
        }

        assertEquals("Created the wrong amount of GOTOJAIL squares.", goToJailNumber, evaluatedGoToJailNumber);
    }

    @Test
    public void testJailSquare(){
        Board board = new Board(properties, propertyFine, propertyPrice, propertyColor, utilityName, utilityRate, utilityPrice, transportName,
                transportFine, transportPrice, taxFine, taxSquares, goToJailNumber);
        board.setSquareList();
        Square[] squares = board.getSquareList();

        assertEquals("10th square is not JAIL square.",squares[10].getSquareName() , "JAIL");
    }

    @Test
    public void testFreeParkingSquare(){
        Board board = new Board(properties, propertyFine, propertyPrice, propertyColor, utilityName, utilityRate, utilityPrice, transportName,
                transportFine, transportPrice, taxFine, taxSquares, goToJailNumber);
        board.setSquareList();
        Square[] squares = board.getSquareList();

        assertEquals("20th square is not FREEPARKING square.",squares[20].getSquareName() , "FREEPARKING");
    }


    @Test
    public void testFirstNormalSquare(){
        Board board = new Board(properties, propertyFine, propertyPrice, propertyColor, utilityName, utilityRate, utilityPrice, transportName,
                transportFine, transportPrice, taxFine, taxSquares, goToJailNumber);
        board.setSquareList();
        Square[] squares = board.getSquareList();

        assertTrue("2nd square must be Normal Square.",squares[2] instanceof NormalSquare);
    }

    @Test
    public void testSecondNormalSquare(){
        Board board = new Board(properties, propertyFine, propertyPrice, propertyColor, utilityName, utilityRate, utilityPrice, transportName,
                transportFine, transportPrice, taxFine, taxSquares, goToJailNumber);
        board.setSquareList();
        Square[] squares = board.getSquareList();

        assertTrue("7th square must be Normal Square.",squares[7] instanceof NormalSquare);
    }

    @Test
    public void testThirdNormalSquare(){
        Board board = new Board(properties, propertyFine, propertyPrice, propertyColor, utilityName, utilityRate, utilityPrice, transportName,
                transportFine, transportPrice, taxFine, taxSquares, goToJailNumber);
        board.setSquareList();
        Square[] squares = board.getSquareList();

        assertTrue("17th square must be Normal Square.",squares[17] instanceof NormalSquare);
    }

    @Test
    public void testFourthNormalSquare(){
        Board board = new Board(properties, propertyFine, propertyPrice, propertyColor, utilityName, utilityRate, utilityPrice, transportName,
                transportFine, transportPrice, taxFine, taxSquares, goToJailNumber);
        board.setSquareList();
        Square[] squares = board.getSquareList();

        assertTrue("22th square must be Normal Square.",squares[22] instanceof NormalSquare);
    }

    @Test
    public void testFifthNormalSquare(){
        Board board = new Board(properties, propertyFine, propertyPrice, propertyColor, utilityName, utilityRate, utilityPrice, transportName,
                transportFine, transportPrice, taxFine, taxSquares, goToJailNumber);
        board.setSquareList();
        Square[] squares = board.getSquareList();

        assertTrue("33th square must be Normal Square.",squares[33] instanceof NormalSquare);
    }

    @Test
    public void testSixthNormalSquare(){
        Board board = new Board(properties, propertyFine, propertyPrice, propertyColor, utilityName, utilityRate, utilityPrice, transportName,
                transportFine, transportPrice, taxFine, taxSquares, goToJailNumber);
        board.setSquareList();
        Square[] squares = board.getSquareList();

        assertTrue("36th square must be Normal Square.",squares[36] instanceof NormalSquare);
    }

}
