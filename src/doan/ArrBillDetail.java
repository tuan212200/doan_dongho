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
public abstract class ArrBillDetail implements Arr{
   private int lenArr = 0, lenFile = 0;
   private Bill_Detail[] arrBD = new Bill_Detail[1];
   private static final String filename = "Bill_Detail.txt";
   Scanner sc = new Scanner(System.in);
   KT_NhapXuat kt = new KT_NhapXuat();
    ArrKho ak;

    public ArrBillDetail() throws IOException {
        this.ak = new ArrKho();
    }
   @Override
   public void ThemDT() throws IOException{}
   
   public void Them1(String maHD, int tt, String maNCC) throws IOException{
       ArrHoaDon arrHD = new ArrHoaDon();
       String mahd, masp;
       System.out.print("- Nhập số lượng sản phẩm trong hóa đơn: ");
       lenArr=kt.KTNhapInt(1);// cập  nhật lại lenArr; lenFile đã dược cập nhật khi gọi lại constructor
       arrBD = Arrays.copyOf(arrBD, lenArr+lenFile+1);
       int vt, gia = 0;
       for(int i = lenFile; i <lenArr+lenFile;i++){
           System.out.printf("\n%60s %d%s\n\n","---------------CTHD", i + 1, "---------------");
           arrBD[i] = new Bill_Detail();
           //nhập hàng
           if(tt==1){
               mahd = "NH" + maHD;
               //cập nhật lại vào đối tượng trong mảng
               arrBD[i].setMAHD(mahd);
               System.out.print("- Nhập giá của sản phẩm này: ");
               gia = kt.KTNhapInt(0);
               arrBD[i].setGIA(gia);
               //nhap so luong trong doi tuong CTHD
               arrBD[i].Nhap();
               int gias = arrBD[i].getGIA() / arrBD[i].getSoLuong();//gia san = gia cua loai san pham do * so luong
               System.out.print("- Nhập mã sản phẩm: ");
               //bat loi khoa chinh cua CTHD (mahd, masp)
               while (true){
                   masp = kt.KT_MaSanPham_KhiNH(maNCC, gia);
                   if(TimKiem_MaSo(mahd, masp, i)!=-1){
                       System.out.print("- Hòa đơn đã có sản phẩm này rồi. Nhập lại mã sản phẩm: ");
                   }else
                       break;
               }
              //cập nhật lại mã sản phẩm vào trong đối tượng
              arrBD[i].setMASP(masp);
           }
           //xuất hàng
           else{
               mahd = "XH" + maHD;
               arrBD[i].setMAHD(mahd);
               System.out.print("- Nhập mã sản phẩm: ");
               //bắt lỗi khóa chính của CTHD(mahd,masp)
               while(true){
                   masp = kt.KT_MaSanPham_KhiXH();
                   if(TimKiem_MaSo(mahd, masp, i)!=-1){
                       System.out.print("- Hóa đơn đã có sản phẩm này. Nhập lại: ");
                   }else
                       break;
               }
               vt = ak.TimKiem_MaSanPham(masp);
               if(ak.TruyXuatPT(vt).getSLuong()==0){
                   System.out.print("- Hiện tại đã hết sản phẩm này");
                   // nếu trong kho số lượng của sản phẩm này đã hết thì trong hóa đơn số lượng sp đó sẽ bằng 0. Ảnh hưởng đến số lượng tổng tiền của hóa đơn
                   arrBD[i].setSoLuong(0);
               }else{
                   // nhập số lượng trong đối tượng CTHD
                   arrBD[i].Nhap();
               }arrBD[i].setMASP(masp);
               // đối với hóa đơn xuất hàng thì giá của loại sản phẩm lúc này sẽ lấy từ kho
               
           }
       }
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
        // Cập nhật lại lenFile
        lenFile = SoluongTrongFile();
        // cấp phát lại bộ nhớ cho mảng
        arrBD = Arrays.copyOf(arrBD, lenArr + lenFile);
        while ((data = br.readLine()) != null) {
            //data.length() != -1. dùng để loại trừ khả năng File đó NULL
            if (data.length() != 1) {
                arrBD[i] = new Bill_Detail();
                // có thể sử dụng split("-") để tách chuỗi theo kí tự -. Nhưng phải khai báo thêm 1 mảng String để lưu các String đã tách
                //sử dụng scanner + useDelimiter cũng có tác dụng giống với split nhưng không cần thêm 1 mảng String
                Scanner scan = new Scanner(data).useDelimiter("-");
                arrBD[i].setMAHD(scan.next());
                arrBD[i].setMASP(scan.next());
                arrBD[i].setSoLuong(scan.nextInt());
                arrBD[i++].setGIA(scan.nextInt());
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
            String data = arrBD[i].getMAHD() + "-" + arrBD[i].getMASP()+ "-" + arrBD[i].getSoLuong()+ "-"
                    + arrBD[i].getGIA();
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
   public int LayGiaSanPhamTrongKho(String mas) throws IOException {
        int gia = 0, vt;
        // tìm vị trí của mã sản phẩm ở trong kho
        vt = ak.TimKiem_MaSanPham(mas);
        // lấy giá của sản phẩm đó. hàm TruyXuatPT(vt) trả về 1 đối tượng kho
        gia = ak.TruyXuatPT(vt).getGIA();
        return gia;
    }

    public double TinhTongTienChoHD(String maHD) {// chỉ sử dụng cho xuất hàng
        double gia = 0, vt;
        for (int i = 0; i < lenArr + lenFile; i++) {
            if (arrBD[i].getMAHD().compareTo(maHD) == 0 || arrBD[i].getMAHD().compareTo("XH" + maHD) == 0) {
                // tổng tiền của hóa đơn = giá của sản phẩm trong kho * 10% * số lượng             ́
                gia += (double) arrBD[i].getGIA() * 1.1 * arrBD[i].getSoLuong();
            }
        }
        return gia;
    }
   @Override
    public int TimKiem_MaSo(String ma) {
        for (int i = 0; i < lenArr + lenFile; i++) {
            //so sánh mã hóa đơn với đối số truyền vào ô (mã) nếu bằng nhau thì trả về vị trí còn không thì trả về -1
            if(arrBD[i].getMAHD().compareTo(ma)==0){
                return i;
            }
        }
        return -1;
    }

    public int TimKiem_MaSo(String mahd, String mas, int j) {// sử dụng để KT mã sản phẩm truyền vào (masp)
        int dem = 0;
        if (j == 0) {
            return -1;
        }
        for (int i = 0; i < j; i++) {
            if (arrBD[i].getMAHD().compareTo(mahd) == 0 && arrBD[i].getMASP().compareTo(mas) == 0) {
                dem++;
            }
        }
        if (dem == 0) {
            return -1;
        }
        for (int i = 0; i < j; i++) {
            if (arrBD[i].getMAHD().compareTo(mahd) == 0 && arrBD[i].getMASP().compareTo(mas) == 0) {
                return i;
            }

        }
        return -1;
    }
     public void TimKiem_MS(String maHD) throws IOException {

        int dem = 0;
        System.out.println("==========CTHD " + maHD + "==========");
        for (int i = 0; i < lenArr + lenFile; i++) {

            // Đối số truyền vô lúc này có dạng ( NH , XH , hoặc số ) nên phải kt cả 3 dạng
            if (arrBD[i].getMAHD().compareTo("NH" + maHD) == 0 || arrBD[i].getMAHD().compareTo("XH" + maHD) == 0
                    || arrBD[i].getMAHD().compareTo(maHD) == 0) {
                arrBD[i].Xuat();
                dem++;
            }
        }
        if (dem == 0) {
            System.out.printf("- Không có mã %s .", maHD);
        }
    }
   @Override
       public void TimKiem_Ten() {}

   @Override
     public void Xoa() {
        while (true) {
            System.out.print("- Nhập mã sản phẩm bạn muốn xóa : ");
            String ma = kt.KTMaSo();
            int vt = TimKiem_MaSo(ma);
            System.out.println(vt);
            if (vt != -1) {
                System.out.print("- Bạn có thực sự muốn xóa sản phẩm này? ( y / n ): ");
                while (true) {
                    // dùng biểu thức chính quy ( Regular Expression )
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
                System.out.println("- Không có mã sản phẩm trong danh sách. ");
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
            arrBD[i] = arrBD[i + 1];
        }
        lenArr--;
    }
   @Override
   public void Sua() throws IOException {
        System.out.print("- Nhập mã hóa đơn muốn sửa: ");
        String ma = kt.KTMaSo();
        int vt;
        if ((vt = TimKiem_MaSo(ma)) != -1) {
            System.out.print("- Bạn có chắc muốn sửa thông tin của hóa đơn này? ( y / n ): ");
            while (true) {
                String regex = "[yY]";
                String yn = kt.KTYesNo();
                if (yn.matches(regex)) {
                    for (int i = 0; i < lenArr + lenFile; i++) {
                        if (i == vt) {
                            arrBD[i] = MenuSua(arrBD[i]);
                        }
                    }
                    System.out.println("- Bạn đã sửa thành công. ");
                    GhiFile();
                    break;
                } else {
                    System.out.print("- Bạn đã không xóa sách này.");
                    break;
                }
            }
        } else {
            System.out.println("- Mã sản phẩm bạn tìm không có trong danh sách. ");
        }
    }
    public Bill_Detail MenuSua(Bill_Detail bd) {
        int lc;
        String temp;
        do {
            System.out.println("** Bạn muốn sửa thông tin nào: ");
            System.out.println("1. Mã Sản phẩm.");
            System.out.println("2. Số lượng.");
            System.out.println("3.Thoát");
            System.out.print("- Nhập lựa chọn của bạn");
            lc = kt.KTLuaChon(3);
            switch (lc) {
                case 1: {
                    System.out.print("- Nhập mã sản phẩm: ");
                    temp = kt.KTMaSo();
                    bd.setMASP(temp);
                    break;
                }
                case 2: {
                    System.out.print("- Nhập số lượng: ");
                    int sl = kt.KTNhapInt(1);
                    bd.setSoLuong(sl);
                    break;
                }
                case 3: {
                    System.out.println("- Bạn đã thoát. ");
                    break;
                }
            }
        } while (lc != 3);
        return bd;
    }
    public void Xuat() throws IOException {
        for (int j = 0; j < lenArr + lenFile; j++) {
            arrBD[j].Xuat();
        }
    }
 public void MenuChinh() throws IOException {
        int lc = 0;
        do {
            System.out.println("=============================MENU============================");
            System.out.println("1.Nhập.");
            System.out.println("2.Xuất.");
            System.out.println("3.Xoá.");
            System.out.println("4.Sửa.");
            System.out.println("5.Thoát.");
            System.out.print("- Nhập Lựa Chọn của bạn");
            lc = kt.KTLuaChon(5);
            switch (lc) {
                case 1: {
                    ThemDT();
                    break;
                }
                case 2: {
                    Xuat();
                    break;
                }
                case 3: {
                    Xoa();
                    break;
                }
                case 4: {
                    Sua();
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
