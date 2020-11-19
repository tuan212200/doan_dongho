/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doan;
import java.util.Scanner;
/**
 *
 * @author Tuấnn Phạm
 */
public abstract class PerSon {
    private String Ten, DienThoai, DiaChi;
    private int GioiTinh;
    private NamSinh NgaySinh;
    
    Scanner sc = new Scanner(System.in);
    KT_NhapXuat kt = new KT_NhapXuat();

    public String getTen() {
        return Ten;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
    }

    public String getDienThoai() {
        return DienThoai;
    }

    public void setDienThoai(String DienThoai) {
        this.DienThoai = DienThoai;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public int getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(int GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public NamSinh getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(int ngay, int thang, int nam) {
        NgaySinh.setNgay(ngay);
        NgaySinh.setThang(thang);
        NgaySinh.setNam(nam);
    }
    
    public PerSon(){
        NgaySinh = new NamSinh();
    }
    public PerSon(String ten, String dienthoai, String diachi, int gioitinh, NamSinh ngaysinh){
        Ten = ten;
        DienThoai = dienthoai;
        DiaChi = diachi;
        GioiTinh = gioitinh;
        NgaySinh = ngaysinh;
    }
    public String KTGioiTinh ( int gt ){
        if (gt == 1)
            return "Nam";
        return "Nu";
    }
    
    public void Nhap(){
        System.out.print("- Nhap Ho Ten: ");
        Ten = kt.KTTen();
        System.out.println("- Nhap Ngay Sinh: ");
        NgaySinh.Nhap();
        System.out.print("- Nhap Gioi Tinh ( 1. Nam || 2. Nu )");
        GioiTinh = kt.KTNhapInt(1);
        System.out.print("- Nhap Dia Chi: ");
        DiaChi = sc.nextLine();
        while(true){
            System.out.print("- Nhap so dien thoai: ");
            DienThoai = sc.nextLine();
            try{
                int dt = Integer.parseInt(DienThoai);
                if (DienThoai.length() != 10)
                    System.out.println("* So dien thoai phai du 10 so *.");
                else if ( dt < 0)
                    System.out.println("* Loi dinh dang *");
                else
                    break;
            }catch(Exception e){
                System.out.println("* Loi dinh dang *");
            }
        }
    }
    
    public void Xuat(){
        System.out.println("");
        System.out.printf("%-30s%-20s",Ten,KTGioiTinh(GioiTinh) );
        NgaySinh.Xuat();
	System.out.printf("%-11s%-30s%-15s\n", "", DiaChi, DienThoai);
    }
}
