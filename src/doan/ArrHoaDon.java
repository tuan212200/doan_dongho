/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Scanner;
/**
 *
 * @author Tuấnn Phạm
 */
public class ArrHoaDon implements Arr{
    private int lenArr = 0;
    private int lenFile = 0;
    private HoaDon[] arrHoaDon = new HoaDon[1];
    private static final String filename = "HoaDon.txt";
    private ArrBillDetail arrBD = new ArrBillDetail() {};
    Calendar c = Calendar.getInstance();
    private NamSinh now = new NamSinh(c.get(Calendar.DATE), c.get(Calendar.MONTH)+1, c.get(Calendar.YEAR));
    Scanner sc = new Scanner(System.in);
    KT_NhapXuat kt = new KT_NhapXuat();
    public ArrHoaDon() throws IOException{
        HienDS();
    }
    @Override
    public void ThemDT() throws IOException{}// không cần vì ta chỉ cần nhập 1 lần 1 hóa đơn vào trong danh sách
    //Thêm 1 nhà cung cấp vào file khi nhập trong class sản phẩm nếu không có
    public void Them1(String maNV) throws IOException{
        lenArr+=1;
        arrHoaDon = Arrays.copyOf(arrHoaDon, lenArr+lenFile);
        int vt = lenArr+lenFile-1,tt;
        String mahd;
        arrHoaDon[vt] = new HoaDon();
        System.out.print("- Nhập trạng thái ( 1. Nhập hàng | 2. Xuất hàng ): ");
        tt = kt.KTLuaChon(2);
        arrHoaDon[vt].setTRANG_THAI(tt);
        System.out.print("- Nhập mã hóa đơn: ");
        //Kiểm tra MAHD trong cả đơn nhập hàng và xuất hàng
        while(true){
            mahd = kt.KTMaSo();
            arrHoaDon[vt].setMAHD(mahd);
            if(tt ==1){
                if(TimKiem_MaSoNH("NH" + mahd, vt)!= -1){
                    System.out.print("- Mã hóa đơn bạn nhập không có. Nhập lại: ");
                }else
                    break;
            }else
                if(TimKiem_MaSoXH("XH" + mahd, vt)!= -1){
                    System.out.print("- Mã hóa đơn bạn nhập không có. Nhập lại: ");
                }else
                    break;
        }
        if(tt ==1){
            arrHoaDon[vt].NhapNH(mahd, maNV);
            arrBD.Them1(mahd, tt, arrHoaDon[vt].getMANV());
        }
        else{
            arrHoaDon[vt].NhapXH(mahd, maNV);
            arrBD.Them1(mahd, tt, arrHoaDon[vt].getMANV());
            // Trong hoá đơn xuất hàng thì Tổng tiền của hoá đơn sẽ lấy từ giá của sản phẩm
			// trong Kho * với số lượng của sản phẩm đó trong CTHD
                        arrHoaDon[vt].setTONG_TIEN(arrBD.TinhTongTienChoHD(mahd));
        }
        // Cập nhật lại ngày xuất hoá đơn trùng vs ngày hiện tại
        arrHoaDon[vt].setNgay_xuat(now);
        GhiFile();
    }
    @Override
    public void HienDS() throws IOException {
		File file = new File(filename);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileReader fr = new FileReader(file.getAbsoluteFile());
		BufferedReader br = new BufferedReader(fr);
		String data;
		int i = 0;
		// boolean check = false;
		lenFile = SoluongTrongFile();
		arrHoaDon = Arrays.copyOf(arrHoaDon, lenArr + lenFile);
		while ((data = br.readLine()) != null) {
			if (data.length() != 1) {
				arrHoaDon[i] = new HoaDon();
				Scanner scan = new Scanner(data).useDelimiter("-");
				arrHoaDon[i].setMAHD(scan.next());
				arrHoaDon[i].setNgay_xuat(scan.nextInt(), scan.nextInt(), scan.nextInt());
				arrHoaDon[i].setTRANG_THAI(scan.nextInt());
				arrHoaDon[i].setMAKH(scan.next());
				arrHoaDon[i].setMANV(scan.next());
				arrHoaDon[i++].setTONG_TIEN(scan.nextDouble());
			}
		}
		if (br != null) {
			br.close();
		}
		if (fr != null) {
			fr.close();
		}
	}
    @Override
    public void GhiFile() throws IOException {
		File file = new File(filename);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		for (int i = 0; i < lenArr + lenFile; i++) {

			String ns = arrHoaDon[i].getNgay_xuat().getNgay() + "-" + arrHoaDon[i].getNgay_xuat().getThang() + "-"
					+ arrHoaDon[i].getNgay_xuat().getNam();
			String data = arrHoaDon[i].getMAHD() + "-" + ns + "-" + arrHoaDon[i].getTRANG_THAI()+ "-"
					+ arrHoaDon[i].getMAKH() + "-" + arrHoaDon[i].getMANV() + "-" + arrHoaDon[i].getTONG_TIEN();

			bw.write(data);
			bw.newLine();
		}

		if (bw != null) {
			bw.close();
		}
		if (fw != null) {
			fw.close();
		}
	}
    @Override
    public int SoluongTrongFile() throws IOException {
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(filename);
			br = new BufferedReader(fr);
			String data;
			lenFile = 0;
			while ((data = br.readLine()) != null) {
				if (data.length() != 1) {
					lenFile++;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return lenFile;
	}

    public int TimKiem_MaSoNH(String ma, int j) {
		if (j == 0) {
			return -1;
		}
		for (int i = 0; i < j; i++) {
			if (arrHoaDon[i].getTRANG_THAI() == 1 && arrHoaDon[i].getMAHD().compareTo(ma) == 0) {
				return i;
			}
		}
		return -1;
	}

	public int TimKiem_MaSoXH(String ma, int j) {
		if (j == 0) {
			return -1;
		}
		for (int i = 0; i < j; i++) {
			if (arrHoaDon[i].getTRANG_THAI() == 2 && arrHoaDon[i].getMAHD().compareTo(ma) == 0) {
				return i;
			}
		}
		return -1;
	}
    @Override
        public int TimKiem_MaSo(String ma){
            for(int i = 0 ; i <lenArr+lenFile; i++){
                if(arrHoaDon[i].getMAHD().equalsIgnoreCase(ma)){
                    return i;
                }
            }
            return -1;
        }
// Tìm Kiếm hoá đơn từ ngày A -> B
	public void TimKiem_MinMaxNgay() throws IOException {
		NamSinh minns = new NamSinh();
		System.out.println("- Bạn  muốn tìm hóa đơn từ: ");
		minns.Nhap();
		NamSinh maxns = new NamSinh();
		System.out.println(" Đến: ");
		maxns.Nhap();
		for (int i = 0; i < lenArr + lenFile; i++) {
			if (arrHoaDon[i].getNgay_xuat().KTNguyenNgay(minns) && maxns.KTNguyenNgay(arrHoaDon[i].getNgay_xuat())) {
				System.out.println("\n============HD " + (i + 1) + "=========");
				arrHoaDon[i].Xuat();
			}
		}
	}
        // TÌm Kiếm hoá đơn từ ngày A
	public void TimKiem_MinNgay() throws IOException {
		NamSinh minns = new NamSinh();
		System.out.println("- Bạn muốn tìm hóa đơn từ: ");
		minns.Nhap();
		for (int i = 0; i < lenArr + lenFile; i++) {
			if (arrHoaDon[i].getNgay_xuat().KTNguyenNgay(minns)) {
				System.out.println("\n============HD " + (i + 1) + "=========");
				arrHoaDon[i].Xuat();
			}
		}
	}
        // Tìm Kiếm hoá đơn từ đầu đến ngày B
	public void TimKiem_MaxNgay() throws IOException {
		NamSinh maxns = new NamSinh();
		System.out.println("- Bạn muốn tìm hóa đơn đến ngày: ");
		maxns.Nhap();
		for (int i = 0; i < lenArr + lenFile; i++) {
			if (maxns.KTNguyenNgay(arrHoaDon[i].getNgay_xuat())) {
				System.out.println("\n============HD " + (i + 1) + "=========");
				arrHoaDon[i].Xuat();
			}
		}
	}
// Tìm kiếm hoá đơn trong ngày C nào đó
	public void TimKiem_Ngay(int lc, NamSinh ns) throws IOException {
		if (lc == 3)
			for (int i = 0; i < lenArr + lenFile; i++) {
				if (arrHoaDon[i].getNgay_xuat().SoSanh(ns)) {
					System.out.println("\n============HD " + (i + 1) + "=========");
					arrHoaDon[i].Xuat();
				}
			}
		else {
			for (int i = 0; i < lenArr + lenFile; i++) {
				if (arrHoaDon[i].getTRANG_THAI() == lc && arrHoaDon[i].getNgay_xuat().SoSanh(ns)) {
					System.out.println("\n============HD " + (i + 1) + "=========");
					arrHoaDon[i].Xuat();
				}
			}
		}
	}
        public void TimKiem_MANV() throws IOException {
            System.out.print("- Bạn muốn tìm hóa đơn của Nhân Viên ( 1. Nhập mã | 2. Nhập tên ): ");
		int lc = kt.KTLuaChon(2);
		String maNV = "", tenNV = "";
		// Đặt cờ hiệu
		boolean check = true;
		if (lc == 1) {
			System.out.print("- Nhập mã: ");
			maNV = kt.KT_MaNV();
		} else {
			ArrNhanVien anv = new ArrNhanVien(){};
			System.out.print("- Nhập tên: ");
			tenNV = kt.KTTen();
			// Tìm vị trí của tên NV trong danh sách
			int vtnv = anv.TimKiem_Ten(tenNV);
			if (vtnv == -1) {
				System.out.println("- Không có nhân viên nào tên : " + tenNV);

				// Set giá trị của cờ hiệu là false vì lúc này tên NV ko tồn tại
				check = false;
			} else
				// Lấy mã NV
                            maNV = anv.getArrNV()[vtnv].getMS();
		}

                for(int i = 0 ; i<lenArr+lenFile; i++){
                    if(check && arrHoaDon[i].getMANV().compareTo(maNV)==0){
                        System.out.println("\n==========HD " + ( i + 1 ) +"==========");
                        arrHoaDon[i].Xuat();
                    }
                }
	}

	public void TimKiem_MAKH() throws IOException {
            System.out.print("- Bạn muốn tìm hóa đơn của khách hàng ( 1. Nhập mã | 2. Nhập tên): ");
		int lc = kt.KTLuaChon(2);
		String maKH = "", tenKH = "";
		boolean check = true;
		if (lc == 1) {
			System.out.print("- Nhập mã: ");
			maKH = kt.KT_MaKH();
		} else {
                    ArrKhachHang ac = new ArrKhachHang() {};
                    System.out.println("- Nhập tên: ");
                    tenKH = kt.KTTen();
			// Tìm vị trí của tên KH trong danh sách
			int vtnv = ac.TimKiem_Ten(tenKH);
			if (vtnv == -1) {
				System.out.println("- Không có khách hàng thành viên nào tên : " + tenKH);

				// Set giá trị của cờ hiệu là false vì lúc này tên KH ko tồn tại
				check = false;
			} else
				// Lấy mã KH
                            maKH = ac.getArrKH()[vtnv].getMS();
		}
                for(int i = 0 ; i<lenArr +lenFile; i++){
                    if(check && arrHoaDon[i].getMAKH().compareTo(maKH)==0){
                        System.out.println("\n===========HD " + ( i + 1 ) + "==========");
                        arrHoaDon[i].Xuat();
                    }
                }
	}

	public void TimKiem_MANCC() throws IOException {
            System.out.print("- Bạn muốn tìm hóa đơn của NCC ( Nhập mã ): ");
            String maNCC = kt.KT_NCC();
            for(int i = 0 ; i < lenArr + lenFile; i++){
                if(arrHoaDon[i].getTRANG_THAI() == 2 && arrHoaDon[i].getMAKH().compareTo(maNCC)== 0)
                    System.out.println("\n ===========HD " + ( i + 1 ) + "==========");
                arrHoaDon[i].Xuat();
            }
        }
        public HoaDon TruyXuatPhanTu(int vt ){
            return arrHoaDon[vt];
        }
        public void TongDoanhThuTrongNgay(int lc) throws IOException{
            int tong = 0;
            if(lc != 3){
                for(int i = 0 ; i<lenArr+lenFile; i++){
                    if(arrHoaDon[i].getTRANG_THAI()==lc && arrHoaDon[i].getNgay_xuat().SoSanh(now)){
                        System.out.println("\n==========HD " + ( i + 1 ) + "==========");
                        arrHoaDon[i].Xuat();
                        tong += arrHoaDon[i].getTONG_TIEN();
                    }
                }
                if(lc==1)
                    System.out.println("- Tổng doanh thu hôm nay cừa hàng nhập vô là: " +tong);
                else
                    System.out.println("- Tổng doanh thu hôm nay cửa hàng xuất đi là: " +tong);
            }else{
                for(int i = 0 ; i < lenArr + lenFile; i++){
                    if(arrHoaDon[i].getTRANG_THAI()== lc &&arrHoaDon[i].getNgay_xuat().SoSanh(now)){
                        System.out.println("\n===========HD " + ( i + 1 ) + "==========");
                        arrHoaDon[i].Xuat();
                        tong += arrHoaDon[i].getTONG_TIEN();
                    }
                }
            }
        }
    @Override
        public void Xoa() {
		while (true) {
			System.out.print("- Nhập mã sản phẩm bạn muốn xóa : ");
			String ma = kt.KTMaSo();
			int vt = TimKiem_MaSo(ma);
			System.out.println(vt);
			if (vt != -1) {
				System.out.print("- Bạn có thực sự muốn xóa sản phẩm này ( y / n ): ");
				while (true) {
					String regex = "y|Y";
					String yn = kt.KTYesNo();
					if (yn.matches(regex)) {
						XoaPS(vt);
						System.out.println("- Bạn đã xoá thành công.");
						break;
					} else {
						System.out.print("- Bạn đã không xóa sách này.");
						break;
					}
				}
			} else {
				System.out.println("- Không có mã số trong danh sách. ");
			}
			System.out.print("- Bạn có muốn tiếp tục ( y / n ): ");
			String regex = "[yY]";
			String yn = kt.KTYesNo();
			if (yn.matches(regex) == false) {
				break;
			}
		}
	}

	public void XoaPS(int vt) {
		for (int i = vt; i < lenArr - 1 + lenFile; i++) {
		arrHoaDon[i] = arrHoaDon[i+1];
		}
		lenArr--;
	}
    @Override
    public void Sua() throws IOException {

		System.out.print("- Nhập mã sản phẩm muốn sửa: ");
		String ma = kt.KTMaSo();
		int vt;
		if ((vt = TimKiem_MaSo(ma)) != -1) {
			System.out.print("- Bạn có chắc muốn sửa thông tin của sản phẩm này ( y / n ): ");
			while (true) {
				String regex = "[yY]";
				String yn = kt.KTYesNo();
				if (yn.matches(regex)) {
					for (int i = 0; i < lenArr + lenFile; i++) {
						if (i == vt) {
							arrHoaDon[i] = MenuSua(arrHoaDon[i]);
						}
					}
					System.out.println("- Bạn đã sửa thành công. ");
					break;
				} else {
					System.out.print("- Sửa thất bại.");
					break;
				}
			}
		} else {
			System.out.println("- Mã sản phẩm bạn tìm không có trong danh sách. ");
		}
	}

	public HoaDon MenuSua(HoaDon hd) throws IOException {
		int lc;
		String temp;
		do {
			System.out.println("** Bạn muốn sửa thông tin nào: ");
			System.out.println("1.Mã HD.");
			System.out.println("2.Mã KH.");
			System.out.println("3.Mã NV.");
			System.out.println("4.Trạng Thái");
			System.out.println("5.Thoát");
			System.out.print("- Nhập lựa chọn của bạn");
			lc = kt.KTLuaChon(4);
			switch (lc) {
			case 1: {
				System.out.print("- Nhập Mã HD : ");
				temp = sc.nextLine();
				hd.setMAHD(temp);
				break;
			}
			case 2: {
				System.out.print("- Nhập Mã KH: ");
				temp = kt.KTMaSo();
				hd.setMAKH(temp);
				break;
			}
			case 3: {
				System.out.print("- Nhập Mã NV: ");
				temp = sc.nextLine();
				hd.setMANV(temp);
				break;
			}

			case 4: {
				System.out.print("- Nhập Trạng Thái: ");
				int tt = kt.KTLuaChon(2);
				hd.setTRANG_THAI(tt);
				break;
			}
			case 5: {
				System.out.println("- Bạn đã thoát. ");
                                GhiFile();
				break;
			}
			}
		} while (lc != 4);
		return hd;
	}
public void Xuat(int lc) throws IOException {
		if (SoluongTrongFile() == 0 && lenArr == 0) {
			String yn;
                        System.out.print("- Danh sách hóa đơn chưa có đơn hàng. Bạn có muốn thêm nhân viên ( y / n ): ");
			while (true) {
				String regex = "[yY]";
				yn = kt.KTYesNo();
				if (yn.matches(regex)) {
					ThemDT();
					break;
				} else {
					break;
				}
			}
		}

		if (lc == 3) {

			for (int j = 0; j < lenArr + lenFile; j++) {
				System.out.println("\n============HD " + (j + 1) + "=========");
				arrHoaDon[j].Xuat();
			}
		} else {
			for (int j = 0; j < lenArr + lenFile; j++) {
				if (arrHoaDon[j].getTRANG_THAI() == lc) {
					System.out.println("\n============HD " + (j + 1) + "=========");
					arrHoaDon[j].Xuat();
				}
			}
		}
	}
public void MenuChinh() throws IOException {
		int lc = 0;
		boolean check = false;
		do {
			System.out.println("=============================MENU============================");
			System.out.println("1.Tìm kiếm hoá đơn.");
			System.out.println("2.Xuất.");
			System.out.println("3.Xoá.");
			System.out.println("4.Sửa.");
			System.out.println("5.Thoát.");
			System.out.print("- Nhập Lựa Chọn của bạn");
			lc = kt.KTLuaChon(5);
			switch (lc) {
			case 1: {
				int lctk = 0;
				do {
					System.out.println("=====MENU=====");
					System.out.println("1. Tìm kiếm theo Mã NV.");
					System.out.println("2. Tìm kiếm theo Mã KH.");
					System.out.println("3. Tìm kiếm theo ngày.");
					System.out.println("4. Thoát.");
					System.out.print("- Nhập lựa chọn của bạn");
					lctk = kt.KTLuaChon(4);
					switch (lctk) {
					case 1: {
						TimKiem_MANV();
						kt.Phim();
						break;
					}

					case 2: {
						TimKiem_MAKH();
						kt.Phim();
						break;
					}
					case 3: {
						int lcn = 0;
						String thoat;
						System.out.println("=====MENU=====");
						System.out.println("** Chọn định dạng **");
						System.out.println("1. Từ ngày A -> B.");
						System.out.println("2. Từ ngày A .");
						System.out.println("3. Từ đầu đến ngày B.");
						System.out.println("4. Trong ngày C nào đó.");
						System.out.println("5. Thoát.");
						System.out.print("- Nhập lựa chọn của bạn");
						lcn = kt.KTLuaChon(5);
						switch (lcn) {
						case 1: {
							TimKiem_MinMaxNgay();
							kt.Phim();
							break;
						}
						case 2: {
							TimKiem_MinNgay();
							kt.Phim();
							break;
						}
						case 3: {
							TimKiem_MaxNgay();
							kt.Phim();
							break;
						}
						case 4: {
							NamSinh ns = new NamSinh();
							System.out.println("- Bạn muốn tìm hóa đơn ngày: ");
							ns.Nhap();
							TimKiem_Ngay(3, ns);
							kt.Phim();
							break;
						}
						case 5: {
							System.out.println("- Bạn đã thoát . ");
							thoat = kt.Out();
							kt.Phim();
							break;
						}
						}
						break;
					}

					}
				} while (lctk != 4);
				break;
			}
			case 2: {
				System.out.print("- Ban muon xuat hoa don 1.Nhap  2.Xuat  3.Tat ca");
				int tt = kt.KTLuaChon(3);
				Xuat(tt);
				kt.Phim();
				break;
			}
			case 3: {
				Xoa();
				kt.Phim();
				break;
			}
			case 4: {
				Sua();
				kt.Phim();
				break;
			}
			case 5: {
				System.out.print("- Bạn có muốn ghi vào cơ sở dữ liệu ko( y / n ) ??? : ");
				String s = kt.KTYesNo();
				String regex = "y|Y";
				if (s.matches(regex)) {
					GhiFile();
				}
				System.out.println("- Bạn đã thoát.");
		
				break;
			}

			}
		} while (lc != 5);
	}
    @Override
  public void TimKiem_Ten(){}
  
}
