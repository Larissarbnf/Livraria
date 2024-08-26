package Interfaces;

import Model.Cliente;
import Model.ClienteRegular;
import Model.ClienteVip;

public abstract class ClienteFactory {
    public abstract Cliente createCliente();

    public static ClienteFactory getClienteFactory(String tipo) {
        if ("VIP".equalsIgnoreCase(tipo)) {
            return new ClienteVIPFactory();
        } else if ("Regular".equalsIgnoreCase(tipo)) {
            return new ClienteRegularFactory();
        }
        return null;
    }
}

class ClienteVIPFactory extends ClienteFactory {
    @Override
    public Cliente createCliente() {
        return new ClienteVip();
    }
}

class ClienteRegularFactory extends ClienteFactory {
    @Override
    public Cliente createCliente() {
        return new ClienteRegular();
    }
}