package Application;

import java.util.Locale;
import java.util.Scanner;

public class Medicamento {
    public static void main(String[] args){
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        String[] listaHorario = new String[100];
        String[] listaDesc = new String[100];
        String[] listaNome = new String [100];


        int menuInicialMedicamento;



        System.out.printf("[1] - Adicionar medicamento.%n[0] - Voltar.%n");
        System.out.print("Escolha: ");
        menuInicialMedicamento = sc.nextInt();
        sc.nextLine();
        switch (menuInicialMedicamento) {
            case 1:
                String nomeMedicamento,descricao,relogio;
                int hora = 0,minuto = 0;
                boolean zeroMinutos = false;

                System.out.print("Insira o nome do medicamento: ");
                nomeMedicamento = sc.nextLine().trim();

                boolean validacaoHora;
                boolean validacaoMinutos;
                do {
                    validacaoMinutos = false;
                    validacaoHora = false;
                    zeroMinutos = false;
                    System.out.print("Insira somente as horas: ");
                    hora = sc.nextInt();
                    sc.nextLine();
                    if (hora >= 0 && hora <= 23) {
                        do {
                            System.out.print("Agora insira os minutos: ");
                            minuto = sc.nextInt();
                            sc.nextLine();
                            if (minuto >= 0 && minuto <= 59) {
                                validacaoMinutos = false;
                                if (minuto == 0) {
                                    zeroMinutos = true;
                                }
                            } else {
                                System.out.println("Minutos invalidos. Tente Novamente!!");
                                validacaoMinutos = true;
                            }
                        } while (validacaoMinutos == true);

                    } else {
                        System.out.println("Hora invalida. Tente Novamente!!");
                        validacaoHora = true;
                    }
                } while (validacaoHora == true);

                System.out.printf("Coloque uma descrição do medicamento: ");
                descricao = sc.nextLine();
                System.out.println();

                if (zeroMinutos == true) {
                    relogio = hora + ":" + "00";
                } else {
                    relogio = hora + ":" + minuto;
                }

                listaHorario[0] = relogio;
                listaDesc[0] = descricao;
                listaNome[0] = nomeMedicamento;

                char menuAER;

                do {
                    for (int i = 0; i < listaHorario.length; i++) {
                        if (listaHorario[i] != null) {
                            System.out.printf("[%d] - | %s | %s | %s%n", i + 1, listaHorario[i], listaNome[i], listaDesc[i]);
                        }
                    }
                    System.out.printf("==================================================%n| [A] - Adicionar | [E] - Editar | [R] - Remover | [S] - Sair%n");
                    System.out.print("Selecione: ");
                    menuAER = sc.nextLine().charAt(0);
                    menuAER = Character.toUpperCase(menuAER);
                    switch (menuAER) {
                        case 'A':
                            do {
                                System.out.print("Insira o nome do medicamento: ");
                                nomeMedicamento = sc.nextLine().trim();
                                validacaoMinutos = false;
                                validacaoHora = false;
                                zeroMinutos = false;
                                System.out.print("Insira somente as horas: ");
                                hora = sc.nextInt();
                                sc.nextLine();
                                if (hora >= 0 && hora <= 23) {
                                    do {
                                        System.out.print("Agora insira os minutos: ");
                                        minuto = sc.nextInt();
                                        sc.nextLine();
                                        if (minuto >= 0 && minuto <= 59) {
                                            validacaoMinutos = false;
                                            if (minuto == 0) {
                                                zeroMinutos = true;
                                            }
                                        } else {
                                            System.out.println("Minutos invalidos. Tente Novamente!!");
                                            validacaoMinutos = true;
                                        }
                                    } while (validacaoMinutos == true);

                                } else {
                                    System.out.println("Hora invalida. Tente Novamente!!");
                                    validacaoHora = true;
                                }
                            } while (validacaoHora == true);

                            System.out.printf("Coloque uma descrição do medicamento: ");
                            descricao = sc.nextLine();
                            System.out.println();

                            if (zeroMinutos == true) {
                                relogio = hora + ":" + "00";
                            } else {
                                relogio = hora + ":" + minuto;
                            }

                            for (int i = 0; i < listaHorario.length; i++) {
                                if (listaHorario[i] == null) {
                                    listaHorario[i] = relogio;
                                    listaDesc[i] = descricao;
                                    listaNome[i] = nomeMedicamento;
                                    break;
                                }
                            }
                            break;
                        case 'E':
                            int menuEditar;
                            boolean loopEditar,verificarIndice;

                            do {
                                loopEditar = false;

                                    do {
                                        System.out.println("Selecione qual medicamento voce deseja editar");
                                        System.out.print("Selecione: ");
                                        menuEditar = sc.nextInt();
                                        sc.nextLine();
                                        if (menuEditar > 0 && menuEditar <= listaHorario.length) {
                                            verificarIndice = true;
                                        } else {
                                            System.out.println("Numero Errado. Tente novamente");
                                            verificarIndice = false;
                                        }
                                    } while (verificarIndice != true);

                                menuEditar--;
                                if (listaHorario[menuEditar] != null) {
                                    System.out.print("Insira o nome do medicamento: ");
                                    nomeMedicamento = sc.nextLine().trim();
                                    do {
                                        validacaoMinutos = false;
                                        validacaoHora = false;
                                        zeroMinutos = false;
                                        System.out.print("Insira somente as horas: ");
                                        hora = sc.nextInt();
                                        sc.nextLine();
                                        if (hora >= 0 && hora <= 23) {
                                            do {
                                                System.out.print("Agora insira os minutos: ");
                                                minuto = sc.nextInt();
                                                sc.nextLine();
                                                if (minuto >= 0 && minuto <= 59) {
                                                    validacaoMinutos = false;
                                                    if (minuto == 0) {
                                                        zeroMinutos = true;
                                                    }
                                                } else {
                                                    System.out.println("Minutos invalidos. Tente Novamente!!");
                                                    validacaoMinutos = true;
                                                }
                                            } while (validacaoMinutos == true);

                                        } else {
                                            System.out.println("Hora invalida. Tente Novamente!!");
                                            validacaoHora = true;
                                        }
                                    } while (validacaoHora == true);

                                    System.out.printf("Coloque uma descrição do medicamento: ");
                                    descricao = sc.nextLine();
                                    System.out.println();

                                    if (zeroMinutos == true) {
                                        relogio = hora + ":" + "00";
                                    } else {
                                        relogio = hora + ":" + minuto;
                                    }
                                    listaHorario[menuEditar] = relogio;
                                    listaDesc[menuEditar] = descricao;
                                    listaNome[menuEditar] = nomeMedicamento;

                                    loopEditar = false;
                                } else {
                                    System.out.println("Numero Invalido. Tente novamente!!");
                                    loopEditar = true;
                                }
                            } while (loopEditar == true);

                            break;
                        case 'R':

                            int menuRemover;
                            boolean verificacaoMenuRemover,verificarIndice2;

                            do {
                                verificacaoMenuRemover = false;

                                do {
                                    System.out.print("Qual medicamento voce deseja remover: ");
                                    menuRemover = sc.nextInt();
                                    sc.nextLine();

                                    if (menuRemover > 0 && menuRemover <= listaHorario.length) {
                                        verificarIndice2 = true;
                                    } else {
                                        System.out.println("Numero Errado. Tente novamente");
                                        verificarIndice2 = false;
                                    }
                                } while (verificarIndice2 != true);

                                menuRemover--;
                                if (listaHorario[menuRemover] != null) {

                                    System.out.println("Removendo medicamento...");

                                    for (int i = menuRemover; i < listaHorario.length - 1; i++) {

                                        listaHorario[i] = listaHorario[i + 1];
                                        listaDesc[i] = listaDesc[i + 1];
                                        listaNome[i] = listaNome[i + 1];
                                    }

                                    listaHorario[listaHorario.length - 1] = null;
                                    listaDesc[listaDesc.length - 1] = null;
                                    listaNome[listaNome.length - 1] = null;

                                    verificacaoMenuRemover = true;

                                } else {
                                    System.out.println("Numero Invalido. tente novamente!!");
                                    verificacaoMenuRemover = false;
                                }
                            } while (verificacaoMenuRemover == false);
                            break;
                        case 'S':
                            System.out.println("Voltando...");
                            break;
                        default:
                            System.out.println("Opção Invalida. Tente novamente!!");
                            break;
                    }
                } while (menuAER != 'S');

                menuInicialMedicamento = 0;
                break;
            default:
                System.out.println("Fechando programa...");
                break;
        }

        sc.close();
    }
}