package pro.helderlee.resteasy.app;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import pro.helderlee.resteasy.service.impl.EmployeeServiceImpl;

@ApplicationPath("rest-api")
public class EmployeeApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();

	public EmployeeApplication() {
		singletons.add(new EmployeeServiceImpl());
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
