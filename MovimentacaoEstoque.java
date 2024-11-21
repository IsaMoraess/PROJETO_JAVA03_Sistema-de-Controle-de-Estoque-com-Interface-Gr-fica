import java.util.ArrayList;
import java.util.List;

// CLASSE QUE GERENCIA O ESTOQUE
public class MovimentacaoEstoque {
    private List<Produto> produtos;

    // CONSTRUTOR
    public MovimentacaoEstoque() {
        produtos = new ArrayList<>();
    }

    // METODO PARA CADASTRAR PRODUTO
    public void cadastrarProduto(Produto produto) {
        produtos.add(produto);
    }

    // METODO PARA LISTAR PRODUTOS
    public List<Produto> listarProdutos() {
        return produtos;
    }

    // METODO PARA REMOVER PRODUTO
    public boolean removerProduto(int id) {
        return produtos.removeIf(produto -> produto.getId() == id);
    }

    // METODO PARA ATUALIZAR QUANTIDADE
    public boolean atualizarQuantidade(int id, int quantidade) {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                produto.setQuantidade(produto.getQuantidade() + quantidade);
                return true;
            }
        }
        return false;
    }
}
