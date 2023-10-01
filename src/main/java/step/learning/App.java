package step.learning;
import com.google.inject.Guice;
import com.google.inject.Injector;
import step.learning.files.FileIoDemo;
import step.learning.ioc.ConfigModule;
import step.learning.ioc.IocDemo;
import step.learning.oop.OopDemo;

import javax.naming.spi.Resolver;

public class App 
{
    public static void main( String[] args )
    {
        // new BasicsDemo().run();
        // new HomeworkReadline().run();
        // new DirDemo().run();
//        new FileIoDemo().run();
        // new OopDemo().run();
        Injector injector = Guice.createInjector(new ConfigModule());
        IocDemo iocDemo = injector.getInstance(IocDemo.class);
        iocDemo.run();
    }
}
