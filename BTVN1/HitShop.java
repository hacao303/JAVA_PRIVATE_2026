import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class HitShop {
    static final ArrayList<String> names = new ArrayList<>();
    static final ArrayList<Double> prices = new ArrayList<>();
    static final ArrayList<Integer> quantities = new ArrayList<>();
    static void printMenu() {
        System.out.println("\nDANH SÁCH CÁC CHỨC NĂNG");
        System.out.println("1. Thêm mặt hàng");
        System.out.println("2. Xóa mặt hàng");
        System.out.println("3. Xem giỏ");
        System.out.println("4. Xem tổng tiền");
        System.out.println("0. Thoát\n");
        System.out.print("Chon chuc nang so: ");
    }
    static void addItem(String name, double price, int quantity) {
        boolean hople = !name.isBlank() && price > 0 && Double.isFinite(price) && quantity > 0;
        if (hople) {
            names.add(name);
            prices.add(price);
            quantities.add(quantity);
        } else {
            System.out.println("Du lieu khong hop le: ten khong duoc rong, don gia > 0 va so luong > 0.");
        }
    }
    static void removeItem(int index) {
        if (index < 0 || index >= names.size()) {
            System.out.println("Không tìm thấy mặt hàng.");
            return;
        }
        names.remove(index);
        prices.remove(index);
        quantities.remove(index);
    }
    static void printItems() {
        if (names.isEmpty()) {
            System.out.println("Giỏ hàng đang trống.");
            return;
        }
        for (int i = 0; i < names.size(); i++) {
            double lineTotal = prices.get(i) * quantities.get(i);
            System.out.printf("%d. %s | %.1f | SL: %d | Thành tiền: %.1f%n",
                    i + 1,
                    names.get(i),
                    prices.get(i),
                    quantities.get(i),
                    lineTotal);
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
        Scanner scanner = new Scanner(System.in);
        final double VAT_RATE = 0.10;
        final double SHIPPING_FEE = 20000;
        int choice = -1;
        do {
            printMenu();
            if (!scanner.hasNextInt()) {
                System.out.println("Lựa chọn không hợp lệ.");
                scanner.nextLine();
                continue;
            }
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> {
                    System.out.print("Ten mat hang: ");
                    String tenMatHang = scanner.nextLine();
                    System.out.print("Don gia: ");
                    if (!scanner.hasNextDouble()) {
                        System.out.println("Du lieu khong hop le.");
                        scanner.nextLine();
                        break;
                    }
                    double donGia = scanner.nextDouble();
                    System.out.print("So luong: ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Du lieu khong hop le.");
                        scanner.nextLine();
                        break;
                    }
                    int soLuong = scanner.nextInt();
                    scanner.nextLine();
                    boolean hople = !tenMatHang.isBlank() && donGia > 0
                            && Double.isFinite(donGia) && soLuong > 0;
                    if (hople) {
                        addItem(tenMatHang, donGia, soLuong);
                    } else {
                        System.out.println("Du lieu khong hop le: ten khong duoc rong, don gia > 0 va so luong > 0.");
                    }
                }
                case 2 -> {
                    System.out.print("Nhap so thu tu can xoa: ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Không tìm thấy mặt hàng.");
                        scanner.nextLine();
                        break;
                    }
                    int displayNumber = scanner.nextInt();
                    scanner.nextLine();
                    removeItem(displayNumber - 1);
                }
                case 3 -> printItems();
                case 4 -> {
                    double thanhTien = calculateTotal();
                    double tienVat = thanhTien * VAT_RATE;
                    double phiVanChuyen = 0;
                    if (!names.isEmpty()) {
                        phiVanChuyen = SHIPPING_FEE;
                    }
                    double tongThanhToan = thanhTien + tienVat + phiVanChuyen;
                    System.out.println("\nHOA DON");
                    System.out.println("Thanh tien: " + thanhTien);
                    System.out.println("Tien VAT: " + tienVat);
                    System.out.println("Phi van chuyen: " + phiVanChuyen);
                    System.out.println("Tong thanh toan: " + tongThanhToan);
                }
                case 0 -> System.out.println("Kết thúc.");
                default -> System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 0);
        scanner.close();
    }
}
