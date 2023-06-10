/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DAO.GiaoVienDAO;
import Entity.GiaoVien;
import java.util.ArrayList;

/**
 *
 * @author Khoi
 */
public class GiaoVienModel {

    public static ArrayList<GiaoVien> LayTatCaGiaoVien() {
        return GiaoVienDAO.LayTatCaGiaoVien();
    }

    public boolean ThemGiaoVien(GiaoVien gv) {
        return GiaoVienDAO.ThemGiaoVien(gv);
    }

    public boolean XoaGiaoVien(String maGV) {
        return GiaoVienDAO.XoaGiaoVien(maGV);
    }

    public boolean SuaGiaoVien(GiaoVien gv) {
        return GiaoVienDAO.SuaGiaoVien(gv);
    }

    public ArrayList<GiaoVien> TimKiem(String bang, String text) {
        return GiaoVienDAO.TimKiem(bang, text);
    }

}
