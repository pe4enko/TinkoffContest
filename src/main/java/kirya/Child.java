package kirya;

public class Child extends Parent{
    Integer cc = 7;

    public Child(Integer a, String b) {
        super(a, b);
    }

    @Override
    public Integer makeJob(int in) {
        return cc;
    }

    public static void main(String[] args) {
        Child child = new Child(3, "4");
    }
}
