public class Dice {

    private int firstValue;
    private int secondValue;


    public Dice(){
        do{
            firstValue = (int)(6 * Math.random() + 1);
            secondValue = (int)(6 * Math.random() + 1);
        } while(firstValue == secondValue);
    }

    public int roll(){
        return 1;
    }

    public int getFirstValue(){
        return firstValue;
    }

    public int getSecondValue(){
        return secondValue;
    }

}
