package org.foxconn.springbootStart.entity;

public class EUSER {
	String LOGONNAME;
	String PASSWORD;
	public String getLOGONNAME() {
		return LOGONNAME;
	}
	public void setLOGONNAME(String lOGONNAME) {
		LOGONNAME = lOGONNAME;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	@Override
	public String toString() {
		return "EUSER [LOGONNAME=" + LOGONNAME + ", PASSWORD=" + PASSWORD + "]";
	}
	
	
}
