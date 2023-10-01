package step.learning.ioc;

import com.google.inject.Inject;
import step.learning.ioc.services.HashService;

public class IocDemo {
    @Inject
    private HashService hashService;
    public void run() {
        System.out.println("IoCDemo");
        System.out.println(hashService.hash("IoCDemo"));
    }
}

/*
* Inversion of Control - это принцип, который говорит о том, что
* объекты не должны создаваться внутри классов, а должны создаваться
* вне классов и передаваться внутрь классов.
*/