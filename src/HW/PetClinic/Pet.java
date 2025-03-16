package HW.PetClinic;

public class Pet {
    private String name;
    private String type;


    public Pet(String name, String type) {
        this.name = name;
        this.type = type;
    }


    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String toString() {
        return "Pet{name='" + name + "', type='" + type + "'}";
    }

}
