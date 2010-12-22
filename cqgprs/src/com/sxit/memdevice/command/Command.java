package com.sxit.memdevice.command;

import com.sxit.communicateguard.service.MemService;
import com.sxit.models.mem.MemDevice;
import com.sxit.models.mem.MemDevicecommand;

public interface Command {
	 String getresult(String orgstr);
	public String getresult(MemService memservice,MemDevice device,MemDevicecommand command, String userid);
}
