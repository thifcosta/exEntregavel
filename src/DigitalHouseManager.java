import java.util.ArrayList;
import java.util.List;

public class DigitalHouseManager {
    public DigitalHouseManager(){
        regCurso("Android",2);
        regCurso("iOS",3);
        regCurso("Full Stack",3);
        regCurso("Marketing Digital",5);

        registrarProfessorTitular("Fabio","Tadashi",1,"Android");
        registrarProfessorTitular("Felipe","Miranda",2,"iOS");
        registrarProfessorTitular("Ana Paula","Silva Pereira",0,"Web Full Stack");
        registrarProfessorTitular("Estevão","Rizzo",1,"Marketing Digital");
        registrarProfessorAdjunto("Gui","Sartori",0,48);
        registrarProfessorAdjunto("Lázaro","Lima dos Santos",2,52);
        registrarProfessorAdjunto("Victor","Torres",0,48);
        registrarProfessorAdjunto("Jeniffer","Santos",3,52);

        registrarAluno("Andre","Pires");
        registrarAluno("Carlos","Ferreira");
        registrarAluno("Cristina","Silva");
        registrarAluno("Francisco","Azevedo");
        registrarAluno("Rafael","Costa");
        registrarAluno("Maicon","Soares");

        alocarProfessores(20001, 101,105);
        alocarProfessores(20002, 102,106);
        alocarProfessores(20003, 103,107);
        alocarProfessores(20004, 104,108);

        matricularAluno(505, 20001);
        matricularAluno(501, 20001);
        matricularAluno(501, 20002);
        matricularAluno(502, 20002);
        matricularAluno(503, 20002);
        matricularAluno(503, 20003);
        matricularAluno(502, 20003);
        matricularAluno(501, 20004);
        matricularAluno(503, 20004);
        matricularAluno(504, 20004);

    }

    private List<Aluno> listaDeAlunos = new ArrayList<>();
    private List<Professor> listaDeProfessores = new ArrayList<>();
    private List<Curso> listaDeCursos = new ArrayList<>();
    private List<Matricula> listaDeMatriculas = new ArrayList<>();

    GoScan goScan = new GoScan();

    private int uniqueAluno = 500;
    private int uniqueProf = 100;
    private int uniqueCurso = 20000;

    private Integer gerarCodigo(String choice){
        Integer numCode = 0;
        switch(choice){
            case "aluno":
                uniqueAluno = uniqueAluno+1;
                numCode = uniqueAluno;
                break;
            case "professor":
                uniqueProf = uniqueProf+1;
                numCode = uniqueProf;
                break;
            case "curso":
                uniqueCurso = uniqueCurso+1;
                numCode = uniqueCurso;
                break;
        }
        return numCode;
    }

    public Aluno getAlunoPorCodigo(Integer codigo){
        boolean foundAluno = false;
        Aluno theOne = null;
        for(Aluno aluno:listaDeAlunos){
            if(aluno.getCodigo().equals(codigo)){
                theOne = aluno;
                foundAluno = true;
            }
        }
        if(foundAluno){
            return theOne;
        }else{
            return null;
        }

    }

    public Curso getCursoPorCodigo(Integer codigo){
        boolean foundCurso = false;
        Curso theCurso = null;
        for(Curso curso:listaDeCursos){
            if(curso.getCodigo().equals(codigo)){
                theCurso = curso;
                foundCurso = true;
            }
        }
        if(foundCurso){
            return theCurso;
        }else{
            return null;
        }
    }

    public Professor getProfessorPorCodigo(Integer codigo){
        Professor foundProfessor = null;
        for(Professor professor:listaDeProfessores){
            if(professor.getCodigo().equals(codigo)){
                foundProfessor = professor;

                // Alertar que o professor foi encontrado
//                System.out.println("Professor "+foundProfessor.getNome()+" encontrado!");

            }
        }
        return foundProfessor;
    }

    private void regCurso(String nome, Integer quantidadeMaximaDeAlunos){
        Curso criarCurso = new Curso();
        criarCurso.setNome(nome);
        criarCurso.setCodigo(gerarCodigo("curso"));
        criarCurso.setQtdMaximaDeAlunos(quantidadeMaximaDeAlunos);
        listaDeCursos.add(criarCurso);
    }

    public String registrarCurso(String nome, Integer quantidadeMaximaDeAlunos){
        Curso criarCurso = new Curso();
        criarCurso.setNome(nome);
        criarCurso.setCodigo(gerarCodigo("curso"));
        criarCurso.setQtdMaximaDeAlunos(quantidadeMaximaDeAlunos);
        listaDeCursos.add(criarCurso);
        System.out.println("Deseja alocar os professores agora? (você pode fazer isso mais tarde)");
        if(getConfirm("Alocar Professores")){
            System.out.println("Digite o código do Professor Titular:");
            int codProfTit = goScan.validInt("Código do Professor Titular");
            System.out.println("Digite o código do Professor Adjunto:");
            int codProfAdj = goScan.validInt("Código do Professor Adjunto");
            System.out.println(alocarProfessores(criarCurso.getCodigo(),codProfTit,codProfAdj));
        }
        return "Curso: "+nome+" criado com sucesso!";
    }

    public void excluirCurso(Integer codigoCurso){
        Curso remover = getCursoPorCodigo(codigoCurso);
        List<Matricula> listRemove = new ArrayList<>();
        if(remover!=null){
            if(remover.listaDeAlunos.size()==0){
                System.out.println("O Curso "+remover.getNome()+" foi removido com sucesso!");
                listaDeCursos.remove(remover);
                System.out.println("Confira a lista de Cursos atualizada:");
                printInfos("cursos");
            }else{
                System.out.println("O Curso "+remover.getNome()+" tem "+remover.listaDeAlunos.size()+" Aluno(s) matriculado(s).\n" +
                        "Ao excluir um Curso, as matrículas de Alunos correspondentes também serão excluídas. Deseja continuar?");
                if(getConfirm("Curso e Matrículas correspondentes")){
                    for(Matricula mat : listaDeMatriculas){
                        if(mat.getCurso() == remover){
                            mat.getAluno().getMatriculadoEm().remove(remover);
                            listRemove.add(mat);
                        }
                    }
                    for(Matricula mat2 : listRemove){
                        listaDeMatriculas.remove(mat2);
                    }
                    listaDeCursos.remove(remover);
                    System.out.println("O Curso "+remover.getNome()+" foi removido com sucesso!\n" +
                            "Confira a lista de Cursos atualizada:");
                    printInfos("cursos");
                }else{
                    System.out.println("OPERAÇÃO CANCELADA!");
                }
            }
        }else{
            System.out.println("O código digitado não corresponde a um curso existente.");
        }
    }

    public String registrarProfessorAdjunto(String nome, String sobrenome, Integer tempoDeCasa, Integer quantidadeDeHoras){
        ProfessorAdjunto novoProfAdjunto = new ProfessorAdjunto();
        novoProfAdjunto.setNome(nome);
        novoProfAdjunto.setSobrenome(sobrenome);
        novoProfAdjunto.setTempoDeCasa(tempoDeCasa);
        novoProfAdjunto.setCodigo(gerarCodigo("professor"));
        novoProfAdjunto.setHorasDeMonitoria(quantidadeDeHoras);
        listaDeProfessores.add(novoProfAdjunto);
        return "Professor adjunto "+nome+" "+sobrenome+" adicionado com sucesso!";
    }

    public String registrarProfessorTitular(String nome, String sobrenome, Integer tempoDeCasa, String especialidade){
        ProfessorTitular novoProfTitular = new ProfessorTitular();
        novoProfTitular.setNome(nome);
        novoProfTitular.setSobrenome(sobrenome);
        novoProfTitular.setTempoDeCasa(tempoDeCasa);
        novoProfTitular.setCodigo(gerarCodigo("professor"));
        novoProfTitular.setEspecialidade(especialidade);
        listaDeProfessores.add(novoProfTitular);
        return "Professor titular "+nome+" "+sobrenome+" adicionado com sucesso!";
    }

    public void excluirProfessor(Integer codigoProfessor){
        Professor foundProf = null;
        String nomeCompleto = "";
        List<Curso> foundAloc = new ArrayList<>();
        List<String> foundAlocName = new ArrayList<>();
        for(Professor professor:listaDeProfessores){
            if(professor.getCodigo() == codigoProfessor){
                foundProf = professor;
                nomeCompleto = foundProf.getNome()+" "+foundProf.getSobrenome();
                for(Curso curso : listaDeCursos){
                    if(curso.getProfessorTitular() == foundProf){
                        foundAloc.add(curso);
                        foundAlocName.add(curso.getNome());
                    }else if(curso.getProfessorAdjunto() == foundProf){
                        foundAloc.add(curso);
                        foundAlocName.add(curso.getNome());
                    }
                }
            }
        }
        if(foundProf==null){
            System.out.println("O código digitado não corresponde a um cadastro de Professor.");
        }else{
            if(foundAloc.size()==0){
                if(getConfirm("Remover Professor")){
                    listaDeProfessores.remove(foundProf);
                    System.out.println("O Professor "+nomeCompleto+" foi removido com sucesso!\n" +
                            "Confira a lista de Professores atualizada:");
                    printInfos("professores");
                }else{
                    System.out.println("OPERAÇÃO CANCELADA!");
                }
            }else{
                System.out.println("O Professor "+nomeCompleto+" está alocado como \"Professor "+foundProf.getClass().getName().substring(9)+"\" no(s) curso(s):\n"+
                        foundAlocName.toString().substring(1,foundAlocName.toString().length()-1)+".\n"+
                        "Ao remover seu cadastro, suas alocações também serão removidas. Deseja continuar?");
                if(getConfirm("Remover Professor e suas Alocações")){
                    for(Curso curso:listaDeCursos){
                        if(curso.getProfessorTitular() == foundProf){
                            curso.setProfessorTitular(null);
                        }else if(curso.getProfessorAdjunto() == foundProf){
                            curso.setProfessorAdjunto(null);
                        }
                    }
                    listaDeProfessores.remove(foundProf);
                    System.out.println("O Professor "+nomeCompleto+" foi removido com sucesso!\n" +
                            "Confira a lista de Professores atualizada:");
                    printInfos("professores");
                }else{
                    System.out.println("OPERAÇÃO CANCELADA!");
                }
            }
        }
    }

    public String registrarAluno(String nome, String sobrenome){
        Aluno novoAluno = new Aluno(nome, sobrenome, gerarCodigo("aluno"));
        listaDeAlunos.add(novoAluno);
        return "O Aluno "+novoAluno.getNome()+" "+novoAluno.getSobrenome()+" registrado com sucesso!";
    }

    public String matricularAluno(Integer codigoAluno, Integer codigoCurso){
        Aluno foundAluno = getAlunoPorCodigo(codigoAluno);
        Curso foundCurso = getCursoPorCodigo(codigoCurso);
        if(foundAluno!=null && foundCurso!=null) {
            if(!foundAluno.matriculadoEm.contains(foundCurso)){
                if (foundCurso.adicionarUmAluno(foundAluno)) {
                    Matricula novaMatricula = new Matricula(foundAluno, foundCurso);
                    listaDeMatriculas.add(novaMatricula);
                    foundAluno.setMatriculadoEm(foundCurso);
                    return "O Aluno "+foundAluno.getNome()+" "+foundAluno.getSobrenome()+" foi adicionado ao Curso " + foundCurso.getNome() + " com sucesso!\n" +
                            "Confira a lista de Matrículas atualizada:\n";
                } else {
                    return "Não foi possível adicionar o aluno "+foundAluno.getNome()+" "+foundAluno.getSobrenome()+" ao Curso " + foundCurso.getNome()+"\n"+
                            "O curso selecionado já está lotado!";
                }
            }else{
                return "O Aluno "+foundAluno.getNome()+" "+foundAluno.getSobrenome()+" já está matriculado no curso "+foundCurso.getNome();
            }
        }else if(foundAluno==null && foundCurso==null){
            return "Não foi possível realizar a matrícula.\nOs códigos do Aluno e do Curso não existem.";
        }else if(foundAluno==null){
            return "Não foi possível realizar a matrícula.\nO código do Aluno não existe.";
        }else{
            return "Não foi possível realizar a matrícula.\nO código do Curso não existe.";
        }
    }

    public void removerMatricula(Integer codigoAluno, Integer codigoCurso){
        Aluno foundAluno = getAlunoPorCodigo(codigoAluno);
        Curso foundCurso = getCursoPorCodigo(codigoCurso);
        String nomeCompleto ="";
        boolean foundMat = false;
        try{
            nomeCompleto = foundAluno.getNome()+" "+foundAluno.getSobrenome();
        }finally {
            if(foundAluno!=null && foundCurso!=null){
                for(Matricula matr : listaDeMatriculas){
                    if(matr.getAluno() == foundAluno && matr.getCurso() == foundCurso){
                        foundMat = true;
                        System.out.println("A matrícula do aluno "+nomeCompleto+" no Curso "+foundCurso.getNome()+" foi encontrada. Deseja removê-la?");
                        if(getConfirm("Remover matrícula")){
                            foundAluno.matriculadoEm.remove(foundCurso);
                            foundCurso.listaDeAlunos.remove(foundAluno);
                            listaDeMatriculas.remove(matr);
                            System.out.println("Matrícula removida com sucesso!");
                            break;
                        }else{
                            System.out.println("OPERAÇÃO CANCELADA!");
                        }
                    }
                }
            }
        }
        if(!foundMat){
            System.out.println("A matrícula não foi encontrada. Confirme os dados e tente novamente.");
        }
    }

    public void removerAluno(Integer codigo){
        Aluno foundAluno = getAlunoPorCodigo(codigo);
        if(foundAluno!=null){
            String nomeCompleto = foundAluno.getNome()+" "+foundAluno.getSobrenome();
            List<Matricula> matriculadoEm = new ArrayList<>();
            List<String> matriculadoEmNomes = new ArrayList<>();
            for(Matricula curMat:listaDeMatriculas){
                int codMat = curMat.getAluno().getCodigo();
                if(codMat == codigo){
                    matriculadoEm.add(curMat);
                    matriculadoEmNomes.add(curMat.getCurso().getNome());
                }
            }
            if(matriculadoEm.size()==0){
                System.out.println("O Aluno "+nomeCompleto+" será excluído do sistema.");
                if(getConfirm("Confirmar exclusão de Aluno")){
                    listaDeAlunos.remove(foundAluno);
                    System.out.println("O Aluno "+nomeCompleto+" foi excluído com sucesso!\n" +
                            "Confira a lista atualizada Alunos:");
                    printInfos("alunos");
                }else{
                    System.out.println("OPERAÇÃO CANCELADA!");
                }
            }else{
                System.out.println("O Aluno "+nomeCompleto+" está matriculado no(s) curso(s):");
                System.out.println(matriculadoEmNomes.toString().substring(1,matriculadoEmNomes.toString().length()-1));
                System.out.println("Ao excluir um Aluno, exclui-se também sua(s) matrícula(s).");
                if(getConfirm("Excluir Aluno e sua(s) Matrícula(s)")){
                    listaDeAlunos.remove(foundAluno);
                    for(Matricula mat : matriculadoEm){
                        mat.getCurso().listaDeAlunos.remove(foundAluno);
                        listaDeMatriculas.remove((mat));
                    }
                    System.out.println("O Aluno "+nomeCompleto+" e suas matrículas foram excluídos com sucesso!\n" +
                            "Confira a lista de Alunos atualizada:");
                    printInfos("alunos");
                }else{
                    System.out.println("\nOPERAÇÃO CANCELADA!");
                }
            }
        }else{
            System.out.println("O código digitado não corresponde a um cadastro de aluno.");
        }
    }

    public String alocarProfessores(Integer codigoCurso, Integer codigoProfessorTitular, Integer codigoProfessorAdjunto){
        Curso curso = getCursoPorCodigo(codigoCurso);
        Professor alocProfTit = getProfessorPorCodigo(codigoProfessorTitular);
        Professor alocProfAdj = getProfessorPorCodigo(codigoProfessorAdjunto);

        List<Object> listCheck = new ArrayList<>();
        listCheck.add(curso);
        listCheck.add(alocProfTit);
        listCheck.add(alocProfAdj);

        String[] nomenclaturas = {"Curso","Professor Titular", "Professor Adjunto"};
        String[] theSoutSing = {"O parâmetro "," não tem um código válido."};
        String[] theSoutPlur = {"Os parâmetros "," não têm um código válido."};
        boolean isPlural = false;

        if(curso!=null && alocProfTit!=null && alocProfAdj!=null){
            curso.setProfessorTitular(alocProfTit);
            curso.setProfessorAdjunto(alocProfAdj);
            return "Professores alocados com sucesso!";
        }else{
            String theSout = null;
            for(int i=0; i<3; i++){
                if(listCheck.get(i)==null){
                    if(theSout == null) {
                        theSout = nomenclaturas[i];
                    }else if(i==2){
                        theSout = theSout+" e "+ nomenclaturas[i];
                        isPlural = true;
                    }else{
                        theSout = theSout+", "+ nomenclaturas[i];
                        isPlural = true;
                    }
                }
            }
            if(isPlural){
                return theSoutPlur[0]+theSout+theSoutPlur[1];
            }else{
                return theSoutSing[0]+theSout+theSoutSing[1];
            }
        }
    }

    private boolean getConfirm(String str){
        System.out.println("Digite \"1\" para confirmar ou \"2\" para cancelar:");
        String theResp = goScan.validString(str);
        boolean gotRight = false;
        boolean whatRet = false;
        while(!gotRight){
            switch(theResp){
                case "1":
                    whatRet = true;
                    gotRight = true;
                    break;
                case "2":
                    gotRight = true;
                    break;
                default:
                    System.out.println("Escolha apenas entre \"1\" e \"2\":");
            }
        }
        return whatRet;
    }

    public void printInfos(String str){
        PrintData printData = new PrintData();
        String listName = "";
        String colTitle = "";
        String choice = "";
        boolean emptyList = true;
        str = str.toLowerCase();
        switch(str){
            case "professores":
                choice = "Professores";
                if(listaDeProfessores!=null && listaDeProfessores.size()>0) {
                    emptyList = false;
                    listName = "LISTA DE PROFESSORES";
                    colTitle = "Código, Nome Completo, Tempo de Casa, Categoria, Cursos Alocados";
                    for (Professor prof : listaDeProfessores) {
                        int numCursos = 0;
                        for(Curso curso : listaDeCursos){
                            if(curso.getProfessorAdjunto() == prof || curso.getProfessorTitular() == prof){
                                numCursos = numCursos+1;
                            }
                        }
                        String retStr = prof.getCodigo() + "," + prof.getNome() + " " + prof.getSobrenome() + "," + prof.getTempoDeCasa() + "," + "Professor "+prof.getClass().getName().substring(9)+","+numCursos;
                        printData.addToList(retStr);
                    }
                }
                break;
            case "alunos":
                choice = "Alunos";
                if(listaDeAlunos!=null && listaDeAlunos.size()>0) {
                    emptyList = false;
                    listName = "LISTA DE ALUNOS";
                    colTitle = "Código, Nome Completo, Cursos Matriculados, Nível de Estudo";
                    for (Aluno aluno : listaDeAlunos) {
                        Integer numCursos;
                        List<String> nomeDosCursos = new ArrayList<>();
                        if(aluno.matriculadoEm.size() == 0){
                            numCursos = 0;
                            nomeDosCursos.add("Não matriculado");
                        }else{
                            numCursos = aluno.getMatriculadoEm().size();
                            for(Curso ccur : aluno.matriculadoEm){
                                nomeDosCursos.add(ccur.getNome());
                            }
                        }
                        String nomeDosCursosStr = nomeDosCursos.toString().replace(","," / ");
                        nomeDosCursosStr = nomeDosCursosStr.substring(1,nomeDosCursosStr.length()-1);
                        String retStr = aluno.getCodigo() + "," + aluno.getNome() + " " + aluno.getSobrenome() + "," + nomeDosCursosStr + ",";
                        String estudioso;
                        if(numCursos == 0){
                            estudioso = "Não Estudioso";
                        }else if(numCursos<3){
                            estudioso = "Estudioso";
                        }else{
                            estudioso = "Muito Estudioso";
                        }
                        printData.addToList(retStr+estudioso);
                    }
                }
                break;
            case "cursos":
                choice = "Cursos";
                if(listaDeCursos!=null && listaDeCursos.size()>0) {
                    emptyList = false;
                    listName = "LISTA DE CURSOS";
                    colTitle = "Código, Nome do Curso, Prof Titular, Prof Adjunto, Qtd Máx Alunos, Alunos Matriculados";
                    for (Curso curso : listaDeCursos) {
                        String retStr = curso.toString();
                        printData.addToList(retStr);
                    }
                }
                break;
            case "matriculas":
                choice = "Matrículas";
                if(listaDeMatriculas!=null && listaDeMatriculas.size()>0) {
                    emptyList = false;
                    listName = "LISTA DE MATRÍCULAS";
                    colTitle = "Data, Código do Aluno, Nome do Aluno, Código do Curso, Nome do Curso";
                    for (Matricula mat : listaDeMatriculas) {
                        String retStr = mat.toString();
                        printData.addToList(retStr);
                    }
                }
                break;
        }
        if(!emptyList){
            printData.printObject(listName, colTitle, 25, 1);
        }else{
            System.out.println("ainda não temos cadastros de " + choice);
        }
    }
}
