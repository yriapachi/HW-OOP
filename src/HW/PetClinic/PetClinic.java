package HW.PetClinic;

import java.util.Scanner;
import java.util.ArrayList;

public class PetClinic {
    static ArrayList<Pet> pets = new ArrayList<>();


    public static void printPets() {
        if (pets.isEmpty()) {
            System.out.println("No pets available.");
        } else {
            for (Pet pet : pets) {
                System.out.println(pet);
            }
        }
    }


    public static void getPet() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Give a type of pet: ");
        String type = sc.nextLine();
        boolean found = false;
        for (Pet pet : pets) {
            if (pet.getType().equals(type)) {
                System.out.println(pet);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No pets of that type found.");
        }
    }


    public static void main(String[] args) {
        // Adding initial pets
        pets.add(new Pet("Oliver", "Cat"));
        pets.add(new Pet("Pluto", "Dog"));
        pets.add(new Pet("Microwave", "Cat"));


        PetList.addPet();


        getPet();
    }
}

