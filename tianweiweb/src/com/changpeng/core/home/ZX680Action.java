package com.changpeng.core.home;

import java.util.List;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.model.CoreProduct;
import com.changpeng.core.model.CorePublish;
import com.changpeng.core.model.CorePublishContent;
import com.changpeng.core.service.ProgressService;
import com.changpeng.sns.diary.service.ProDiaryService;

public class ZX680Action extends AbstractAction {
	public ZX680Action() {
		this.rightCode = "PUBLIC";
	}

	@Override
	protected String go() throws Exception {

		return SUCCESS;
	}

}
