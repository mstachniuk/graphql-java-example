package io.github.mstachniuk.graphqljavaexample;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.PropertyDataFetcher;
import io.github.mstachniuk.graphqljavaexample.item.ItemService;

@Component
public class ItemDataFetcher extends PropertyDataFetcher<List<Item>> {

	@Autowired
	private ItemService itemService;

	public ItemDataFetcher() {
		super("items");
	}

	@Override
	public List<Item> get(DataFetchingEnvironment environment) {
		Order source = environment.getSource();
		String orderId = source.getId();
		return itemService.getItemsByOrderId(orderId);
	}
}
