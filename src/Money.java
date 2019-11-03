//Money class.
public class Money extends Player{

    private int money; //money amount

    //Constructor of Money Class.
    public Money(int money){
        this.money = money;
    }

    //getter method for money amount
    public int getCurrentMoney(){
        return this.money;
    }

    //method for increase the amount of money with a given parameter.
    public void increaseMoney(int increase){
        this.money += increase;
    }

    //method for decrease the amount of money with a given parameter.
    public void decreaseMoney(int decrease){
        this.money -= decrease;
    }


}