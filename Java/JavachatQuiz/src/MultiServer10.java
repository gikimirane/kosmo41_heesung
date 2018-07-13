import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MultiServer10 {

	ServerSocket serverSocket = null;
	Socket socket = null;
	Map<String, PrintWriter> clientMap;

	public MultiServer10() {
		// Į���̾�Ʈ�� ��½�Ʈ���� ������ �ؽ��� ����. //������
		clientMap = new HashMap<String, PrintWriter>();
		// �ؽ��� ����ȭ ����
		Collections.synchronizedMap(clientMap);
	}

	public void init() {

		try {
			serverSocket = new ServerSocket(9999);// 9999��Ʈ�� �������� ��ü����
			System.out.println("������ ���۵Ǿ����ϴ�.");

			while (true) {
				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress() + ":" + socket.getPort());
				// System.out.println("�����:"+socket.getPort());
				Thread mst = new MultiServerT(socket); // ������ ����
				mst.start(); // ������ �õ�
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {
				serverSocket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// ���ӵ� ��� Ŭ���̾�Ʈ�鿡�� �޼��� ����
	public void sendAllMsg(String msg) {
		// ��½�Ʈ���� ���������� ���ͼ� �ش�޼��� ���.
		Iterator it = clientMap.keySet().iterator();
		while (it.hasNext()) {
			try {
				PrintWriter it_out = (PrintWriter) clientMap.get(it.next());
				it_out.println(msg);

			} catch (Exception e) {
				System.out.println("����" + e);
			}
		}

	}

	public void sendSoloMsg(PrintWriter out) {
		// ��½�Ʈ���� ���������� ���ͼ� �ش�޼��� ���.
		Set<String> it = clientMap.keySet();

		try {
			// PrintWriter it_ou =clientMap.get(out);
			out.print("[ ");
			for (String b : it) {
				out.print(b.toString() + " ");
			}
			out.println("]");
		} catch (Exception e) {
			System.out.println("����" + e);
		}
	}
	public static void main(String[] args) {
		MultiServer10 ms = new MultiServer10();
		ms.init();

	}

	////////////////////////////// ����Ŭ����///////////////////////////////
	class MultiServerT extends Thread {
		Socket socket = null;
		PrintWriter out = null;
		BufferedReader in = null;

		public MultiServerT(Socket socket) {
			this.socket = socket;
			try {
				out = new PrintWriter(this.socket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			} catch (Exception e) {
				System.out.println("����" + e);
			}
		}

		@Override
		public void run() {
			String name = ""; // Ŭ���̾�Ʈ�� ���� ���� �̸��� ������ ����
			try {
				name = in.readLine(); // Ŭ���̾�Ʈ���� ó������ ������ �޼�����
										// Ŭ���̾�Ʈ�� ����� �̸��̴�
				sendAllMsg(name + "�Ϲ� �����Ͽ����ϴ�.");// �������� ������ �ٸ� Ŭ���̾�Ʈ����Ͽ� �˸�.
				clientMap.put(name, out); // �ؽ��ʿ� Ű�� �̸���½�Ʈ����ü�� ����
				System.out.println("���� ������ ����" + clientMap.size() + "�� �Դϴ�.");

				String s = "";
				while (in != null) {
					s = in.readLine();
					System.out.println(s);
					if (s.equals("q") || s.equals("Q"))
						break;
					if (s.equals("/list")) {
						sendSoloMsg(out);
						continue;
					}
					if (s.substring(0, 2).equals("/r")) {
						String e[] = s.split(" ");
						PrintWriter it_out = (PrintWriter) clientMap.get(e[1]);
						it_out.print(name+"���� �ӼӸ� :");
						for(int i=2; i<e.length;i++) {
						it_out.print(e[i]+" ");
						}
						it_out.println();
						continue;
					}
					if (s.substring(0, 3).equals("/to")) {
						String e[] = s.split(" ");
						PrintWriter it_out = (PrintWriter) clientMap.get(e[1]);
						it_out.print(name+"���� �ӼӸ� :");
						for(int i=2; i<e.length;i++) {
						it_out.print(e[i]+" ");
						}
						it_out.println();
						continue;
					}
					sendAllMsg(s);

				}
			} catch (Exception e) {
				System.out.println("����" + e);
			} finally {
				// ���ܰ� �߻��Ҷ� ����, �ؽ��ʿ��� �ش� ������ ����.
				// ���� �����ϰų� ������ java.net.SocketException. ���ܹ߻�
				clientMap.remove(name);
				sendAllMsg(name + "�Ϲ� �����ϼ̽��ϴ�.");
				System.out.println("���� ������ ����" + clientMap.size() + "�� �Դϴ�");
				try {
					in.close();
					out.close();

					socket.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
