package com.omp.admin.device.tester.action;

import java.util.List;

import com.omp.admin.common.util.CommonUtil;
import com.omp.admin.device.tester.model.PhoneTester;
import com.omp.admin.device.tester.service.DeviceTesterService;
import com.omp.commons.action.BaseAction;
import com.omp.commons.model.PagenateList;
import com.omp.commons.util.StringUtil;

@SuppressWarnings("serial")
public class DeviceTesterAction extends BaseAction {

	// private GLogger log = new GLogger(DeviceTesterAction.class);

	private DeviceTesterService deviceTesterService;

	private PhoneTester phoneTester = null;
	private List<PhoneTester> phoneTesterList = null;

	private String selectedTesterId = null;

	private long srchCnt = 0;

	public DeviceTesterAction() {
		deviceTesterService = new DeviceTesterService();
	}

	@SuppressWarnings("rawtypes")
	public String selectPhoneTesterList() {

		if (phoneTester == null) {
			phoneTester = new PhoneTester();
		}

		String sSearchValue = ("".equals(StringUtil.nvlStr(this.req.getParameter("searchValue")))) ? phoneTester.getSearchValue()
				: this.req.getParameter("searchValue");
		String sPageNo = ("".equals(StringUtil.nvlStr(this.req.getParameter("pageNo")))) ? "" + phoneTester.getPage().getNo() : this.req
				.getParameter("pageNo");
		sPageNo = StringUtil.nvlStr(sPageNo, "1");

		phoneTester.setSearchValue(CommonUtil.removeSpecial(sSearchValue));
		phoneTester.getPage().setNo(Integer.parseInt(sPageNo));

		phoneTesterList = deviceTesterService.selectPhoneTesterPagingList(phoneTester);
		srchCnt = ((PagenateList) phoneTesterList).getTotalCount();

		return SUCCESS;
	}

	public String insertPhoneTester() {

		if (phoneTester == null) {
			phoneTester = new PhoneTester();
		}

		phoneTester.setRegId(CommonUtil.getLoginId(this.req.getSession()));
		phoneTester.setUpdId(CommonUtil.getLoginId(this.req.getSession()));

		deviceTesterService.insertPhoneTester(phoneTester);

		return SUCCESS;
	}

	public String deletePhoneTester() {

		if (phoneTester == null) {
			phoneTester = new PhoneTester();
		}

		phoneTester.setTesterId(selectedTesterId);
		phoneTester.setRegId(CommonUtil.getLoginId(this.req.getSession()));
		phoneTester.setUpdId(CommonUtil.getLoginId(this.req.getSession()));

		deviceTesterService.deletePhoneTester(phoneTester);

		return SUCCESS;
	}

	public PhoneTester getPhoneTester() {
		return phoneTester;
	}

	public void setPhoneTester(PhoneTester phoneTester) {
		this.phoneTester = phoneTester;
	}

	public List<PhoneTester> getPhoneTesterList() {
		return phoneTesterList;
	}

	public void setPhoneTesterList(List<PhoneTester> phoneTesterList) {
		this.phoneTesterList = phoneTesterList;
	}

	public String getSelectedTesterId() {
		return selectedTesterId;
	}

	public void setSelectedTesterId(String selectedTesterId) {
		this.selectedTesterId = selectedTesterId;
	}

	public long getSrchCnt() {
		return srchCnt;
	}

	public void setSrchCnt(long srchCnt) {
		this.srchCnt = srchCnt;
	}

}
