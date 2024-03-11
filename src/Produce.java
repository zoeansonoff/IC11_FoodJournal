import java.io.Serializable;
import java.util.Objects;

public class Produce extends PaleoFood implements Serializable {
    private int organic; // Change variable name to lowercase

    public Produce(String name, int calories, int organic, int carbs) {
        super(name, calories, carbs); // Pass calories and carbs to the superclass constructor
        this.organic = organic;
    }

    // Getters and setters for organic

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Produce produce = (Produce) o;
        return organic == produce.organic;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), organic);
    }

    @Override
    public String toString() {
        String output;
        if (organic == 1)
            output = "Organic: ";
        else
            output = "Not Organic: ";
        output += name + ", " + calories + " calories, " + carbs + "g carbs, " + organic + " organic ";
        return output;
    }
}


