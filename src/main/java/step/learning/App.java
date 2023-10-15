package step.learning;
import com.google.inject.Guice;
import com.google.inject.Injector;
import step.learning.async.AsyncDemo;
import step.learning.async.InflationTaskHw;
import step.learning.async.TaskDemo;
import step.learning.files.FileIoDemo;
//import step.learning.ioc.ConfigModule;
//import step.learning.ioc.IocDemo;
//import step.learning.ioc.IocDemo2;
import step.learning.oop.OopDemo;

import javax.naming.spi.Resolver;
import java.util.concurrent.ExecutionException;

public class App 
{
    public static void main( String[] args ) throws ExecutionException, InterruptedException {
        // new BasicsDemo().run();
        // new HomeworkReadline().run();
        // new DirDemo().run();
//        new FileIoDemo().run();
//         new OopDemo().run();
//        Injector injector = Guice.createInjector(new ConfigModule());
//        IocDemo iocDemo = injector.getInstance(IocDemo.class);
//        iocDemo.run();
       // Guice.createInjector(new ConfigModule()).getInstance(InflationTaskHw.class).run();
//        new AsyncDemo().run();
//        Guice.createInjector(new ConfigModule()).getInstance(TaskDemo.class).run();
        new InflationTaskHw().run();
    }
}
