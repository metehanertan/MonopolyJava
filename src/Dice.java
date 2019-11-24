public class Dice {

    private int firstValue; //First dice value
    private int secondValue; //Second dice value

    //Default constructor
    public Dice() {
    }

    //Getter method for first dice value
    public int getFirstValue() {
        return firstValue;
    }

    //Getter method for second dice value
    public int getSecondValue() {
        return secondValue;
    }

    public void rollDice(){
        this.firstValue = (int) (6 * Math.random() + 1);
        this.secondValue = (int) (6 * Math.random() + 1);
    }

    public int getTotal(){
        int total;
        total = this.firstValue + this.secondValue;
        return total;
    }

    public int getFirstRandomValue(){
        this.firstValue = (int) (6 * Math.random() + 1);
        return firstValue;
    }
    public int getSecondRandomValue(){
        this.secondValue = (int) (6 * Math.random() + 1);
        return secondValue;
    }
}
