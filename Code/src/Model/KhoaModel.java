/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.KhoaDAO;
import Entity.Khoa;
import java.util.ArrayList;

/**
 *
 * @author Khoi
 */
public class KhoaModel {

    public static ArrayList<Khoa> LayTatCaKhoa() {
        return KhoaDAO.LayTatCaKhoa();
    }

    public boolean ThemKhoa(Khoa k) {
        return KhoaDAO.ThemKhoa(k);
    }

    public boolean XoaKhoa(String maKhoa) {
        return KhoaDAO.XoaKhoa(maKhoa);
    }

    public boolean SuaKhoa(Khoa k) {
        return KhoaDAO.SuaKhoa(k);
    }

    public ArrayList<Khoa> TimKiem(String bang, String text) {
        return KhoaDAO.TimKiem(bang, text);
    }
}
