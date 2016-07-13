package by.training.concurrent.semaphore;

public class SemaphoreCommonResource {
	private int id;

	public SemaphoreCommonResource(){
		super();
	}
	
	public int getId() {
		return id;
	}
	
	public void incrementId(){
		id++;
	}
}
