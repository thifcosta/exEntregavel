import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Curso {

    private String nome;
    private Integer codigo;
    private Professor professorTitular = null;
    private Professor professorAdjunto = null;
    private Integer qtdMaximaDeAlunos = null;
    public List<Aluno> listaDeAlunos = new ArrayList<>();

    public Curso() {
    }

    public Curso(String nome, Integer codigo, ProfessorTitular professorTitular, ProfessorAdjunto professorAdjunto, Integer qtdMaximaDeAlunos) {
        this.nome = nome;
        this.codigo = codigo;
        this.professorTitular = professorTitular;
        this.professorAdjunto = professorAdjunto;
        this.qtdMaximaDeAlunos = qtdMaximaDeAlunos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Professor getProfessorTitular() {
        return professorTitular;
    }

    public void setProfessorTitular(Professor professorTitular) {
        this.professorTitular = professorTitular;
    }

    public Professor getProfessorAdjunto() {
        return professorAdjunto;
    }

    public void setProfessorAdjunto(Professor professorAdjunto) {
        this.professorAdjunto = professorAdjunto;
    }

    public Integer getQtdMaximaDeAlunos() {
        return qtdMaximaDeAlunos;
    }

    public void setQtdMaximaDeAlunos(Integer qtdMaximaDeAlunos) {
        this.qtdMaximaDeAlunos = qtdMaximaDeAlunos;
    }

    public List<Aluno> getListaDeAlunos() {
        return listaDeAlunos;
    }

    public void setListaDeAlunos(List<Aluno> listaDeAlunos) {
        this.listaDeAlunos = listaDeAlunos;
    }

    @Override
    public boolean equals(Object objetoCurso) {
        if (this == objetoCurso) return true;
        if (objetoCurso == null || getClass() != objetoCurso.getClass()) return false;
        Curso curso = (Curso) objetoCurso;
        return Objects.equals(codigo, curso.codigo);
    }

    public Boolean adicionarUmAluno(Aluno umAluno) {
        if (listaDeAlunos.size() < qtdMaximaDeAlunos) {
            listaDeAlunos.add(umAluno);
            return true;
        } else {
            return false;
        }
    }

    public void excluirUmAluno(Aluno umAluno) {
        for (Aluno aluno : listaDeAlunos) {
            if (aluno.getCodigo() == umAluno.getCodigo()) {
                listaDeAlunos.remove(aluno);
            }
        }
    }

    @Override
    public String toString() {
        String profTit = null;
        String profAdj = null;
        if(professorTitular!=null){
            profTit = professorTitular.getNome()+" "+professorTitular.getSobrenome();
        }
        if(professorAdjunto!=null){
            profAdj = professorAdjunto.getNome()+" "+professorAdjunto.getSobrenome();
        }
        return
                codigo +","+
                nome+","+
                profTit +","+
                profAdj +","+
                qtdMaximaDeAlunos +","+
                listaDeAlunos.size();
    }

}