import br.com.digitalhouse.Estudioso;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Aluno implements Estudioso {

    private String nome;
    private String sobrenome;
    private Integer codigo;
    public List<Curso> matriculadoEm = new ArrayList<>();

    public Aluno(String nome, String sobrenome, Integer codigo){
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.codigo = codigo;
    }

    public List<Curso> getMatriculadoEm() {
        return matriculadoEm;
    }

    public void setMatriculadoEm(Curso matriculadoNoCurso) {
        this.matriculadoEm.add(matriculadoNoCurso);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String estaMatriculado(){
        if(matriculadoEm==null){
            return "O aluno "+nome+" não está matriculado";
        }else{
            if (matriculadoEm.size() == 0) {
                return "o aluno "+nome+" não está matriculado.";
            }else if (matriculadoEm.size() == 1){
                return "o aluno "+nome+" está atualmente matriculado em "+matriculadoEm.size()+" curso.";
            }else{
                return "o aluno "+nome+" está atualmente matriculado em "+matriculadoEm.size()+" cursos.";
            }
        }
    }

    @Override
    public boolean equals(Object objetoAluno) {
        if (this==objetoAluno) return true;
        if(objetoAluno == null || getClass() != objetoAluno.getClass()) return false;
        Aluno aluno = (Aluno) objetoAluno;
        return Objects.equals(codigo, aluno.codigo);
    }

    @Override
    public String toString(){
        String estudioso;
        int numCursos = this.matriculadoEm.size();
        if(numCursos == 0){
            estudioso = "Não Estudioso";
        }else if(numCursos<3){
            estudioso = "Estudioso";
        }else{
            estudioso = "Muito Estudioso";
        }
        return codigo+","+
                nome+" "+sobrenome+","+
                matriculadoEm.size()+","+
                estudioso+" ("+getNivelDeEstudo()+")";
    }

    @Override
    public float getNivelDeEstudo(){
        if(matriculadoEm == null){
            return 0;
        }else{
            int mat = matriculadoEm.size();
            float theRet;
            switch(mat){
                case 0:
                    theRet = 0F;
                    break;
                case 1:
                    theRet = 6.5F;
                    break;
                case 2:
                    theRet = 8.5F;
                    break;
                case 3:
                    theRet = 10F;
                    break;
                default:
                    theRet = 11F;
            }
            return theRet;
        }
    }


}
