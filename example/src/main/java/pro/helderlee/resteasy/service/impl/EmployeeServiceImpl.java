package pro.helderlee.resteasy.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pro.helderlee.resteasy.model.Employee;
import pro.helderlee.resteasy.model.GenericResponse;
import pro.helderlee.resteasy.service.EmployeeService;

@Path("/employee")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeServiceImpl implements EmployeeService {

	private static Map<Long, Employee> emps = new HashMap<Long, Employee>();

	@Override
	@POST
	@Path("/add")
	public Response addEmployee(Employee e) {
		GenericResponse response = new GenericResponse();
		if (emps.get(e.getId()) != null) {
			response.setStatus(false);
			response.setMessage("Employee Already Exists");
			response.setErrorCode("EC-01");
			return Response.status(422).entity(response).build();
		}
		emps.put(e.getId(), e);
		response.setStatus(true);
		response.setMessage("Employee created successfully");
		return Response.ok(response).build();
	}

	@Override
	@DELETE
	@Path("/{id}/delete")
	public Response deleteEmployee(@PathParam("id") long id) {
		GenericResponse response = new GenericResponse();
		if (emps.get(id) == null) {
			response.setStatus(false);
			response.setMessage("Employee Doesn't Exists");
			response.setErrorCode("EC-02");
			return Response.status(404).entity(response).build();
		}
		emps.remove(id);
		response.setStatus(true);
		response.setMessage("Employee deleted successfully");
		return Response.ok(response).build();
	}

	@Override
	@GET
	@Path("/{id}/get")
	public Employee getEmployee(@PathParam("id") long id) {
		return emps.get(id);
	}

	@Override
	@GET
	@Path("/getAll")
	public Employee[] getAllEmployees() {
		Set<Long> ids = emps.keySet();
		Employee[] e = new Employee[ids.size()];
		int i = 0;
		for (Long id : ids) {
			e[i] = emps.get(id);
			i++;
		}
		return e;
	}

}
