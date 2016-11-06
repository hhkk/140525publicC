public class Main {
    public static void main(String[] args) {
        Script script = new Script();
        System.out.println("a1");
        JavaClassToTestPassingToG j = new JavaClassToTestPassingToG();
        script.hello_world("asd", j);
        System.out.println("a2");
    }
}