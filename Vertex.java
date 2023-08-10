public class Vertex<V> {
    private V data;

    public Vertex(V data) {
        this.data = data;
    }

    public V getData() {
        return data;
    }

    public void setData(V data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
