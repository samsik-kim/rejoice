package com.omp.admin.contents.adminrec.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.omp.admin.common.util.CommonUtil;
import com.omp.admin.contents.adminrec.model.AdminRecommend;
import com.omp.admin.contents.adminrec.model.AdminRecommendParam;
import com.omp.admin.contents.adminrec.model.DpCategoryList;
import com.omp.admin.contents.adminrec.service.AdminRecommendService;
import com.omp.admin.contents.adminrec.service.AdminRecommendServiceImpl;
import com.omp.commons.action.BaseAction;
import com.omp.commons.model.PagenateList;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.StringUtil;


@SuppressWarnings("serial")
public class AdminRecommendAction extends BaseAction {

	private static final GLogger log = new GLogger(AdminRecommendAction.class);
	
	private AdminRecommendService service;

	public AdminRecommendAction() {
		service = new AdminRecommendServiceImpl();
	}
	
	private AdminRecommendParam sub = null;
	
	private List<DpCategoryList> dpCategoryList = null;
	// AdminRecommend list
	private List<AdminRecommend> list = null;
	
	private AdminRecommend detail = null;
	
	// AdminRecommend list totalCount
	private long totalCount;


	public AdminRecommendParam getSub() {
		return sub;
	}


	public void setSub(AdminRecommendParam sub) {
		this.sub = sub;
	}

	public List<DpCategoryList> getDpCategoryList() {
		return dpCategoryList;
	}


	public void setDpCategoryList(List<DpCategoryList> dpCategoryList) {
		this.dpCategoryList = dpCategoryList;
	}


	public List<AdminRecommend> getList() {
		return list;
	}


	public void setList(List<AdminRecommend> list) {
		this.list = list;
	}


	public long getTotalCount() {
		return totalCount;
	}


	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}


	public AdminRecommend getDetail() {
		return detail;
	}


	public void setDetail(AdminRecommend detail) {
		this.detail = detail;
	}


	/**
	 * 메인 리스트 조회.
	 * @return
	 */
	public String selectAdminRecommendList() {
		
		if (sub == null) {
			if (logger.isInfoEnabled())
				logger.info("init serarch");
			sub = new AdminRecommendParam();
		}

		sub.setDpUpCatNo(StringUtils.defaultIfEmpty(sub.getDpUpCatNo(), "DP01"));	// category main
		sub.setScrGbn(StringUtils.defaultIfEmpty(sub.getScrGbn(), "DP005103")); // 추천
		sub.setDpCatNo(StringUtils.defaultIfEmpty(sub.getDpCatNo(), "DP01"));	
		
		dpCategoryList = service.getDpCategoryList(sub);
		
		this.setStep("SelectAdminRecommendList");
		list = service.selectAdminRecommendList(sub);
		
		totalCount = ((PagenateList) list).getTotalCount();
		
//		if(list.size() > 0)
//			totalCount = list.get(0).getTotalCount();
	
		log.debug("############# AdminRecommendAction.selectAdminRecommendList End  ##################");

		return SUCCESS;
	}
	
	public String selectAdminBeginnerList() {
		
		if (sub == null) {
			if (logger.isInfoEnabled())
				logger.info("init serarch");
			sub = new AdminRecommendParam();
		}

		sub.setDpUpCatNo(StringUtils.defaultIfEmpty(sub.getDpUpCatNo(), "DP01"));	// category main
		sub.setScrGbn(StringUtils.defaultIfEmpty(sub.getScrGbn(), "DP005105")); // 추천
		sub.setDpCatNo(StringUtils.defaultIfEmpty(sub.getDpCatNo(), "DP01"));	
		
		this.setStep("SelectDpCategoryList");
		dpCategoryList = service.getDpCategoryList(sub);
		this.setStep("SelectAdminBeginnerList");
		list = service.selectAdminRecommendList(sub);
		
		totalCount = ((PagenateList) list).getTotalCount();
		
//		if(list.size() > 0)
//			totalCount = list.get(0).getTotalCount();
	
		log.debug("############# AdminRecommendAction.selectAdminBeginnerList End  ##################");

		return SUCCESS;
	}
	
	public String popAdminProdList() {
		
		if (sub == null) {
			if (logger.isInfoEnabled())
				logger.info("init serarch");
			sub = new AdminRecommendParam();
		}
		
		sub.setDpUpCatNo(StringUtils.defaultIfEmpty(sub.getDpUpCatNo(), "DP01"));	// category main
		sub.setScrGbn(StringUtils.defaultIfEmpty(sub.getScrGbn(), "DP005103")); // 추천
		sub.setDpCatNo(StringUtils.defaultIfEmpty(sub.getDpCatNo(), "DP01"));	
		sub.setDpProdNm(StringUtils.defaultIfEmpty(sub.getDpProdNm(), ""));	
		sub.setAid(StringUtils.defaultIfEmpty(sub.getAid(), ""));
		this.setStep("PopAdminProdList");
		list = service.popAdminProdList(sub);
		
		totalCount = ((PagenateList) list).getTotalCount();
		
//		if(list.size() > 0)
//			totalCount = list.get(0).getTotalCount();
	
		log.debug("############# AdminRecommendAction.findAdminRecommends End  ##################");

		return SUCCESS;
	}
	
	public String popAdminProdDetail() {
		
		if (sub == null) {
			if (logger.isInfoEnabled())
				logger.info("init serarch");
			sub = new AdminRecommendParam();
		}
		
		HttpServletRequest request = this.req;
		
		String prodId = req.getParameter("prodId");
		this.setStep("PopAdminProdDetail");
		detail = service.popAdminProdDetail(prodId);
	
		log.debug("############# AdminRecommendAction.findAdminRecommends End  ##################");

		return SUCCESS;
	}
	
	public void insertAdminRecommendProd() {
		
		if (sub == null) {
			if (logger.isInfoEnabled())
				logger.info("init insert Prod");
			sub = new AdminRecommendParam();
		}
		
		String selectedProdId = StringUtil.nvlStr(this.req.getParameter("selectedProdId"));
		sub.setRegId(CommonUtil.getLoginId(this.req.getSession()));
		sub.setUpdId(CommonUtil.getLoginId(this.req.getSession()));
		this.setStep("InsertAdminRecommendProd");		
		service.insertAdminRecommendProd(selectedProdId, sub);
	
		log.debug("############# AdminRecommendAction.findAdminRecommends End  ##################");

		//return SUCCESS;
	}
	
	public String updateAdminRecommendList() {
		
		if (sub == null) {
			if (logger.isInfoEnabled())
				logger.info("init update AdminRecommendList");
			sub = new AdminRecommendParam();
		}
		
		String[] upProdId 	= this.req.getParameterValues("upProdId");
		String[] expoYn 	= this.req.getParameterValues("expoYn");
		String[] expoPrior 	= this.req.getParameterValues("expoPrior");

		sub.setUpdId(CommonUtil.getLoginId(this.req.getSession()));
		this.setStep("UpdateAdminRecommendList");		
		service.updateAdminRecommendList(sub, upProdId, expoYn, expoPrior);
	
		log.debug("############# AdminRecommendAction.findAdminRecommends End  ##################");

		return SUCCESS;
	}
	
	public String deleteAdminRecommendList() {
		
		if (sub == null) {
			if (logger.isInfoEnabled())
				logger.info("init delete AdminRecommendList");
			sub = new AdminRecommendParam();
		}
		
		String selectedProdId = StringUtil.nvlStr(this.req.getParameter("selectedProdId"));

		sub.setUpdId(CommonUtil.getLoginId(this.req.getSession()));
		this.setStep("DeleteAdminRecommendList");		
		service.deleteAdminRecommendList(selectedProdId, sub);
	
		log.debug("############# AdminRecommendAction.findAdminRecommends End  ##################");

		return SUCCESS;
	}
	
//	
//	/**
//	 * e-Book 메인 리스트 조회.
//	 * @return
//	 */
//	public String findEbookAdminRecommends() {
//		try {
////			if (topCodeModel == null) {
////				topCodeModel = new TopCodeModel();
////				HttpServletRequest request = getRequest();
////				topCodeModel.setTopCode(request.getParameter("topCode"));
////				topCodeModel.setLeftCode(request.getParameter("leftCode"));
////				String LimitUpCnt = getText("game.limit.regist.count");
////				adminRecommend.setLimitCnt(Integer.parseInt(LimitUpCnt));
////			}
//
//			if (adminRecommend == null) {
//				adminRecommend = new AdminRecommend();
//			}
//			
//			PageResultList pList = adminRecommendService.findEbookAdminRecommends(setCondition());
//			adminRecommendList = pList;
//
//			pageNavi.setTotalSize(pList.getTotalSize());
//
//		} catch (Exception e) {
//			log.error("[Exception] : " + e.getMessage());
//		}
//		return SUCCESS;
//	}
//	
//	/**
//	 * Comic 메인 리스트 조회.
//	 * @return
//	 */
//	public String findComicAdminRecommends() {
//		try {
////			if (topCodeModel == null) {
////				topCodeModel = new TopCodeModel();
////				HttpServletRequest request = getRequest();
////				topCodeModel.setTopCode(request.getParameter("topCode"));
////				topCodeModel.setLeftCode(request.getParameter("leftCode"));
////				String LimitUpCnt = getText("game.limit.regist.count");
////				adminRecommend.setLimitCnt(Integer.parseInt(LimitUpCnt));
////			}
//
//			if (adminRecommend == null) {
//				adminRecommend = new AdminRecommend();
//			}
//			
//			//SUB 화면 상단메뉴바
//			if(getViewType().equals("SUB")){
//				selectFirstCategoryList();
//			}
//			
//			// Comic 카테고리 리스트
//			HashMap<String,Object> hm  = new HashMap<String,Object>();
//		    hm.put("upDpCatNo", getSelCategory());
//		    setSearchList1(adminRecommendService.selectFirstCategoryList(hm));	
//
//			PageResultList pList = adminRecommendService.findComicAdminRecommends(setCondition());
//			adminRecommendList = pList;
//
//			pageNavi.setTotalSize(pList.getTotalSize());
//
//		} catch (Exception e) {
//			log.error("[Exception] : " + e.getMessage());
//		}
//		return SUCCESS;
//	}
//	
//	/*
//	 * 파라미터 세팅
//	 */
//	private HashMap<String, Object> setCondition() {
//		HashMap<String, Object> mapCondition = new HashMap<String, Object>();
//
//		// 페이징 셋팅
//		HttpServletRequest request = getRequest();
//	
//		pageNavi = makePageNavigator(request);
//		pageNavi.setRowsPerPage(50);
//		mapCondition.put("startRow", pageNavi.getStartRow());
//		mapCondition.put("endRow", pageNavi.getEndRow());
//
//		log.debug("action getSelCategory="+getSelCategory());
//		mapCondition.put("selCategory",getSelCategory());
//		mapCondition.put("viewType",viewType);
//		
//		/*
//		 * [이하][20100719][김슬기] 조회 조건 VM타입 추가
//		 */
//		if(sub != null)	mapCondition.put("vmType", ((vmType!= null && "ALL".equals(vmType.toUpperCase()))? "" :  vmType) );
//		/*
//		 * [이하][20100719][김슬기] 조회 조건 VM타입 추가
//		 */
//		
//		// e-Book 조회 조건 추가
//		if ("DP13".equals(getSelCategory())) {
//			mapCondition.put("ctgrSearch1",getCtgrSearch1());
//			mapCondition.put("ctgrSearch2",getCtgrSearch2());
//		}
//		// Comic 조회 조건 추가
//		if ("DP14".equals(getSelCategory())) {
//			mapCondition.put("ctgrSearch1",getCtgrSearch1());
//		}
//		
//		return mapCondition;
//	}
//
//	/*
//	 * 파라미터 세팅
//	 */
//	private HashMap<String, Object> setCondition_search() {
//		HashMap<String, Object> mapCondition = new HashMap<String, Object>();
//		// 페이징 셋팅
//		HttpServletRequest request = getRequest();
//		pageNavi = makePageNavigator(request);
//		
//		// e-Book, Comic 출력건수 조건 추가
//		if ("DP13".equals(getSelCategory()) || "DP14".equals(getSelCategory())) {
//			pageNavi.setRowsPerPage(Integer.parseInt(getSelPageCount()));
//		} else {
//			pageNavi.setRowsPerPage(50);
//		}
//		
//		// Comic 완결여부 조건 추가
//		if ("DP14".equals(getSelCategory())) {
//			mapCondition.put("etcYn", getEtcYn());
//		}
//		
//		mapCondition.put("startRow", pageNavi.getStartRow());
//		mapCondition.put("endRow", pageNavi.getEndRow());
//
//		mapCondition.put("startDate", getStartDate());
//		mapCondition.put("endDate", getEndDate());
//
//		mapCondition.put("ageRestictionsSel", getAgeRestictionsSel());
//		mapCondition.put("selCategory",getSelCategory());
//		mapCondition.put("viewType",getViewType());
//		mapCondition.put("ctgrSearch1",getCtgrSearch1());
//		mapCondition.put("ctgrSearch2",getCtgrSearch2());
//
//		if( getProdTypeSel() != null && getProdTypeSel().equals("search_nm") ){
//			mapCondition.put("prodNm",getProdSearch());
//		}else {
//			mapCondition.put("prodId",getProdSearch());
//		}
//		
//		mapCondition.put("hstType",getHstType());	// WAP 프리존 이력타입
//		mapCondition.put("chkHstType",getChkHstType());	// WAP 프리존 이력타입
//		mapCondition.put("freezoneHstNo",getFreezoneHstNo());	// WAP 프리존 이력타입
//		
//		mapCondition.put("searchCondition1",getSearchCondition1());	// 검색조건1
//		mapCondition.put("searchCondition2",getSearchCondition2());	// 검색조건2
//		mapCondition.put("searchCondition3",getSearchCondition3());	// 검색조건3
//		mapCondition.put("searchCondition4",getSearchCondition4());	// 검색조건4
//		mapCondition.put("searchCondition5",getSearchCondition5());	// 검색조건5	
//		
//		/*
//		 * [이하][20100719][김슬기] 조회 조건 VM타입 추가
//		 */
//		if(sub != null)	mapCondition.put("vmType", ((vmType!= null && "ALL".equals(vmType.toUpperCase()))? "" :  vmType) );
//		/*
//		 * [이하][20100719][김슬기] 조회 조건 VM타입 추가
//		 */
//		
//		return mapCondition;
//	}
//	
//	/**
//	 * 
//	 * @return
//	 */
//	private HashMap<String, Object> setCondition_search2() {
//		HashMap<String, Object> mapCondition = new HashMap<String, Object>();
//		// 페이징 셋팅
//		HttpServletRequest request = getRequest();
//		pageNavi = makePageNavigator(request);
//		pageNavi.setRowsPerPage(20); 
//		
//		mapCondition.put("hbnImgNo", getHbnImgNo());
//		
//		mapCondition.put("startRow", pageNavi.getStartRow());
//		mapCondition.put("endRow", pageNavi.getEndRow());
//		
//		mapCondition.put("startDate", getStartDate());
//		mapCondition.put("endDate", getEndDate());
//		
//		mapCondition.put("searchCondition1",getSearchCondition1());	// 검색조건1
//		mapCondition.put("searchCondition2",getSearchCondition2());	// 검색조건2
//		mapCondition.put("searchCondition3",getSearchCondition3());	// 검색조건3
//		mapCondition.put("searchCondition4",getSearchCondition4());	// 검색조건4
//		mapCondition.put("searchCondition5",getSearchCondition5());	// 검색조건5	
//		
//		return mapCondition;
//	}
//
//	/*
//	 * sub 상단메뉴
//	 */
//    private void selectFirstCategoryList()
//    {
//        HashMap<String,String> hm  = new HashMap<String,String>();
//
//        	hm.put("upDpCatNo", "999999");
//
//        	if(viewType.equals("SUB")){
//        		hm.put("con","");
//        	}else{
//        		hm.put("con","no");
//        	}
//
//        try
//        {
//            setFirstCategoryList(adminRecommendService.selectFirstCategoryList(hm));
//        }
//        catch(Exception e)
//        {
//            log.debug("ERROR MultiCategoryAction.selectFirstCategoryList\n"+e.getMessage());
//        }
//
//        hm.clear();
//        hm  = null;
//
//        if( getSelCategory()==null || "".equals(getSelCategory()) )
//        {
//            if(firstCategoryList!=null)
//            {
//                if(firstCategoryList.size()>0)
//                {
//                	AdminRecommend   dto = (AdminRecommend)firstCategoryList.get(0);
//                    setSelCategory(dto.getDpCatNo());
//
//                }
//            }
//        }
//   }
//
//    /*
//     * SUB 메뉴바.
//     */
//    private void selectSubCategoryList()
//    {
//        HashMap<String,String> hm  = new HashMap<String,String>();
//
//        	hm.put("upDpCatNo", selCategory );
//        	hm.put("con", "no" );
//
//        try
//        {
//            setSubCategoryList(adminRecommendService.selectFirstCategoryList(hm));
//        }
//        catch(Exception e)
//        {
//            log.debug("ERROR MultiCategoryAction.selectFirstCategoryList\n"+e.getMessage());
//        }
//
//        hm.clear();
//        hm  = null;
//    }
//
//
//
//	/*
//	 * 등록화면 리스트화면
//	 */
//	public String findWriteAdminRecommend(){
//		try{
//	        if(startDate == null || endDate == null || startDate.equals("") || endDate.equals("")){
//	            startDate = DateUtil.getFirstDayOfWeek(Constants.THIS_WEEK);
//	            endDate = DateUtil.getLastDayOfWeek(Constants.THIS_WEEK);
////	            adminRecommend.setStartDate(startDate);
////	            adminRecommend.setEndDate(endDate);
//	        }
//	        
//	        /*
//			 * [이하][20100719][김슬기] 조회 조건 VM타입 추가
//			 */
//			if(sub == null)	sub = new ContentsSub(); //초기화 
////			if(sub.getSearchNew().equals("Y")) 	sub.setPage(1); // 조회조건시 초기화
////			sub.setVmTypeList(CacheCommCode.getCommCode(Constants.CODE_VM_TYPE)); // VM타입 코드리스트
//			/*
//			 * [이상][20100719][김슬기] 조회 조건 VM타입 추가
//			 */
//	        
//			//상품검색 selectBox
//			selectFirstCategoryList();
//
//			if( viewType.equals("SUB")){
//				// SUB 화면
//				selectSubCategoryList();
//			}
//
//			PageResultList pList = adminRecommendService.findWriteAdminRecommend(setCondition_search());
//			adminRecommendList = pList;
//
//			pageNavi.setTotalSize(pList.getTotalSize());
//
//		}catch(Exception e){
//			log.error("[Exception] : " + e.getMessage());
//		}
//		return SUCCESS;
//	}
//	
//	/*
//	 * e-Book 등록화면 리스트화면
//	 */
//	public String findWriteEbookAdminRecommend(){
//		try{
//	        if(startDate == null || endDate == null || startDate.equals("") || endDate.equals("")){
//	            startDate = DateUtil.dashDate(DateUtil.getAddDay("-30"));
//	            endDate = DateUtil.dashDate(DateUtil.getSysDate());
////	            adminRecommend.setStartDate(startDate);
////	            adminRecommend.setEndDate(endDate);
//	        }
//  
//			//상품검색 selectBox
//			selectFirstCategoryList();
//			
//			// e-Book 2depth 카테고리 리스트
//			HashMap<String,Object> hm  = new HashMap<String,Object>();
//		    hm.put("upDpCatNo", getSelCategory());
//		    setSearchList1(adminRecommendService.selectFirstCategoryList(hm));	
//		    
//		    // e-Book 3depth 카테고리 리스트
//			if (!StringUtil.isEmpty(getCtgrSearch1())) {
//				hm.put("upDpCatNo", getCtgrSearch1());
//			    setSearchList2(adminRecommendService.selectFirstCategoryList(hm));	
//			}
//
//			// 처음 페이지 진입시 리스트 출력 안함
//			if (!"Y".equals(getFirstEnter())) {
//				PageResultList pList = adminRecommendService.findWriteEbookAdminRecommend(setCondition_search());
//				adminRecommendList = pList;
//	
//				pageNavi.setTotalSize(pList.getTotalSize());
//			
//				setFirstEnter("N");
//			}
//
//		}catch(Exception e){
//			log.error("[Exception] : " + e.getMessage());
//		}
//		return SUCCESS;
//	}
//	
//	/*
//	 * Comic 등록화면 리스트화면
//	 */	
//	public String findWriteComicAdminRecommend(){
//		try{
//	        if(startDate == null || endDate == null || startDate.equals("") || endDate.equals("")){
//	            startDate = DateUtil.dashDate(DateUtil.getAddDay("-30"));
//	            endDate = DateUtil.dashDate(DateUtil.getSysDate());
////	            adminRecommend.setStartDate(startDate);
////	            adminRecommend.setEndDate(endDate);
//	        }
//  
//			//상품검색 selectBox
//			selectFirstCategoryList();
//			
//			// Comic 카테고리 리스트
//			HashMap<String,Object> hm  = new HashMap<String,Object>();
//		    hm.put("upDpCatNo", getSelCategory());
//		    setSearchList1(adminRecommendService.selectFirstCategoryList(hm));	
//
//		    // 처음 페이지 진입시 리스트 출력 안함
//			if (!"Y".equals(getFirstEnter())) {
//				PageResultList pList = adminRecommendService.findWriteComicAdminRecommend(setCondition_search());
//				adminRecommendList = pList;
//	
//				pageNavi.setTotalSize(pList.getTotalSize());
//				
//				setFirstEnter("N");
//			}
//
//		}catch(Exception e){
//			log.error("[Exception] : " + e.getMessage());
//		}
//		return SUCCESS;
//	}	
//	
//	// e-Book 서브 카테고리 리스트 Ajax
//	public String ajaxEbookSubCategory() throws Throwable {
//		log.debug("ajaxEbookSubCategory() Start...");
//		
//		HttpServletResponse response = getResponse();
//		response.setContentType("text/plain; charset=UTF-8");
//		PrintWriter writer = null;
//
//		try { 
//			JSONObject jsonObject = new JSONObject();
//			
//			HashMap<String,Object> hm  = new HashMap<String,Object>();
//	        hm.put("upDpCatNo", getCtgrSearch1());
//	        setSubCategoryList(adminRecommendService.selectFirstCategoryList(hm));		
//		
//			String result = "<option value=''>전체</option>";
//			for (int i = 0; i < getSubCategoryList().size(); i++) {
//				result += "<option value='"+ ((AdminRecommend)getSubCategoryList().get(i)).getDpCatNo() + "'>";
//				result += ((AdminRecommend)getSubCategoryList().get(i)).getDpCatNm() + "</option>";
//			}
//			
//			jsonObject.put("result", result);
//
//			writer = response.getWriter();
//			writer.write(jsonObject.toString());
//            
//        } catch(Exception e){
//        	log.error(e.getMessage(), e);
//			throw e;
//        } finally {
//            if(writer != null) { writer.close(); }
//        }
//        
//        return SUCCESS;
//	}
//	
//	/*
//	 *  아작스 select box
//	 */
//	public String ajaxSubSelect()
//    {
//        try
//        {
//            HashMap<String, Object> hm = new HashMap<String, Object>();
//            HttpServletRequest request = getRequest();
//            String  upDpCatNo   = request.getParameter("getSelTopCatNo")==null?"":request.getParameter("getSelTopCatNo");
//            hm.put("upDpCatNo", upDpCatNo);
//            searchList2	= adminRecommendService.selectDispCtgrList(hm);
//
//        }
//        catch(InfraException ie)
//        {
//        	log.debug(ie);
//            throw ie;
//        }
//
//        return SUCCESS;
//    }
//
//	/*
//	 * 저장
//	 */
//	public String saveAdminRecommend() throws Exception {
//		try{
//			HashMap adminRecommendParams = new HashMap();
//
//			adminRecommendParams.put("selectedProdId",selectedProdId);
//			adminRecommendParams.put("viewType",viewType);
//			adminRecommendParams.put("loginId",getLoginId());
//			adminRecommendParams.put("selCategory",selCategory);
//			//log.debug("saveAdminRecommend  selCategory="+selCategory);
//			adminRecommendService.saveAdminRecommend(adminRecommendParams);
//		}
//		catch(Exception e){
//			log.error("[Exception] : " + e.getMessage());
////			throw new ServiceException("","",e);
//		}
//		return SUCCESS;
//	}
//	
//	/*
//	 * 삭제
//	 * 일괄삭제 추가 20110119 shinyi
//	 */
//	public String deleteAdminRecommend() {
//		try{
//			HashMap adminRecommendParams = new HashMap();
//			HttpServletRequest request = getRequest();
//			String flag = request.getParameter("flag") == null ? "" : request.getParameter("flag");
//			log.debug("===========flag:::multi이면 일괄삭제 실행====>>>"+flag);
//			if(flag.equals("multi")){
//				log.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$일괄삭제 실행$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
//				String[] chkbox = (String[]) (request.getParameterValues("chkbox") == null ? "" : request.getParameterValues("chkbox"));
//				for(int i=0;i<chkbox.length;i++){
//					log.debug("===========chkbox:::prodId|dpRank====>>>"+chkbox[i]);
//					StringTokenizer st = new StringTokenizer(chkbox[i], "|");
//					adminRecommendParams.put("prodId",st.nextToken());
//					adminRecommendParams.put("dpRank",st.nextToken());
//					adminRecommendParams.put("viewType",viewType);
//					adminRecommendParams.put("loginId",getLoginId());
//					adminRecommendParams.put("selCategory",selCategory);
//					adminRecommendService.deleteAdminRecommend(adminRecommendParams);
//				}
//			}else{
//				adminRecommendParams.put("prodId",selectedProdId);
//				adminRecommendParams.put("dpRank",selecteddpRank);
//				adminRecommendParams.put("viewType",viewType);
//				adminRecommendParams.put("loginId",getLoginId());
//				adminRecommendParams.put("selCategory",selCategory);
//				adminRecommendService.deleteAdminRecommend(adminRecommendParams);
//			}
//
//		} catch (ServiceException se) {
//			log.error("###### Service Exception ###");
//			throw se;
//
//		} catch (InfraException ie) {
//			log.error("#InfraException Exception ##");
//			throw ie;
//		}
//		return SUCCESS;
//	}
//
//	/*
//	 * 순위변경
//	 */
//	public String updateAdminRecommend() throws Exception {
//		try{
//			log.debug("updateAdminRecommend start......");
//			
//			String[] prodIds ;
//			String[] dpRanks ;
//			String dpRankChg ;
//			
//			HttpServletRequest request = getRequest();
//			prodIds = request.getParameterValues("prodId");			// 일괄적용할 PRODID 리스트
//			dpRanks = request.getParameterValues("beforeexpopriod");// 일괄적용할 PRODID의 현재DB의 Rank 리스트
//			
//			HashMap adminRecommendParams = new HashMap();
//			adminRecommendParams.put("updown"		, updown);
//			adminRecommendParams.put("viewType"		, viewType);
//			adminRecommendParams.put("selCategory"	,selCategory);
//			adminRecommendParams.put("loginId"		,getLoginId());
//			
//			for (int i = 0; i < prodIds.length; i++) {
//				dpRankChg = request.getParameter("dpRankChg"+i); //변경할 Rank값을 읽어 온다.
//				
//				if(!dpRanks[i].equals(dpRankChg)){
//					adminRecommendParams.put("prodId", 		prodIds[i]);
//					adminRecommendParams.put("dpRank", 		dpRanks[i]);
//					adminRecommendParams.put("dpRankChg", 	dpRankChg);
//					adminRecommendService.updateAdminRecommend(adminRecommendParams);
//				}
//			}
//			
//			log.debug("updateAdminRecommend end........");
//		}catch (InfraException ie) {
//			log.error("#InfraException Exception ##");
//			throw ie;
//		}
//		return SUCCESS;
//	}
//
//	/*
//	 * 상세팝업
//	 */
//	public String popupMultiView()
//	{
//		try
//		{
//			HashMap<String, Object> hm = new HashMap<String, Object>();
//
//			hm.put("prodId", getProdId());
//			hm.put("imgCls", getImgCls());
//			hm.put("dpCatNo", getDpCatNo());
//			setAdminRecommend(adminRecommendService.getProdDetailView(hm));
//			setEpsdList(adminRecommendService.epsdList(hm));
//		}
//		catch(InfraException ie)
//		{
//
//			throw ie;
//		}
//
//		return SUCCESS;
//	}
//	
//	/**
//	 * WAP프리존노출컨텐츠이력를 등록한다.
//	 * @since 2010.04.19
//	 * @author Sung-Min Choi
//	 * @throws Exception
//	 */
//	public String saveFreeZoneHst() throws Exception {
//		try{
//			HashMap adminRecommendParams = new HashMap();
//			
//			adminRecommendParams.put("selectedProdId",selectedProdId);
//			adminRecommendParams.put("viewType",viewType);
//			adminRecommendParams.put("loginId",getLoginId());
//			adminRecommendParams.put("selCategory",selCategory);
//			adminRecommendParams.put("hstType",hstType);
//			
//			//log.debug("saveAdminRecommend  selCategory="+selCategory);
//			adminRecommendService.saveFreeZoneHst(adminRecommendParams);
//		}
//		catch(Exception e){
//			log.error("[Exception] : " + e.getMessage());
////			throw new ServiceException("","",e);
//		}
//		return SUCCESS;
//	}
//	
//	/**
//	 * WAP프리존노출컨텐츠이력를 등록한다.
//	 * @since 2010.04.19
//	 * @author Sung-Min Choi
//	 * @throws Exception
//	 */
//	public String deleteFreezoneProd() throws Exception {
//		try{
//			HashMap hm = setCondition_search();
//			hm.put("delYn","Y");
//			hm.put("freezoneProdNo",searchCondition1);
//			
//			adminRecommendService.deleteFreezoneProd(hm);
//		}
//		catch(Exception e){
//			log.error("[Exception] : " + e.getMessage());
////			throw new ServiceException("","",e);
//		}
//		return SUCCESS;
//	}
//	
//	/**
//	 * WAP프리존 추천,최신,인기 상품 목록조회
//	 * @since 2010.04.19
//	 * @author Sung-Min Choi
//	 */
//	public String findFreeZoneList(){
//		try{
//			if(startDate == null || endDate == null || startDate.equals("") || endDate.equals("")){
//				startDate = DateUtil.getFirstDayOfWeek(Constants.THIS_WEEK);
//				endDate = DateUtil.getLastDayOfWeek(Constants.THIS_WEEK);
////				adminRecommend.setStartDate(startDate);
////				adminRecommend.setEndDate(endDate);
//			}
//			//상품검색 selectBox
//			selectFirstCategoryList();
//			
//			if( viewType.equals("SUB")){
//				// SUB 화면
//				selectSubCategoryList();
//			}
//			
//			PageResultList pList = adminRecommendService.findFreeZoneList(setCondition_search());
//			adminRecommendList = pList;
//			
//			pageNavi.setTotalSize(pList.getTotalSize());
//			
//		}catch(Exception e){
//			log.error("[Exception] : " + e.getMessage());
//		}
//		return SUCCESS;
//	}
//	
//	/**
//	 * WAP프리존 이력리스트 목록조회
//	 * @since 2010.04.22
//	 * @author Sung-Min Choi
//	 */
//	public String findFreeZoneHstList(){
//		try{
//			if(startDate == null || endDate == null || startDate.equals("") || endDate.equals("")){
//				startDate = DateUtil.getFirstDayOfWeek(Constants.THIS_WEEK);
//				endDate = DateUtil.getLastDayOfWeek(Constants.THIS_WEEK);
////				adminRecommend.setStartDate(startDate);
////				adminRecommend.setEndDate(endDate);
//			}
//
//			//상품검색 selectBox
//			selectFirstCategoryList();
//			
//			if( viewType.equals("SUB")){
//				// SUB 화면
//				selectSubCategoryList();
//			}
//			
//			PageResultList pList = adminRecommendService.findFreeZoneHstList(setCondition_search());
//			adminRecommendList = pList;
//			
//			pageNavi.setTotalSize(pList.getTotalSize());
//			
//		}catch(Exception e){
//			log.error("[Exception] : " + e.getMessage());
//		}
//		return SUCCESS;
//	}
//	
//	/**
//	 * WAP프리존 이력리스트 상세조회
//	 * @since 2010.04.22
//	 * @author Sung-Min Choi
//	 */
//	public String findFreeZoneHstDtlList(){
//		try{
//			/*
//			if(startDate == null || endDate == null || startDate.equals("") || endDate.equals("")){
//				startDate = DateUtil.getFirstDayOfWeek(Constants.THIS_WEEK);
//				endDate = DateUtil.getLastDayOfWeek(Constants.THIS_WEEK);
//				adminRecommend.setStartDate(startDate);
//				adminRecommend.setEndDate(endDate);
//			}
//			
//			//상품검색 selectBox
//			selectFirstCategoryList();
//			
//			if( viewType.equals("SUB")){
//				// SUB 화면
//				selectSubCategoryList();
//			}
//			*/
//			
//			PageResultList pList = adminRecommendService.findFreeZoneHstDtlList(setCondition_search());
//			adminRecommendList = pList;
//			
//			pageNavi.setTotalSize(pList.getTotalSize());
//			
//		}catch(Exception e){
//			log.error("[Exception] : " + e.getMessage());
//		}
//		return SUCCESS;
//	}
//	
//	/**
//	 * WAP프리존 배너이미지, 컨텐츠 상품 목록 조회
//	 * @since 2010.05.03
//	 * @author Sung-Min Choi
//	 */
//	public String findFreezoneListDtl(){
//		try{
//			/*
//			if(startDate == null || endDate == null || startDate.equals("") || endDate.equals("")){
//				startDate = DateUtil.getFirstDayOfWeek(Constants.THIS_WEEK);
//				endDate = DateUtil.getLastDayOfWeek(Constants.THIS_WEEK);
//				adminRecommend.setStartDate(startDate);
//				adminRecommend.setEndDate(endDate);
//			}
//			//상품검색 selectBox
//			selectFirstCategoryList();
//			
//			if( viewType.equals("SUB")){
//				// SUB 화면
//				selectSubCategoryList();
//			}
//			 */
//			
//			List pList = adminRecommendService.findFreezoneListDtl(setCondition_search());
//			adminRecommendList = pList;
//			
//		}catch(Exception e){
//			log.error("[Exception] : " + e.getMessage());
//		}
//		return SUCCESS;
//	}
//	
//	/**
//	 * WAP프리존 추천상품으로 서버반영함.
//	 * @since 2010.05.04
//	 * @author Sung-Min Choi
//	 * @throws Exception
//	 */
//	public String saveFreezoneServ() throws Exception {
//		try{
//			HashMap adminRecommendParams = setCondition_search();
//			
//			adminRecommendParams.put("loginId",getLoginId());
//			
//			//log.debug("saveAdminRecommend  selCategory="+selCategory);
//			adminRecommendService.saveFreezoneServ(adminRecommendParams);
//		}
//		catch(Exception e){
//			log.error("[Exception] : " + e.getMessage());
////			throw new ServiceException("","",e);
//		}
//		return SUCCESS;
//	}
//	
//	
//	////////////////////////////////////////////////////////////////////////////////////////////////////
//	// 네이트 홈 배너 
//	////////////////////////////////////////////////////////////////////////////////////////////////////
//	
//	/**
//	 * &를 제거함. ( 네이트에서 &amp;로 줘야하는데 &로 주는 문제해결 )
//	 * @param reader
//	 * @return
//	 */
//	public ByteArrayInputStream getEscapeXml(Reader reader) {
//
//		ByteArrayInputStream bis = null;
//
//		try {
//			int ch = 0;
//			StringBuffer tempSb = new StringBuffer();
//			while((ch=reader.read()) != -1) {
//				tempSb.append((char)ch);
//			}
//
//			String xmlText = StringUtil.replace(tempSb.toString(),"&caid","&amp;caid");
//			xmlText = StringUtil.replace(xmlText,"&appid","&amp;appid");
//			xmlText = StringUtil.replace(xmlText,"&conid","&amp;conid");
//			xmlText = StringUtil.replace(xmlText,"&XAREA","&amp;XAREA");
//
//			log.info("------------------------------------------------");
//			log.info("xmlText : " + xmlText);
//			log.info("------------------------------------------------");
//
//			bis = new ByteArrayInputStream(xmlText.getBytes("UTF-8"));
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		return bis;
//	}
//
//	/**
//	 * URL 전송후 결과코드값 반환
//	 * @param strUrl
//	 * @return
//	 */
//	public AdminRecommend getSendUrl(String strUrl) {
//		
//		SAXBuilder sb = new SAXBuilder(); 
//		Document doc = null; 
//		
//		URL url = null;
//		URLConnection conn = null;
//		
//		InputStream in = null;
//		Reader reader = null;
//		InputSource is = null;
//		
//		AdminRecommend admin = new AdminRecommend();
//
//		try{
//			url = new URL(strUrl);
//			conn = url.openConnection();
//			conn.setConnectTimeout(5000);
//			
//			in = conn.getInputStream();
//			reader = new InputStreamReader(in,"euc-kr");
//			
//			// &를 제거함. ( 네이트에서 &amp;로 줘야하는데 &로 주는 문제해결 )
//			in = getEscapeXml(reader);
//		}
//		catch(Exception e){ 
//			e.printStackTrace(); 
//		}
//		
//		try{
//			doc = sb.build(in);
//			
//			Element root = doc.getRootElement();
//			admin.setResultCd(root.getAttributeValue("CODE"));
//			admin.setResultCont(root.getChildText("DESC"));
//			
//			log.info("------------------------------------------------");
//			log.info("CODE : " + root.getAttributeValue("CODE"));
//			log.info("DESC : " + root.getChildText("DESC"));
//			log.info("------------------------------------------------");
//		}
//		catch(Exception e){ 
//			e.printStackTrace(); 
//		}
//		return admin;
//	}
}