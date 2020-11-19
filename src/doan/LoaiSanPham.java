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
public class LoaiSanPham {
    private String MaLoai, TenLoai;
    Scanner sc = new Scanner(System.in);
    KT_NhapXuat kt = new KT_NhapXuat();

    public String getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(String MaLoai) {
        this.MaLoai = MaLoai;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String TenLoai) {
        this.TenLoai = TenLoai;
    }
    
    public LoaiSanPham(String maloai, String tenloai){
        super();
        MaLoai = maloai;
        TenLoai = tenloai;
    }
    public LoaiSanPham(){
        
    }
    public void Nhap() throws IOException{
        System.out.print("- Nhập mã loại: ");
        MaLoai = kt.KTLoai();
        System.out.print("- Nhập tên loại: ");
        TenLoai =kt.KTTen();
    }
    public void Xuat(){
        System.out.printf("%5s%-10s%-20s\n","",MaLoai,TenLoai);
    }
}
