public class Money extends Player{

    private int money;

    public Money(int money){
        this.money = money;
    }

    public int getMoney(){
        return this.money;
    }

    public void increaseMoney(int increase){
        this.money += increase;
    }

    public void decreaseMoney(int decrease){
        this.money -= decrease;
    }


}