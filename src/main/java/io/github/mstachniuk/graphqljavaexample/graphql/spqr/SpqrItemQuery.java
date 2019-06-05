package io.github.mstachniuk.graphqljavaexample.graphql.spqr;

import io.github.mstachniuk.graphqljavaexample.company.Company;
import io.github.mstachniuk.graphqljavaexample.item.Item;
import io.github.mstachniuk.graphqljavaexample.item.ItemDataFetcher;
import io.github.mstachniuk.graphqljavaexample.item.ItemService;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLRootContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpqrItemQuery {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemDataFetcher.class);

    @Autowired
    private ItemService itemService;

    @GraphQLQuery(name = "items")
    public List<SpqrItem> getItemsForOrders(@GraphQLContext SpqrOrder order,
                                            @GraphQLRootContext graphql.servlet.GraphQLContext context) {
        LOGGER.info("getItems for Customer " + context.toString());
        return toSpqr(itemService.getItemsByOrderId(order.getId()));
    }

    private List<SpqrItem> toSpqr(List<Item> items) {
        return items.stream()
                .map(item -> new SpqrItem(item.getId(), item.getName(), item.getAmount(), item.getPrice(), item.getCurrency(), toSpqr(item.getProducer())))
                .collect(Collectors.toList());
    }

    private SpqrCompany toSpqr(Company company) {
        return new SpqrCompany(company.getId(), company.getName(), company.getWebsite());
    }
}
