package api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Item {
    private String name;
    private double price;
    private double price_for_quantity;
    private String quantity_unit;
}
