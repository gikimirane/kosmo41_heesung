import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Sender5 extends Thread {
	Socket socket;
	PrintWriter out = null;
	String name;

	// 생성자
	public Sender5(Socket socket, String name) {
		this.socket = socket;
		try {
			out = new PrintWriter(this.socket.getOutputStream(), true);
			this.name = name;
		} catch (Exception e) {
			System.out.println("예외S3:" + e);
		}
	}

	@Override
	public void run() {
		Scanner s = new Scanner(System.in);

		try {
			out.println(name);

			while (out != null) {
				try {
					String s2 = s.nextLine();
					if (s2.length() > 3 && s2.substring(0, 3).equals("/to")) {
						out.println(s2);
						String e[] = s2.split(" ");
						String a = e[1];
						for (;;) {
							String b = s.nextLine();
							
							if (b.equals("//to") || b.equals("//TO")) {
								break;
							}else {
							out.println("/to " + a + " " + b);
							}
						}
					}
			else {
						out.println(s2);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			out.close();
			socket.close();
		} catch (Exception e) {
			System.out.println("예외S2:" + e);
		}
	}

}
