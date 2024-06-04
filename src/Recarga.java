import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Recarga {
    private Date data;
    private float valor;

    public Recarga(Date data, float valor) {
        this.data = data;
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public float getValor() {
        return valor;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DecimalFormat df = new DecimalFormat("R$#0.00");
        return "Recarga{" +
                "data=" + sdf.format(data) +
                ", valor=" + df.format(valor) +
                '}';
    }
}
