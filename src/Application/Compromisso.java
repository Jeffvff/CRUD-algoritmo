package Application;

import java.util.Locale;
import java.util.Scanner;

public class Compromisso {

    // Método para ler Horas
    public static int lerHora(Scanner sc) {
        int hora;

        do {
            System.out.print("Insira somente as horas: ");
            hora = sc.nextInt();
            sc.nextLine();

            if (hora < 0 || hora > 23) {
                System.out.println("Hora inválida!");
            }

        } while (hora < 0 || hora > 23);

        return hora;
    }

    // Método para Ler Minutos
    public static int lerMinuto(Scanner sc) {
        int minuto;

        do {
            System.out.print("Agora insira os minutos: ");
            minuto = sc.nextInt();
            sc.nextLine();

            if (minuto < 0 || minuto > 59) {
                System.out.println("Minutos inválidos!");
            }

        } while (minuto < 0 || minuto > 59);

        return minuto;
    }

    // Método Gravar Comprmissos
    public static void gravarCompromisso(Scanner sc, int[] listaHora, int[] listaMinutos, String[] listaNome, String[] listaLocal,
            String[] listaDesc, int posicao) {
        String nomeCompromisso, observacao, local;
        int hora, minuto;

        System.out.print("\nInsira o nome do compromisso: ");
        nomeCompromisso = sc.nextLine().trim();

        hora = lerHora(sc);
        minuto = lerMinuto(sc);

        System.out.printf("Digite o local do compromisso: ");
        local = sc.nextLine();

        System.out.printf("Coloque uma observação do compromisso: ");
        observacao = sc.nextLine();

        listaHora[posicao] = hora;
        listaMinutos[posicao] = minuto;
        listaDesc[posicao] = observacao;
        listaNome[posicao] = nomeCompromisso;
        listaLocal[posicao] = local;

    }

    // Método Exibir Comproissos
    public static void exibirCompromissos(int[] listaHora, int[] listaMinutos, String[] listaNome, String[] listaLocal,
            String[] listaDesc, int qtdCompromissos) {

        System.out.println();
        System.out.println("==============================================================");
        System.out.println("N° | HORÁRIO | NOME | LOCAL | OBSERVAÇÃO");
        System.out.println("==============================================================");

        for (int i = 0; i < qtdCompromissos; i++) {
            System.out.printf(
                    "%d | %02d:%02d | %s | %s | %s%n",
                    i + 1, listaHora[i], listaMinutos[i], listaNome[i], listaLocal[i], listaDesc[i]);
        }

        System.out.println("==============================================================");
        System.out.println();

    }

    // Método que Ordena os Compromissos
    public static void ordenarCompromissos(int[] listaHora, int[] listaMinutos, String[] listaNome, String[] listaLocal,
            String[] listaDesc, int qtdCompromissos) {

        for (int i = 0; i < qtdCompromissos - 1; i++) {

            for (int j = i + 1; j < qtdCompromissos; j++) {

                int horarioI = listaHora[i] * 60 + listaMinutos[i];
                int horarioJ = listaHora[j] * 60 + listaMinutos[j];

                if (horarioJ < horarioI) {
                    int auxHora = listaHora[i];
                    listaHora[i] = listaHora[j];
                    listaHora[j] = auxHora;

                    int auxMin = listaMinutos[i];
                    listaMinutos[i] = listaMinutos[j];
                    listaMinutos[j] = auxMin;

                    String auxNome = listaNome[i];
                    listaNome[i] = listaNome[j];
                    listaNome[j] = auxNome;

                    String auxLocal = listaLocal[i];
                    listaLocal[i] = listaLocal[j];
                    listaLocal[j] = auxLocal;

                    String auxDesc = listaDesc[i];
                    listaDesc[i] = listaDesc[j];
                    listaDesc[j] = auxDesc;
                }
            }
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        int[] listaHora = new int[100];
        int[] listaMinutos = new int[100];
        String[] listaDesc = new String[100];
        String[] listaNome = new String[100];
        String[] listaLocal = new String[100];

        int qtdCompromissos = 0;

        int menuInicialCompromisso;

        System.out.printf("[1] - Adicionar compromisso.%n[0] - Voltar.%n");
        System.out.print("Escolha: ");
        menuInicialCompromisso = sc.nextInt();
        sc.nextLine();

        switch (menuInicialCompromisso) {
            case 1:

                gravarCompromisso(sc, listaHora, listaMinutos, listaNome, listaLocal, listaDesc, qtdCompromissos);

                qtdCompromissos++;

                ordenarCompromissos(listaHora, listaMinutos, listaNome, listaLocal, listaDesc, qtdCompromissos);

                char menuAER;

                do {
                    exibirCompromissos(listaHora, listaMinutos, listaNome, listaLocal, listaDesc, qtdCompromissos);

                    System.out.print(
                            "==================================================\n| [A] - Adicionar | [E] - Editar | [R] - Remover | [S] - Sair\n");
                    System.out.print("Selecione: ");
                    menuAER = sc.nextLine().charAt(0);
                    menuAER = Character.toUpperCase(menuAER);
                    switch (menuAER) {
                        case 'A':

                            gravarCompromisso(sc, listaHora, listaMinutos, listaNome, listaLocal, listaDesc, qtdCompromissos);
                            qtdCompromissos++;

                            ordenarCompromissos(listaHora, listaMinutos, listaNome, listaLocal, listaDesc,
                                    qtdCompromissos);

                            break;

                        case 'E':
                            int menuEditar;
                            boolean loopEditar, verificarIndice;

                            do {
                                loopEditar = false;

                                do {
                                    System.out.println("\nSelecione qual compromisso voce deseja editar");
                                    System.out.print("Selecione: ");
                                    menuEditar = sc.nextInt();
                                    sc.nextLine();
                                    if (menuEditar > 0 && menuEditar <= qtdCompromissos) {
                                        verificarIndice = true;
                                    } else {
                                        System.out.println("Compromisso inexistente! Tente novamente");
                                        verificarIndice = false;
                                    }
                                } while (verificarIndice == false);

                                menuEditar--;

                                if (menuEditar >= 0 && menuEditar < qtdCompromissos) {
                                    
                                    gravarCompromisso(sc, listaHora, listaMinutos, listaNome, listaLocal, listaDesc, menuEditar);
                                    ordenarCompromissos(listaHora, listaMinutos, listaNome, listaLocal, listaDesc, qtdCompromissos);

                                    loopEditar = false;
                                } else {
                                    System.out.println("Número Invalido! Tente novamente.");
                                    loopEditar = true;
                                }
                            } while (loopEditar == true);
                            break;

                        case 'R':

                            int menuRemover;
                            boolean verificacaoMenuRemover, verificarIndice2;

                            do {
                                verificacaoMenuRemover = false;

                                do {
                                    System.out.print("Qual compromisso você deseja remover: ");
                                    menuRemover = sc.nextInt();
                                    sc.nextLine();

                                    if (menuRemover > 0 && menuRemover <= qtdCompromissos) {
                                        verificarIndice2 = true;
                                    } else {
                                        System.out.println("Numero Errado. Tente novamente");
                                        verificarIndice2 = false;
                                    }
                                } while (verificarIndice2 != true);

                                menuRemover--;

                                if (menuRemover >= 0 && menuRemover < qtdCompromissos) {
                                    System.out.println("Removendo compromisso...");
                                    System.out.println("\nCompromisso removido com sucesso!\n");

                                    for (int i = menuRemover; i < qtdCompromissos - 1; i++) {

                                        listaHora[i] = listaHora[i + 1];
                                        listaMinutos[i] = listaMinutos[i + 1];
                                        listaDesc[i] = listaDesc[i + 1];
                                        listaNome[i] = listaNome[i + 1];
                                        listaLocal[i] = listaLocal[i + 1];
                                    }

                                    listaHora[qtdCompromissos - 1] = 0;
                                    listaMinutos[qtdCompromissos - 1] = 0;
                                    listaDesc[qtdCompromissos - 1] = null;
                                    listaNome[qtdCompromissos - 1] = null;
                                    listaLocal[qtdCompromissos - 1] = null;

                                    qtdCompromissos--;

                                    verificacaoMenuRemover = true;

                                } else {
                                    System.out.println("Número Inválido. tente novamente!!");
                                    verificacaoMenuRemover = false;
                                }
                            } while (verificacaoMenuRemover == false);
                            break;
                        case 'S':
                            System.out.println("Voltando...");
                            break;
                        default:
                            System.out.println("Opção Inválida. Tente novamente!!");
                            break;
                    }
                } while (menuAER != 'S');

                menuInicialCompromisso = 0;
                break;
            default:
                System.out.println("Fechando programa...");
        }

        sc.close();
    }
}