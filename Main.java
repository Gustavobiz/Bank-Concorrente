public class Main {

    public static void main(String[] args) {
        // Cria duas instancias do recuso compartilhado
        final Conta contaA = new Conta(1, 1000.0);
        final Conta contaB = new Conta(2, 1000.0);

        // Cria duas instancias da tarefa concorrente,
        // uma para cada direção da transferência.
        Transferencia transferencia1 = new Transferencia(contaA, contaB, 100.0);
        Transferencia transferencia2 = new Transferencia(contaB, contaA, 200.0);

        // Cria as duas threads. Associamos cada thread a uma tarefa
        Thread t1 = new Thread(transferencia1, "Thread-1 (A->B)");
        Thread t2 = new Thread(transferencia2, "Thread-2 (B->A)");


        t1.start();
        t2.start();

        System.out.println("Threads de transferência iniciadas...");
    }
}
