package br.dev.xt.clients.dto.client;

import br.dev.xt.clients.entities.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

public class ClientUpdateEnterpriseDTO {
    @NotNull(message = "Razão Social inválida.")
    @NotEmpty(message = "Razão Social inválida.")
    private String corporateName;

    @NotNull(message = "Nome Fantasia inválido.")
    @NotEmpty(message = "Nome Fantasia inválido.")
    private String fantasyName;

    @NotNull(message = "Documento inválido.")
    @NotEmpty(message = "Documento inválido.")
    private String document;

    @NotNull(message = "Telefone inválido.")
    @Length(min = 10, max = 14, message = "Telefone inválido.")
    private String phone;

    @NotNull(message = "Celular inválido.")
    @Length(min = 11, max = 15, message = "Celular inválido.")
    private String cellphone;

    @NotNull(message = "E-mail inválido.")
    @Email(message = "E-mail inválido.")
    private String email;

    @NotNull(message = "Rua inválida")
    @Length(min = 3, max = 70, message = "Rua inválida.")
    private String street;

    @NotNull(message = "Número inválido.")
    @Length(min = 1, max = 5, message = "Número inválido.")
    private String number;

    @NotNull(message = "Bairro/Distrito inválido.")
    @Length(min = 5, max = 40, message = "Bairro/Distrito inválido.")
    private String neighborhood;

    private String complement;

    @NotNull(message = "CEP inválido.")
    @Length(min = 8, max = 10, message = "CEP inválido.")
    private String code;

    @NotNull(message = "Cidade inválida.")
    @Length(min = 3, max = 80, message = "Cidade inválida.")
    private String city;

    @NotNull(message = "Estado inválido.")
    @Length(min = 2, max = 2, message = "Estado inválido.")
    private String state;

    public ClientUpdateEnterpriseDTO(String corporateName, String fantasyName, String document, String phone, String cellphone, String email, String street, String number, String neighborhood, String complement, String code, String city, String state) {
        this.corporateName = corporateName;
        this.fantasyName = fantasyName;
        this.document = document;
        this.phone = phone;
        this.cellphone = cellphone;
        this.email = email;
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.complement = complement;
        this.code = code;
        this.city = city;
        this.state = state;
    }

    public String getCorporateName() {
        return corporateName;
    }

    public String getFantasyName() {
        return fantasyName;
    }

    public String getDocument() {
        return document;
    }

    public String getPhone() {
        return phone;
    }

    public String getCellphone() {
        return cellphone;
    }

    public String getEmail() {
        return email;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getComplement() {
        return complement;
    }

    public String getCode() {
        return code;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public Client toEntity(Client cli) {
        cli.getPerson().getAddress().setStreet(this.street);
        cli.getPerson().getAddress().setNumber(this.number);
        cli.getPerson().getAddress().setNeighborhood(this.neighborhood);
        cli.getPerson().getAddress().setComplement(this.complement);
        cli.getPerson().getAddress().setCode(this.code);
        cli.getPerson().getAddress().setCity(this.city);
        cli.getPerson().getAddress().setState(this.state);

        cli.getPerson().getContact().setPhone(this.phone);
        cli.getPerson().getContact().setCellphone(this.cellphone);
        cli.getPerson().getContact().setEmail(this.email);

        cli.getPerson().getEnterprise().setCorporateName(this.corporateName);
        cli.getPerson().getEnterprise().setFantasyName(this.fantasyName);
        cli.getPerson().getEnterprise().setDocument(this.document);

        return cli;
    }
}
