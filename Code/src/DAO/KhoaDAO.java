/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Khoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class KhoaDAO {

    public static ArrayList<Khoa> LayTatCaKhoa() {
        ArrayList<Khoa> list = new ArrayList<>();
        try {
            Connection connection = DatabaseUtil.getConnection();
            String sql = "SELECT * FROM `khoa`";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Khoa k = new Khoa(rs.getString(1), rs.getString(2));
                list.add(k);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean ThemKhoa(Khoa k) {
        try {
            Connection connection = DatabaseUtil.getConnection();
            String sql = "INSERT INTO `khoa` VALUES (?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, k.getMaKhoa());
            ps.setString(2, k.getTenKhoa());
            int kq = ps.executeUpdate();
            return kq == 1;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean XoaKhoa(String maKhoa) {
        try {
            Connection connection = DatabaseUtil.getConnection();
            String sql = "DELETE FROM `khoa` WHERE ma_khoa LIKE ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, maKhoa);
            int kq = ps.executeUpdate();
            return kq == 1;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean SuaKhoa(Khoa k) {
        try {
            Connection connection = DatabaseUtil.getConnection();
            String sql = "UPDATE `khoa` SET `ten_khoa` = ? WHERE ma_khoa LIKE ? ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, k.getTenKhoa());
            ps.setString(2, k.getMaKhoa());
            int kq = ps.executeUpdate();
            return kq == 1;
        } catch (Exception e) {
            return false;
        }
    }

    public static ArrayList<Khoa> TimKiem(String bang, String text) {
        ArrayList<Khoa> list = new ArrayList<>();
        try {
            Connection connection = DatabaseUtil.getConnection();
            String sql = "SELECT * FROM `khoa` WHERE `" + bang + "` LIKE '%" + text + "%'";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, bang);
//            ps.setString(2, text);
            ResultSet rs = connection.createStatement().executeQuery(sql);
            while (rs.next()) {
                Khoa k = new Khoa(rs.getString(1), rs.getString(2));
                list.add(k);
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }
}
