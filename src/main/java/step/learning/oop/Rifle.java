package step.learning.oop;

import com.google.gson.JsonObject;

@Serializable
public class Rifle extends Weapon implements Classified {
    Rifle(String name, float caliber) {
        super.setName(name);
        this.setCaliber(caliber);
    }
    private float caliber;

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
        String[] requiredFields = {"name", "caliber"};
        for(String field : requiredFields) {
            if(!jsonObject.has(field)) {
                throw new IllegalArgumentException(String.format("Rifle: Missing required field '%s'", field));
            }
        }
        return new Rifle(jsonObject.get(requiredFields[0]).getAsString(), jsonObject.get(requiredFields[1]).getAsFloat());
    }

    @JsonParseChecker
    public static boolean isParseableFromJson(JsonObject jsonObject) {
        return (jsonObject.has("name") && jsonObject.has("caliber"));
    }
}
