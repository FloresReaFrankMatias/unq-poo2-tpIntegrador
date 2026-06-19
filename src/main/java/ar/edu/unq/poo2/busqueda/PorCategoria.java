public class PorCategoria implements CriterioBusqueda {

    private Categoria categoria;

    public PorCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean cumple(Item item) {
        return item.getCategoria().equals(categoria);
    }
}