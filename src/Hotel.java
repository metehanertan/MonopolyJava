public class Hotel {
    private String name;
    private PropertySquare pSquare;

    public Hotel(PropertySquare pSquare){

        this.name = "HOTEL";
        this.pSquare = pSquare;
    }

    public PropertySquare getPropertySquare(){
        return this.pSquare;
    }

    public void setPropertySquare(PropertySquare pSquare){
        this.pSquare = pSquare;
    }

}
