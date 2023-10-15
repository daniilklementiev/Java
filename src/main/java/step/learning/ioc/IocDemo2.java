//package step.learning.ioc;
//
//import com.google.inject.Inject;
//import com.google.inject.name.Named;
////import step.learning.ioc.services.hash.HashService;
////import step.learning.ioc.services.random.RandomService;
//
//public class IocDemo2 {
////    @Inject @Named("Digest-hash")
////    private HashService digestHashService;
////    @Inject @Named("Signature-hash")
////    private HashService signatureHashService;
//    private final HashService digestHashService;
//    private final HashService signatureHashService;
//    private final RandomService randomService;
//
//    @Inject
//    public IocDemo2(
//            @Named("Digest-hash") HashService digestHashService,
//            @Named("Signature-hash") HashService signatureHashService, RandomService randomService) {
//        this.digestHashService = digestHashService;
//        this.signatureHashService = signatureHashService;
//        this.randomService = randomService;
//    }
//
//    @Inject @Named("Digest-hash")
//    private HashService digestHashService2;
//
//    public void run() {
////        System.out.println("IocDemo2.run");
////        System.out.println("digestHashService.hash(\"IoC Demo\") = " + digestHashService.hash("IoC Demo"));
////        System.out.println("signatureHashService.hash(\"IoC Demo\") = " + signatureHashService.hash("IoC Demo"));
////        System.out.println("digestHashService2.hash(\"IoC Demo\") = " + digestHashService2.hash("IoC Demo"));
////        System.out.println(digestHashService.hashCode() + " " + digestHashService2.hashCode());
//        System.out.println(randomService.randomHex(6));
//    }
//}
