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
public class TaiKhoan {
    private String username, password, MANV;
    private int Quyen;
    Scanner sc = new Scanner(System.in);
    KT_NhapXuat kt = new KT_NhapXuat();
    
    public int getQuyen() {
		return Quyen;
	}

	public void setQuyen(int quyen) {
		Quyen = quyen;
	}

	public String getMANV() {
		return MANV;
	}

	public void setMANV(String mANV) {
		MANV = mANV;
	}
        public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public TaiKhoan(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public TaiKhoan(String user) {
		username = user;
	}
	
	public TaiKhoan() {}
        public void Nhap() throws IOException {	
		System.out.print("- Password: ");
		password = sc.nextLine();
		System.out.print("- Mã nhân viên của bạn: ");
		MANV = kt.KT_MaNV();
		System.out.print("- Quyền : 1. Admin   2. User");
		Quyen= kt.KTLuaChon(2);
	}
	
	public String KTQuyen() {
		if(Quyen == 1)
			return "admin";
		return "user";
	}
	
	public void Xuat() {
		System.out.printf("%5s%-10s%-20s%-5s%-10s\n", "", username, password,MANV,KTQuyen());
		
	}
}
