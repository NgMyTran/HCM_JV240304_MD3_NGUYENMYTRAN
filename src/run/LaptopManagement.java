package run;
//import entity.Laptop;

import entity.Laptop;
import entity.LaptopType;

import java.util.Scanner;

public class LaptopManagement {
    private static int sizeType;
    private static int sizeLap;
    public static LaptopType[] laptopTypes = new LaptopType[sizeType];
    public static Laptop[] laptops = new Laptop[sizeLap];


    //------------MAIN------------
    public static void main(String[] args) {
        management();
    }

    private static void management() {
        while (true) {
            System.out.println(
                    "╔════════════════════════════════════╗\n" +
                    "║          LAPTOP-MANAGEMENT         ║\n" +
                    "╠════════════════════════════════════╣\n" +
                    "║  1. Quản lý loại laptop            ║\n" +
                    "║  2. Quản lý laptop                 ║\n" +
                    "║  3. Thoát                          ║\n" +
                    "║  Lựa chọn của bạn:                 ║\n" +
                    "╚════════════════════════════════════╝\n");
            Scanner sc = new Scanner(System.in);
            System.out.println("Nhập số vào để thực hiện hành một trong những hành động trên ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    laptopTypeMenu();
                    break;
                case 2:
                    laptopMenu();
                    break;
                case 3:
                    System.out.println("Thoát chương trình");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
            if (choice == 3) {
                break;
            }
        }

    }
    //------------END MAIN------------


    //------------LAPTOP_TYPE-MENU------------
    private static void laptopTypeMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(
                            "╔═════════════════════════════════════════╗\n" +
                            "║             LAPTOP_TYPE-MENU            ║\n" +
                            "╠═════════════════════════════════════════╣\n" +
                            "║  1. Hiển thị danh sách các loại laptop  ║\n" +
                            "║  2. Thêm mới loại laptop                ║\n" +
                            "║  3. Cập nhật thông tin loại laptop      ║\n" +
                            "║  4. Xóa loại Laptop                     ║\n" +
                            "║  5. Thoát                               ║\n" +
                            "║  Lựa chọn của bạn:                      ║\n" +
                            "╚═════════════════════════════════════════╝\n");
            System.out.println("Nhập số vào để thực hiện hành một trong những hành động trên ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    displayLaptopTypes();
                    break;
                case 2:
                    addLaptopType(scanner);
                    break;
                case 3:
                    updateLaptopType(scanner);
                    break;
                case 4:
                    deleteLaptopType(scanner);
                    break;
                case 5:
                    System.out.println("Thoát chương trình");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    public static void displayLaptopTypes() {
        if (sizeType == 0 || laptopTypes == null) {
            System.out.println("Chưa có loại laptop nào.");
            return;
        }
        for (LaptopType type : laptopTypes) {
            if (type != null && !type.isDeleted()) {
                System.out.println(type.displayData());  // Display the type information
                boolean hasLaptops = false;
                for (Laptop laptop : laptops) {
                    if (laptop != null && laptop.getTypeId() != null && laptop.getTypeId().equals(type)) {
                        // Display the laptop information regardless of isDeleted status
                        System.out.println(laptop.displayData());
                        hasLaptops = true;
                    }
                }
                if (!hasLaptops) {
                    System.out.println("Không có laptop nào thuộc loại này.");
                }
                System.out.println();
            }
        }
    }

    public static void addLaptopType(Scanner scanner) {
        LaptopType newType = LaptopType.inputData();
        for (LaptopType laptopType : laptopTypes) {
            if (laptopType.getTypeName().equalsIgnoreCase(newType.getTypeName()) && laptopType.isDeleted()) {
                laptopType.setDeleted(false);
                laptopType.setDescription(newType.getDescription());
                System.out.println("Loại laptop đã được khôi phục.");
                return;
            }
        }
        LaptopType[] newLaptopTypeArray = new LaptopType[sizeType + 1];
        if (sizeType >= 0) System.arraycopy(laptopTypes, 0, newLaptopTypeArray, 0, sizeType);
        newLaptopTypeArray[newLaptopTypeArray.length - 1] = newType;
        laptopTypes = newLaptopTypeArray;
        sizeType++;
    }

    public static void updateLaptopType(Scanner scanner) {
        if (laptopTypes.length == 0) {
            System.err.println("Chưa có loại laptop nào để cập nhật.");
            return;
        }
        System.out.print("Nhập mã loại laptop cần cập nhật: ");
        int id = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < sizeType; i++) {
            if (laptopTypes[i].getTypeId() == id) {
                System.out.println("Thông tin loại laptop hiện tại:");
                laptopTypes[i].displayData();
                System.out.println("Nhập thông tin mới:");
                laptopTypes[i].updateData();
                System.out.println("Cập nhật thông tin thành công.");
                laptopTypes[i].displayData();
                return;
            }
        }
        System.err.println("Không tìm thấy loại laptop với mã số này.");
    }

    public static void deleteLaptopType(Scanner scanner) {
        if (sizeType == 0 || laptopTypes == null) {
            System.err.println("Chưa có loại laptop nào để xóa.");
            return;
        }
        System.out.print("Nhập mã loại laptop cần xóa: ");
        int id = Integer.parseInt(scanner.nextLine());

        // Check if the type is used by any laptop
        boolean isTypeUsed = false;
        for (Laptop laptop : laptops) {
            if (laptop.getTypeId().getTypeId() == id && !laptop.isDeleted()) {
                isTypeUsed = true;
                break;
            }
        }
        if (isTypeUsed) {
            System.err.println("Không thể xóa loại " + id + " vì nó đang có laptop.");
            return;
        }
        for (int i = 0; i < sizeType; i++) {
            if (laptopTypes[i].getTypeId() == id && !laptopTypes[i].isDeleted()) {
                laptopTypes[i].setDeleted(true);
                System.out.println("Xóa thành công!");
                return;
            }
        }
        System.err.println("Không tìm thấy mã loại laptop để xóa.");
    }
    //------------END LAPTOP_TYPE-MENU------------


    //------------LAPTOP-MENU------------
    private static void laptopMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println(
                            "╔════════════════════════════════════════════╗\n" +
                            "║                 LAPTOP-MENU                ║\n" +
                            "╠════════════════════════════════════════════╣\n" +
                            "║  1. Hiển thị danh sách laptop              ║\n" +
                            "║  2. Thêm mới laptop                        ║\n" +
                            "║  3. Cập nhật thông tin laptop              ║\n" +
                            "║  4. Xóa Laptop                             ║\n" +
                            "║  5. Thống kê số lượng laptop theo từng loại║\n" +
                            "║  6. Thoát                                  ║\n" +
                            "║  Lựa chọn của bạn:                         ║\n" +
                            "╚════════════════════════════════════════════╝\n");
            System.out.println("Nhập số vào để thực hiện hành một trong những hành động trên ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    displayLaptop();
                    break;
                case 2:
                    addLaptop(sc);
                    break;
                case 3:
                    updateLaptop(sc);
                    break;
                case 4:
                    deleteLaptop(sc);
                    break;
                case 5:
                    summary();
                    break;
                case 6:
                    System.out.println("Thoát chương trình");
                    break;
                default:
                    System.err.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
            if (choice == 6) {
                break;
            }
        }
    }

    public static void displayLaptop() {
        if (sizeLap == 0 || laptops == null) {
            System.err.println("Chưa có laptop nào.");
            return;
        }
        for (Laptop laptop : laptops) {
            System.out.println(laptop);
        }
    }

    public static void addLaptop(Scanner scanner) {
        if (sizeType == 0 || laptopTypes == null) {
            System.err.println("Chưa có loại laptop nào. Vui lòng thêm loại laptop trước.");
            laptopTypeMenu();
            return;
        }
        Laptop newLaptop = Laptop.inputData();
        if (newLaptop == null) {
            return;
        }
        for (Laptop laptop : laptops) {
            if (laptop.getLaptopName().equalsIgnoreCase(newLaptop.getLaptopName()) && laptop.isDeleted()) {
                laptop.setDeleted(false);
                laptop.setDescription(newLaptop.getDescription());
                System.out.println("Laptop đã được khôi phục.");
                return;
            }
        }
        Laptop[] newLaptopArray = new Laptop[sizeLap + 1];
        System.arraycopy(laptops, 0, newLaptopArray, 0, sizeLap);
        newLaptopArray[sizeLap] = newLaptop;
        laptops = newLaptopArray;
        sizeLap++;
    }

    public static void updateLaptop(Scanner scanner) {
        if (laptops.length == 0) {
            System.err.println("Chưa có loại laptop nào để cập nhật.");
            return;
        }
        System.out.print("Nhập mã loại laptop cần cập nhật: ");
        String id = scanner.nextLine();
        for (int i = 0; i < sizeLap; i++) {
            if (laptops[i].getLaptopId().equals(id)) {
                System.out.println("Thông tin loại laptop hiện tại:");
                laptops[i].displayData();
                System.out.println("Nhập thông tin mới:");
                laptops[i].updateData();
                System.out.println("Cập nhật thông tin thành công.");
                laptops[i].displayData();
                return;
            }
        }
        System.err.println("Không tìm thấy loại laptop với mã số này.");
    }

    public static void deleteLaptop(Scanner scanner) {
        if (sizeLap == 0 || laptops == null) {
            System.err.println("Chưa có laptop nào để xóa.");
            return;
        }
        System.out.print("Nhập mã laptop cần xóa: ");
        String id = scanner.nextLine();
        for (Laptop laptop : laptops) {
            if (laptop.getLaptopId().equals(id) && !laptop.isDeleted()) {
                laptop.setDeleted(true);
                System.out.println("Xóa thành công!");
                return;
            }
        }
        System.err.println("Không tìm thấy mã laptop để xóa.");
    }

    public static void summary() {
        if (sizeLap == 0 || laptops == null) {
            System.err.println("Chưa có laptop nào.");
            return;
        }

        // Create an array to count laptops per type
        int[] typeCounts = new int[sizeType];
        for (int i = 0; i < sizeType; i++) {
            typeCounts[i] = 0;
        }

        // Count laptops by type
        for (Laptop laptop : laptops) {
            if (!laptop.isDeleted()) {
                LaptopType type = laptop.getTypeId();
                // Find the index of the laptop type in the laptopTypes array
                for (int i = 0; i < sizeType; i++) {
                    if (laptopTypes[i].equals(type)) {
                        typeCounts[i]++;
                        break;
                    }
                }
            }
        }
        System.out.println("Số lượng laptop theo từng loại:");
        for (int i = 0; i < sizeType; i++) {
            if (!laptopTypes[i].isDeleted()) {
                System.out.println("Type " + laptopTypes[i].getTypeName() + ": " + typeCounts[i] + " laptop(s)");
            }
        }
    }
    //------------END LAPTOP-MENU------------

}
