import java.util.Calendar;
import java.util.Date;

public class PrePago extends Assinante {
    protected Recarga[] recargas;
    private int numRecargas = 0;
    private float creditos = 0;
    private final float VALOR_CHAMADA = 1.45f;

    public PrePago(long cpf, String nome, int numero) {
        super(cpf, nome, numero);
        this.recargas = new Recarga[4];
    }

    public void fazerChamada(Date data, int duracao) {
        if(this.numChamadas <= this.chamadas.length) {
            float custo = duracao * VALOR_CHAMADA;
            if(this.creditos >= custo) {
                this.chamadas[numChamadas] = new Chamada(data, duracao);
                this.creditos -= custo;
                this.numChamadas++;
                System.out.println("Ligação efetuada com sucesso!");
            } else {
                System.out.println("Saldo insuficiente para efetuar ligação.");
            }
        } else {
            System.out.println("Não há mais espaço disponível no vetor de chamadas!");
        }
    }

    public void recarregar(Date data, float valor) {
        if(this.numRecargas <= this.recargas.length) {
            this.creditos += valor;
            this.recargas[numRecargas] = new Recarga(data, valor);
            this.numRecargas++;
            System.out.println("Recarga de " + valor + " efetuada com sucesso.");
        } else {
            System.out.println("Não há mais espaço disponível no vetor de recargas!");
        }
    }

    public void imprimirFatura(int mes) {
        System.out.println();
        System.out.println(this.toString());
        double totalValorLigacoes = 0;
        double totalValorRecargas = 0;

        Calendar calendar = Calendar.getInstance();
        System.out.println("------------ CHAMADAS ------------");
        for(Chamada chamada : this.chamadas) {
            if(chamada == null) {
                break;
            }
            calendar.setTime(chamada.getData());
            int mesAtual = calendar.get(Calendar.MONTH);
            if(mesAtual == mes-1) {
                float valorChamadaAtual = chamada.getDuracao() * VALOR_CHAMADA;
                System.out.printf(chamada.toString() + ", valor=R$%.2f}\n", valorChamadaAtual);
                totalValorLigacoes += valorChamadaAtual;
            }
        }
        System.out.println("-------------- RECARGAS --------------");
        for(Recarga recarga : this.recargas) {
            if(recarga == null) {
                break;
            }
            calendar.setTime(recarga.getData());
            int mesAtual = calendar.get(Calendar.MONTH);
            if(mesAtual == mes-1) {
                System.out.println(recarga.toString());
                totalValorRecargas += recarga.getValor();
            }
        }
        System.out.println("--------------- RESUMO -------------");
        System.out.printf("Total gasto em ligações: R$%.2f \n", totalValorLigacoes);
        System.out.printf("Total gasto em Recargas: R$%.2f \n", totalValorRecargas);
        System.out.printf("Saldo atual: R$%.2f \n", this.creditos);
        System.out.println();
        System.out.println("======================================");
    }
}
