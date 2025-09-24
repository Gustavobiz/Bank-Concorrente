public class Main {
    public static void main(String[] args) throws InterruptedException { // Adicione "throws"
        final Conta contaA = new Conta(1, 1000.0);
        final Conta contaB = new Conta(2, 1000.0);

        Transferencia transferencia1 = new Transferencia(contaA, contaB, 100.0);
        Transferencia transferencia2 = new Transferencia(contaB, contaA, 200.0);

        Thread t1 = new Thread(transferencia1, "Thread-1 (A->B)");
        Thread t2 = new Thread(transferencia2, "Thread-2 (B->A)");

        t1.start();
        t2.start();

        System.out.println("Threads de transferência iniciadas, aguardando finalização...");
        

        t1.join();
        t2.join();


        System.out.println("\n--- Transferências finalizadas ---");
        System.out.println("Saldo final da Conta A: " + contaA.getSaldo());
        System.out.println("Saldo final da Conta B: " + contaB.getSaldo());
    }
}