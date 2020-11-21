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
        ArrNhanVien nv = new ArrNhanVien() {};
        ArrKhachHang kh = new ArrKhachHang() {};
        ArrLoaiSanPham lsp = new ArrLoaiSanPham() {};
        ArrSanPham sp = new ArrSanPham() {};
       ArrNhaCungCap ncc = new ArrNhaCungCap(){};
       ArrBillDetail abd = new ArrBillDetail() {
};
       ArrHoaDon hd = new ArrHoaDon(){};
       ArrKho ak = new ArrKho();
       ArrTaiKhoan tk = new ArrTaiKhoan();
       String temp;
       int lcdn, lcnv, lcad;
       String thoatUS="", thoatAD="";
        //int lc;
        do{
            System.out.println("\n===============MENU===============");
            System.out.println("1. Đăng nhập.");
            System.out.println("2. Đăng kí.");
            System.out.println("3. Thoát.");
            System.out.print("- Nhập lựa chọn của bạn: ");
            lcdn = kt.KTLuaChon(3);
            switch (lcdn){
                case 1:{
                    String maNV = tk.DangNhap();
                    String s = maNV;
                    if(maNV!="admin"){
                        int vt = nv.TimKiem_MaSo(maNV);
                        s = nv.getArrNV()[vt].getTen();
                    }
                    if(s.compareTo("admin")!= 0){
                        System.out.println("- Chào mừng: " + s);
                        do{
                            System.out.println("\n==========MENU==========");
                            System.out.println("1. Nhập hóa đơn");
                            System.out.println("2. Xuất hóa đơn");
                            System.out.println("3. Tổng doanh thu hôm nay");
                            System.out.println("4. Thống kê hóa đơn trong ngày hôm nay");
                            System.out.println("5. Xuất danh sách sản phẩm trong kho");
                            System.out.println("6. Tìm kiếm đồng hồ");
                            System.out.println("7. Thoát");
                            System.out.print("- Nhập lựa chọn của bạn: ");
                            lcnv = kt.KTLuaChon(7);
                            switch (lcnv){
                                case 1:{
                                    System.out.println("\n==========NHẬP HÀNG==========");
                                    hd.Them1(maNV);
                                    kt.Phim();
                                    break;
                                }
                                case 2:{
                                    System.out.println("1. Hóa đơn nhập");
                                    System.out.println("2. Hóa đơn xuất");
                                    System.out.println("3. Tất cả");
                                    System.out.print("- Bạn muốn xuất hóa đơn: ");
                                    int tt = kt.KTLuaChon(3);
                                    hd.Xuat(tt);
                                    kt.Phim();
                                    break;
                                }
                                case 3:{
                                    System.out.println("1. Nhập hàng");
                                    System.out.println("2. Xuất hàng");
                                    System.out.println("3. Tất cả");
                                    System.out.print("- Bạn muốn xuất tổng doanh thu hóa đơn: ");
                                    int lc = kt.KTLuaChon(3);
                                    hd.TongDoanhThuTrongNgay(lc);
                                    kt.Phim();
                                    break;
                                }
                                case 4:{
                                     System.out.println("1. Nhập hàng");
                                    System.out.println("2. Xuất hàng");
                                    System.out.println("3. Tất cả");
                                    System.out.print("- Bạn muốn xuất hóa đơn: ");
                                    int lc = kt.KTLuaChon(3);
                                    hd.TimKiem_Ngay(lc, Now);
                                    kt.Phim();
                                    break;
                                }
                                case 5:{
                                    System.out.println("- Danh sách sản phẩm trong kho: ");
                                    ak.Xuat();
                                    kt.Phim();
                                    break;
                                }
                                case 6:{
                                    System.out.print("- Nhập tên sản phẩm cần tìm: ");
                                    sp.TimKiem_Ten();
                                    kt.Phim();
                                    break;   
                                }
                                case 7:{
                                    System.out.println("- Bạn đã thoát.");
                                    thoatUS=kt.Out();
                                }
                            }
                        }while(thoatUS.compareTo(" ")==0);
                    }else{
                        //ArrKhachHang akh = new ArrKhachHang(){};
                        //ArrNhaCungCap anc = new ArrNhaCungCap(){};
                        //ArrLoaiSanPham alsp = new ArrLoaiSanPham(){};
                        System.out.println("- Chào mừng admin.");
                        do{
                            System.out.println("\n===============MENU===============");
                            System.out.println("-----DANH MỤC-----");
                            System.out.println("1. Tài khoản");
                            System.out.println("2. Khách hàng");
                            System.out.println("3. Nhân viên");
                            System.out.println("4. Nhà cung cấp");
                            System.out.println("5. Loại sản phẩm");
                            System.out.println("6. Đồng hồ");
                            System.out.println("7. Hóa đơn");
                            System.out.println("8. Thoát");
                            System.out.print("- Nhập lựa chọn của bạn: ");
                            lcad = kt.KTLuaChon(8);
                            switch(lcad){
                                case 1:{
                                    tk.MenuChinh();
                                    kt.Phim();
                                    break;
                                }
                                case 2:{
                                    kh.MenuChinh();
                                    kt.Phim();
                                    break;
                                }
                                case 3:{
                                    nv.MenuChinh();
                                    kt.Phim();
                                    break;
                                }
                                case 4:{
                                    ncc.MenuChinh();
                                    kt.Phim();
                                    break;
                                }
                                case 5:{
                                    lsp.MenuChinh();
                                    kt.Phim();
                                    break;
                                }
                                case 6:{
                                    sp.MenuChinh();
                                    kt.Phim();
                                    break;
                                }
                                case 7:{
                                    hd.MenuChinh();
                                    kt.Phim();
                                    break;
                                }case 8:{
                                    System.out.println("- Bạn đã thoát.");
                                    thoatAD = kt.Out();
                                    break;
                                }
                            }
                        }while(thoatAD.compareTo(" ")== 0);
                    }
                    break;
                }
                case 2:{
                    tk.DangKi();
                    break;
                }
            }
        }while(lcdn!=3);
        ArrTaiKhoan atk = new ArrTaiKhoan();
        atk.MenuChinh();
}
}
