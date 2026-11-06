package com.gqs.carrinho;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CarrinhoTest {

    private Carrinho carrinho;

    @BeforeEach
    void setUp() {
        carrinho = new Carrinho();
    }

    @Test
    void carrinhoNovoEstaVazio() {
        assertEquals(0, carrinho.getQuantidade());
        assertEquals(0.0, carrinho.getTotal(), 0.001);
    }

    @Test
    void adicionaProdutosESomaSubtotal() {
        carrinho.adicionar(new Produto("Caneta", 3.50));
        carrinho.adicionar(new Produto("Caderno", 16.50));
        assertEquals(2, carrinho.getQuantidade());
        assertEquals(20.0, carrinho.getSubtotal(), 0.001);
    }

    @Test
    void rejeitaProdutoNulo() {
        assertThrows(IllegalArgumentException.class, () -> carrinho.adicionar(null));
    }

    @Test
    void semDescontoTotalIgualSubtotal() {
        carrinho.adicionar(new Produto("Caderno", 20.0));
        assertEquals(20.0, carrinho.getTotal(), 0.001);
    }

    @Test
    void aplicaDescontoPercentual() {
        carrinho.adicionar(new Produto("Mochila", 200.0));
        carrinho.aplicarDesconto(10);
        assertEquals(180.0, carrinho.getTotal(), 0.001);
        assertEquals(200.0, carrinho.getSubtotal(), 0.001);
    }

    @Test
    void aceitaLimitesDeDesconto() {
        carrinho.adicionar(new Produto("Caderno", 100.0));
        carrinho.aplicarDesconto(0);
        assertEquals(100.0, carrinho.getTotal(), 0.001);
        carrinho.aplicarDesconto(50);
        assertEquals(50.0, carrinho.getTotal(), 0.001);
    }

    @Test
    void rejeitaDescontoForaDoLimite() {
        assertThrows(IllegalArgumentException.class, () -> carrinho.aplicarDesconto(-1));
        assertThrows(IllegalArgumentException.class, () -> carrinho.aplicarDesconto(51));
    }
}
