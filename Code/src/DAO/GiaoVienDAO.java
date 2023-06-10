/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.GiaoVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Khoi
 */
public class GiaoVienDAO {

    public static ArrayList<GiaoVien> LayTatCaGiaoVien() {
        ArrayList<GiaoVien> dsGV = new ArrayList<>();
        try {
            Connection connection = DatabaseUtil.getConnection();
            String sql = "SELECT * FROM `giaovien`";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                GiaoVien gv = new GiaoVien(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getString(5));
                dsGV.add(gv);
            }
            return dsGV;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsGV;
    }

    public static boolean ThemGiaoVien(GiaoVien gv) {
        try {
            Connection connection = DatabaseUtil.getConnection();
            String sql = "INSERT INTO `giaovien` VALUES (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, gv.getMaGiaoVien());
            ps.setString(2, gv.getHoTen());
            ps.setInt(3, gv.getNamSinh());
            ps.setDouble(4, gv.getDanhGia());
            ps.setString(5, gv.getTenKhoa());
            int kq = ps.executeUpdate();
            return kq == 1;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean XoaGiaoVien(String maGV) {
        try {
            Connection connection = DatabaseUtil.getConnection();
            String sql = "DELETE FROM `giaovien` WHERE ma_gv LIKE ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, maGV);
            int kq = ps.executeUpdate();
            return kq == 1;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean SuaGiaoVien(GiaoVien gv) {
        try {
            Connection connection = DatabaseUtil.getConnection();
            String sql = "UPDATE `giaovien` SET `ho_ten` = ?,`nam_sinh`= ?,`danh_gia`= ?,`ten_khoa`= ? WHERE `ma_gv` LIKE ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, gv.getHoTen());
            ps.setInt(2, gv.getNamSinh());
            ps.setDouble(3, gv.getDanhGia());
            ps.setString(4, gv.getTenKhoa());
            ps.setString(5, gv.getMaGiaoVien());
            int kq = ps.executeUpdate();
            return kq == 1;
        } catch (Exception e) {
            return false;
        }
    }

    public static ArrayList<GiaoVien> TimKiem(String bang, String text) {
        ArrayList<GiaoVien> list = new ArrayList<>();
        try {
            Connection connection = DatabaseUtil.getConnection();
            String sql = "SELECT * FROM `giaovien` WHERE `" + bang + "` LIKE '%" + text + "%'";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            while (rs.next()) {
                GiaoVien gv = new GiaoVien(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getString(5));
                list.add(gv);
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }
}
