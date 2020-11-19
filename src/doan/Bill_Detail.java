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
public class Bill_Detail {
    String MAHD, MASP;
    int SoLuong, GIA;
    Scanner sc = new Scanner(System.in);
    KT_NhapXuat kt = new KT_NhapXuat();

    public String getMAHD() {
        return MAHD;
    }

    public void setMAHD(String MAHD) {
        this.MAHD = MAHD;
    }

    public String getMASP() {
        return MASP;
    }

    public void setMASP(String MASP) {
        this.MASP = MASP;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public int getGIA() {
        return GIA;
    }

    public void setGIA(int GIA) {
        this.GIA = GIA;
    }
    public Bill_Detail(){}

    public Bill_Detail(String MAHD, String MASP, int SoLuong, int GIA) {
        this.MAHD = MAHD;
        this.MASP = MASP;
        this.SoLuong = SoLuong;
        this.GIA = GIA;
    }
    public void Nhap() throws IOException{
        System.out.print("- Nhập số lượng sản phẩm trong CTHD: ");
        SoLuong = kt.KTNhapInt(0);
    }
    public void Xuat() throws IOException{
        ArrSanPham arrSP = new ArrSanPham() {};
        int vt = arrSP.TimKiem_MaSo(MASP);
        String ten = arrSP.getArrSP()[vt].getTenSP();
        System.out.printf("(\"%-5s%-10s%-20s%-6s\n","",MAHD,MASP,SoLuong);
    }
}
