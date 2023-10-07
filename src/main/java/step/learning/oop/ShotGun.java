package step.learning.oop;

import com.google.gson.JsonObject;

import java.util.Arrays;
import java.util.stream.Stream;

@Serializable
public class ShotGun extends Weapon implements Classified, Used{

    @Required
    private int barrelLength;

    public ShotGun(String name, int barrelLength) {
        super.setName(name);
        this.barrelLength = barrelLength;
    }

    @Required
    private int yearsInUse;
    public int getYearsInUse() {
        return yearsInUse;
    }

    public int getBarrelLength() {
        return barrelLength;
    }

    @JsonFactory
    public static Gun fromJson(JsonObject jsonObject) {
        String[] requiredFields = {"name", "barrelLength", "yearsInUse"};
        for(String field : requiredFields) {
            if(!jsonObject.has(field)) {
                throw new IllegalArgumentException(String.format("Missing required field '%s'", field));
            }
        }
        return new Gun(jsonObject.get(requiredFields[0]).getAsString(), jsonObject.get(requiredFields[1]).getAsInt(), jsonObject.get(requiredFields[2]).getAsInt());
    }

    @JsonParseChecker
    public static boolean isParseableFromJson(JsonObject jsonObject) {
        return (jsonObject.has("name") && jsonObject.has("barrelLength"));
    }

    @Override
    public String getLevel() {
        return "For hunting";
    }

    @Override
    public String getCard() {
        return String.format("ShotGun %s - %d barrel length", super.getName(), getBarrelLength());
    }

    @Override
    public String getYears() {
        return String.format("Years in use: %d", getYearsInUse());
    }
}
