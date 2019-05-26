import br.com.digitalhouse.DHException;
import br.com.digitalhouse.DigitalException;
import br.com.digitalhouse.OtherException;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {

        DigitalHouseManager digitalHouseManager = new DigitalHouseManager();
        DigitalException digitalException = new DigitalException();
        PrintData printData = new PrintData();
        GoScan goScan = new GoScan();

        String menuTxt = "\n>>> Menu de Alunos <<<\n" +
                "Digite \"1\" para exibir todos os Alunos registrados\n" +
                "Digite \"2\" para registrar um novo Aluno\n"+
                "Digite \"3\" para excluir um Aluno pelo código\n"+
                "Digite \"DH\" para testar um Aluno para DHException\n"+
                "\n>>> Menu de Professores <<<\n" +
                "Digite \"4\" para exibir todos os Professores registrados\n" +
                "Digite \"5\" para registrar um Professor Titular\n" +
                "Digite \"6\" para registrar um Professor Aadjunto\n" +
                "Digite \"7\" para alocar Professores em um curso\n" +
                "Digite \"8\" para excluir um Professor pelo código\n" +
                "\n>>> Menu de Cursos <<<\n" +
                "Digite \"9\" para exibir todos os Cursos registrados\n" +
                "Digite \"10\" para registrar um novo Curso\n"+
                "Digite \"11\" para excluir um Curso pelo código\n" +
                "\n>>> Menu de Matrículas <<<\n" +
                "Digite \"12\" para exibir todas as Matrículas\n" +
                "Digite \"13\" para matricular um Aluno em um Curso\n" +
                "Digite \"14\" para excluir uma Matrícula\n" +
                "\nPara sair, sigite \"sair\"";

        System.out.println("BEM VINDO AO SISTEMA!!");
        String input = "";
        while(!input.toLowerCase().equals("sair")){
            System.out.println(menuTxt);
            input = goScan.validString("Menu");
            input = input.toLowerCase();
            switch(input){
                case "1":
                    digitalHouseManager.printInfos("alunos");
                    break;
                case "2":
                    System.out.println("Digite o nome do novo Aluno:");
                    String aNome = goScan.validString("Nome do Aluno");
                    System.out.println("Digite o sobrenome do novo Aluno:");
                    String aSobrenome = goScan.validString("Sobrenome do Aluno");
                    System.out.println(digitalHouseManager.registrarAluno(aNome,aSobrenome));
                    break;
                case "3":
                    System.out.println("Digite o código do aluno a ser excluído:");
                    Integer aExc = goScan.validInt("Código do Aluno");
                    digitalHouseManager.removerAluno(aExc);
                    break;
                case "dh":
                    System.out.println("Digite o código do Aluno que deseja aplicar \"Digital Exception\":");
                    Integer gotCode = goScan.validInt("Código do Aluno");
                    Aluno gotAluno = digitalHouseManager.getAlunoPorCodigo(gotCode);
                    if(gotAluno!=null){
                        try{
                            digitalException.matriculaDH(gotAluno);
                        } catch (DHException e){
                            System.out.println("O aluno deve vir ao co-learning");
                        } catch (OtherException e2){
                            System.out.println("Falar com os alunos");
                        }
                    }else{
                        System.out.println("O código digitado não corresponde a um aluno cadastrado.");
                    }
                    break;
                case "4":
                    digitalHouseManager.printInfos("professores");
                    break;
                case "5":
                    System.out.println("Digite o nome do novo Professor Titular:");
                    String pTNome = goScan.validString("Nome do Professor Titular");
                    System.out.println("Digite o sobrenome do novo Professor Titular:");
                    String pTSobrenome = goScan.validString("Sobrenome do Professor Titular");
                    System.out.println("Digite o tempo de casa do novo Professor Titular:");
                    Integer pTTempo = goScan.validInt("Tempo de casa do Professor Titular");
                    System.out.println("Digite a especialidade do novo Professor Titular:");
                    String pTEspecialidade = goScan.validString("Especialidade do Professor Titular");
                    System.out.println(digitalHouseManager.registrarProfessorTitular(pTNome,pTSobrenome,pTTempo,pTEspecialidade));
                    break;
                case "6":
                    System.out.println("Digite o nome do novo Professor Adjunto:");
                    String pANome = goScan.validString("Nome do Professor Adjunto");
                    System.out.println("Digite o sobrenome do novo Professor Adjunto:");
                    String pASobrenome = goScan.validString("Sobrenome do Professor Adjunto");
                    System.out.println("Digite o tempo de casa do novo Professor Adjunto:");
                    Integer pATempo = goScan.validInt("Tempo de casa do Professor Adjunto");
                    System.out.println("Digite a especialidade do novo Professor Adjunto:");
                    Integer pAHoras = goScan.validInt("Especialidade do Professor Adjunto");
                    System.out.println(digitalHouseManager.registrarProfessorAdjunto(pANome,pASobrenome,pATempo,pAHoras));
                    break;
                case "7":
                    System.out.println("Digite o código do Curso:");
                    Integer cCodigo = goScan.validInt("Código do Curso");
                    System.out.println("Digite o código do Professor Titular:");
                    Integer cProfTit = goScan.validInt("Código do Professor Titular");
                    System.out.println("Digite o código do Professor Adjunto:");
                    Integer cProfAdj = goScan.validInt("Código do Professor Adjunto");
                    digitalHouseManager.alocarProfessores(cCodigo, cProfTit, cProfAdj);
                    break;
                case "8":
                    System.out.println("Digite o código do Professor a ser excluído:");
                    Integer pCode = goScan.validInt("Código do Professor");
                    digitalHouseManager.excluirProfessor(pCode);
                    break;
                case "9":
                    digitalHouseManager.printInfos("cursos");
                    break;
                case "10":
                    System.out.println("Digite o nome do novo Curso:");
                    String cNome = goScan.validString("Nome do Curso");
                    System.out.println("Digite a quantidade máxima de Alunos do novo Curso:");
                    Integer cMax = goScan.validInt("Nome do Curso");
                    System.out.println(digitalHouseManager.registrarCurso(cNome, cMax));
                    break;
                case "11":
                    System.out.println("Digite o código do curso a ser excluído:");
                    Integer cCod = goScan.validInt("Código do curso!");
                    digitalHouseManager.excluirCurso(cCod);
                    break;
                case "12":
                    digitalHouseManager.printInfos("matriculas");
                    break;
                case "13":
                    System.out.println("Digite o código do Aluno que deseja matricular:");
                    Integer aCodd = goScan.validInt("Código do Aluno");
                    System.out.println("Digite o código do Curso que deseja efetuar a matrícula:");
                    Integer cCodd = goScan.validInt("Código do Curso");
                    System.out.println(digitalHouseManager.matricularAluno(aCodd,cCodd));
                    break;
                case "14":
                    System.out.println("Digite o código do Aluno que deseja cancelar a matrícula:");
                    Integer aaCodigo = goScan.validInt("Código do Aluno");
                    System.out.println("Digite o código do Curso que deseja cancelar a matrícula");
                    Integer ccCodigo = goScan.validInt("Código do Curso");
                    digitalHouseManager.removerMatricula(aaCodigo,ccCodigo);
                    break;
                case "sair":
                    break;
                default:
                    System.out.println("Este campo só aceita as opções do Menu");
            }
            if(!input.equals("sair")){
                System.out.println("\nDigite qualquer tecla para exibir o Menu novamente,\n" +
                        "ou \"sair\" para encerrar o programa.");
                Scanner scn = new Scanner(System.in);
                input = scn.nextLine();
            }
        }
        System.out.println("Obrigado pela visita! Até a próxima!");
    }
}
