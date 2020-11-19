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
public class ArrTaiKhoan implements Arr {

    private int lenArr = 0;
    private int lenFile = 0;
    private TaiKhoan[] arrTK = new TaiKhoan[1];
    private static final String filename = "TaiKhoan.txt";
    Scanner sc = new Scanner(System.in);
    KT_NhapXuat kt = new KT_NhapXuat();

    public ArrTaiKhoan() throws IOException {
        HienDS();
    }

    @Override
    public void ThemDT() throws IOException {
        String ma;
        System.out.print("- Nhập số lượng tài khoản muốn thêm vào danh sách");
        lenArr = kt.KTNhapInt(1);
        arrTK = Arrays.copyOf(arrTK, lenArr + lenFile);
        for (int i = lenFile; i < lenArr + lenFile; i++) {
            System.out.printf("\n%60s %d%s\n\n", "------------TaiKhoan", i + 1, "------------");
            System.out.print("- Username: ");
            ma = kt.KTTaiKhoan(sc.nextLine(), i);
            arrTK[i] = new TaiKhoan(ma);
            arrTK[i].Nhap();
        }
    }

    public void Them1(String ma) throws IOException {
        lenArr += 1;
        arrTK = Arrays.copyOf(arrTK, lenArr + lenFile);
        int vt = lenArr + lenFile - 1;
        ma = kt.KTTaiKhoan(ma, vt);
        arrTK[vt] = new TaiKhoan(ma);
        arrTK[vt].Nhap();
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
        arrTK = Arrays.copyOf(arrTK, lenArr + lenFile);
        while ((data = br.readLine()) != null) {
            if (data.length() != 1) {
                arrTK[i] = new TaiKhoan();
                Scanner scan = new Scanner(data).useDelimiter("-");
                arrTK[i].setUsername(scan.next());
                arrTK[i].setPassword(scan.next());
                arrTK[i].setMANV(scan.next());
                arrTK[i++].setQuyen(scan.nextInt());
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
            String data = arrTK[i].getUsername() + "-" + arrTK[i].getPassword() + "-" + arrTK[i].getMANV() + "-"
                    + arrTK[i].getQuyen();
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

    @Override
    public int TimKiem_MaSo(String ma) {
        for (int i = 0; i < lenArr + lenFile; i++) {
            if (arrTK[i].getUsername().compareTo(ma) == 0) {
                return i;
            }
        }
        return -1;

    }

    public int TimKiem_MaSo(String ma, int j) {
        if (j == 0) {
            return -1;
        }
        for (int i = 0; i < j; i++) {
            if (arrTK[i].getUsername().compareTo(ma) == 0) {
                return i;
            }
        }
        return -1;

    }

    public void TimKiem_MS() throws IOException {
        System.out.print("-Nhập username bạn muốn tìm :");
        String ma = sc.nextLine();
        int dem = 0;
        for (int i = 0; i < lenArr + lenFile; i++) {
            if (arrTK[i].getUsername().compareTo(ma) == 0) {
                arrTK[i].Xuat();
                dem++;
            }
        }
        if (dem == 0) {
            System.out.printf("- Không có mã %s .", ma);
        }
    }

    @Override
    public void TimKiem_Ten() {
    }

    public int TimKiem_MK(String mk) {
        for (int i = 0; i < lenArr + lenFile; i++) {
            if (arrTK[i].getPassword().compareTo(mk) == 0) {
                return i;
            }
        }
        return -1;
    }
    // Kiểm tra tài khoản khi nhập vô có thỏa mãn không

    public String DangNhap() throws IOException {
        ArrNhanVien nv = new ArrNhanVien() {
        };
        String user, pass, manv;
        System.out.print("- Tên đăng nhập: ");
        user = kt.KTTaiKhoanCo(sc.nextLine());
        System.out.print("- Mật khẩu : ");
        int vt = TimKiem_MaSo(user);
        pass = kt.KTMKCo(sc.nextLine(), vt);
        if (arrTK[vt].getQuyen() != 1) {
            //lấy MANV của user
            manv = arrTK[vt].getMANV();
            return manv; // trả về tên của nhân viên đó́
        }
        return "admin"; // admin
    }

    public void DangKi() throws IOException {
        System.out.print("- Tên đăng nhập: ");
        String user = kt.KTTaiKhoan(sc.nextLine());
        Them1(user);
    }

    // Truy xuất tài khoản theo tài khoản username
    public TaiKhoan TruyXuatPT(String ma) throws IOException {
        ma = kt.KTTaiKhoan(ma);
        for (int i = 0; i < lenArr + lenFile; i++) {
            if (arrTK[i].getUsername().equalsIgnoreCase(ma)) {
                return arrTK[i];
            }
        }
        return null;
    }

//	Truy xuất tài khoản theo vị trí
    public TaiKhoan TruyXuatPT(int vt) throws IOException {
        return arrTK[vt];
    }

    @Override
    public void Xoa() throws IOException {
        while (true) {
            System.out.print("- Nhập tên user bạn muốn xóa : ");
            String user = kt.KTTaiKhoanCo(sc.nextLine());
            int vt = TimKiem_MaSo(user);
            System.out.println(vt);
            if (vt != -1) {
                System.out.print("- Bạn có thực sự muốn xóa tài khoản này ( y / n ): ");
                while (true) {
                    String regex = "y|Y";
                    String yn = kt.KTYesNo();
                    if (yn.matches(regex)) {
                        XoaPS(vt);
                        System.out.println("- Bạn đã xoá thành công.");
                        break;
                    } else {
                        System.out.print("- Bạn đã không xóa tài khoản này.");
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
            arrTK[i] = arrTK[i + 1];
        }
        lenArr--;
    }

    @Override
    public void Sua() throws IOException {
        System.out.print("- Nhập tên đăng nhập muốn sửa: ");
        String user = kt.KTTaiKhoanCo(sc.nextLine());
        int vt;
        if ((vt = TimKiem_MaSo(user)) != -1) {
            System.out.print("- Bạn có chắc muốn sửa thông tin của USER này ( y / n ): ");
            while (true) {
                String regex = "[yY]";
                String yn = kt.KTYesNo();
                if (yn.matches(regex)) {
                    for (int i = 0; i < lenArr + lenFile; i++) {
                        if (i == vt) {
                            arrTK[i] = MenuSua(arrTK[i]);
                        }
                    }
                    System.out.println("- Bạn đã sửa thành công. ");
                    break;
                } else {
                    System.out.print("- Bạn đã không sửa user này.");
                    break;
                }
            }
        } else {
            System.out.println("- Mã USER bạn tìm không có trong danh sách. ");
        }
    }

    public TaiKhoan MenuSua(TaiKhoan tk) throws IOException {
        int lc;
        String temp;
        do {
            System.out.println("** Bạn muốn sửa thông tin nào: ");
            System.out.println("1.Tên đăng nhập.");
            System.out.println("2.Mật khẩu.");
            System.out.println("3. Quyền.");
            System.out.println("4.Thoát");
            System.out.print("- Nhập lựa chọn của bạn");
            lc = kt.KTLuaChon(4);
            switch (lc) {
                case 1: {
                    System.out.print("- Tên đăng nhập mới : ");
                    tk.setUsername(sc.nextLine());
                    break;
                }
                case 2: {
                    System.out.print("- Mật khẩu mới: ");
                    tk.setPassword(sc.nextLine());
                    break;
                }
                case 3: {
                    System.out.println("- Quyền hiện tại là : " + tk.KTQuyen());
                    System.out.print("- Bạn muốn đổi thành ( 1. Admin   2. User)");
                    tk.setQuyen(kt.KTLuaChon(2));
                    break;
                }

                case 4: {
                    System.out.println("- Bạn đã thoát.");
                    GhiFile();
                    break;
                }
            }
        } while (lc != 4);
        return tk;
    }

    public void Xuat() throws IOException {
        for (int j = 0; j < lenArr + lenFile; j++) {
            arrTK[j].Xuat();
        }
    }

    public void MenuChinh() throws IOException {
        int lc = 0;
        do {
            System.out.println("\n=============================MENU============================");
            System.out.println("1.Thêm");
            System.out.println("2.Xuất.");
            System.out.println("3.Xoá.");
            System.out.println("4.Sửa.");
            System.out.println("5.Thoát.");
            System.out.print("- Nhập Lựa Chọn của bạn");
            lc = kt.KTLuaChon(5);
            switch (lc) {
                case 1: {
                    DangKi();
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
}
