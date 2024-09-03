package Model;

public class ClienteVip extends Cliente {

  
    public double calcularDesconto(double preco) {
        return preco - (preco * 0.20);
    }
}
