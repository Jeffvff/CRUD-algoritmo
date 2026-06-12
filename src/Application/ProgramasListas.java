package Application;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class ProgramasListas {

    static final String FILE_MEDICAMENTO = "medicamentos.csv";
    static final String FILE_ANOTACOES = "anotacoes.csv";
    static final String FILE_COMPROMISSOS = "compromissos.csv";
    static final String FILE_CONTATOS = "contatos.csv";
    static final String FILE_COMPRAS = "compras.csv";

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        int opcao = -1;

        do {
            System.out.println("==========================================");
            System.out.println("          PROGRAMAS E LISTAS");
            System.out.println("==========================================");
            System.out.println("[1] - Medicamentos");
            System.out.println("[2] - Anotacoes");
            System.out.println("[3] - Compromissos");
            System.out.println("[4] - Contatos");
            System.out.println("[5] - Lista de Compras");
            System.out.println("[0] - Sair");
            System.out.print("Escolha: ");
            if (sc.hasNextInt()) {
                opcao = sc.nextInt();
                sc.nextLine();
            } else {
                sc.nextLine();
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    menuMedicamento(sc);
                    break;
                case 2:
                    menuAnotacoes(sc);
                    break;
                case 3:
                    menuCompromissos(sc);
                    break;
                case 4:
                    menuContatos(sc);
                    break;
                case 5:
                    menuCompras(sc);
                    break;
                case 0:
                    System.out.println("Encerrando programa...");
                    break;
                default:
                    System.out.println("Opcao Invalida!");
            }
        } while (opcao != 0);

        sc.close();
    }

    private static String[] lerCSV(String caminhoArquivo) {
        String[] linhas = new String[100];
        int cont = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null && cont < 100) {
                linhas[cont] = linha;
                cont++;
            }
        } catch (IOException e) {
        }
        return linhas;
    }

    private static void salvarCSV(String caminhoArquivo, String[] dados) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(caminhoArquivo))) {
            for (String linha : dados) {
                if (linha != null) {
                    pw.println(linha);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + caminhoArquivo);
        }
    }

    private static void menuMedicamento(Scanner sc) {
        String[] db = lerCSV(FILE_MEDICAMENTO);
        String[] listaHorario = new String[100];
        String[] listaNome = new String[100];
        String[] listaDesc = new String[100];

        int qtd = 0;
        for (String linha : db) {
            if (linha != null) {
                String[] partes = linha.split(";");
                if (partes.length >= 3) {
                    listaHorario[qtd] = partes[0];
                    listaNome[qtd] = partes[1];
                    listaDesc[qtd] = partes[2];
                    qtd++;
                }
            }
        }

        char menuAER = ' ';
        do {
            System.out.println("--- LISTA DE MEDICAMENTOS ---");
            for (int i = 0; i < qtd; i++) {
                System.out.printf("[%d] - | %s | %s | %s%n", i + 1, listaHorario[i], listaNome[i], listaDesc[i]);
            }
            System.out.println("==================================================");
            System.out.println("| [A] - Adicionar | [E] - Editar | [R] - Remover | [S] - Sair");
            System.out.print("Selecione: ");
            String input = sc.nextLine().trim();
            if (input.isEmpty())
                continue;
            menuAER = Character.toUpperCase(input.charAt(0));

            switch (menuAER) {
                case 'A':
                    if (qtd >= 100) {
                        System.out.println("Lista cheia!");
                        break;
                    }
                    System.out.print("Insira o nome do medicamento: ");
                    String nome = sc.nextLine().trim();
                    String relogio = lerRelogio(sc);
                    System.out.print("Coloque uma descricao do medicamento: ");
                    String desc = sc.nextLine().trim();

                    listaHorario[qtd] = relogio;
                    listaNome[qtd] = nome;
                    listaDesc[qtd] = desc;
                    qtd++;
                    salvarMedicamentos(listaHorario, listaNome, listaDesc, qtd);
                    break;
                case 'E':
                    System.out.print("Qual medicamento voce deseja editar: ");
                    int posE = sc.nextInt() - 1;
                    sc.nextLine();
                    if (posE >= 0 && posE < qtd) {
                        System.out.print("Insira o novo nome: ");
                        listaNome[posE] = sc.nextLine().trim();
                        listaHorario[posE] = lerRelogio(sc);
                        System.out.print("Insira a nova descricao: ");
                        listaDesc[posE] = sc.nextLine().trim();
                        salvarMedicamentos(listaHorario, listaNome, listaDesc, qtd);
                    } else {
                        System.out.println("Numero Invalido.");
                    }
                    break;
                case 'R':
                    System.out.print("Qual medicamento voce deseja remover: ");
                    int posR = sc.nextInt() - 1;
                    sc.nextLine();
                    if (posR >= 0 && posR < qtd) {
                        for (int i = posR; i < qtd - 1; i++) {
                            listaHorario[i] = listaHorario[i + 1];
                            listaNome[i] = listaNome[i + 1];
                            listaDesc[i] = listaDesc[i + 1];
                        }
                        qtd--;
                        salvarMedicamentos(listaHorario, listaNome, listaDesc, qtd);
                        System.out.println("Medicamento removido.");
                    } else {
                        System.out.println("Numero Invalido.");
                    }
                    break;
                case 'S':
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opcao Invalida.");
            }
        } while (menuAER != 'S');
    }

    private static String lerRelogio(Scanner sc) {
        int hora = 0, minuto = 0;
        boolean valido = false;
        do {
            System.out.print("Insira somente as horas: ");
            hora = sc.nextInt();
            sc.nextLine();
            if (hora >= 0 && hora <= 23)
                valido = true;
            else
                System.out.println("Hora invalida.");
        } while (!valido);

        valido = false;
        do {
            System.out.print("Agora insira os minutos: ");
            minuto = sc.nextInt();
            sc.nextLine();
            if (minuto >= 0 && minuto <= 59)
                valido = true;
            else
                System.out.println("Minutos invalidos.");
        } while (!valido);

        return String.format("%02d:%02d", hora, minuto);
    }

    private static void salvarMedicamentos(String[] listaHorario, String[] listaNome, String[] listaDesc, int qtd) {
        String[] dados = new String[qtd];
        for (int i = 0; i < qtd; i++) {
            dados[i] = listaHorario[i] + ";" + listaNome[i] + ";" + listaDesc[i];
        }
        salvarCSV(FILE_MEDICAMENTO, dados);
    }

    private static void menuAnotacoes(Scanner sc) {
        String[] db = lerCSV(FILE_ANOTACOES);
        String[] titulos = new String[100];
        String[] datas = new String[100];
        String[] textos = new String[100];
        int qtd = 0;

        for (String linha : db) {
            if (linha != null) {
                String[] partes = linha.split(";", 3);
                if (partes.length >= 3) {
                    titulos[qtd] = partes[0];
                    datas[qtd] = partes[1];
                    textos[qtd] = partes[2];
                    qtd++;
                }
            }
        }

        char menuAER = ' ';
        do {
            System.out.println("--- ANOTACOES ---");
            for (int i = 0; i < qtd; i++) {
                System.out.printf("[%d] - %s [%s]%n", i + 1, titulos[i], datas[i]);
            }
            System.out.println("==================================================");
            System.out.println("| [A] - Adicionar | [E] - Editar/Ler | [R] - Remover | [S] - Sair");
            System.out.print("Selecione: ");
            String input = sc.nextLine().trim();
            if (input.isEmpty())
                continue;
            menuAER = Character.toUpperCase(input.charAt(0));

            switch (menuAER) {
                case 'A':
                    if (qtd >= 100) {
                        System.out.println("Lista cheia!");
                        break;
                    }
                    System.out.print("Titulo: ");
                    titulos[qtd] = sc.nextLine().trim();
                    System.out.print("Data (ex: 11/06/2025): ");
                    datas[qtd] = sc.nextLine().trim();
                    System.out.print("Texto da anotacao: ");
                    textos[qtd] = sc.nextLine().trim();
                    qtd++;
                    salvarAnotacoes(titulos, datas, textos, qtd);
                    break;
                case 'E':
                    System.out.print("Qual anotacao deseja editar/ler: ");
                    int posE = sc.nextInt() - 1;
                    sc.nextLine();
                    if (posE >= 0 && posE < qtd) {
                        System.out.println("Texto Atual: " + textos[posE]);
                        System.out.print("Novo Titulo (ou enter para manter): ");
                        String nTit = sc.nextLine().trim();
                        if (!nTit.isEmpty())
                            titulos[posE] = nTit;

                        System.out.print("Nova Data (ou enter para manter): ");
                        String nDat = sc.nextLine().trim();
                        if (!nDat.isEmpty())
                            datas[posE] = nDat;

                        System.out.print("Novo Texto (ou enter para manter): ");
                        String nTex = sc.nextLine().trim();
                        if (!nTex.isEmpty())
                            textos[posE] = nTex;

                        salvarAnotacoes(titulos, datas, textos, qtd);
                    } else {
                        System.out.println("Numero Invalido.");
                    }
                    break;
                case 'R':
                    System.out.print("Qual anotacao deseja remover: ");
                    int posR = sc.nextInt() - 1;
                    sc.nextLine();
                    if (posR >= 0 && posR < qtd) {
                        for (int i = posR; i < qtd - 1; i++) {
                            titulos[i] = titulos[i + 1];
                            datas[i] = datas[i + 1];
                            textos[i] = textos[i + 1];
                        }
                        qtd--;
                        salvarAnotacoes(titulos, datas, textos, qtd);
                        System.out.println("Anotacao removida.");
                    } else {
                        System.out.println("Numero Invalido.");
                    }
                    break;
                case 'S':
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opcao Invalida.");
            }
        } while (menuAER != 'S');
    }

    private static void salvarAnotacoes(String[] titulos, String[] datas, String[] textos, int qtd) {
        String[] dados = new String[qtd];
        for (int i = 0; i < qtd; i++) {
            dados[i] = titulos[i] + ";" + datas[i] + ";" + textos[i];
        }
        salvarCSV(FILE_ANOTACOES, dados);
    }

    private static void menuCompromissos(Scanner sc) {
        String[] db = lerCSV(FILE_COMPROMISSOS);
        String[] listaHorario = new String[100];
        String[] listaNome = new String[100];
        String[] listaLocal = new String[100];
        String[] listaDesc = new String[100];
        int qtd = 0;

        for (String linha : db) {
            if (linha != null) {
                String[] partes = linha.split(";", 4);
                if (partes.length >= 4) {
                    listaHorario[qtd] = partes[0];
                    listaNome[qtd] = partes[1];
                    listaLocal[qtd] = partes[2];
                    listaDesc[qtd] = partes[3];
                    qtd++;
                }
            }
        }

        char menuAER = ' ';
        do {
            System.out.println("--- LISTA DE COMPROMISSOS ---");
            for (int i = 0; i < qtd; i++) {
                System.out.printf("[%d] - | %s | %s | %s | %s%n", i + 1, listaHorario[i], listaNome[i], listaLocal[i],
                        listaDesc[i]);
            }
            System.out.println("==================================================");
            System.out.println("| [A] - Adicionar | [E] - Editar | [R] - Remover | [S] - Sair");
            System.out.print("Selecione: ");
            String input = sc.nextLine().trim();
            if (input.isEmpty())
                continue;
            menuAER = Character.toUpperCase(input.charAt(0));

            switch (menuAER) {
                case 'A':
                    if (qtd >= 100) {
                        System.out.println("Lista cheia!");
                        break;
                    }
                    System.out.print("Insira o nome do compromisso: ");
                    listaNome[qtd] = sc.nextLine().trim();
                    listaHorario[qtd] = lerRelogio(sc);
                    System.out.print("Digite o local do compromisso: ");
                    listaLocal[qtd] = sc.nextLine().trim();
                    System.out.print("Coloque uma observacao do compromisso: ");
                    listaDesc[qtd] = sc.nextLine().trim();
                    qtd++;
                    salvarCompromissos(listaHorario, listaNome, listaLocal, listaDesc, qtd);
                    break;
                case 'E':
                    System.out.print("Qual compromisso deseja editar: ");
                    int posE = sc.nextInt() - 1;
                    sc.nextLine();
                    if (posE >= 0 && posE < qtd) {
                        System.out.print("Novo nome: ");
                        listaNome[posE] = sc.nextLine().trim();
                        listaHorario[posE] = lerRelogio(sc);
                        System.out.print("Novo local: ");
                        listaLocal[posE] = sc.nextLine().trim();
                        System.out.print("Nova observacao: ");
                        listaDesc[posE] = sc.nextLine().trim();
                        salvarCompromissos(listaHorario, listaNome, listaLocal, listaDesc, qtd);
                    } else {
                        System.out.println("Numero Invalido.");
                    }
                    break;
                case 'R':
                    System.out.print("Qual compromisso deseja remover: ");
                    int posR = sc.nextInt() - 1;
                    sc.nextLine();
                    if (posR >= 0 && posR < qtd) {
                        for (int i = posR; i < qtd - 1; i++) {
                            listaHorario[i] = listaHorario[i + 1];
                            listaNome[i] = listaNome[i + 1];
                            listaLocal[i] = listaLocal[i + 1];
                            listaDesc[i] = listaDesc[i + 1];
                        }
                        qtd--;
                        salvarCompromissos(listaHorario, listaNome, listaLocal, listaDesc, qtd);
                        System.out.println("Compromisso removido.");
                    } else {
                        System.out.println("Numero Invalido.");
                    }
                    break;
                case 'S':
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opcao Invalida.");
            }
        } while (menuAER != 'S');
    }

    private static void salvarCompromissos(String[] listaHorario, String[] listaNome, String[] listaLocal,
            String[] listaDesc, int qtd) {
        String[] dados = new String[qtd];
        for (int i = 0; i < qtd; i++) {
            dados[i] = listaHorario[i] + ";" + listaNome[i] + ";" + listaLocal[i] + ";" + listaDesc[i];
        }
        salvarCSV(FILE_COMPROMISSOS, dados);
    }

    private static void menuContatos(Scanner sc) {
        String[] db = lerCSV(FILE_CONTATOS);
        String[] nomes = new String[100];
        String[] telefones = new String[100];
        boolean[] bloqueados = new boolean[100];
        int qtd = 0;

        for (String linha : db) {
            if (linha != null) {
                String[] partes = linha.split(";");
                if (partes.length >= 3) {
                    nomes[qtd] = partes[0];
                    telefones[qtd] = partes[1];
                    bloqueados[qtd] = Boolean.parseBoolean(partes[2]);
                    qtd++;
                }
            }
        }

        if (qtd == 0) {
            nomes[0] = "SUS (Saude)";
            telefones[0] = "136";
            bloqueados[0] = true;
            nomes[1] = "Policia";
            telefones[1] = "190";
            bloqueados[1] = true;
            qtd = 2;
            salvarContatos(nomes, telefones, bloqueados, qtd);
        }

        char menuAER = ' ';
        do {
            System.out.println("--- LISTA TELEFONICA ---");
            for (int i = 0; i < qtd; i++) {
                System.out.printf("[%d] - Nome: %s | Tel: %s%n", i + 1, nomes[i], telefones[i]);
            }
            System.out.println("==================================================");
            System.out.println("| [A] - Adicionar | [E] - Editar | [R] - Remover | [S] - Sair");
            System.out.print("Selecione: ");
            String input = sc.nextLine().trim();
            if (input.isEmpty())
                continue;
            menuAER = Character.toUpperCase(input.charAt(0));

            switch (menuAER) {
                case 'A':
                    if (qtd >= 100) {
                        System.out.println("Lista cheia!");
                        break;
                    }
                    System.out.print("Nome: ");
                    nomes[qtd] = sc.nextLine().trim();
                    System.out.print("Telefone: ");
                    telefones[qtd] = sc.nextLine().trim();
                    bloqueados[qtd] = false;
                    qtd++;
                    salvarContatos(nomes, telefones, bloqueados, qtd);
                    break;
                case 'E':
                    System.out.print("Qual contato deseja editar: ");
                    int posE = sc.nextInt() - 1;
                    sc.nextLine();
                    if (posE >= 0 && posE < qtd) {
                        if (bloqueados[posE]) {
                            System.out.println("Este contato de emergencia nao pode ser alterado.");
                        } else {
                            System.out.print("Novo Nome: ");
                            nomes[posE] = sc.nextLine().trim();
                            System.out.print("Novo Telefone: ");
                            telefones[posE] = sc.nextLine().trim();
                            salvarContatos(nomes, telefones, bloqueados, qtd);
                        }
                    } else {
                        System.out.println("Numero Invalido.");
                    }
                    break;
                case 'R':
                    System.out.print("Qual contato deseja remover: ");
                    int posR = sc.nextInt() - 1;
                    sc.nextLine();
                    if (posR >= 0 && posR < qtd) {
                        if (bloqueados[posR]) {
                            System.out.println("Contatos de emergencia nao podem ser apagados!");
                        } else {
                            for (int i = posR; i < qtd - 1; i++) {
                                nomes[i] = nomes[i + 1];
                                telefones[i] = telefones[i + 1];
                                bloqueados[i] = bloqueados[i + 1];
                            }
                            qtd--;
                            salvarContatos(nomes, telefones, bloqueados, qtd);
                            System.out.println("Contato removido.");
                        }
                    } else {
                        System.out.println("Numero Invalido.");
                    }
                    break;
                case 'S':
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opcao Invalida.");
            }
        } while (menuAER != 'S');
    }

    private static void salvarContatos(String[] nomes, String[] telefones, boolean[] bloqueados, int qtd) {
        String[] dados = new String[qtd];
        for (int i = 0; i < qtd; i++) {
            dados[i] = nomes[i] + ";" + telefones[i] + ";" + bloqueados[i];
        }
        salvarCSV(FILE_CONTATOS, dados);
    }

    private static void menuCompras(Scanner sc) {
        String[] db = lerCSV(FILE_COMPRAS);
        String[] nomes = new String[100];
        String[] qtds = new String[100];
        String[] categorias = new String[100];
        String[] prioridades = new String[100];
        String[] status = new String[100];
        int qtd = 0;

        for (String linha : db) {
            if (linha != null) {
                String[] partes = linha.split(";", 5);
                if (partes.length >= 5) {
                    nomes[qtd] = partes[0];
                    qtds[qtd] = partes[1];
                    categorias[qtd] = partes[2];
                    prioridades[qtd] = partes[3];
                    status[qtd] = partes[4];
                    qtd++;
                }
            }
        }

        char menuAER = ' ';
        do {
            System.out.println("--- LISTA DE COMPRAS ---");
            for (int i = 0; i < qtd; i++) {
                String st = status[i].equals("S") ? "Comprado" : "Pendente";
                System.out.printf("[%d] - %s | Qtd: %s | Cat: %s | Prio: %s | Status: %s%n",
                        i + 1, nomes[i], qtds[i], categorias[i], prioridades[i], st);
            }
            System.out.println("==================================================");
            System.out.println("| [A] - Adicionar | [E] - Marcar Comprado/Editar | [R] - Remover | [S] - Sair");
            System.out.print("Selecione: ");
            String input = sc.nextLine().trim();
            if (input.isEmpty())
                continue;
            menuAER = Character.toUpperCase(input.charAt(0));

            switch (menuAER) {
                case 'A':
                    if (qtd >= 100) {
                        System.out.println("Lista cheia!");
                        break;
                    }
                    System.out.print("Nome do produto: ");
                    nomes[qtd] = sc.nextLine().trim();
                    System.out.print("Quantidade: ");
                    qtds[qtd] = sc.nextLine().trim();
                    System.out.println("Categoria:");
                    System.out.println("[1] - Alimento");
                    System.out.println("[2] - Higiene");
                    System.out.println("[3] - Limpeza");
                    System.out.println("[4] - Outros");
                    System.out.print("Escolha: ");
                    int catCod = sc.nextInt();
                    sc.nextLine();
                    categorias[qtd] = (catCod == 1) ? "Alimento"
                            : ((catCod == 2) ? "Higiene" : ((catCod == 3) ? "Limpeza" : "Outros"));

                    System.out.println("Prioridade:");
                    System.out.println("[1] - Baixa");
                    System.out.println("[2] - Media");
                    System.out.println("[3] - Alta");
                    System.out.print("Escolha: ");
                    int priCod = sc.nextInt();
                    sc.nextLine();
                    prioridades[qtd] = (priCod == 3) ? "Alta" : ((priCod == 2) ? "Media" : "Baixa");
                    status[qtd] = "N";
                    qtd++;
                    salvarCompras(nomes, qtds, categorias, prioridades, status, qtd);
                    break;
                case 'E':
                    System.out.print("Qual item deseja marcar como comprado ou editar: ");
                    int posE = sc.nextInt() - 1;
                    sc.nextLine();
                    if (posE >= 0 && posE < qtd) {
                        System.out.println("Deseja (1) Marcar como comprado ou (2) Editar informações?");
                        int opc = sc.nextInt();
                        sc.nextLine();
                        if (opc == 1) {
                            status[posE] = "S";
                        } else if (opc == 2) {
                            System.out.print("Novo nome: ");
                            nomes[posE] = sc.nextLine().trim();
                            System.out.print("Nova Quantidade: ");
                            qtds[posE] = sc.nextLine().trim();
                        }
                        salvarCompras(nomes, qtds, categorias, prioridades, status, qtd);
                    } else {
                        System.out.println("Numero Invalido.");
                    }
                    break;
                case 'R':
                    System.out.print("Qual item deseja remover: ");
                    int posR = sc.nextInt() - 1;
                    sc.nextLine();
                    if (posR >= 0 && posR < qtd) {
                        for (int i = posR; i < qtd - 1; i++) {
                            nomes[i] = nomes[i + 1];
                            qtds[i] = qtds[i + 1];
                            categorias[i] = categorias[i + 1];
                            prioridades[i] = prioridades[i + 1];
                            status[i] = status[i + 1];
                        }
                        qtd--;
                        salvarCompras(nomes, qtds, categorias, prioridades, status, qtd);
                        System.out.println("Item removido.");
                    } else {
                        System.out.println("Numero Invalido.");
                    }
                    break;
                case 'S':
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opcao Invalida.");
            }
        } while (menuAER != 'S');
    }

    private static void salvarCompras(String[] nomes, String[] qtds, String[] categorias, String[] prioridades,
            String[] status, int qtd) {
        String[] dados = new String[qtd];
        for (int i = 0; i < qtd; i++) {
            dados[i] = nomes[i] + ";" + qtds[i] + ";" + categorias[i] + ";" + prioridades[i] + ";" + status[i];
        }
        salvarCSV(FILE_COMPRAS, dados);
    }
}