package kirya;

public abstract class Parent {

    private Integer a = 100;
    private String b = new String("sdfsdfsfsdf");

    public Parent(Integer a, String b) {
        System.out.println("invoke parent constructor a" + a + " b" + b);

        Integer a1 = makeJob(a);
        this.a = a1;
        this.b = b;

        System.out.println("parent а" + a + "b" + b);
    }

    public abstract Integer makeJob(int in);
}
