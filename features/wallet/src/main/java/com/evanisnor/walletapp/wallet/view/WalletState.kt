package com.evanisnor.walletapp.wallet.view

import com.evanisnor.walletapp.Transform
import com.evanisnor.walletapp.wallet.data.Card
import java.time.LocalDate

/**
 * Represents a Wallet that can be rendered on screen
 */
data class WalletState(
  val greeting: String,
  val cashAmount: Double,
  val netBalance: Double,
  val amountOwed: Double,
  val totalCredit: Double,
  val creditCards: List<CreditCard>,
  val giftCards: List<GiftCard>,
) {

  /**
   * Credit Card information that can be rendered on screen
   */
  data class CreditCard(
    val issuer: String,
    val numberRedacted: String,
    val balance: Double,
    val isPaymentDueSoon: Boolean,
  )

  /**
   * Gift Card information that can be rendered on screen
   */
  data class GiftCard(
    val vendorName: String,
    val amount: Double,
    val isHidden: Boolean,
  )
}

/**
 * Transform a [Card.CreditCard] into a [WalletState.CreditCard] for display on screen
 */
val buildCreditCardState = Transform<Card.CreditCard, WalletState.CreditCard> {
  val cardNumberShort = it.number.substring(0 until 4)

  WalletState.CreditCard(
    issuer = it.issuer,
    numberRedacted = "$cardNumberShort********",
    balance = it.balance,
    isPaymentDueSoon = LocalDate.now().until(it.nextStatementOn).days <= 7
  )
}
