package svcomp.SimpleExample;

import org.sosy_lab.sv_benchmarks.Verifier;

public class Main {

    public static void test(double a) {
        if(a > 0) {
            if(a < 100) {
                System.out.println("Branch P001");
            } else {
                System.out.println("Branch P002");
            }
        } else {
            System.out.println("Branch F001");
        }
    }

    public static void main(String[] args) {
        double a = Verifier.nondetDouble();
        test(a);
    }
}
