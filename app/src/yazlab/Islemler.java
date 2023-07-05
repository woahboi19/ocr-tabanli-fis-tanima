package yazlab;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author USER
 */
public class Islemler {
private Connection con=null;
private Statement statement=null;
private PreparedStatement preparedstatement=null;

public Islemler() {
   
    String url="jdbc:mysql://"+ Baglanti.host + ":" +  Baglanti.port + "/" +  Baglanti.db_ismi;
    try {
        Class.forName("com.mysql.jdbc.Driver");     }
    catch(ClassNotFoundException ex) {
        System.out.println("driver bulunamadı...");     }
    try {
         con = DriverManager.getConnection(url, Baglanti.kullanici_adi, Baglanti.parola);
	 System.out.println("Bağlantı başarılı");   } 
    catch (SQLException ex) {
	//ex.printStackTrace();
	System.out.println("Bağlantı başarısız...");    }

    }

    
}
    