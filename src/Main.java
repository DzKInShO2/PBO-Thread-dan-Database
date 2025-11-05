
public class Main {
	public static void main(String[] args) {
		System.out.println("Hello, World");
		
		var db = new Database();
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
		
		t1.start();
		t2.start();
		t3.start();
	}
}
