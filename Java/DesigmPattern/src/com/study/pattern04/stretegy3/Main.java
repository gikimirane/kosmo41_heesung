package com.study.pattern04.stretegy3;

public class Main {

	public static void main(String[] args) {
		//�����ͺ��̽��� ���������� �����Ͽ� ����Ѵ�.
		DatabaseUse myDB = new DatabaseUse();
		myDB.connect();
		
		myDB.setDatbase(new MySQL());
		myDB.connect();
		
		myDB.setDatbase(new Oracle());
		myDB.connect();
		
		myDB.setDatbase(new Informix());
		myDB.connect();

	}

}
