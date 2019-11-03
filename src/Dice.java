public class Dice extends MonopolyGame{

    private int firstValue;
    private int secondValue;
    private int result;

    public Dice(){
    }

    public int getFirstValue(){
        firstValue = (int)(6 * Math.random() + 1);
        return firstValue;
    }

    public int getSecondValue(){
        secondValue = (int)(6 * Math.random() + 1);
        return secondValue;
    }

}
