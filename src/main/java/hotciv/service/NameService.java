package hotciv.service;

import hotciv.framework.City;
import hotciv.framework.Unit;

public interface NameService {

    /** Put a Unit into the name service under given id
     *
     * @param objectId ID of the object
     * @param unit the servant object
     */
    void putUnit(String objectId, Unit unit);

    /** Get a Unit.
     *
     * @param objectId the id of the servant object to get
     * @return the servant object
     */
    Unit getUnit(String objectId);
    /** Put a City into the name service under given id
     *
     * @param objectId ID of the object
     * @param city the servant object
     */
    void putCity(String objectId, City city);

    /** Get a City.
     *
     * @param objectId the id of the servant object to get
     * @return the servant object
     */
    Unit getCity(String objectId);
}
