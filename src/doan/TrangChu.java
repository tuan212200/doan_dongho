   /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doan;

import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;

/**
 *
 * @author Tuấnn Phạm
 */
public class TrangChu {

    public static void main(String[] args) throws IOException {
        KT_NhapXuat kt = new KT_NhapXuat();
        Scanner sc = new Scanner(System.in);
        Calendar c = Calendar.getInstance();
        NamSinh Now = new NamSinh(c.get(Calendar.DATE), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR));
        ArrNhanVien nv = new ArrNhanVien() {
        };
        ArrKhachHang kh = new ArrKhachHang() {
        };
        ArrLoaiSanPham lsp = new ArrLoaiSanPham() {};
        ArrSanPham sp = new ArrSanPham() {};
       ArrNhaCungCap ncc = new ArrNhaCungCap(){};
        int lc;
        do {
            System.out.println("\n===================MENU=====================");
            System.out.println("1. Nhân Viên: ");
            System.out.println("2. Khách hàng: ");
            System.out.println("3. Loại sản phẩm.");
            System.out.println("4. Sản phẩm.");
            System.out.println("5. Nhà cung cấp");
            System.out.println("6. Thoát.");
            System.out.print("- Nhập lựa chọn bạn");
            lc = kt.KTLuaChon(6);
            switch (lc) {
                case 1: {
                    nv.MenuChinh();
                    kt.Phim();
                    break;
                }
                case 2: {
                    kh.MenuChinh();
                    kt.Phim();
                    break;
                }
                case 3 :{
                    lsp.MenuChinh();
                    kt.Phim();
                    break;
                }
                case 4:{
                  sp.MenuChinh();
                    kt.Phim();
                    break;
                }
                case 5: {
                    ncc.MenuChinh();
                    kt.Phim();
                    break;
                }
                case 6: {
                    break;
                }
            }
        } while (lc != 5);
    }
}
