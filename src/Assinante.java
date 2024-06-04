public class Assinante {
    private long cpf;
    private String nome;
    private int numero;
    protected Chamada[] chamadas;
    protected int numChamadas = 0;

    public Assinante(long cpf, String nome, int numero) {
        this.cpf = cpf;
        this.nome = nome;
        this.numero = numero;
        chamadas = new Chamada[4];
    }

    public long getCpf() {
        return cpf;
    }

    @Override
    public String toString() {
        return "Assinante{" +
                "cpf=" + cpf +
                ", nome=" + nome +
                ", numero=" + numero +
                '}';
    }
}
