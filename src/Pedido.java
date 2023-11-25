public class Pedido {
    private int numeroMesa;
    private String nomePrato;
    private String descricaoPedido;
    private double valorTotal;
    private String nomeGarcom;

    public Pedido(int numeroMesa, String nomePrato, String descricaoPedido, double valorTotal, String nomeGarcom) {
        this.numeroMesa = numeroMesa;
        this.nomePrato = nomePrato;
        this.descricaoPedido = descricaoPedido;
        this.valorTotal = valorTotal;
        this.nomeGarcom = nomeGarcom;
    }

    @Override
    public String toString() {
        return "Mesa: " + numeroMesa +
                ", Prato: " + nomePrato +
                ", Descrição: " + descricaoPedido +
                ", Valor Total: " + valorTotal +
                ", Garçom: " + nomeGarcom;
    }
}