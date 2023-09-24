package step.learning.oop;

public class OopDemo {
    public void run() {
        Armory armory = new Armory();
        Weapon weapon = new Weapon() {
            @Override
            public String getCard() {
                return String.format("Weapon %s", getName());
            }
        };
        weapon.setName("AK-47");
        armory.addWeapon(weapon);

        armory.listWeapons();
    }
}
