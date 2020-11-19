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
public class HoaDon {
    private String MAHD, MAKH, MANV;
    private NamSinh Ngay_xuat = new NamSinh();
    private int TRANG_THAI;
    private double TONG_TIEN = 0;
    Scanner sc = new Scanner(System.in);
    KT_NhapXuat kt = new KT_NhapXuat();

    public String getMAHD() {
        return MAHD;
    }

    public void setMAHD(String MAHD) {
        this.MAHD = MAHD;
    }

    public String getMAKH() {
        return MAKH;
    }

    public void setMAKH(String MAKH) {
        this.MAKH = MAKH;
    }

    public String getMANV() {
        return MANV;
    }

    public void setMANV(String MANV) {
        this.MANV = MANV;
    }

    public NamSinh getNgay_xuat() {
        return Ngay_xuat;
    }

    public void setNgay_xuat(int ngay, int thang, int nam) {
        Ngay_xuat.setNgay(ngay);
        Ngay_xuat.setThang(thang);
        Ngay_xuat.setNam(nam);
    }
    public void setNgay_xuat(NamSinh ns){
        Ngay_xuat.setNgay(ns.getNgay());
        Ngay_xuat.setThang(ns.getThang());
        Ngay_xuat.setNam(ns.getNam());
    }

    public int getTRANG_THAI() {
        return TRANG_THAI;
    }

    public void setTRANG_THAI(int TRANG_THAI) {
        this.TRANG_THAI = TRANG_THAI;
    }

    public double getTONG_TIEN() {
        return TONG_TIEN;
    }

    public void setTONG_TIEN(double TONG_TIEN) {
        this.TONG_TIEN = TONG_TIEN;
    }

    public HoaDon(String MAHD, String MAKH, String MANV, int TRANG_THAI) {
        this.MAHD = MAHD;
        this.MAKH = MAKH;
        this.MANV = MANV;
        this.TRANG_THAI = TRANG_THAI;
    }
    public HoaDon(){}
    public void NhapNH(String maHD, String maNV) throws IOException{
        MAHD = "NH" + maHD;
        System.out.print("- Nhập mã nhà cung cấp: ");
        MAKH = kt.KTMaNhaCungCap();
        MANV = maNV;
        System.out.print("- Nhập tổng tiền sản phẩm khi nhập: ");
        TONG_TIEN = kt.KTNhapInt(0);
    }
    public void NhapXH(String maHD, String maNV) throws IOException{
        MAHD = "XH" + maHD;
        System.err.print("- Bạn có là thành viên của shop không? ( y / n ): ");
        String yn = kt.KTYesNo();
        String regex = "y|Y";
        if(yn.matches(regex)){
            System.out.print("- Nhập mã khách hàng: ");
            MAKH = kt.KT_MAKH();
        }else
            MAKH = null;
        MANV = maNV;
    }
    public void Xuat() throws IOException{
        ArrBillDetail abd = new ArrBillDetail() {};
        ArrNhanVien anv = new ArrNhanVien(){};
        ArrKhachHang akh = new ArrKhachHang(){};
        int vtnv = anv.TimKiem_MaSo(MANV);
        int vtkh = akh.TimKiem_MaSo(MAKH);
        String tenNV = anv.getArrNV()[vtnv].getTen();
        String tenKH = "";
        if(vtkh!=-1)
            tenKH = akh.getArrKH()[vtkh].getTen();
            System.out.printf("%5s%-10s", "", MAHD);
            Ngay_xuat.Xuat();
            System.out.printf("%-11s%-20s%-20s%-20s%-10.2f\n","", KT_TT(), tenKH, tenNV, TONG_TIEN);
            abd.TimKiem_MS(MAHD);
    }
    public String KT_TT(){
        if(TRANG_THAI == 1)
            return "Nhập Hàng";
        return "Xuất Hàng";
    }
}
