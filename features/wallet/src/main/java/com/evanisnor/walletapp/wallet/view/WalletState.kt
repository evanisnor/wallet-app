package com.evanisnor.walletapp.wallet.view

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
