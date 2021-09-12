/**
 * Represents one city in the country.
 * Maman15 question1 2019a
 * Maytal Twig
 * @version 19/01/19
 */
public class CityNode
{
	private City _city;
	private CityNode _next;

	/**
	 * constructor
	 * @param city
	 */
	public CityNode(City c) {
		_city = c;
		_next = null;
	}

	/**
	 * constructor that initializes the attributes according to the parameters
	 * * @param city
	 * @param cityNode object
	 */
	public CityNode(City c, CityNode n) {
		_city = c;
		_next = n;
	}

	/**
	 * Copy constructor
	 * @param c
	 */
	public CityNode(CityNode c) {
		_next = c.getNext();
	}

	public City getCity() {
		return _city;
	}

	public CityNode getNext() {
		return _next;
	}

	public void setCity(City c) {
		_city = c;
	}

	public void setNext(CityNode next) {
		_next = next;
	}
}