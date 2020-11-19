/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doan;
import java.io.IOException;
import java.util.Scanner;
/**
 *
 * @author Tuấnn Phạm
 */
public class SanPham {
    private String MaSP, TenSP, MaLoai, MaNCC;
    private int Gia;
    Scanner sc = new Scanner(System.in);
    KT_NhapXuat kt = new KT_NhapXuat();

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public String getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(String MaLoai) {
        this.MaLoai = MaLoai;
    }

    public int getGia() {
        return Gia;
    }

    public void setGia(int Gia) {
        this.Gia = Gia;
    }

    public String getMaNCC() {
        return MaNCC;
    }

    public void setMaNCC(String MaNCC) {
        this.MaNCC = MaNCC;
    }
    
    public SanPham(String maSP, String tenSP, String maLoai, String maNCC, int gia){
        super();
        MaSP = maSP;
        TenSP = tenSP;
        MaLoai = maLoai;
        MaNCC=MaNCC;
        Gia = gia;
    }
    public SanPham(){}
    public void Nhap(String maNCC) throws IOException{
        System.out.print("- Nhập mã sản phẩm: ");
        MaSP = kt.KTMaSP();
        System.out.print("- Nhập tên sản phẩm: ");
        TenSP = kt.KTTen();
        System.out.print("- Nhập mã loại: ");
        MaLoai = kt.KTTheLoai();
        MaNCC = maNCC;

    }
    public void Xuat(){
        System.out.printf("%5s%-10s%-20s%-20s%-20s\n","",MaSP,TenSP,MaLoai,MaNCC);
    }
}
