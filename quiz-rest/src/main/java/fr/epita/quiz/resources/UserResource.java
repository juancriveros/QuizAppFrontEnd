package fr.epita.quiz.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RequestBody;

import fr.epita.quiz.datamodel.User;
import fr.epita.quiz.dto.UserDTO;
import fr.epita.quiz.services.business.ExamDataService;

/**
 * User resource
 * Class that handles the operations of the transfer of the user to the data services and frontend 
 * @author juanc
 *
 */
@Path("/user")
public class UserResource {
	
	@Inject
	ExamDataService examDs;
	
	/**
	 * Method that handles the creating of a new user or the update
	 * @param userDto
	 * @return
	 */
	@POST
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response createUser(@RequestBody UserDTO userDto) {
		
		try {
			User user = new User();
			user.setId(userDto.getId());
			user.setName(userDto.getName());
			user.setAge(userDto.getAge());
			user.setEmail(userDto.getEmail());
			
			examDs.createUser(user);

			return Response.ok().build();
		}
		catch (Exception e) {
			// TODO Handle things properly
			e.printStackTrace();
			return Response.serverError().build();
		}
		
		
		
	}

}
