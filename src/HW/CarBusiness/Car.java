package HW.CarBusiness;

import java.util.ArrayList;
import java.util.Scanner;

public class Car {
    private String model;
    private String plateNumber;
    private int km;
    private boolean rented;
    static ArrayList<Car> cars = new ArrayList<>();


    public Car(String model, String plateNumber, int km, boolean rented) {
        this.model = model;
        this.plateNumber = plateNumber;
        this.km = km;
        this.rented = rented;
    }


    public String getModel() {
        return model;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public int getKm() {
        return km;
    }

    public boolean isRented() {
        return rented;
    }


    public static void addCar() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Add car? Y/N");
        String choice = sc.nextLine();
        if (choice.equals("Y")) {
            System.out.println("Enter Model:");
            String model = sc.nextLine();
            System.out.println("Enter Plate Number:");
            String plateNumber = sc.nextLine();
            System.out.println("Enter KM:");
            int km = sc.nextInt();
            sc.nextLine();
            cars.add(new Car(model, plateNumber, km, false));
            System.out.println("Car added!");
        }
    }

    public static boolean isAvailable(String plateNumber) {
        for (Car car : cars) {
            if (car.getPlateNumber().equals(plateNumber)) {
                return !car.isRented();
            }
        }
        return false;
    }


    public static void rentCar() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter plate number of the car you want to rent:");
        String plateNumber = sc.nextLine();

        if (isAvailable(plateNumber)) {
            for (Car car : cars) {
                if (car.getPlateNumber().equals(plateNumber)) {
                    car.rented = true;
                    System.out.println("Car rented successfully!");
                    break;
                }
            }
        } else {
            System.out.println("Car not available.");
        }
    }


    public static void returnCar() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter plate number of the car to return:");
        String givenPlateNumber = sc.nextLine();

        boolean carFound = false;
        for (Car car : cars) {
            if (car.getPlateNumber().equals(givenPlateNumber)) {
                carFound = true;
                car.rented = false;
                System.out.println("Enter new km for the car:");
                int newKm = sc.nextInt();
                car.km = newKm;
                System.out.println("Car returned successfully!");
                break;
            }
        }

        if (!carFound) {
            System.out.println("Car with that plate number not found.");
        }
    }


    public static void displayCars() {
        if (cars.isEmpty()) {
            System.out.println("No cars available.");
            return;
        }
        for (Car car : cars) {
            System.out.println("Model: " + car.getModel() + ", Plate Number: " + car.getPlateNumber() +
                    ", KM: " + car.getKm() + ", Rented: " + (car.isRented() ? "Yes" : "No"));
        }
    }

}