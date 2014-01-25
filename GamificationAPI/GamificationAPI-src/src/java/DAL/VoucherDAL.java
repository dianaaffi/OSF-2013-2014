/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import entities.Voucher;
import java.util.List;
import util.HibernateUtil;

/**
 *
 * @author user
 */
public class VoucherDAL {

    private static final String GET_ALL_VOUCHERS_QUERY = "from Voucher as voucher where voucher.application =";
    private static final String GET_VOUCHER_BY_ID_QUERY = "from Voucher as voucher where voucher.id =";

    public static List<Voucher> getAllVouchersByApplicationId(int applicationId) {
        List<Voucher> allVouchers = null;
        try {
            allVouchers = HibernateUtil.executeHQLQuery(GET_ALL_VOUCHERS_QUERY + applicationId);
        } catch (Exception ex) {
            System.out.println("Error in VoucherDAL-getAllVouchersByApplicationId: " + ex.getMessage());
        }
        return allVouchers;
    }

    public static Voucher getVoucherById(int voucherId) {
        List<Voucher> allVouchers;
        Voucher voucher = null;
        try {
            allVouchers = HibernateUtil.executeHQLQuery(GET_VOUCHER_BY_ID_QUERY + voucherId);
            if (allVouchers != null && !allVouchers.isEmpty()) {
                voucher = allVouchers.get(0);
            }
        } catch (Exception ex) {
            System.out.println("Error in VoucherDAL-getVoucherById: " + ex.getMessage());
        }
        return voucher;
    }

    public static Voucher addVoucher(Voucher voucher) {
        try {
            voucher.setId(HibernateUtil.addEntity(voucher));
        } catch (Exception ex) {
            System.out.println("Error in VoucherDAL-addVoucher: " + ex.getMessage());
        }
        return voucher;
    }

    public static void editVoucher(Voucher voucher) {
        try {
            if (voucher.getId() != null && voucher.getId() > 0) {
                HibernateUtil.editEntity(voucher);
            }
        } catch (Exception ex) {
            System.out.println("Error in VoucherDAL-editVoucher: " + ex.getMessage());
        }
    }
    
    public static void deleteVoucher(Voucher voucher) {
        try {
            if (voucher.getId() != null && voucher.getId() > 0) {
                HibernateUtil.deleteEntity(voucher);
            }
        } catch (Exception ex) {
            System.out.println("Error in VoucherDAL-deleteVoucher: " + ex.getMessage());
        }
    }

}
