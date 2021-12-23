package han.ica.dea.persistence.mappings.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import han.ica.dea.domain.exceptions.DbConnectionException;

@Provider
public class DbConnectionExceptionMapper implements ExceptionMapper<DbConnectionException> {
    @Override
    public Response toResponse(DbConnectionException e) {
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
