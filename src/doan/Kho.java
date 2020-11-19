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
public class Kho {

    private String MaKhu, MaSanPham;
    private int SLuong, GIA;

    public int getGIA() {
        return GIA;
    }

    public void setGIA(int gIA) {
        GIA = gIA;
    }
    Scanner sc = new Scanner(System.in);
    KT_NhapXuat kt = new KT_NhapXuat();

    public String getMaKhu() {
        return MaKhu;
    }

    public void setMaKhu(String MaKhu) {
        this.MaKhu = MaKhu;
    }

    public String getMaSanPham() {
        return MaSanPham;
    }

    public void setMaSanPham(String MaSanPham) {
        this.MaSanPham = MaSanPham;
    }

    public int getSLuong() {
        return SLuong;
    }

    public void setSLuong(int SLuong) {
        this.SLuong = SLuong;
    }

    public Kho(String maKhu, String maSanPham, int sLuong) {
        super();
        MaKhu = maKhu;
        MaSanPham = maSanPham;
        SLuong = sLuong;
    }

    public Kho() {
    }

    public void Nhap(int g) {
        System.out.print("- Nhập mã khu trong kho ( từ 1 -> 8 ) : ");
        MaKhu = kt.KTMaKhu();
        SLuong = 0;
        GIA = g;
    }

    public void Xuat(String tens) {
        System.out.printf("%5s%-5s%-5s%-20s%-10d\n", "", MaKhu, MaSanPham, tens, SLuong);
    }
}
