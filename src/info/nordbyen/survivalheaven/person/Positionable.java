/*
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <alexmsagen@gmail.com> wrote this file.  As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return.   Alexander Sagen
 * ----------------------------------------------------------------------------
 */
package info.nordbyen.survivalheaven.person;

/**
 * The Interface Positionable.
 */
public interface Positionable {

	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public Vektor getLocation();

	/**
	 * Sets the location.
	 */
	public void setLocation();
}
