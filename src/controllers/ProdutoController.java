package controllers;

import java.util.Scanner;
import java.util.UUID;

import entities.Produto;
import repositories.ProdutoRepository;

public class ProdutoController {
	private Scanner scanner = new Scanner(System.in);

	public void cadastrarProduto() {
		try {
			System.out.println("\nCadastro de Produtos: \n");
			System.out.print("Nome do Produto...:");
			var nome = scanner.nextLine();
			System.out.print("Preço.............:");
			var preco = Double.parseDouble(scanner.nextLine());
			System.out.print("Quantidade:.....:");
			var quantidade =  Integer.parseInt(scanner.nextLine());
			
			var produto = new Produto(UUID.randomUUID(), nome, preco, quantidade);
			var produtoRepository = new ProdutoRepository();
			produtoRepository.inserir(produto);
		} catch (Exception e) {
			System.out.println("\nFalha ao cadastrar o produto \nErro:\n" + e.getMessage());
			System.out.println("Detalhes:" + e.getStackTrace());
		}
	}
	
	public void atualizarProduto() {
		try {
			System.out.println("Atualização de Produto");
			System.out.println("Informe o id do Produto:");
			
			var id = UUID.fromString(scanner.nextLine());
			
			var produtoRepository = new ProdutoRepository();
			var produto = produtoRepository.obterPorId(id);
			
			if (produto != null) {
				System.out.println("\nInformações do Produto");
				System.out.println("id: " + produto.getId());
				System.out.println("nome: " + produto.getNome());
				System.out.println("preco: " + produto.getPreco());
				System.out.println("quantidade: " + produto.getQuantidade());
				
				System.out.println("Altere o nome");
				produto.setNome(scanner.nextLine());
				System.out.println("Altere o Preco");
				produto.setPreco(Double.parseDouble(scanner.nextLine()));
				System.out.println("Altere a Quantidade");
				produto.setQuantidade(Integer.parseInt(scanner.nextLine()));
				
				produtoRepository.atualizar(produto);
			}else {
				System.out.println("Produto não encontrado. Verifique o ID informado");
			}
		} catch (Exception e) {
			System.out.println("\nFalha ao atualizar o produto");
			System.out.println(e.getMessage());
		}
	}
	public void excluirProduto() {
		try {
			System.out.println("\nExclusão de Produto");
			System.out.println("Informe o id do Produto:");
			var id = UUID.fromString(scanner.nextLine());
			
			var produtoRepository = new ProdutoRepository();
			var produto = produtoRepository.obterPorId(id);
			
			if (produto != null) {
				System.out.println("Informações do Produto");
				System.out.println("id: " + produto.getId());
				System.out.println("nome: " + produto.getNome());
				System.out.println("preco: " + produto.getPreco());
				System.out.println("quantidade: " + produto.getQuantidade());
				
				produtoRepository.excluir(produto.getId());;
			}else {
				System.out.println("Produto não encontrado. Verifique o ID informado");
			}
		} catch (Exception e) {
			System.out.println("\nFalha ao atualizar o produto");
			System.out.println(e.getMessage());
		}
	}
	
	public void consultarProdutos(){
		try {
			System.out.println("\nConsulta de Produtos:");
			var produtoRepository = new ProdutoRepository();
			var lista = produtoRepository.consultar();
			
			for (Produto produto : lista) {
				System.out.println("Id:"+produto.getId());
				System.out.println("Nome:"+produto.getNome());
				System.out.println("Preco:"+produto.getPreco());
				System.out.println("Quantidade:"+produto.getQuantidade());
				System.out.println("");
			}
			
		} catch (Exception e) {
			System.out.println("\nFalha ao consultar Produtos");
			System.out.println(e.getMessage());
			// TODO: handle exception
		}
	}
	
}
