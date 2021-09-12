/**
 * Represents a country.
 * Maman15 question1 2019a
 * Maytal Twig
 * @version 19/01/19
 */
public class Country
{
	private CityNode _head;
	private String _countryName;
	/**
	 * constructor that creates an empty country.
	 * @param countryName
	 * Time Complexity: O(1)
	 * Space complexity: O(1)
	 */
	public Country(String countryName) {
		_head = null;
		_countryName=countryName;
	}

	/**
	 * Add city to the country
	 * @param name
	 * @param xCenter
	 * @param yCenter
	 * @param Xstation
	 * @param Ystation
	 * @param numOfResidents
	 * @param noOfNeighborhoods
	 * @return true- city successfully added
	 * @return false - The city already exists
	 * Time Complexity: O(n)
	 * Space complexity: O(n)
	 */
	public boolean addCity(String name, double xCenter, double yCenter,
			double Xstation, double Ystation, long numOfResidents, int noOfNeighborhoods){
		City newC = new City(name,xCenter,yCenter,Xstation,Ystation,numOfResidents,noOfNeighborhoods);
		for (CityNode p = _head; p != null ; p = p.getNext()) {
			if (p.getCity().getCityName().equals(newC.getCityName()))
				return false;
		}

		CityNode newCity = new CityNode(newC);
		if (_head == null)
			_head = newCity; 
		else {
			newCity.setNext(_head);
			_head = newCity;
		}
		return true;
	}

	/**
	 * Returns the number of residents of the country.
	 * Time Complexity: O(n)
	 * Space complexity: O(1)
	 */
	public long getNumOfResidents() {
		long sum = 0;
		for (CityNode p = _head; p != null; p= p.getNext())
			sum += p.getCity().getNumOfResidents();
		return sum;
	}

	/**
	 * Returns the The longest distance between two cities in the country.
	 * Time Complexity: O(1)
	 * Space complexity: O(1)
	 */
	public double longestDistance() {
		if (_head == null || _head.getNext() == null)
			return 0;
		return longestDistance(_head , _head.getNext(), 0);	
	}
	private double longestDistance(CityNode c1, CityNode c2, double sum) {
		if (c1 == null || c2 == null || c1 == c2 || getNumOfCities()<2)
			return 0;
		sum = Math.max(longestDistance(c1.getNext() , c2, sum), 
				longestDistance(c1 , c2.getNext(), sum));
		sum = Math.max(checkSum(c1 , c2, sum),
				sum);
		return sum;
	}

	private double checkSum(CityNode c1, CityNode c2, double sum) {
		double temp = (c1.getCity().getCityCenter()).distance(c2.getCity().getCityCenter());
		if (temp > sum)
			sum = temp;
		return sum;
	}

	/**
	 * printing all the cities in the country that are located north of a certain city (above the city).
	 * @param cityName
	 * Time Complexity: O(1)
	 * Space complexity: O(n)
	 */
	public String citiesNorthOf(String cityName) {
		CityNode c;
		c = inTheCountry(_countryName, _head);
		if (c == null)
			return "There is no city with the name " + cityName;		
		String res = "";
		res += citiesNorthOf(c, _head, res);
		return res;
	}	

	private CityNode inTheCountry(String _countryName, CityNode c) {
		if (c == null)
			return null;
		if (c.getCity().getCityName().equals(_countryName)) 
			return c; 
		if (c.getNext() == null)
			return null;
		return inTheCountry(_countryName, c.getNext());
	}

	private String citiesNorthOf(CityNode theCity, CityNode otherCity, String res) {
		if (otherCity == null && res.equals("")) 
			return "There are no cities north of " + theCity.getCity().getCityName();
		if ((otherCity.getCity().getCityCenter()).isAbove(theCity.getCity().getCityCenter())) 
			res += otherCity.getCity() + "\n";
		if (otherCity.getNext() == null) {
			if (res.equals(""))
				return "There are no cities north of " + theCity.getCity().getCityName();
			return res;
		}
		return citiesNorthOf(theCity, otherCity.getNext(), res);
	}

	/**
	 * the most south city in the country
	 * Time Complexity: O(1)
	 * Space complexity: O(1)
	 */
	public City southernmostCity() {
		if (_head == null)
			return null;
		if (_head.getNext() == null)
			return _head.getCity();
		return southernmostCity(_head, _head.getNext());
	}

	private City southernmostCity(CityNode south, CityNode north) {
		if ((north.getCity().getCityCenter()).isUnder(south.getCity().getCityCenter()))
			south = north;
		if (north.getNext() == null)
			return south.getCity();
		return southernmostCity(south, north.getNext());
	}

	/**
	 * Returns the name of the country.
	 * Time Complexity: O(1)
	 * Space complexity: O(1)
	 */
	public String getCountryName() { 
		return _countryName;
	}


	/**
	 * Returns the number of the cities in the country.
	 * Time Complexity: O(1)
	 * Space complexity: O(1)
	 */
	public int getNumOfCities() {
		return getNumOfCities(_head, 0);
	}

	private int getNumOfCities(CityNode c, int count) {
		if (c == null)
			return count;
		return getNumOfCities(c.getNext(), count + 1);
	}
	/**
	 * 
	 * @return copy of the list of cities
	 * Time Complexity: O(n)
	 * Space complexity: O(n)
	 */
	public Country getCities() {
		Country newCountry= new Country(_countryName);
		return getCities(newCountry, _head);
	}

	private Country getCities(Country newCountry, CityNode c) {
		if (c == null)
			return newCountry;
		newCountry.addCity(c.getCity().getCityName(), c.getCity().getCityCenter().getX(), c.getCity().getCityCenter().getY(),c.getCity().getCenteralStation().getX(),c.getCity().getCenteralStation().getY(),c.getCity().getNumOfResidents(),c.getCity().getNoOfNeighborhoods());
		return getCities(newCountry, c.getNext());
	}

	/**
	 * unify between two cities.
	 * @param cityName1
	 * @param cityName2
	 * Time Complexity: O(n)
	 * Space complexity: O(n)
	 */
	public City unifyCities(String cityName1, String cityName2) {
		City c1 = null, c2 = null;
		if (inTheCountry(cityName1, _head) != null)
			c1 = inTheCountry(cityName1, _head).getCity();
		if (inTheCountry(cityName2, _head) != null)
			c2 = inTheCountry(cityName2, _head).getCity();
		if (c1 == null && c2 == null)
			return null;
		if (c1 == null)
			return c2;
		if (c2 == null)
			return c1;
		if (c1.getNumOfResidents() == c2.getNumOfResidents()){
			removeCity(_head, c1, c2);
			return setUnifyCities(c1, c2);
		}
		if (c2.getNumOfResidents() > c1.getNumOfResidents())
			return setUnifyCities(c2, c1);
		return setUnifyCities(c1, c2);
	}

	private City setUnifyCities(City big, City small) {
		big.setCityName(big.getCityName() + "-" + small.getCityName());
		double newX = (big.getCityCenter().getX() + small.getCityCenter().getX()) / 2;
		double newY = (big.getCityCenter().getY() + small.getCityCenter().getY()) / 2;
		Point newP = new Point(newX, newY);
		big.setCityCenter(newP);
		if (small.getCenteralStation().isLeft(big.getCenteralStation()))
			big.setCenteralStation(small.getCenteralStation());
		big.setNumOfResidents(big.getNumOfResidents() + small.getNumOfResidents());
		big.setNoOfNeighborhoods(big.getNoOfNeighborhoods() + big.getNoOfNeighborhoods());
		removeCity(_head, big, small);
		return big;
	}

	private void removeCity(CityNode c, City big, City small) {
		if (big.getNumOfResidents() == small.getNumOfResidents()) {
			if (c.getCity() != big && c.getNext().getCity() == small)
				justRemove(c, c.getNext());
			removeCity(c.getNext(), big, small);
		}
		if (c != null && c.getNext() != null) {
			if (c.getCity() == small) {
				c.setCity(c.getNext().getCity());
				c.setNext(c.getNext().getNext());
			}
			if (c.getNext().getCity() == small)	
				justRemove(c, c.getNext());
			removeCity(c.getNext(), big, small);
		}
		
	}		

	private void justRemove(CityNode befor, CityNode remove) {
		befor.setNext(remove.getNext());
	}

	/**
	 * Returns a string representation of the cities in the country.
	 * Time Complexity: O(n)
	 * Space complexity: O(1)
	 */
	public String toString() {
		if (_head==null)
			return "There are no cities in this country.";
		String res = "Cities of "+_countryName+":\n";
		return toString(_head, res);
	}

	private String toString(CityNode c, String res) {
		if (c == null)
			return res;
		res += "\n" + c.getCity();
		return toString(c.getNext(), res);
	}
}