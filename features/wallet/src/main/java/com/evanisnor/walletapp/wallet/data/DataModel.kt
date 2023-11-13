package com.evanisnor.walletapp.wallet.data

import java.time.LocalDate

/**
 * Represents a digital wallet that can contain a [cashAmount] and a collection of [Card]s
 */
data class Wallet(
  val ownerName: String,
  val cashAmount: Double,
  val cards: Collection<Card>
)

/**
 * Supported types of Cards
 */
sealed interface Card {

  /**
   * Represents a Credit Card. Funds represented in the [balance] must be paid by [nextStatementOn].
   */
  data class CreditCard(
    val number: String,
    val balance: Double,
    val limit: Double,
    val expiresOn: LocalDate,
    val nextStatementOn: LocalDate,
  ) : Card

  /**
   * Represents a Gift Card from a vendor valid for the given [amount]
   */
  data class GiftCard(
    val vendorName: String,
    val amount: Double,
  ) : Card
}