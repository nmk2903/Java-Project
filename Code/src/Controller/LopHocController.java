/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.GiaoVien;
import Entity.LopHoc;
import Model.GiaoVienModel;
import Model.LopHocModel;
import View.LopHocView;
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
public class LopHocController {

    LopHocModel model;
    LopHocView view;

    private String checkLuu;

    public LopHocController(LopHocModel model, LopHocView view) {
        this.model = model;
        this.view = view;

        init();
        view.setVisible(true);
    }

    public LopHocController() {
        model = new LopHocModel();
        view = new LopHocView();
        init();
    }

    public void setVisible(boolean x) {
        view.setVisible(x);
    }

    private void init() {
        showTable();
        ArrayList<GiaoVien> dsGV = GiaoVienModel.LayTatCaGiaoVien();
        for (GiaoVien gv : dsGV) {
            view.getCboGiaoVien().addItem(gv.getHoTen());
        }
        view.getTblLopHoc().addMouseListener(tableListener());
        setDefaultForm();

        view.getCboSapXep().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sapXep();
            }
        });

        view.getCboLocBuoiDay().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sapXep();
            }
        });

        //BtnTimKiem
        view.getBtnTimKiem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ArrayList<LopHoc> dsLop = new ArrayList<>();
                String bang = "ma_lop";
                switch (view.getCboTimKiem().getSelectedIndex()) {
                    case 1:
                        bang = "ten_lop";
                        break;
                    case 2:
                        bang = "buoi_day";
                        break;
                    case 3:
                        bang = "ma_gv";
                        break;
                }
                String text = view.getTxtTimKiem().getText();
                dsLop = model.TimKiem(bang, text);
                setDefaultForm();
                view.getBtnHuy().setEnabled(true);
                DefaultTableModel tableModel = (DefaultTableModel) view.getTblLopHoc().getModel();
                tableModel.setRowCount(0);
                for (LopHoc lh : dsLop) {
                    tableModel.addRow(new Object[]{lh.getMaLop(), lh.getTenLop(), lh.getBuoiDayTrongTuan(), lh.getMaGiaoVien()});
                }
                view.getTblLopHoc().setModel(tableModel);
            }
        });

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
                String maLop = view.getTxtMaLop().getText();
                model.XoaLopHoc(maLop);
                view.getTxtMaLop().setEnabled(true);
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
                view.getTxtMaLop().setEnabled(false);
            }
        });

        //BtnHuy
        view.getBtnHuy().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDefaultForm();
            }
        });

        //BtnLuuMoi
        view.getBtnLuuMoi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maLop, ten;
                if (!view.getTxtMaLop().getText().equals("")) {
                    maLop = view.getTxtMaLop().getText();
                } else {
                    JOptionPane.showMessageDialog(view, "Mã lớp không được để trống!");
                    return;
                }
                if (!view.getTxtTenLop().getText().equals("")) {
                    ten = view.getTxtTenLop().getText();
                } else {
                    JOptionPane.showMessageDialog(view, "Tên lớp không được để trống!");
                    return;
                }
                String buoiDay = view.getCboBuoiDay().getSelectedItem().toString();
                String maGV = view.getCboGiaoVien().getSelectedItem().toString();
                if (checkLuu.equals(view.getBtnThem().getText())) {
                    if (!model.ThemLopHoc(new LopHoc(maLop, ten, buoiDay, maGV))) {
                        JOptionPane.showMessageDialog(view, "Trùng mã lớp");
                    }
                    setDefaultForm();
                    showTable();
                } else if (checkLuu.equals(view.getBtnSua().getText())) {
                    if (!model.SuaLopHoc(new LopHoc(maLop, ten, buoiDay, maGV))) {
                        JOptionPane.showMessageDialog(view, "Lỗi dữ liệu!");
                    }
                    view.getTxtMaLop().setEnabled(true);
                    setDefaultForm();
                    showTable();
                }
            }
        });
    }

    private void sapXep() {
        ArrayList<LopHoc> dsLop = new ArrayList<>();
        if (view.getCboLocBuoiDay().getSelectedIndex() != 0) {
            dsLop = model.layLopHocTheoBuoiDay(view.getCboLocBuoiDay().getSelectedItem().toString());
        } else {
            dsLop = model.LayTatCaLopHoc();
        }
        int index = view.getCboSapXep().getSelectedIndex();
        if (index == 0) {
            dsLop = model.sapXepTheoMaLop(dsLop);
        } else {
            dsLop = model.sapXepTheoTenRoiTheoMaGV(dsLop);
        }
        setDefaultForm();
        view.getBtnHuy().setEnabled(true);
        DefaultTableModel tableModel = (DefaultTableModel) view.getTblLopHoc().getModel();
        tableModel.setRowCount(0);
        for (LopHoc lh : dsLop) {
            tableModel.addRow(new Object[]{lh.getMaLop(), lh.getTenLop(), lh.getBuoiDayTrongTuan(), lh.getMaGiaoVien()});
        }
        view.getTblLopHoc().setModel(tableModel);
    }

    //Hien thi bang
    private void showTable() {
        DefaultTableModel tableModel = (DefaultTableModel) view.getTblLopHoc().getModel();
        tableModel.setColumnCount(0);
        tableModel.setRowCount(0);

        tableModel.addColumn("Mã lớp");
        tableModel.addColumn("Tên lớp");
        tableModel.addColumn("Buổi dạy");
        tableModel.addColumn("Tên giáo viên");

        ArrayList<LopHoc> dsLop = model.LayTatCaLopHoc();
        for (LopHoc lh : dsLop) {
            tableModel.addRow(new Object[]{lh.getMaLop(), lh.getTenLop(), lh.getBuoiDayTrongTuan(), lh.getMaGiaoVien()});
        }
        view.getTblLopHoc().setModel(tableModel);
    }

    private void setPrepareSave() {
        view.getTxtMaLop().setEnabled(true);
        view.getTxtTenLop().setEnabled(true);
        view.getCboBuoiDay().setEnabled(true);
        view.getCboGiaoVien().setEnabled(true);
        view.getBtnHuy().setEnabled(true);
        view.getBtnLuuMoi().setEnabled(true);
        view.getBtnLuuMoi().setText("Lưu");

    }

    private void setDefaultForm() {
        view.getTxtMaLop().setText("");
        view.getTxtTenLop().setText("");
        view.getCboBuoiDay().setSelectedIndex(0);
        view.getCboGiaoVien().setSelectedIndex(0);

        view.getTxtMaLop().setEnabled(false);
        view.getTxtTenLop().setEnabled(false);
        view.getCboBuoiDay().setEnabled(false);
        view.getCboGiaoVien().setEnabled(false);

        view.getBtnThem().setEnabled(true);
        view.getBtnSua().setEnabled(false);
        view.getBtnXoa().setEnabled(false);
        view.getBtnHuy().setEnabled(false);
        view.getBtnLuuMoi().setEnabled(false);

        showTable();
    }

    private void setFalseEnabledButton() {
        view.getBtnThem().setEnabled(false);
        view.getBtnSua().setEnabled(false);
        view.getBtnXoa().setEnabled(false);
    }

    private MouseAdapter tableListener() {
        return new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                view.getTxtMaLop().setEnabled(false);
                int index = view.getTblLopHoc().getSelectedRow();
                view.getTxtMaLop().setText(view.getTblLopHoc().getValueAt(index, 0) + "");
                view.getTxtTenLop().setText(view.getTblLopHoc().getValueAt(index, 1) + "");
                view.getCboBuoiDay().setSelectedItem(view.getTblLopHoc().getValueAt(index, 2) + "");
                view.getCboGiaoVien().setSelectedItem(view.getTblLopHoc().getValueAt(index, 3) + "");

                view.getBtnHuy().setEnabled(true);
                view.getBtnXoa().setEnabled(true);
                view.getBtnSua().setEnabled(true);
                view.getBtnThem().setEnabled(false);
            }
        };
    }
}
