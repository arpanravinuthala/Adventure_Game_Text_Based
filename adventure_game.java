import java.util.Scanner;
import java.util.Random;

abstract class Character {
    protected String name;
    protected int health;
    protected int attackDamage;

    public Character(String name, int health, int attackDamage) {
        this.name = name;
        this.health = health;
        this.attackDamage = attackDamage;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void reduceHealth(int amount) {
        health -= amount;
        if (health < 0) {
            health = 0;
        }
    }

    public int getAttackDamage() {
        return attackDamage;
    }

}

class Enemy extends Character {
    public Enemy(String name, int maxHealth, int attackDamage) {
        super(name, maxHealth, attackDamage);
    }
}

class Player extends Character {
    private int numHealthPotions;

    public Player(String name, int health, int attackDamage, int numHealthPotions) {
        super(name, health, attackDamage);
        this.numHealthPotions = numHealthPotions;
    }

    public int getNumHealthPotions() {
        return numHealthPotions;
    }

    public void useHealthPotion(int amount) {
        health += amount;
        numHealthPotions--;
    }

    public void addHealthPotion() {
        numHealthPotions++;
    }
}


public class AdventureGame {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    public static void main(String[] args) {
        System.out.println("Welcome to the Text Adventure Game");

        Player player = new Player("Player",100, 50, 3);
        Enemy[] enemies = {
            new Enemy("Skeleton", 75, 25),
            new Enemy("Zombie", 75, 25),
            new Enemy("Warrior", 75, 25),
            new Enemy("Assassin", 75, 25)
        };

        boolean running = true;
        while (running) {
            Enemy enemy = enemies[random.nextInt(enemies.length)];
            System.out.println("----------------------------------------------");
            System.out.println("\t# " + enemy.getName() + " has appeared! #\n");

            while (enemy.getHealth() > 0 && player.getHealth() > 0) {
                System.out.println("\tYour HP: " + player.getHealth());
                System.out.println("\t" + enemy.getName() + "'s HP: " + enemy.getHealth());
                System.out.println("\n\tWhat would you like to do?");
                System.out.println("\t1. Attack!!");
                System.out.println("\t2. Drink Health Potion");
                System.out.println("\t3. Run!");

                String input = scanner.nextLine();

                switch (input) {
                    case "1":
                        int playerDamageDealt = random.nextInt(player.getAttackDamage());
                        int enemyDamageTaken = random.nextInt(enemy.getAttackDamage());

                        enemy.reduceHealth(playerDamageDealt);
                        player.reduceHealth(enemyDamageTaken);

                        System.out.println("\t> You strike the " + enemy.getName() + " for " + playerDamageDealt + " damage.");
                        System.out.println("\t> You receive " + enemyDamageTaken + " in retaliation.");

                        if (player.getHealth() <= 0) {
                            System.out.println("\t You have taken too much damage, you are too weak to go on!!");
                            break;
                        }
                        break;
                    case "2":
                        if (player.getNumHealthPotions() > 0) {
                            player.useHealthPotion(30);
                            System.out.println("\t> You drink a health potion, healing yourself for 30.");
                            System.out.println("\t> You now have " + player.getHealth() + " HP.");
                            System.out.println("\t> You have " + player.getNumHealthPotions() + " health potions left.\n");
                        } else {
                            System.out.println("\t> You have no health potions left! Defeat enemies for a chance to get one!");
                        }
                        break;
                    case "3":
                        System.out.println("\t You run away from the " + enemy.getName() + "!");
                        continue;
                    default:
                        System.out.println("\t Invalid command!");
                        break;
                }
            }

            if (player.getHealth() == 0) {
                System.out.println("You limp out of the game, weak from battle.");
                break;
            }

            System.out.println("----------------------------------------------");
            System.out.println(" # " + enemy.getName() + " was defeated! #");
            System.out.println(" # You have " + player.getHealth() + " HP left. #");

            if (random.nextInt(100) <= 50) {
                player.addHealthPotion();
                System.out.println(" # The " + enemy.getName() + " dropped a health potion! #");
                System.out.println("# You now have " + player.getNumHealthPotions() + " health potion(s). #");
            }

            System.out.println("----------------------------------------------");
            System.out.println("What would you like to do?");
            System.out.println("1. Continue Fighting");
            System.out.println("2. Exit game");

            String input = scanner.nextLine();

            while (!input.equals("1") && !input.equals("2")) {
                System.out.println("Invalid Command!\n");
                System.out.println("Enter the valid command\n");
                input = scanner.nextLine();
            }

            if (input.equals("2")) {
                System.out.println("You exit the game");
                break;
            }
        }

        System.out.println("######################");
        System.out.println("# THANKS FOR PLAYING #");
        System.out.println("######################");
    }
}
