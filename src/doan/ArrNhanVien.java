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
import java.util.Scanner;

/**
 *
 * @author Tuấnn Phạm
 */
public abstract class ArrNhanVien implements Arr {

    private int lenArr = 0;
    private int lenFile = 0;
    private NhanVien[] ArrNV = new NhanVien[0];
    private final String filename = "NhanVien.txt";

    Scanner sc = new Scanner(System.in);
    KT_NhapXuat kt = new KT_NhapXuat();

    public ArrNhanVien(int len, NhanVien[] ArrNV) {
        this.lenArr = len;
        ArrNV = ArrNV;
    }

    public int getLen() {
        return lenArr;
    }

    public void setLen(int len) {
        this.lenArr = len;
    }

    public NhanVien[] getArrNV() {
        return ArrNV;
    }

    public void setArrNV(NhanVien[] ArrNV) {
        this.ArrNV = ArrNV;
    }

    public ArrNhanVien() throws IOException {
        HienDS();
    }

    @Override
    public void ThemDT() throws IOException {
        System.out.print("- Nhập số lượng nhân viên: ");
        lenArr = kt.KTNhapInt(1);
        ArrNV = Arrays.copyOf(ArrNV, lenArr + lenFile);
        for (int i = lenFile; i < lenArr + lenFile; i++) {
            System.out.printf("\n%60s %d%s\n\n", "------------NV", i + 1, "------------");
            ArrNV[i] = new NhanVien();
            ArrNV[i].Nhap();
            ArrNV[i].setMS("" + (i + 1));
//            GhiFile();
        }
    }
// Thêm 1 nhà cung cấp vô file khi nhập trong Class sản phẩm nếu ko có sẽ thêm vô File
	public void Them1(String ma) throws IOException {
		lenArr += 1;
		ArrNV = Arrays.copyOf(ArrNV, lenArr + lenFile);
		ArrNV[lenArr + lenFile - 1] = new NhanVien();
		ArrNV[lenArr + lenFile - 1].Nhap();
		ArrNV[lenArr + lenFile - 1].setMS("" + (lenArr + lenFile));
		GhiFile();
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
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return lenFile;
    }
    
    @Override
    public void HienDS() throws IOException {
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        String data;
        int i = 0;
        lenFile = SoluongTrongFile();
        ArrNV = Arrays.copyOf(ArrNV, lenArr + lenFile);
        while ((data = br.readLine()) != null) {
            if (data.length() != 1) {
                ArrNV[i] = new NhanVien();
                Scanner sc = new Scanner(data).useDelimiter("-");
                ArrNV[i].setMS(sc.next());
                ArrNV[i].setTen(sc.next());
                ArrNV[i].setGioiTinh(sc.nextInt());
                ArrNV[i].setNgaySinh(sc.nextInt(), sc.nextInt(), sc.nextInt());
                ArrNV[i].setDiaChi(sc.next());
                ArrNV[i++].setDienThoai(sc.next());
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
            String ns = ArrNV[i].getNgaySinh().getNgay() + "-" + ArrNV[i].getNgaySinh().getThang() + "-" + ArrNV[i].getNgaySinh().getNam();
            String data = (int) (i + 1) + "-" + ArrNV[i].getTen() + "-" + ArrNV[i].getGioiTinh() + "-" + ns + "-" + ArrNV[i].getDiaChi() + "-" + ArrNV[i].getDienThoai();
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
    public int TimKiem_MaSo(String ma) {
        for (int i = 0; i < lenArr + lenFile; i++) {
            if (ArrNV[i].getMS().compareTo(ma) == 0) {
                return i;
            }
        }
        return -1;
    }

    public int TimKiem_Ten(String ten) {
        for (int i = 0; i < lenArr + lenFile; i++) {
            if (ArrNV[i].getTen().compareTo(ten) == 0) {
                return i;
            }
        }
        return -1;
    }

    public void TimKiem_MS() throws IOException {
        System.out.print("- Nhập mã nhân viên cần tìm: ");
        String ma = sc.nextLine();
        int dem = 0;
        for (int i = 0; i < lenArr + lenFile; i++) {
            if (ArrNV[i].getMS().compareTo(ma) == 0) {
                ArrNV[i].Xuat();
                dem++;
            }
        }
        if (dem == 0) {
            System.out.printf("-Không có mã %s .", ma);
        }
    }

    @Override
    public void TimKiem_Ten() {
        System.out.print("- Nhập tên nhân viên cần tìm: ");
        String kitu = kt.KTTen();
        int dem = 0;
        for (int i = 0; i < lenArr + lenFile; i++) {
            if (ArrNV[i].getTen().compareTo(kitu) == 0) {
                ArrNV[i].Xuat();
                dem++;
            }
        }
        if (dem == 0) {
            System.out.printf("- Không có nhân viên có tên %s .", kitu);
        }
    }

    @Override
    public void Xoa() throws IOException {
        while (true) {
            Xuat();
            System.out.print("- Nhập mã nhân viên bạn muốn xóa: ");
            String ma = kt.KTMaSo();
            int vt = TimKiem_MaSo(ma);
            if (vt != -1) {
                System.out.println("- Tìm thấy nhân viên ở vị trí: "+vt);
                System.out.print("- Bạn có chắc muốn xóa nhân viên ?? ( y / n )");
                while (true) {
                    String regex = "y|Y";
                    String yn = kt.KTYesNo();
                    if (yn.matches(regex)) {
                        XoaNV(vt);
                        System.out.println("- Xóa thành công.");
                        break;
                    } else {
                        System.out.println("- Xóa thất bại.");
                        break;
                    }
                }
            } else {
                System.out.println("- Không có mã nhân viên trong danh sách.");
            }
            System.out.print("- Bạn có muốn tiếp tục ( y / n ): ");
            String regex = "[yY]";
            String yn = kt.KTYesNo();
            if (yn.matches(regex) == false) {
                break;
            }
        }
    }

    public void XoaNV(int vt) {
        for (int i = vt; i < lenArr - 1 + lenFile; i++) {
            ArrNV[i] = ArrNV[i + 1];
        }
        lenArr--;
    }

    @Override
    public void Sua() throws IOException {
        while(true){
            Xuat();
        System.out.print("- Nhập mã nhân viên cần sửa: ");
        String ma = kt.KTMaSo();
        int vt = TimKiem_MaSo(ma);      
        if ((vt = TimKiem_MaSo(ma)) != -1) {
            System.out.println("- Tìm thấy mã nhân viên tại vị trí: " + vt);
            System.out.print("- Bạn có chắc muốn sửa thông tin của nhân viên này ?? ( y / n ): ");
            while (true) {
                String regex = "[yY]";
                String yn = kt.KTYesNo();
                if (yn.matches(regex)) {
                    for (int i = 0; i < lenArr + lenFile; i++) {
                        if (i == vt) {
                            ArrNV[i] = MenuSua(ArrNV[i]);
                        }
                    }
                    System.out.println("- Bạn đã sửa thành công.");
                    break;
                } else {
                    System.out.println("- Người dùng hủy thao tác.");
                    break;
                }
            }
        } else {
            System.out.println("- Mã nhân viên không có trong danh sách.");
        }
                System.out.print("- Bạn có muốn tiếp tục ? ( y / n ): ");
            String regex = "y|y";
            String yn = kt.KTYesNo();
            if (yn.matches(regex) == false) {
                break;
            }
        }
    }

    public NhanVien MenuSua(NhanVien nv) {
        int lc;
        String temp;
        do {
            System.out.println("- Bạn muốn sửa thông tin của: ");
            System.out.println("1. Họ Tên.");
            System.out.println("2. Giới Tính.");
            System.out.println("3. Ngày Sinh.");
            System.out.println("4. Địa Chỉ.");
            System.out.println("5. Số Điện Thoại.");
            System.out.println("6. Tất cả.");
            System.out.println("7. Thoát.");
            System.out.print("- Nhập lựa chọn của bạn: ");
            lc = kt.KTLuaChon(7);
            switch (lc) {
                case 1: {
                    System.out.print("- Nhập tên mới: ");
                    temp = kt.KTTen();
                    nv.setTen(temp);
                    break;
                }
                case 2: {
                    System.out.print("- Nhập ( 1.Nam || 2.Nữ ): ");
                    int kitu = kt.KTLuaChon(2);
                    nv.setGioiTinh(kitu);
                    break;
                }
                case 3: {
                    System.out.print("- Nhập ngày sinh: ");
                    NamSinh ns = new NamSinh();
                    ns.Nhap();
                    nv.setNgaySinh(ns.getNgay(), ns.getThang(), ns.getNam());
                    break;
                }
                case 4: {
                    System.out.print("- Nhập địa chỉ: ");
                    temp = sc.nextLine();
                    nv.setDiaChi(temp);
                    break;
                }
                case 5: {
                    System.out.print("- Nhập số điện thoại: ");
                    temp = kt.KTDienThoai();
                    nv.setDienThoai(temp);
                    break;
                }
                case 6: {
                    System.out.print("- Nhập tất cả: ");
                    NhanVien n = new NhanVien();
                    n.Nhap();
                    nv = n;
                    break;
                }
                case 7: {
                    System.out.println("- Bạn đã thoát.");
                    break;
                }
            }
        } while (lc != 7);
        return nv;
    }

    public void Xuat() throws IOException {
        if (SoluongTrongFile() == 0 && lenArr == 0) {
            String yn;
            System.out.print("- Danh sách chưa có nhân viên. Bạn có muốn thêm mới ( y / n ): ");
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
        for (int j = 0; j < lenArr + lenFile ; j++) {
            ArrNV[j].Xuat();
        }
    }

    public void MenuChinh() throws IOException {
        int lc = 0;
        boolean check = false;
        do {
            System.out.println("====================MENU====================");
            System.out.println("1. Nhập.");
            System.out.println("2. Xuất.");
            System.out.println("3. Xóa.");
            System.out.println("4. Sửa.");
            System.out.println("5. Thoát.");
            System.out.print("- Nhập lựa chọn cũa bạn: ");
            lc = kt.KTLuaChon(5);
            switch (lc) {
                case 1: {
                    ThemDT();
                    kt.Phim();
                    break;
                }
                case 2: {
                    Xuat();
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
                    System.out.print("- Bạn có muốn ghi vào CSDL không ( y / n ): ");
                    String s = kt.KTYesNo();
                    String regex = "y|Y";
                    if (s.matches(regex)) {
                        GhiFile();
                    }
                    System.err.println("- Bạn đã thoát.");
                    break;
                }
            }
        } while (lc != 5);
    }
}
