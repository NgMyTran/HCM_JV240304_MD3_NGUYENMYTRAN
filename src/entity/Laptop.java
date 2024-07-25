package entity;

import java.time.LocalDate;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static run.LaptopManagement.laptopTypes;

public class Laptop {
    private String laptopId, laptopName, description;
    private int ram;
    private LocalDate createAt;
    private double laptopPrice, weight;
    public boolean isDeleted;
    public LaptopType typeId;

    public Laptop() {
    }

    public Laptop(String laptopId, String laptopName, String description, int ram, double laptopPrice, double weight, LaptopType typeId) {
        this.laptopId = laptopId;
        this.laptopName = laptopName;
        this.description = description;
        this.ram = ram;
        this.laptopPrice = laptopPrice;
        this.weight = weight;
        this.typeId = typeId;
    }

    public Laptop(String laptopName, String description, int ram, double weight, LocalDate createAt, double laptopPrice) {
        this.laptopName = laptopName;
        this.description = description;
        this.ram = ram;
        this.createAt = createAt;
        this.laptopPrice = laptopPrice;
    }

    public String getLaptopId() {
        return laptopId;
    }

    public void setLaptopId(String laptopId) {
        this.laptopId = laptopId;
    }

    public String getLaptopName() {
        return laptopName;
    }

    public void setLaptopName(String laptopName) {
        this.laptopName = laptopName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public static Laptop inputData() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Laptop Name: ");
        String laptopName = sc.nextLine();

        System.out.print("Enter Description: ");
        String description = sc.nextLine();

        System.out.print("Enter RAM: ");
        int ram = Integer.parseInt(sc.nextLine());

        System.out.print("Enter Weight: ");
        double weight = Double.parseDouble(sc.nextLine());

        System.out.print("Enter Date (yyyy-MM-dd): ");
        LocalDate createAt = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.print("Enter Laptop Price: ");
        double laptopPrice = Double.parseDouble(sc.nextLine());

        System.out.println("Type of Laptop: ");
        for (LaptopType laptopType : laptopTypes) {
            System.out.println(laptopType);
        }
        System.out.print("Enter Type ID: ");


        return new Laptop(laptopName, description, ram,weight,createAt,laptopPrice);
    }

    private LocalDate parseDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return null;
        }
    }

//    private LaptopType findLaptopTypeById(String typeId) {
//        for (LaptopType laptopType : laptopTypes) {
//            if (laptopType != null && laptopType.getTypeId() == Integer.parseInt(typeId)) {
//                return laptopType;
//            }
//        }
//        return null;
//    }

    public void displayData() {
        System.out.printf(
                "Laptop ID: %d | Laptop Name: %s | Description: %s | RAM: %d | Weight: %.2f | Date: %s | Price: %.2f | Type ID: %d%n",
                laptopId, laptopName, description, ram, weight, createAt.toString(), laptopPrice, typeId
        );
    }
}
