import java.util.Objects;
public abstract class Professor {
    private String nome;
    private String sobrenome;
    private Integer tempoDeCasa;
    private Integer codigo;

    public Professor(){}

    public Professor(String nome, String sobrenome, Integer tempoDeCasa, Integer codigo) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.tempoDeCasa = tempoDeCasa;
        this.codigo = codigo;
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

    public Integer getTempoDeCasa() {
        return tempoDeCasa;
    }

    public void setTempoDeCasa(Integer tempoDeCasa) {
        this.tempoDeCasa = tempoDeCasa;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    @Override
    public boolean equals(Object objetoProfessor) {
        if (this==objetoProfessor) return true;
        if(objetoProfessor == null || getClass() != objetoProfessor.getClass()) return false;
        Professor professor = (Professor) objetoProfessor;
        return Objects.equals(codigo, professor.codigo);
    }
}
