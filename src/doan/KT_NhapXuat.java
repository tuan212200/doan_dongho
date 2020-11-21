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
public class KT_NhapXuat {
    Scanner sc = new Scanner(System.in);
    
    public float KTNhap(String s){
        float so = 0;
        while (true){
            System.out.print(" : ");
            try {
                so =Float.parseFloat(s);
                if ( so > 0)
                    break;
                else {
                    System.out.print("* So nhap vao phai lon hon 0 *. Nhap lai: ");
                    so = Float.parseFloat(sc.nextLine());
                }
            } catch (Exception e){
                System.out.println("* Loi du lieu !!! *. Nhap lai: ");
            }
        }
        return so;
    }
    public String KTYesNo(){
        String s;
        while (true){
            String regex = "[yn]";
            s = sc.nextLine();
            boolean check;
            if ((check = s.matches(regex)) == true)
                break;
            else
                System.out.print("* Ki tu nhap khong hop le *. Nhap lai: ");
        }
        return s;
    }
    
    public int KTNhapInt(int x){
        int so = 0;
        while(true){
            System.out.print(" : ");
            try{
                so = Integer.parseInt(sc.nextLine());
                if ( so >= x )
                    break;
                else
                    System.out.println("* So nhap vao khong duoc am *. Nhap lai: ");
            }catch (Exception e){
                System.out.println("* Loi du lieu !! *. Nhap lai: ");
            }
        }
        return so;
    }
    
    public int KTLuaChon(int x){
        int LuaChon;
        while(true){
            LuaChon = KTNhapInt(0);
            if (LuaChon > x)
                System.out.println("* Lua chon cua ban da sai *. Nhap lai: ");
            else
                break;
        }
        return LuaChon;
    }
    
    /*-----Person----*/
    public String KTMaSo() {
		String regex;
		String s;
		boolean test;
		while (true) {
			s = sc.nextLine();
			regex = "[1-9][0-9]*";
			if ((test = s.matches(regex)))
				break;
			else
				System.out.print("- Nhap sai ma so. Nhap lai: ");
		}
		return s;
	}
    public String KTTen(){
		String regex = "[\\pL\\pMn*\\s*]+";		// \pL (\p{L})là 1 ký tự Unicode ( â , ă , ơ ,....) 
		String s;								// \pMn (\p{Mn} là một kí tự dự định kết hợp với một ký tự khác mà không chiếm thêm không gian ( các thanh ngang )
		boolean test;
                while (true) {
			s = sc.nextLine();
			if((test =s.matches(regex)))
                            break;
			else {
				System.out.print("** Tên nhập không hợp lệ ** . Nhập lai : ");

			}
		}
		return s;
}
    public String KTDienThoai(){
        String regex = "0[1-9]{9}";
        String s;
        while(true){
            s = sc.nextLine();
            boolean check = s.matches(regex);
            if (check == true)
                break;
            else{
                System.out.println("* Dien thoai nhap khong hop le *. Nhap lai: ");
            }
        }
        return s;
    }
    public String KTTheLoai() throws IOException{
        String ma;
        while(true){
            ma = sc.nextLine();
            ArrLoaiSanPham arrLSP = new ArrLoaiSanPham() {};
            if(arrLSP.TimKiem_MaSo(ma) == -1){
                System.out.print("- Mã thể loại bạn nhập chưa có trong danh sách. Bạn có muốn thêm mới ?? ( y / n ): ");
                String yn = KTYesNo();
                String regex = "y|Y";
                if(yn.matches(regex)){
                    arrLSP.Them1(ma);
                    break;
                }else{
                    System.out.print("- Mã bạn nhập không có. Nhập lại: ");
                }
            }
            else
                break;
        }
        return ma;
    }
       public String KTMaNhaCungCap() throws IOException{
        String ma;
        while(true){
            ma = sc.nextLine();
            ArrNhaCungCap arrNCC = new ArrNhaCungCap() {};
            if(arrNCC.TimKiem_MaSo(ma) == -1){
                System.out.print("- Mã nhà cung cấp bạn nhập chưa có trong danh sách. Bạn có muốn thêm mới ?? ( y / n ): ");
                String yn = KTYesNo();
                String regex = "y|Y";
                if(yn.matches(regex)){
                    arrNCC.Them1(ma);
                    break;
                }else{
                    System.out.print("- Mã bạn nhập không có. Nhập lại: ");
                }
            }
            else
                break;
        }
        return ma;
    }
       public String KT_MAKH() throws IOException{
           String ma;
           while(true){
               ma = KTMaSo();
               ArrKhachHang arrKH = new ArrKhachHang() {};
               if(arrKH.TimKiem_MaSo(ma)==-1){
                   System.out.print("- Mã khách hàng bạn nhập chưa có trong danh sách. Bạn có muốn thêm? ( y / n ): ");
                   String yn = KTYesNo();
                   String regex = "y|Y";
                   if(yn.matches(regex)){
                       arrKH.Them1(ma);
                       break;
                   }else{
                       System.out.print("- Mã bạn nhập không có. Nhập lại: ");
                   }
               }else
                   break;
               
           }
           return ma;
       }
       public String KTMaKhu(){
           String regex ="[1-8]";
           String s;
           boolean test;
           while(true){
               s = sc.nextLine();
               if((test=s.matches(regex)))
                   break;
               else
                   System.out.print("- Nhập sai mã số. Nhập lại: ");
           }
           return s;
       }
       public String KT_MaNV() throws IOException {
		String ma;
		while (true) {
			ma = KTMaSo();
			ArrNhanVien arrNV = new ArrNhanVien() {};
			if (arrNV.TimKiem_MaSo(ma) == -1) {
				System.out.print("- Mã Nhân viên bạn nhập chưa có trong danh sách bạn có muốn thêm không ( y / n ) ??? : ");
				String yn = KTYesNo();
				String regex = "y|Y";
				if (yn.matches(regex)) {
                                    arrNV.Them1(ma);
					break;
				} else {
					System.out.print("- Mã bạn nhập không có . Hãy nhập lai : ");
				}
			} else
				break;
		}
		return ma;
	}

	public String KT_MaKH() throws IOException {
		String ma;
		while (true) {
			ma = KTMaSo();
			ArrKhachHang arrKH = new ArrKhachHang() {};
			if (arrKH.TimKiem_MaSo(ma) == -1) {
				System.out.print("- Mã khách hàng bạn nhập chưa có trong danh sách bạn có muốn thêm không ( y / n ) ??? : ");
				String yn = KTYesNo();
				String regex = "y|Y";
				if (yn.matches(regex)) {
					arrKH.Them1(ma);
					break;
				} else {
					System.out.print("- Mã bạn nhập không có . Hãy nhập lai : ");
				}
			} else
				break;
		}
		return ma;
	}
       public String KT_MaSanPham_KhiNH(String maNCC, int gia) throws IOException {
		String mas;
		mas = KTMaSo();
		ArrKho arrK = new ArrKho();
                int vt = arrK.TimKiem_MaSanPham(mas);
		if (vt == -1) {
                    System.out.print("- Mã sản phẩm bạn nhập không có trong danh sách. Bạn phải nhập thông tin của sách đó.");
			arrK.Them1(mas, maNCC, gia);
		}
		return mas;
	}
public String KT_MaSanPham_KhiXH() throws IOException {
		String mas;
		ArrKho arrK = new ArrKho();
		while (true) {
			mas = KTMaSo();
                        int vt = arrK.TimKiem_MaSanPham(mas);
			if (vt == -1) {
				System.out.print("- Mã bạn nhập không có . Hãy nhập lai : ");
			} else {
				break;
			}
		}
		return mas;
	}
public String KT_NCC() throws IOException{
    String ma;
    while(true){
        ma = KTMaSo();
        ArrNhaCungCap arrNCC = new ArrNhaCungCap() {};
        if(arrNCC.TimKiem_MaSo(ma) ==-1){
            System.out.print("- Mã nhà cung cấp chưa có. Bạn có muốn thêm ( y / n ):  ");
           String yn = KTYesNo();
           String regex = "y|Y";
           if(yn.matches(regex)){
               arrNCC.Them1(ma);
               break;
           }else{
               System.out.print("- Mã bạn nhập không có. Hãy nhập lại: ");
           }
        }else
            break;
    }
    return ma;
}
public void ThayDoiSlSach(String mas, int tt, int sl) throws IOException {
		ArrKho arrK = new ArrKho();
                int vt = arrK.TimKiem_MaSanPham(mas);
		if (vt != -1)
                    arrK.ThayDoiSLuongSanPham(tt, vt, sl);
	}

   //----------Kiem tra Mã nhập vào có bị trùng hay không? 
    public String KTLoai() throws IOException{
        String ma;
        while(true){
            ma=sc.nextLine();
            ArrLoaiSanPham arrLSP = new ArrLoaiSanPham(){};
            if(arrLSP.TimKiem_MaSo(ma)!=-1){
                System.out.print("- Mã loại sản phẩm nhập bị trùng. Nhập lại: ");
            }
            else{
                break;
            }
        }
        return ma;
    }
    public String KTMaSP() throws IOException{
        String ma;
        while(true){
            ma = sc.nextLine();
            ArrSanPham arrSP = new ArrSanPham(){};
            if(arrSP.TimKiem_MaSo(ma) != -1){
                System.out.print("- Mã sản phẩm bạn nhập bị trùng. Nhập lại: ");
            }
            else{
                break;
            }
        }
        return ma;
    }
    public String KTTaiKhoan() throws IOException{
        String ma;
        while(true){
            ma = sc.nextLine();
            ArrTaiKhoan tk = new ArrTaiKhoan();
            if(tk.TimKiem_MaSo(ma)!=-1){
                System.out.print("- Mã tài khoan");
            }
        }
    }
    public String KTMaNCC() throws IOException{
        String ma;
        while(true){
            ma = sc.nextLine();
            ArrNhaCungCap arrNCC = new ArrNhaCungCap(){};
            if(arrNCC.TimKiem_MaSo(ma)!=-1){
                System.out.print("- Mã nhà cung cấp bạn nhập bị trùng. Nhập lại: ");
            }else
                break;
        }
        return ma;
    }
public String KTTaiKhoan(String ma) throws IOException {
		ArrTaiKhoan arrTk = new ArrTaiKhoan();
		while (true) {
			if (arrTk.TimKiem_MaSo(ma) != -1) {
                            System.out.println("- Tên đăng nhập đã tồn tại. Nhập lại: ");
				ma = sc.nextLine();
			} else
				break;
		}
		return ma;
	}

	public String KTTaiKhoanCo(String user) throws IOException {
		ArrTaiKhoan arrTk = new ArrTaiKhoan();
		while (true) {
			if (arrTk.TimKiem_MaSo(user) == -1) {
				System.out.print("- Tên đăng nhập không tồn tại. Nhập lại: ");
				user = sc.nextLine();
			} else
				break;
		}
		return user;
	}

	public String KTMKCo(String pass, int vt) throws IOException {
		ArrTaiKhoan arrTk = new ArrTaiKhoan();
		while (true) {
			if (arrTk.TruyXuatPT(vt).getPassword().compareTo(pass) != 0) {
				System.out.print("- Mật khẩu bạn nhập không đúng. Nhập lại:  ");
				pass = sc.nextLine();
			} else
				break;
		}
		return pass;
	}

	public String KTTaiKhoan(String ma, int vt) throws IOException {
		ArrTaiKhoan arrTk = new ArrTaiKhoan();
		while (true) {
			if (arrTk.TimKiem_MaSo(ma, vt) != -1) {
				System.out.print("- Tên đăng nhập đã tồn tại. Nhập lại: ");
				ma = sc.nextLine();
			} else
				break;
		}
		return ma;
	}
	    
    
    public void Phim(){
        System.out.println("- Nhấn phím bất kì để tiếp tục: ");
        String s = sc.nextLine();
    }
    public String  Out() {
		System.out.print("- Nhấn phím bất kì để tiếp tục: ");
		return sc.nextLine();
			
	}
}
