package bugwork

import groovy.transform.CompileStatic

@CompileStatic
class StaticallyCompiledCityWithNonFieldProperties {
    String _name
    String _shortCode
    String _state
    String _country

    BigDecimal _latitude
    BigDecimal _longitude

    String getName() {
        return _name
    }

    void setName(String name) {
        this._name = name
    }

    String getShortCode() {
        return _shortCode
    }

    void setShortCode(String shortCode) {
        this._shortCode = shortCode
    }

    String getState() {
        return _state
    }

    void setState(String state) {
        this._state = state
    }

    String getCountry() {
        return _country
    }

    void setCountry(String country) {
        this._country = country
    }

    BigDecimal getLatitude() {
        return _latitude
    }

    void setLatitude(BigDecimal latitude) {
        this._latitude = latitude
    }

    BigDecimal getLongitude() {
        return _longitude
    }

    void setLongitude(BigDecimal longitude) {
        this._longitude = longitude
    }
}
