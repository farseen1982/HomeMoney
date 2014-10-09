package com.farseen.homemoney.data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Journal {
	private long id;
	private float amount;
	private Date date;
	private String member;
	private String comment;
	private String type;

	public Journal(long id, float amount, Date date, String member,
			String comment, String type) {
		this.amount = amount;
		this.date = date;
		this.member = member;
		this.comment = comment;
		this.type = type;
		this.id = id;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public String getDateString() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		return format.format(date);
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
