package com.sync.task.application;

import com.sync.common.application.MyApplication;

import io.dropwizard.setup.Environment;

public class Application extends MyApplication<Configuration> {

	@Override
	protected void runCustom(Configuration configuration, Environment environment) {
		super.runCustom(configuration, environment);
	}

	public static void main(String[] args) throws Exception {
		new Application().run(args);
	}
}
