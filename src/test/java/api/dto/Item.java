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
    private String currency;
    private double price_for_quantity;
    private String quantity_unit;
    private boolean is_limited;
    private String catalog_number;
    private int outside_id;
    private String name_en;

    public Item(String name, double price, double price_for_quantity, String quantity_unit) {
        this.name = name;
        this.price = price;
        this.price_for_quantity = price_for_quantity;
        this.quantity_unit = quantity_unit;
    }

}
