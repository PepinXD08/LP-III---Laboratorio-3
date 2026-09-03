package martin;

public class Main3 {
    public static abstract class Weapon {
        protected int baseDamage;

        public Weapon(int baseDamage) {
            this.baseDamage = baseDamage;
        }
        public abstract int calculateDamage(int level);
    }

    public static class Espada extends Weapon {
        public Espada(int baseDamage) {
            super(baseDamage);
        }
        @Override
        public int calculateDamage(int level) {
            return baseDamage + (level * 5);
        }
    }

    public static class Arco extends Weapon {
        public Arco(int baseDamage) {
            super(baseDamage);
        }
        @Override
        public int calculateDamage(int level) {
            return baseDamage + (level * 3);
        }
    }

    public static class Hacha extends Weapon {
        public Hacha(int baseDamage) {
            super(baseDamage);
        }
        @Override
        public int calculateDamage(int level) {
            return baseDamage + (level * 4);
        }
    }

    public static class DamageCalculator {
        public int calculateDamage(Weapon weapon, int level) {
            return weapon.calculateDamage(level);
        }
    }

    public static void main(String[] args) {
        Weapon sword = new Espada(50);
        Weapon bow = new Arco(30);
        Weapon axe = new Hacha(40);

        DamageCalculator calculator = new DamageCalculator();

        int swordDamage = calculator.calculateDamage(sword, 10);
        int bowDamage = calculator.calculateDamage(bow, 10);
        int axeDamage = calculator.calculateDamage(axe, 10);

        System.out.println("Daño de espada: " + swordDamage);
        System.out.println("Daño de arco: " + bowDamage);
        System.out.println("Daño de hacha: " + axeDamage);
    }
}
