package com.omp.commons.commcode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import pat.neocore.util.StringPattern;

import com.omp.commons.commcode.model.CommCode;
import com.omp.commons.util.FreezableList;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.config.ConfigProperties;

/**
 * 공통코드 Cache 클래스 <br>
 *
 * @author pat
 */

public class CacheCommCode {
	

	private static final GLogger	logger	= new GLogger(CacheCommCode.class);
	
	private static class CachedCode {
		Map<String, CommCode>					fullCodeMap;
		Map<String, Map<String, CommCode>>		groupCodeMap;
		Map<String, FreezableList<CommCode>>	groupCodeList;
		
		private CachedCode() {
			this.fullCodeMap	= new HashMap<String, CommCode>();
			this.groupCodeMap	= new HashMap<String, Map<String,CommCode>>();
			this.groupCodeList	= new HashMap<String, FreezableList<CommCode>>();
		}
		
		public void add(CommCode cc) {
			String						groupId;
			Map<String, CommCode>		gm;
			
			this.fullCodeMap.put(cc.getDtlFullCd(), cc);
			groupId	= cc.getGrpCd();
			gm		= this.groupCodeMap.get(groupId);
			if (gm == null) {
				gm	= new HashMap<String, CommCode>();
				this.groupCodeMap.put(groupId, gm);
			}
			gm.put(cc.getDtlCd(), cc);
			if ("Y".equalsIgnoreCase(cc.getUseYN())) {
				FreezableList<CommCode>	gl;
				
				gl	= this.groupCodeList.get(groupId);
				if (gl == null) {
					gl	= new FreezableList<CommCode>();
					this.groupCodeList.put(groupId, gl);
				}
				gl.add(cc);
			}
		}
		
		public void freeze() {
			for (FreezableList<CommCode> codeList : this.groupCodeList.values()) {
				codeList.freeze();
			}
		}
	}
	
	private static CachedCode	cachedCode; 
	
//	public static CacheCommCode loadInstance(){
//		log.debug( "CacheCommCode init...");
//
//		if( instance==null ) instance = new CacheCommCode();
//		return instance;
//	}
	
	public static synchronized void initialize(ConfigProperties conf) {
		String		driver;
		String		url;
		String		user;
		String		passwd;
		String		query;
		Connection	con;
		CachedCode	wccc;
		
		driver	= conf.getString("omp.common.codecache.db.driver");
		if (StringUtils.isEmpty(driver)) {
			throw new RuntimeException("config 'omp.common.codecache.db.driver' required.");
		}
		try {
			Class.forName(driver).newInstance();
		} catch (Exception e) {
			throw new RuntimeException("config 'omp.common.codecache.db.driver' is invalid JDBC Driver class name.", e);
		}
		url		= conf.getString("omp.common.codecache.db.url");
		if (StringUtils.isEmpty(url)) {
			throw new RuntimeException("config 'omp.common.codecache.db.url' required.");
		}
		user	= conf.getString("omp.common.codecache.db.user");
		if (StringUtils.isEmpty(user)) {
			throw new RuntimeException("config 'omp.common.codecache.db.user' required.");
		}
		passwd	= conf.getString("omp.common.codecache.db.passwd");
		if (StringUtils.isEmpty(passwd)) {
			throw new RuntimeException("config 'omp.common.codecache.db.passwd' required.");
		}
		query	= conf.getString("omp.common.codecache.db.query");
		if (StringUtils.isEmpty(query)) {
//			query = "select * from (\n"
//				+ "select grp_cd, dtl_cd, dtl_full_cd, cd_nm, add_field1, add_field2, use_yn, description, display_order, tags, reg_dttm, upd_dttm from tbl_comm_cd\n"
//				+ "union all\n"
//				+ "select 'BKCD' as grp_cd, BANK_CD || certi_cd as dtl_cd, BANK_CD || certi_cd as dtl_full_cd, BANK_NM as cd_nm, BANK_BRIEF_NM as add_field1, CERTI_CD as add_field2, use_yn, '' as description, to_char(rownum) as display_order, '' as tags, reg_dttm, upd_dttm from tbl_comm_bank_cd)\n"
//				+ "order by grp_cd asc, to_number(display_order) asc, dtl_cd asc";
			query = "select grp_cd, dtl_cd, dtl_full_cd, cd_nm, add_field1, add_field2, use_yn, description, display_order, tags, reg_dttm, upd_dttm from tbl_comm_cd"
				+ " order by grp_cd asc, to_number(display_order) asc, dtl_cd asc";
		}
		try { 
			con	= DriverManager.getConnection(url, user, passwd);
			try {
				Statement	stmt;
				
				wccc	= new CachedCode();
				stmt	= con.createStatement();
				try {
					ResultSet	rs;
				
					if (logger.isDebugEnabled()) {
						logger.debug("query for cache code {0}", new Object[] {query});
					}
					rs	= stmt.executeQuery(query);
					try {
						while (rs.next()) {
							CommCode	cc = new CommCode();
							
							cc.setGrpCd(rs.getString("GRP_CD"));
							cc.setDtlCd(rs.getString("DTL_CD"));
							cc.setDtlFullCd(rs.getString("DTL_FULL_CD"));
							cc.setCdNm(rs.getString("CD_NM"));
							cc.setAddField1(rs.getString("ADD_FIELD1"));
							cc.setAddField2(rs.getString("ADD_FIELD2"));
							cc.setUseYN(rs.getString("USE_YN"));
							cc.setDescription(rs.getString("DESCRIPTION"));
							cc.setDisplayOrder(rs.getString("DISPLAY_ORDER"));
							cc.setTags(rs.getString("TAGS"));
							wccc.add(cc);
						}
					} finally {
						rs.close();
					}
				} finally {
					stmt.close();
				}
			} finally {
				con.close();
			}
			wccc.freeze();
			cachedCode	= wccc;
			if (logger.isDebugEnabled()) {
				logger.debug("cache code loaded.");
			}
		} catch (SQLException e) {
			throw new RuntimeException("config 'omp.common.codecache.db.*' common code read fail.", e);
		}
	}

	/**
	 * 주어진 groupCode에 해당하는 코드리스트를 얻어 온다.
	 * USE_YN 이 Y인것만 반환 됩니다.
	 * 얻어진 리스트는 편집 불가로, 수정 작업시 RuntimeException이 발생됩니다.
	 * @param grpCd 가져올 코드의 그룹코드 번호
	 * @return 해당 groupCode의 코드 리스트
	 */
	public static List<CommCode> getCommCode(String grpCd) {
		return cachedCode.groupCodeList.get(grpCd);
	}

	/**
	 * 주어진 groupCode, detailCode 에 해당하는 코드를 얻어 온다.
	 *
	 * @param grpCd 가져올 코드의 그룹코드 번호
	 * @param dtlCd 가져올 코드의 상세코드 번호
	 * @return 해당 상세코드
	 */
	public static CommCode getCommCode(String grpCd, String dtlCd) {
		Map<String, CommCode>	codeGroup;
		
		codeGroup	= cachedCode.groupCodeMap.get(grpCd);
		if (codeGroup == null) {
			throw new IllegalArgumentException("code group '" + grpCd + "' is NOT available.");
		}
		
		return codeGroup.get(dtlCd);
	}

	/**
	 * 주어진 detailFullCode 에 해당하는 코드를 얻어 온다.
	 *
	 * @param dtlFullCd 가져올 코드의 상세풀코드 번호
	 * @return 해당 상세코드
	 */
	public static CommCode getCommCodeByFullCode(String dtlFullCd) {
		return cachedCode.fullCodeMap.get(dtlFullCd);
	}
	
	/**
	 * 코드 리스트 중에 코드에 설정된 태그를 주어진 패턴식으로 판별하여, 그 결과를 기준으로 대상 코드를 포함하거나  제외시킨 새로운 코드 리스트를 반환합니다.
	 * 필터 패턴 둘을 모두 null로 설정하면 변집 가능한 list로 복사하는 역할만 수행합니다.
	 * @param src 필터링할 대상 코드 리스트
	 * @param filterPattern 포함 필터 패턴 와일드 카드 *나 ?를 포함하는 패턴 식, null 지정시 모두 포함
	 * @param excludeFilterPattern 제외 필터 패턴 와일드 카드 *나 ?를 포함하는 패턴 식, null 지정시 제거 없음
	 * @return 포함필터 패턴에 유효하고 제외필터 패턴에 포함되지 않는 코드들의 새로운 리스트
	 */
	public static List<CommCode> getFilteredList(List<CommCode> src, String filterPattern, String excludeFilterPattern) {
		List<CommCode>	rv;
		StringPattern	filter;
		StringPattern	excludeFilter;
		
		filter			= filterPattern == null ? null : new StringPattern(filterPattern);
		excludeFilter	= excludeFilterPattern == null ? null : new StringPattern(excludeFilterPattern);
		rv				= new ArrayList<CommCode>();
		for (CommCode cd : src) {
			if (filter != null && !cd.isAvailable(filter)) {
				continue;
			}
			if (excludeFilter != null && cd.isAvailable(excludeFilterPattern)) {
				continue;
			}
			rv.add(cd);
		}
		return rv;
	}

//	public static CommCode getField1CommCode(String field1) {
//		return instance._getField1CommCode(field1);
//	}
//
//	private CommCode _getField1CommCode(String field1) {
//		return service.findField1CommCode(field1);
//	}
//
//	/**
//	 * 주어진 groupCode, addField1 에 해당하는 코드를 얻어 온다.
//	 *
//	 * @param grpCd 가져올 코드의 그룹코드 번호
//	 * @param addField1 가져올 코드의 추가필드2의 값
//	 * @return 해당 groupCodee, addField1의 코드 리스트
//	 */
//	public static List getCommCodeWithAddField1(String grpCd, String addField1) {
//		return instance._getCommCodeWithAddField1(grpCd, addField1);
//	}
//
//	private List _getCommCodeWithAddField1(String grpCd, String addField1) {
//		return service.findCommCodeWithAddField1(grpCd, addField1);
//	}
//
//	/**
//	 * 주어진 Code Name 에 해당하는 코드를 얻어 온다.
//	 * @param grpCd 코드명이 속하는 그룹코드
//	 * @param codeName 코드명
//	 * @return
//	 */
//	public static CommCode getCommCodeByCodeName(String grpCd, String codeName) {
//		return instance._getCommCodeByCodeName(grpCd, codeName);
//	}
//
//	public CommCode _getCommCodeByCodeName(String grpCd, String codeName) {
//		return service.findCommCodeByCodeName(grpCd, codeName);
//	}
}
