package com.study.pattern02.stretegy3;

public class DatabaseUse {
	
	//������
	private Database db;
	
	//�����ͺ��̽� ��ȯ �����ϰ�
	public void setDatbase(Database db) {
		this.db = db;
	}
	//��� ���
	public void connect() {
		if (db == null) {
			System.out.println("�����ͺ��̽��������ϼ���.");
		} else {
			//Delegate �޼��� ȣ�� : ��ü���� ������ ���̽��� ������ ���� ��� ��� ����
			db.connectDatabase();
		}
	}
	

}
