
public class Main {
	public static void main(String[] args) {		
		// Inisialisasi kelas Database sekaligus mengkoneksikan databse
		var db = new Database();
		
		// Membuat 3 Threads, setiap Thread merepresentasikan suatu tempat
		// contoh: Mataram, Narmada, Batulayar
		var t1 = new Thread(() -> {
			var loc = "Mataram";
			
			for (var i = 0; i < 20; i++) {
				var temp = (int)((Math.random() * 40) + 5);				
				db.addCurrentTemprature(temp, loc);
			}
		});		
		
		var t2 = new Thread(() -> {
			var loc = "Narmada";
			
			for (var i = 0; i < 20; i++) {
				var temp = (int)((Math.random() * 40) + 5);				
				db.addCurrentTemprature(temp, loc);
			}
		});
		
		
		var t3 = new Thread(() -> {
			var loc = "Batulayar";
			
			for (var i = 0; i < 20; i++) {
				var temp = (int)((Math.random() * 40) + 5);
				db.addCurrentTemprature(temp, loc);
			}
		});
		
		// Mulai Thread
		t1.start();
		t2.start();
		t3.start();
	}
}
