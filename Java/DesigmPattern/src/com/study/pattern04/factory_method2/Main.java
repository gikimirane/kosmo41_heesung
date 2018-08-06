package com.study.pattern04.factory_method2;

public class Main {

	public static void main(String[] args) {
		DatabaseFactory factory = new DatabaseFactoryUse();
		
		//� ������ ���̽��� ������ ���⼭�� �𸥴�
		//���丮�޼��忡�� �����Ǵ� �����ͺ��̽��� �׳� ����Ѵ�.
		Database db = factory.setDatabase();
		
		//�����ͺ��̽� ����
		db.connectDatabase();
		
		//���ӵ� �����ͺ��̽��� �̿��� ����ó���� �Ѵ�
		db.insertData();
		db.selectData();
		
		
		

	}

}
