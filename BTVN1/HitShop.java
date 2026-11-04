import java.util.ArrayList;
import java.util.Scanner;
public class HitShop {
    static ArrayList<String> names = new ArrayList<>();
    static ArrayList<Double> prices = new ArrayList<>();
    static ArrayList<Integer> quantities = new ArrayList<>();
    static void printMenu() {
        System.out.println("\nHIT SHOP");
        System.out.println("1. Them mat hang");
        System.out.println("2. Xoa mat hang");
        System.out.println("3. Xem gio hang");
        System.out.println("4. Xem tong tien");
        System.out.println("0. Thoat");
        System.out.print("\nChon chuc nang so: ");
    }
    static void addItem(String name, double price, int quantity) {
        if (name.isBlank() || price <= 0 || !Double.isFinite(price) || quantity <= 0) {
            System.out.println("Du lieu khong hop le.");
            return;
        }
        names.add(name);
        prices.add(price);
        quantities.add(quantity);
        System.out.println("Them thanh cong.");
    }
    static void removeItem(int index) {
        if (index < 0 || index >= names.size()) {
            System.out.println("Khong tim thay mat hang.");
            return;
        }
        names.remove(index);
        prices.remove(index);
        quantities.remove(index);
        System.out.println("Xoa thanh cong.");
    }
    static void printItems() {
        if (names.isEmpty()) {
            System.out.println("Gio hang trong.");
            return;
        }
        System.out.println("\nGIO HANG");
        for (int i = 0; i < names.size(); i++) {
            System.out.printf("%d. %s | Gia: %.0f | SL: %d | Thanh tien: %.0f%n",
                    i + 1, names.get(i), prices.get(i), quantities.get(i),
                    prices.get(i) * quantities.get(i));
        }
    }
    static double calculateTotal() {
        double total = 0;
        for (int i = 0; i < names.size(); i++) {
            total += prices.get(i) * quantities.get(i);
        }
        return total;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final double VAT_RATE = 0.10;
        final double SHIPPING_FEE = 20000;
        int choice = -1;
        do {
            printMenu();
            if (!sc.hasNextInt()) {
                System.out.println("Lua chon khong hop le.");
                sc.nextLine();
                continue;
            }
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1 -> {
                    System.out.print("Ten mat hang: ");
                    String name = sc.nextLine();
                    System.out.print("Don gia: ");
                    if (!sc.hasNextDouble()) {
                        System.out.println("Du lieu khong hop le.");
                        sc.nextLine();
                        break;
                    }
                    double price = sc.nextDouble();
                    System.out.print("So luong: ");
                    if (!sc.hasNextInt()) {
                        System.out.println("Du lieu khong hop le.");
                        sc.nextLine();
                        break;
                    }
                    int quantity = sc.nextInt();
                    sc.nextLine();
                    addItem(name, price, quantity);
                }
                case 2 -> {
                    System.out.print("Nhap STT can xoa: ");
                    if (!sc.hasNextInt()) {
                        System.out.println("Khong tim thay mat hang.");
                        sc.nextLine();
                        break;
                    }
                    int index = sc.nextInt();
                    sc.nextLine();
                    removeItem(index - 1);
                }
                case 3 -> printItems();
                case 4 -> {
                    double total = calculateTotal();
                    double vat = total * VAT_RATE;
                    double shipping = names.isEmpty() ? 0 : SHIPPING_FEE;
                    System.out.println("\nHOA DON");
                    System.out.printf("Tien hang: %.0f%n", total);
                    System.out.printf("VAT: %.0f%n", vat);
                    System.out.printf("Phi van chuyen: %.0f%n", shipping);
                    System.out.printf("Tong thanh toan: %.0f%n", total + vat + shipping);
                }
                case 0 -> System.out.println("Ket thuc.");
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 0);
        sc.close();
    }
}