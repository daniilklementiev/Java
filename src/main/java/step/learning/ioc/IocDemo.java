package step.learning.ioc;

import com.google.inject.Inject;
import step.learning.ioc.services.hash.HashService;
import step.learning.ioc.services.hash.Md5HashService;
import step.learning.ioc.services.hash.Md5OldHashService;

public class IocDemo {
    @Inject
    private HashService hashService;
    @Inject
    private Md5HashService md5HashService;
    @Inject
    private Md5OldHashService md5OldHashService;

    public void run() {
        System.out.println("IoCDemo");
        long t1, t2;
        String hash;
        System.out.println(hashService.hash("IoCDemo"));
        System.out.println("--------------------");
        t1 = System.nanoTime();
        hash = md5HashService.hash("IoCDemo");
        t2 = System.nanoTime();
        System.out.println( hash + " " + (t2 - t1) + " ns");
        t1 = System.nanoTime();
        hash = md5OldHashService.hash("IoCDemo");
        t2 = System.nanoTime();
        System.out.println( hash + " " + (t2 - t1) + " ns");


    }
}

/*
* Inversion of Control - это принцип, который говорит о том, что
* объекты не должны создаваться внутри классов, а должны создаваться
* вне классов и передаваться внутрь классов.
*/