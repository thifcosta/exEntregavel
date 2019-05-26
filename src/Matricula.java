import java.text.SimpleDateFormat;
import java.util.Date;

public class Matricula {

    private Aluno aluno;
    private Curso curso;
    private Date dataDeMatricula;

    public Matricula(Aluno aluno, Curso curso) {
        this.aluno = aluno;
        this.curso = curso;
        this.dataDeMatricula = new Date();
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Date getDataDeMatricula() {
        return dataDeMatricula;
    }

    public void setDataDeMatricula(Date dataDeMatricula) {
        this.dataDeMatricula = dataDeMatricula;
    }

    SimpleDateFormat sFormat = new SimpleDateFormat("dd/MM/yyy hh:mma");

    @Override
    public String toString() {
        return sFormat.format(dataDeMatricula) +","+
                aluno.getCodigo()+","+
                aluno.getNome()+" "+aluno.getSobrenome() +","+
                curso.getCodigo()+","+
                curso.getNome();
    }
}
