import java.util.Scanner;
public class HitShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final double VAT_RATE = 0.10;
        final double SHIPPING_FEE = 20000;
        System.out.print("Ten mat hang: ");
        String tenMatHang = scanner.nextLine();
        System.out.print("Don gia: ");
        double donGia = scanner.nextDouble();
        System.out.print("So luong: ");
        int soLuong = scanner.nextInt();
        boolean hople = !tenMatHang.isEmpty() && donGia > 0 && soLuong > 0;
        if (hople) {
            double thanhTien = donGia * soLuong;
            double tienVat = thanhTien * VAT_RATE;
            double tongThanhToan = thanhTien + tienVat + SHIPPING_FEE;
            System.out.println("\nHOA DON");
            System.out.println("Ten mat hang: " + tenMatHang);
            System.out.println("Don gia: " + donGia);
            System.out.println("So luong: " + soLuong);
            System.out.println("Thanh tien: " + thanhTien);
            System.out.println("Tong thanh toan: " + tongThanhToan);
        } else {
            System.out.println("Du lieu khong hop le: ten khong duoc rong, don gia > 0 va so luong > 0.");
        }
        scanner.close();
    }
}