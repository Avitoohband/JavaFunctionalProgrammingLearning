package exercise.entity;

public record Customer(Long id, String name, Integer tier) {

    @Override
    public String toString() {
        return  "Id:" + id + "\n" +
                "Name:" + name + "\n" +
                "Tier:" + tier + "\n";
    }
}


