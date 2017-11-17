package perftest

import bugwork.City
import bugwork.CityWithNonFieldProperties
import bugwork.CityWithTrait
import bugwork.StaticallyCompiledCity
import bugwork.StaticallyCompiledCityWithNonFieldProperties
import bugwork.StaticallyCompiledCityWithTrait
import org.springframework.util.StopWatch

class BootStrap {
    Long count = 100_000
    Map props = ['name': 'test', 'shortCode':'test', state:"Gujarat", "country": "india", 'latitude':"10.10", 'longitude': "10.10"]


    def init = { servletContext ->
        benchmarkDatabinding(City)
        benchmarkDatabinding(City)

        benchmarkDatabinding(CityWithNonFieldProperties)
        benchmarkDatabinding(CityWithNonFieldProperties)

        benchmarkDatabinding(CityWithTrait)
        benchmarkDatabinding(CityWithTrait)

        benchmarkDatabinding(StaticallyCompiledCity)
        benchmarkDatabinding(StaticallyCompiledCity)

        benchmarkDatabinding(StaticallyCompiledCityWithNonFieldProperties)
        benchmarkDatabinding(StaticallyCompiledCityWithNonFieldProperties)

        benchmarkDatabinding(StaticallyCompiledCityWithTrait)
        benchmarkDatabinding(StaticallyCompiledCityWithTrait)

        benchmarkManualAssignment(City)
        benchmarkManualAssignment(CityWithNonFieldProperties)
        benchmarkManualAssignment(CityWithTrait)
        benchmarkManualAssignment(StaticallyCompiledCity)
        benchmarkManualAssignment(StaticallyCompiledCityWithNonFieldProperties)
        benchmarkManualAssignment(StaticallyCompiledCityWithTrait)

    }

    void benchmarkDatabinding(Class domain) {
        StopWatch watch = new StopWatch()
        def instance = domain.newInstance()
        watch.start()
        for (int i in (1..count)) {
            instance.properties = props
        }
        watch.stop()
        println "Took ${watch.totalTimeSeconds} seconds to databind domain $domain.simpleName $count times"
    }

    void benchmarkManualAssignment(Class domain) {
        StopWatch watch = new StopWatch()
        def instance = domain.newInstance()
        watch.start()
        for (int i in (1..count)) {
            instance.name = props['name']
            instance.shortCode = props['shortCode']
            instance.state = props['state']
            instance.country = props['country']
            instance.latitude = props['latitude'] as BigDecimal
            instance.longitude = props['longitude'] as BigDecimal

        }
        watch.stop()
        println "Took ${watch.totalTimeSeconds} seconds to manually set props on domain $domain.simpleName $count times"
    }

}
