import java.util.Date;
import java.util.Scanner;

public class Telefonia {
    private int numPrePagos = 0;
    private int numPosPagos = 0;
    private final int NUMERO_MAX_ASSINANTES = 2;

    protected PrePago[] prePagos;
    protected PosPago[] posPagos;

    public Telefonia() {
        this.prePagos = new PrePago[NUMERO_MAX_ASSINANTES];
        this.posPagos = new PosPago[NUMERO_MAX_ASSINANTES];
    }

    public void cadastrarAssinante() {
        System.out.println("Selecione o tipo de plano:");
        System.out.println("1: PRÉ-PAGO");
        System.out.println("2: PÓS-PAGO");
        System.out.println("Digite qualquer valor para retornar");
        Scanner input = new Scanner(System.in);
        int opcao = input.nextInt();
        if (opcao == 1) {
            if(this.numPrePagos >= this.NUMERO_MAX_ASSINANTES) {
                System.out.println("Não há mais espaço disponível no vetor de pré-pagos!");
                return;
            }
            System.out.println("Digite o CPF do novo assinante:");
            long cpf = input.nextLong();
            input.nextLine();
            System.out.println("Digite o Nome do novo assinante:");
            String nome = input.nextLine();
            System.out.println("Digite o número do telefone:");
            int numero = input.nextInt();
            input.nextLine();
            this.prePagos[this.numPrePagos] = new PrePago(cpf, nome, numero);
            this.numPrePagos++;
            System.out.println();
            System.out.println("Novo Assinante pré-pago cadastrado com sucesso!");
        } else if (opcao == 2) {
            if(this.numPosPagos >= this.NUMERO_MAX_ASSINANTES) {
                System.out.println("Não há mais espaço disponível no vetor de pós-pagos!");
                return;
            }
            System.out.println("Digite o CPF do novo assinante:");
            long cpf = input.nextLong();
            input.nextLine();
            System.out.println("Digite o Nome do novo assinante:");
            String nome = input.nextLine();
            System.out.println("Digite o número do telefone:");
            int numero = input.nextInt();
            input.nextLine();
            System.out.println("Digite o valor da assinatura:");
            float assinatura = input.nextFloat();
            this.posPagos[this.numPosPagos] = new PosPago(cpf, nome, numero, assinatura);
            this.numPosPagos++;
            System.out.println();
            System.out.println("Novo Assinante pós-pago cadastrado com sucesso!");
        }
    }

    public void listarAssinantes() {
        System.out.println("------LISTA ASSINANTES PRÉ-PAGOS-----");
        for(PrePago assinante : this.prePagos) {
            if(assinante != null) {
                System.out.println(assinante.toString());
            }
        }
        System.out.println("------LISTA ASSINANTES PÓS-PAGOS-----");
        for(PosPago assinante : this.posPagos) {
            if(assinante != null) {
                System.out.println(assinante.toString());
            }
        }
    }

    public void fazerChamada() {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o CPF do assinante: ");
        long cpf = input.nextInt();
        input.nextLine();
        System.out.println("Selecione o tipo de plano:");
        System.out.println("1: PRÉ-PAGO");
        System.out.println("2: PÓS-PAGO");
        System.out.println("Digite qualquer valor para retornar");
        int opcao = input.nextInt();
        if(opcao == 1) {
            PrePago assinanteEncontrado = localizarPrePago(cpf);
            if(assinanteEncontrado != null) {
                System.out.println("Digite a duração da chamada em minutos: ");
                int duracao = input.nextInt();
                input.nextLine();
                assinanteEncontrado.fazerChamada(new Date(), duracao);
            } else {
                System.out.println("Assinante não encontrado!");
            }
        } else if (opcao == 2) {
            PosPago assinanteEncontrado = localizarPosPago(cpf);
            if(assinanteEncontrado != null) {
                System.out.println("Digite a duração da chamada em minutos: ");
                int duracao = input.nextInt();
                input.nextLine();
                assinanteEncontrado.fazerChamada(new Date(), duracao);
            } else {
                System.out.println("Assinante não encontrado!");
            }
        }
    }

    public void fazerRecarga() {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o CPF do assinante: ");
        long cpf = input.nextInt();
        input.nextLine();
        PrePago assinanteEncontrado = localizarPrePago(cpf);
        if(assinanteEncontrado != null) {
            System.out.println("Digite o valor da recarga: ");
            float valor = input.nextFloat();
            input.nextLine();
            assinanteEncontrado.recarregar(new Date(), valor);
        } else {
            System.out.println("Assinante não encontrado!");
        }
    }

    private PrePago localizarPrePago(long cpf) {
        for(PrePago assinantePrePago : this.prePagos) {
            if(assinantePrePago.getCpf() == cpf) {
                return assinantePrePago;
            }
        }
        return null;
    }

    private PosPago localizarPosPago(long cpf) {
        for(PosPago assinantePosPago : this.posPagos) {
            if(assinantePosPago.getCpf() == cpf) {
                return assinantePosPago;
            }
        }
        return null;
    }

    public void imprimirFaturas() {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o mês desejado: ");
        int mes = input.nextInt();
        input.nextLine();
        for(PrePago assinantePrePago : this.prePagos) {
            if(assinantePrePago != null) {
                assinantePrePago.imprimirFatura(mes);
            }
        }
        for(PosPago assinantePosPago : this.posPagos) {
            if(assinantePosPago != null) {
                assinantePosPago.imprimirFatura(mes);
            }
        }
    }

    public static void main(String[] args) {

        Telefonia telefonia = new Telefonia();

        boolean running = true;
        Scanner input = new Scanner(System.in);
        do {
            int opcao;
            System.out.println("-------------------------------------");
            System.out.println("------ DIGITE A OPÇÃO DESEJADA ------");
            System.out.println("-------------------------------------");
            System.out.println("1: CADASTRAR ASSINANTE");
            System.out.println("2: LISTAR ASSINANTES");
            System.out.println("3: FAZER CHAMADA");
            System.out.println("4: FAZER RECARGA");
            System.out.println("5: IMPRIMIR FATURAS");
            System.out.println("6: SAIR");
            System.out.println("--------------------------------------");
            opcao = input.nextInt();

            switch (opcao) {
                case 1:
                    telefonia.cadastrarAssinante();
                    break;
                case 2:
                    telefonia.listarAssinantes();
                    break;
                case 3:
                    telefonia.fazerChamada();
                    break;
                case 4:
                    telefonia.fazerRecarga();
                    break;
                case 5:
                    telefonia.imprimirFaturas();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while(running);
    }
}
