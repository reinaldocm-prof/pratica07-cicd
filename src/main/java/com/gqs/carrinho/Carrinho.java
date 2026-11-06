package com.gqs.carrinho;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    public static final double VALOR_FRETE = 15.0;
    public static final double MINIMO_FRETE_GRATIS = 200.0;

    private final List<Produto> itens = new ArrayList<>();
    private int desconto = 0;

    public void adicionar(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo");
        }
        itens.add(produto);
    }

    public int getQuantidade() {
        return itens.size();
    }

    public double getSubtotal() {
        double soma = 0.0;
        for (Produto p : itens) {
            soma += p.getPreco();
        }
        return soma;
    }

    public void aplicarDesconto(int percentual) {
        if (percentual < 0 || percentual > 50) {
            throw new IllegalArgumentException("Desconto deve estar entre 0 e 50");
        }
        this.desconto = percentual;
    }

    public double getTotal() {
        return getSubtotal() * (100 - desconto) / 100.0;
    }

    public double getFrete() {
        if (itens.isEmpty() || getTotal() >= MINIMO_FRETE_GRATIS) {
            return 0.0;
        }
        return VALOR_FRETE;
    }

    public double getTotalComFrete() {
        return getTotal() + getFrete();
    }
}
