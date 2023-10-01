package step.learning.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class OopDemo {
    public void run() {
        Armory armory = new Armory();
        armory.load();
        armory.printAll();
    }
    public void run3() {
        // доступ до ресурсів, без-типова робота з JSON
        String resourceName = "colt.json" ;
        try (InputStreamReader reader = new InputStreamReader(
                Objects.requireNonNull(
                    this.getClass()
                        .getClassLoader()
                        .getResourceAsStream( resourceName ) ) ) )
        {
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            Weapon weapon = null;
            if(jsonObject.has("Cartridge")) {
                weapon = new Gun(jsonObject.get("name").getAsString(), jsonObject.get("Cartridge").getAsInt());
            }
            else if (jsonObject.has("fireRate")) {
                weapon = new MachineGun(jsonObject.get("name").getAsString(), jsonObject.get("fireRate").getAsDouble());
            }
            else if (jsonObject.has("caliber")) {
                weapon = new Rifle(jsonObject.get("name").getAsString(), jsonObject.get("caliber").getAsFloat());
            }
            else {
                System.err.println("Weapon type unrecognized");
            }
            if(weapon != null) {
                System.out.println(weapon.getCard());
            }
        }
        catch( IOException ex ) {
            System.err.println( "IO error: " + ex.getMessage() ) ;
        }
        catch( NullPointerException ignored ) {
             System.err.printf( "Resource '%s' not found %n", resourceName ) ;
        }
    }
    public void run2() {
        // Gson - библиотека классов для работы с JSON
        String jsonString = "{\"name\":\"Colt Defender\", \"Cartridge\":8}";
        Gson gson = new Gson();
        Gun gun = gson.fromJson(jsonString, Gun.class);
        System.out.println(gun.getCard());
        System.out.println(gson.toJson(gun));

        Gson gson2 = new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .setDateFormat("dd.MM.yyyy")
                .create();
        System.out.println(gson2.toJson(gun));
    }
    public void run1() {
        Armory armory = new Armory();
        Weapon weapon = new Weapon() {
            @Override
            public String getCard() {
                return String.format("Weapon %s", getName());
            }
        };
        armory.addWeapon(new Gun("Makarov", 9));
        armory.addWeapon(new MachineGun("KM-7,62", 650));
        armory.addWeapon(new Rifle("L96A1", 7.62f));
        armory.addWeapon(new Gun("Colt Defender", 8));
        armory.addWeapon(new MachineGun("M-16", 700));
        armory.addWeapon(new Rifle("AK-47", 7.62f));
        armory.addWeapon(new Rifle("Dragunov", 7.62f));
        armory.printAll();
        System.out.println("--------------------");
        armory.printAutomatic();
        System.out.println("--------------------");
        armory.printNonAutomatic();
        System.out.println("--------------------");
        armory.printClassified();
        System.out.println("--------------------");
        armory.printNonClassified();

        armory.save();
    }
}
