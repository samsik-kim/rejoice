package com.omp.commons.persistence.dao;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.StringUtils;

import pat.neocore.util.objlook.ObjectLooker;

import com.ibatis.common.jdbc.SimpleDataSource;
import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.template.SqlMapDaoTemplate;
import com.ibatis.sqlmap.client.event.RowHandler;
import com.omp.commons.commcode.CacheCommCode;
import com.omp.commons.model.ColumnInfoModel;
import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.PagenateList;
import com.omp.commons.model.Pagenateable;
import com.omp.commons.model.TotalCountCarriable;
import com.omp.commons.text.LocalizeMessage;
import com.omp.commons.util.CipherAES;
import com.omp.commons.util.ExcelHtmlWriter;
import com.omp.commons.util.config.ConfigProperties;

/**
 * 공통 범용 DAO
 * @author pat
 *
 */
public class CommonDAOImpl
	extends SqlMapDaoTemplate
	implements CommonDAO {
	
	private class PageListRowHandler 
		implements RowHandler {
		
		private PagenateList<Object>	list;
		private boolean					checked;

		private PageListRowHandler(PagenateInfoModel page) {
			this.list		= new PagenateList<Object>();
			this.list.setPage(page);
			page.setTotalCount(0L);
			this.checked	= false;
		}
		
		@Override
		public void handleRow(Object valueObject) {
			if (!this.checked) {
				Long				totalCount;
				PagenateInfoModel	page;
				
				if (valueObject instanceof TotalCountCarriable) {
					totalCount	= ((TotalCountCarriable)valueObject).getTotalCount();
				} else {
					ObjectLooker	olook	= ObjectLooker.getLooker(valueObject);
					totalCount	= olook.getLongObj(valueObject, "totalCount");
				}
				page	= this.list.getPage();
				page.setTotalCount(totalCount);
				this.checked	= true;
			}
			this.list.add(valueObject);
		}
		
		public PagenateList<Object> getList() {
			return this.list;
		}
	}
	
	private class ExcelWriterRowHandler
		implements RowHandler {
		
		private ExcelHtmlWriter		ehw;
		private ObjectLooker		olook;
		private String[]			columnNames;
		private String[]			columnTexts;
		private	int[]				columnTypes;
		private long				rcnt;
		private String				notice;
		
		private ExcelWriterRowHandler(ExcelHtmlWriter ehw, List<ColumnInfoModel> columnList, String notice) {
			this.ehw		= ehw;
			this.columnNames	= new String[columnList.size()];
			this.columnTexts	= new String[this.columnNames.length];
			this.columnTypes	= new int[this.columnNames.length];
			for (int i=0; i<this.columnNames.length; i++) {
				ColumnInfoModel	colinf;
				
				colinf	= columnList.get(i);
				this.columnNames[i]	= colinf.getName();
				this.columnTexts[i]	= LocalizeMessage.getLocalizedMessage(colinf.getText());
				this.columnTypes[i]	= colinf.getType();
			}
			this.rcnt	= 0;
			this.notice	= notice;
		}
		
		@Override
		public void handleRow(Object valueObject) {
			if (this.olook == null) {
				Long	totalCount;
				int		numOfSheet;
				
				this.olook	= ObjectLooker.getLooker(valueObject);
				
				if (valueObject instanceof TotalCountCarriable) {
					totalCount	= ((TotalCountCarriable)valueObject).getTotalCount();
				} else {
					totalCount	= olook.getLongObj(valueObject, "totalCount");
				}
				numOfSheet	= (int)(totalCount.longValue()/60000) + 1;
	            this.ehw.setMaxSheet(numOfSheet);
	            for (int i=0; i<numOfSheet; i++) {
	            	this.ehw.setSheetName(i, "Sheet " + (i+1));
	            }
			}
			if (this.rcnt % 60000 == 0) {
				if (this.rcnt > 0) {
					this.ehw.tableEnd();
				}
                this.ehw.startSheet();
                if (StringUtils.isNotEmpty(this.notice)) {
                	this.ehw.printNotice(this.notice);
                }
				this.ehw.printNotice("Writed date " + new Date());
				this.ehw.tableStart();
				this.ehw.rowStart();
				for (String columnText : this.columnTexts) {
					this.ehw.printField(columnText, ExcelHtmlWriter.ALIGN_CENTER);
				}
				this.ehw.rowEnd();
			}
			this.ehw.rowStart();
			for (int i=0; i<this.columnNames.length; i++) {
				switch(this.columnTypes[i]) {
					case ColumnInfoModel.COLUMN_TYPE_STRING :
						this.ehw.printField(this.olook.getString(valueObject, this.columnNames[i]));
						break;
					case ColumnInfoModel.COLUMN_TYPE_INTEGER :
						this.ehw.printField(this.olook.getInteger(valueObject, this.columnNames[i]));
						break;
					case ColumnInfoModel.COLUMN_TYPE_LONG :
						this.ehw.printField(this.olook.getLong(valueObject, this.columnNames[i]));
						break;
					case ColumnInfoModel.COLUMN_TYPE_FLOAT :
						this.ehw.printField(this.olook.getFloat(valueObject, this.columnNames[i]));
						break;
					case ColumnInfoModel.COLUMN_TYPE_DOUBLE :
						this.ehw.printField(this.olook.getDouble(valueObject, this.columnNames[i]));
						break;
					case ColumnInfoModel.COLUMN_TYPE_DATE :
						this.ehw.printField(this.olook.getDate(valueObject, this.columnNames[i]));
						break;
					case ColumnInfoModel.COLUMN_TYPE_CODE :
						this.ehw.printField(CacheCommCode.getCommCodeByFullCode(this.olook.getString(valueObject, this.columnNames[i])).getCdNm());
						break;
					case ColumnInfoModel.COLUMN_TYPE_ENCRYRT :
						try {
							this.ehw.printField(CipherAES.decryption(this.olook.getString(valueObject, this.columnNames[i])));
						} catch (Exception e) {
							this.ehw.printField("decrypt fail.");
						}
						break;
					default:
						this.ehw.printField(this.olook.getObject(valueObject, this.columnNames[i]));
				}
			}
			this.ehw.rowEnd();
			this.rcnt++;
		}
		
		public void close() {
			if (this.rcnt == 0) {
	            this.ehw.setMaxSheet(1);
	            this.ehw.setSheetName(0, "Sheet 1");
                this.ehw.startSheet();
                if (StringUtils.isNotEmpty(this.notice)) {
                	this.ehw.printNotice(this.notice);
                }
				this.ehw.printNotice("Writed date " + new Date());
				this.ehw.tableStart();
				this.ehw.rowStart();
				for (String columnText : this.columnTexts) {
					this.ehw.printField(columnText, ExcelHtmlWriter.ALIGN_CENTER);
				}
				this.ehw.rowEnd();
			}
			this.ehw.tableEnd();
			this.ehw.close();
			
		}
	}
	

	public CommonDAOImpl(DaoManager daoManager) {
		super(daoManager);
	}
	
	/**
	 * 페이징 쿼리 처리 부분 추가됨
	 */
	@SuppressWarnings("rawtypes")
	public PagenateList queryForPageList(String id, Pagenateable param) {
		PageListRowHandler	prh;
		
		prh	= new PageListRowHandler(param.getPage());
		super.queryWithRowHandler(id, param, prh);
		return prh.getList();
	}
	
	/**
	 * 쿼리 수행 내용을 컨피그에 설정된 임시 폴더에 작성하고 작성된 파일을 반환합니다.
	 * @param id 쿼리 아이디
	 * @param param 쿼리 파라메터, 만약 Pagenateable 인터페이스를 구현 했다면 페이지 번호를 1, 페이지당 출력수를 Integer 최대값으로 설정하여 조건에 맞는 모든 내용이 출력되도록 설정합니다.
	 * @param columnList 엑셀파일에 작성할 컬럼들의 정의 리스트
	 * @throws IOException 작성중 오류 발생시
	 */
	public File queryForExcel(String id, Object param, List<ColumnInfoModel> columnList)
		throws IOException {
		return this.queryForExcel(id, param, columnList, null);
	}
	

	/**
	 * 쿼리 수행 내용을 컨피그에 설정된 임시 폴더에 작성하고 작성된 파일을 반환합니다.
	 * @param id 쿼리 아이디
	 * @param param 쿼리 파라메터, 만약 Pagenateable 인터페이스를 구현 했다면 페이지 번호를 1, 페이지당 출력수를 Integer 최대값으로 설정하여 조건에 맞는 모든 내용이 출력되도록 설정합니다.
	 * @param columnList 엑셀파일에 작성할 컬럼들의 정의 리스트
	 * @param notice 엑셀 파일 첫 부분에 기록할 문구
	 * @throws IOException 작성중 오류 발생시
	 */
	public File queryForExcel(String id, Object param, List<ColumnInfoModel> columnList, String notice)
		throws IOException {
		ConfigProperties		cfg;
		File					dir;
		File					workfile;
		ExcelHtmlWriter			ehw;
		ExcelWriterRowHandler	ewrh;
		
		cfg			= new ConfigProperties();
		dir			= new File(cfg.getString("omp.common.path.temp.excel-download"));
		dir.mkdirs();
		workfile	= File.createTempFile("excelwriter.", ".temp", dir);
		ehw			= new ExcelHtmlWriter(workfile, "UTF-8");
		if (param instanceof Pagenateable) {
			PagenateInfoModel	page;
			
			page	= ((Pagenateable)param).getPage();
			page.setNo(1);
			page.setPageAll();
		}
		ewrh		= new ExcelWriterRowHandler(ehw, columnList, notice);
		try {
			super.queryWithRowHandler(id, param, ewrh);
		} finally {
			ewrh.close();
		}

		return workfile;
	}
	
	public void closeDataSource()
		throws SQLException {
		DataSource	ds;
		
		ds	= this.getSqlMapTransactionManager().getDataSource();
		if (ds instanceof BasicDataSource) {
			((BasicDataSource)ds).close();
		} else if (ds instanceof SimpleDataSource) {
			((SimpleDataSource)ds).forceCloseAll();
		}
	}
}
