package martin;

import java.util.Random;

public class Main2 {
    public interface DamageStrategy {
        int calculateDamage(int baseDamage, int level);
    }

    public static class SwordDamageStrategy implements DamageStrategy {
        @Override
        public int calculateDamage(int baseDamage, int level) {
            return baseDamage + (level * 5);
        }
    }

    public static class BowDamageStrategy implements DamageStrategy {
        @Override
        public int calculateDamage(int baseDamage, int level) {
            return baseDamage + (level * 3);
        }
    }

    public static class AxeDamageStrategy implements DamageStrategy {
        @Override
        public int calculateDamage(int baseDamage, int level) {
            return baseDamage + (level * 4);
        }
    }

    public static class KatanaDamageStrategy implements DamageStrategy {
        @Override
        public int calculateDamage(int baseDamage, int level) {
            return baseDamage + (level * 7);
        }
    }

    public static class Weapon {
        private String type;
        private int baseDamage;
        private int durability;
        private boolean broken;
        private DamageStrategy damageStrategy;

        public Weapon(String type, int baseDamage, int durability, DamageStrategy damageStrategy) {
            this.type = type;
            this.baseDamage = baseDamage;
            this.durability = durability;
            this.broken = false;
            this.damageStrategy = damageStrategy;
        }

        public String getType() {
            return type;
        }

        public int getBaseDamage() {
            return baseDamage;
        }

        public int getDurability() {
            return durability;
        }

        public boolean isBroken() {
            return broken;
        }

        public int calculateDamage(int level) {
            return damageStrategy.calculateDamage(baseDamage, level);
        }

        public void useWeapon() {
            if (broken) {
                System.out.println("El arma ya está rota y no se puede usar");
                return;
            }
            durability = durability - 5;
            System.out.println("Durabilidad restante: " + durability);

            if (durability <= 0) {
                durability = 0;
                broken = true;
                System.out.println("¡El arma se ha roto!");
            }
        }
    }

    public static class DamageCalculator {
        private Random random = new Random();

        public int calculateDamage(Weapon weapon, int level) {
            if (weapon.isBroken()) {
                System.out.println("No puedes atacar porque el arma está rota");
                return 0;
            }
            
            int damage = weapon.calculateDamage(level);
            int probabilidad = random.nextInt(10);

            if (probabilidad <= 3) {
                damage = damage * 2;
                System.out.println("¡¡¡GOLPE CRÍTICO " + weapon.getType() + " !!!");
            }
            weapon.useWeapon();
            return damage;
        }
    }

    public static class Juego {
        public static void main(String[] args) {
            Weapon sword = new Weapon("espada",50,60,new SwordDamageStrategy());
            Weapon bow = new Weapon("arco",30,40,new BowDamageStrategy());
            Weapon axe = new Weapon("hacha",40,50,new AxeDamageStrategy());
            Weapon katana = new Weapon("katana",80,80,new KatanaDamageStrategy());

            DamageCalculator calculator = new DamageCalculator();
            for (int i = 1; i <= 20; i++) {
                System.out.println("\nAtaque " + i);
                int swordDamage = calculator.calculateDamage(sword, 7);
                int bowDamage = calculator.calculateDamage(bow, 3);
                int axeDamage = calculator.calculateDamage(axe, 6);
                int katanaDamage = calculator.calculateDamage(katana, 9);
                System.out.println("Daño de espada: " + swordDamage);
                System.out.println("Daño de arco: " + bowDamage);
                System.out.println("Daño de hacha: " + axeDamage);
                System.out.println("Daño de katana: " + katanaDamage);

                if (sword.isBroken()) {
                    System.out.println("La espada se rompió");
                }
                if (bow.isBroken()) {
                    System.out.println("El arco se rompió");
                }
                if (axe.isBroken()) {
                    System.out.println("El hacha se rompió");
                }
                if (katana.isBroken()) {
                    System.out.println("La katana se rompió");
                }

                if (sword.isBroken() && bow.isBroken() && axe.isBroken() && katana.isBroken()) {
                    System.out.println("Todas las armas están rotas");
                    break;
                }
            }
        }
    }
}




