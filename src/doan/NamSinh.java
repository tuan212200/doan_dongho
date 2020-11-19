/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doan;
import java.time.Year;
import java.util.Scanner;
/**
 *
 * @author Tuấnn Phạm
 */
public class NamSinh {
    private int Ngay, Thang, Nam;
    private final int MAX_NAM = 2020;
    Scanner sc = new Scanner(System.in);
    KT_NhapXuat kt = new KT_NhapXuat();
    
    
    public int getNgay() {
        return Ngay;
    }

    public void setNgay(int Ngay) {
        this.Ngay = Ngay;
    }

    public int getThang() {
        return Thang;
    }

    public void setThang(int Thang) {
        this.Thang = Thang;
    }

    public int getNam() {
        return Nam;
    }

    public void setNam(int Nam) {
        this.Nam = Nam;
    }
    public NamSinh(){
        Ngay = Thang = Nam = 0;
    }
    public NamSinh(int ngay, int thang, int nam){
        Ngay = ngay;
        Thang = thang;
        Nam = nam;
    }
    public NamSinh(NamSinh ns){
        Ngay = ns.Ngay;
        Thang = ns.Thang;
        Nam = ns.Nam;
    }

    public boolean KTNamNhuan(){
        if (Nam % 400 == 0 || (Nam % 4 == 0 && Nam % 100 != 0 ))
            return true;
        return false;
    }
    public boolean KTNgay(int ngay){
        switch(Thang){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12: {
                if (Ngay > 31)
                    return false;
                break;
            }
            case 4:
            case 6:
            case 9:
            case 11: {
                if (Ngay > 30)
                    return false;
                break;
            }
            case 2: {
                if (KTNamNhuan())
                    if (Ngay > 29)
                        return false;
                    else
                        return true;
                    else if( Ngay > 28)
                        return false;
                    else
                        return true;
            }
        }
        return true;
    }
    public boolean KTNguyenNgay(NamSinh ns) {
		if (this.Nam < ns.Nam)
			return false;
		else if (this.Nam > ns.Nam)
			return true;
		if (this.Thang < ns.Thang)
			return false;
		else if (this.Thang > ns.Thang)
			return true;
		if (this.Ngay < ns.Ngay)
			return false;
		else if (this.Ngay > ns.Ngay)
			return true;
		return true;
	}

	public boolean SoSanh(NamSinh ns) {
		if (Nam != ns.Nam)
			return false;
		if (Thang != ns.Thang)
			return false;
		if (Ngay != ns.Ngay)
			return false;
		return true;
	}
        public void Nhap(){
        System.out.print("- Nhap Nam: ");
        Nam = kt.KTLuaChon(MAX_NAM);
        System.out.print("- Nhap Thang: ");
        Thang = kt.KTLuaChon(12);
        System.out.print("- Nhap Ngay: ");
        while (true){
            Ngay = kt.KTNhapInt(1);
            if (KTNgay(Ngay))
                break;
            else
                System.out.print("- Nhap lai ngay: ");
        }
    }
        public void Xuat(){
            System.out.format("%02d/%02d/%04d", Ngay, Thang, Nam);
        }
        
}
