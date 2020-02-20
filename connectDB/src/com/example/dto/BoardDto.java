package com.example.dto;

import java.sql.Timestamp;

public class BoardDto {
	int wId;
	String writer;
	String wTitle;
	String wContent;
	Timestamp wDate;
	public BoardDto() {
		
	}
	public BoardDto(int wId, String writer, String wTitle, String wContent, Timestamp wDate) {
		super();
		this.wId = wId;
		this.writer = writer;
		this.wTitle = wTitle;
		this.wContent = wContent;
		this.wDate = wDate;
	}
	public int getwId() {
		return wId;
	}
	public void setwId(int wId) {
		this.wId = wId;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getwTitle() {
		return wTitle;
	}
	public void setwTitle(String wTitle) {
		this.wTitle = wTitle;
	}
	public String getwContent() {
		return wContent;
	}
	public void setwContent(String wContent) {
		this.wContent = wContent;
	}
	public Timestamp getwDate() {
		return wDate;
	}
	public void setwDate(Timestamp wDate) {
		this.wDate = wDate;
	}
	
}
