package com.study.pattern04.factory_method1.factory;

import com.study.pattern04.factory_method1.unit.Firebet;
import com.study.pattern04.factory_method1.unit.Marine;

public class PatternBGenerator extends UnitGenerator {

	@Override
	public void createUnits() {
		units.add(new Firebet());
		units.add(new Firebet());
		units.add(new Firebet());
		units.add(new Firebet());
		units.add(new Marine());
		units.add(new Marine());
		units.add(new Marine());
		units.add(new Marine());
	}

}
