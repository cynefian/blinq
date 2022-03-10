package com.gsd.sreenidhi.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;



/**
 * @author Sreenidhi, Gundlupet
 *
 */
public class JSONUtils {

	/**
	 * Returns JSONObject for the given key
	 * 
	 * @param jSon
	 *            JSON String
	 * @param key
	 *            Key
	 * @return JSON Object
	 * @
	 *             Generic  Exception Object that handles all exceptions
	 */
	public JSONObject getJsonObject(String jSon, String key)  {

		JSONObject obj = null;
		if (jSon != null && !"".equalsIgnoreCase(jSon)) {
			String jsonStr = jSon;
			JSONObject jsonObj = new JSONObject(jsonStr);
			obj = jsonObj.getJSONObject(key);

		}
		return obj;
	}

	/**
	 * Returns a String for the given key
	 * 
	 * @param jSon
	 *            JSON String
	 * @param key
	 *            Key
	 * @return String
	 * @
	 *             Generic  Exception Object that handles all exceptions
	 */
	public String getJsonString(String jSon, String key)  {

		String value = null;
		if (jSon != null && !"".equalsIgnoreCase(jSon)) {
			String jsonStr = jSon;
			JSONObject jsonObj = new JSONObject(jsonStr);
			value = jsonObj.getString(key);

		}
		return value;
	}

	/**
	 * Returns an int for the given key
	 * 
	 * @param jSon
	 *            JSON String
	 * @param key
	 *            Key
	 * @return int
	 * @
	 *             Generic  Exception Object that handles all exceptions
	 */
	public int getJsonInt(String jSon, String key)  {

		int value = 0;
		if (jSon != null && !"".equalsIgnoreCase(jSon)) {
			String jsonStr = jSon;
			JSONObject jsonObj = new JSONObject(jsonStr);
			value = jsonObj.getInt(key);

		}
		return value;
	}

	/**
	 * Returns a BigDecimal for the given key
	 * 
	 * @param jSon
	 *            JSON String
	 * @param key
	 *            Key
	 * @return BigDecimal
	 * @
	 *             Generic  Exception Object that handles all exceptions
	 */
	public BigDecimal getJsonBigDecimal(String jSon, String key)  {

		BigDecimal value = null;
		if (jSon != null && !"".equalsIgnoreCase(jSon)) {
			String jsonStr = jSon;
			JSONObject jsonObj = new JSONObject(jsonStr);
			value = jsonObj.getBigDecimal(key);

		}
		return value;
	}

	/**
	 * Returns a BigInteger for the given key
	 * 
	 * @param jSon
	 *            JSON String
	 * @param key
	 *            Key
	 * @return BigInteger
	 * @
	 *             Generic  Exception Object that handles all exceptions
	 */
	public BigInteger getJsonBigInteger(String jSon, String key)  {

		BigInteger value = null;
		if (jSon != null && !"".equalsIgnoreCase(jSon)) {
			String jsonStr = jSon;
			JSONObject jsonObj = new JSONObject(jsonStr);
			value = jsonObj.getBigInteger(key);

		}
		return value;
	}

	/**
	 * Returns a Double for the given key
	 * 
	 * @param jSon
	 *            JSON String
	 * @param key
	 *            Key
	 * @return Double
	 * @
	 *             Generic  Exception Object that handles all exceptions
	 */
	public Double getJsonDouble(String jSon, String key)  {

		Double value = null;
		if (jSon != null && !"".equalsIgnoreCase(jSon)) {
			String jsonStr = jSon;
			JSONObject jsonObj = new JSONObject(jsonStr);
			value = jsonObj.getDouble(key);

		}
		return value;
	}

	/**
	 * Returns a Long for the given key
	 * 
	 * @param jSon
	 *            JSON String
	 * @param key
	 *            Key
	 * @return Long
	 * @
	 *             Generic  Exception Object that handles all exceptions
	 */
	public Long getJsonLong(String jSon, String key)  {

		Long value = null;
		if (jSon != null && !"".equalsIgnoreCase(jSon)) {
			String jsonStr = jSon;
			JSONObject jsonObj = new JSONObject(jsonStr);
			value = jsonObj.getLong(key);
		}
		return value;
	}

	/**
	 * Returns a JSONArray for the given key
	 * 
	 * @param jSon
	 *            JSON String
	 * @param key
	 *            Key
	 * @return JSONArray
	 * @
	 *             Generic  Exception Object that handles all exceptions
	 */
	public JSONArray getJsonArray(String jSon, String key)  {

		JSONArray value = null;
		if (jSon != null && !"".equalsIgnoreCase(jSon)) {
			String jsonStr = jSon;
			JSONObject jsonObj = new JSONObject(jsonStr);
			value = jsonObj.getJSONArray(key);
		}
		return value;
	}

	/**
	 * Returns a JSONArray for the given key
	 * 
	 * @param obj
	 *            JSONObject
	 * @param key Key
	 * @return JSONArray
	 * @
	 *             Exception Generic  Exception Object that handles all
	 *             exceptions
	 */
	public JSONArray getJsonArray(JSONObject obj, String key)  {

		JSONArray value = null;
		String json = convertJSONObjecttoString(obj);
		if (json != null && !"".equalsIgnoreCase(json)) {
			String jsonStr = json;
			JSONObject jsonObj = new JSONObject(jsonStr);
			value = jsonObj.getJSONArray(key);
		}
		return value;
	}

	/**
	 * Returns a Boolean for the given key
	 * 
	 * @param jSon
	 *            JSON String
	 * @param key
	 *            Key
	 * @return Boolean
	 * @
	 *             Generic  Exception Object that handles all exceptions
	 */
	public Boolean getJsonBoolean(String jSon, String key)  {

		Boolean value = null;
		if (jSon != null && !"".equalsIgnoreCase(jSon)) {
			String jsonStr = jSon;
			JSONObject jsonObj = new JSONObject(jsonStr);
			value = jsonObj.getBoolean(key);
		}
		return value;
	}

	/**
	 * Returns a boolean value after checking is the value of the given key is null
	 * 
	 * @param jSon
	 *            JSON String
	 * @param key
	 *            Key
	 * @return boolean
	 * @
	 *             Generic  Exception Object that handles all exceptions
	 */
	public boolean isNullValue(String jSon, String key)  {

		boolean value = true;
		if (jSon != null && !"".equalsIgnoreCase(jSon)) {
			String jsonStr = jSon;
			JSONObject jsonObj = new JSONObject(jsonStr);
			value = jsonObj.isNull(key);
		}
		return value;
	}

	/**
	 * Retuns a String Iterator for Keys
	 * 
	 * @param jSon
	 *            JSON String
	 * @return Iterator
	 * @
	 *             Generic  Exception Object that handles all exceptions
	 */
	public Iterator<String> getKeys(String jSon)  {

		Iterator<String> value = null;
		if (jSon != null && !"".equalsIgnoreCase(jSon)) {
			String jsonStr = jSon;
			JSONObject jsonObj = new JSONObject(jsonStr);
			value = jsonObj.keys();
		}
		return value;
	}

	/**
	 * Returns a JSONArray of names
	 * 
	 * @param jSon
	 *            JSON String
	 * @return JSONArray
	 * @
	 *             Generic  Exception Object that handles all exceptions
	 */
	public JSONArray getNames(String jSon)  {

		JSONArray value = null;
		if (jSon != null && !"".equalsIgnoreCase(jSon)) {
			String jsonStr = jSon;
			JSONObject jsonObj = new JSONObject(jsonStr);
			value = jsonObj.names();
		}
		return value;
	}

	/**
	 * Returns a String array of Names
	 * 
	 * @param jSon
	 *            JSON String
	 * @return String Array
	 * @
	 *             Generic  Exception Object that handles all exceptions
	 */
	public String[] getNamesArray(String jSon)  {

		String[] value = null;
		if (jSon != null && !"".equalsIgnoreCase(jSon)) {
			String jsonStr = jSon;
			JSONObject jsonObj = new JSONObject(jsonStr);
			value = jsonObj.getNames(jsonObj);
		}
		return value;
	}

	/**
	 * @param obj
	 *            JSONObject
	 * @return String
	 */
	public static String convertJSONObjecttoString(JSONObject obj) {
		return JSONObject.valueToString(obj);
	}

}
