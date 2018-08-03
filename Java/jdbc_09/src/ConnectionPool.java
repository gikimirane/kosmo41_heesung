
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public final class ConnectionPool {

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}

	// ������� ���� Ŀ�ؼ� ��, �ʱ� Ŀ�ؼ��� �����ϴ� ����
	private ArrayList<Connection> free;

	// ��� ���� Ŀ�ؼ��� �����ϴ� ����
	private ArrayList<Connection> used;
	// --------------------------------------------------------
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "scott";
	private String password = "tiger";
	private int	   maxCons = 3; // �ִ� Ŀ�ؼǼ�
	// ------------------------------------------------------

	// Ŀ�ؼ� Ǯ
	private static ConnectionPool cp;

	// ����
	public static ConnectionPool getInstance() {
		try {
			if (cp == null) {
				synchronized (ConnectionPool.class) {
					cp = new ConnectionPool();
				}
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return cp;
	}

	private ConnectionPool() throws SQLException {

		// �ʱ� Ŀ�ؼ� ������ ������ ArrayList�� ������ ��
		// �ֵ��� �ʱ� Ŀ�ؼ� ����ŭ ArrayList�� �����Ѵ�.
		free = new ArrayList<Connection>(maxCons);
		used = new ArrayList<Connection>(maxCons);

		// maxCons ����ŭ Connection�� ����(free)�Ѵ�.
		for (int i = 0; i < maxCons; i++) {
			addConnection();
		}
	}

	// free�� Ŀ�ؼ� ��ü�� �����Ѵ�.
	private void addConnection() throws SQLException {
		free.add(getNewConnection());
	}

	// ���ο� Ŀ�ؼ� ��ü�� �����Ѵ�.
	private Connection getNewConnection() throws SQLException {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ���ؼ� ������ ������ ���ڰ� �����Ѵ�.
		return con;
	}

	// free�� �ִ� Ŀ�ؼ��� used�� �ű�� �۾� => free-->used
	public synchronized Connection getConnection() throws SQLException {
		// free�� Connection�� ������
		// maxCons��ŭ Connection�� �� �����Ѵ�.
		if (free.isEmpty()) {
			System.out.println("aaaa");
		}
		Connection _con;
		_con = free.get(free.size() - 1);
		free.remove(_con);
		used.add(_con);
		return _con;
	}

	// used�� �ִ� Ŀ�ؼ��� free�� �ݳ��Ѵ�.
	public synchronized void releaseConnection(Connection _con) throws SQLException {
		boolean flag = false;
		if (used.contains(_con)) {
			used.remove(_con);
			flag = true;
		} else {
			throw new SQLException("ConnectionPool �� �����ʳ׿�!!");
		}
		try {
			if (flag) {
				free.add(_con);
			} else {
				_con.close();
			}
		} catch (SQLException e) {
			try {
				_con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	// ��� Connection �ڿ��� �ݳ��Ѵ�.
	public void closeAll() {
		// used�� �ִ� Ŀ�ؼ��� ��� �����Ѵ�.
		for (int i = 0; i < used.size(); i++) {
			Connection _con = (Connection) used.get(i);
			used.remove(i--);
			try {
				_con.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

		// free�� �ִ� Ŀ�ؼ��� ��� �����Ѵ�.
		for (int i = 0; i < free.size(); i++) {
			Connection _con = (Connection) free.get(i);
			free.remove(i--);
			try {
				_con.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}

	public int getMaxCons() {
		return maxCons;
	}
}