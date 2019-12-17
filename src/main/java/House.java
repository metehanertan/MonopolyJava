package main.java;

public class House {
    private String name;
    private PropertySquare pSquare;

    public House(PropertySquare pSquare){
        this.name = "HOUSE";
        this.pSquare = pSquare;
    }

    public PropertySquare getPropertySquare(){
        return this.pSquare;
    }

    public void setPropertySquare(PropertySquare pSquare){
        this.pSquare = pSquare;
    }

}
