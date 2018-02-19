package algorithms.fundamentals.basic;

public class Counter {
	private String id;
	private long count=0L;
	public Counter(String id){
		this.id=id;
	}
	public void increment(){
		this.count++;
	}
	public long tally(){
		return this.count;
	}
	public String toString(){
		return this.id+"操作的次数为："+this.count;
	}
}
