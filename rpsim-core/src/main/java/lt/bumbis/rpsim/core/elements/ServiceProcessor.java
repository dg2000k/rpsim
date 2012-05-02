package lt.bumbis.rpsim.core.elements;

import java.util.concurrent.TimeUnit;

import lt.bumbis.rpsim.core.SimModel;
import lt.bumbis.rpsim.core.entities.SvcReq;
import lt.bumbis.rpsim.core.entities.SvcProcessorExec;
import desmoj.core.dist.ContDist;
import desmoj.core.simulator.Queue;

public class ServiceProcessor extends SimModelElement {
	
	private String name;
	private int numReqExec;
	private String distName;
	private boolean showInReport;
	private boolean showInTrace;
	
	private Queue<SvcReq> waitQueue;
	private Queue<SvcProcessorExec> idleQueue;
	private ContDist serviceTimeDist;
	private TimeUnit serviceTimeUnit;
		
	public ServiceProcessor(String name, int numReqExec, String distName, TimeUnit serviceTimeUnit, boolean showInReport, boolean showInTrace) {
		this.name = name;
		this.numReqExec = numReqExec;
		this.distName = distName;
		this.serviceTimeUnit = serviceTimeUnit;
		this.showInReport = showInReport;
		this.showInTrace = showInTrace;
	}
	
	public void init(SimModel model) {
		waitQueue = new Queue<SvcReq>(model, name, showInReport, showInTrace); 
		idleQueue = new Queue<SvcProcessorExec>(model, name, showInReport, showInTrace);
		for (int i=0; i<numReqExec; i++) {
			SvcProcessorExec svcReqExec = new SvcProcessorExec(model, name+"_exec_"+i, showInTrace);
			idleQueue.insert(svcReqExec);
		}
		serviceTimeDist = model.getDist(distName);
	}
	
	@Override
	public void doInitialSchedules(SimModel model) {
		// TODO Auto-generated method stub
		
	}

		
	public Queue<SvcReq> getWaitQueue() {
		return waitQueue;
	}

	public Queue<SvcProcessorExec> getIdleQueue() {
		return idleQueue;
	}

	public ContDist getServiceTimeDist() {
		return serviceTimeDist;
	}

	public TimeUnit getServiceTimeUnit() {
		return serviceTimeUnit;
	}
}
