package controller;

public class chat_vo {

	private int idchat_msg;
	private String msg;
	private int sender;
	private int receiver;
	private String status;
	
	public int getIdchat_msg() {
		return idchat_msg;
	}
	public void setIdchat_msg(int idchat_msg) {
		this.idchat_msg = idchat_msg;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getSender() {
		return sender;
	}
	public void setSender(int sender) {
		this.sender = sender;
	}
	public int getReceiver() {
		return receiver;
	}
	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
