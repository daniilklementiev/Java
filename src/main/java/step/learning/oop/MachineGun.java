package step.learning.oop;

import com.google.gson.JsonObject;

@Serializable
public class MachineGun extends Weapon implements Automatic, Classified{
    @Override
    public String getLevel() {
        return "For military";
    }

    MachineGun(String name, double firerate) {
        super.setName(name);
        this.setFireRate(firerate);
    }
    private double fireRate;
    public double getFireRate() {
        return fireRate;
    }

    public void setFireRate(double firerate) {
        fireRate = firerate;
    }
    @Override
    public String getCard() {
        return String.format("Machine Gun %s - %.1f bullets per minute", super.getName(), getFireRate());
    }

    @JsonFactory
    public static MachineGun fromJson(JsonObject jsonObject){
        String[] requiredFields = {"name", "fireRate"};
        for(String field : requiredFields) {
            if(!jsonObject.has(field)) {
                throw new IllegalArgumentException(String.format("Missing required field '%s'", field));
            }
        }
        return new MachineGun(jsonObject.get(requiredFields[0]).getAsString(), jsonObject.get(requiredFields[1]).getAsDouble());
    }

    @JsonParseChecker
    public static boolean isParseableFromJson(JsonObject jsonObject) {
        return (jsonObject.has("name") && jsonObject.has("fireRate"));
    }
}
