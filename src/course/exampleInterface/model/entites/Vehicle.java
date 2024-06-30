package course.exampleInterface.model.entites;

public class Vehicle implements Comparable<Vehicle> {
    private String model;
    private double price;

    public Vehicle(String model, double price) {
        this.model = model;
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public int compareTo(Vehicle vehicle) {
        // Retorna um valor positivo se o objeto atual eh maior que o objeto verificado
        // Retorna um valor negativo se o objeto atual eh menor que o objeto verificado
        // Retorna o valor 0 se ambos os objetos forem do mesmo tamanho
        // return -getPrice().compareTo(vehicle.getPrice()); Retorna em ordem decresente
        return getModel().compareTo(vehicle.getModel());
    }
}
