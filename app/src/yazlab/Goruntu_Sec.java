package yazlab;

import java.awt.Image;
import java.io.File;
import java.text.ParseException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import net.sourceforge.tess4j.*;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import java.util.Date;
import java.text.SimpleDateFormat; 
import java.util.logging.Level;
import java.util.logging.Logger;

public class Goruntu_Sec extends javax.swing.JFrame {
    private static String dosya_yolu; 
    public Goruntu_Sec() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Goruntu_Sec = new javax.swing.JButton();
        panel1 = new java.awt.Panel();
        show_image = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        fis_tablosu = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Goruntu_Sec.setText("Goruntu Sec");
        Goruntu_Sec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Goruntu_SecActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(show_image, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(show_image, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                .addContainerGap())
        );

        show_image.getAccessibleContext().setAccessibleName("");

        fis_tablosu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "işletme adı", "tarih", "fiş no", "ürünler KDV'ler fiyatlar", "toplam fiyat"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(fis_tablosu);
        if (fis_tablosu.getColumnModel().getColumnCount() > 0) {
            fis_tablosu.getColumnModel().getColumn(0).setResizable(false);
            fis_tablosu.getColumnModel().getColumn(1).setResizable(false);
            fis_tablosu.getColumnModel().getColumn(2).setResizable(false);
            fis_tablosu.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 846, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Goruntu_Sec, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Goruntu_Sec))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Goruntu_SecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Goruntu_SecActionPerformed
        JFileChooser fc=new JFileChooser();
        int i=fc.showOpenDialog(this);
        
        if(i==JFileChooser.APPROVE_OPTION){
            this.dosya_yolu= fc.getSelectedFile().getPath();
            System.load("C:\\Users\\anil_\\Desktop\\Fis\\opencv\\build\\java\\x64\\opencv_java411.dll");
               
            Mat erode=new Mat();
            Mat dilate=new Mat();
            Mat source = Imgcodecs.imread(dosya_yolu,Imgproc.COLOR_BGR2GRAY);
            Mat imageThresh = new Mat(source.size(), CvType.CV_8UC4);
            Mat imageBlurr = new Mat(source.size(),CvType.CV_8UC4);
            Mat imageA = new Mat(source.rows(),source.cols(),source.type());
            Mat morp=new Mat(); 
            
            Imgproc.cvtColor(source,imageA, Imgproc.COLOR_BGR2GRAY);
            Imgproc.threshold(imageA,imageThresh,127,255,Imgproc.THRESH_BINARY_INV+Imgproc.THRESH_OTSU);
            Imgproc.GaussianBlur(imageThresh, imageBlurr, new Size(9,9),0);
            //Imgproc.adaptiveThreshold(imageBlurr, imageThresh1,255,Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY,11,12);
                
            Mat kernel =Imgproc.getStructuringElement(Imgproc.MORPH_CROSS, new Size(3, 3));
            
            Imgproc.morphologyEx(imageBlurr, morp,Imgproc.MORPH_OPEN,kernel);
            //Imgproc.erode(morp,erode, kernel);
            Imgproc.dilate(morp, dilate, kernel);
            
            Imgcodecs.imwrite("C:\\Users\\anil_\\Desktop\\Fis\\Yazlab\\fis_son.jpg",dilate); 
            
            File imageFile = new File("C:\\Users\\anil_\\Desktop\\Fis\\Yazlab\\fis_son.jpg");
            Tesseract tessInst = new Tesseract();
            tessInst.setDatapath("C:\\Users\\anil_\\Desktop\\Fis\\Yazlab\\tessdata");
            tessInst.setLanguage("tur");
            
            
        try { 
           String result = tessInst.doOCR(imageFile);
           result = result.toUpperCase();
           System.out.println(result);
           
           
           String srkt = result.substring(0,20);
           System.out.println(srkt);
           
           int trh=result.indexOf("TARİH");
           String trh1 = result.substring(trh+8,trh+19);
           int len = trh1.length();
           String trh2 = "00/00/0000 00:00:00";
           int ind = 0;
           for(int a=0; a < len; a++){
               if ((Character.isDigit(trh1.charAt(a)) == true) || (trh1.charAt(a) == '/')) {
                   char c = trh1.charAt(a);
                   trh2 = replaceChar(trh2,c,ind);
                   ind++;
            }
           }
           int saat=result.indexOf("SAAT");
           String saat1 = result.substring(saat+7,saat+16);
           len = saat1.length();
           ind++;
           for(int a=0; a < len; a++){
               if ((Character.isDigit(saat1.charAt(a)) == true) || (saat1.charAt(a) == ':')) {
                   char c = saat1.charAt(a);
                   trh2 = replaceChar(trh2,c,ind);
                   ind++;
            }
           }
           SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
           Date date=formatter1.parse(trh2);
           //System.out.println(trh1);
           //System.out.println(trh2);
           System.out.println(date);
           
           len = result.length();
           int no = 0;
           for(int a=0; a < len; a++){
               if(result.charAt(a) == 'F'){
                   if(result.charAt(a+2) == 'Ş'){
                       no = a;
                       break;
                   }
               }
           }
           String no1 = result.substring(no+8,no+13);
           String no2 = "00000";
           len = no1.length();
           ind = 4;
           for(int a=len-1; -1 < a; a--){
               if ((Character.isDigit(no1.charAt(a)) == true)) {
                   char c = no1.charAt(a);
                   no2 = replaceChar(no2,c,ind);
                   ind--;
            }
           }
           int fisNo = Integer.parseInt(no2);
           //System.out.println(no1);
           //System.out.println(no2);
           System.out.println(fisNo);
            
           int kdv=result.indexOf("TOPKDV");
           String kdv1 = result.substring(kdv+8, kdv+14);
           String kdv2 = "00000";
           len = kdv1.length();
           ind = 4;
           char v =',';
           char n = '.';
           for(int a=len-1; -1 < a; a--){
               if ((Character.isDigit(kdv1.charAt(a)) == true)) {
                   char c = kdv1.charAt(a);
                   kdv2 = replaceChar(kdv2,c,ind);
                   ind--;
            }
               if (v == kdv1.charAt(a) || n == kdv1.charAt(a)) {
                   kdv2 = replaceChar(kdv2,n,ind);
                   ind--;
            }
           }
           float topKdv = Float.parseFloat(kdv2);
           //System.out.println(kdv1);
           //System.out.println(kdv2);
           System.out.println(topKdv);
           
           int lam=result.indexOf("TOPLAM");
           String lam1= result.substring(lam+8,lam+12);
           String lam2 = "000000";
           len = lam1.length();
           ind = 5;
           for(int a=len-1; -1 < a; a--){
               if ((Character.isDigit(lam1.charAt(a)) == true)) {
                   char c = lam1.charAt(a);
                   lam2 = replaceChar(lam2,c,ind);
                   ind--;
            }
               if (v == lam1.charAt(a) || n == lam1.charAt(a)) {
                   lam2 = replaceChar(lam2,n,ind);
                   ind--;
            }
           }
           float toplam = Float.parseFloat(lam2);
           //System.out.println(lam1);
           //System.out.println(lam2);
           System.out.println(toplam);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }   catch (ParseException ex) {
                Logger.getLogger(Goruntu_Sec.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }//GEN-LAST:event_Goruntu_SecActionPerformed
        ImageIcon ii=new ImageIcon(dosya_yolu);
        Image image=ii.getImage().getScaledInstance(show_image.getWidth(), show_image.getHeight(),Image.SCALE_SMOOTH);
        show_image.setIcon(new ImageIcon(image));
    }
    
    public String replaceChar(String str, char ch, int index){
        StringBuilder myString = new StringBuilder(str);
        myString.setCharAt(index,ch);
        return myString.toString();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Goruntu_Sec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Goruntu_Sec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Goruntu_Sec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Goruntu_Sec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Goruntu_Sec().setVisible(true);
            }
            
        });
        /////BURADAN İTİBAREN
        
         
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Goruntu_Sec;
    private javax.swing.JTable fis_tablosu;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Panel panel1;
    private javax.swing.JLabel show_image;
    // End of variables declaration//GEN-END:variables
}
