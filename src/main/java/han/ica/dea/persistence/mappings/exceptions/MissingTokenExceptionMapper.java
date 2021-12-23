package han.ica.dea.persistence.mappings.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import han.ica.dea.domain.exceptions.MissingTokenException;

@Provider
public class MissingTokenExceptionMapper implements ExceptionMapper<MissingTokenException> {
    @Override
    public Response toResponse(MissingTokenException e) {
        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }
}
