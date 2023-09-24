package step.learning.oop;

import java.util.ArrayList;
import java.util.List;

public class Armory {
    private List<Weapon> weapons;

    public Armory() {
        weapons = new ArrayList<>();
    }

    public void addWeapon(Weapon weapon) {
        weapons.add(weapon);
    }

    public void removeWeapon(Weapon weapon) {
        weapons.remove(weapon);
    }

    public void listWeapons() {
        for (Weapon weapon : weapons) {
            System.out.println(weapon.getCard());
        }
    }
}
