package bugwork

import groovy.transform.CompileStatic

@CompileStatic
trait CityTrait {
    String name
    String shortCode
    String state
    String country

    BigDecimal latitude
    BigDecimal longitude
}
