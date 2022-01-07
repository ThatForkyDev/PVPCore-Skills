package games.trident.skills.utilities;

import com.google.common.collect.Maps;

import java.util.NavigableMap;
import java.util.Random;

public class RandomCollection<T> {
	public NavigableMap<Double, T> map = Maps.newTreeMap();
	private Random random = new Random();
	public double total = 0;

	public void add(double weight, T result) {
		if (weight <= 0.0D)
			return;

		total += weight;
		map.put(total, result);
	}

	public T next() {
		double value = random.nextDouble() * total;

		return map.ceilingEntry(value).getValue();
	}

	public NavigableMap<Double, T> getMap() {
		return map;
	}
}