package cn.blmdz.test.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int userId;
	private String userName;
	private String password;
	private int credits;
	private Date lastVisit;
	private String lastIp;
}
