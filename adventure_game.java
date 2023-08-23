/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.adventure_game_text_based;

/**
 *
 * @author JACOB ARPAN
 */

import java.util.Scanner;
import java.util.Random;


public class Adventure_Game_Text_Based {

    public static void main(String[] args) {
        
        //System objects
        Scanner in = new Scanner(System.in);
        Random rand = new Random();
            
        //Game variables
        String[] enemies = {"Skeleton", "Zombie", "Warrior", "Assassin"};   //enemies
        int maxEnemyHealth = 75;        // maximum enemy health
        int enemyAttackDamage = 25;     // maximum damage enemy can get
        
        // Player Variables
        int health = 100;               // health of the player
        int attackDamage = 50;          // maximum damage player can do to enemy
        int numHealthPotions = 3;       // no.of times refill of health
        int healthPotionHealAmount = 30;
        int healthPotionDropChance = 50; //percentage
        
        boolean running = true;     // game running condition
        
        System.out.println("Welcome to the Text Adventure Game");
        
        GAME:
        while(running){
            System.out.println("----------------------------------------------");
            
            int enemyHealth = rand.nextInt(maxEnemyHealth);
            String enemy = enemies[rand.nextInt(enemies.length)];
            
            System.out.println("\t# "+enemy+" has appeared!#\n");
            
            while(enemyHealth > 0){
                System.out.println("\tYour HP: "+health);
                System.out.println("\t"+enemy+"'s HP: "+enemyHealth);
                System.out.println("\n\tWhat would you like to do?");
                System.out.println("\t1.Attack!!");
                System.out.println("\t2.Drink Health Potion");
                System.out.println("\t3.Run!");
                
                String input = in.nextLine();
                
                if(input.equals("1")){
                    int damageDealt = rand.nextInt(attackDamage);
                    int damageTaken = rand.nextInt(enemyAttackDamage);
                    
                    enemyHealth-=damageDealt;
                    health-=damageTaken;
                    
                    System.out.println("\t> You strike the "+enemy+" fro "+damageDealt+" damage.");
                    System.out.println("\t> You receive "+damageTaken+" in retaliation ");
                    
                    if(health<1){
                        System.out.println("\t You have taken too much damage, you are too weak to go on!!");
                        break;
                    }
                }
                else if(input.equals("2")){
                    if(numHealthPotions>0){
                        health+=healthPotionHealAmount;
                        numHealthPotions--;
                        System.out.println("\t> You drink a health potion, healing yourself for "+ healthPotionHealAmount+"."
                        +"\n\t> You now have "+health+" HP."
                        +"\n\t> You have "+numHealthPotions+" health potions left.\n");
                    }
                    else{
                        System.out.println("\t> You have no health potions left! Defeat enemies for a chance to get one!");
                    }
                }
                else if(input.equals("3")){
                    System.out.println("\t You run away from the "+enemy+"!");
                    continue GAME;
                }
                else{
                    System.out.println("\t Invalid command!");
                }
            }
            
            if(health<1){
                System.out.println("You limp out of the game, weak from battle.");
                break;
            }
            
            System.out.println("----------------------------------------------");
            System.out.println(" # "+enemy+" was defeated! #");
            System.out.println(" # You have "+health+" HP left. #");
            if(rand.nextInt(100) <= healthPotionDropChance){
                numHealthPotions++;
                System.out.println(" # The "+enemy+" dropped a health potion! #");
                System.out.println("# You now have "+numHealthPotions+" health potion(s). #");
            }
            System.out.println("----------------------------------------------");
            System.out.println("What would you like to do?");
            System.out.println("1.Continue Fighting");
            System.out.println("2.Exit game");
            
            String input = in.nextLine();
            
            while(!input.equals("1") && !input.equals("2")){
                System.out.println("Invalid Command!\n");
                System.out.println("Enter the valid command\n");
                input = in.nextLine();
            }
            
            if(input.equals("1")){
                System.out.println("You continue on your adventure!");
            }
            else if(input.equals("2")){
                System.out.println("You exit the game");
                break;
            }
        }
        System.out.println("######################");
        System.out.println("# THANKS FOR PLAYING #");
        System.out.println("######################");
    }
}
