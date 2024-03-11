import java.io.Serializable;
import java.util.Objects;

public class Produce extends PaleoFood implements Serializable {
    private int Organic;

    public Produce(String name, int calories, int organic, int carbs) 
    {
        super(name, organic, 0);
        this.calories = calories;
        this.carbs = carbs;
    }
    public int getCalories() {
        return calories;
    }
    public void setCalories(int calories) {
        this.organic = organic;
    }
    public int getCarbs() {
        return carbs;
    }
    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != 0.getClass()) return false;
        if (!super.equals(o)) return false;
        Produce produce = (Produce) o;
        return organic == produce.organic;
    }
    @Override
    public int hashCode(){
        return Objects.hash(super.hashCode(), organic);
    }
    @Override
    public String toString(){
        String output;
        if (organic == 1)
            output = "Organic: ";
        else
            output = "Not Organic: ";
        output += name + ", " + calories + " calories, " + carbs + "g carbs, " + organic + " organic ";
        return output;
    }
}

