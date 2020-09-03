package challenge;

import java.util.ArrayList;
import java.util.List;

public class Estacionamento {

	List<Carro> estacionamento = new ArrayList<Carro>();

    public void estacionar(Carro carro) {
    	if (carrosEstacionados() >= 10) {
    		for (int i = 0; i < carrosEstacionados(); i++) {
				Motorista motorista = estacionamento.get(i).getMotorista();
				if (motorista.getIdade() <= 55) {
					estacionamento.remove(i);
					estacionamento.add(carro);
					break;
				} 
			}
		} else {
			estacionamento.add(carro);
		}
    	
    	if (!carroEstacionado(carro))
    		throw new EstacionamentoException("Estacionamento lotado");
    }
    
    public int carrosEstacionados() {
        return estacionamento.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return estacionamento.contains(carro);
    }
}
