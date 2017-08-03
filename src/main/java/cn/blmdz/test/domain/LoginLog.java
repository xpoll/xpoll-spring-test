package cn.blmdz.test.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class LoginLog implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int loginLogId;
	private int userId;
	private String ip;
	private Date loginDate;

}
