package br.unifesp.profetas.business.common;

public enum OrderType {
	ASC	("asc"),
	DESC("desc");
	
	private String description;
	
	private OrderType(String descr){
		this.description = descr;
	}
	
	public static OrderType getOrderType(String text){
		OrderType orderType = OrderType.DESC;
		for(OrderType ob : OrderType.values()){
			if(ob.getDescription().equals(text)){
				orderType = ob;
				break;
			}
		}
		return orderType;
	}
	
	public String getDescription() {
		return description;
	}
}