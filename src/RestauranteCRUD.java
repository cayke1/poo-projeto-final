import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RestauranteCRUD extends JFrame {
    private ArrayList<Pedido> pedidos;
    private DefaultTableModel pedidoTableModel;
    private JTable pedidoTable;
    private JTextField mesaTextField;
    private JTextField pratoTextField;
    private JTextField descricaoTextField;
    private JTextField valorTextField;
    private JTextField garcomTextField;

    public RestauranteCRUD() {
        // Inicialização da lista de pedidos
        pedidos = new ArrayList<>();

        // Configurações do JFrame
        setTitle("Restaurante CRUD");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Componentes
        JLabel mesaLabel = new JLabel("Número da Mesa:");
        mesaTextField = new JTextField(5);

        JLabel pratoLabel = new JLabel("Nome do Prato:");
        pratoTextField = new JTextField(20);

        JLabel descricaoLabel = new JLabel("Descrição do Pedido:");
        descricaoTextField = new JTextField(30);

        JLabel valorLabel = new JLabel("Valor Total:");
        valorTextField = new JTextField(10);

        JLabel garcomLabel = new JLabel("Nome do Garçom:");
        garcomTextField = new JTextField(20);

        JLabel pedidoLabel = new JLabel("Pedidos Ativos:");

        // Modelo da tabela
        pedidoTableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Mesa", "Prato", "Descrição", "Garçom", "Valor"}
        );

        // Tabela
        pedidoTable = new JTable(pedidoTableModel);
        JScrollPane tableScrollPane = new JScrollPane(pedidoTable);

        JButton adicionarButton = new JButton("Adicionar");
        JButton removerButton = new JButton("Remover");

        // Layout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(mesaLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(mesaTextField, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(pratoLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(pratoTextField)))
                                .addGap(89, 89, 89)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(descricaoLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(descricaoTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(garcomLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(garcomTextField)))
                                .addGap(87, 87, 87)
                                .addComponent(valorLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(valorTextField, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(removerButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(adicionarButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(26, 26, 26))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(419, 419, 419)
                                .addComponent(pedidoLabel)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(tableScrollPane)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(mesaTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(descricaoLabel)
                                                .addComponent(descricaoTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(valorLabel)
                                                .addComponent(valorTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(adicionarButton))
                                        .addComponent(mesaLabel))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(pratoLabel)
                                                .addComponent(pratoTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                .addComponent(garcomTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(garcomLabel))
                                        .addComponent(removerButton))
                                .addGap(38, 38, 38)
                                .addComponent(pedidoLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tableScrollPane, GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE))
        );

        // Adiciona um pedido ao clicar no botão "Adicionar"
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarPedido();
            }
        });

        // Remove um pedido ao clicar no botão "Remover"
        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerPedido();
            }
        });
    }

    private void adicionarPedido() {
        // Obtem os valores dos campos
        int numeroMesa = Integer.parseInt(mesaTextField.getText());
        String nomePrato = pratoTextField.getText();
        String descricaoPedido = descricaoTextField.getText();
        double valorTotal = Double.parseDouble(valorTextField.getText());
        String nomeGarcom = garcomTextField.getText();

        // Cria um novo pedido
        Pedido novoPedido = new Pedido(numeroMesa, nomePrato, descricaoPedido, valorTotal, nomeGarcom);

        // Adiciona o pedido à lista e à tabela
        pedidos.add(novoPedido);
        pedidoTableModel.addRow(new Object[]{numeroMesa, nomePrato, descricaoPedido, nomeGarcom, valorTotal});

        // Limpa os campos
        mesaTextField.setText("");
        pratoTextField.setText("");
        descricaoTextField.setText("");
        valorTextField.setText("");
        garcomTextField.setText("");
    }

    private void removerPedido() {
        int selectedRow = pedidoTable.getSelectedRow();
        if (selectedRow != -1) {
            pedidos.remove(selectedRow);
            pedidoTableModel.removeRow(selectedRow);
        }
    }}