public class ProfessorTitular extends Professor{

    private String especialidade;

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public String toString(){
        return this.getNome()+","+
                this.getSobrenome()+","+
                this.getTempoDeCasa()+","+
                this.getCodigo()+","+
                especialidade;
    }
}
