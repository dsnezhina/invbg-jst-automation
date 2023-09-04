package api.dto;

import lombok.Getter;

@Getter
public class Client {

    private String name; //Фирма ЕООД
    private String town; //София
    private String address; //Славееви гори 21
    private String bulstat; //112233445566
    private boolean is_reg_vat; //false
    private String vat_number; //BG112233445566
    private String mol; //Георги Йорданов
    private boolean is_person; //false
    private int egn; //8210129421
    private String country; //Германия
    private String code; //Client-79832
    private String office; //Горни Брод, централен офис
    private String name_en; //Firma EOOD
    private String town_en; //Sofia
    private String address_en; //Slaveevi gori 21
    private String mol_en; //Georgi Jordanov
    private String country_en; //Germany

    public Client(String name, String town, String address, String bulstat, String mol) {
        this.name = name;
        this.town = town;
        this.address = address;
        this.bulstat = bulstat;
        this.mol = mol;
    }
}
