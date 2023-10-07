package step.learning.oop;

import com.google.gson.JsonObject;

@Serializable
public class MachineGun extends Weapon implements Automatic, Classified, Used{
    @Override
    public String getLevel() {
        return "For military";
    }

    @Required
    private int yearsInUse;
    public int getYearsInUse() {
        return yearsInUse;
    }

    MachineGun(String name, double firerate, int yearsInUse) {
        super.setName(name);
        this.setFireRate(firerate);
        this.yearsInUse = yearsInUse;
    }
    public String getYears() {
        return getYearsInUse() + " years in use";
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
        String[] requiredFields = {"name", "fireRate", "yearsInUse"};
        for(String field : requiredFields) {
            if(!jsonObject.has(field)) {
                throw new IllegalArgumentException(String.format("Missing required field '%s'", field));
            }
        }
        return new MachineGun(jsonObject.get(requiredFields[0]).getAsString(), jsonObject.get(requiredFields[1]).getAsDouble(), jsonObject.get(requiredFields[2]).getAsInt());
    }

    @JsonParseChecker
    public static boolean isParseableFromJson(JsonObject jsonObject) {
        return (jsonObject.has("name") && jsonObject.has("fireRate"));
    }
}
