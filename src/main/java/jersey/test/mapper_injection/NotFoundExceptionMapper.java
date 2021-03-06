package jersey.test.mapper_injection;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Martynas Jusevičius <martynas@atomgraph.com>
 */
@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    public static final int STATUS = 666;
    
    @Inject PerRequestInterface obj;
            
    @Override
    public Response toResponse(NotFoundException e) {
        if (obj != null) return Response.status(500).entity(obj.getField()).build();
            
        return Response.status(STATUS).location(obj.getURI()).build();
    }
    
}
