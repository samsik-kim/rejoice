package com.stockinvest.data.board.info;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BoardManageInfo implements Serializable{
	private String seqNo;		//순번
	private String boardNum;
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
	 * @return the boardNum
	 */
	public String getBoardNum() {
		return boardNum;
	}
	/**
	 * @param boardNum the boardNum to set
	 */
	public void setBoardNum(String boardNum) {
		this.boardNum = boardNum;
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
