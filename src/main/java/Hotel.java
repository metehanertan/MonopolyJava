public class Hotel {
    private int count; // Hotel number on a square
    private String name;
    private PropertySquare pSquare;

    public Hotel(PropertySquare pSquare){
        this.count = 0;
        this.name = "HOTEL";
        this.pSquare = pSquare;
    }

    public int getCount(){
        return this.count;
    }

    public void incCount(){
        this.count++;
    }

    public void decCount(){
        this.count--;
    }

    public PropertySquare getPropertySquare(){
        return this.pSquare;
    }

    public void setPropertySquare(PropertySquare pSquare){
        this.pSquare = pSquare;
    }

}
