import java.text.SimpleDateFormat;
import java.util.Date;

public class Chamada {
    private Date data;
    private int duracao;

    public Chamada(Date data, int duracao) {
        this.data = data;
        this.duracao = duracao;
    }

    public Date getData() {
        return data;
    }

    public int getDuracao() {
        return duracao;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "Chamada{" +
                "data=" + sdf.format(data) +
                ", duracao=" + duracao;
    }
}
