package com.stockinvest.data.board.info;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BoardManageInfo implements Serializable{
	private String seqNo;		//순번
	private String boardName;
	private String bbsCd;
	/**
	 * @return the seqNo
	 */
	public String getSeqNo() {
		return seqNo;
	}
	/**
	 * @param seqNo the seqNo to set
	 */
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
	/**
	 * @return the boardName
	 */
	public String getBoardName() {
		return boardName;
	}
	/**
	 * @param boardName the boardName to set
	 */
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	/**
	 * @return the bbsCd
	 */
	public String getBbsCd() {
		return bbsCd;
	}
	/**
	 * @param bbsCd the bbsCd to set
	 */
	public void setBbsCd(String bbsCd) {
		this.bbsCd = bbsCd;
	}

}
