package com.sixin.broad.domain;

import com.sixin.common.base.BaseEntity;

/**
	节目单和终端设备关联
 */
public class ProTerminal extends BaseEntity{

	private static final long serialVersionUID = 1L;

	private int programmeID;
	private String terminalID;

	public int getProgrammeID() {
		return programmeID;
	}

	public void setProgrammeID(int programmeID) {
		this.programmeID = programmeID;
	}

	public String getTerminalID() {
		return terminalID;
	}

	public void setTerminalID(String terminalID) {
		this.terminalID = terminalID;
	}

	@Override
	public String toString() {
		return "ProTerminal{" +
				"programmeID=" + programmeID +
				", terminalID='" + terminalID + '\'' +
				'}';
	}
}
