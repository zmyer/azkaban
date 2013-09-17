package azkaban.jmx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import azkaban.executor.ExecutableFlow;
import azkaban.executor.ExecutionReference;
import azkaban.executor.ExecutorManagerAdapter;
import azkaban.utils.Pair;

public class JmxExecutorManagerAdapter implements JmxExecutorManagerAdapterMBean {
	private ExecutorManagerAdapter manager;

	public JmxExecutorManagerAdapter(ExecutorManagerAdapter manager) {
		this.manager = manager;
	}

	@Override
	public int getNumRunningFlows() {
		try {
			return this.manager.getRunningFlows().size();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public String getExecutorManagerThreadState() {
		return manager.getExecutorManagerThreadState().toString();
	}

	@Override
	public boolean isExecutorManagerThreadActive() {
		return manager.isExecutorManagerThreadActive();
	}

	@Override
	public Long getLastExecutorManagerThreadCheckTime() {
		return manager.getLastExecutorManagerThreadCheckTime();
	}
	
	@Override 
	public List<String> getPrimaryExecutorHostPorts() {
		return new ArrayList<String>(manager.getPrimaryServerHosts());
	}

	@Override
	public String getRunningFlows() {
		List<Integer> allIds = new ArrayList<Integer>();
		try {
			for (ExecutableFlow flow : manager.getRunningFlows()) {
				allIds.add(flow.getExecutionId());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collections.sort(allIds);
		return allIds.toString();
	}

	@Override
	public String getUpdaterThreadStage() {
		return manager.getUpdaterThreadStage();
	}
}
