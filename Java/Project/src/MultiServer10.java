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
	// 전챗방
	List<String> userAll = new ArrayList<>();
	// 룸
	List<String> allroomlist = new ArrayList<>();
	// 금칙어
	List<String> blocktalk = new ArrayList<>();
	//Iterator<String> it = clientMap.keySet().iterator();
	

	public MultiServer10() 
	{
		// 칼라이언트의 출력스트림을 저장할 해쉬맵 생성. //생성자
		clientMap = new HashMap<String, PrintWriter>();
		// 해쉬맵 동기화 설정
		Collections.synchronizedMap(clientMap);
	}

	public void init() 
	{
		try 
		{	
			con = ConnectionPool.getConnection();
			serverSocket = new ServerSocket(9999);// 9999포트로 서버소켓 객체생성
			System.out.println("서버가 시작되었습니다.");
			stmt = con.createStatement();
			
			while (true) 
			{
				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress() + ":" + socket.getPort());
				Thread mst = new MultiServerT(socket); // 쓰레드 생성
				mst.start(); // 쓰레드 시동
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

	// 접속된 모든 클라이언트들에게 메세지 전달
	public void hashsendAllMsg(String msg) 
	{
		// 출력스트림을 순차적으로 얻어와서 해당메세지 출력.
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
				System.out.println("예외" + e);
			}
		}

	}

	// 명령어 보기
	public void Mlist(PrintWriter out)
	{
		try 
		{
			out.println("-----------------------------------------");
			out.println("대소문자 잘 구분해 주세요^^ ");
			out.println("-----------공용------------");
			out.println("/q or /Q   : 채팅 끝내기");
			out.println("/w 받는사람 : 귓속말 보내기 ");
			out.println("/to 받는사람 : 귓속말 고정 ");
			out.println("/list  :  전쳇방 인원 보기 ");
			out.println("/roomlist   : 방 목록 보기 ");
			out.println("/Room   : 방 만들기 ");
			out.println("/roomip   :  방 입장하기 ");
			out.println("/Br 금칙어  :  금칙어 추가하기 ");
			out.println("/All 공지할말  :   전체에게 공지사항 보내기 ");
			out.println("---------방 안에서만 --------");
			out.println("/Rlist  : 방안에서 방안 인원보기 ");
			out.println("/exit   : 방나가기 ");
			out.println("----------방장 권한---------- ");
			out.println("/out  강퇴시킬이름    : 방에서 강퇴. ");
			out.println("/roomdelete     : 방 폭파 / 방없애기 / 다나가짐 ");
			out.println("/cho 초대할사람    :   자기 방에 사람을 초대함");
			out.println("------------------------------------------- ");
		} 
		catch (Exception e)
		{
			System.out.println("예외" + e);
		}

	}

	//------------------------------main------------------------
	public static void main(String[] args) 
	{
		MultiServer10 ms = new MultiServer10();
		ms.init();

	}
	//------------------------------main------------------------
	
	// 전체방에서 보내는 메세지
	public void sendAllMsg(String msg, PrintWriter out) 
	{
		// 출력스트림을 순차적으로 얻어와서 해당메세지 출력.
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
					out.println("금칙어입니다");
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
	// 방안에서 보내는 메세지
	public void sendRoomMsg(String room, String msg, PrintWriter out) 
	{
		// 출력스트림을 순차적으로 얻어와서 해당메세지 출력.
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
					out.println("금칙어입니다");
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
	// 시작시 전챗방 인원
	public void start1(String name)
	{
		try 
		{
			rs = stmt.executeQuery("INSERT INTO userlist " + "VALUES('" + name + "')");
		} 
		catch (SQLException sqle) 
		{
			// System.out.println("중복된 아이디입니다.다시 실행해주세요.");
		} 
	}
	// 챗팅 끌시
	private void close(String name) 
	{
		try 
		{
			rs = stmt.executeQuery("delete userlist where id='" + name + "'");
		}
		catch (SQLException sqle) 
		{
			// System.out.println("중복된 아이디입니다.다시 실행해주세요.");
		}
	}
	// 금칙어등록
	public void Blackchat(String blackplus,PrintWriter out) 
	{
		try 
		{	
			String black[] = blackplus.split(" ");
			rs = stmt.executeQuery("INSERT INTO chat " + "VALUES('" + black[1] + "')");
			out.println("금칙어 추가 되었습니다.");
		} 
		catch (SQLException sqle) 
		{
			// System.out.println("중복된 아이디입니다.다시 실행해주세요.");
		} 	
	}
	//방 금칙어 등록
	public void roomBlackchat(String blackplus,PrintWriter out) 
	{
		try 
		{	
			String black[] = blackplus.split(" ");
			rs = stmt.executeQuery("INSERT INTO chat " + "VALUES('" + black[2] + "')");
			out.println("금칙어 추가 되었습니다.");
		} 
		catch (SQLException sqle) 
		{
			// System.out.println("중복된 아이디입니다.다시 실행해주세요.");
		} 	
	}
	// 생성된 방에 입장
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
				out.println("인원이 다 찼습니다.");
				return;
			}
			else 
			{
				sendAllMsg(name + "님이 " + roomname + " 방에 입장하셨습니다.", out);
				sendRoomMsg(roomname, name + "님이입장하셨습니다.", out);
				rs = stmt.executeQuery("INSERT INTO " + roomname + " VALUES('" + name + "','null',0)");
				rs = stmt.executeQuery("DELETE userlist" + " where id='" + name + "'");
				rs = stmt.executeQuery("update " + roomname + " set human = human-1");				
				System.out.println("while 전");		
			}			
		}
		catch (SQLException sqle)
		{
			// System.out.println("중복된 아이디입니다.다시 실행해주세요.");
		}
		
	}
	// 방퇴장시 방에 유저 삭제/전챗방으로 복귀
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
			// System.out.println("중복된 아이디입니다.다시 실행해주세요.");
		} 
	}
	// 방생성
	public void Roomake(String name, String roomname, int human,PrintWriter out)
	{
		try 
		{
			out.println(roomname + " 방에 입장하셨습니다.");			
			rs = stmt.executeQuery("create table " + roomname + "( id varchar2(20),super varchar(20), human number(20))");
			rs = stmt.executeQuery("INSERT INTO " + roomname + " VALUES('" + name + "','super','" + human + "')");
			rs = stmt.executeQuery("INSERT INTO roomlist" + " VALUES('" + roomname + "')");
			rs = stmt.executeQuery("update " + roomname + " set human = human-1");
			rs = stmt.executeQuery("DELETE userlist where id='"+ name + "'");		
		}
		catch (SQLException sqle) 
		{
			// System.out.println("중복된 아이디입니다.다시 실행해주세요.");
		} 
	}
	// 생성된 방 목록들
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
			// System.out.println("중복된 아이디입니다.다시 실행해주세요.");
		} 
	}
	// 전쳇방에있는 인원보기.
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
			// System.out.println("중복된 아이디입니다.다시 실행해주세요.");
		} 
	}
	// 방에 있는 유저 보기
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
			// System.out.println("중복된 아이디입니다.다시 실행해주세요.");
		}
	}
	// 방폭파
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
				sendRoomMsg(roomname, "방이 폭파되었습니다 나가기 해주세요. [/exit]", out);
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
						it_out.println("방장만 사용할 수 있는 기능입니다.");
					}
				}
			}
		} 
		catch (SQLException sqle) 
		{
			// System.out.println("중복된 아이디입니다.다시 실행해주세요.");
		} 
	}
	// 방에서 강퇴
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
						it_out.println("방장에 의해 강퇴당했습니다.확인[/y]을 눌러주세요");
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
						it_out.println("방장만 사용할 수 있는 기능입니다.");
					}
				}
			}
		} 
		catch (SQLException sqle) 
		{
			// System.out.println("중복된 아이디입니다.다시 실행해주세요.");
		} 
	}
	// 방으로 초대하는 메세지 보내기.
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
					it_out.println(name + "님이 " + roomname + " 방에 초대하셨습니다");
					it_out.println("수락하시겠습니까?  수락 /y  거절 /n");
					break;
				}
			}
		}
		catch (SQLException sqle)
		{
			// System.out.println("중복된 아이디입니다.다시 실행해주세요.");
		} 
	}
	// 초대 거절하기.
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
					it_out.println(name + "님이 거절하셨습니다.");
					break;
				}
			}
			rs = stmt.executeQuery("delete cholist where choid = '" + name + "'");
		}
		catch (SQLException sqle) 
		{
			// System.out.println("중복된 아이디입니다.다시 실행해주세요.");
		} 
	}
	// 초대 수락하기
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
					it_out.println(name + "님이 수락하셨습니다.");
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
				out.println("인원이 다 찼습니다.");
				PrintWriter it_out = (PrintWriter) clientMap.get(supid);
				it_out.println("초대하신 " + name + " 님이 인원이 다 차서 못들어오셨습니다.");
			}
			else if(f!=0) 
			{
			Roomjoin(name, choroom,out);
			sendAllMsg(name + "님이 " + choroom + " 방에 입장하셨습니다.", out);
			sendRoomMsg(choroom, name + "님이입장하셨습니다.", out);
			rs = stmt.executeQuery("delete cholist where choid = '" + name + "'");
			}
		} 
		catch (SQLException sqle)
		{
			// System.out.println("중복된 아이디입니다.다시 실행해주세요.");
		} 
		
	}
	//귓속말
	public void rnltakf(String name,String msg,PrintWriter out) 
	{
		String talk[] = msg.split(" ");
		PrintWriter it_out = (PrintWriter) clientMap.get(talk[1]);
		it_out.print(name + "님의 귓속말 :");
		String rnlt = "";
		for (int i = 2; i < talk.length; i++) 
		{
			it_out.print(talk[i] + " ");
			rnlt += talk[i];
		}
		out.print(talk[1] + "님에게 : " + rnlt + " ");
		it_out.println();
		out.println();
		
	}
	//룸내에 귓속말
	public void roomrnlt(String name,String msg,PrintWriter out) 
	{
		String talk[] = msg.split(" ");
		PrintWriter it_out = (PrintWriter) clientMap.get(talk[2]);
		it_out.print(name + "님의 귓속말 :");
		for (int i = 3; i < talk.length; i++) 
		{
			it_out.print(talk[i] + " ");
			out.print(talk[2] + "님에게 : " + talk[i] + " ");
		}
		it_out.println();
		out.println();
		
	}
	//블랙 및 중복 확인
	public void black(String name,PrintWriter out) throws SQLException 
	{
			int count = 0;
			rs = stmt.executeQuery("select*from userlist");
			while (rs.next()) 
			{
				if(name.equals(rs.getString(1))) 
				{
					out.println("중복 아이디 입니다.다른 아이디를 입력하세요");
					count = 1;
					break;
				}
				
			}
			rs = stmt.executeQuery("select*from blacklist");
			while (rs.next()) 
			{
				if (name.equals(rs.getString(1))) 
				{
					out.println("블랙리스트에 추가된 아이디 입니다.다른 아이디를 입력하세요");
					count = 1;
					break;
				} 
			}
			if(count==0) 
			{
				out.println("사용할 수 있는 아이디입니다.");
				out.println("다시 입력후 서버에 접속하세요");
				System.out.println("gg");
			}
		}
	 
	
		
	////////////////////////////// 내부클래스///////////////////////////////
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
				System.out.println("예외" + e);
			}
		}

		@Override
		public void run() 
		{
			String name3 = "";

			//----------------------블랙 및 중복 아이디 검사 ------------------------------------------------
			try 
			{
				name3 = in.readLine();
				black(name3, out);
			} 
			catch (Exception e)
			{
			}
			//------------------------------서버 시작----------------------------
			String name = ""; // 클라이언트로 부터 받은 이름을 저장할 변수
			try 
			{
				name = in.readLine(); // 클라이언트에서 처음으로 보내는 메세지는
				out.println("전체 채팅방에 들어오셨습니다."); // 클라이언트가 사용할 이름이다
				out.println("/Mlist 를 입력하시면 명령어를 볼 수 있습니다.");
				// 연동클래스 불러오기

				start1(name);
				sendAllMsg(name + "님이 입장하셨습니다.", out);
				clientMap.put(name, out);
				System.out.println("현재 접속자 수는" + clientMap.size() + "명 입니다.");
				String s = "";
				while (in != null)
				{
					s = in.readLine();
					System.out.println(s);
					// 채팅나가기
					if (s.equals("q") || s.equals("Q"))
						break;
					else if (s.equals("/list")) 
					{
						Userlist(name);
					} // 명령어 보기
					else if (s.equals("/Mlist"))
					{
						Mlist(out);
					}
					// 1회용 귓속말
					else if (s.length() >= 2 && s.substring(0, 2).equals("/w"))
					{
						rnltakf(name, s, out);
					}
					// 귓속말 고정
					else if (s.length() >= 3 && s.substring(0, 3).equals("/to")) 
					{
						rnltakf(name, s, out);
					}
					// 금칙어 등록
					else if (s.length() >= 3 && s.substring(0, 3).equals("/Br"))
					{
						Blackchat(s,out);
					}
					// 방 초대 거절
					else if (s.equals("/n")) 
					{
						chono(name);
					}
					// 방 초대 수락
					else if (s.equals("/y")) 
					{
						choyes(name,out);		
						continue;
					}

					// 방 생성 및 입장 명령어
					else if ((s.length() >= 5 && s.substring(0, 5).equals("/Room")) ||
							(s.length() >= 7 && s.substring(0, 7).equals("/roomip"))) 
					{
						String e[] = s.split(" ");
						String it = e[1].toString();
						if(e[0].equals("/Room")) 
						{
							out.println("인원수를 설정하세요.");
							while (true) 
							{
								String human1 = "";
								human1 = in.readLine();
								int human2 = Integer.valueOf(human1);
								if (human2 > 4) 
								{
									out.println("최대인원을 초과합니다.다시 입력해주세요");
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
									sendRoomMsg(it, name + " 님이 퇴장하셨습니다.", out);
									sendAllMsg(name + "님이 전체방에 들어오셨습니다.", out);
									break;
								}
								//방폭파 기능
								else if (e2[1].equals("/roomdelete")) 
								{
									Roomdelete(name,it,out);
									continue;
								}
								//방 리스트들
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
									hashsendAllMsg(name + "님의 공지 사항 : " + c);
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
									sendRoomMsg(it, name + " 님이 퇴장하셨습니다.", out);
									sendAllMsg(name + "님이 전체방에 들어오셨습니다.", out);
									break;
								}
								if (e2[1].equals("/Br"))
								{
									roomBlackchat(s2, out);
									continue;
								}
								else 
								{
									sendRoomMsg(it, name + " 님의 메세지 :" + c, out);
									continue;
								}
							
						} // for 끝나는곳
						continue;
					}																	
					// 방 목록
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
						hashsendAllMsg(name + "님의 공지사항 : " + it);
						continue;
					}
					else 
					{
						sendAllMsg(name + "님의 메세지 : " + s, out);
					}
				}
			} // run의 try 끝나는 부분
			catch (Exception e) 
			{
				System.out.println("예외1" + e);
			} 
			finally 
			{
				close(name);
				// 예외가 발생할때 퇴장, 해쉬맵에서 해당 데이터 제거.
				// 보통 종료하거나 나가면 java.net.SocketException. 예외발생
				clientMap.remove(name);
				hashsendAllMsg(name + "님이 퇴장하셨습니다.");
				System.out.println("현재 접속자 수는" + clientMap.size() + "명 입니다");
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
