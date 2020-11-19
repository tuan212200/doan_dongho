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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public abstract class ArrSanPham implements Arr {

    private int lenArr = 0;
    private int lenFile = 0;
    private SanPham[] arrSP = new SanPham[1];
    private static final String filename = "SanPham.txt";
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

    public SanPham[] getArrSP() {
        return arrSP;
    }

    public void setArrSP(SanPham[] arrSP) {
        this.arrSP = arrSP;
    }

    public ArrSanPham() throws IOException {
        HienDS();
    }

    @Override
    public void HienDS() throws IOException {
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        String data;
        int i = 0;
        lenFile = SoluongTrongFile();
        arrSP = Arrays.copyOf(arrSP, lenArr + lenFile);
        while ((data = br.readLine()) != null) {
            if (data.length() != 1) {
                arrSP[i] = new SanPham();
                Scanner sc = new Scanner(data).useDelimiter("-");
                arrSP[i].setMaSP(sc.next());
                arrSP[i].setTenSP(sc.next());
                arrSP[i].setMaLoai(sc.next());
                arrSP[i++].setGia(sc.nextInt());
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
            String data = arrSP[i].getMaSP() + "-" + arrSP[i].getTenSP() + "-" + arrSP[i].getMaLoai() + "-" + arrSP[i].getGia();
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

    public int TimKiem_MaSoSP(String ma, int j) throws IOException {
        for (int i = 0; i < j; i++) {
            if (arrSP[i].getMaSP().equalsIgnoreCase(ma)) {
                return i;
            }
        }
        return -1;
    }
    public int TimKiem_MaSanPham(String masanpham) throws IOException{
        for(int i = 0 ; i < lenArr+lenFile; i++){
            if(arrSP[i].getMaSP().compareTo(masanpham)==0){
                return i;
            }
        }return -1;
    }

    @Override
    public int TimKiem_MaSo(String ma) throws IOException {
        for (int i = 0; i < lenArr + lenFile; i++) {
            if (arrSP[i].getMaSP().equalsIgnoreCase(ma)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void TimKiem_Ten() throws IOException {
        System.out.print("- Nhập tên sản phẩm cần tìm: ");
        String ma = kt.KTTen();
        int dem = 0;
        for (int i = 0; i < lenArr + lenFile; i++) {
            if (arrSP[i].getTenSP().compareTo(ma) == 0) {
                arrSP[i].Xuat();
                dem++;
            }
        }
        if (dem == 0) {
            System.out.printf("- Không có loại sản phẩm nào có tên %s .", ma);
        }
    }

    @Override
    public void ThemDT() throws IOException {
        /*
        System.out.print("- Nhập số lượng sản phẩm: ");
        lenArr = kt.KTNhapInt(1);
        arrSP = Arrays.copyOf(arrSP, lenArr + lenFile);//kéo dài mảng ra
        for (int i = lenFile; i < lenArr + lenFile; i++) {
            System.out.printf("\n%60s %d%s\n\n", "------------SP", i + 1, "------------");
            arrSP[i] = new SanPham();
            arrSP[i].Nhap();

            System.out.print("- Bạn có muốn ghi vào cơ sở dữ liệu không? ( y / n ): ");
            String yn = kt.KTYesNo();
            String regex = "[yY]";
            if (yn.matches(regex)) {
                GhiFile();
                System.err.println("- Thêm thành công.");
            } else {
                break;
            }
            while (true) {
                if (TimKiem_MaSoSP(arrSP[i].getMaSP(), i) != -1) {

                    System.out.print("- Mã sản phẩm đã tồn tại. Nhập lại: ");
                    arrSP[i].setMaSP(sc.nextLine());
                } else {
                    break;
                }

            }
        }
*/
    }

    public void Them1(String mas, String maNCC) throws IOException{
        lenArr+=1;
        arrSP=Arrays.copyOf(arrSP, lenArr+lenFile);
        arrSP[lenArr+lenFile-1] = new SanPham();
        arrSP[lenArr+lenFile-1].Nhap(maNCC);
        arrSP[lenArr+lenFile-1].setMaSP(mas);
        GhiFile();
    }

    public void XoaPS(int vt) {
        for (int i = vt; i < lenArr - 1 + lenFile; i++) {
            arrSP[i] = arrSP[i + 1];
        }
        lenArr--;
    }

    @Override
    public void Xoa() throws IOException {
        while (true) {
            Xuat();
            System.out.print("- Nhập mã sản phẩm bạn muốn xóa: ");
            String ma = sc.nextLine();
            int vt = TimKiem_MaSo(ma);
            if (vt != -1) {
                System.out.println("- Tìm thấy sản phẩm ở vị trí: " + vt);
                System.out.print("- Bạn có muốn xóa sản phẩm này? ( y / n ): ");
                while (true) {
                    String regex = "y|Y";
                    String yn = kt.KTYesNo();
                    if (yn.matches(regex)) {
                        XoaPS(vt);
                        System.out.print("- Bạn có muốn ghi vào CSDL? ( y / n ): ");
                        String yy = kt.KTYesNo();
                        if (yy.matches(regex)) {
                            GhiFile();
                            System.err.println("- Xóa thành công.");
                            break;
                        } else {
                            System.out.println("- Xóa thất bại.");
                            break;
                        }
                    }
                }
            } else {
                System.out.println("- Không có mã sản phẩm trong danh sách. ");
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
        while (true) {
            Xuat();
            System.out.print("- Nhập mã sản phẩm cần sửa thông tn: ");
            String ma = sc.nextLine();
            int vt = TimKiem_MaSo(ma);
            if (vt != -1) {
                System.out.println("- Tìm thấy sản phẩm ở vị trí: " + vt);
                System.out.print("- Bạn có chắc muốn sửa thông tin không? ( y / n ): ");
                while (true) {
                   String regex ="y|Y";
                   String yn = kt.KTYesNo();
                   if(yn.matches(regex)){
                       for(int i = 0; i<lenArr+lenFile; i++){
                           if(i==vt){
                               arrSP[i] = MenuSua(arrSP[i]);
                           }
                       }
                       System.out.println("- Bạn đã sửa thành công.");
                       GhiFile();
                       break;
                   }else{
                       System.out.println("- Cancel bởi người dùng.");
                       break;
                   }
                }
            }else{
                System.out.println("- Mã sản phẩm không có trong danh sách.");
            }
            System.out.print("- Bạn có muốn tiếp tục ? ( y / n ): ");
            String regex = "y|y";
            String yn = kt.KTYesNo();
            if (yn.matches(regex) == false) {
                break;
            }
        } 
        }
public SanPham MenuSua(SanPham sp) throws IOException{
        int lc;
        String temp;
        do{
            System.out.println("- Bạn muốn sửa thông tin của: ");
            System.out.println("1. Tên sản phẩm.");
            System.out.println("2. Mã loại.");
            System.out.println("3. Mã nhà cung cấp.");
            System.out.println("4. Thoát.");
            System.out.print("- Nhập lựa chọn của bạn: ");
            lc = kt.KTLuaChon(4);
            switch (lc) {
                case 1: {
                    System.out.print("- Nhập tên mới: ");
                    temp = kt.KTTen();
                    sp.setTenSP(temp);
                    break;
                }
                case 2: {
                    System.out.print("- Nhập mã loại: ");
                   temp = kt.KTTheLoai();
                   sp.setMaLoai(temp);
                    break;
                }
                case 3: {
                    System.out.print("- Nhập mã nhà cung cấp: ");
                    temp = kt.KTMaNhaCungCap();
                    sp.setMaNCC(temp);
                    break;
                }

                case 4:{
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
        }while(lc!=5);
    return sp;
    }
    
    
    public void Xuat() throws IOException{
        if(SoluongTrongFile()==0&&lenArr==0){
            String yn;
            System.out.println("- Danh sách chưa có sản phẩm.");
            System.out.print("- Bạn có muốn thêm mới? ( y / n ): ");
            while(true){
                String regex = "y|Y";
                yn = kt.KTYesNo();
                if(yn.matches(regex)){
                    ThemDT();
                    break;
                }else
                    break;
            }
        }
        for(int j = 0; j <lenArr + lenFile; j++){
            arrSP[j].Xuat();
        }
    }
    public void MenuChinh() throws IOException {
        int lc = 0;
        do{
            System.out.println("\n=============== MENU SẢN PHẨM ===============");
            System.out.println("1. Nhập.");
            System.out.println("2. Xuất.");
            System.out.println("3. Xóa.");
            System.out.println("4. Sửa.");
            System.out.println("5. Thoát.");
            System.out.print("- Nhập lựa chọn của bạn: ");
            lc = kt.KTLuaChon(5);
            switch(lc){
                case 1:{
                //    ThemDT();
                    kt.Phim();
                    break;
                }
                case 2:{
                    Xuat();
                    kt.Phim();
                    break;
                }
                case 3:{
                    Xoa();
                    kt.Phim();
                    break;
                }
                case 4:{
                    Sua();
                    kt.Phim();
                    break;
                }
                case 5:{
                    System.out.print("- Bạn có muốn ghi vào CSDL không? ( y / n ) ");
                    String s = kt.KTYesNo();
                    String regex = "y|Y";
                    if(s.matches(regex))
                        GhiFile();
                    System.err.println("- Bạn đã thoát.");
                    break;
                }
            }
        }while(lc!=5);
    }
    
}
