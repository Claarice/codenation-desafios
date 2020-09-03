package br.com.codenation.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.codenation.model.OrderItem;
import br.com.codenation.model.Product;
import br.com.codenation.repository.ProductRepository;
import br.com.codenation.repository.ProductRepositoryImpl;

public class OrderServiceImpl implements OrderService {

	private ProductRepository productRepository = new ProductRepositoryImpl();

	/**
	 * Calculate the sum of all OrderItems
	 */
	@Override
	public Double calculateOrderValue(List<OrderItem> items) {
		return items.stream()
				.map(item -> calculateTotalValue(getProduct(item.getProductId()), item.getQuantity()))
				.reduce(Double.valueOf(0), Double::sum);
	}

	/**
	 * Map from idProduct List to Product Set
	 */
	@Override
	public Set<Product> findProductsById(List<Long> ids) {
		return ids.stream()
				.map(id -> getProduct(id))
				.filter(product -> product != null)
				.collect(Collectors.toSet());
	}

	/**
	 * Calculate the sum of all Orders(List<OrderIten>)
	 */
	@Override	
	public Double calculateMultipleOrders(List<List<OrderItem>> orders) {
		return orders.stream()
				.map(order -> calculateOrderValue(order))
				.reduce(Double.valueOf(0), Double::sum);
	}

	/**
	 * Group products using isSale attribute as the map key
	 */
	@Override
	public Map<Boolean, List<Product>> groupProductsBySale(List<Long> productIds) {
		return productIds.stream()
				.map(id -> getProduct(id))
				.filter(product -> product != null)
				.collect(Collectors.groupingBy(Product::getIsSale));
	}

	private Double calculateTotalValue(Product product, Long quantity) {
		if (product == null) 
			return Double.valueOf(0);
		Double total = product.getValue() * quantity;
		return product.getIsSale() ? 0.8 * total : total;
	}
		
	private Product getProduct(Long id) {
		if (id == null || id < 0) {
			return null;
		}
		
		Optional<Product> product = productRepository.findById(id);
		
		if (product.isPresent()) {
			return product.get();
		}
		
		return null;
	}
}