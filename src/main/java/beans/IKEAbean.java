package beans;

/*
 * Bean to handle response from ikea database.
 */
public class IKEAbean {

	private int key;
	private String itemName, itemDescription, extraOne, extraTwo, extraThree;
	
	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
	
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getExtraOne() {
		return extraOne;
	}

	public void setExtraOne(String extraOne) {
		this.extraOne = extraOne;
	}

	public String getExtraTwo() {
		return extraTwo;
	}

	public void setExtraTwo(String extraTwo) {
		this.extraTwo = extraTwo;
	}

	public String getExtraThree() {
		return extraThree;
	}

	public void setExtraThree(String extraThree) {
		this.extraThree = extraThree;
	}

}
