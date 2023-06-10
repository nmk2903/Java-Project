/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Entity.GiaoVien;
import Entity.Khoa;
import Model.GiaoVienModel;
import Model.KhoaModel;
import View.GiaoVienView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Khoi
 */
public class GiaoVienController {

    GiaoVienModel model;
    GiaoVienView view;

    private String checkLuu;

    public GiaoVienController(GiaoVienModel model, GiaoVienView view) {
        this.model = model;
        this.view = view;

        init();
        view.setVisible(true);
    }

    public GiaoVienController() {
        model = new GiaoVienModel();
        view = new GiaoVienView();

        init();
    }

    public void setVisible(boolean x) {
        view.setVisible(x);
    }

    private void init() {
        view.getTblGiaoVien().addMouseListener(tableListener());
        ArrayList<Khoa> dsKhoa = KhoaModel.LayTatCaKhoa();
        for (Khoa k : dsKhoa) {
            view.getCboKhoa().addItem(k.getTenKhoa());
        }
        setDefaultForm();

        //BtnThem
        view.getBtnThem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setFalseEnabledButton();
                checkLuu = view.getBtnThem().getText();
                setPrepareSave();
            }
        });

        //BtnXoa
        view.getBtnXoa().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String maLop = view.getTxtMaGV().getText();
                model.XoaGiaoVien(maLop);
                view.getTxtMaGV().setEnabled(true);
                setDefaultForm();
                showTable();
            }
        });

        //BtnSua
        view.getBtnSua().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setFalseEnabledButton();
                checkLuu = view.getBtnSua().getText();
                setPrepareSave();
                view.getTxtMaGV().setEnabled(false);
            }
        });

        //BtnHuy
        view.getBtnHuy().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setDefaultForm();
            }
        });

        //BtnLuu
        view.getBtnLuuMoi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maGV, ten;
                int namSinh;
                double danhGia;
                if (!view.getTxtMaGV().getText().equals("")) {
                    maGV = view.getTxtMaGV().getText();
                } else {
                    JOptionPane.showMessageDialog(view, "Mã giáo viên không được để trống!");
                    return;
                }
                if (!view.getTxtHoTen().getText().equals("")) {
                    ten = view.getTxtHoTen().getText();
                } else {
                    JOptionPane.showMessageDialog(view, "Họ tên không được để trống!");
                    return;
                }
                try {
                    namSinh = Integer.parseInt(view.getTxtNamSinh().getText());
                    if (namSinh < 1970 || namSinh > 2000) {
                        JOptionPane.showMessageDialog(view, "Chỉ nhận giáo viên có năm sinh từ 1970 - 2000!");
                        return;
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Năm sinh phải là một con số!");
                    return;
                }
                try {
                    danhGia = Double.parseDouble(view.getTxtDanhGia().getText());
                    if (danhGia < 0 || danhGia > 10) {
                        JOptionPane.showMessageDialog(view, "Điểm đánh giá phải từ 0 - 10!");
                        return;
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Điểm đánh giá phải là một con số!");
                    return;
                }
                String tenKhoa = view.getCboKhoa().getSelectedItem().toString();
                if (checkLuu.equals(view.getBtnThem().getText())) {
                    if (!model.ThemGiaoVien(new GiaoVien(maGV, ten, namSinh, danhGia, tenKhoa))) {
                        JOptionPane.showMessageDialog(view, "Trùng mã giáo viên!");
                    }
                    setDefaultForm();
                    showTable();
                } else if (checkLuu.equals(view.getBtnSua().getText())) {
                    if (!model.SuaGiaoVien(new GiaoVien(maGV, ten, namSinh, danhGia, tenKhoa))) {
                        JOptionPane.showMessageDialog(view, "Lỗi dữ liệu!");
                    }
                    view.getTxtMaGV().setEnabled(true);
                    setDefaultForm();
                    showTable();
                }
            }
        });

        //BtnTimKiem
        view.getBtnTimKiem().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                String bang = "ma_gv";
                switch (view.getCboTimKiem().getSelectedIndex()) {
                    case 1:
                        bang = "ho_ten";
                        break;
                    case 2:
                        bang = "nam_sinh";
                        break;
                    case 3:
                        bang = "danh_gia";
                        break;
                    case 4:
                        bang = "ten_khoa";
                }
                String text = view.getTxtTimKiem().getText();

                ArrayList<GiaoVien> dsGV = model.TimKiem(bang, text);
                setDefaultForm();
                view.getBtnHuy().setEnabled(true);
                DefaultTableModel tableModel = (DefaultTableModel) view.getTblGiaoVien().getModel();
                tableModel.setRowCount(0);
                for (GiaoVien gv : dsGV) {
                    tableModel.addRow(new Object[]{gv.getMaGiaoVien(), gv.getHoTen(), gv.getNamSinh(), gv.getDanhGia(), gv.getTenKhoa()});
                }

                view.getTblGiaoVien().setModel(tableModel);
            }
        });

    }

    private void showTable() {
        DefaultTableModel tableModel = (DefaultTableModel) view.getTblGiaoVien().getModel();
        tableModel.setColumnCount(0);
        tableModel.setRowCount(0);
        tableModel.addColumn("Mã giáo viên");
        tableModel.addColumn("Họ tên");
        tableModel.addColumn("Năm sinh");
        tableModel.addColumn("Đánh giá");
        tableModel.addColumn("Tên khoa");
        ArrayList<GiaoVien> dsGV = model.LayTatCaGiaoVien();
        for (GiaoVien gv : dsGV) {
            tableModel.addRow(new Object[]{gv.getMaGiaoVien(), gv.getHoTen(), gv.getNamSinh(), gv.getDanhGia(), gv.getTenKhoa()});
        }
        view.getTblGiaoVien().setModel(tableModel);
    }

    private void setDefaultForm() {
        view.getTxtMaGV().setText("");
        view.getTxtHoTen().setText("");
        view.getTxtNamSinh().setText("");
        view.getTxtDanhGia().setText("");

        view.getTxtMaGV().setEnabled(false);
        view.getTxtHoTen().setEnabled(false);
        view.getTxtNamSinh().setEnabled(false);
        view.getTxtDanhGia().setEnabled(false);
        view.getCboKhoa().setEnabled(false);

        view.getBtnThem().setEnabled(true);

        view.getBtnSua().setEnabled(false);
        view.getBtnXoa().setEnabled(false);
        view.getBtnHuy().setEnabled(false);
        view.getBtnLuuMoi().setEnabled(false);
        showTable();
    }

    private void setPrepareSave() {
        view.getTxtMaGV().setEnabled(true);
        view.getTxtHoTen().setEnabled(true);
        view.getTxtNamSinh().setEnabled(true);
        view.getTxtDanhGia().setEnabled(true);
        view.getCboKhoa().setEnabled(true);

        view.getBtnHuy().setEnabled(true);
        view.getBtnLuuMoi().setEnabled(true);
        view.getBtnLuuMoi().setText("Lưu");
    }

    private void setFalseEnabledButton() {
        view.getBtnThem().setEnabled(false);
        view.getBtnSua().setEnabled(false);
        view.getBtnXoa().setEnabled(false);
    }

    private MouseAdapter tableListener() {
        return new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                view.getTxtMaGV().setEnabled(false);
                view.getTxtHoTen().setEnabled(false);
                view.getTxtNamSinh().setEnabled(false);
                view.getTxtDanhGia().setEnabled(false);
                view.getCboKhoa().setEnabled(false);
                view.getBtnLuuMoi().setEnabled(false);

                int index = view.getTblGiaoVien().getSelectedRow();
                view.getTxtMaGV().setText(view.getTblGiaoVien().getValueAt(index, 0) + "");
                view.getTxtHoTen().setText(view.getTblGiaoVien().getValueAt(index, 1) + "");
                view.getTxtNamSinh().setText(view.getTblGiaoVien().getValueAt(index, 2) + "");
                view.getTxtDanhGia().setText(view.getTblGiaoVien().getValueAt(index, 3) + "");

                view.getBtnHuy().setEnabled(true);
                view.getBtnXoa().setEnabled(true);
                view.getBtnSua().setEnabled(true);
                view.getBtnThem().setEnabled(false);
            }
        };
    }
}
