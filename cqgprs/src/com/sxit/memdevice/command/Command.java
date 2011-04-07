package com.sxit.memdevice.command;

import com.sxit.communicateguard.service.MemService;
import com.sxit.models.mem.MemDevice;
import com.sxit.models.mem.MemDevicecommand;

public abstract class Command {
	public String orgresult;
	abstract String getresult(String orgstr);
	public abstract String getresult(MemService memservice,MemDevice device,MemDevicecommand command, String userid);
}
