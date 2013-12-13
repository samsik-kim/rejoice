package com.omp.dev.common.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.omp.commons.action.BaseAction;
import com.omp.commons.util.CommonUtil;
import com.omp.commons.util.GLogger;
import com.omp.dev.common.service.ZipCodeService;
import com.omp.dev.common.service.ZipCodeServiceImpl;

@SuppressWarnings("serial")
public class ZipCodeAction extends BaseAction{
    @SuppressWarnings("unused")
    private final static GLogger log = new GLogger(ZipCodeAction.class);
    
    private ZipCodeService service;
    private List<?> zipCodeList;
    private String keyword;
    
    public ZipCodeAction(){
        service = new ZipCodeServiceImpl();
    }
        
    public String popZipCodeList(){
        if(keyword != null && !"".equals(keyword)){
            keyword = CommonUtil.removeSpecial(keyword);
            zipCodeList = service.getZipCodeList(keyword);
        }
        return SUCCESS;
    }

    
    
    
    public List<?> getZipCodeList() {
        return zipCodeList;
    }

    public void setZipCodeList(List<?> zipCodeList) {
        this.zipCodeList = zipCodeList;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}