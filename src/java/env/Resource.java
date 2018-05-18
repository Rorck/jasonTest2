package env;

public class Resource  extends PlanetCell {
	private final int type;
    private int amount;

    public Resource(int t, int am) {

        type = t;
        amount = am;

    }
    
    public Resource(int t){
    	type = t;
    	amount = 0;
    }

    public int getType() {

        return type;

    }

    public int getAmount() {

        return amount;

    }

    public void mine() {

        amount--;

    }

    public boolean depleted() {

        return amount < 1;

    }

}
