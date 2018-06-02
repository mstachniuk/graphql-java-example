package io.github.mstachniuk.graphqljavaexample.item;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.PropertyDataFetcher;
import io.github.mstachniuk.graphqljavaexample.order.Order;

@Component
public class ItemDataFetcher extends PropertyDataFetcher<List<Item>> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemDataFetcher.class);

	@Autowired
	private ItemService itemService;

	public ItemDataFetcher() {
		super("items");
	}

	@Override
	public List<Item> get(DataFetchingEnvironment environment) {
		Order source = environment.getSource();
		String orderId = source.getId();
		LOGGER.trace("ItemDataFetcher get by {}", orderId);
		return itemService.getItemsByOrderId(orderId);
	}
}
