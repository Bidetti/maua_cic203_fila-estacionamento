import java.util.Scanner;

public class QueuePark {
    public static void main(String[] args) {
        Fila estacionamento = new Fila(12);
        Fila fila_estacionamento = new Fila(12);
        Scanner sc = new Scanner(System.in);
        String entrada;
        System.out.println("Iniciando a simulação do QueuePark\n");
        do {
            System.out.print("Para iniciar escolha uma das seguintes opções:\n" +
                    "C - Chegada\n" +
                    "P - Partida\n" +
                    "F - Finalizar\n" + "> ");
            entrada = sc.nextLine();
            if (entrada.equalsIgnoreCase("C")) {
                System.out.println("Insire o número da placa do veículo: ");
                String placa = sc.nextLine();
                if (estacionamento.estaCheia()) {
                    fila_estacionamento.enfileira(placa);
                    System.out.println("Estacionamento cheio, veículo " + placa + " está na fila! Posição: " + fila_estacionamento.getTamanho());
                } else {
                    estacionamento.enfileira(placa);
                    System.out.println("Veículo " + placa + " estacionado!");
                }
            } else if (entrada.equalsIgnoreCase("P")) {
                boolean carroEncontrado = false;
                int deslocamentos = 0;
                System.out.println("Insire o número da placa do veículo: ");
                String placa = sc.nextLine();

                while (!estacionamento.estaVazia()) {
                    String carro = String.valueOf(estacionamento.desenfilera());
                    if (carro.equalsIgnoreCase(placa)) {
                        carroEncontrado = true;
                    }else {
                        estacionamento.enfileira(carro);
                    }
                    if (deslocamentos == estacionamento.getTamanho()) {
                        break;
                    }
                    deslocamentos++;
                }

                if (carroEncontrado) {
                    if (!fila_estacionamento.estaVazia()) {
                        String carro_da_fila = String.valueOf(fila_estacionamento.desenfilera());
                        estacionamento.enfileira(carro_da_fila);
                        System.out.println("Abriu uma vaga no estacionamento! O carro " + carro_da_fila + " foi estacionado.");
                    }
                    System.out.println("O carro " + placa + " saiu do estacionamento após " + deslocamentos + " deslocamento(s).");
                } else {
                    System.out.println("O carro " + placa + " não foi encontrado.");
                }
            }
            System.out.println(estacionamento);
        } while (!entrada.equalsIgnoreCase("F"));

        sc.close();
        System.out.println("Simulação finalizada!");
    }
}