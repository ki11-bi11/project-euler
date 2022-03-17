package utils

import kotlin.math.pow

class BigFraction(
    var numerator: List<Primes.PrimePower>,
    var denominator: List<Primes.PrimePower>
) {

    constructor(
        numeratorFactors: LongArray,
        denominatorFactors: LongArray
    ): this(
        numerator = numeratorFactors.map { nf -> Primes.primePowers(nf) }.flatten(),
        denominator = denominatorFactors.map { df -> Primes.primePowers(df) }.flatten()
    )

    init {
        numerator = collectPrimePowers(numerator)
        denominator = collectPrimePowers(denominator)
    }

    private fun collectPrimePowers(lsPrimePower: List<Primes.PrimePower>): List<Primes.PrimePower> {
        return lsPrimePower.groupBy { (p, _) -> p }
            .mapValues { (_, ls) -> ls.sumOf { (_, pow) -> pow } }
            .map { (p, pow) -> Primes.PrimePower(p, pow) }
    }

    private fun evaluate(lsPrimePower: List<Primes.PrimePower>): Long {
        return lsPrimePower.fold(1L) { prod, (p, pow) -> prod * p.toDouble().pow(pow).toLong() }
    }

    fun reduce() {
        val numeratorFactors = ArrayDeque(numerator.sortedBy { (p, _) -> p })
        val denominatorFactors = ArrayDeque(denominator.sortedBy { (p, _) -> p })
        val reducedNumerator = mutableListOf<Primes.PrimePower>()
        val reducedDenominator = mutableListOf<Primes.PrimePower>()
        var lastNumeratorFactor: Primes.PrimePower? = null
        var lastDenominatorFactor: Primes.PrimePower? = null

        while (numeratorFactors.isNotEmpty() || denominatorFactors.isNotEmpty()) {
            if (lastNumeratorFactor == null && numeratorFactors.isNotEmpty()) {
                lastNumeratorFactor = numeratorFactors.removeFirst()
            }
            if (lastDenominatorFactor == null && denominatorFactors.isNotEmpty()) {
                lastDenominatorFactor = denominatorFactors.removeFirst()
            }
            if (lastNumeratorFactor != null && lastDenominatorFactor != null) {
                if (lastNumeratorFactor.prime == lastDenominatorFactor.prime) {
                    val reducedPower = lastNumeratorFactor.power - lastDenominatorFactor.power
                    if (reducedPower > 0) {
                        reducedNumerator.add(Primes.PrimePower(lastNumeratorFactor.prime, reducedPower))
                    } else if (reducedPower < 0) {
                        reducedDenominator.add(Primes.PrimePower(lastDenominatorFactor.prime, -reducedPower))
                    }
                    lastNumeratorFactor = null
                    lastDenominatorFactor = null
                }
                else if (lastNumeratorFactor.prime < lastDenominatorFactor.prime) {
                    reducedNumerator.add(lastNumeratorFactor)
                    lastNumeratorFactor = null
                } else {
                    reducedDenominator.add(lastDenominatorFactor)
                    lastDenominatorFactor = null
                }
            } else if (lastNumeratorFactor != null) {
                reducedNumerator.add(lastNumeratorFactor)
                lastNumeratorFactor = null
            } else if (lastDenominatorFactor != null) {
                reducedDenominator.add(lastDenominatorFactor)
                lastDenominatorFactor = null
            }
        }

        if (lastNumeratorFactor != null) reducedNumerator.add(lastNumeratorFactor)
        if (lastDenominatorFactor != null) reducedDenominator.add(lastDenominatorFactor)

        numerator = reducedNumerator
        denominator = reducedDenominator
    }

    fun getValue(): Long {
        reduce()
        return evaluate(numerator) / evaluate(denominator)
    }
}