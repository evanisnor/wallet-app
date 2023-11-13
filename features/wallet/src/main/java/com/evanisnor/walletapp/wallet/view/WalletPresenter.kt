package com.evanisnor.walletapp.wallet.view

import com.evanisnor.walletapp.wallet.data.Card
import com.evanisnor.walletapp.wallet.data.ExampleWalletRepository
import com.evanisnor.walletapp.wallet.data.Wallet
import java.time.LocalDate

/**
 * Function interface to enable building single-purpose state calculations from [Wallet] data.
 */
fun interface WalletCalculation {
  operator fun invoke(wallet: Wallet) : Double
}

/**
 * [WalletCalculation] for calculating the amount of money owed on Credit Cards
 */
val amountOwed = WalletCalculation { wallet ->
  wallet.cards
    .filterIsInstance<Card.CreditCard>()
    .map { it.balance }
    .fold(0.0) { acc, balance -> acc + balance }
}

/**
 * [WalletCalculation] for calculating the total credit available by Credit Cards
 */
val totalCredit = WalletCalculation { wallet ->
  wallet.cards
    .filterIsInstance<Card.CreditCard>()
    .map { it.limit }
    .fold(0.0) { acc, limit -> acc + limit }
}

/**
 * Presenter for the Wallet view. Calling [present] from the View will return an instance of
 * [WalletState] which can then be rendered by the view.
 */
class WalletPresenter(
  private val walletRepository: ExampleWalletRepository
) {

  /**
   * Present a [WalletState] to the Wallet View.
   */
  fun present(): WalletState {
    val wallet = walletRepository.loadWallet(userId = "user-99")
    val amountOwed = amountOwed(wallet)
    val totalCredit = totalCredit(wallet)

    val netBalance = wallet.cashAmount - amountOwed

    return WalletState(
      greeting = "Hello, ${wallet.ownerName}!",
      cashAmount = wallet.cashAmount,
      netBalance = netBalance,
      amountOwed = amountOwed,
      totalCredit = totalCredit,
      creditCards = wallet.cards.filterIsInstance<Card.CreditCard>().map {
        val cardNumberShort = it.number.substring(0 until 4)

        WalletState.CreditCard(
          issuer = when (cardNumberShort) {
            "2143" -> "WunderCard"
            "8943" -> "CorpBank of America XTra Cash"
            else -> "Unknown"
          },
          numberRedacted = "$cardNumberShort********",
          balance = it.balance,
          isPaymentDueSoon = LocalDate.now().until(it.nextStatementOn).days < 7
        )
      },
      giftCards = wallet.cards.filterIsInstance<Card.GiftCard>().map {
        WalletState.GiftCard(
          vendorName = it.vendorName,
          amount = it.amount,
          isHidden = it.amount < 1
        )
      }

    )

  }

}