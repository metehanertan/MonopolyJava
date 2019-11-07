public class Dice{

    private int firstValue; //First dice value
    private int secondValue; //Second dice value
    private int result;

    //Getter method for first dice value
    public int getFirstValue(){
        firstValue = (int)(6 * Math.random() + 1);
        return firstValue;
    }

    //Getter method for second dice value
    public int getSecondValue(){
        secondValue = (int)(6 * Math.random() + 1);
        return secondValue;
    }
}
