/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Khoa;
import Model.KhoaModel;
import View.KhoaView;
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
public class KhoaController {

    KhoaModel model;
    KhoaView view;

    private String checkLuu;

    public KhoaController(KhoaModel model, KhoaView view) {
        this.model = model;
        this.view = view;

        init();
        view.setVisible(true);
    }

    public KhoaController() {
        model = new KhoaModel();
        view = new KhoaView();

        init();
    }

    public void setVisible(boolean x) {
        view.setVisible(x);
    }

    private void init() {
        view.getTblKhoa().addMouseListener(tableListener());
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
                String maKhoa = view.getTxtMaKhoa().getText();
                model.XoaKhoa(maKhoa);
                view.getTxtMaKhoa().setEnabled(true);
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
                view.getTxtMaKhoa().setEnabled(false);
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
                String ma, ten;
                if (!view.getTxtMaKhoa().getText().equals("")) {
                    ma = view.getTxtMaKhoa().getText();
                } else {
                    JOptionPane.showMessageDialog(view, "Mã khoa không được để trống!");
                    return;
                }
                if (!view.getTxtTenKhoa().getText().equals("")) {
                    ten = view.getTxtTenKhoa().getText();
                } else {
                    JOptionPane.showMessageDialog(view, "Tên khoa không được để trống!");
                    return;
                }
                if (checkLuu.equals(view.getBtnThem().getText())) {
                    if (!model.ThemKhoa(new Khoa(ma, ten))) {
                        JOptionPane.showMessageDialog(view, "Trùng mã khoa!");
                    }
                    setDefaultForm();
                    showTable();
                } else if (checkLuu.equals(view.getBtnSua().getText())) {
                    if (!model.SuaKhoa(new Khoa(ma, ten))) {
                        JOptionPane.showMessageDialog(view, "Lỗi dữ liệu!");
                    }
                    view.getTxtMaKhoa().setEnabled(true);
                    setDefaultForm();
                    showTable();
                }
            }
        });

        //BtnTimKiem
        view.getBtnTimKiem().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                String bang = "ma_khoa";
                switch (view.getCboTimKiem().getSelectedIndex()) {
                    case 1:
                        bang = "ten_khoa";
                        break;
                }
                String text = view.getTxtTimKiem().getText();
                ArrayList<Khoa> dsKhoa = model.TimKiem(bang, text);
                setDefaultForm();
                view.getBtnHuy().setEnabled(true);
                DefaultTableModel tableModel = (DefaultTableModel) view.getTblKhoa().getModel();
                tableModel.setRowCount(0);
                for (Khoa k : dsKhoa) {
                    tableModel.addRow(new Object[]{k.getMaKhoa(), k.getTenKhoa()});
                }

                view.getTblKhoa().setModel(tableModel);
            }
        });
    }

    private void showTable() {
        DefaultTableModel tableModel = (DefaultTableModel) view.getTblKhoa().getModel();
        tableModel.setColumnCount(0);
        tableModel.setRowCount(0);
        tableModel.addColumn("Mã khoa");
        tableModel.addColumn("Tên khoa");
        ArrayList<Khoa> dsKhoa = model.LayTatCaKhoa();
        for (Khoa k : dsKhoa) {
            tableModel.addRow(new Object[]{k.getMaKhoa(), k.getTenKhoa()});
        }
        view.getTblKhoa().setModel(tableModel);
    }

    private void setDefaultForm() {
        view.getTxtMaKhoa().setText("");
        view.getTxtTenKhoa().setText("");

        view.getTxtMaKhoa().setEnabled(false);
        view.getTxtTenKhoa().setEnabled(false);

        view.getBtnThem().setEnabled(true);
        view.getBtnSua().setEnabled(false);
        view.getBtnXoa().setEnabled(false);
        view.getBtnHuy().setEnabled(false);
        view.getBtnLuuMoi().setEnabled(false);

        showTable();
    }

    private void setPrepareSave() {
        view.getTxtMaKhoa().setEnabled(true);
        view.getTxtTenKhoa().setEnabled(true);

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
                view.getTxtMaKhoa().setEnabled(false);
                view.getTxtTenKhoa().setEnabled(false);
                view.getBtnHuy().setEnabled(true);

                int index = view.getTblKhoa().getSelectedRow();
                view.getTxtMaKhoa().setText(view.getTblKhoa().getValueAt(index, 0) + "");
                view.getTxtTenKhoa().setText(view.getTblKhoa().getValueAt(index, 1) + "");

                view.getBtnLuuMoi().setEnabled(false);
                view.getBtnXoa().setEnabled(true);
                view.getBtnSua().setEnabled(true);
                view.getBtnThem().setEnabled(false);
            }
        };
    }
}
