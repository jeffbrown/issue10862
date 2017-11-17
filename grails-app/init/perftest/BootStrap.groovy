package perftest

import bugwork.City
import bugwork.CityWithTrait
import org.springframework.util.StopWatch

class BootStrap {
    Long count = 1_000_00_0
    Map props = ['name': 'test', 'shortCode':'test', state:"Gujarat", "country": "india", 'latitude':"10.10", 'longitude': "10.10"]


    def init = { servletContext ->
        benchmarkDatabinding(City)
        benchmarkDatabinding(City)

        benchmarkDatabinding(CityWithTrait)
        benchmarkDatabinding(CityWithTrait)

        benchmarkManualAssignment(City)
        benchmarkManualAssignment(CityWithTrait)

    }

    void benchmarkDatabinding(Class domain) {
        StopWatch watch = new StopWatch()
        watch.start()
        for (int i in (1..count)) {
            def instance = domain.newInstance()
            instance.properties = props
        }
        watch.stop()
        println "Took ${watch.totalTimeSeconds} seconds to databind domain $domain.simpleName $count times"
    }

    void benchmarkManualAssignment(Class domain) {
        StopWatch watch = new StopWatch()
        watch.start()
        for (int i in (1..count)) {
            def instance = domain.newInstance()
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
