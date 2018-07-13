package com.study.pattern04.factory_method2;

public class DatabaseFactoryUse extends DatabaseFactory {

	// �̹��� ����� �����ͺ��̽��� Oracle�Դϴ�.
	// ������ ���̽� ����� ���⼭ Ÿ���� �����Ѵ�.
	public DBType dbType = DBType.MySQL;

	@Override
	public Database setDatabase() {

		// ȸ�� ������ ���� � �����ͺ��̽��� ��� ������� �𸥴�.
		// �׷��� ������ ������ �������ʰ� ����� ����Ѵ�.
		if (dbType == DBType.MySQL) {
			System.out.println("MySQL use...");
			return new MySQL();
		} else if (dbType == DBType.Oracle) {
			System.out.println("Oracle use...");
			return new Oracle();
		} else if (dbType == DBType.infomix) {
			System.out.println("infomix use...");
			return new infomix();
		}

		return null;
	}

}
