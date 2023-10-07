package step.learning.oop;

import com.google.gson.JsonObject;

@Serializable
public class Rifle extends Weapon implements Classified, Used {
    Rifle(String name, float caliber, int yearsInUse) {
        super.setName(name);
        this.setCaliber(caliber);
    }
    private float caliber;

    @Required
    private int yearsInUse;
    public int getYearsInUse() {
        return yearsInUse;
    }
    public String getYears() {
        return getYearsInUse() + " years in use";
    }

    public double getCaliber() {
        return caliber;
    }

    public void setCaliber(float caliber) {
        this.caliber = caliber;
    }

    @Override
    public String getCard() {
        return String.format("Rifle: %s - %.2f mm", super.getName(), getCaliber());
    }

    @Override
    public String getLevel() {
        return "For military";
    }

    @JsonFactory
    public static Rifle fromJson(JsonObject jsonObject){
        String[] requiredFields = {"name", "caliber", "yearsInUse"};
        for(String field : requiredFields) {
            if(!jsonObject.has(field)) {
                throw new IllegalArgumentException(String.format("Rifle: Missing required field '%s'", field));
            }
        }
        return new Rifle(jsonObject.get(requiredFields[0]).getAsString(), jsonObject.get(requiredFields[1]).getAsFloat(), jsonObject.get(requiredFields[2]).getAsInt());
    }

    @JsonParseChecker
    public static boolean isParseableFromJson(JsonObject jsonObject) {
        return (jsonObject.has("name") && jsonObject.has("caliber"));
    }

}
