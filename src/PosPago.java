import java.util.Calendar;
import java.util.Date;

public class PosPago extends Assinante {
    private float assinatura;
    private final float VALOR_CHAMADA = 1.04f;

    public PosPago(long cpf, String nome, int numero, float assinatura) {
        super(cpf, nome, numero);
        this.assinatura = assinatura;
    }

    public void fazerChamada(Date data, int duracao) {
        if(this.numChamadas <= this.chamadas.length) {
                this.chamadas[numChamadas] = new Chamada(data, duracao);
                this.numChamadas++;
                System.out.println("Ligação efetuada com sucesso!");
        } else {
            System.out.println("Não há mais espaço disponível no vetor de chamadas!");
        }
    }

    public void imprimirFatura(int mes) {
        System.out.println();
        System.out.println(this.toString());
        double totalValorLigacoes = 0;

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
        System.out.println("--------------- RESUMO -------------");
        System.out.printf("Total da sua fatura: R$%.2f \n", totalValorLigacoes + this.assinatura);
        System.out.println();
        System.out.println("=====================================");
    }
}
