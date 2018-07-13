package com.study.pattern04.factory_method2;


enum DBType{
	MySQL,
	Oracle,
	infomix
}
public abstract class DatabaseFactory {
	
	//Factory Method
	public abstract Database setDatabase();
}
