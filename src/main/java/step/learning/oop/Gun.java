package step.learning.oop;

import com.google.gson.JsonObject;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Serializable
public class Gun extends Weapon implements Classified/*, Used*/{
    @Required
    private int Cartridge;

    private int _yearsInUse;

    public Gun(String name, int cartridge, int yearsInUse) {
        super.setName(name);
        this.setCartridge(cartridge);
        this._yearsInUse = yearsInUse;
    }
    public void set_yearsInUse(int _yearsInUse) {
        this._yearsInUse = _yearsInUse;
    }
    public int getCartridge() {
        return Cartridge;
    }
    public void setCartridge(int cartridge) {
        Cartridge = cartridge;
    }
    @Override
    public String getCard() {
        return String.format("Gun %s - %d bullets"/*%d years in use*/, super.getName(), getCartridge()/*, getYears()*/);
    }

    @Override
    public String getLevel() {
        return "For civil";
    }


    @JsonFactory
    public static Gun fromJson(JsonObject jsonObject) {
        String[] requiredFields = {"name", "Cartridge", "yearsInUse"};
        for(String field : requiredFields) {
            if(!jsonObject.has(field)) {
                throw new IllegalArgumentException(String.format("Missing required field '%s'", field));
            }
        }
        return new Gun(jsonObject.get(requiredFields[0]).getAsString(), jsonObject.get(requiredFields[1]).getAsInt(), jsonObject.get(requiredFields[2]).getAsInt());
    }

    @JsonParseChecker
    public static boolean isJsonParseableFrom(JsonObject jsonObject) {
        return Stream.concat(
                    Arrays.stream( Gun.class.getDeclaredFields() ),
                    Arrays.stream( Gun.class.getSuperclass().getDeclaredFields() ) )
                        .filter( field-> field.isAnnotationPresent ( Required.class ) )
                        .allMatch( field -> jsonObject.has(field.getName()));
    }

//    @Override
//    public int getYears() {
//        return _yearsInUse;
//    }
}
