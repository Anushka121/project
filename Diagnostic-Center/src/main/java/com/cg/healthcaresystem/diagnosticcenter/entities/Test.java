package com.cg.healthcaresystem.diagnosticcenter.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table(name = "tests")
public class Test {

	@Id
	private String testId;
	private String testName;

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null || !(object instanceof Test))
			return false;
		Test test = (Test) object;
		return this.testId.equals(test.getTestId());
	}

}
