import java.sql.*;

public class Database {
	private final String url = "jdbc:mysql://localhost:3306/warehouse";
	private final String user = "root";
	
	private Connection con;
	
	public Database() {
		try {
			var con_ = DriverManager.getConnection(url, user, "");
			System.out.println("Terhubung ke Database!");
			
			var s = con_.createStatement();
			var rs = s.executeQuery("SELECT DATABASE()");
			if (rs.next()) {
				System.out.println("Database Terhubung = " + rs.getString(1));
			}
			con = con_;
		} catch (SQLException e) {
			System.out.println("Gagal Koneksi!");
		}
	}
	
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
