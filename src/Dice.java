public class Dice {

    private int firstValue;
    private int secondValue;
    private int result;

    public Dice(){
    }

    public int roll(){
        result = getFirstValue() + getSecondValue();
        return result;
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
