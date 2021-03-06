package jersey.test;

import javax.ws.rs.ApplicationPath;
import jersey.test.constructor_exception.ConstructorExceptionMapper;
import jersey.test.constructor_exception.ConstructorExceptionResource;
import jersey.test.mapper_injection.MapperInjectionResource;
import jersey.test.mapper_injection.NotFoundExceptionMapper;
import jersey.test.mapper_injection.PerRequestInterfaceFactory;
import jersey.test.mapper_injection.PerRequestInterface;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("")
public class App extends ResourceConfig {
    
    public App() {
        register(ConstructorExceptionResource.class);
        register(ConstructorExceptionMapper.class);

        register(MapperInjectionResource.class);
        register(NotFoundExceptionMapper.class);
        register(new AbstractBinder()
        {
            @Override
            protected void configure()
            {
                bindFactory(PerRequestInterfaceFactory.class).to(PerRequestInterface.class).
                proxy(true).proxyForSameScope(false).in(RequestScoped.class);
            }
        });
    }
    
}
