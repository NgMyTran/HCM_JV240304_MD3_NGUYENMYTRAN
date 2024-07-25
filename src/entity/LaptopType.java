package entity;

import java.util.Scanner;

public class LaptopType {
    private int typeId;
    public static int next = 1;
    private String typeName, description;
    private boolean isDeleted;

    public LaptopType() {}

    public LaptopType(String typeName, String description) {
        this.typeId = next++;
        this.typeName = typeName;
        this.description = description;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public static LaptopType inputData() {
        Scanner sc = new Scanner(System.in);
        String typeName, description;
        while (true) {
            System.out.print("Enter Type Name: ");
            typeName = sc.nextLine().trim();
            if (!typeName.isEmpty()) {
                break;
            }
            System.out.println("Type Name cannot be blank. Please enter a valid Type Name.");
        }
        while (true) {
            System.out.print("Enter Description: ");
            description = sc.nextLine().trim();
            if (!description.isEmpty()) {
                break;
            }
            System.out.println("Description cannot be blank. Please enter a valid Description.");
        }
        return new LaptopType(typeName, description);
    }


    public void updateData() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Type Name: ");
        this.typeName = sc.nextLine();

        System.out.print("Enter Description: ");
        this.description = sc.nextLine();
    }


    public char displayData() {
        System.out.printf("Type ID: %d | Type Name: %s | Description: %s | isDelete: %s%n" + '\'', typeId, typeName, description, isDeleted);
        return 0;
    }

    @Override
    public String toString() {
        return "LaptopType {" +
                "typeId=" + typeId +
                "| typeName='" + typeName + '\'' +
                '}';

    }
}
