package HW.PetClinic;

import java.util.ArrayList;
import java.util.Scanner;

public class PetList {


    private static ArrayList<Pet> pets = new ArrayList<>();

    public static void addPet() {

        pets.add(new Pet("Oliver", "cat"));
        pets.add(new Pet("Pluto", "dog"));
        pets.add(new Pet("Microwave", "cat"));

        Scanner scanner = new Scanner(System.in);

        System.out.println("Add pet? y/n");
        String input = scanner.nextLine();

        if (input.equals("y")) {
            String choice;
            do {

                String name, type;

                System.out.println("Enter the name of the pet: ");
                name = scanner.nextLine();

                System.out.println("Enter the type of the pet: ");
                type = scanner.nextLine();


                pets.add(new Pet(name, type));

                System.out.println("Would you like to enter another pet? (Yes/No)");
                choice = scanner.nextLine();

            } while (choice.equals("Yes"));
        }

        System.out.println("List of pets:");
        for (Pet pet : pets) {
            System.out.println(pet.getName() + " - " + pet.getType());
        }
    }

    public static void main(String[] args) {
        addPet();
    }
}

