package christmas.domain.benefit;

import christmas.constant.Constants;

public record Benefit(String name, long discount) {

    @Override
    public String toString() {
        return String.format(name + Constants.BENEFIT_INFO_TEMPLATE, discount);
    }
}
