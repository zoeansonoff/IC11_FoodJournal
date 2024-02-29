import java.io.Serializable;
import java.util.Objects;

public abstract class PaleoFood implements Serializable {
    protected String name;
    protected int calories;
    protected int carbs;

    protected PaleoFood(String name, int calories, int carbs) {
        this.name = name;
        this.calories = calories;
        this.carbs = carbs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
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
        if (o == null || getClass() != o.getClass()) return false;
        PaleoFood paleoFood = (PaleoFood) o;
        return calories == paleoFood.calories && carbs == paleoFood.carbs && Objects.equals(name, paleoFood.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, calories, carbs);
    }
}
