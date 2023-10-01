package step.learning.ioc;

import com.google.inject.AbstractModule;
import step.learning.ioc.services.HashService;
import step.learning.ioc.services.Md5HashService;

public class ConfigModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(HashService.class).to(Md5HashService.class);
    }
}
