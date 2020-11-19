/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doan;
import java.io.IOException;
/**
 *
 * @author Tuấnn Phạm
 */
public interface Arr {
    void ThemDT() throws IOException;
    
    void HienDS() throws IOException;   //Đọc file

    int SoluongTrongFile() throws IOException;
    
    int TimKiem_MaSo(String ma) throws IOException;

    void TimKiem_Ten()throws IOException;

    void Xoa() throws IOException;

    void Sua() throws IOException;	

    void GhiFile() throws IOException;
}
