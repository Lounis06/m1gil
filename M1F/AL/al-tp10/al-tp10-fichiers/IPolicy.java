public interface IPolicy {
	public void allow(String rule);
	public void deny(String rule);
	public boolean isAllowed(String rule);
}
