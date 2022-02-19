/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PembeliDashBoard;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author Ridho Gymnastiar Al Rasyid
 */
public interface Format {
    NumberFormat kurensiIndonesia = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));
    default String formatRp(double duit){
        return "Rp. " + kurensiIndonesia.format(duit).substring(2, kurensiIndonesia.format(duit).length() - 3);
    }
    static String formatKosong(){
        return "";
    }
}
