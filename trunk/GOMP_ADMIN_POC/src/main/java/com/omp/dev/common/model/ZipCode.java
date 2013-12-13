package com.omp.dev.common.model;

public class ZipCode{
    private String zipCd; //우편번호
    private String zipSeq;   
    private String si;   
    private String gu;   
    private String dong;   
    private String ri;   
    private String dosu;  
    private String bungi;   
    private String builNm;  
    private String address;  
    private String chgDt;
    
    public String getZipCd() {
        return zipCd;
    }
    public void setZipCd( String zipCd ) {
        this.zipCd = zipCd;
    }
    
    public String getZipSeq() {
        return zipSeq;
    }
    public void setZipSeq( String zipSeq ) {
        this.zipSeq = zipSeq;
    }
    
    public String getSi() {
        return si;
    }
    public void setSi( String si ) {
        this.si = si;
    }
    
    public String getGu() {
        return gu;
    }
    public void setGu( String gu ) {
        this.gu = gu;
    }
    
    public String getDong() {
        return dong;
    }
    public void setDong( String dong ) {
        this.dong = dong;
    }
    
    public String getRi() {
        return ri;
    }
    public void setRi( String ri ) {
        this.ri = ri;
    }
    
    public String getDosu() {
        return dosu;
    }
    public void setDosu( String dosu ) {
        this.dosu = dosu;
    }
    
    public String getBungi() {
        return bungi;
    }
    public void setBungi( String bungi ) {
        this.bungi = bungi;
    }
    
    public String getBuilNm() {
        return builNm;
    }
    public void setBuilNm( String builNm ) {
        this.builNm = builNm;
    }
    
    public String getAddress() {
        return address;
    }
    public void setAddress( String address ) {
        this.address = address;
    }
    
    public String getChgDt() {
        return chgDt;
    }
    public void setChgDt( String chgDt ) {
        this.chgDt = chgDt;
    } 
}
