/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.LopHocDAO;
import Entity.LopHoc;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Khoi
 */
public class LopHocModel {

    public ArrayList<LopHoc> LayTatCaLopHoc() {
        return LopHocDAO.layTatCaLopHoc();
    }

    public boolean ThemLopHoc(LopHoc lh) {
        return LopHocDAO.themLopHoc(lh);
    }

    public boolean XoaLopHoc(String maLop) {
        return LopHocDAO.XoaLopHoc(maLop);
    }

    public boolean SuaLopHoc(LopHoc lh) {
        return LopHocDAO.SuaLophoc(lh);
    }

    public ArrayList<LopHoc> TimKiem(String bang, String text) {
        return LopHocDAO.TimKiem(bang, text);
    }

    public ArrayList<LopHoc> sapXepTheoMaLop(ArrayList<LopHoc> dsClone) {
        ArrayList<LopHoc> result = (ArrayList<LopHoc>) dsClone.clone();
        Collections.sort(result, new Comparator<LopHoc>() {
            @Override
            public int compare(LopHoc lh1, LopHoc lh2) {
                return lh1.getMaLop().compareTo(lh2.getMaLop());
            }
        });
        return result;
    }

    public ArrayList<LopHoc> sapXepTheoTenRoiTheoMaGV(ArrayList<LopHoc> list) {
        ArrayList<LopHoc> result = (ArrayList<LopHoc>) list.clone();
        Collections.sort(result, new Comparator<LopHoc>() {
            @Override
            public int compare(LopHoc lh1, LopHoc lh2) {
                return lh1.getMaGiaoVien().compareTo(lh2.getMaGiaoVien());
            }
        });
        Collections.sort(result, new Comparator<LopHoc>() {
            @Override
            public int compare(LopHoc lh1, LopHoc lh2) {
                return lh1.getTenLop().compareTo(lh2.getTenLop());
            }
        });
        return result;
    }

    public ArrayList<LopHoc> layLopHocTheoBuoiDay(String buoi) {
        return LopHocDAO.layLopHocTheoBuoi(buoi);
    }
}
