package com.cvaccari.commons.extensions

import com.google.common.primitives.UnsignedBytes.toInt

private const val LOW_DEGREE_PREFIX = "L: "
private const val HIGH_DEGREE_PREFIX = "H: "
private const val DEGREE_SUFIX = "°"

fun Double.toDegree() = "${toInt()}$DEGREE_SUFIX --"

fun Double.toLowDegree() = "$LOW_DEGREE_PREFIX${toDegree()}"

fun Double.toHighDegree() = "$HIGH_DEGREE_PREFIX${toDegree()}"
