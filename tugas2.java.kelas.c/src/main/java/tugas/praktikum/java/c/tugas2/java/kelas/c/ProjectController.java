/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas.praktikum.java.c.tugas2.java.kelas.c;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ASUS
 */

@Controller
public class ProjectController {
    
    @RequestMapping ("/sendata")
    public String getData(HttpServletRequest data, Model kurir) {
        
         String nama_buah = data.getParameter("var_input1");
         String harga_perkilo = data.getParameter("var_input2");
         String jumlah_beli = data.getParameter("var_input3");
         String tunai = data.getParameter("var_input4");
         
         Double harga_kilo = Double.parseDouble(harga_perkilo);
         Double jmlh_beli = Double.parseDouble(jumlah_beli);
         Integer uang = Integer.valueOf(tunai);
         
         Double total = harga_kilo * jmlh_beli;
         String diskon = null;
         Double total_diskon = null;
         Double total_harga = null;
         
         if (total < 16000) {
             diskon = "0%";
             total_diskon = total*0/100;
             total_harga = total - (total*0/100);
         }
         else if (total >= 16000 && total <= 25000) {
             diskon = "10%";
             total_diskon = total*0/100;
             total_harga = total - (total*10/100);
         }
         else if (total > 25000) {
             diskon = "15%";
             total_diskon = total*15/100;
             total_harga = total - (total*15/100);
         }
         
         String view = null;
         Double sisa = total_harga - uang;
         
         if (uang < total_harga)
         {
             view = "Uang yang anda berikan kurang sejumlah " + sisa ;
         }
         else if (Objects.equals(uang, total_harga))
         {
             view = "Uang yang anda berikan pas";
         }
         else if (uang > total_harga)
         {
             view = "Kembalian anda " + (uang - total_harga);
         }
         
         kurir.addAttribute("nama", nama_buah);
         kurir.addAttribute("hargaKilo", harga_perkilo);
         kurir.addAttribute("jbeli", jumlah_beli);
         kurir.addAttribute("harga", total);
         kurir.addAttribute("disc", diskon);
         kurir.addAttribute("totdisc", total_diskon);
         kurir.addAttribute("tot_harga", total_harga);
         kurir.addAttribute("duit", view);
         kurir.addAttribute("uangtampil", tunai);
         
         return "viewer";
    }
}
   
