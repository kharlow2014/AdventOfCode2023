package day

import DayOfCode

class Day03(filename: String? = null) : DayOfCode(filename ?: "03.data") {
    
    override fun solveOne(): Any {
        val data = openStream().readLines().map { it.toCharArray() }
        val partNumbers: MutableList<Int> = mutableListOf()
        var lineNumber = 0
        var charNumber = 0
        
        while (lineNumber < data.size) {
            if (data[lineNumber][charNumber].isDigit()) {
                var numberRange = charNumber..charNumber
                charNumber++
                while (charNumber < data[lineNumber].size) {
                    if (data[lineNumber][charNumber].isDigit()) {
                        numberRange = numberRange.first..charNumber
                    } else {
                        break
                    }
                    charNumber++
                }
                
                val partNumber = data[lineNumber].slice(numberRange).joinToString("").toInt()
                for (i in numberRange) {
                    if (i - 1 > 0) {
                        if (lineNumber - 1 > 0 && data[lineNumber - 1][i - 1].isSymbolic()) {
                            partNumbers.add(partNumber)
                            break
                        }
                        if (data[lineNumber][i - 1].isSymbolic()) {
                            partNumbers.add(partNumber)
                            break
                        }
                        if (lineNumber + 1 < data.size - 1 && data[lineNumber + 1][i - 1].isSymbolic()) {
                            partNumbers.add(partNumber)
                            break
                        }
                    }
                    if (lineNumber - 1 > 0 && data[lineNumber - 1][i].isSymbolic()) {
                        partNumbers.add(partNumber)
                        break
                    }
                    if (lineNumber + 1 < data.size - 1 && data[lineNumber + 1][i].isSymbolic()) {
                        partNumbers.add(partNumber)
                        break
                    }
                    if (i + 1 < data[lineNumber].size - 1) {
                        if (lineNumber - 1 > 0 && data[lineNumber - 1][i + 1].isSymbolic()) {
                            partNumbers.add(partNumber)
                            break
                        }
                        if (data[lineNumber][i + 1].isSymbolic()) {
                            partNumbers.add(partNumber)
                            break
                        }
                        if (lineNumber + 1 < data.size - 1 && data[lineNumber + 1][i + 1].isSymbolic()) {
                            partNumbers.add(partNumber)
                            break
                        }
                    }
                }
            }
            
            if (charNumber < data[lineNumber].size - 1) {
                charNumber++
            } else {
                charNumber = 0
                lineNumber++
            }
        }
        
        println(partNumbers)
        return partNumbers.sum()
    }

    override fun solveTwo(): Any {
        val data = openStream().readLines().map { it.toCharArray() }
        val gearRatios: MutableList<Int> = mutableListOf()
        for (lineNumber in data.indices) {
            for (charNumber in data[lineNumber].indices) {
                if (data[lineNumber][charNumber] == '*') {
                    val topRow = when {
                        lineNumber == 0 -> "...".toCharArray()
                        charNumber == 0 -> charArrayOf('.', data[lineNumber - 1][0], data[lineNumber - 1][1])
                        charNumber == data[lineNumber].size - 1 -> charArrayOf(data[lineNumber - 1][charNumber - 1], data[lineNumber - 1][charNumber], '.')
                        else -> charArrayOf(data[lineNumber - 1][charNumber - 1], data[lineNumber - 1][charNumber], data[lineNumber - 1][charNumber + 1])
                    }
                    val centerRow = when (charNumber) {
                        0 -> charArrayOf('.', data[lineNumber][0], data[lineNumber][1])
                        data[lineNumber].size - 1 -> charArrayOf(data[lineNumber][charNumber - 1], data[lineNumber][charNumber], '.')
                        else -> charArrayOf(data[lineNumber][charNumber - 1], '.', data[lineNumber][charNumber + 1])
                    }
                    val bottomRow = when {
                        lineNumber == data.size - 1 -> "...".toCharArray()
                        charNumber == 0 -> charArrayOf('.', data[lineNumber + 1][0], data[lineNumber + 1][1])
                        charNumber == data[lineNumber].size - 1 -> charArrayOf(data[lineNumber + 1][charNumber - 1], data[lineNumber + 1][charNumber], '.')
                        else -> charArrayOf(data[lineNumber + 1][charNumber - 1], data[lineNumber + 1][charNumber], data[lineNumber + 1][charNumber + 1])
                    }
                    
                    var numberOne: Int? = null
                    var numberTwo: Int? = null
                    if (topRow[0].isDigit() && !topRow[1].isDigit() && topRow[2].isDigit()) {
                        var numberOneRange = (charNumber - 1) until charNumber
                        var charIndex = charNumber - 2
                        while (charIndex >= 0) {
                            if (data[lineNumber - 1][charIndex].isDigit()) {
                                numberOneRange = charIndex until charNumber
                                charIndex--
                            } else {
                                break
                            }
                        }

                        var numberTwoRange = (charNumber + 1)..(charNumber + 1)
                        charIndex = charNumber + 2
                        while (charIndex < data[lineNumber - 1].size) {
                            if (data[lineNumber - 1][charIndex].isDigit()) {
                                numberTwoRange = (charNumber + 1)..charIndex
                                charIndex ++
                            } else {
                                break
                            }
                        }
                        
                        numberOne = data[lineNumber - 1].slice(numberOneRange).joinToString("").toInt()
                        numberTwo = data[lineNumber - 1].slice(numberTwoRange).joinToString("").toInt()
                    } else if (centerRow[0].isDigit() && centerRow[2].isDigit()) {
                        var numberOneRange = charNumber - 1 until charNumber
                        var charIndex = charNumber - 2
                        while (charIndex >= 0) {
                            if (data[lineNumber][charIndex].isDigit()) {
                                numberOneRange = charIndex until charNumber
                                charIndex--
                            } else {
                                break
                            }
                        }
                        
                        var numberTwoRange = charNumber + 1..charNumber + 1
                        charIndex = charNumber + 2
                        while (charIndex < data[lineNumber].size) {
                            if (data[lineNumber][charIndex].isDigit()) {
                                numberTwoRange = charNumber + 1..charIndex
                                charIndex++
                            } else {
                                break
                            }
                        }
                        
                        numberOne = data[lineNumber].slice(numberOneRange).joinToString("").toInt()
                        numberTwo = data[lineNumber].slice(numberTwoRange).joinToString("").toInt()
                    } else if (bottomRow[0].isDigit() && !bottomRow[1].isDigit() && bottomRow[2].isDigit()) {
                        var numberOneRange = (charNumber - 1) until charNumber
                        var charIndex = charNumber - 2
                        while (charIndex >= 0) {
                            if (data[lineNumber + 1][charIndex].isDigit()) {
                                numberOneRange = charIndex until charNumber
                                charIndex--
                            } else {
                                break
                            }
                        }

                        var numberTwoRange = (charNumber + 1)..(charNumber + 1)
                        charIndex = charNumber + 2
                        while (charIndex < data[lineNumber + 1].size) {
                            if (data[lineNumber + 1][charIndex].isDigit()) {
                                numberTwoRange = (charNumber + 1)..charIndex
                                charIndex ++
                            } else {
                                break
                            }
                        }

                        numberOne = data[lineNumber + 1].slice(numberOneRange).joinToString("").toInt()
                        numberTwo = data[lineNumber + 1].slice(numberTwoRange).joinToString("").toInt()
                    } else if (topRow.any { it.isDigit() } && centerRow.any { it.isDigit() }) {
                        val indexOfFirstDigit = topRow.indexOfFirst { it.isDigit() } + charNumber - 1
                        var numberOneRange = indexOfFirstDigit..indexOfFirstDigit
                        var charIndex = indexOfFirstDigit - 1
                        while (charIndex >= 0) {
                            if (data[lineNumber - 1][charIndex].isDigit()) {
                                numberOneRange = charIndex..indexOfFirstDigit
                                charIndex--
                            } else {
                                break
                            }
                        }
                        charIndex = indexOfFirstDigit + 1
                        while (charIndex < data[lineNumber - 1].size) {
                            if (data[lineNumber - 1][charIndex].isDigit()) {
                                numberOneRange = numberOneRange.first..charIndex
                                charIndex++
                            } else {
                                break
                            }
                        }
                        
                        var numberTwoRange: IntRange
                        if (centerRow[0].isDigit()) {
                            numberTwoRange = charNumber - 1 until charNumber
                            charIndex = charNumber - 2
                            while (charIndex >= 0) {
                                if (data[lineNumber][charIndex].isDigit()) {
                                    numberTwoRange = charIndex..numberTwoRange.last
                                    charIndex--
                                } else {
                                    break
                                }
                            }
                        } else {
                            numberTwoRange = charNumber + 1..charNumber + 1
                            charIndex = charNumber + 2
                            while (charIndex < data[lineNumber].size) {
                                if (data[lineNumber][charIndex].isDigit()) {
                                    numberTwoRange = numberTwoRange.first..charIndex
                                    charIndex++
                                } else {
                                    break
                                }
                            }
                        }

                        numberOne = data[lineNumber - 1].slice(numberOneRange).joinToString("").toInt()
                        numberTwo = data[lineNumber].slice(numberTwoRange).joinToString("").toInt()
                    } else if (topRow.any { it.isDigit() } && bottomRow.any { it.isDigit() }) {
                        var indexOfFirstDigit = topRow.indexOfFirst { it.isDigit() } + charNumber - 1
                        var numberOneRange = indexOfFirstDigit..indexOfFirstDigit
                        var charIndex = indexOfFirstDigit - 1
                        while (charIndex >= 0) {
                            if (data[lineNumber - 1][charIndex].isDigit()) {
                                numberOneRange = charIndex..numberOneRange.last
                                charIndex--
                            } else {
                                break
                            }
                        }
                        charIndex = indexOfFirstDigit + 1
                        while (charIndex < data[lineNumber - 1].size) {
                            if (data[lineNumber - 1][charIndex].isDigit()) {
                                numberOneRange = numberOneRange.first..charIndex
                                charIndex++
                            } else {
                                break
                            }
                        }
                        
                        indexOfFirstDigit = bottomRow.indexOfFirst { it.isDigit() } + charNumber - 1
                        var numberTwoRange = indexOfFirstDigit..indexOfFirstDigit
                        charIndex = indexOfFirstDigit - 1
                        while (charIndex >= 0) {
                            if (data[lineNumber + 1][charIndex].isDigit()) {
                                numberTwoRange = charIndex..numberTwoRange.last
                                charIndex--
                            } else {
                                break
                            }
                        }
                        charIndex = indexOfFirstDigit + 1
                        while (charIndex < data[lineNumber + 1].size) {
                            if (data[lineNumber + 1][charIndex].isDigit()) {
                                numberTwoRange = numberTwoRange.first..charIndex
                                charIndex++
                            } else {
                                break
                            }
                        }
                        
                        numberOne = data[lineNumber - 1].slice(numberOneRange).joinToString("").toInt()
                        numberTwo = data[lineNumber + 1].slice(numberTwoRange).joinToString("").toInt()
                    } else if (centerRow.any { it.isDigit() } && bottomRow.any { it.isDigit() }) {
                        var numberOneRange: IntRange
                        var charIndex: Int
                        if (centerRow[0].isDigit()) {
                            numberOneRange = charNumber - 1 until charNumber
                            charIndex = charNumber - 2
                            while (charIndex >= 0) {
                                if (data[lineNumber][charIndex].isDigit()) {
                                    numberOneRange = charIndex..numberOneRange.last
                                    charIndex--
                                } else {
                                    break
                                }
                            }
                        } else {
                            numberOneRange = charNumber + 1..charNumber + 1
                            charIndex = charNumber + 2
                            while (charIndex < data[lineNumber].size) {
                                if (data[lineNumber][charIndex].isDigit()) {
                                    numberOneRange = numberOneRange.first..charIndex
                                    charIndex++
                                } else {
                                    break
                                }
                            }
                        }

                        val indexOfFirstDigit = bottomRow.indexOfFirst { it.isDigit() } + charNumber - 1
                        var numberTwoRange = indexOfFirstDigit..indexOfFirstDigit
                        charIndex = indexOfFirstDigit - 1
                        while (charIndex >= 0) {
                            if (data[lineNumber + 1][charIndex].isDigit()) {
                                numberTwoRange = charIndex..numberTwoRange.last
                                charIndex--
                            } else {
                                break
                            }
                        }
                        charIndex = indexOfFirstDigit + 1
                        while (charIndex < data[lineNumber + 1].size) {
                            if (data[lineNumber + 1][charIndex].isDigit()) {
                                numberTwoRange = numberTwoRange.first..charIndex
                                charIndex++
                            } else {
                                break
                            }
                        }

                        numberOne = data[lineNumber].slice(numberOneRange).joinToString("").toInt()
                        numberTwo = data[lineNumber + 1].slice(numberTwoRange).joinToString("").toInt()
                    }
                    if (numberOne != null && numberTwo != null) {
                        gearRatios.add(numberOne * numberTwo)
                        println("Gears: $numberOne, $numberTwo; Gear ratio: ${numberOne * numberTwo}")
                    }
                }
            }
        }
        return gearRatios.sumOf { it }
    }
    
    private fun Char.isSymbolic(): Boolean = !this.isDigit() && this != '.'
}
