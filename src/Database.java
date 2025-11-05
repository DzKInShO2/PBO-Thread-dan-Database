import java.sql.*;

public class Database {
	// Menjadikan database warehouse pada localhost sebagai target koneksi
	private final String url = "jdbc:mysql://localhost:3306/warehouse";
	// Mengatur root sebagai user yang digunakan
	private final String user = "root";
	
	private Connection con;
	
	public Database() {
		try {
			// Mendapatkan koneksi ke database
			var con_ = DriverManager.getConnection(url, user, "");
			System.out.println("Terhubung ke Database!");
			
			var s = con_.createStatement();
			
			// Query untuk mendatapatkan database teroilih
			var rs = s.executeQuery("SELECT DATABASE()");
			if (rs.next()) {
				// Print database
				System.out.println("Database Terhubung = " + rs.getString(1));
			}
			con = con_;
		} catch (SQLException e) {
			System.out.println("Gagal Koneksi!");
		}
	}
	
	// Fungsi ini diberikan 'synchronized' untuk menandai bahwa setiap thread harus
	// melakukan sinkronisasi saat menjalankan fungsi ini. Mengapa begitu? Karena
	// ada kemungkinan terjadinya race condition, dan saya juga tidak yakin kalau
	// 'executeUpdate' bisa menerima update dari banyak tempat sekaligus.
	public synchronized void addCurrentTemprature(int temp, String loc) {
		var q = "INSERT INTO Temprature (time, temp, loc) VALUES (?, ?, ?)";
		try (var ps = con.prepareStatement(q)) {
		    var now = System.currentTimeMillis();
		    var date = new Timestamp(now);
		    
	        ps.setTimestamp(1, date);
	        ps.setInt(2, temp);
	        ps.setString(3, loc);
	        ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
