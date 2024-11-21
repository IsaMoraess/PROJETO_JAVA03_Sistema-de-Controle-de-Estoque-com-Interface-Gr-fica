import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// INTERFACE GRAFICA PARA GERENCIAR O ESTOQUE
public class EstoqueGUI extends JFrame {
    private MovimentacaoEstoque estoque;

    // CAMPOS PARA INSERIR DADOS
    private JTextField txtId, txtNome, txtQuantidade, txtPreco;

    // AREA PARA MOSTRAR LISTA DE PRODUTOS
    private JTextArea areaProdutos;

    public EstoqueGUI() {
        estoque = new MovimentacaoEstoque();

        // CONFIGURACOES DA JANELA
        setTitle("Controle de Estoque");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // PAINEL SUPERIOR: FORMULARIO DE PRODUTO
        JPanel panelForm = new JPanel(new GridLayout(5, 2, 5, 5));
        panelForm.setBorder(BorderFactory.createTitledBorder("Cadastrar Produto"));
        txtId = new JTextField();
        txtNome = new JTextField();
        txtQuantidade = new JTextField();
        txtPreco = new JTextField();

        panelForm.add(new JLabel("ID:"));
        panelForm.add(txtId);
        panelForm.add(new JLabel("Nome:"));
        panelForm.add(txtNome);
        panelForm.add(new JLabel("Quantidade:"));
        panelForm.add(txtQuantidade);
        panelForm.add(new JLabel("Preço:"));
        panelForm.add(txtPreco);

        JButton btnCadastrar = new JButton("Cadastrar Produto");
        panelForm.add(btnCadastrar);

        // PAINEL INFERIOR: LISTAGEM DE PRODUTOS
        areaProdutos = new JTextArea();
        areaProdutos.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaProdutos);

        // BOTOES PARA OUTRAS OPERACOES
        JPanel panelBotoes = new JPanel();
        JButton btnListar = new JButton("Listar Produtos");
        JButton btnRemover = new JButton("Remover Produto");
        JButton btnAtualizar = new JButton("Atualizar Quantidade");

        panelBotoes.add(btnListar);
        panelBotoes.add(btnRemover);
        panelBotoes.add(btnAtualizar);

        // ADICIONANDO COMPONENTES NA JANELA
        add(panelForm, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotoes, BorderLayout.SOUTH);

        // EVENTOS DOS BOTOES
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(txtId.getText());
                String nome = txtNome.getText();
                int quantidade = Integer.parseInt(txtQuantidade.getText());
                double preco = Double.parseDouble(txtPreco.getText());

                Produto produto = new Produto(id, nome, quantidade, preco);
                estoque.cadastrarProduto(produto);
                JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
                limparCampos();
            }
        });

        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                areaProdutos.setText("Produtos no Estoque:\n");
                for (Produto produto : estoque.listarProdutos()) {
                    areaProdutos.append(produto + "\n");
                }
            }
        });

        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o ID do produto a remover:"));
                if (estoque.removerProduto(id)) {
                    JOptionPane.showMessageDialog(null, "Produto removido com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Produto não encontrado!");
                }
            }
        });

        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o ID do produto:"));
                int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade (+ para entrada, - para saída):"));
                if (estoque.atualizarQuantidade(id, quantidade)) {
                    JOptionPane.showMessageDialog(null, "Quantidade atualizada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Produto não encontrado!");
                }
            }
        });
    }

    // METODO PARA LIMPAR CAMPOS DO FORMULARIO
    private void limparCampos() {
        txtId.setText("");
        txtNome.setText("");
        txtQuantidade.setText("");
        txtPreco.setText("");
    }

    // METODO PRINCIPAL PARA INICIAR O PROGRAMA
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EstoqueGUI().setVisible(true);
        });
    }
}
