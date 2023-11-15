package christmas.domain.benefit;

import java.util.List;

public record Benefits(List<Benefit> benefits) {

    public long getTotalDiscount() {
        return benefits.stream()
                .mapToLong(Benefit::discount)
                .sum();
    }
}
