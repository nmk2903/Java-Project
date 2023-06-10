/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.LopHoc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Khoi
 */
public class LopHocDAO {

    public static ArrayList<LopHoc> layTatCaLopHoc() {
        ArrayList<LopHoc> dsLopHoc = new ArrayList<>();
        try {
            Connection connection = DatabaseUtil.getConnection();
            String sql = "SELECT * FROM `lophoc`";

            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                LopHoc lh = new LopHoc(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
                dsLopHoc.add(lh);
            }

        } catch (Exception e) {
        }

        return dsLopHoc;
    }

    public static boolean themLopHoc(LopHoc lh) {
        try {
            Connection connection = DatabaseUtil.getConnection();
            String sql = "INSERT INTO `lophoc` VALUES (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, lh.getMaLop());
            ps.setString(2, lh.getTenLop());
            ps.setString(3, lh.getBuoiDayTrongTuan());
            ps.setString(4, lh.getMaGiaoVien());
            int kq = ps.executeUpdate();
            return kq == 1;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean XoaLopHoc(String maLop) {
        try {
            Connection connection = DatabaseUtil.getConnection();
            String sql = "DELETE FROM `lophoc` WHERE ma_lop LIKE ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, maLop);
            int kq = ps.executeUpdate();
            return kq == 1;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean SuaLophoc(LopHoc lh) {
        try {
            Connection connection = DatabaseUtil.getConnection();
            String sql = "UPDATE `lophoc` SET `ten_lop` = ?,`buoi_day`= ?,`ma_gv`= ? WHERE `ma_lop` LIKE ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, lh.getTenLop());
            ps.setString(2, lh.getBuoiDayTrongTuan());
            ps.setString(3, lh.getMaGiaoVien());
            ps.setString(4, lh.getMaLop());
            int kq = ps.executeUpdate();
            return kq == 1;
        } catch (Exception e) {
            return false;
        }
    }

    public static ArrayList<LopHoc> TimKiem(String bang, String text) {
        ArrayList<LopHoc> list = new ArrayList<>();
        try {
            Connection connection = DatabaseUtil.getConnection();
            String sql = "SELECT * FROM `lophoc` WHERE `" + bang + "` LIKE '%" + text + "%'";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            while (rs.next()) {
                LopHoc lh = new LopHoc(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4));
                list.add(lh);
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public static ArrayList<LopHoc> layLopHocTheoBuoi(String buoi) {
        ArrayList<LopHoc> dsLopHoc = new ArrayList<>();
        try {
            Connection connection = DatabaseUtil.getConnection();
            String sql = "SELECT * FROM `lophoc` WHERE `buoi_day` like '" + buoi + "'";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LopHoc lh = new LopHoc(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4));
                dsLopHoc.add(lh);
            }
        } catch (Exception e) {
        }
        return dsLopHoc;
    }
}
