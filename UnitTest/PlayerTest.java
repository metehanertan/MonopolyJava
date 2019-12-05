import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    String name = "John";
    int money = 150;
    Player player = new Player(name, money);

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

    Board board = new Board(properties, propertyFine, propertyPrice, propertyColor, utilityName, utilityRate, utilityPrice, transportName,
            transportFine, transportPrice, taxFine, taxSquares, goToJailNumber);


    @Test
    public void testHasItAllTrue(){
        board.setSquareList();
        Square[] squares = board.getSquareList();
        int a = 0;

        for(int i = 0; i < squares.length; i++){
            if(squares[i] instanceof PropertySquare && ((PropertySquare)squares[i]).getColor() == "Blue"){
                ((PropertySquare)squares[i]).setOwner(player);
                a = i;
            }
        }

        assertTrue("hasItAll method does not work correctly.", player.hasItAll((PropertySquare) squares[a], board));
    }

    @Test
    public void testHasItAllFalse(){
        board.setSquareList();
        Square[] squares = board.getSquareList();
        int a = 0;
        int number = 0;
        String color = propertyColor[0];

        for(int j = 1; j < propertyColor.length; j++){
            for(int i = 0; i < squares.length; i++){
                if(squares[i] instanceof PropertySquare && ((PropertySquare)squares[i]).getColor() == color) {
                    number++;
                }
            }

            if(number <= 1){
                color = propertyColor[j];
            }
            else {
                break;
            }
        }


        for(int i = 0; i < squares.length; i++){
            if(squares[i] instanceof PropertySquare && ((PropertySquare)squares[i]).getColor() == color){
                ((PropertySquare)squares[i]).setOwner(player);
                System.out.println("Player owns: " + squares[i].getSquareName() + ", color: "+ ((PropertySquare)squares[i]).getColor());
                a = i;
                break;
            }
        }

        System.out.println("Color " + color + " squares:");
        for (int i = 0; i < squares.length; i++){
            if(squares[i] instanceof PropertySquare && ((PropertySquare)squares[i]).getColor() == color) {
                System.out.println("Square ID: " + squares[i].getSquareID());
            }
        }

        assertTrue("hasItAll method does not work correctly.", ! (player.hasItAll((PropertySquare) squares[a], board)));
    }


    @Test
    public void testPlayerMoney(){
        controlPlayerMoney(player, money);
    }

    @Test
    public void testPlayerName(){
        controlPlayerName(player, name);
    }

    void controlPlayerName(Player player, String name){
        assertEquals("Player name is not assigned properly.", player.getPlayerName(), name);
    }
    void controlPlayerMoney(Player player, int money){
        assertEquals("Player money is not assigned properly.", player.getMoney().getCurrentMoney(), money);
    }
}