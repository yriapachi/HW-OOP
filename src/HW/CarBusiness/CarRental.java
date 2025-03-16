package HW.CarBusiness;

import java.util.Scanner;

public class CarRental {


    public static void existingCars() {
        Car.cars.add(new Car("a1", "123ABC", 100000, false));
        Car.cars.add(new Car("a2", "456ABC", 200000, false));
        Car.cars.add(new Car("a3", "789ABC", 300000, false));
        Car.cars.add(new Car("a4", "123XYZ", 400000, false));
        Car.cars.add(new Car("a5", "456XYZ", 500000, false));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        existingCars();

        while (true) {

            System.out.println("Car Rental");
            System.out.println("1. Rent a car");
            System.out.println("2. Return a car");
            System.out.println("3. Display all cars");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                Car.rentCar();
            } else if (choice == 2) {
                Car.returnCar();
            } else if (choice == 3) {
                Car.displayCars();
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

    }
}

