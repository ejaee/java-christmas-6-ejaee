package christmas.domain.benefit;

public record Benefit(String name, long discount) {

    @Override
    public String toString() {
        return String.format(name + ": " + "%,d원", discount);
    }
}
