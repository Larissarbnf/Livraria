package Model;

public class ClienteVip extends Cliente {
    public double calcularDesconto(double valorCompra) {
        return valorCompra * 0.15;
    }
}