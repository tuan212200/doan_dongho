/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doan;

/**
 *
 * @author Tuấnn Phạm
 */
public class NhanVien extends PerSon {
    private String MS;

    public String getMS() {
        return MS;
    }

    public void setMS(String MS) {
        this.MS = MS;
    }
    
    public NhanVien(){
        super();
    }
    public NhanVien(String ten, String dienthoai, String diachi, int gioitinh, NamSinh ngaysinh ){
        super(ten, dienthoai, diachi, gioitinh, ngaysinh);
    }
    @Override
    public void Nhap(){
        super.Nhap();
    }
    @Override
    public void Xuat(){
        System.out.printf("%5s%-10s", "", MS);
	super.Xuat();
    }
    
}
