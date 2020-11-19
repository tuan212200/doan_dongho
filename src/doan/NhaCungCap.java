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
public class NhaCungCap {
   private String MaNCC, TenNCC;
   Scanner sc = new Scanner(System.in);
   KT_NhapXuat kt = new KT_NhapXuat();

    public String getMaNCC() {
        return MaNCC;
    }

    public void setMaNCC(String MaNCC) {
        this.MaNCC = MaNCC;
    }

    public String getTenNCC() {
        return TenNCC;
    }

    public void setTenNCC(String TenNCC) {
        this.TenNCC = TenNCC;
    }

    public NhaCungCap(String MaNCC, String TenNCC) {
        this.MaNCC = MaNCC;
        this.TenNCC = TenNCC;
    }
    public NhaCungCap(){}
    public void Nhap() throws IOException{
        System.out.print("- Nhập mã nhà cung cấp: ");
        MaNCC = kt.KTMaNCC();
        System.out.print("- Nhập tên nhà cung cấp: ");
        TenNCC = kt.KTTen();
    }
    public void Xuat(){
        System.out.printf("%5s%-10s%-20s\n","",MaNCC,TenNCC);
    }
}
