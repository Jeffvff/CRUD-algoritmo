package Application;

import java.util.Scanner;

// 1. Classe para representar o Contato
class Contato {
    private String nome;
    private String telefone;
    private boolean ehBloqueado;

    public Contato(String nome, String telefone, boolean ehBloqueado) {
        this.nome = nome;
        this.telefone = telefone;
        this.ehBloqueado = ehBloqueado;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public boolean isEhBloqueado() { return ehBloqueado; }
}

// 2. Classe Principal (com o mesmo nome do seu arquivo TelefoneV2.java)
public class Contatos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Vetor fixo para até 100 contatos
        Contato[] listaContatos = new Contato[100];

        // Controlador de contatos ativos
        int totalContatos = 0;

        // Adicionando os contatos obrigatórios e bloqueados nas posições 0 e 1
        listaContatos[0] = new Contato("SUS (Saúde)", "136", true);
        totalContatos++;

        listaContatos[1] = new Contato("Polícia", "190", true);
        totalContatos++;

        int opcao = 0;

        do {
            System.out.println("\n");
            System.out.println("  LISTA TELEFÔNICA 60+  ");
            System.out.println("");
            System.out.println("1 - Ver Contatos");
            System.out.println("2 - Adicionar Novo Contato");
            System.out.println("3 - Editar Contato");
            System.out.println("4 - Apagar Contato");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Opção inválida! Digite um número.");
                scanner.nextLine();
                continue;
            }

            switch (opcao) {
                case 1:
                    // --- VER CONTATOS ---
                    System.out.println("\n--- SEUS CONTATOS ---");
                    for (int i = 0; i < totalContatos; i++) {
                        System.out.println((i + 1) + "º - Nome: " + listaContatos[i].getNome() + " | Tel: " + listaContatos[i].getTelefone());
                    }
                    break;

                case 2:
                    // --- ADICIONAR CONTATO ---
                    System.out.println("\n--- NOVO CONTATO ---");

                    if (totalContatos >= listaContatos.length) {
                        System.out.println("A memória da lista está cheia!");
                        break;
                    }

                    System.out.print("Digite o nome da pessoa: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o telefone: ");
                    String telefone = scanner.nextLine();

                    listaContatos[totalContatos] = new Contato(nome, telefone, false);
                    totalContatos++;

                    System.out.println("Contato salvo com sucesso!");
                    break;

                case 3:
                    // --- EDITAR CONTATO ---
                    System.out.println("\n--- EDITAR CONTATO ---");
                    System.out.print("Digite a posição do contato que quer editar: ");
                    int posEditar = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (posEditar >= 0 && posEditar < totalContatos) {
                        Contato contatoEditar = listaContatos[posEditar];

                        if (contatoEditar.isEhBloqueado()) {
                            System.out.println("Atenção: Este contato é de emergência e não pode ser alterado.");
                        } else {
                            System.out.print("Digite o novo nome (ou Enter para manter): ");
                            String novoNome = scanner.nextLine();
                            if (!novoNome.isEmpty()) contatoEditar.setNome(novoNome);

                            System.out.print("Digite o novo telefone (ou Enter para manter): ");
                            String novoTelefone = scanner.nextLine();
                            if (!novoTelefone.isEmpty()) contatoEditar.setTelefone(novoTelefone);

                            System.out.println("Contato atualizado com sucesso!");
                        }
                    } else {
                        System.out.println("Posição inválida!");
                    }
                    break;

                case 4:
                    // --- APAGAR CONTATO ---
                    System.out.println("\n--- APAGAR CONTATO ---");
                    System.out.print("Digite a posição do contato que deseja apagar: ");
                    int posApagar = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (posApagar >= 0 && posApagar < totalContatos) {
                        Contato contatoApagar = listaContatos[posApagar];

                        if (contatoApagar.isEhBloqueado()) {
                            System.out.println("Atenção: Contatos de emergência não podem ser apagados!");
                        } else {
                            // Desloca os contatos para preencher o espaço do contato apagado
                            for (int i = posApagar; i < totalContatos - 1; i++) {
                                listaContatos[i] = listaContatos[i + 1];
                            }

                            listaContatos[totalContatos - 1] = null;
                            totalContatos--;

                            System.out.println("Contato apagado com sucesso!");
                        }
                    } else {
                        System.out.println("Posição inválida!");
                    }
                    break;

                case 5:
                    System.out.println("Obrigado por usar a Lista Telefônica. Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 5);

        scanner.close();
    }
}