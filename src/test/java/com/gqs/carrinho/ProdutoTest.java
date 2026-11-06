package com.gqs.carrinho;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ProdutoTest {

    @Test
    void criaProdutoValido() {
        Produto p = new Produto("Caneta", 3.50);
        assertEquals("Caneta", p.getNome());
        assertEquals(3.50, p.getPreco(), 0.001);
    }

    @Test
    void aceitaPrecoZero() {
        assertEquals(0.0, new Produto("Brinde", 0).getPreco(), 0.001);
    }

    @Test
    void rejeitaNomeNulo() {
        assertThrows(IllegalArgumentException.class, () -> new Produto(null, 1));
    }

    @Test
    void rejeitaNomeEmBranco() {
        assertThrows(IllegalArgumentException.class, () -> new Produto("   ", 1));
    }

    @Test
    void rejeitaPrecoNegativo() {
        assertThrows(IllegalArgumentException.class, () -> new Produto("Caneta", -0.01));
    }
}
