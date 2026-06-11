package Agenda;
import java.util.Scanner;
import java.io.*;
public class ListaDeCompra {
    static final String ARQUIVO = "lista_compras.txt";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int opcao;

        do {

            System.out.println("\n================================================================");
            System.out.println("                    LISTA DE COMPRAS");
            System.out.println("================================================================");
            System.out.println("[1] Adicionar item");
            System.out.println("[2] Listar todos os itens");
            System.out.println("[3] Buscar item");
            System.out.println("[4] Marcar como comprado");
            System.out.println("[5] Remover item");
            System.out.println("[0] Voltar ao menu principal");
            System.out.println("================================================================");
            System.out.print("Escolha uma opcao: ");

            opcao = lerInteiro();

            switch (opcao) {

                case 1:
                    adicionarItem();
                    break;

                case 2:
                    listarItens();
                    break;

                case 3:
                    buscarItem();
                    break;

                case 4:
                    marcarComprado();
                    break;

                case 5:
                    removerItem();
                    break;

                case 0:
                    System.out.println("Voltando...");
                    break;

                default:
                    System.out.println("Opcao invalida!");
            }

        } while (opcao != 0);
    }

    static int lerInteiro() {

        while (!sc.hasNextInt()) {
            System.out.print("Digite apenas numeros: ");
            sc.next();
        }

        int numero = sc.nextInt();
        sc.nextLine();

        return numero;
    }

    static void adicionarItem() {

        try {

            FileWriter fw = new FileWriter(ARQUIVO, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            System.out.print("Nome do produto: ");
            String nome = sc.nextLine();

            System.out.print("Quantidade: ");
            String qtd = sc.nextLine();

            System.out.println("Categoria:");
            System.out.println("[1] Alimento");
            System.out.println("[2] Higiene");
            System.out.println("[3] Limpeza");
            System.out.println("[4] Outros");
            System.out.print("Escolha: ");

            int catCod = lerInteiro();

            String categoria;

            switch (catCod) {

                case 1:
                    categoria = "Alimento";
                    break;

                case 2:
                    categoria = "Higiene";
                    break;

                case 3:
                    categoria = "Limpeza";
                    break;

                default:
                    categoria = "Outros";
            }

            System.out.println("Prioridade:");
            System.out.println("[1] Baixa");
            System.out.println("[2] Media");
            System.out.println("[3] Alta");
            System.out.print("Escolha: ");

            int prioCod = lerInteiro();

            String prioridade;

            switch (prioCod) {

                case 1:
                    prioridade = "Baixa";
                    break;

                case 2:
                    prioridade = "Media";
                    break;

                case 3:
                    prioridade = "Alta";
                    break;

                default:
                    prioridade = "Baixa";
            }

            pw.println(nome + " | " + qtd + " | " + categoria + " | " + prioridade + " | N");

            pw.close();
            bw.close();
            fw.close();

            System.out.println("Item adicionado com sucesso!");

        } catch (IOException e) {

            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    static String[] lerArquivo() {


        String[] linhas = new String[1000];

        int cont = 0;

        try {

            BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));

            String linha;

            while ((linha = br.readLine()) != null) {

                linhas[cont] = linha;
                cont++;
            }

            br.close();

        } catch (IOException e) {

            System.out.println("Arquivo ainda nao criado.");
        }

        String[] resultado = new String[cont];

        System.arraycopy(linhas, 0, resultado, 0, cont);

        return resultado;
    }

    static void salvarTudo(String[] dados) {

        try {

            PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO));

            for (String linha : dados) {

                if (linha != null) {
                    pw.println(linha);
                }
            }

            pw.close();

        } catch (IOException e) {

            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    static void listarItens() {

        String[] lista = lerArquivo();

        if (lista.length == 0) {

            System.out.println("Lista vazia.");
            return;
        }

        System.out.println("\n================================================================");
        System.out.println("                    SUA LISTA DE COMPRAS");
        System.out.println("================================================================");

        System.out.printf("%-3s | %-20s | %-6s | %-12s | %-10s | %s%n",
                "N", "Produto", "Qtd", "Categoria", "Prioridade", "Status");

        System.out.println("--------------------------------------------------------------------------");

        for (int i = 0; i < lista.length; i++) {

            String[] dados = lista[i].split("\\|");

            String status;

            if (dados[4].equals("S")) {
                status = "Comprado";
            } else {
                status = "Pendente";
            }

            System.out.printf("%-3d | %-20s | %-6s | %-12s | %-10s | %s%n",
                    (i + 1),
                    dados[0],
                    dados[1],
                    dados[2],
                    dados[3],
                    status);
        }
    }

    static void buscarItem() {

        System.out.println("\n================ BUSCAR ITEM ================");
        System.out.println("[1] Buscar por nome");
        System.out.println("[2] Buscar por categoria");
        System.out.print("Escolha: ");

        int op = lerInteiro();

        System.out.print("Digite o termo da busca: ");

        String termo = sc.nextLine().toLowerCase();

        String[] lista = lerArquivo();

        boolean achou = false;

        System.out.println("\nResultados encontrados:");

        for (String linha : lista) {

            String[] dados = linha.split("\\|");

            if (op == 1 && dados[0].toLowerCase().contains(termo)) {

                System.out.println(
                        dados[0] + " | " +
                                dados[1] + " | " +
                                dados[2] + " | " +
                                dados[3]
                );

                achou = true;
            }

            else if (op == 2 && dados[2].toLowerCase().contains(termo)) {

                System.out.println(
                        dados[0] + " | " +
                                dados[1] + " | " +
                                dados[2] + " | " +
                                dados[3]
                );

                achou = true;
            }
        }

        if (!achou) {
            System.out.println("Nenhum item encontrado.");
        }
    }

    static void marcarComprado() {

        String[] lista = lerArquivo();

        if (lista.length == 0) {

            System.out.println("Lista vazia.");
            return;
        }

        listarItens();

        System.out.print("Digite o numero do item comprado: ");

        int num = lerInteiro() - 1;

        if (num < 0 || num >= lista.length) {

            System.out.println("Numero invalido!");
            return;
        }

        String[] partes = lista[num].split("\\|");

        partes[4] = "S";

        lista[num] = partes[0] + "|" +
                partes[1] + "|" +
                partes[2] + "|" +
                partes[3] + "|" +
                partes[4];

        salvarTudo(lista);

        System.out.println("Item marcado como comprado!");
    }

    static void removerItem() {

        String[] lista = lerArquivo();

        if (lista.length == 0) {

            System.out.println("Lista vazia.");
            return;
        }

        listarItens();

        System.out.print("Digite o numero do item para remover: ");

        int num = lerInteiro() - 1;

        if (num < 0 || num >= lista.length) {

            System.out.println("Numero invalido!");
            return;
        }

        String[] nova = new String[lista.length - 1];

        int indice = 0;

        for (int i = 0; i < lista.length; i++) {

            if (i != num) {

                nova[indice] = lista[i];
                indice++;
            }
        }

        salvarTudo(nova);

        System.out.println("Item removido com sucesso!");
    }
}


