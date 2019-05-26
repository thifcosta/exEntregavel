public class ProfessorAdjunto extends Professor{

    private Integer horasDeMonitoria;

    public Integer getHorasDeMonitoria() {
        return horasDeMonitoria;
    }

    public void setHorasDeMonitoria(Integer horasDeMonitoria) {
        this.horasDeMonitoria = horasDeMonitoria;
    }

    @Override
    public String toString(){
        return this.getNome()+","+
                this.getSobrenome()+","+
                this.getTempoDeCasa()+","+
                this.getCodigo()+","+
                horasDeMonitoria;
    }
}
