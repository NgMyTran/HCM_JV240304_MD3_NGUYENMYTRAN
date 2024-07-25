package run;
import entity.Laptop;
import entity.LaptopType;
import java.util.Scanner;
public class LaptopManagement {
    public static Laptop [] laptops;
    public static LaptopType [] laptopTypes;
    private static int count = 0;

    //------------MAIN------------
    public static void main(String[] args) {
        while (true){
            System.out.println(
                    "******************LAPTOP-MANAGEMENT******************\n"+
                            "1. Quản lý loại laptop\n" +
                            "2. Quản lý laptop\n" +
                            "3. Thoát\n" +
                            "Lựa chọn của bạn:");
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
                    "***************LAPTOP_TYPE-MENU***************\n" +
                            "1. Hiển thị danh sách các loại laptop \n" +
                            "2. Thêm mới loại laptop \n" +
                            "3. Cập nhật thông tin loại laptop \n" +
                            "4. Xóa loại Laptop \n" +
                            "5. Thoát\n" +
                            "Lựa chọn của bạn:");
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
                    deleteLaptopType( scanner);
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
        if (count == 0 || laptopTypes == null) {
            System.out.println("Chưa có loại laptop nào.");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println("---------- Loại laptop " + (i + 1) + " ----------");
            laptopTypes[i].displayData();
            System.out.println("---------------------------------");
        }
    }
    public static void addLaptopType(Scanner scanner) {
        if (laptopTypes == null) {
            laptopTypes = new LaptopType[5];
        }
        if (count == laptopTypes.length) {
            // If array is full, resize it
            LaptopType[] newArray = new LaptopType[laptopTypes.length + 5];
            System.arraycopy(laptopTypes, 0, newArray, 0, laptopTypes.length);
            laptopTypes = newArray;
        }
        LaptopType laptopType = LaptopType.inputData();
        if (laptopType != null) {
            laptopTypes[count++] = laptopType;
            System.out.println("Loại laptop đã được thêm vào danh sách.");
        } else {
            System.out.println("Không thể thêm loại laptop do dữ liệu không hợp lệ.");
        }
    }
    public static void updateLaptopType(Scanner scanner) {
        if (count == 0 || laptopTypes == null) {
            System.out.println("Chưa có loại laptop nào để cập nhật.");
            return;
        }
        System.out.print("Nhập mã loại laptop cần cập nhật: ");
        int id = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < count; i++) {
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
        System.out.println("Không tìm thấy loại laptop với mã số này.");
    }
    public static void deleteLaptopType(Scanner scanner) {
        if (count == 0 || laptopTypes == null) {
            System.out.println("Chưa có loại laptop nào.");
            return;
        }
        System.out.print("Nhập mã laptop cần xóa: ");
        int id = Integer.parseInt(scanner.nextLine());
        int deleteIndex = -1;
        for (int i = 0; i < count; i++) {
            if (laptopTypes[i].getTypeId() == id) {
                deleteIndex = i;
                break;
            }
        }
        if (deleteIndex == -1) {
            System.out.println("Không tìm thấy mã laptop để xóa.");
            return;
        }
        for (int i = deleteIndex; i < count - 1; i++) {
            laptopTypes[i] = laptopTypes[i + 1];
        }
        count--;
        for (int i = 0; i < count; i++) {
            laptopTypes[i].setTypeId(i + 1);
        }
        LaptopType.next = count + 1;
        System.out.println("Xóa thành công!");
    }
    //------------END LAPTOP_TYPE-MENU------------


    //------------LAPTOP-MENU------------
    private static void laptopMenu() {
        Scanner sc = new Scanner(System.in);

        while (true){
            System.out.println(
                    "***************LAPTOP_TYPE-MENU***************\n"+
                            "1. Danh sách Laptop \n" +
                            "2. Thêm mới Laptop \n" +
                            "3. Cập nhật thông tin Laptop \n" +
                            "4. Xóa Laptop \n" +
                            "5. Thống kê số lượng laptop theo từng loại \n" +
                            "6. Thoát\n" +
                            "Lựa chọn của bạn:");
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
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }
    public static void displayLaptop() {
        if (count == 0 || laptops == null) {
            System.out.println("Chưa có laptop nào.");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println("---------- Laptop " + (i + 1) + " ----------");
            laptops[i].displayData();
            System.out.println("---------------------------------");
        }
    }
    public static void addLaptop(Scanner scanner) {
        if (laptops == null) {
            laptops = new Laptop[5];
        }
        if (count == laptops.length) {
            Laptop[] newArray = new Laptop[laptops.length + 5];
            System.arraycopy(laptops, 0, newArray, 0, laptops.length);
            laptops = newArray;
        }
        Laptop laptop= Laptop.inputData();
        if (laptop != null) {
            laptops[count++] = laptop;
            System.out.println("Laptop đã được thêm vào danh sách.");
        } else {
            System.out.println("Không thể thêm laptop do dữ liệu không hợp lệ.");
        }
    }
    public static void updateLaptop(Scanner scanner) {
        if (count == 0 || laptops == null) {
            System.out.println("Chưa có laptop nào để cập nhật.");
            return;
        }
        System.out.print("Nhập mã laptop cần cập nhật: ");
        String id = scanner.nextLine();
        for (int i = 0; i < count; i++) {
            if (laptops[i].getLaptopId().equals(id)) {
                System.out.println("Thông tin laptop hiện tại:");
                laptops[i].displayData();
                System.out.println("Nhập thông tin mới:");
                laptops[i].inputData();
                System.out.println("Cập nhật thông tin thành công.");
                laptops[i].displayData();
                return;
            }
        }
        System.out.println("Không tìm thấy laptop với mã số này.");
    }
    public static void deleteLaptop(Scanner scanner) {
        if (count == 0 || laptops == null) {
            System.out.println("Chưa có laptop nào.");
            return;
        }
        System.out.print("Nhập mã laptop cần xóa: ");
        String id = scanner.nextLine();
        int deleteIndex = -1;
        for (int i = 0; i < count; i++) {
            if (laptops[i].getLaptopId().equals(id)) {
                deleteIndex = i;
                break;
            }
        }
        if (deleteIndex == -1) {
            System.out.println("Không tìm thấy mã laptop để xóa.");
            return;
        }
        for (int i = deleteIndex; i < count - 1; i++) {
            laptops[i] = laptops[i + 1];
        }
        count--;
        System.out.println("Xóa thành công!");
    }
    public static void summary() {
        if (count == 0 || laptops == null) {
            System.out.println("Chưa có laptop nào.");
            return;
        }
        int[] typeCounts = new int[count];
        for (int i = 0; i < count; i++) {
            typeCounts[laptops[i].typeId.getTypeId() - 1]++;
        }
        for (int i = 0; i < count; i++) {
            System.out.println("Loại laptop " + (i + 1) + ": " + typeCounts[i] + " laptop(s)");
        }
    }
    //------------END LAPTOP-MENU------------

}
