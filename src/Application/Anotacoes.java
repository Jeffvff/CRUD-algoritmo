package Application;

import java.util.Scanner;

public class Anotacoes {

    public static void main(String[] args) {

        Scanner leitor = new Scanner(System.in);
        int opcao = 0;
        int totalAnotacoes = 0;
        int indice = 0;
        int i = 0;
        int tamanhoMaximo = 100;
        String entrada = "";
        String linha = "";
        String[] titulos = new String[tamanhoMaximo];
        String[] textos = new String[tamanhoMaximo];
        String[] datas = new String[tamanhoMaximo];
        boolean encontrado = false;

        System.out.println("==========================================");
        System.out.println("       BEM-VINDO AO CADERNO DE NOTAS     ");
        System.out.println("==========================================");

        do {
            System.out.println();
            System.out.println("------------------------------------------");
            System.out.println("  MENU PRINCIPAL");
            System.out.println("------------------------------------------");
            System.out.println("  1 - Escrever nova anotacao");
            System.out.println("  2 - Ver todas as anotacoes");
            System.out.println("  3 - Ler uma anotacao");
            System.out.println("  4 - Apagar uma anotacao");
            System.out.println("  5 - Sair");
            System.out.println("------------------------------------------");
            System.out.print("  Digite sua escolha: ");

            entrada = leitor.nextLine().trim();

            if (entrada.equals("1")) {
                opcao = 1;
            } else if (entrada.equals("2")) {
                opcao = 2;
            } else if (entrada.equals("3")) {
                opcao = 3;
            } else if (entrada.equals("4")) {
                opcao = 4;
            } else if (entrada.equals("5")) {
                opcao = 5;
            } else {
                opcao = 0;
            }

            if (opcao == 1) {

                if (totalAnotacoes >= tamanhoMaximo) {
                    System.out.println();
                    System.out.println("  O caderno esta cheio. Apague uma anotacao antes de continuar.");
                } else {
                    System.out.println();
                    System.out.println("  NOVA ANOTACAO");
                    System.out.println("------------------------------------------");
                    System.out.print("  Titulo: ");
                    titulos[totalAnotacoes] = leitor.nextLine().trim();

                    System.out.print("  Data (ex: 11/06/2025): ");
                    datas[totalAnotacoes] = leitor.nextLine().trim();

                    System.out.print("  Texto da anotacao: ");
                    textos[totalAnotacoes] = leitor.nextLine().trim();

                    totalAnotacoes = totalAnotacoes + 1;

                    System.out.println();
                    System.out.println("  Anotacao salva com sucesso!");
                }

            } else if (opcao == 2) {

                System.out.println();
                System.out.println("  SUAS ANOTACOES");
                System.out.println("------------------------------------------");

                if (totalAnotacoes == 0) {
                    System.out.println("  Nenhuma anotacao encontrada.");
                } else {
                    i = 0;
                    while (i < totalAnotacoes) {
                        System.out.println("  " + (i + 1) + ". " + titulos[i] + "  [" + datas[i] + "]");
                        i = i + 1;
                    }
                }

            } else if (opcao == 3) {

                System.out.println();
                System.out.println("  LER ANOTACAO");
                System.out.println("------------------------------------------");

                if (totalAnotacoes == 0) {
                    System.out.println("  Nenhuma anotacao para ler.");
                } else {
                    i = 0;
                    while (i < totalAnotacoes) {
                        System.out.println("  " + (i + 1) + ". " + titulos[i]);
                        i = i + 1;
                    }
                    System.out.println();
                    System.out.print("  Digite o numero da anotacao: ");
                    entrada = leitor.nextLine().trim();

                    encontrado = false;
                    i = 0;
                    while (i < totalAnotacoes) {
                        if (entrada.equals(String.valueOf(i + 1))) {
                            encontrado = true;
                            indice = i;
                        }
                        i = i + 1;
                    }

                    if (encontrado) {
                        System.out.println();
                        System.out.println("  Titulo : " + titulos[indice]);
                        System.out.println("  Data   : " + datas[indice]);
                        System.out.println("  Texto  : " + textos[indice]);
                    } else {
                        System.out.println();
                        System.out.println("  Numero invalido.");
                    }
                }

            } else if (opcao == 4) {

                System.out.println();
                System.out.println("  APAGAR ANOTACAO");
                System.out.println("------------------------------------------");

                if (totalAnotacoes == 0) {
                    System.out.println("  Nenhuma anotacao para apagar.");
                } else {
                    i = 0;
                    while (i < totalAnotacoes) {
                        System.out.println("  " + (i + 1) + ". " + titulos[i]);
                        i = i + 1;
                    }
                    System.out.println();
                    System.out.print("  Digite o numero da anotacao a apagar: ");
                    entrada = leitor.nextLine().trim();

                    encontrado = false;
                    i = 0;
                    while (i < totalAnotacoes) {
                        if (entrada.equals(String.valueOf(i + 1))) {
                            encontrado = true;
                            indice = i;
                        }
                        i = i + 1;
                    }

                    if (encontrado) {
                        i = indice;
                        while (i < totalAnotacoes - 1) {
                            titulos[i] = titulos[i + 1];
                            textos[i] = textos[i + 1];
                            datas[i] = datas[i + 1];
                            i = i + 1;
                        }
                        titulos[totalAnotacoes - 1] = null;
                        textos[totalAnotacoes - 1] = null;
                        datas[totalAnotacoes - 1] = null;
                        totalAnotacoes = totalAnotacoes - 1;
                        System.out.println();
                        System.out.println("  Anotacao apagada com sucesso.");
                    } else {
                        System.out.println();
                        System.out.println("  Numero invalido.");
                    }
                }

            } else if (opcao == 5) {

                System.out.println();
                System.out.println("  Ate logo! Tenha um otimo dia.");
                System.out.println("==========================================");

            } else {

                System.out.println();
                System.out.println("  Opcao invalida. Tente novamente.");

            }

        } while (opcao != 5);

        leitor.close();
    }
}
