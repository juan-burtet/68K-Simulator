package progsistemas;

public class Memoria {
    
    String dados[]; // registradores para dados
    String enderecos[]; // registradores para enderecos
    
    public Memoria() {
        dados = new String[8]; // registradores d0 a d7
        enderecos = new String[8]; // registradores a0 a a7
        for (int i = 0; i < 8; ++i) {
            dados[i] = "0000000000000000";
            enderecos[i] = "0000000000000000";
        }
    }
    
    public String getD(int i){
        return dados[i];
    }
    
    public String getA(int i){
        return enderecos[i];
    }
    
    public void setD(int i, String x){
        dados[i] = x;
    }
    
    public void setA(int i, String x){
        enderecos[i] = x;
    }
    
}
