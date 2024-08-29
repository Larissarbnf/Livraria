package Model;

public class ClienteRegular extends Cliente {
    public double calcularDesconto(double valorCompra) {
        return valorCompra * 0.05; 
    }
}
