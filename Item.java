
/**
 * Object used to keep items together
 * includes:
 * SKU
 * Cost
 * Weight
 * @author jowen
 *
 */
public class Item{
	String sku;
	int weight,cost;
	public Item(String sku,int weight,int cost){
		this.sku = sku;
		this.weight = weight;
		this.cost = cost;
	}
}