package telran.drones.dto;

public class DroneItemsAmountImpl implements DroneItemsAmount {
	 private String number;
	    private long amount;
	@Override
	public String getNumber() {
		// TODO Auto-generated method stub
		return number;
	}

	public DroneItemsAmountImpl(String number, long amount) {
		super();
		this.number = number;
		this.amount = amount;
	}

	@Override
	public long getAmount() {
		// TODO Auto-generated method stub
		return amount;
	}

}
