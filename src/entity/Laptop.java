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
    private boolean isDeleted;
//    public int typeId;
    private LaptopType typeId;

    public Laptop() {
    }
    public Laptop(String laptopName, boolean isDeleted) {
        this.laptopName = laptopName;
        this.isDeleted = isDeleted;
    }
    public Laptop(String laptopId, String laptopName, String description, int ram, double weight, LocalDate createAt, double laptopPrice, LaptopType type) {
        this.laptopId = laptopId;
        this.laptopName = laptopName;
        this.description = description;
        this.ram = ram;
        this.createAt = createAt;
        this.laptopPrice = laptopPrice;
        this.weight = weight;
        this.typeId = type;
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

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public double getLaptopPrice() {
        return laptopPrice;
    }

    public void setLaptopPrice(double laptopPrice) {
        this.laptopPrice = laptopPrice;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public LaptopType getTypeId() {
        return typeId;
    }

    public void setTypeId(LaptopType typeId) {
        this.typeId = typeId;
    }

    public static Laptop inputData() {
        Scanner sc = new Scanner(System.in);

        String laptopId;
        while (true) {
            System.out.print("Enter Laptop Id: ");
             laptopId = sc.nextLine().trim();
            if (!laptopId.isEmpty()) {
                break;
            }
            System.out.println("Laptop Name cannot be blank. Please enter a valid Type Name.");
        }

        String laptopName;
        while (true) {
            System.out.print("Enter Laptop Name: ");
             laptopName = sc.nextLine().trim();
            if (!laptopName.isEmpty()) {
                break;
            }
            System.out.println("Laptop Name cannot be blank. Please enter a valid Type Name.");
        }


        String description;
        while (true) {
            System.out.print("Enter Laptop Description: ");
            description = sc.nextLine().trim();
            if (!description.isEmpty()) {
                break;
            }
            System.out.println("Laptop Description cannot be blank. Please enter a valid Description.");
        }

        int ram;
        while (true) {
            try {
                System.out.print("Enter RAM: ");
                ram = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer for RAM.");
            }
        }

        double weight;
        while (true) {
            try {
                System.out.print("Enter Weight: ");
                weight = Double.parseDouble(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for Weight.");
            }
        }

        LocalDate createAt;
        while (true) {
            try {
                System.out.print("Enter Date (yyyy-MM-dd): ");
                createAt = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter a date in the format yyyy-MM-dd.");
            }
        }

        double laptopPrice;
        while (true) {
            try {
                System.out.print("Enter Laptop Price: ");
                laptopPrice = Double.parseDouble(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for Laptop Price.");
            }
        }

        System.out.println("Type of Laptop: ");
        System.out.println("STT | Name | Description");
        for (LaptopType laptopType : laptopTypes) {
            if (!laptopType.isDeleted()) {
                System.out.println(laptopType.getTypeId() + ". " + laptopType.getTypeName() + " | " + laptopType.getDescription());
            }
        }

        int typeId;
        while (true) {
            try {
                System.out.print("Choose a laptop type by ID: ");
                typeId = Integer.parseInt(sc.nextLine());
                boolean isValidType = false;
                for (LaptopType type : laptopTypes) {
                    if (type.getTypeId() == typeId && !type.isDeleted()) {
                        isValidType = true;
                        break;
                    }
                }
                if (!isValidType) {
                    throw new IllegalArgumentException("Invalid type ID.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer for Type ID.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        LaptopType selectedType = null;
        for (LaptopType type : laptopTypes) {
            if (type.getTypeId() == typeId && !type.isDeleted()) {
                selectedType = type;
                break;
            }
        }

        return new Laptop(laptopId, laptopName, description, ram, weight, createAt, laptopPrice, selectedType);
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

    public void updateData() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Which field would you like to update?");
        System.out.println("1. Laptop Name");
        System.out.println("2. Description");
        System.out.println("3. RAM");
        System.out.println("4. Weight");
        System.out.println("5. Date");
        System.out.println("6. Laptop Price");
        System.out.println("7. Laptop Type");
        System.out.println("8. Exit");
        System.out.print("Enter your choice (1-8): ");

        int choice = Integer.parseInt(sc.nextLine());

        switch (choice) {
            case 1:
                System.out.print("Enter new Laptop Name: ");
                this.laptopName = sc.nextLine();
                break;

            case 2:
                System.out.print("Enter new Description: ");
                this.description = sc.nextLine();
                break;

            case 3:
                System.out.print("Enter new RAM: ");
                this.ram = Integer.parseInt(sc.nextLine());
                break;

            case 4:
                System.out.print("Enter new Weight: ");
                this.weight = Double.parseDouble(sc.nextLine());
                break;

            case 5:
                System.out.print("Enter new Date (yyyy-MM-dd): ");
                this.createAt = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                break;

            case 6:
                System.out.print("Enter new Laptop Price: ");
                this.laptopPrice = Double.parseDouble(sc.nextLine());
                break;

            case 7:
                System.out.println("Type of Laptop: ");
                System.out.println("STT | Name | Description");
                for (LaptopType laptopType : laptopTypes) {
                    if (!laptopType.isDeleted()) {
                        System.out.println(laptopType.getTypeId() + ". " + laptopType.getTypeName() + " | " + laptopType.getDescription());
                    }
                }
                System.out.print("Choose a new laptop type by ID: ");
                int typeId = Integer.parseInt(sc.nextLine());
                LaptopType selectedType = null;
                for (LaptopType type : laptopTypes) {
                    if (type.getTypeId() == typeId && !type.isDeleted()) {
                        selectedType = type;
                        break;
                    }
                }
                if (selectedType != null) {
                    this.typeId = selectedType;
                } else {
                    System.out.println("Invalid type ID.");
                }
                break;

            case 8:
                System.out.println("Exiting update.");
                return;

            default:
                System.out.println("Invalid choice. No updates made.");
                break;
        }
        System.out.println("Update successful.");
    }

    public String displayData() {
        System.out.printf(
                "Laptop ID: %s | Laptop Name: %s | Description: %s | RAM: %d | Weight: %.2f | Date: %s | Price: %.2f | isDeleted: %b",
                laptopId, laptopName, description, ram, weight, createAt.toString(), laptopPrice, isDeleted
        );
        return "";
    }

    @Override
    public String toString() {
        return "Laptop [" +
                "laptopId='" + laptopId + '\'' +
                "| laptopName='" + laptopName + '\'' +
                "| description='" + description + '\'' +
                "| ram=" + ram +
                "| createAt=" + createAt +
                "| laptopPrice=" + laptopPrice +
                "| weight=" + weight +
                "| isDeleted=" + isDeleted +
                "| typeId=" + typeId +
                ']';
    }

}
