/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Tuấnn Phạm
 */
public abstract class ArrLoaiSanPham implements Arr {

    private int lenArr = 0;
    private int lenFile = 0;
    private LoaiSanPham[] ArrLSP = new LoaiSanPham[1];
    private static final String filename = "LoaiSanPham.txt";
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

    public LoaiSanPham[] getArrLSP() {
        return ArrLSP;
    }

    public void setArrLSP(LoaiSanPham[] ArrLSP) {
        this.ArrLSP = ArrLSP;
    }

    public ArrLoaiSanPham() throws IOException {
        HienDS();
    }

    @Override
    public void HienDS() throws IOException {
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        String data;
        int i = 0;
        lenFile = SoluongTrongFile();
        ArrLSP = Arrays.copyOf(ArrLSP, lenArr + lenFile);
        while ((data = br.readLine()) != null) {
            if (data.length() != 1) {
                ArrLSP[i] = new LoaiSanPham();
                Scanner sc = new Scanner(data).useDelimiter("-");
                ArrLSP[i].setMaLoai(sc.next());
                ArrLSP[i++].setTenLoai(sc.next());
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
            String data = ArrLSP[i].getMaLoai() + "-" + ArrLSP[i].getTenLoai();
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

        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {

                }
            }
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {

                }
            }
        }
        return lenFile;
    }

    @Override
    public void ThemDT() throws IOException {
        System.out.print("- Nhập số lượng loại sản phẩm có trong danh sách: ");
        lenArr = kt.KTNhapInt(1);
        ArrLSP = Arrays.copyOf(ArrLSP, lenArr + lenFile);
        for (int i = lenFile; i < lenArr + lenFile; i++) {
            System.out.printf("\n%60s %d%s\n\n", "------------Loại", i + 1, "------------");
            ArrLSP[i] = new LoaiSanPham();
            ArrLSP[i].Nhap();
            
             System.out.print("- Bạn có muốn ghi vào cơ sở dữ liệu không? ( y / n ): ");
                    String yn = kt.KTYesNo();
                    String regex = "[yY]";
                    if (yn.matches(regex)) {
                        GhiFile();
                        System.err.println("- Thêm thành công.");
                    }
                    else{
                        break;
                    }
            
            while (true) {
                if (TimKiem_MaSoTL(ArrLSP[i].getMaLoai(), i) != -1) {
                    System.out.print("- Mã loại sản phẩm đã tồn tại. Nhập lại: ");
                    ArrLSP[i].setMaLoai(sc.nextLine());
                } else {
                    break;
                }
            }
        }
    }
//Thêm 1 thể loại vô file khi nhập trong class SanPham không có
    public void Them1(String ma) throws IOException{
        lenArr +=1;
        ArrLSP = Arrays.copyOf(ArrLSP, lenArr + lenFile);
        ArrLSP[lenArr+lenFile - 1] = new LoaiSanPham();
        System.out.print("- Nhập tên Loại sản phẩm: ");
        String ten = kt.KTTen();
        ArrLSP[lenArr+lenFile-1].setTenLoai(ten);
        ArrLSP[lenArr+lenFile-1].setMaLoai(ma);
        GhiFile();
        
    }
    @Override
    public void Xoa() throws IOException {
        while (true) {
            Xuat();
            System.out.print("- Nhập mã loại sản phẩm bạn muồn xóa: ");
            String ma = sc.nextLine();
            int vt = TimKiem_MaSo(ma);
   
            if (vt != -1) {
                System.out.println("Tìm thấy loại sản phẩm ở vị trí " + vt);
                System.out.print("- Bạn có muốn xóa loại sản phẩm ?? ( y / n ): ");
                while (true) {
                    String regex = "y|Y";
                    String yn = kt.KTYesNo();
                    if (yn.matches(regex)) {
                        XoaPS(vt);
                        
                        System.out.print("- Bạn có muốn ghi vào cơ sở dữ liệu không? ( y / n ): ");
                    String yy = kt.KTYesNo();
                    String reg = "[yY]";
                    if (yy.matches(reg)) {
                        GhiFile();
                        System.err.println("- Xóa thành công.");
                        break;
                    }
                        
                        
                    } else {
                        System.out.println("- Xóa thất bại.");
                        break;
                    }
                }
            }else {
                System.out.println("- Không có mã loại sản phẩm trong danh sách.");
            }
            System.out.print("- Bạn có muốn tiếp tục ? ( y / n ): ");
            String regex = "y|y";
            String yn = kt.KTYesNo();
            if (yn.matches(regex) == false) {
                break;
        }
    }
    }

    public void XoaPS(int vt) {
        for (int i = vt; i < lenArr -1 + lenFile; i++) {
            ArrLSP[i] = ArrLSP[i + 1];
        }
        lenArr--;
    }

    @Override
    public void Sua() throws IOException {
        while(true){
        Xuat();
        System.out.print("- Nhập mã loại sản phẩm cần sửa thông tin: ");
        String ma = sc.nextLine();
        int vt = TimKiem_MaSo(ma);
        if ((vt = TimKiem_MaSo(ma)) != -1) {
            System.out.println("- Tìm thấy mã loại tại vị trí: " + vt);
            System.out.print("- Bạn có chắc muốn sửa thông tin không ? ( y / n ): ");
            while (true) {
                String regex = "[yY]";
                String yn = kt.KTYesNo();
                if (yn.matches(regex)) {
                    for (int i = 0; i < lenArr + lenFile; i++) {
                        if (i == vt) {
                            System.out.print("- Nhập tên loại sản phẩm mới: ");
                            ArrLSP[i].setTenLoai(kt.KTTen());
                        }
                    }
                    
                         System.out.print("- Bạn có muốn ghi vào cơ sở dữ liệu không? ( y / n ): ");
                    String yy = kt.KTYesNo();
                    String reg = "y|Y";
                    if (yy.matches(regex)) {
                        GhiFile();
                        
                    
                    System.err.println("- Bạn đã sửa thành công.");
                    break;
                } else {
                    System.out.println("- Xóa không thành công.");
                    break;
                }
            }
         else {
            System.out.println("- Mã loại sản phẩm không có trong danh sách.");
        }
            }
            System.out.print("- Bạn có muốn tiếp tục không ? ( y / n ): ");
            String regex = "[yY]";
            String yn = kt.KTYesNo();
            if(yn.matches(regex)==false){
                break;
            }
    }
        }
    }
    
    public void Xuat() throws IOException {
        if (SoluongTrongFile() == 0 && lenArr == 0) {
            String yn;
            System.out.println("- Danh sách chưa có loại sản phẩm. ");
            System.out.print("- Bạn có muốn thêm mới ? ( y / n ): ");
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
        for (int j = 0; j < lenArr + lenFile; j++) {
            ArrLSP[j].Xuat();
        }
    }

    public int TimKiem_MaSoTL(String ma, int j) {
        for (int i = 0; i < j; i++) {
            if (ArrLSP[i].getMaLoai().equalsIgnoreCase(ma)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int TimKiem_MaSo(String ma) {
        for (int i = 0; i < lenArr + lenFile; i++) {
            if (ArrLSP[i].getMaLoai().equalsIgnoreCase(ma)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void TimKiem_Ten() {
        System.out.print("- Nhập tên loại sản phẩm cần tìm: ");
        String ma = kt.KTTen();
        int dem = 0;
        for (int i = 0; i < lenArr + lenFile; i++) {
            if (ArrLSP[i].getTenLoai().compareTo(ma) == 0) {
                ArrLSP[i].Xuat();
                dem++;
            }
        }
        if (dem == 0) {
            System.out.printf("- Không có loại sản phẩm nào có tên %s .", ma);
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
                    String regex = "[yY]";
                    if (yn.matches(regex)) {
                        GhiFile();
                        System.err.println("- Bạn đã thoát.");
                        break;
                    }
                }
            }
        } while (lc != 5);
    }
}
