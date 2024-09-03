package Model;

public class ClienteRegular extends Cliente {

    public double calcularDesconto(double preco) {
        return preco - (preco * 0.05); 
    }
}
