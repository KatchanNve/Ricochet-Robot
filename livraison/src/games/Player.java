package games;


public interface Player {
    public Fleet getFleet();
    public Pair<Integer, Integer> getShoot();
    public void deleteShotElement(int i, int j);
    public void deleteShotElement(Pair<Integer,Integer> pair);
}
