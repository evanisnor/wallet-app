package com.evanisnor.walletapp

/**
 * Functional interface to enable building transforms that can be easily tested and maintained.
 */
fun interface Transform<T, R> {
  operator fun invoke(data: T) : R
}