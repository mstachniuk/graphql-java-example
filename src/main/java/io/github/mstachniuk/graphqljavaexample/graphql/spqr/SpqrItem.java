package io.github.mstachniuk.graphqljavaexample.graphql.spqr;

import io.leangen.graphql.annotations.types.GraphQLType;

@GraphQLType(name = "Item")
public class SpqrItem {
    private final String id;
    private final String name;
    private final int amount;
    private final String price;
    private final String currency;
    private final SpqrCompany producer;


    public SpqrItem(String id, String name, int amount, String price, String currency, SpqrCompany producer) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.currency = currency;
        this.producer = producer;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public String getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public SpqrCompany getProducer() {
        return producer;
    }
}
