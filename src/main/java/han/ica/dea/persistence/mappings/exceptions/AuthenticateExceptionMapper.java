package han.ica.dea.persistence.mappings.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import han.ica.dea.domain.exceptions.AuthenticateException;

@Provider
public class AuthenticateExceptionMapper implements ExceptionMapper<AuthenticateException> {
    @Override
    public Response toResponse(AuthenticateException e) {
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
