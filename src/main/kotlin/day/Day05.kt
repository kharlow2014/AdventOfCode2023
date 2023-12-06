package day

import DayOfCode

class Day05(filename: String? = null) : DayOfCode(filename ?: "05.data") {

    override fun solveOne(): Any = openStream().use { stream ->
        stream.readLines().let { lines ->
            Almanac().also {
                it.setData(lines)
            }
        }.getMinimumLocation() ?: 0L
    }

    override fun solveTwo(): Any = openStream().use { stream ->
        stream.readLines().let { lines ->
            Almanac().also {
                it.setData(lines)
            }
        }.getMinimumLocationWithRanges()
    }

    data class Almanac(
        private val seeds: MutableList<Long> = mutableListOf(),
        private val seedsToSoilMappings: MutableList<Mapping> = mutableListOf(),
        private val soilToFertilizerMappings: MutableList<Mapping> = mutableListOf(),
        private val fertilizerToWaterMappings: MutableList<Mapping> = mutableListOf(),
        private val waterToLightMappings: MutableList<Mapping> = mutableListOf(),
        private val lightToTemperatureMappings: MutableList<Mapping> = mutableListOf(),
        private val temperatureToHumidityMappings: MutableList<Mapping> = mutableListOf(),
        private val humidityToLocationMappings: MutableList<Mapping> = mutableListOf()
    ) {

        fun setData(data: List<String>) {
            seeds.addAll(data[0].split(": ")[1].split(" ").map { it.toLong() })

            var index = 3
            while (data[index].isNotBlank()) {
                data[index].split(" ").map { it.toLong() }.run {
                    seedsToSoilMappings.add(Mapping(this[0], this[1], this[2]))
                }
                index++
            }

            index += 2
            while (data[index].isNotBlank()) {
                data[index].split(" ").map { it.toLong() }.run {
                    soilToFertilizerMappings.add(Mapping(this[0], this[1], this[2]))
                }
                index++
            }

            index += 2
            while (data[index].isNotBlank()) {
                data[index].split(" ").map { it.toLong() }.run {
                    fertilizerToWaterMappings.add(Mapping(this[0], this[1], this[2]))
                }
                index++
            }

            index += 2
            while (data[index].isNotBlank()) {
                data[index].split(" ").map { it.toLong() }.run {
                    waterToLightMappings.add(Mapping(this[0], this[1], this[2]))
                }
                index++
            }

            index += 2
            while (data[index].isNotBlank()) {
                data[index].split(" ").map { it.toLong() }.run {
                    lightToTemperatureMappings.add(Mapping(this[0], this[1], this[2]))
                }
                index++
            }

            index += 2
            while (data[index].isNotBlank()) {
                data[index].split(" ").map { it.toLong() }.run {
                    temperatureToHumidityMappings.add(Mapping(this[0], this[1], this[2]))
                }
                index++
            }

            index += 2
            while (index < data.size && data[index].isNotBlank()) {
                data[index].split(" ").map { it.toLong() }.run {
                    humidityToLocationMappings.add(Mapping(this[0], this[1], this[2]))
                }
                index++
            }
        }

        fun getMinimumLocation(): Long? = seeds.minOfOrNull { getLocationForSeed(it) }
        
        fun getMinimumLocationWithRanges(): Long { 
            val ranges = seeds.chunked(2).map { it[0]..(it[0] + it[1]) }
            return ranges.minOf { range ->
                range.minOf { seed ->
                    getLocationForSeed(seed)
                }
            }
        }

        private fun getLocationForSeed(seed: Long): Long = getSoilForSeed(seed).let { soil ->
            getFertilizerForSoil(soil).let { fertilizer ->
                getWaterForFertilizer(fertilizer).let { water ->
                    getLightForWater(water).let { light ->
                        getTemperatureForLight(light).let { temperature ->
                            getHumidityForTemperature(temperature).let { humidity ->
                                val location = getLocationForHumidity(humidity)
                                location
                            }
                        }
                    }
                }
            }
        }

        private fun getSoilForSeed(seed: Long): Long = 
            seedsToSoilMappings.firstOrNull { it.containsSource(seed) }?.destinationForSource(seed) ?: seed

        private fun getFertilizerForSoil(soil: Long): Long =
            soilToFertilizerMappings.firstOrNull { it.containsSource(soil) }?.destinationForSource(soil) ?: soil

        private fun getWaterForFertilizer(fertilizer: Long): Long =
            fertilizerToWaterMappings.firstOrNull { it.containsSource(fertilizer) }?.destinationForSource(fertilizer)
                ?: fertilizer

        private fun getLightForWater(water: Long): Long =
            waterToLightMappings.firstOrNull { it.containsSource(water) }?.destinationForSource(water) ?: water

        private fun getTemperatureForLight(light: Long): Long =
            lightToTemperatureMappings.firstOrNull { it.containsSource(light) }?.destinationForSource(light) ?: light

        private fun getHumidityForTemperature(temperature: Long): Long =
            temperatureToHumidityMappings.firstOrNull { it.containsSource(temperature) }
                ?.destinationForSource(temperature) ?: temperature

        private fun getLocationForHumidity(humidity: Long): Long =
            humidityToLocationMappings.firstOrNull { it.containsSource(humidity) }?.destinationForSource(humidity)
                ?: humidity

        data class Mapping(
            private val destination: Long,
            private val source: Long,
            private val range: Long
        ) {
            fun containsSource(source: Long) = (this.source..(this.source + range)).contains(source)

            fun destinationForSource(source: Long) = destination + (source - this.source)
        }
    }
}
