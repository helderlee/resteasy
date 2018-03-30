package pro.helderlee.resteasy.service;

import javax.ws.rs.core.Response;

import pro.helderlee.resteasy.model.Employee;

public interface EmployeeService {
	
	public Response addEmployee(Employee e);

	public Response deleteEmployee(long id);

	public Employee getEmployee(long id);

	public Employee[] getAllEmployees();
	
}
