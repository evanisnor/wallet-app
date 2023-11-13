package com.evanisnor.walletapp.wallet.view

import com.evanisnor.walletapp.wallet.data.Card
import com.evanisnor.walletapp.wallet.data.ExampleWalletRepository
import java.time.LocalDate


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