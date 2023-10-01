package step.learning.oop;

public abstract class Weapon {
    @Required
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract String getCard();
}

// Armory -----<> Weapon { Name }  *code, *num, *date, *price
//                         / | \
//       Gun { Cartridge }   |  Machine Gun { FireRate }
//                   Rifle { Caliber }
//
// String getCard() - облікова картка: назва, тип, інші хар-ки
// Д.З. Встановити склад класу Armory,
// описати клас, реалізувати необхідні поля та методи
