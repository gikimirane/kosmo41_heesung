import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MultiServer10 
{

	ServerSocket serverSocket = null;
	Socket socket = null;
	Map<String, PrintWriter> clientMap;
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	List<String> roomlist = new ArrayList<>();
	List<String> list = new ArrayList<>();
	// ��ê��
	List<String> userAll = new ArrayList<>();
	// ��
	List<String> allroomlist = new ArrayList<>();
	// ��Ģ��
	List<String> blocktalk = new ArrayList<>();
	//Iterator<String> it = clientMap.keySet().iterator();
	

	public MultiServer10() 
	{
		// Į���̾�Ʈ�� ��½�Ʈ���� ������ �ؽ��� ����. //������
		clientMap = new HashMap<String, PrintWriter>();
		// �ؽ��� ����ȭ ����
		Collections.synchronizedMap(clientMap);
	}

	public void init() 
	{
		try 
		{	
			con = ConnectionPool.getConnection();
			serverSocket = new ServerSocket(9999);// 9999��Ʈ�� �������� ��ü����
			System.out.println("������ ���۵Ǿ����ϴ�.");
			stmt = con.createStatement();
			
			while (true) 
			{
				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress() + ":" + socket.getPort());
				Thread mst = new MultiServerT(socket); // ������ ����
				mst.start(); // ������ �õ�
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			try
			{
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
				serverSocket.close();
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}

	// ���ӵ� ��� Ŭ���̾�Ʈ�鿡�� �޼��� ����
	public void hashsendAllMsg(String msg) 
	{
		// ��½�Ʈ���� ���������� ���ͼ� �ش�޼��� ���.
		Iterator it = clientMap.keySet().iterator();
		while (it.hasNext()) 
		{
			try 
			{
				PrintWriter it_out = (PrintWriter) clientMap.get(it.next());
				it_out.println(msg);
			} 
			catch (Exception e) 
			{
				System.out.println("����" + e);
			}
		}

	}

	// ��ɾ� ����
	public void Mlist(PrintWriter out)
	{
		try 
		{
			out.println("-----------------------------------------");
			out.println("��ҹ��� �� ������ �ּ���^^ ");
			out.println("-----------����------------");
			out.println("/q or /Q   : ä�� ������");
			out.println("/w �޴»�� : �ӼӸ� ������ ");
			out.println("/to �޴»�� : �ӼӸ� ���� ");
			out.println("/list  :  ���¹� �ο� ���� ");
			out.println("/roomlist   : �� ��� ���� ");
			out.println("/Room   : �� ����� ");
			out.println("/roomip   :  �� �����ϱ� ");
			out.println("/Br ��Ģ��  :  ��Ģ�� �߰��ϱ� ");
			out.println("/All �����Ҹ�  :   ��ü���� �������� ������ ");
			out.println("---------�� �ȿ����� --------");
			out.println("/Rlist  : ��ȿ��� ��� �ο����� ");
			out.println("/exit   : �泪���� ");
			out.println("----------���� ����---------- ");
			out.println("/out  �����ų�̸�    : �濡�� ����. ");
			out.println("/roomdelete     : �� ���� / ����ֱ� / �ٳ����� ");
			out.println("/cho �ʴ��һ��    :   �ڱ� �濡 ����� �ʴ���");
			out.println("------------------------------------------- ");
		} 
		catch (Exception e)
		{
			System.out.println("����" + e);
		}

	}

	//------------------------------main------------------------
	public static void main(String[] args) 
	{
		MultiServer10 ms = new MultiServer10();
		ms.init();

	}
	//------------------------------main------------------------
	
	// ��ü�濡�� ������ �޼���
	public void sendAllMsg(String msg, PrintWriter out) 
	{
		// ��½�Ʈ���� ���������� ���ͼ� �ش�޼��� ���.
		Iterator<String> it = clientMap.keySet().iterator();
		int q = 0;
		try 
		{
			rs = stmt.executeQuery("select*from userlist");
			while (rs.next()) 
			{
				userAll.add(rs.getString(1));
			}
			rs = stmt.executeQuery("select*from chat");
			while (rs.next()) 
			{
				blocktalk.add(rs.getString(1));
			}
			for (int i = 0; i < blocktalk.size(); i++) 
			{
				if (msg.contains(blocktalk.get(i).toString())) 
				{
					out.println("��Ģ���Դϴ�");
					q = 1;
					break;
				} 
				else 
				{
					q = 0;
					continue;
				}
			}
			while (it.hasNext()) 
			{
				if (q == 0) 
				{
					String d = it.next().toString();
					PrintWriter it_out = (PrintWriter) clientMap.get(d);
					for (int j = 0; j < userAll.size(); j++) 
					{
						if (d.equals(userAll.get(j)))
						{
							it_out.println(msg);
						}
					}
					continue;
				}
				if (q == 1) 
				{
					break;
				}
			}
			rs = stmt.executeQuery("select*from userlist");
			while (rs.next()) 
			{
				userAll.remove(rs.getString(1));
			}
			rs = stmt.executeQuery("select*from chat");
			while (rs.next()) 
			{
				blocktalk.remove(rs.getString(1));
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("aaaaa");
		}
		
	}
	// ��ȿ��� ������ �޼���
	public void sendRoomMsg(String room, String msg, PrintWriter out) 
	{
		// ��½�Ʈ���� ���������� ���ͼ� �ش�޼��� ���.
		Iterator<String> it = clientMap.keySet().iterator();
		int q = 0;
		try 
		{
			rs = stmt.executeQuery("select id from " + room);
			while (rs.next())
			{
				allroomlist.add(rs.getString(1));
			}

			rs = stmt.executeQuery("select*from chat");
			while (rs.next()) 
			{
				blocktalk.add(rs.getString(1));
			}
			for (int i = 0; i < blocktalk.size(); i++) 
			{
				if (msg.contains(blocktalk.get(i).toString())) 
				{
					out.println("��Ģ���Դϴ�");
					q = 1;
					break;
				} 
				else 
				{
					q = 0;
					continue;
				}
			}
			while (it.hasNext()) 
			{
				if (q == 0) 
				{
					String d = it.next().toString();
					PrintWriter it_out = (PrintWriter) clientMap.get(d);
					for (int j = 0; j < allroomlist.size(); j++)
					{
						if (d.equals(allroomlist.get(j))) 
						{
							it_out.println(msg);
						}
					}
					continue;
				}
				if (q == 1) 
				{
					break;
				}
			}
			rs = stmt.executeQuery("select id from " + room);
			while (rs.next()) 
			{
				allroomlist.remove(rs.getString(1));
			}
			rs = stmt.executeQuery("select*from chat");
			while (rs.next()) 
			{
				blocktalk.remove(rs.getString(1));
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("aaaaa");
		} 		

	}
	// ���۽� ��ê�� �ο�
	public void start1(String name)
	{
		try 
		{
			rs = stmt.executeQuery("INSERT INTO userlist " + "VALUES('" + name + "')");
		} 
		catch (SQLException sqle) 
		{
			// System.out.println("�ߺ��� ���̵��Դϴ�.�ٽ� �������ּ���.");
		} 
	}
	// ê�� ����
	private void close(String name) 
	{
		try 
		{
			rs = stmt.executeQuery("delete userlist where id='" + name + "'");
		}
		catch (SQLException sqle) 
		{
			// System.out.println("�ߺ��� ���̵��Դϴ�.�ٽ� �������ּ���.");
		}
	}
	// ��Ģ����
	public void Blackchat(String blackplus,PrintWriter out) 
	{
		try 
		{	
			String black[] = blackplus.split(" ");
			rs = stmt.executeQuery("INSERT INTO chat " + "VALUES('" + black[1] + "')");
			out.println("��Ģ�� �߰� �Ǿ����ϴ�.");
		} 
		catch (SQLException sqle) 
		{
			// System.out.println("�ߺ��� ���̵��Դϴ�.�ٽ� �������ּ���.");
		} 	
	}
	//�� ��Ģ�� ���
	public void roomBlackchat(String blackplus,PrintWriter out) 
	{
		try 
		{	
			String black[] = blackplus.split(" ");
			rs = stmt.executeQuery("INSERT INTO chat " + "VALUES('" + black[2] + "')");
			out.println("��Ģ�� �߰� �Ǿ����ϴ�.");
		} 
		catch (SQLException sqle) 
		{
			// System.out.println("�ߺ��� ���̵��Դϴ�.�ٽ� �������ּ���.");
		} 	
	}
	// ������ �濡 ����
	public void Roomjoin(String name, String roomname,PrintWriter out) throws IOException 
	{
		try 
		{
			int f = 1;
			rs = stmt.executeQuery("select human from " + roomname + " where super = 'super'");
			rs.next(); 
			{
				f = rs.getInt(1);
			}
			if (f == 0) 
			{
				out.println("�ο��� �� á���ϴ�.");
				return;
			}
			else 
			{
				sendAllMsg(name + "���� " + roomname + " �濡 �����ϼ̽��ϴ�.", out);
				sendRoomMsg(roomname, name + "���������ϼ̽��ϴ�.", out);
				rs = stmt.executeQuery("INSERT INTO " + roomname + " VALUES('" + name + "','null',0)");
				rs = stmt.executeQuery("DELETE userlist" + " where id='" + name + "'");
				rs = stmt.executeQuery("update " + roomname + " set human = human-1");				
				System.out.println("while ��");		
			}			
		}
		catch (SQLException sqle)
		{
			// System.out.println("�ߺ��� ���̵��Դϴ�.�ٽ� �������ּ���.");
		}
		
	}
	// ������� �濡 ���� ����/��ê������ ����
	public void Room_user_de(String name, String roomname)
	{
		String sup = "";
		int human = 0;
		String nextname = "";
		try 
		{
			rs = stmt.executeQuery("update " + roomname + " set human = human+1");
			rs = stmt.executeQuery("select super,human from " + roomname + " where super = 'super'");
			while (rs.next()) 
			{
				sup = rs.getString(1);
				human = rs.getInt(2);
			}

			rs = stmt.executeQuery("delete " + roomname + " WHERE id = '" + name + "'");
			rs = stmt.executeQuery("select id from " + roomname);
			while (rs.next()) 
			{
				nextname = rs.getString(1);
			}

			rs = stmt.executeQuery("update " + roomname + " set super = '" + sup + "', human = '" + human + "' where id = '" + nextname + "'");
			rs = stmt.executeQuery("INSERT INTO userlist " + "VALUES('" + name + "')");
			rs = stmt.executeQuery("select count(*) from " + roomname);
			int i = 0;
			rs.next();
			i = rs.getInt(1);
			if (i == 0) 
			{
				rs = stmt.executeQuery("drop table " + roomname);
				rs = stmt.executeQuery("delete roomlist where list='" + roomname + "'");
				roomlist.remove(roomname);
			}
			
		}
		catch (SQLException sqle)
		{
			// System.out.println("�ߺ��� ���̵��Դϴ�.�ٽ� �������ּ���.");
		} 
	}
	// �����
	public void Roomake(String name, String roomname, int human,PrintWriter out)
	{
		try 
		{
			out.println(roomname + " �濡 �����ϼ̽��ϴ�.");			
			rs = stmt.executeQuery("create table " + roomname + "( id varchar2(20),super varchar(20), human number(20))");
			rs = stmt.executeQuery("INSERT INTO " + roomname + " VALUES('" + name + "','super','" + human + "')");
			rs = stmt.executeQuery("INSERT INTO roomlist" + " VALUES('" + roomname + "')");
			rs = stmt.executeQuery("update " + roomname + " set human = human-1");
			rs = stmt.executeQuery("DELETE userlist where id='"+ name + "'");		
		}
		catch (SQLException sqle) 
		{
			// System.out.println("�ߺ��� ���̵��Դϴ�.�ٽ� �������ּ���.");
		} 
	}
	// ������ �� ��ϵ�
	public void Roomlist(String name) 
	{
		Iterator<String> it = clientMap.keySet().iterator();
		try 
		{
			rs = stmt.executeQuery("select*from roomlist");
			while (rs.next()) 
			{
				roomlist.add(rs.getString(1));
			}
			while (it.hasNext()) 
			{
				String d = it.next().toString();
				PrintWriter it_out = (PrintWriter) clientMap.get(d);
				if (d.equals(name)) 
				{
					it_out.print("[ ");
					for (String e : roomlist) 
					{
						it_out.print(e + " ");
					}
					it_out.println("]");
				}
			}
			rs = stmt.executeQuery("select*from roomlist");
			while (rs.next()) 
			{
				roomlist.remove(rs.getString(1));
			}
		} 
		catch (SQLException sqle)
		{
			// System.out.println("�ߺ��� ���̵��Դϴ�.�ٽ� �������ּ���.");
		} 
	}
	// ���¹濡�ִ� �ο�����.
	public void Userlist(String name)
	{
		Iterator<String> it = clientMap.keySet().iterator();
		try 
		{
			rs = stmt.executeQuery("select*from userlist");
			while (rs.next()) 
			{
				list.add(rs.getString(1));
			}
			while (it.hasNext())
			{
				String d = it.next().toString();
				PrintWriter it_out = (PrintWriter) clientMap.get(d);
				if (d.equals(name))
				{
					it_out.print("[ ");
					for (String e : list) 
					{
						it_out.print(e + " ");
					}
					it_out.println("]");
				}
			}
			rs = stmt.executeQuery("select*from userlist");
			while (rs.next())
			{
				list.remove(rs.getString(1));
			}
		}
		catch (SQLException sqle)
		{
			// System.out.println("�ߺ��� ���̵��Դϴ�.�ٽ� �������ּ���.");
		} 
	}
	// �濡 �ִ� ���� ����
	public void RoomUserlist(String name, String roomname)
	{
		Iterator<String> it = clientMap.keySet().iterator();
		try 
		{
			rs = stmt.executeQuery("select*from " + roomname);
			while (rs.next())
			{
				list.add(rs.getString(1));
			}
			while (it.hasNext()) 
			{
				String d = it.next().toString();
				PrintWriter it_out = (PrintWriter) clientMap.get(d);
				if (d.equals(name)) 
				{
					it_out.print("[ ");
					for (String e : list) 
					{
						it_out.print(e + " ");
					}
					it_out.println("]");
				}
			}
			rs = stmt.executeQuery("select*from " + roomname);
			while (rs.next())
			{
				list.remove(rs.getString(1));
			}
		} 
		catch (SQLException sqle)
		{
			// System.out.println("�ߺ��� ���̵��Դϴ�.�ٽ� �������ּ���.");
		}
	}
	// ������
	public void Roomdelete(String name, String roomname,PrintWriter out) 
	{
		Iterator<String> it = clientMap.keySet().iterator();
		String user = "";
		try 
		{
			rs = stmt.executeQuery("select id from " + roomname + " where super ='super'");
			while (rs.next()) 
			{
				user = rs.getString(1);
			}
			if (name.equals(user)) 
			{
				sendRoomMsg(roomname, "���� ���ĵǾ����ϴ� ������ ���ּ���. [/exit]", out);
				rs = stmt.executeQuery("insert into userlist select id from " + roomname);
				rs = stmt.executeQuery("drop table " + roomname);
				rs = stmt.executeQuery("delete roomlist where list='" + roomname + "'");
			}
			if (!name.equals(user))
			{
				while (it.hasNext()) 
				{
					String d = it.next().toString();
					PrintWriter it_out = (PrintWriter) clientMap.get(d);
					if (d.equals(name)) 
					{
						it_out.println("���常 ����� �� �ִ� ����Դϴ�.");
					}
				}
			}
		} 
		catch (SQLException sqle) 
		{
			// System.out.println("�ߺ��� ���̵��Դϴ�.�ٽ� �������ּ���.");
		} 
	}
	// �濡�� ����
	public void RoomUserOut(String name, String roomname, String outname)
	{
		Iterator<String> it = clientMap.keySet().iterator();
		String user = "";
		try 
		{
			rs = stmt.executeQuery("select id from " + roomname + " where super ='super'");
			while (rs.next())
			{
				user = rs.getString(1);
			}
			if (name.equals(user)) 
			{
				while (it.hasNext()) 
				{
					String d = it.next().toString();
					PrintWriter it_out = (PrintWriter) clientMap.get(d);
					if (d.equals(outname))
					{
						it_out.println("���忡 ���� ������߽��ϴ�.Ȯ��[/y]�� �����ּ���");
						break;
					}
				}
				rs = stmt.executeQuery("insert into userlist values ('" + outname + "')");
				rs = stmt.executeQuery("delete " + roomname + " WHERE id = '" + outname + "'");
			}
			if (!name.equals(user)) 
			{
				while (it.hasNext()) 
				{
					String d = it.next().toString();
					PrintWriter it_out = (PrintWriter) clientMap.get(d);
					if (d.equals(name)) 
					{
						it_out.println("���常 ����� �� �ִ� ����Դϴ�.");
					}
				}
			}
		} 
		catch (SQLException sqle) 
		{
			// System.out.println("�ߺ��� ���̵��Դϴ�.�ٽ� �������ּ���.");
		} 
	}
	// ������ �ʴ��ϴ� �޼��� ������.
	public void RoomUserCho(String name, String roomname, String choname) 
	{
		Iterator<String> it = clientMap.keySet().iterator();
		String user = "";
		try 
		{
			rs = stmt.executeQuery(
					"insert into cholist values('" + name + "','" + roomname + "','" + choname + "')");
			rs = stmt.executeQuery("select*from userlist where id='" + choname + "'");
			while (rs.next())
			{
				user = rs.getString(1);
			}
			while (it.hasNext())
			{
				String d = it.next().toString();
				PrintWriter it_out = (PrintWriter) clientMap.get(d);
				if (d.equals(user)) 
				{
					it_out.println(name + "���� " + roomname + " �濡 �ʴ��ϼ̽��ϴ�");
					it_out.println("�����Ͻðڽ��ϱ�?  ���� /y  ���� /n");
					break;
				}
			}
		}
		catch (SQLException sqle)
		{
			// System.out.println("�ߺ��� ���̵��Դϴ�.�ٽ� �������ּ���.");
		} 
	}
	// �ʴ� �����ϱ�.
	public void chono(String name) 
	{
		Iterator<String> it = clientMap.keySet().iterator();
		String user = "";
		try
		{
			rs = stmt.executeQuery("select id from cholist where choid = '" + name + "'");
			while (rs.next()) 
			{
				user = rs.getString(1);
			}
			while (it.hasNext()) 
			{
				String d = it.next().toString();
				PrintWriter it_out = (PrintWriter) clientMap.get(d);
				if (d.equals(user)) 
				{
					it_out.println(name + "���� �����ϼ̽��ϴ�.");
					break;
				}
			}
			rs = stmt.executeQuery("delete cholist where choid = '" + name + "'");
		}
		catch (SQLException sqle) 
		{
			// System.out.println("�ߺ��� ���̵��Դϴ�.�ٽ� �������ּ���.");
		} 
	}
	// �ʴ� �����ϱ�
	public void choyes(String name,PrintWriter out) throws IOException
	{
		Iterator<String> it = clientMap.keySet().iterator();
		String user = "";
		try
		{
			rs = stmt.executeQuery("select id from cholist where choid = '" + name + "'");
			while (rs.next())
			{
				user = rs.getString(1);
			}
			while (it.hasNext()) 
			{
				String d = it.next().toString();
				PrintWriter it_out = (PrintWriter) clientMap.get(d);
				if (d.equals(user))
				{
					it_out.println(name + "���� �����ϼ̽��ϴ�.");
					break;
				}
			}
			int f = 0;
			String choroom = "";
			String supid = "";
			rs = stmt.executeQuery("select room from cholist where choid = '" + name + "'");
			if (rs.next())
			{
				choroom = rs.getString(1);
			}
			rs = stmt.executeQuery("select human,id from " + choroom + " where super = 'super'");
			if (rs.next()) 
			{
				f = rs.getInt(1);
				supid = rs.getString(2);
			}

			if (f == 0) 
			{
				out.println("�ο��� �� á���ϴ�.");
				PrintWriter it_out = (PrintWriter) clientMap.get(supid);
				it_out.println("�ʴ��Ͻ� " + name + " ���� �ο��� �� ���� �������̽��ϴ�.");
			}
			else if(f!=0) 
			{
			Roomjoin(name, choroom,out);
			sendAllMsg(name + "���� " + choroom + " �濡 �����ϼ̽��ϴ�.", out);
			sendRoomMsg(choroom, name + "���������ϼ̽��ϴ�.", out);
			rs = stmt.executeQuery("delete cholist where choid = '" + name + "'");
			}
		} 
		catch (SQLException sqle)
		{
			// System.out.println("�ߺ��� ���̵��Դϴ�.�ٽ� �������ּ���.");
		} 
		
	}
	//�ӼӸ�
	public void rnltakf(String name,String msg,PrintWriter out) 
	{
		String talk[] = msg.split(" ");
		PrintWriter it_out = (PrintWriter) clientMap.get(talk[1]);
		it_out.print(name + "���� �ӼӸ� :");
		String rnlt = "";
		for (int i = 2; i < talk.length; i++) 
		{
			it_out.print(talk[i] + " ");
			rnlt += talk[i];
		}
		out.print(talk[1] + "�Կ��� : " + rnlt + " ");
		it_out.println();
		out.println();
		
	}
	//�볻�� �ӼӸ�
	public void roomrnlt(String name,String msg,PrintWriter out) 
	{
		String talk[] = msg.split(" ");
		PrintWriter it_out = (PrintWriter) clientMap.get(talk[2]);
		it_out.print(name + "���� �ӼӸ� :");
		for (int i = 3; i < talk.length; i++) 
		{
			it_out.print(talk[i] + " ");
			out.print(talk[2] + "�Կ��� : " + talk[i] + " ");
		}
		it_out.println();
		out.println();
		
	}
	//�� �� �ߺ� Ȯ��
	public void black(String name,PrintWriter out) throws SQLException 
	{
			int count = 0;
			rs = stmt.executeQuery("select*from userlist");
			while (rs.next()) 
			{
				if(name.equals(rs.getString(1))) 
				{
					out.println("�ߺ� ���̵� �Դϴ�.�ٸ� ���̵� �Է��ϼ���");
					count = 1;
					break;
				}
				
			}
			rs = stmt.executeQuery("select*from blacklist");
			while (rs.next()) 
			{
				if (name.equals(rs.getString(1))) 
				{
					out.println("������Ʈ�� �߰��� ���̵� �Դϴ�.�ٸ� ���̵� �Է��ϼ���");
					count = 1;
					break;
				} 
			}
			if(count==0) 
			{
				out.println("����� �� �ִ� ���̵��Դϴ�.");
				out.println("�ٽ� �Է��� ������ �����ϼ���");
				System.out.println("gg");
			}
		}
	 
	
		
	////////////////////////////// ����Ŭ����///////////////////////////////
	class MultiServerT extends Thread 
	{	
		Socket socket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = null;
		String rom = null;

		public MultiServerT(Socket socket)
		{
			this.socket = socket;
			try 
			{
				out = new PrintWriter(this.socket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			} 
			catch (Exception e)
			{
				System.out.println("����" + e);
			}
		}

		@Override
		public void run() 
		{
			String name3 = "";

			//----------------------�� �� �ߺ� ���̵� �˻� ------------------------------------------------
			try 
			{
				name3 = in.readLine();
				black(name3, out);
			} 
			catch (Exception e)
			{
			}
			//------------------------------���� ����----------------------------
			String name = ""; // Ŭ���̾�Ʈ�� ���� ���� �̸��� ������ ����
			try 
			{
				name = in.readLine(); // Ŭ���̾�Ʈ���� ó������ ������ �޼�����
				out.println("��ü ä�ù濡 �����̽��ϴ�."); // Ŭ���̾�Ʈ�� ����� �̸��̴�
				out.println("/Mlist �� �Է��Ͻø� ��ɾ �� �� �ֽ��ϴ�.");
				// ����Ŭ���� �ҷ�����

				start1(name);
				sendAllMsg(name + "���� �����ϼ̽��ϴ�.", out);
				clientMap.put(name, out);
				System.out.println("���� ������ ����" + clientMap.size() + "�� �Դϴ�.");
				String s = "";
				while (in != null)
				{
					s = in.readLine();
					System.out.println(s);
					// ä�ó�����
					if (s.equals("q") || s.equals("Q"))
						break;
					else if (s.equals("/list")) 
					{
						Userlist(name);
					} // ��ɾ� ����
					else if (s.equals("/Mlist"))
					{
						Mlist(out);
					}
					// 1ȸ�� �ӼӸ�
					else if (s.length() >= 2 && s.substring(0, 2).equals("/w"))
					{
						rnltakf(name, s, out);
					}
					// �ӼӸ� ����
					else if (s.length() >= 3 && s.substring(0, 3).equals("/to")) 
					{
						rnltakf(name, s, out);
					}
					// ��Ģ�� ���
					else if (s.length() >= 3 && s.substring(0, 3).equals("/Br"))
					{
						Blackchat(s,out);
					}
					// �� �ʴ� ����
					else if (s.equals("/n")) 
					{
						chono(name);
					}
					// �� �ʴ� ����
					else if (s.equals("/y")) 
					{
						choyes(name,out);		
						continue;
					}

					// �� ���� �� ���� ��ɾ�
					else if ((s.length() >= 5 && s.substring(0, 5).equals("/Room")) ||
							(s.length() >= 7 && s.substring(0, 7).equals("/roomip"))) 
					{
						String e[] = s.split(" ");
						String it = e[1].toString();
						if(e[0].equals("/Room")) 
						{
							out.println("�ο����� �����ϼ���.");
							while (true) 
							{
								String human1 = "";
								human1 = in.readLine();
								int human2 = Integer.valueOf(human1);
								if (human2 > 4) 
								{
									out.println("�ִ��ο��� �ʰ��մϴ�.�ٽ� �Է����ּ���");
								} 
								else if(human2 <= 4)
								{
									Roomake(name, it, human2,out);
									break;
								}
							}	
						}
						else if(e[0].equals("/roomip")) 
						{
							Roomjoin(name,it,out);	
						}
						while (in != null) 
						{
								String s2 = it + " " + in.readLine();
								System.out.println(s2);
								String e2[] = s2.split(" ");
								String outname1 = "";
								String c = "";
								for (int i = 1; i < e2.length; i++)
								{
									c += e2[i] + " ";
								}
								if (e2[1].equals("/exit")) 
								{
									Room_user_de(name, it);
									sendRoomMsg(it, name + " ���� �����ϼ̽��ϴ�.", out);
									sendAllMsg(name + "���� ��ü�濡 �����̽��ϴ�.", out);
									break;
								}
								//������ ���
								else if (e2[1].equals("/roomdelete")) 
								{
									Roomdelete(name,it,out);
									continue;
								}
								//�� ����Ʈ��
								else if (e2[1].equals("/roomlist"))
								{
									Roomlist(name);
									continue;
								}
								else if (e2[1].equals("/list")) 
								{
									Userlist(name);
									continue;
								}
								else if (e2[1].equals("/Rlist")) 
								{
									RoomUserlist(name, it);
									continue;
								}
								else if (e2[1].equals("/out")) 
								{
									outname1 = e2[2].toString();
									RoomUserOut(name,it, outname1);
									continue;
								}
								else if (e2[1].equals("/All"))
								{	
									for (int i = 2; i < e2.length; i++)
									{
										c += e2[i] + " ";
									}
									hashsendAllMsg(name + "���� ���� ���� : " + c);
									continue;
								}
								else if (e2[1].equals("/to")) 
								{
									roomrnlt(name, s2, out);
									continue;
								}
								else if (e2[1].equals("/w")) 
								{
									roomrnlt(name, s2, out);
									continue;
								}
								else if(e2[1].equals("/cho"))
								{	
									RoomUserCho(name,it, e2[2]);
									continue;
								}
								else if (e2[1].equals("/Mlist")) 
								{
									Mlist(out);
									continue;
								}
								else if (e2[1].equals("/y")) 
								{
									Room_user_de(name, it);
									sendRoomMsg(it, name + " ���� �����ϼ̽��ϴ�.", out);
									sendAllMsg(name + "���� ��ü�濡 �����̽��ϴ�.", out);
									break;
								}
								if (e2[1].equals("/Br"))
								{
									roomBlackchat(s2, out);
									continue;
								}
								else 
								{
									sendRoomMsg(it, name + " ���� �޼��� :" + c, out);
									continue;
								}
							
						} // for �����°�
						continue;
					}																	
					// �� ���
					else if (s.length() >= 9 && s.substring(0, 9).equals("/roomlist")) 
					{
						Roomlist(name);
						continue;
					}
					else if (s.length() >= 4 && s.substring(0, 4).equals("/All"))
					{
						String e[] = s.split(" ");
						String it = "";
						for (int i = 1; i < e.length; i++) 
						{
							it += e[i] + " ";
						}
						hashsendAllMsg(name + "���� �������� : " + it);
						continue;
					}
					else 
					{
						sendAllMsg(name + "���� �޼��� : " + s, out);
					}
				}
			} // run�� try ������ �κ�
			catch (Exception e) 
			{
				System.out.println("����1" + e);
			} 
			finally 
			{
				close(name);
				// ���ܰ� �߻��Ҷ� ����, �ؽ��ʿ��� �ش� ������ ����.
				// ���� �����ϰų� ������ java.net.SocketException. ���ܹ߻�
				clientMap.remove(name);
				hashsendAllMsg(name + "���� �����ϼ̽��ϴ�.");
				System.out.println("���� ������ ����" + clientMap.size() + "�� �Դϴ�");
				try 
				{	
					in.close();
					out.close();
					socket.close();
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}

			}
		}
	}
}
