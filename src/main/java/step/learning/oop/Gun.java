package step.learning.oop;

import com.google.gson.JsonObject;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Serializable
public class Gun extends Weapon implements Classified{
    @Required
    private int Cartridge;

    public Gun(String name, int cartridge) {
        super.setName(name);
        this.setCartridge(cartridge);
    }
    public int getCartridge() {
        return Cartridge;
    }
    public void setCartridge(int cartridge) {
        Cartridge = cartridge;
    }
    @Override
    public String getCard() {
        return String.format("Gun %s - %d bullets", super.getName(), getCartridge());
    }

    @Override
    public String getLevel() {
        return "For civil";
    }


    @JsonFactory
    public static Gun fromJson(JsonObject jsonObject) {
        String[] requiredFields = {"name", "Cartridge"};
        for(String field : requiredFields) {
            if(!jsonObject.has(field)) {
                throw new IllegalArgumentException(String.format("Missing required field '%s'", field));
            }
        }
        return new Gun(jsonObject.get(requiredFields[0]).getAsString(), jsonObject.get(requiredFields[1]).getAsInt());
    }

    @JsonParseChecker
    public static boolean isJsonParseableFrom(JsonObject jsonObject) {
        return Stream.concat(
                    Arrays.stream( Gun.class.getDeclaredFields() ),
                    Arrays.stream( Gun.class.getSuperclass().getDeclaredFields() ) )
                        .filter( field-> field.isAnnotationPresent ( Required.class ) )
                        .allMatch( field -> jsonObject.has(field.getName()));
    }
}
