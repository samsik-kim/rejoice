package com.omp.dev.community.model;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;
import com.omp.commons.model.TotalCountCarriable;
import com.omp.commons.util.CommonUtil;

public class Notice implements Pagenateable, TotalCountCarriable   {
	
	private String rnum;
	private Long   totalCount;
    private String noticeId;        // Notice_ID
    private String ctgrCd;          // Category_Code
    private String title;           // Title
    private String dscr;            // Content
    private String hit;             // Hits
    private String mainOpenYn;      // Main_Open_Check
    private String mainOpenDttm;    // Main_Open_Date
    private String openYn;          // Open_Check
    private String delYn;           // Delete_Check
    private String insDttm;         // Insert_Date
    private String insBy;           // Insert_person
    private String updDttm;         // Update_Date
    private String updBy;           // Updater
    private String htmlEsc;			// Html Escape
    private String gid;				// gid
    private String fnm;				
    private String ofnm;				
    private String fsz;				
    private String furl;				
    private String ftype;
    private String newYn;
    private String downYn;
    private String down_ofnm;
    private String down_path;
    private String imgYn;
    private String img_path;
    
    private String searchType; 
    private String searchWord;
    
    private PagenateInfoModel    page        = new PagenateInfoModel(10);

    @Override
	public Long getTotalCount() {
		return this.totalCount;
	}

	@Override
	public void setTotalCount(Long totalCount) {
		this.totalCount	= totalCount;
	}
	
	@Override
    public PagenateInfoModel getPage() {
        return this.page;
    }
	public String getRnum() {
		return rnum;
	}

	public void setRnum(String rnum) {
		this.rnum = rnum;
	}
    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId( String noticeId )  {
        this.noticeId   = noticeId;
    }

    public String getCtgrCd()   {
        return ctgrCd;
    }

    public void setCtgrCd( String ctgrCd )  {
        this.ctgrCd = ctgrCd;
    }

    public String getTitle()    {
        return title;
    }

    public void setTitle( String title )    {
        this.title  = title;
    }

    public String getDscr() {
        return dscr;
    }

    public void setDscr( String dscr )  {
        this.dscr   = dscr;
    }

    public String getHit()  {
        return hit;
    }

    public void setHit( String hit )    {
        this.hit    = hit;
    }

    public String getMainOpenYn()   {
        return mainOpenYn;
    }

    public void setMainOpenYn( String mainOpenYn )  {
        this.mainOpenYn = mainOpenYn;
    }

    public String getMainOpenDttm() {
        return mainOpenDttm;
    }

    public void setMainOpenDttm( String mainOpenDttm )  {
        this.mainOpenDttm   = mainOpenDttm;
    }

    public String getOpenYn()   {
        return openYn;
    }

    public void setOpenYn( String openYn )  {
        this.openYn = openYn;
    }

    public String getDelYn()    {
        return delYn;
    }

    public void setDelYn( String delYn )    {
        this.delYn  = delYn;
    }

    public String getInsDttm()  {
        return insDttm;
    }

    public void setInsDttm( String insDttm )    {
        this.insDttm    = insDttm;
    }

    public String getInsBy()    {
        return insBy;
    }

    public void setInsBy( String insBy )    {
        this.insBy  = insBy;
    }

    public String getUpdDttm()  {
        return updDttm;
    }

    public void setUpdDttm( String updDttm )    {
        this.updDttm    = updDttm;
    }

    public String getUpdBy()    {
        return updBy;
    }

	public void setUpdBy( String updBy )    {
        this.updBy  = updBy;
    }

	  public String getFnm() {
		return fnm;
	}

	public void setFnm(String fnm) {
		this.fnm = fnm;
	}

	public String getOfnm() {
		return ofnm;
	}

	public void setOfnm(String ofnm) {
		this.ofnm = ofnm;
	}

	public String getFsz() {
		return fsz;
	}

	public void setFsz(String fsz) {
		this.fsz = fsz;
	}

	public String getFurl() {
		return furl;
	}

	public void setFurl(String furl) {
		this.furl = furl;
	}

	public String getFtype() {
		return ftype;
	}

	public void setFtype(String ftype) {
		this.ftype = ftype;
	}

	/**
	 * @return the newYn
	 */
	public String getNewYn() {
		return newYn;
	}

	/**
	 * @param newYn the newYn to set
	 */
	public void setNewYn(String newYn) {
		this.newYn = newYn;
	}

	/**
	 * @return the downYn
	 */
	public String getDownYn() {
		return downYn;
	}

	/**
	 * @param downYn the downYn to set
	 */
	public void setDownYn(String downYn) {
		this.downYn = downYn;
	}

	/**
	 * @return the down_ofnm
	 */
	public String getDown_ofnm() {
		return down_ofnm;
	}

	/**
	 * @param down_ofnm the down_ofnm to set
	 */
	public void setDown_ofnm(String down_ofnm) {
		this.down_ofnm = down_ofnm;
	}

	/**
	 * @return the down_path
	 */
	public String getDown_path() {
		return down_path;
	}

	/**
	 * @param down_path the down_path to set
	 */
	public void setDown_path(String down_path) {
		this.down_path = down_path;
	}

	/**
	 * @return the imgYn
	 */
	public String getImgYn() {
		return imgYn;
	}

	/**
	 * @param imgYn the imgYn to set
	 */
	public void setImgYn(String imgYn) {
		this.imgYn = imgYn;
	}

	/**
	 * @return the img_path
	 */
	public String getImg_path() {
		return img_path;
	}

	/**
	 * @param img_path the img_path to set
	 */
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}

	/**
	 * @return the searchType
	 */
	public String getSearchType() {
		return searchType;
	}

	/**
	 * @param searchType the searchType to set
	 */
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	/**
	 * @return the searchWord
	 */
	public String getSearchWord() {
		return searchWord;
	}

	/**
	 * @param searchWord the searchWord to set
	 */
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	
	
	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	/************************************************************
	 * Html Escape
	 */
	/**
	 * @param htmlEsc the htmlEsc to set
	 */
	public void setHtmlEsc(String htmlEsc) {
		this.htmlEsc = htmlEsc;
	}
	
	public String getHtmlEsc(){
		String str = "";
  	  
  	  try{
  		  str = StringUtils.abbreviate(CommonUtil.replaceAllTagsExcept(this.dscr, ""), 250);
  	  }catch(Exception ex){
  	   str = this.dscr;
  	  }
		return str;
	}
}