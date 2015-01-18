package sat.entities;

public class Literal {

	private int id;
	private int weight;
	private Boolean negativity;
	
	public Literal(int id, int weight, Boolean negativity) {
		this.id = id;
		this.weight = weight;
		this.negativity = negativity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Boolean getNegativity() {
		return negativity;
	}

	public void setNegativity(Boolean negativity) {
		this.negativity = negativity;
	}
	
	@Override
	public String toString() {
		
		return "Literal #" + this.id + "\n--Weight: " + this.weight + "\n--Negativity: " + this.negativity.toString();
	}
}
