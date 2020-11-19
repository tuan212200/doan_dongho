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
public abstract class ArrKhachHang implements Arr {

    private int lenArr = 0;
    private int lenFile = 0;
    private KhachHang[] ArrKH = new KhachHang[0];
    private final String filename = "KhachHang.txt";
    Scanner sc = new Scanner(System.in);
    KT_NhapXuat kt = new KT_NhapXuat();

    public int getLenArr() {
        return lenArr;
    }

    public void setLenArr(int lenArr) {
        this.lenArr = lenArr;
    }

    public int getLenFile() {
        return lenFile;
    }

    public void setLenFile(int lenFile) {
        this.lenFile = lenFile;
    }

    public KhachHang[] getArrKH() {
        return ArrKH;
    }

    public void setArrKH(KhachHang[] ArrKH) {
        this.ArrKH = ArrKH;
    }

    public ArrKhachHang() throws IOException {
        HienDS();
    }

    @Override
    public void ThemDT() {
        System.out.print("- Nhập số lượng khách hàng: ");
        lenArr = kt.KTNhapInt(1);
        ArrKH = Arrays.copyOf(ArrKH, lenArr + lenFile);//kéo dài mảng ra
        for (int i = lenFile; i < lenArr + lenFile; i++) {
            System.out.printf("\n%60s %d%s\n\n", "------------KH", i + 1, "------------");
            ArrKH[i] = new KhachHang();
            ArrKH[i].Nhap();
            ArrKH[i].setMS("" + (i + 1));
        }
    }

        public void Them1(String ma) throws IOException{
        lenArr +=1;
        ArrKH = Arrays.copyOf(ArrKH, lenArr + lenFile);
        ArrKH[lenArr+lenFile - 1] = new KhachHang();
        ArrKH[lenArr+lenFile-1].Nhap();
        ArrKH[lenArr+lenFile-1].setMS("" + (lenArr+lenFile));//biến thành chuỗi
        GhiFile();
        
    }
    @Override
    public void HienDS() throws IOException {
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        String data;
        int i = 0;
        lenFile = SoluongTrongFile();
        ArrKH = Arrays.copyOf(ArrKH, lenArr + lenFile);
        while ((data = br.readLine()) != null) {//doc het file
            if (data.length() != 1) {
                ArrKH[i] = new KhachHang();
                Scanner sc = new Scanner(data).useDelimiter("-");
                ArrKH[i].setMS(sc.next());
                ArrKH[i].setTen(sc.next());
                ArrKH[i].setGioiTinh(sc.nextInt());
                ArrKH[i].setNgaySinh(sc.nextInt(), sc.nextInt(), sc.nextInt());
                ArrKH[i].setDiaChi(sc.next());
                ArrKH[i++].setDienThoai(sc.next());
            }
        }
        br.close();
        fr.close();
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
            String ns = ArrKH[i].getNgaySinh().getNgay() + "-" + ArrKH[i].getNgaySinh().getThang() + "-" + ArrKH[i].getNgaySinh().getNam();
            String data = (int) (i + 1) + "-" + ArrKH[i].getTen() + "-" + ArrKH[i].getGioiTinh() + "-" + ns + "-" + ArrKH[i].getDiaChi() + "-" + ArrKH[i].getDienThoai();
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
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return lenFile;
    }

    @Override
    public int TimKiem_MaSo(String ma) {
        for (int i = 0; i < lenArr + lenFile; i++) {
            if (ArrKH[i].getMS().equalsIgnoreCase(ma)) {
                return i;
            }
        }
        return -1;
    }

    public int TimKiem_Ten(String ten) {
        for (int i = 0; i < lenArr + lenFile; i++) {
            if (ArrKH[i].getTen().compareTo(ten) == 0) {
                return i;
            }
        }
        return -1;
    }

    public void TimKim_MS() throws IOException {
        System.out.print("- Nhập mã khách hàng cần tìm: ");
        String ma = sc.nextLine();
        int dem = 0;
        for (int i = 0; i < lenArr + lenFile; i++) {
            if (ArrKH[i].getMS().compareTo(ma) == 0) {
                ArrKH[i].Xuat();
                dem++;
            }
        }
        if (dem == 0) {
            System.out.printf("- Không có mã %s. ", ma);
        }
    }

    public void TimKiem_Ten() {
        System.out.print("- Nhập tên khách hàng bạn cần tìm: ");
        String ma = kt.KTTen();
        int dem = 0;
        for (int i = 0; i < lenArr + lenFile; i++) {
            if (ArrKH[i].getTen().compareTo(ma) == 0) {
                ArrKH[i].Xuat();
                dem++;
            }
        }
        if (dem == 0) {
            System.out.printf("- Không có khách hàng nào tên %s. ", ma);
        }
    }

    public void XoaPS(int vt) {
        for (int i = vt; i < lenArr - 1 + lenFile; i++) {
            ArrKH[i] = ArrKH[i + 1];
        }
        lenArr--;
    }

    @Override
    public void Xoa() throws IOException {
        while (true) {
                    Xuat();
            System.out.print("- Nhập mã khách hàng bạn muốn xóa: ");
            String ma = kt.KTMaSo();
            int vt = TimKiem_MaSo(ma);     
            if (vt != -1) {
                 System.out.println("- Tìm thấy khách hàng ở vị trí: " + vt);
                System.out.print("- Bạn có thực sự muốn xóa khách hàng ? ( y / n ): ");
                while (true) {
                    String regex = "y|Y";
                    String yn = kt.KTYesNo();
                    if (yn.matches(regex)) {
                        XoaPS(vt);
                        System.out.println("- Bạn đã xóa thành công.");
                        break;
                    } else {
                        System.out.println("- Bạn đã không xóa khách hàng này.");
                        break;
                    }
                }
            } else {
                System.out.println("- Không có mã khách hàng trong danh sách.");
            }
            System.out.print("- Bạn có muốn tiếp tục ? ( y / n ): ");
            String regex = "y|y";
            String yn = kt.KTYesNo();
            if (yn.matches(regex) == false) {
                break;
            }
        }
    }

    @Override
    public void Sua() throws IOException {
        while(true){
                    Xuat();
        System.out.print("- Nhập mã khách hàng muốn sửa: ");
        String ma = kt.KTMaSo();
        int vt = TimKiem_MaSo(ma);
        if ((vt = TimKiem_MaSo(ma)) != -1) {
                    System.out.println("Tìm thấy khách hàng ở vị trí: " + vt );
            System.out.print("- Bạn có chắc muốn sửa thông tin của khách hàng? ( y / n ); ");
            while (true) {
                String regex = "y|Y";
                String yn = kt.KTYesNo();
                if (yn.matches(regex)) {
                    for (int i = 0; i < lenArr + lenFile; i++) {
                        if (i == vt) {
                            ArrKH[i] = MenuSua(ArrKH[i]);
                        }
                    }
                    System.out.println("- Bạn đã sửa thành công.");
                    break;
                } else {
                    System.out.println("- Bạn đã không xóa khách hàng này.");
                    break;
                }
            }
        } else {
            System.out.println("- Mã khách hàng bạn tìm không có trong danh sách.");
        }
            System.out.print("- Bạn có muốn tiếp tục ? ( y / n ): ");
            String regex = "[yY]";
            String yn = kt.KTYesNo();
            if(yn.matches(regex)==false){
                break;
            }
    }
    }
    public KhachHang MenuSua(KhachHang kh) {
        int lc;
        String temp;
        do {
            System.out.println("- Bạn muốn sửa thông tin của: ");
            System.out.println("1.Họ Tên.");
            System.out.println("2.Giới Tính.");
            System.out.println("3.Ngày Sinh.");
            System.out.println("4.Địa Chỉ.");
            System.out.println("5.Số Điện Thoại.");
            System.out.println("6.Toàn Bộ");
            System.out.println("7.Thoát");
            System.out.print("- Nhập lựa chọn của bạn");
            lc = kt.KTLuaChon(7);
            switch (lc) {
                case 1: {
                    System.out.print("- Nhập tên mới: ");
                    temp = kt.KTTen();
                    kh.setTen(temp);
                    break;
                }
                case 2: {
                    System.out.print("- Nhập (1. Nam | 2. Nữ ): ");
                    int i = kt.KTLuaChon(2);
                    kh.setGioiTinh(i);
                    break;
                }
                case 3: {
                    System.out.print("- Nhập ngày sinh: ");
                    NamSinh ns = new NamSinh();
                    ns.Nhap();
                    kh.setNgaySinh(ns.getNgay(), ns.getThang(), ns.getNam());
                    break;
                }
                case 4: {
                    System.out.print("- Nhập địa chỉ: ");
                    temp = sc.nextLine();
                    kh.setDiaChi(temp);
                    break;
                }
                case 5: {
                    System.out.print("- Nhập số điện thoại: ");
                    temp = kt.KTDienThoai();
                    kh.setDienThoai(temp);
                    break;
                }
                case 6: {
                    System.out.print("- Nhập tất cả: ");
                    KhachHang k = new KhachHang();
                    k.Nhap();
                    kh = k;
                    break;
                }
                case 7: {
                    System.out.println("- Bạn đã thoát.");
                    break;
                }
            }
        } while (lc != 7);
        return kh;
    }

    public void Xuat() throws IOException {
        if (SoluongTrongFile() == 0 && lenArr == 0) {
            String yn;
            System.out.print("- Danh sách khách hàng trống. Bạn có muốn thêm ( y / n ): ");
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
        for (int i = 0; i < lenArr + lenFile; i++) {
            ArrKH[i].Xuat();
        }
    }

    public void MenuChinh() throws IOException {
        int lc = 0;
        do {
            System.out.println("\n===============MENU===============");
            System.out.println("1. Nhập.");
            System.out.println("2. Xuất.");
            System.out.println("3. Xóa.");
            System.out.println("4. Sửa.");
            System.out.println("5. Thoát.");
             System.out.print("- Nhập lựa chọn của bạn: ");
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
                    System.out.print("- Bạn có muốn ghi vào cơ sở dữ liệu không? ( y / n ): ");
                    String yn = kt.KTYesNo();
                    String regex = "y|Y";
                    if (yn.matches(regex)) {
                        GhiFile();
                    }
                    System.err.println("- Bạn đã thoát.");
                    break;
                }
            }
        } while (lc != 5);
    }
}
