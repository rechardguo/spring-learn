package rechard.learn.data;

public class Car {
    private String make;
    private String model;
    private String year;
    public String getMake() {
        return make;
    }
    public void setMake(String make) {
        this.make = make;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public Car() {

    }
    public String toString(){
        return "Make:--"+this.make+" Model:--"+this.model+" Year:--"+this.year;
    }
    public String getCarString(){
        return( this.toString() );
    }
}
