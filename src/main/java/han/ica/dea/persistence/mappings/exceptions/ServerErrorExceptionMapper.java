package han.ica.dea.persistence.mappings.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


import han.ica.dea.domain.exceptions.ServerErrorException;

@Provider
public class ServerErrorExceptionMapper implements ExceptionMapper<ServerErrorException> {
    @Override
    public Response toResponse(ServerErrorException e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}
