package com.ejavinas.trackbyitunes.itunessearch.utils

/**
 * Utility class for pricing labels
 */
object PriceUtils {

    /**
     * Prepends a dollar sign to given price
     */
    @JvmStatic
    fun prependDollarSign(price: Double): String {
        return "$$price"
    }

}