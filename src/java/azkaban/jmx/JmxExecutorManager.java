package azkaban.jmx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import azkaban.executor.ExecutableFlow;
import azkaban.executor.ExecutorManager;

public class JmxExecutorManager implements JmxExecutorManagerMBean {
	private ExecutorManager manager;

	public JmxExecutorManager(ExecutorManager manager) {
		this.manager = manager;
	}

	@Override
	public int getNumRunningFlows() {
		return this.manager.getRunningFlows().size();
	}

	@Override
	public String getExecutorThreadState() {
		return manager.getExecutorManagerThreadState().toString();
	}
	
	@Override
	public String getExecutorThreadStage() {
		return manager.getUpdaterThreadStage();
	}

	@Override
	public boolean isThreadActive() {
		return manager.isExecutorManagerThreadActive();
	}

	@Override
	public Long getLastThreadCheckTime() {
		return manager.getLastExecutorManagerThreadCheckTime();
	}
	
	@Override 
	public List<String> getPrimaryExecutorHostPorts() {
		return new ArrayList<String>(manager.getPrimaryServerHosts());
	}

	@Override
	public String getRunningFlows() {
		List<Integer> allIds = new ArrayList<Integer>();
		for (ExecutableFlow flow : manager.getRunningFlows()) {
			allIds.add(flow.getExecutionId());
		}
		Collections.sort(allIds);
		return allIds.toString();
	}

	
}
